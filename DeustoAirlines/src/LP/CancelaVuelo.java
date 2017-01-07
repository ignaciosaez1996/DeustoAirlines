package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LD.BasesDeDatos;
import LN.GestorTrabajador;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;

import net.proteanit.sql.DbUtils;

/**
 * Clase que crea un formulario que será llamado desde PrincipalTrabajador y recogera los datos necesarios para eliminar un vuelo.
 * Despues se los mandara a GestorTrabajador para que elimine el vuelo de la Base de Datos
 */
public class CancelaVuelo extends JInternalFrame implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	
	Connection connection = null;
	JPanel contentPane;
	private JTable table;
	private JList list ;
	private String codVueloSelec;
	
	/**
	 * Crea el JInternalFrame.
	 * Obtiene la conexion con la Base de Datos.
	 * Llama al metodo para que rellene la lista con los codigos de los vuelos.
	 * Llama al metodo para que rellene la tabla con la informacion completa de los vuelos existentes.
	 */
	public CancelaVuelo()
	{
		createAndShowGUI();
		connection = BasesDeDatos.getConnection();
		llenarLista();
		LlenarTabla();
	}
	
	/**
	 * Crea las etiquetas, campos de texto y botones y los agrega a la ventana de Agenda de Trabajo
	 */
	private void createAndShowGUI()
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 68, 667, 161);
		contentPane.add(scrollPane);
		
		table = new JTable()
		{
			public boolean isCellEditable ( int rowIndez, int colIndex)
			{
				return false;
			}
			
		};
		scrollPane.setViewportView(table);
		
		JLabel lblEligeElCodigo = new JLabel("Elige el codigo de vuelo que quiera eliminar");
		lblEligeElCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEligeElCodigo.setBounds(97, 261, 373, 44);
		contentPane.add(lblEligeElCodigo);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(96, 312, 235, 153);
		contentPane.add(scrollPane_1);
		
		 list = new JList();
		scrollPane_1.setViewportView(list);
		list.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				codVueloSelec = list.getSelectedValue().toString();
			}
		});
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setActionCommand("ELIMINAR");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(492, 312, 118, 50);
		contentPane.add(btnEliminar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setActionCommand("CANCELAR");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(492, 388, 118, 50);
		contentPane.add(btnCancelar);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Cancelar Vuelo");
		this.setBounds(260, 30, 859, 540);
		
	}
	
	/**
	 * Metodo que se encarga de rellenar la lista con los codigos de los vuelos que existan
	 */
	public void llenarLista()
	{
		try
		{
			String query = "select cod_vuelo from vuelo "; 
			PreparedStatement pst = connection.prepareStatement(query);	
			ResultSet rs = pst.executeQuery();
			DefaultListModel DL = new DefaultListModel();
			while(rs.next())
				{
					DL.addElement(rs.getString("cod_vuelo"));
				}
			list.setModel(DL);
			pst.close();
			rs.close();
		}
		catch ( Exception e)
		{
			JOptionPane.showInternalMessageDialog(null,"No hay vuelos disponibles");
		}
	}
	
	/**
	 * Metodo encargado de rellenar la tabla con la informacion completa de los vuelos existentes
	 */
	public void LlenarTabla()
	{
		try
		{
			String query = "select * from vuelo";
			PreparedStatement pat = connection.prepareStatement(query);
			ResultSet rs = pat.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception a)
		{
			a.printStackTrace();
		}
		
	}
	
	/**
	 * Metodo para poder detectar cuando un boton es pulsado.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		switch(arg0.getActionCommand())
		{
			case "ELIMINAR":
				boolean aviso = false;
				if(codVueloSelec!=null)
				{
					aviso = this.Aviso();
					if(aviso!=true)
					{
						this.EliminarVuelo();
						this.dispose();
					}
				}else
				{
					JOptionPane.showMessageDialog(null, "Elija el vuelo que desea eliminar");
				}
				break;
			
			case "CANCELAR":
				this.dispose();
				break;
		}
		
	}
	
	/**
	 * Metodo que vuelve a preguntar al trabajador si esta seguro de que desea eliminar el vuelo
	 * @return true en caso de que el trabajador no quiera eliminar el vuelo
	 */
	private boolean Aviso() 
	{
		boolean aviso = false;
		 int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar?", "DeustoAirlines - Aviso",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    
	    if (respuesta == JOptionPane.NO_OPTION) 
	    {
	      aviso = true;
	    } 
	    else if (respuesta == JOptionPane.YES_OPTION) 
	    {
	      aviso = false;
	    } 
	    else if (respuesta == JOptionPane.CLOSED_OPTION) 
	    {
	      aviso = true;
	    }
	    
		return aviso;		
	}
	
	/**
	 * Metodo encargado de recoger el codigo de vuelo seleccionado y mandarselo al GestorTrabajador para que lo elimine
	 */
	public void EliminarVuelo()
	{
		GestorTrabajador  gesTra = new GestorTrabajador();
		Statement state = BasesDeDatos.getStatement();

		String cod_vuelo = codVueloSelec;
		
		gesTra.CancelarVuelo(state, cod_vuelo);
	}
}
