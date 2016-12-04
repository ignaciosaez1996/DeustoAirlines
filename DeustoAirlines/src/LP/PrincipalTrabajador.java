package LP;

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


public class PrincipalTrabajador extends JFrame implements ActionListener
{
	
	private JDesktopPane 	panel;
	
	private JMenuBar 		menuBar;
	
	private JMenu			mnInicio;
	private JMenu			mnAgenda;
	private JMenu 			mnIngreso;

	private JMenuItem 		mnitmConsVuelo;
	private JMenuItem		mnitmAgenda;
	private JMenuItem 		mnitmSalir;
	private JMenuItem 		mnitmConsIngresos;
	private JMenuItem 		mnitmCreaerVuelo;
	private JMenuItem 		mnitmCancVuelo;
	

	private JSeparator 		separator_4;
	
	/**
	 * @serialField Default Version UID
	 */
	private static final long serialVersionUID = 1L;
		
	/**
	 * Constantes para anchura y altura.
	 */
	public final static int panelWidth = 1400;
	public final static int panelHeight = 680;
	
	
	

	
	/**
	 * Constructor sin parámetros.
	 * @param usuario Nombre del usuario que accede al menu.
	 */
	
	
	public PrincipalTrabajador( ) 
	{
		//setIconImage(Toolkit.getDefaultToolkit().getImage(PrincipalCliente.class.getResource("/imagenes/Appicon.png")));	
	
		setTitle("Opciones del trabajador - Menu Principal ");
		
		createAndShowGUI();
	}
	
	/**
	 * Crear el frame Menu Principal para el administrador.
	 */
	private void createAndShowGUI()
	{
		panel = new JDesktopPane();		
		panel.setSize(panelWidth,panelHeight);
		getContentPane().add(panel);	
				
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
		
		mnInicio = new JMenu("Vuelos");
		menuBar.add(mnInicio);
		
		mnitmConsVuelo = new JMenuItem("Consultar vuelos");		
		//mnitmConsVuelo.setIcon(new ImageIcon(frmMenuUsuario.class.getResource("/imagenes/lista.png")));		
		mnitmConsVuelo.setActionCommand(null);
		mnitmConsVuelo.addActionListener(this);
		mnInicio.add(mnitmConsVuelo);
		
		mnitmCreaerVuelo = new JMenuItem("Crear vuelos");		
		//mnitmCreaerVuelo.setIcon(new ImageIcon(frmMenuUsuario.class.getResource("/imagenes/lista.png")));		
		mnitmCreaerVuelo.setActionCommand(CMD_CREARVUELO);
		mnitmCreaerVuelo.addActionListener(this);
		mnInicio.add(mnitmCreaerVuelo);
		
		mnitmCancVuelo = new JMenuItem("Cancelar vuelos");		
		//mnitmCancVuelo.setIcon(new ImageIcon(frmMenuUsuario.class.getResource("/imagenes/lista.png")));		
		mnitmCancVuelo.setActionCommand(null);
		mnitmCancVuelo.addActionListener(this);
		mnInicio.add(mnitmCancVuelo);
		
		
		
		separator_4 = new JSeparator();
		mnInicio.add(separator_4);
		
		mnitmSalir = new JMenuItem("Salir");			
		mnitmSalir.setActionCommand(null);
		//mnitmSalir.setIcon(new ImageIcon(frmMenuUsuario.class.getResource("/imagenes/exit_icon.png")));
		mnitmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
		mnitmSalir.addActionListener(this);
		mnInicio.add(mnitmSalir);
		
		
		mnAgenda = new JMenu("Agenda");
		menuBar.add(mnAgenda);
		
		mnitmAgenda = new JMenuItem("Agenda de trabajo");
		//mnitmCancBille.setIcon(new ImageIcon(frmMenuUsuario.class.getResource("/imagenes/lista.png")));
		mnitmAgenda.setActionCommand(null);
		mnitmAgenda.addActionListener(this);
		mnAgenda.add(mnitmAgenda);
		
		mnIngreso = new JMenu("Ingresos");
		menuBar.add(mnIngreso);
		
		mnitmConsIngresos = new JMenuItem("Consultar ingresos");		
		//mnitmConsIngresos.setIcon(new ImageIcon(frmMenuUsuario.class.getResource("/imagenes/lista.png")));
		mnitmConsIngresos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mnitmConsIngresos.setActionCommand(null);
		mnitmConsIngresos.addActionListener(this);
		mnIngreso.add(mnitmConsIngresos);
		
		
		
		
		getContentPane().setLayout(null);		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		switch(arg0.getActionCommand())
		{
			case CMD_CREARVUELO:
				this.CrearVuelo();
				break;
				
			case CMD_CANCELARVUELO:
				this.CancelarVuelo();
				break;
			
			case CMD_SALIR:
				String ObjButtons[] = {"Si","Cancelar"};
		        int PromptResult = JOptionPane.showOptionDialog(null,"¿Seguro que deseas salir?","Inmo M&J - Aviso",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		        if(PromptResult==JOptionPane.YES_OPTION)
		        {
		            System.exit(0);
		        }
				break;
				
			case CMD_CONSULTAVUELO:
				this.ConsultaVuelo();
				break;
				
			
				
			case CMD_INGRESOS:
				this.Ingresos();
				break;
				
				
			case CMD_AGENDA:
				this.Agenda();
				break;
				
	
		}
		
		
	}

	private void CrearVuelo() 
	{
		
			//Crearvuelo objCrea = new Crearvuelo();	
			//objCrea.setVisible(true);
			//panel.add(objCrea);	
		
		
	}

	private void CancelarVuelo() 
	{
			//CancelarVuelo objCancel = new CancelarVuelo();	
			//objCancel.setVisible(true);
			//panel.add(objCancel);	
		
		
	}

	private void ConsultaVuelo() 
	{
		
			//ConsultaVuelo objVuelo = new ConsultaVuelo();	
			//objVuelo.setVisible(true);
			//panel.add(objVuelo);	
		
		
	}



	private void Ingresos() 
	{
		
			//Ingresos objIngreso = new Ingresos();	
			//objIngreso.setVisible(true);
			//panel.add(objIngreso);	
		
		
	}

	private void Agenda() 
	{
		
			//Agenda objAgenda = new Agenda();	
			//objAgenda.setVisible(true);
			//panel.add(objAgenda);	
		
		
	}
	
}
