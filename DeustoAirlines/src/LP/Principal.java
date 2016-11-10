package LP;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import static COMUN.Definiciones.*;

public class Principal extends JFrame implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnUsuario;
	private JButton btnCliente;
	private JLabel lblBienvenidoADeustoairlines;
	private JLabel lbliconoData;
	

	public Principal() 
	{
		
		createAndShowGUI();
	
	}
	
	
	private void createAndShowGUI()
	{
	
	{
		//setMaximizedBounds(true);
		//setIconifiable(true);
		//setResizable(true);
		//setClosable(true);
		
		setForeground(Color.BLACK);
		setTitle("MENU PRINCIPAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 393);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnUsuario = new JButton("ENTRAR COMO USUARIO");
		btnUsuario.setFont(new Font("Tekton Pro", Font.BOLD, 15));
		btnUsuario.setBounds(68, 149, 308, 57);
		contentPane.add(btnUsuario);
		btnUsuario.addActionListener(this);
		
		btnCliente = new JButton("ENTRAR COMO CLIENTE");
		btnCliente.setFont(new Font("Tekton Pro", Font.BOLD, 15));
		btnCliente.setBounds(68, 247, 308, 57);
		contentPane.add(btnCliente);
		
		lblBienvenidoADeustoairlines = new JLabel("BIENVENIDO A DEUSTOAIRLINES");
		lblBienvenidoADeustoairlines.setFont(new Font("Tekton Pro Ext", Font.ITALIC, 22));
		lblBienvenidoADeustoairlines.setBounds(23, 11, 425, 116);
		contentPane.add(lblBienvenidoADeustoairlines);
		btnCliente.addActionListener(this);

		lbliconoData = new JLabel("");
		lbliconoData.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/avion.jpg")));
		lbliconoData.setBounds(-313, -45, 1059, 408);
		getContentPane().add(lbliconoData);

	}
	}


@Override
public void actionPerformed(ActionEvent e)
{switch(e.getActionCommand())
	
	 {
	case CMD_BTN_USUARIO:
		this.Usuario();
		break;
		
	case CMD_BTN_CLIENTE:
		this.Cliente();
		break;
		
	} 
}


private void Cliente() 
{
	PrincipalCliente objCliente = new PrincipalCliente();
	//objCliente.setVisible(true);
	this.dispose();
}

private void Usuario() 
{
	PrincipalTrabajador objTrabajador = new PrincipalTrabajador();
	//objTrabajador.setVisible(true);
	this.dispose();
}





}

