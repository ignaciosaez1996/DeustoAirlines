package LP;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import LD.BasesDeDatos;
import LN.GestorCliente;
import static COMUN.Definiciones.*;

/**
 * Metodo que aparece al principio del programa y permite al usuario entrar como cliente. Despues debera elegir si se trata
 * de un cliente registrado o no registrado.
 */
public class EntrarComoCliente extends JFrame implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnTrabajador;
	private JButton btnCliente;
	private JLabel lbliconoData;
	
	/**
	 * Crea el JFrame
	 */
	public EntrarComoCliente() 
	{
		createAndShowGUI();
	}
	
	/**
	 * Crea las etiquetas, campos de texto y botones y los agrega a la ventana de EntrarComoCliente
	 */
	private void createAndShowGUI()
	{
					
		setForeground(Color.BLACK);
		setTitle("Acceder como cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 445, 400);
			
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		btnTrabajador = new JButton("CLIENTE REGISTRADO");
		btnTrabajador.setFont(new Font("Tekton Pro", Font.BOLD, 15));
		btnTrabajador.setBounds(68, 85, 308, 57);
		btnTrabajador.setActionCommand(CMD_BTN_REGISTRADO);
		contentPane.add(btnTrabajador);
		btnTrabajador.addActionListener(this);
			
			
		btnCliente = new JButton("CLIENTE NO REGISTRADO");
		btnCliente.setFont(new Font("Tekton Pro", Font.BOLD, 15));
		btnCliente.setBounds(68, 199, 308, 57);
		btnCliente.setActionCommand(CMD_BTN_NOREGISTRADO);
		btnCliente.addActionListener(this);
		contentPane.add(btnCliente);
			
		lbliconoData = new JLabel("");
		lbliconoData.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/equipajehombre.jpg")));
		lbliconoData.setBounds(-313, -45, 1059, 408);
		getContentPane().add(lbliconoData);
		
	}

	/**
	 * Metodo para poder detectar cuando un boton es pulsado.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
	 		case CMD_BTN_REGISTRADO:
	 			this.Registrado();
	 			break;
		
	 		case CMD_BTN_NOREGISTRADO:
	 			this.NoRegistrado();
	 			break;
		} 
	}

	/**
	 * Metodo al que se accede cuando un cliente ya esta registrado y hace que aparezca la ventana para poder acceder al programa
	 */
	private void Registrado() 
	{
		ClienteRegistrado objCliente = new ClienteRegistrado();
		objCliente.setVisible(true);
		this.dispose();
	}
	
	/**
	 * Metodo al que se accede cuando un cliente no esta registrado y hace que aparezca la ventana para poder acceder al programa
	 */
	private void NoRegistrado() 
	{
		ClienteNoRegistrado objClienteNo = new ClienteNoRegistrado();
		objClienteNo.setVisible(true);
		this.dispose();
	}





}
