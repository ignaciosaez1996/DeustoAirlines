package LP;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import LD.BasesDeDatos;
import LN.GestorCliente;
import static COMUN.Definiciones.*;


public class ClienteRegistrado extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 1L;
	private JLabel 	   		lblcorreo;
	private JLabel 	   		lblContrasena;

	
	private JButton    		btnAceptar;
	private JButton    		btnCancelar;
	
	private JTextField 		txtCorreo;
	private JPasswordField passwordField;

	
	
	
	@SuppressWarnings("unused")

	private int DNI;

	
	private final static int x = (1400/2) - ((int)465/2);
	private final static int y = (680/2) - (480/2);	

	
	public ClienteRegistrado() 
	{
		createAndShowGUI();	
	}
	
	private void createAndShowGUI() 	
	{		
		lblcorreo = new JLabel("Introduzca el correo electrónico: ");
		lblcorreo.setFont(new Font("Calibri", Font.BOLD, 14));
		lblcorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblcorreo.setBounds(33, 51, 304, 17);
		getContentPane().add(lblcorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(33, 79, 257, 32);
		getContentPane().add(txtCorreo);
		txtCorreo.setColumns(10);
		
		lblContrasena = new JLabel("Introduzca la contraseña: ");
		lblContrasena.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasena.setFont(new Font("Calibri", Font.BOLD, 14));
		lblContrasena.setBounds(33, 141, 190, 14);
		getContentPane().add(lblContrasena);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(33, 185, 257, 32);
		getContentPane().add(passwordField);

		
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setBounds(96, 249, 100, 23);
		btnAceptar.setActionCommand(CMD_BTN_ACEPTAR);
		btnAceptar.addActionListener(this);
		this.getRootPane().setDefaultButton(btnAceptar);
		getContentPane().add(btnAceptar);
		
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(237, 249, 100, 23);
		btnCancelar.setActionCommand(CMD_BTN_CANCELAR);
		btnCancelar.addActionListener(this);
		this.getRootPane().setDefaultButton(btnCancelar);
		getContentPane().add(btnCancelar);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		setTitle("Acceso para los clientes registrados");
		setBounds(x, y, 418, 330);
		getContentPane().setLayout(null);

	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		
		 {
			case CMD_BTN_ACEPTAR:
				//isEmpty()==trueif its not null
				if(txtCorreo.getText().isEmpty()|| passwordField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(this, "Rellene todos los campos");
				}
				else
				{
					this.Cliente();
				}
				break;
			
			case CMD_BTN_CANCELAR:
				this.dispose();
				break;
		} 
		
	}
	
	private void Cliente() 
	{
		String correo = txtCorreo.getText();
		char[] passWord = passwordField.getPassword();
		String contrasenya = String.valueOf(passWord);
		GestorCliente gesCli = new GestorCliente(correo, contrasenya);
		boolean existe; 
		Statement state = BasesDeDatos.getStatement();
		
		existe = gesCli.ValidarEntradaCli(state, correo, contrasenya);
		if(existe == true)
		{
			PrincipalCliente objCliente = new PrincipalCliente( );
			objCliente.setVisible(true);
			this.dispose();
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectas, vuelva a introducirlas");
		}
	}
}
	
	

