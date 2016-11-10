package LP;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import static COMUN.Definiciones.*;

public class EntrarComoTrabajador extends JFrame implements ActionListener
{


	private static final long serialVersionUID = 1L;
	private JLabel 	   		lblDNI;
	private JLabel 	   		lblContrasena;

	
	private JButton    		btnAceptar;
	private JButton    		btnCancelar;
	
	private JTextField 		txtCorreo;
	private JTextField 		txtContrasena;
	
	
	
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
		lblDNI.setHorizontalAlignment(SwingConstants.CENTER);
		lblDNI.setBounds(35, 61, 304, 17);
		getContentPane().add(lblDNI);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(33, 105, 212, 25);
		getContentPane().add(txtCorreo);
		txtCorreo.setColumns(10);
		
		lblContrasena = new JLabel("Introduzca la contraseña");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblContrasena.setBounds(33, 162, 190, 14);
		getContentPane().add(lblContrasena);
		
		txtContrasena = new JTextField();
		txtContrasena.setColumns(10);
		txtContrasena.setBounds(44, 203, 201, 25);
		getContentPane().add(txtContrasena);
		
		
		
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
			
			break;
			
		} 
		
		
	}
	
	private void Trabajador() 
	{
		PrincipalTrabajador objTrabajador = new PrincipalTrabajador();
		//objTrabajador.setVisible(true);
		this.dispose();
	}
}
