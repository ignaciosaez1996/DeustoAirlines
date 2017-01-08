package LP;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import LD.BasesDeDatos;
import LN.GestorTrabajador;
import static COMUN.Definiciones.*;

/**
 * Clase que crea la ventana de bienvenida al usuario, ofreciendo que pueda acceder como cliente o como trabajador
 */
public class Principal extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnTrabajador;
	private JButton btnCliente;
	private JLabel lblBienvenidoADeustoairlines;
	private JLabel lbliconoData;
	
	/**
	 * Crea el JFrame
	 */
	public Principal() 
	{	
		createAndShowGUI();
	}
	
	/**
	 * Crea las etiquetas, campos de texto y botones y los agrega a la ventana de Principal
	 */
	private void createAndShowGUI()
	{
		setForeground(Color.BLACK);
		setTitle("MENÚ PRINCIPAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 549, 453);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setSize(500, 500);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnTrabajador = new JButton("ENTRAR COMO TRABAJADOR");
		btnTrabajador.setFont(new Font("Tekton Pro", Font.BOLD, 15));
		btnTrabajador.setBounds(108, 151, 333, 75);
		btnTrabajador.setActionCommand(CMD_BTN_TRABAJADOR);
		contentPane.add(btnTrabajador);
		btnTrabajador.addActionListener(this);
		
		
		btnCliente = new JButton("ENTRAR COMO CLIENTE");
		btnCliente.setFont(new Font("Tekton Pro", Font.BOLD, 15));
		btnCliente.setBounds(108, 281, 333, 75);
		btnCliente.setActionCommand(CMD_BTN_CLIENTE);
		btnCliente.addActionListener(this);
		contentPane.add(btnCliente);
		
		lblBienvenidoADeustoairlines = new JLabel("BIENVENIDO A DEUSTOAIRLINES");
		lblBienvenidoADeustoairlines.setFont(new Font("Tekton Pro Ext", Font.ITALIC, 22));
		lblBienvenidoADeustoairlines.setBounds(69, 11, 425, 116);
		contentPane.add(lblBienvenidoADeustoairlines);
		btnCliente.addActionListener(this);

		lbliconoData = new JLabel("");
		lbliconoData.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/avion.jpg")));
		lbliconoData.setBounds(-313, -45, 1164, 499);
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
			case CMD_BTN_TRABAJADOR:
				this.Trabajador();
				break;
		
			case CMD_BTN_CLIENTE:
				this.Cliente();
				break;
		} 
	}

	/**
	 * Metodo que lleva a la ventana de cliente si el usuario asi lo elige
	 */
	private void Cliente() 
	{
		EntrarComoCliente objCliente = new EntrarComoCliente();
		objCliente.setVisible(true);
		this.dispose();
	}
	
	/**
	 * Metodo que lleva a la ventana de trabajador si el usuario asi lo elige. Ademas crea la Base de Datos de los trabajadores
	 * introduciendolos ya que los trabajadores estan predeterminados
	 */
	private void Trabajador() 
	{
		GestorTrabajador gesTra = new GestorTrabajador();
		Statement state = BasesDeDatos.getStatement();
		BasesDeDatos.crearTablaTrabajadorBD();
		boolean existe2;
		existe2 = gesTra.ExistenTrabajadores(state);
		if(existe2==false)
		{
			BasesDeDatos.InsertarTrabajadores(state);
		}
		
		EntrarComoTrabajador objTrabajador = new EntrarComoTrabajador();
		objTrabajador.setVisible(true);
		this.dispose();
	}





}

