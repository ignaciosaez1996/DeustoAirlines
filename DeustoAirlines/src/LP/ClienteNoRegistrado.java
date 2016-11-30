package LP;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


public class ClienteNoRegistrado extends JFrame implements ActionListener
{


	private static final long serialVersionUID = 1L;
	private JLabel 	   		lblcorreo;
	private JLabel 	   		lblContrasena;
	
	private JButton    		btnAceptar;
	private JButton    		btnCancelar;
	
	private JTextField 		txtCorreo;
	private JPasswordField  passwordField;
	private JTextField      txtNombre;
	
	private final static int x = (1400/2) - ((int)465/2);
	private final static int y = (680/2) - (480/2);	
	

	
	public ClienteNoRegistrado() 
	{
		createAndShowGUI();	
	
	}
	private void createAndShowGUI() 	
	{		
		lblcorreo = new JLabel(" Introduzca el correo electrónico:");
		lblcorreo.setFont(new Font("Calibri", Font.BOLD, 14));
		lblcorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblcorreo.setBounds(20, 121, 255, 17);
		getContentPane().add(lblcorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(20, 152, 255, 25);
		getContentPane().add(txtCorreo);
		txtCorreo.setColumns(10);
		
		lblContrasena = new JLabel("Introduzca la contraseña: ");
		lblContrasena.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasena.setFont(new Font("Calibri", Font.BOLD, 14));
		lblContrasena.setBounds(20, 209, 190, 14);
		getContentPane().add(lblContrasena);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(20, 250, 255, 25);
		getContentPane().add(passwordField);
		
		JLabel lblIntroduzcaSuNombre = new JLabel("Introduzca su nombre y apellido: ");
		lblIntroduzcaSuNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblIntroduzcaSuNombre.setFont(new Font("Calibri", Font.BOLD, 14));
		lblIntroduzcaSuNombre.setBounds(20, 38, 255, 17);
		getContentPane().add(lblIntroduzcaSuNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(20, 66, 212, 25);
		getContentPane().add(txtNombre);

		
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setBounds(102, 326, 100, 23);
		btnAceptar.setActionCommand(CMD_BTN_ACEPTAR);
		btnAceptar.addActionListener(this);
		this.getRootPane().setDefaultButton(btnAceptar);
		getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(239, 326, 100, 23);
		btnCancelar.setActionCommand(CMD_BTN_CANCELAR);
		btnCancelar.addActionListener(this);
		this.getRootPane().setDefaultButton(btnCancelar);
		getContentPane().add(btnCancelar);
		
		
		setVisible(true);
		//setMaximizable(true);
		//setClosable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setIconifiable(true);		
		setTitle("Acceder como cliente no registrado");
		setBounds(x, y, 455, 402);
		getContentPane().setLayout(null);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		 {
			case CMD_BTN_ACEPTAR:
				//isEmpty()==trueif its not null
				if(txtCorreo.getText().isEmpty()|| passwordField.getText().isEmpty() ||txtNombre.getText().isEmpty())
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
		BasesDeDatos.crearTablaClienteBD();
		GestorCliente gesCli = new GestorCliente();
		
		String correo = txtCorreo.getText();
		char[] passWord = passwordField.getPassword();
		String contrasenya = String.valueOf(passWord);
		String nombre = txtNombre.getText();
		boolean existe;
		
		existe=gesCli.ExisteCliente(correo);
		gesCli.ClienteNuevo(correo, nombre, contrasenya);
		
		PrincipalCliente objCliente = new PrincipalCliente( );
		objCliente.setVisible(true);
		this.dispose();
		
	}
}

