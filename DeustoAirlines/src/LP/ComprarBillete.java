package LP;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import LD.BasesDeDatos;

import javax.swing.JList;

public class ComprarBillete extends JInternalFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	private DefaultTableModel modelo;
	private JList list;
	private JList list_1;
	
	
	private final static int x = (1400) - ((int)465);
	private final static int y = (680) - (480);	

	Connection connection =  BasesDeDatos.getConnection();
	
	/**
	 * Launch the application.
	 */
	
	public ComprarBillete()
	{
		createAndShowGUI();
		llenarLista();
		llenarLista_1();
	}
	

	
	
	/**
	 * Create the frame.
	 */
	public  void createAndShowGUI()
	{
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBilletes = new JLabel("Escoga el viaje que quiere realizar");
		lblBilletes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBilletes.setBounds(10, 90, 327, 33);
		contentPane.add(lblBilletes);
		
		JLabel lblNewLabel = new JLabel("\u00BF A DONDE LE LLEVAMOS?\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(324, 11, 399, 56);
		contentPane.add(lblNewLabel);
		
		JLabel lblDe = new JLabel("DE");
		lblDe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDe.setBounds(409, 90, 22, 14);
		contentPane.add(lblDe);
		
		JLabel lblA = new JLabel("A");
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblA.setBounds(604, 90, 22, 16);
		contentPane.add(lblA);
		
		//DefaultTableModel modelo= new DefaultTableModel();
		//tabla = new JTable();
		//tabla.setBounds(10, 260, 1024, 246);
		//tabla.setModel(modelo);
		//JScrollPane scroll= new JScrollPane(tabla);
		//add(scroll);
		//contentPane.add(tabla);
		
		JLabel lblVuelosOfrecids = new JLabel("VUELOS DISPONIBLES DESTINO-ORIGEN SELECCIONADOS");
		lblVuelosOfrecids.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVuelosOfrecids.setBounds(10, 287, 371, 33);
		contentPane.add(lblVuelosOfrecids);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(420, 542, 69, 43);
		contentPane.add(spinner);
		
		JButton btnNewButton = new JButton("COMPRAR");
		btnNewButton.setBounds(607, 543, 121, 41);
		btnNewButton.setActionCommand("COMPRAR");
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione cuantos billetes desea comprar");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(118, 559, 289, 29);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("CANCELAR");
		btnNewButton_1.setBounds(777, 542, 121, 43);
		btnNewButton_1.setActionCommand("CANCELAR");
		btnNewButton_1.addActionListener(this);
		contentPane.add(btnNewButton_1);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Crear un vuelo");
		setBounds(70, 10, 950, 650);
		getContentPane().setLayout(null);
		
		list = new JList();
		list.setBounds(412, 114, 126, 158);
		contentPane.add(list);
		
		list_1 = new JList();
		list_1.setBounds(602, 114, 126, 158);
		contentPane.add(list_1);
		
		setIconifiable(true);
		setResizable(true);
		
		
	}
	public void cargarDatosTabla(Statement state)
	{
		try{
		String vuelos;
		String fecha;
		int capacidad;
		String SelectBD = "select * from VUELO";
		ResultSet rs = state.executeQuery( SelectBD );
		String [] columnas ={"VUELOS DISPONIBLES (De__A__)", "FECHA DE VUELO","CAPACIDAD DEL VUELO"};
		modelo.setColumnIdentifiers(columnas);
		while (rs.next())
		{
			vuelos= rs.getString("VUELOS DISPONIBLES (De__A__)");
			fecha= rs.getString("FECHA DE VUELO");
			capacidad= rs.getInt("CAPACIDAD DEL VUELO");
			modelo.addRow(new Object[]{vuelos,fecha,capacidad});
		}
		
		}catch(Exception e)
		{
			JOptionPane.showInternalMessageDialog(null,"No ha vuelos disponibles");
			
		}
	}
	
	
	public void llenarLista()
	{
		
		try
		
		{
			
			String query = "select cod_postal_o from vuelo "; 
			
			PreparedStatement pst = connection.prepareStatement(query);
					
			ResultSet rs = pst.executeQuery();
		
			
			DefaultListModel DL = new DefaultListModel();
			
			while(rs.next())
				{
				
					DL.addElement(rs.getString("cod_postal_o"));
				
				}
			
			list.setModel(DL);
			
			pst.close();
			rs.close();
		
		}
		
		catch ( Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	
	public void llenarLista_1()
	{
		
		try
		{
		    String query = "select cod_postal_d from vuelo "; 
			
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
		
			
			DefaultListModel DLM = new DefaultListModel();
			
			while(rs.next())
				{
				
					DLM.addElement(rs.getString("cod_postal_d"));
				
					//list.add(list, rs.getString("cod_vuelo_o"));
				}
			
			list_1.setModel(DLM);
			
			pst.close();
			rs.close();
		}
		
		catch ( Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case "COMPRAR":
				break;
			
			case "CANCELAR":
				this.dispose();
				break;
		}
	}
}
