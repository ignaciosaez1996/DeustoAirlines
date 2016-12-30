package LP;

import static COMUN.Definiciones.CMD_BTN_CANCELAR;
import static COMUN.Definiciones.CMD_BTN_ELIMINAR;
import static COMUN.Definiciones.CMD_BTN_CARGARTABLA;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import net.proteanit.sql.DbUtils;
import LD.BasesDeDatos;
import LN.GestorTrabajador;

import LN.clsVuelo;

public class CancelarVuelo extends JInternalFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	ArrayList<clsVuelo>ListaVuelos;

	JPanel contentPane; 
	
	private JTable table;
	private JButton btnCancelar;

	
	Connection connection = null;
	private JButton btnEliminar_1;
	
	public CancelarVuelo()
	{
		createAndShowGUI();
		connection = BasesDeDatos.getConnection();
	}
	
	
//	//Para refrescar la tabla
//	public void RefrescarTabla()
//	{
//		try
//		{
//			
//			String query = "select * from vuelo";
//			PreparedStatement pat = connection.prepareStatement(query);
//			ResultSet rs = pat.executeQuery();
//			while(rs.next())
//			{
//				System.out.println(rs.getString("cod_vuelo"));
//			}
//			table.setModel(DbUtils.resultSetToTableModel(rs));
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		
//	}
	
	
	private void createAndShowGUI()
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Eliminar un vuelo");
		this.setBounds(260, 30, 802, 597);
		this.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 78, 706, 367);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(490, 490, 102, 30);
		btnCancelar.setActionCommand(CMD_BTN_CANCELAR);
		btnCancelar.addActionListener(this);
		this.getRootPane().setDefaultButton(btnCancelar);
		contentPane.add(btnCancelar);
		
		
		JButton btnCargarTabla = new JButton("Cargar Tabla");
		btnCargarTabla.setBounds(223, 11, 247, 46);
		btnCargarTabla.setActionCommand(CMD_BTN_CARGARTABLA);
		btnCargarTabla.addActionListener(this);
		contentPane.add(btnCargarTabla);
		
		btnEliminar_1 = new JButton("ELIMINAR");
		btnEliminar_1.addActionListener(this);
			// BORRA TODOS LOS VUELOS.. tenemos que pensar alguna manera para que borre		
			//solo los seleccionados

		btnEliminar_1.setActionCommand(CMD_BTN_ELIMINAR);
		btnEliminar_1.setBounds(277, 490, 102, 30);
		contentPane.add(btnEliminar_1);
	
	}
		
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch(arg0.getActionCommand())
		 {	
			case CMD_BTN_CANCELAR:
				this.dispose();
				break;
				
			case CMD_BTN_CARGARTABLA:
				CargarTabla();
				break;
				
			case CMD_BTN_ELIMINAR:
				break;
		 }

	}
	
	public void CargarTabla()
	{
		Statement state = BasesDeDatos.getStatement();
		GestorTrabajador GesTra = new GestorTrabajador();
		ListaVuelos = GesTra.DevolverVuelos(state);
	}
	
	private void CrearTabla()
	{
		table=null;
		TablaVuelosModel tablaModel = new TablaVuelosModel((Map<String, clsVuelo>) ListaVuelos);
		
		table = new JTable(tablaModel);
		table.setBounds(25, 33, 378, 86);
		table.setFillsViewportHeight(true);
		table.setEnabled(true);
		table.setRowSelectionAllowed(true);
		tablaModel.fireTableDataChanged();
		
		table.setVisible(true);
	}
	
class TablaVuelosModel extends AbstractTableModel
{
	
	private static final long serialVersionUID = 1L;
	private String[] columNames = {"cod_vuelo", "capacidad", "fecha" , "cod_psotal_o", "cod_postal_d"};
	Object [][] data;
	
	
	 public TablaVuelosModel(Map<String,clsVuelo> map)
    {        
    	super();   
    	
		int filas = map.size();
		int cont;
		data = new Object[filas][];
		cont=0;    		
		
		//Nos recorremos el map para cargar la variable data[][]
		for (Map.Entry<String,clsVuelo> entry : map.entrySet())
		{
		    //System.out.println(entry.getKey() + "/" + entry.getValue());
			Object[]a = {new String(entry.getValue().getCod_vuelo()),
					   	 new Integer(entry.getValue().getCapacidad()),
					   	 new String(entry.getValue().getFecha()),
					   	 new String(entry.getValue().getCod_postal_o()),
					   	 new String(entry.getValue().getCod_postal_d()) };
			data[cont] = a;
			cont++;
		}
		
    }
	 public void setData(Map<String,clsVuelo> map) 
     {
     	int filas = map.size();
 		int cont;
 		data=new Object[filas][];
 		cont=0;
 		
 		for (Map.Entry<String,clsVuelo> entry : map.entrySet())
 		{
 		    //System.out.println(entry.getKey() + "/" + entry.getValue());
 			Object[]a = {new String(entry.getValue().getCod_vuelo()),
				   	 new Integer(entry.getValue().getCapacidad()),
				   	 new String(entry.getValue().getFecha()),
				   	 new String(entry.getValue().getCod_postal_o()),
				   	 new String(entry.getValue().getCod_postal_d()) };
 			data[cont] = a;
 			cont++;
 		}
     }
/*	public TablaVuelosModel(ArrayList <clsVuelo> MapVuelos)
	{
		super();
		int filas = MapVuelos.size();
		data = new Object [filas][];
		int cont = 0;
		
	/*	for (int i=0; i<MapVuelos.size();i++)
		{
			//No se porqué el int me da error, debería de ser Integer pero no me deja
			Object[]vueloNuevo = {new String(((clsVuelo)MapVuelos.get(i).getCod_vuelo()),
								  new int(((clsVuelo)MapVuelos.get(i).getCapacidad()),
								new String(((clsVuelo)MapVuelos.get(i).getFecha()),
								new String(((clsVuelo)MapVuelos.get(i).getCod_postal_o()),
								new String(((clsVuelo)MapVuelos.get(i).getCod_postal_d()))))))};
		data[cont]=vueloNuevo;
			cont ++;
			}
		}
		
		
	} */
	
	

	
	/*public void setData(ArrayList<clsVuelo>MapVuelos)
	{
		int filas = MapVuelos.size();
		int cont=0;
		data = new Object[filas][];
		
		for (int i=0; i<MapVuelos.size();i++)
		{
//			Object[]vueloNuevo = {new String(((clsVuelo)MapVuelos.get(i).getCod_vuelo()),
//			  new int(((clsVuelo)MapVuelos.get(i).getCapacidad()),
//			  new String(((clsVuelo)MapVuelos.get(i).getFecha())};
//			  data[cont]=vueloNuevo;
//			  cont ++;
//}
		}
	}*/

	@Override
	public int getColumnCount() 
	{
		return columNames.length;
	}

	@Override
	public int getRowCount() 
	{
		return data.length;
	}
	
	public String getColumName (int col)
	{
		return columNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) 
	{
		return data[row][col];
	}
	
	public Class getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}
	
	public boolean isCellEditable(int row, int col)
	{
		return false;
	}
	
	public void setValueAt(Object value, int row, int col)
	{
		data[row][col]=value;
		fireTableCellUpdated(row, col);
	}
	}
}

