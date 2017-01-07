package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;

import LD.BasesDeDatos;
import LN.GestorTrabajador;
import static COMUN.Definiciones.*;

/**
 * Clase que crea un formulario que será llamado desde PrincipalTrabajador y despues de recoger los datos necesarios mostrara por pantalla las tareas
 * asignadas a cada trabajador
 */
public class CalendarioTrabajo extends JInternalFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList listVuelos;
	private JList list;
	Connection connection;
	String trabajadorSeleccionado;
	
	/**
	 * Crea el JInternalFrame.
	 * Obtiene la conexion con la Base de Datos.
	 * Llama al metodo para que cargue la lista con los trabajadores existentes.
	 */
	public CalendarioTrabajo()
	{
		connection = BasesDeDatos.getConnection();
		createAndShowGUI();
		CargarLista();
	}
	
	/**
	 * Crea las etiquetas, campos de texto y botones y los agrega a la ventana de Agenda de Trabajo
	 */
	public void createAndShowGUI()
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lista de trabajadores");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(85, 51, 253, 39);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 100, 573, 204);
		contentPane.add(scrollPane);
		
		list = new JList();
		list.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				trabajadorSeleccionado = list.getSelectedValue().toString();
			}
		});
		scrollPane.setViewportView(list);
		
		JButton btnElegirTrabajador = new JButton("ELEGIR TRABAJADOR");
		btnElegirTrabajador.setActionCommand(CMD_ELEGIRTRABAJADOR);
		btnElegirTrabajador.addActionListener(this);
		btnElegirTrabajador.setBounds(498, 315, 161, 31);
		contentPane.add(btnElegirTrabajador);
		
		JLabel lblNewLabel_1 = new JLabel("Lista de vuelos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(85, 351, 203, 39);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(85, 401, 574, 142);
		contentPane.add(scrollPaneTable);
		
		listVuelos = new JList();
		scrollPaneTable.setViewportView(listVuelos);
		
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setActionCommand(CMD_BTN_ACEPTAR);
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(273, 578, 112, 31);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setActionCommand(CMD_BTN_CANCELAR);
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(427, 578, 112, 31);
		contentPane.add(btnCancelar);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Calendario de Trabajo");
		this.setBounds(260, 10, 746, 650);
	}
	
	/**
	 * Metodo para poder detectar cuando un boton es pulsado.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case CMD_BTN_ACEPTAR:
				this.dispose();
				break;
				
			case CMD_BTN_CANCELAR:
				this.dispose();
				break;
				
			case CMD_ELEGIRTRABAJADOR:
				if(trabajadorSeleccionado!=null && trabajadorSeleccionado!="")
				{
					ElegirTrabajador();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Seleccione un trabajador");
				}
				
				break;
		}
	}
	
	/**
	 * Metodo que se encarga de rellenar la lista con las tareas del trabajador que se haya seleccionado
	 */
	public void ElegirTrabajador()
	{
		GestorTrabajador  gesTra = new GestorTrabajador();
		Statement state = BasesDeDatos.getStatement();
		String dni_tra = trabajadorSeleccionado.substring(0, 9);
		ArrayList<String> rs = gesTra.CodTarea(state, dni_tra, connection);
		if(rs!=null)
		{
			DefaultListModel DL = new DefaultListModel();
			ResultSet rs1;
			for(int i=0; i<rs.size();i++)
			{
				try 
				{
					String query = "select * from VUELO where (cod_vuelo = '" + rs.get(i) + "' )";
					PreparedStatement pat;
					pat = connection.prepareStatement(query);
					rs1 = pat.executeQuery();
					DL.addElement(rs1.getString("cod_vuelo").concat(" --- ").concat(rs1.getString("fecha").concat(" --- ").concat(rs1.getString("cod_postal_o")).concat(" --- ").concat(rs1.getString("cod_postal_d"))));
					
				} catch (SQLException e)
				{
					JOptionPane.showMessageDialog(null, "La creación de tabla ha fallado");
					e.printStackTrace();
				}
			}
			listVuelos.setModel(DL);
		}
	}
	
	/**
	 * Metodo encargado de mostrar por pantalla los trabajadores existentes
	 */
	public void CargarLista()
	{
		try
		{
			String query = "select dni_tra, nombre_tra, categoria from trabajador"; 
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
		
			DefaultListModel DL = new DefaultListModel();
			while(rs.next())
				{
					DL.addElement(rs.getString("dni_tra").concat(" -- ").concat(rs.getString("nombre_tra")).concat(" -- ").concat(rs.getString("categoria")));
					DL.addElement("");
				}
			
			list.setModel(DL);
			
			pst.close();
			rs.close();
		}
		catch ( Exception e)
		{
			JOptionPane.showInternalMessageDialog(null,"No hay trabajadores disponibles");
		}
	}
}
