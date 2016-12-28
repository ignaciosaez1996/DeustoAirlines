package LP;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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

public class ComprarBillete extends JInternalFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	private DefaultTableModel modelo;
	
	private final static int x = (1400) - ((int)465);
	private final static int y = (680) - (480);	

	/**
	 * Launch the application.
	 */
	
	public ComprarBillete()
	{
		createAndShowGUI();
	}
	
	public void llenarComboBox()
	{
		
		try
		{
			
		}
		
		catch ( Exception e)
		{
			e.printStackTrace();
		}
		
		
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
		lblBilletes.setBounds(10, 126, 327, 33);
		contentPane.add(lblBilletes);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(420, 136, 94, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("\u00BF A DONDE LE LLEVAMOS?\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(324, 11, 399, 56);
		contentPane.add(lblNewLabel);
		
		JLabel lblDe = new JLabel("DE");
		lblDe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDe.setBounds(388, 140, 22, 14);
		contentPane.add(lblDe);
		
		JLabel lblA = new JLabel("A");
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblA.setBounds(575, 139, 22, 16);
		contentPane.add(lblA);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(607, 136, 89, 19);
		contentPane.add(comboBox_1);
		
		//DefaultTableModel modelo= new DefaultTableModel();
		//tabla = new JTable();
		//tabla.setBounds(10, 260, 1024, 246);
		//tabla.setModel(modelo);
		//JScrollPane scroll= new JScrollPane(tabla);
		//add(scroll);
		//contentPane.add(tabla);
		
		JLabel lblVuelosOfrecids = new JLabel("VUELOS DISPONIBLES DESTINO-ORIGEN SELECCIONADOS");
		lblVuelosOfrecids.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVuelosOfrecids.setBounds(10, 199, 371, 33);
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
