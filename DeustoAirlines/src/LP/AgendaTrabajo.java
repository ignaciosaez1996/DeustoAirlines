package LP;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import LD.BasesDeDatos;
import LN.GestorTrabajador;
import static COMUN.Definiciones.*;

import javax.swing.JTable;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;
import javax.swing.SwingConstants;

public class AgendaTrabajo extends JInternalFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList listaVuelos;
	private JList listaTrabajadores;
	private JScrollPane scrollVuelos;
	private JScrollPane scrollTrabajadores;
	private String vueloSeleccionado;
	private String trabajadorSeleccionado;
	
	Connection connection = BasesDeDatos.getConnection();
	private JTable table;
	private JScrollPane scrollPane;
	
	public AgendaTrabajo()
	{
		createAndShowGUI();
		llenarListaVuelos();
		llenarListaTrabajadores();
	}
	
	public void createAndShowGUI()
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVuelo = new JLabel("Escoja el vuelo al que quiera asignar el trabajador");
		lblVuelo.setBounds(34, 39, 428, 27);
		lblVuelo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblVuelo);
		
		JLabel lblTrabajador = new JLabel("Escoja el trabajador que quiera asignar al puesto");
		lblTrabajador.setBounds(472, 39, 462, 27);
		lblTrabajador.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblTrabajador);
		
		JLabel lblVuelosDisponibles = new JLabel("Vuelos disponibles");
		lblVuelosDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblVuelosDisponibles.setBounds(40, 315, 207, 33);
		contentPane.add(lblVuelosDisponibles);
		
		JButton btnAceptar = new JButton("ASIGNAR");
		btnAceptar.setBounds(255, 552, 117, 33);
		btnAceptar.setActionCommand(CMD_BTN_ACEPTAR);
		btnAceptar.addActionListener(this);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(410, 552, 117, 33);
		btnCancelar.setActionCommand(CMD_BTN_CANCELAR);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);
		
		listaVuelos = new JList();
		scrollVuelos = new JScrollPane();		
		scrollVuelos.setSize(250, 200);
		scrollVuelos.setLocation(40, 72);
		scrollVuelos.setViewportView(listaVuelos);
		contentPane.add(scrollVuelos);
		listaVuelos.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e)
			{
				vueloSeleccionado = listaVuelos.getSelectedValue().toString();
			}
		});
		
		listaTrabajadores= new JList();
		scrollTrabajadores = new JScrollPane();
		scrollTrabajadores.setBounds(482, 77, 237, 200);
		scrollTrabajadores.setViewportView(listaTrabajadores);
		contentPane.add(scrollTrabajadores);
		listaTrabajadores.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e)
			{
				trabajadorSeleccionado = listaTrabajadores.getSelectedValue().toString();
			}
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 371, 677, 158);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnCargarTabla = new JButton("Cargar tabla");
		btnCargarTabla.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCargarTabla.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					String query = "select * from vuelo";
					PreparedStatement pat = connection.prepareStatement(query);
					ResultSet rs = pat.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		btnCargarTabla.setBounds(335, 323, 127, 23);
		contentPane.add(btnCargarTabla);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Agenda de Trabajo");
		setBounds(70, 10, 950, 650);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case CMD_BTN_CANCELAR:
				this.dispose();
				break;
			
			case CMD_BTN_ACEPTAR:
				if(vueloSeleccionado!=null && trabajadorSeleccionado!=null && trabajadorSeleccionado!="------------")
				{
					this.Asignar();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Elija un vuelo y un trabajador");
				}
				break;
		}
	}
	
	public void Asignar()
	{
		BasesDeDatos.crearTablaTareaBD();
		GestorTrabajador  gesTra = new GestorTrabajador();
		Statement state = BasesDeDatos.getStatement();
		boolean registrado;
		
		String cod_vuelo = vueloSeleccionado;
		String dni_tra = trabajadorSeleccionado.substring(0, 9);
		do
		{
			registrado = gesTra.CrearTarea(state, cod_vuelo, dni_tra);
			JOptionPane.showMessageDialog(this, "Tarea registrada");
		}while(registrado==false);
		this.dispose();
		
	}
	
	public void llenarListaVuelos()
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
			
			listaVuelos.setModel(DL);
			
			pst.close();
			rs.close();
		}
		catch ( Exception e)
		{
			JOptionPane.showInternalMessageDialog(null,"No hay vuelos disponibles");
		}
	}
	
	public void llenarListaTrabajadores()
	{
		try
		{
			String query = "select dni_tra, categoria from trabajador"; 
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
		
			DefaultListModel DL = new DefaultListModel();
			while(rs.next())
				{
					DL.addElement(rs.getString("dni_tra").concat("--").concat(rs.getString("categoria")));
					DL.addElement("------------");
				}
			
			listaTrabajadores.setModel(DL);
			
			pst.close();
			rs.close();
		}
		catch ( Exception e)
		{
			JOptionPane.showInternalMessageDialog(null,"No hay trabajadores disponibles");
		}
	}
}
