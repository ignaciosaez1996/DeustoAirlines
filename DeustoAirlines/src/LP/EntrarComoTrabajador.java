package LP;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;
import LD.BasesDeDatos;
import LN.GestorCliente;
import LN.GestorTrabajador;
import static COMUN.Definiciones.*;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class EntrarComoTrabajador extends JFrame implements ActionListener
{


	private static final long serialVersionUID = 1L;
	private JLabel 	   		lblDNI;
	private JLabel 	   		lblContrasena;

	
	private JButton    		btnAceptar;
	private JButton    		btnCancelar;
	
	private JTextField 		txtDNI;
	private JPasswordField passwordField;
	

	Connection connection = null;

	
	private final static int x = (1400) - ((int)465);
	private final static int y = (680) - (480);	
	private JTable table_1;
	private JScrollPane scrollPane;
	private JLabel lbliconoData;
	private JComboBox comboBox;
	
	public EntrarComoTrabajador() 
	{
		createAndShowGUI();	
		
		connection = BasesDeDatos.getConnection();
		llenarCombo();
		VerTrabajadores();
	}
	
	private void createAndShowGUI() 	
	{	
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try{
					
				
				String query = "select * from trabajador where dni_tra = ?";
				PreparedStatement pat = connection.prepareStatement(query);
				pat.setString(1, (String)comboBox.getSelectedItem());
				
				ResultSet rs = pat.executeQuery();	
				
				while(rs.next())
				{
					txtDNI.setText(rs.getString("dni_tra"));
					passwordField.setText(rs.getString("contrasenya_tra"));
					
					
				}
				
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				
			}
		});
		comboBox.setBounds(33, 11, 190, 41);
		getContentPane().add(comboBox);
		lblDNI = new JLabel("Introduzca el DNI (debe contener 9 caracteres): ");
		lblDNI.setFont(new Font("Calibri", Font.BOLD, 14));
		lblDNI.setHorizontalAlignment(SwingConstants.LEFT);
		lblDNI.setBounds(33, 92, 299, 17);
		getContentPane().add(lblDNI);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(33, 131, 274, 35);
		getContentPane().add(txtDNI);
		txtDNI.setColumns(10);
		
		lblContrasena = new JLabel("Introduzca la contraseña: ");
		lblContrasena.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasena.setFont(new Font("Calibri", Font.BOLD, 14));
		lblContrasena.setBounds(33, 202, 190, 14);
		getContentPane().add(lblContrasena);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(33, 238, 274, 35);
		getContentPane().add(passwordField);
		
		
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setBounds(307, 326, 100, 23);
		btnAceptar.setActionCommand(CMD_BTN_ACEPTAR);
		btnAceptar.addActionListener(this);
		this.getRootPane().setDefaultButton(btnAceptar);
		getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(459, 326, 100, 23);
		btnCancelar.setActionCommand(CMD_BTN_CANCELAR);
		btnCancelar.addActionListener(this);
		this.getRootPane().setDefaultButton(btnCancelar);
		getContentPane().add(btnCancelar);
				
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Acceder como trabajador");
		//setBounds(x, y, 737, 400);
		setBounds(250, 200 , 900, 400);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(373, 54, 501, 141);
		getContentPane().add(scrollPane);
		
		table_1 = new JTable()
		{
			public boolean isCellEditable ( int rowIndez, int colIndex)
			{
				return false;
			}
			
		};
		
		scrollPane.setViewportView(table_1);
		
		lbliconoData = new JLabel("");
		lbliconoData.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/AvionNoche.jpg")));
		lbliconoData.setBounds(0, -45, 1059, 408);
		getContentPane().add(lbliconoData);
	}
	
	
	public void llenarCombo()
	{
		
	try
	{
		
		
		String query = "select * from trabajador ";
		PreparedStatement pat = connection.prepareStatement(query);
	
	
		ResultSet rs = pat.executeQuery();	
	
		while(rs.next())
		{
			comboBox.addItem(rs.getString("dni_tra"));
		}
	
	}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 	
	{
		switch(e.getActionCommand())
		 {
			case CMD_BTN_ACEPTAR:
				//isEmpty()==trueif its not null
				if(txtDNI.getText().isEmpty()|| passwordField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(this, "Rellene todos los campos");
				}
				else
				{
					this.Trabajador();
				}
				break;
				
			case CMD_BTN_CANCELAR:
				Cancelar();
				break;	
				
			//case CMD_VERTRABAJADORES:
				//VerTrabajadores();
				//break;
				
		} 
	}
	
	private void Cancelar()
	{
		Principal objPrincipal = new Principal();
		objPrincipal.setVisible(true);
		this.dispose();
	}
	
	private void VerTrabajadores()
	{
		try
		{
			String query = "select * from trabajador";
			PreparedStatement pat = connection.prepareStatement(query);
			ResultSet rs = pat.executeQuery();	
			
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void Trabajador() 
	{
		GestorTrabajador gesTra = new GestorTrabajador();
		Statement state = BasesDeDatos.getStatement();
		String DNI = txtDNI.getText();
		char[] passWord = passwordField.getPassword();
		String contrasenya = String.valueOf(passWord);
		boolean existe;
	
		
		existe = gesTra.ValidarEntradaTra(state, DNI, contrasenya);
		if(existe == true)
		{
			PrincipalTrabajador objTrabajador = new PrincipalTrabajador();
			objTrabajador.setVisible(true);
			this.dispose();
		}
		else
		{
			JOptionPane.showMessageDialog(this, "DNI o contraseña incorrectas, vuelva a introducirlas");
		}
	}
}
