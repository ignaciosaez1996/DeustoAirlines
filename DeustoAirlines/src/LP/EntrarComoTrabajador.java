package LP;

/*
 * Hay que poner en algun sitio lo siguiente para que se cree la tabla de trabajadores:
 * BasesDeDatos.crearTablaTrabajadorBD();
 */

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import LD.BasesDeDatos;
import LN.GestorCliente;
import LN.GestorTrabajador;
import static COMUN.Definiciones.*;

public class EntrarComoTrabajador extends JFrame implements ActionListener
{


	private static final long serialVersionUID = 1L;
	private JLabel 	   		lblDNI;
	private JLabel 	   		lblContrasena;
	private JLabel 	   		lblSingUpIcon;

	
	private JButton    		btnAceptar;
	private JButton    		btnCancelar;
	
	private JTextField 		txtDNI;
	private JPasswordField passwordField;
	
	
	
	@SuppressWarnings("unused")

	private int DNI;
	private String contrasena;
	
	private final static int x = (1400/2) - ((int)465/2);
	private final static int y = (680/2) - (480/2);	

	
	public EntrarComoTrabajador() 
	{
		createAndShowGUI();	
	}
	
	private void createAndShowGUI() 	
	{	
		/*Por mi (Ianire) si se puede ponemos que salte un mensaje y no deje seguir si tiene m�s de 9 caracteres, o menos
		 * y quitamos el aviso que hay entre par�ntesis
		*/
		lblDNI = new JLabel("Introduzca el DNI (debe contener 9 caracteres): ");
		lblDNI.setFont(new Font("Calibri", Font.BOLD, 14));
		lblDNI.setHorizontalAlignment(SwingConstants.LEFT);
		lblDNI.setBounds(33, 64, 299, 17);
		getContentPane().add(lblDNI);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(33, 105, 274, 35);
		getContentPane().add(txtDNI);
		txtDNI.setColumns(10);
		
		lblContrasena = new JLabel("Introduzca la contrase�a: ");
		lblContrasena.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasena.setFont(new Font("Calibri", Font.BOLD, 14));
		lblContrasena.setBounds(33, 162, 190, 14);
		getContentPane().add(lblContrasena);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(33, 198, 274, 35);
		getContentPane().add(passwordField);
		
		
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
		
		lblSingUpIcon = new JLabel("");
		lblSingUpIcon.setIcon(new ImageIcon(EntrarComoTrabajador.class.getResource("/imagenes/Sign-up-icon.png")));
		lblSingUpIcon.setBounds(310, 30, 106, 100);
		getContentPane().add(lblSingUpIcon);
				
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Acceder como trabajador");
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
				this.dispose();
				break;	
				
		} 
	}
	
	private void Trabajador() 
	{
		Statement state = BasesDeDatos.getStatement();
		BasesDeDatos.crearTablaTrabajadorBD();
		BasesDeDatos.InsertarTrabajadores(state);
		
		String DNI = txtDNI.getText();
		char[] passWord = passwordField.getPassword();
		String contrasenya = String.valueOf(passWord);
		GestorTrabajador gesTra = new GestorTrabajador(DNI, contrasenya);
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
			JOptionPane.showMessageDialog(this, "DNI o contrase�a incorrectas, vuelva a introducirlas");
		}
	}
}
