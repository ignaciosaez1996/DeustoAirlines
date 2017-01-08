package LP;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import static COMUN.Definiciones.*;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;

/**
 * Clase que una vez accedido como cliente sera su pantalla principal y muestra todas las opciones a las que puede acceder
 */
public class PrincipalCliente extends JFrame implements ActionListener
{
	private JDesktopPane 	panel;
	
	private JMenuBar 		menuBar;
	
	private JMenu			mnInicio;
	private JMenu 			mnHorario;
	private JMenu 			mnHistorial;

	private JMenuItem 		mnitmComprBille;
	private JMenuItem		mnitmCancBille;
	private JMenuItem 		mnitmSalir;
	private JMenuItem 		mnitmConsHorario;
	private JMenuItem 		mnitmHistorial;
	
	private JSeparator 		separator_4;

	private static final long serialVersionUID = 1L;
		
	/**
	 * Constantes para anchura y altura.
	 */
	public final static int panelWidth = 1400;
	public final static int panelHeight = 680;

	private JLabel label;
	private JTextField textField;
	private JLabel lblBienvenid;
	private JLabel lblCorreo;
	
	String correo;

	/**
	 * Crea el JFrame del menu principal.
	 * Asigna un titulo a la clase.
	 * @param correo: Recoge el correo del cliente para que se puedan identificar sus transacciones
	 */
	public PrincipalCliente(String correo) 
	{
		this.correo = correo;
		setTitle("Opciones del cliente - Menu Principal (" + correo + ")");
		createAndShowGUI();
	}
	
	/**
	 * Crea las etiquetas, campos de texto y botones y los agrega a la ventana de PrincipalCliente
	 */
	private void createAndShowGUI()
	{
		panel = new JDesktopPane();		
		panel.setSize(panelWidth,panelHeight);
		getContentPane().add(panel);	
				
		lblBienvenid = new JLabel("Bienvenid@");
		lblBienvenid.setForeground(Color.WHITE);
		lblBienvenid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBienvenid.setBounds(817, 26, 189, 39);
		panel.add(lblBienvenid);
		
		lblCorreo = new JLabel("");
		lblCorreo.setForeground(Color.WHITE);
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCorreo.setText(correo);
		lblCorreo.setBounds(967, 23, 380, 45);
		panel.add(lblCorreo);
		
		setExtendedState(Frame.MAXIMIZED_BOTH);				
		setBounds(100, 100, 450, 301);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() 
		{
		    @Override
		    public void windowClosing(WindowEvent we)
		    { 
		        String ObjButtons[] = {"Si","Cancelar"};
		        int PromptResult = JOptionPane.showOptionDialog(null,"¿Seguro que deseas salir?","Aviso",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		        if(PromptResult==JOptionPane.YES_OPTION)
		        {
		            System.exit(0);
		        }
		    }
		});
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnInicio = new JMenu("Billetes");
		menuBar.add(mnInicio);
		
		mnitmComprBille = new JMenuItem("Comprar Billete");		
		mnitmComprBille.setActionCommand(CMD_COMPRABILLETE);
		mnitmComprBille.addActionListener(this);
		mnInicio.add(mnitmComprBille);
		
		mnitmCancBille = new JMenuItem("Cancelar billete");
		mnitmCancBille.setActionCommand(CMD_CANCELARBILLETE);
		mnitmCancBille.addActionListener(this);
		mnInicio.add(mnitmCancBille);
		
		separator_4 = new JSeparator();
		mnInicio.add(separator_4);
		
		mnitmSalir = new JMenuItem("Salir");			
		mnitmSalir.setActionCommand(CMD_SALIR);
		//mnitmSalir.setIcon(new ImageIcon(frmMenuUsuario.class.getResource("/imagenes/exit_icon.png")));
		mnitmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
		mnitmSalir.addActionListener(this);
		mnInicio.add(mnitmSalir);
		
		mnHorario = new JMenu("Horarios");
		menuBar.add(mnHorario);
		
		mnitmConsHorario = new JMenuItem("Consultar horarios");		
		mnitmConsHorario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mnitmConsHorario.setActionCommand(CMD_CONSULTAHORARIO);
		mnitmConsHorario.addActionListener(this);
		mnHorario.add(mnitmConsHorario);

		mnHistorial = new JMenu("Historial");				
		menuBar.add(mnHistorial);
		
		mnitmHistorial = new JMenuItem("Historial de compras");
		mnitmHistorial.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnitmHistorial.setActionCommand(CMD_HISTORIAL);
		mnitmHistorial.addActionListener(this);
		mnHistorial.add(mnitmHistorial);
		
		getContentPane().setLayout(null);			
	}
	
	/**
	 * Metodo para poder detectar cuando un boton es pulsado.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		switch(arg0.getActionCommand())
		{
			case CMD_COMPRABILLETE:
				this.ComprarBillete();
				break;
				
			case CMD_CANCELARBILLETE:
				this.CancelarBillete();
				break;
			
			case CMD_SALIR:
				String ObjButtons[] = {"Si","Cancelar"};
		        int PromptResult = JOptionPane.showOptionDialog(null,"¿Seguro que deseas salir?","DeustoAirlines",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		        if(PromptResult==JOptionPane.YES_OPTION)
		        {
		            System.exit(0);
		        }
				break;
				
			case CMD_CONSULTAHORARIO:
				this.ConsultarHorarios();
				break;
				
			case CMD_HISTORIAL:
				this.Historial();
				break;

		}
	}

	private void ComprarBillete() 
	{
		ComprarBillete objCompra = new ComprarBillete(correo);	
		objCompra.setVisible(true);
		panel.add(objCompra);	
	}

	private void CancelarBillete() 
	{
		CancelarBillete objCancel = new CancelarBillete();	
		objCancel.setVisible(true);
		panel.add(objCancel);	
	}

	private void ConsultarHorarios() 
	{
		ConsultaHorario objHorario = new ConsultaHorario();	
		objHorario.setVisible(true);
		panel.add(objHorario);	
	}

	private void Historial() 
	{
		HistorialCompras objHistorial = new HistorialCompras(correo);	
		objHistorial.setVisible(true);
		panel.add(objHistorial);	
	}

}