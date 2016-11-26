package LP;

/*
 * Hay que poner en algun sitio lo siguiente para que se cree la tabla de trabajadores:
 * BasesDeDatos.crearTablaTrabajadorBD();
 */

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import LD.BasesDeDatos;
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
		lblDNI = new JLabel(" Introduzca el DNI (debe contener 9 caracteres) ");
		lblDNI.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblDNI.setHorizontalAlignment(SwingConstants.LEFT);
		lblDNI.setBounds(33, 64, 299, 17);
		getContentPane().add(lblDNI);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(33, 105, 274, 35);
		getContentPane().add(txtDNI);
		txtDNI.setColumns(10);
		
		lblContrasena = new JLabel("Introduzca la contraseña");
		lblContrasena.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasena.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
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
		//setMaximizable(true);
		//setClosable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setIconifiable(true);		
		setTitle("Acceder como trabajador");
		//setFrameIcon(new ImageIcon(frmAltaVivienda.class.getResource("/imagenes/plus.png")));
		setBounds(x, y, 455, 402);
		getContentPane().setLayout(null);

	}
	@Override
	public void actionPerformed(ActionEvent e) 
	
	{switch(e.getActionCommand())
		
		 {
		case CMD_BTN_ACEPTAR:
			this.Trabajador();
			break;
			
		case CMD_BTN_CLIENTE:
			this.dispose();
			break;
			
		} 
		
		
	}
	
	private void Trabajador() 
	{
		PrincipalTrabajador objTrabajador = new PrincipalTrabajador();
		objTrabajador.setVisible(true);
		this.dispose();
	}
}
