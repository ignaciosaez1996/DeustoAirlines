package LP;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import LD.BasesDeDatos;
import LN.GestorCliente;
import static COMUN.Definiciones.*;


public class EntrarComoCliente extends JFrame implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnTrabajador;
	private JButton btnCliente;

	
	/**
	 * Constructor sin parámetros.
	 */
	public EntrarComoCliente() 
	{
		createAndShowGUI();
	}
	
	
	/**
	 * Crear el frame para iniciar sesión.
	 */
	private void createAndShowGUI()
	{
	
		{
			/*setMaximizedBounds(true);
			setIconifiable(true);
			setResizable(true);
			setClosable(true);
			*/		
			
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
		
		}
	}


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


	private void Registrado() 
	{
		ClienteRegistrado objCliente = new ClienteRegistrado();
		objCliente.setVisible(true);
		this.dispose();
	}

	private void NoRegistrado() 
	{
		ClienteNoRegistrado objClienteNo = new ClienteNoRegistrado();
		objClienteNo.setVisible(true);
		this.dispose();
	}





}
