package LP;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import LD.BasesDeDatos;
import LN.GestorCliente;

import javax.swing.JList;

import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;

public class ComprarBillete extends JInternalFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	private DefaultTableModel modelo;
	private JList list;
	private JList list_1;
	private JList list_2 ;
	private String seleccionado1;
	private String seleccionado2;
	private JScrollPane scrollLista;
	private JScrollPane scrollLista2;

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
		llenarLista_2();
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
		
		JLabel lblBilletes = new JLabel("Escoja el viaje que quiere realizar");
		lblBilletes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBilletes.setBounds(10, 90, 327, 33);
		contentPane.add(lblBilletes);
		
		JLabel lblNewLabel = new JLabel("\u00BF A DONDE LE LLEVAMOS?\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(324, 11, 399, 56);
		contentPane.add(lblNewLabel);
		
		JLabel lblDe = new JLabel("DE");
		lblDe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDe.setBounds(365, 100, 22, 14);
		contentPane.add(lblDe);
		
		JLabel lblA = new JLabel("A");
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblA.setBounds(580, 100, 22, 16);
		contentPane.add(lblA);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 235, 900, 168);
		contentPane.add(scrollPane);
		
		tabla = new JTable();
		JScrollPane scroll= new JScrollPane(tabla);
		scrollPane.setViewportView(scroll);
		
		JLabel lblVuelosOfrecids = new JLabel("VUELOS DISPONIBLES DESTINO-ORIGEN SELECCIONADOS");
		lblVuelosOfrecids.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVuelosOfrecids.setBounds(10, 187, 371, 33);
		contentPane.add(lblVuelosOfrecids);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(420, 566, 69, 43);
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
		
		JButton btnNewButton_2 = new JButton("FILTRAR");
		btnNewButton_2.setBounds(777, 90, 121, 43);
		btnNewButton_2.setActionCommand("FILTRAR");
		btnNewButton_2.addActionListener(this);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				GestorCliente objGC = new GestorCliente(null, null);
				Statement state = BasesDeDatos.getStatement();
				boolean existe;
				existe = objGC.EncontrarVueloOrigenDestino(state, seleccionado1, seleccionado2);
				if(list.hasFocus()==false || list_1.hasFocus()==false)
				{
					try
					{
						String query = "select * from VUELO where (cod_postal_o = '" + seleccionado1 + "' and cod_postal_d = '" + seleccionado2 + "')";
						PreparedStatement pat = connection.prepareStatement(query);
						ResultSet rs = pat.executeQuery();
						tabla.setModel(DbUtils.resultSetToTableModel(rs));
					if(existe==false)
					{
						JOptionPane.showMessageDialog(null,"�No hay vuelos disponibles origen-destino!");
						
					}				
					}catch(Exception e)
					{
						JOptionPane.showInternalMessageDialog(null,"No hay vuelos disponibles con el origen y destino seleccionados");
					}
					}
				}
				
			
		});
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Compra de billete");
		setBounds(70, 10, 950, 650);
		getContentPane().setLayout(null);
		
		list = new JList();
		scrollLista = new JScrollPane();
		scrollLista.setBounds(415, 90, 146, 65);
		scrollLista.setViewportView(list);
		contentPane.add(scrollLista);
		list.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				seleccionado1=list.getSelectedValue().toString();
				
			}
		});
	
		
		list_1 = new JList();
		//contentPane.add(list_1);
		scrollLista2 = new JScrollPane();
		scrollLista2.setViewportView(list_1);
		scrollLista2.setBounds(610, 90, 146, 65);
		contentPane.add(scrollLista2);
	
		
		JLabel lblEligaElOrigen = new JLabel("Elige el codigo del vuelo");
		lblEligaElOrigen.setBounds(95, 414, 242, 33);
		contentPane.add(lblEligaElOrigen);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(95, 441, 334, 98);
		contentPane.add(scrollPane_1);
		
		list_2 = new JList();
		scrollPane_1.setViewportView(list_2);
	
	
		
		list_1.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				seleccionado2=list_1.getSelectedValue().toString();
				
			}
		});
	
		setIconifiable(true);
		setResizable(true);
		
		
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
			JOptionPane.showInternalMessageDialog(null,"No hay vuelos disponibles");
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
			JOptionPane.showInputDialog("No hay vuelos disponibles");
		}
		
		
	}
	

	public void llenarLista_2()
	{
		try
		{
			
			String query = "select cod_vuelo from vuelo "; 
			
			PreparedStatement pst = connection.prepareStatement(query);
					
			ResultSet rs = pst.executeQuery();
		
			DefaultListModel DLc = new DefaultListModel();
			
			while(rs.next())
				
				{
				
					DLc.addElement(rs.getString("cod_vuelo"));
					
				
				}
			
			list_2.setModel(DLc);
			
			pst.close();
			rs.close();
		
		}
		
		catch ( Exception e)
		{
			JOptionPane.showInternalMessageDialog(null,"No hay vuelos disponibles");
		}
		
		
	}
	
	
	
	public void RealizarCompra()
	{
		
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
//			case "FILTRAR":
//				this.dispose();
//				break;	
		}
	}
}
