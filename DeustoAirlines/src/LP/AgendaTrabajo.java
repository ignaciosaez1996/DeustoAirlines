package LP;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import LD.BasesDeDatos;
import javax.swing.JTable;
import javax.swing.JButton;

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
	
	public AgendaTrabajo()
	{
		createAndShowGUI();
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
		
		listaTrabajadores = new JList();
		scrollTrabajadores = new JScrollPane();	
		scrollTrabajadores.setSize(250, 200);
		scrollTrabajadores.setLocation(40, 72);
		scrollTrabajadores.setViewportView(listaTrabajadores);
		contentPane.add(scrollTrabajadores);
		
		JLabel lblVuelosDisponibles = new JLabel("Vuelos disponibles");
		lblVuelosDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblVuelosDisponibles.setBounds(40, 315, 207, 33);
		contentPane.add(lblVuelosDisponibles);
		
		JButton btnNewButton = new JButton("ACEPTAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(255, 552, 117, 33);
		contentPane.add(btnNewButton);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(410, 552, 117, 33);
		contentPane.add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(482, 77, 237, 200);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		table = new JTable();
		table.setBounds(40, 371, 677, 129);
		contentPane.add(table);
		listaTrabajadores.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e)
			{
				trabajadorSeleccionado = listaTrabajadores.getSelectedValue().toString();
			}
		});
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Agenda de Trabajo");
		setBounds(70, 10, 950, 650);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
	}
}
