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

/**
 * Clase para registrar aquellos clientes que acceden por primera vez a la aplicación
 */
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
	
	/**
	 * El constructor de la clase, que  llama al metodo que crea el JInternalFrame
	 */
	
	public ClienteNoRegistrado() 
	{
		createAndShowGUI();	
	}
	
	/**
	 * Crea las etiquetas, campos de texto y botones y los agrega a la ventana de Cliente No Registrado
	 */
	private void createAndShowGUI() 	
	{		
		lblcorreo = new JLabel("Introduzca el correo electrónico:");
		lblcorreo.setFont(new Font("Calibri", Font.BOLD, 14));
		lblcorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblcorreo.setBounds(70, 121, 255, 17);
		getContentPane().add(lblcorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(70, 152, 257, 32);
		getContentPane().add(txtCorreo);
		txtCorreo.setColumns(10);
		
		lblContrasena = new JLabel("Introduzca la contraseña: ");
		lblContrasena.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasena.setFont(new Font("Calibri", Font.BOLD, 14));
		lblContrasena.setBounds(70, 209, 190, 14);
		getContentPane().add(lblContrasena);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(70, 250, 257, 32);
		getContentPane().add(passwordField);
		
		JLabel lblIntroduzcaSuNombre = new JLabel("Introduzca su nombre y apellido: ");
		lblIntroduzcaSuNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblIntroduzcaSuNombre.setFont(new Font("Calibri", Font.BOLD, 14));
		lblIntroduzcaSuNombre.setBounds(70, 38, 255, 17);
		getContentPane().add(lblIntroduzcaSuNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(70, 66, 257, 32);
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		setTitle("Acceso para los clientes no registrados");
		setBounds(440, 200, 455, 402);
		getContentPane().setLayout(null);
		
		
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
				Cancelar();
				break;

		} 
		
	}
	
	private void Cancelar()
	{
		EntrarComoCliente objCliente = new EntrarComoCliente();
		objCliente.setVisible(true);
		this.dispose();
	}
	
	
	/**
	 * Metodo en el cual se abre la conexión con la base de datos y mediante la llamada a un metodo del gestor se crea el cliente.
	 */
	private void Cliente() 
	{
		BasesDeDatos.crearTablaClienteBD();
		
		String correo = txtCorreo.getText();
		char[] passWord = passwordField.getPassword();
		String contrasenya = String.valueOf(passWord);
		String nombre = txtNombre.getText();
		GestorCliente gesCli = new GestorCliente();
		
		//Devuelve true si ya existe algun cliente con ese correo
		boolean existe;
		Statement state = BasesDeDatos.getStatement();
		existe=gesCli.ExisteCliente(state, correo);
		if(existe==true)
		{
			JOptionPane.showMessageDialog(null, "El correo introducido ya está registrado", "ERROR", JOptionPane.ERROR_MESSAGE);
			
		}
		else
		{
			existe=gesCli.ClienteNuevo(state, correo, nombre, contrasenya);
			if(existe==true)
			{
				
				JOptionPane.showMessageDialog(this, "Cliente registrado");
			}
			PrincipalCliente objCliente = new PrincipalCliente(correo);
			objCliente.setVisible(true);
			this.dispose();
		}
	}
}

