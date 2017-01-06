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
import java.util.Random;

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
	private JList listCodPostalO;
	private JList listCodPostalD;
	private JList listCodVuelo ;
	private String seleccionado1;
	private String seleccionado2;
	private String codVueloSelec;
	private JSpinner spinner;
	private JScrollPane scrollLista;
	private JScrollPane scrollLista2;
	private JScrollPane scrollLista3;


	Connection connection =  BasesDeDatos.getConnection();
	
	/**
	 * Launch the application.
	 */
	String correo;
	
	public ComprarBillete(String correo)
	{
		this.correo = correo;
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
		
		tabla = new JTable()
		{
			public boolean isCellEditable ( int rowIndez, int colIndex)
			{
				return false;
			}
		};
		JScrollPane scroll= new JScrollPane(tabla);
		scrollPane.setViewportView(scroll);
		
		JLabel lblVuelosOfrecids = new JLabel("VUELOS DISPONIBLES DESTINO-ORIGEN SELECCIONADOS");
		lblVuelosOfrecids.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVuelosOfrecids.setBounds(10, 187, 371, 33);
		contentPane.add(lblVuelosOfrecids);
		
		spinner = new JSpinner();
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
				GestorCliente objGC = new GestorCliente();
				Statement state = BasesDeDatos.getStatement();
				boolean existe;
				existe = objGC.EncontrarVueloOrigenDestino(state, seleccionado1, seleccionado2);
				if(listCodPostalO.hasFocus()==false || listCodPostalD.hasFocus()==false)
				{
					try
					{
						String query = "select * from VUELO where (cod_postal_o = '" + seleccionado1 + "' and cod_postal_d = '" + seleccionado2 + "')";
						PreparedStatement pat = connection.prepareStatement(query);
						ResultSet rs = pat.executeQuery();
						tabla.setModel(DbUtils.resultSetToTableModel(rs));
					if(existe==false)
					{
						JOptionPane.showMessageDialog(null,"¡No hay vuelos disponibles origen-destino!");
						
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
		setBounds(70, 10, 1000, 693);
		getContentPane().setLayout(null);
		
		listCodPostalO = new JList();
		scrollLista = new JScrollPane();
		scrollLista.setBounds(415, 90, 146, 65);
		scrollLista.setViewportView(listCodPostalO);
		contentPane.add(scrollLista);
		listCodPostalO.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				seleccionado1=listCodPostalO.getSelectedValue().toString();
				
			}
		});
	
		
		listCodPostalD = new JList();
		//contentPane.add(list_1);
		scrollLista2 = new JScrollPane();
		scrollLista2.setViewportView(listCodPostalD);
		scrollLista2.setBounds(610, 90, 146, 65);
		contentPane.add(scrollLista2);
		listCodPostalD.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				seleccionado2=listCodPostalD.getSelectedValue().toString();
			}
		});
	
		
		JLabel lblEligaElOrigen = new JLabel("Elige el codigo del vuelo");
		lblEligaElOrigen.setBounds(95, 414, 242, 33);
		contentPane.add(lblEligaElOrigen);
		
		listCodVuelo = new JList();
		scrollLista3 = new JScrollPane();
		scrollLista3.setViewportView(listCodVuelo);
		scrollLista3.setBounds(95, 441, 334, 98);
		contentPane.add(scrollLista3);
		listCodVuelo.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				codVueloSelec=listCodVuelo.getSelectedValue().toString();
			}
		});
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Comprar Billete");
		this.setBounds(260, 10, 1000, 1000);
		
		
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
			listCodPostalO.setModel(DL);
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
				}
			listCodPostalD.setModel(DLM);
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
			listCodVuelo.setModel(DLc);
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
		BasesDeDatos.crearTablaBilleteBD();
		GestorCliente gesCli = new GestorCliente();
		Statement state = BasesDeDatos.getStatement();
		String precio = gesCli.DevolverPrecio(state, codVueloSelec);
		int cantidad = (int) spinner.getValue();
		boolean comprado; //Si es false es que no se ha podido comprar
		
		for(int i=0; i<cantidad; i++)
		{
			Random rnd = new Random();
			int cod_billete = rnd.nextInt(10000);
			comprado = gesCli.Comprar(state, cod_billete, precio, codVueloSelec, correo);
			if(comprado == true)
			{
				JOptionPane.showMessageDialog(null, "Billete comprado");
				this.dispose();
			}
		}
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case "COMPRAR":
				int cantidad = (int) spinner.getValue();
				if(cantidad >0)
				{
					RealizarCompra();
				}else
				{
					JOptionPane.showMessageDialog(null, "Seleccione cuantos billetes desea comprar");
				}
				break;
			
			case "CANCELAR":
				this.dispose();
				break;
		}
	}
}
