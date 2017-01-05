package LP;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import LN.Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Enviar_Correo extends JInternalFrame 
{
	private JPanel contentPane;
	private JTextField txtDestino;
	private JTextField txtAsunto;
	private JTextField txtMensaje;
	private JButton btnEnviar;
	String correo;
	
	Correo c = new Correo();
	
	public Enviar_Correo(String correo)
	{
		this.correo = correo;
		//Se debería poner en el botón de enviar, pero como nosotros enviaremos el correo con el texto que
		//hayamos asignado por defecto, lo mandaremos en el constructor
		enviarCorreo();
		createAndShowGUI();
	}
	
	public void createAndShowGUI()
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setBounds(22, 34, 46, 14);
		contentPane.add(lblDestino);
		
		JLabel lblAsunto = new JLabel("Asunto");
		lblAsunto.setBounds(22, 73, 46, 14);
		contentPane.add(lblAsunto);
		
		txtDestino = new JTextField();
		txtDestino.setBounds(78, 31, 392, 20);
		contentPane.add(txtDestino);
		txtDestino.setColumns(10);
		
		txtAsunto = new JTextField();
		txtAsunto.setColumns(10);
		txtAsunto.setBounds(78, 70, 392, 20);
		contentPane.add(txtAsunto);
		
		txtMensaje = new JTextField();
		txtMensaje.setBounds(78, 118, 392, 140);
		contentPane.add(txtMensaje);
		txtMensaje.setColumns(10);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				enviarCorreo();
			}
		});
		btnEnviar.setBounds(381, 293, 89, 23);
		contentPane.add(btnEnviar);
	}
	
	public void enviarCorreo()
	{
		//Hay que buscar una contraseña de aplicación para que funcione
		c.setContrasenya("skdykixwcvudpjkq");
		c.setUsuarioCorreo(correo);
		//Editar el asunto para que mande en el mensaje lo que nos interese
		c.setAsunto(txtAsunto.getText());
		c.setMensaje(txtMensaje.getText());
		c.setDestino(txtDestino.getText().trim());
		//c.setNombreArchivo("logo.png");	NO TENEMOS ARCHIVOS PARA ENVIAR
		//c.setRutaArchivo("logo.png");		NO TENEMOS ARCHIVOS PARA ENVIAR
		Controlador co = new Controlador();
		if(co.enviarCorreo(c))
		{
			JOptionPane.showMessageDialog(null, "Correo enviado");
		}else
		{
			JOptionPane.showMessageDialog(null, "Error al enviar correo");
		}
	}
}
