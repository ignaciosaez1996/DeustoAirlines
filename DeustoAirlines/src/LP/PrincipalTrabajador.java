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

/**
 * Clase que una vez accedido como trabajador sera su pantalla principal y muestra todas las opciones a las que puede acceder
 */
public class PrincipalTrabajador extends JFrame implements ActionListener
{
	private JDesktopPane 	panel;
	
	private JMenuBar 		menuBar;
	
	private JMenu			mnInicio;
	private JMenu			mnAgenda;
	private JMenu 			mnIngreso;

	private JMenuItem 		mnitmConsVuelo;
	private JMenuItem		mnitmAgenda;
	private JMenuItem		mnitmCalendario;
	private JMenuItem 		mnitmSalir;
	private JMenuItem 		mnitmConsIngresos;
	private JMenuItem 		mnitmCreaerVuelo;
	private JMenuItem 		mnitmCancVuelo;
	
	private JSeparator 		separator_4;
	
	private static final long serialVersionUID = 1L;
		
	/**
	 * Constantes para anchura y altura.
	 */
	public final static int panelWidth = 1400;
	public final static int panelHeight = 680;

	/**
	 * Crea el JFrame que ser� el menu principal de trabajadores y le asigna un titulo
	 */
	public PrincipalTrabajador( ) 
	{	
		setTitle("Opciones del trabajador - Menu Principal ");
		createAndShowGUI();
	}
	
	/**
	 * Crea las etiquetas, campos de texto y botones y los agrega a la ventana de PrincipalTrabajador
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
		        int PromptResult = JOptionPane.showOptionDialog(null,"�Seguro que deseas salir?","Aviso",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
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
		mnitmConsVuelo.setActionCommand(CMD_CONSULTAVUELO);
		mnitmConsVuelo.addActionListener(this);
		mnInicio.add(mnitmConsVuelo);
		
		mnitmCreaerVuelo = new JMenuItem("Crear vuelos");		
		mnitmCreaerVuelo.setActionCommand(CMD_CREARVUELO);
		mnitmCreaerVuelo.addActionListener(this);
		mnInicio.add(mnitmCreaerVuelo);
		
		mnitmCancVuelo = new JMenuItem("Cancelar vuelos");		
		mnitmCancVuelo.setActionCommand(CMD_CANCELARVUELO);
		mnitmCancVuelo.addActionListener(this);
		mnInicio.add(mnitmCancVuelo);
		
		separator_4 = new JSeparator();
		mnInicio.add(separator_4);
		
		mnitmSalir = new JMenuItem("Salir");			
		mnitmSalir.setActionCommand(CMD_SALIR);
		mnitmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
		mnitmSalir.addActionListener(this);
		mnInicio.add(mnitmSalir);
		
		mnAgenda = new JMenu("Agenda");
		menuBar.add(mnAgenda);
		
		mnitmAgenda = new JMenuItem("Agenda de trabajo");
		mnitmAgenda.setActionCommand(CMD_AGENDA);
		mnitmAgenda.addActionListener(this);
		mnAgenda.add(mnitmAgenda);
		
		mnitmCalendario = new JMenuItem("Calendario de trabajo");
		mnitmCalendario.setActionCommand(CMD_CALENDARIO);
		mnitmCalendario.addActionListener(this);
		mnAgenda.add(mnitmCalendario);
		
		mnIngreso = new JMenu("Ingresos");
		menuBar.add(mnIngreso);
		
		mnitmConsIngresos = new JMenuItem("Consultar ingresos");		
		mnitmConsIngresos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mnitmConsIngresos.setActionCommand(CMD_INGRESOS);
		mnitmConsIngresos.addActionListener(this);
		mnIngreso.add(mnitmConsIngresos);
		
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
			case CMD_CREARVUELO:
				this.CrearVuelo();
				break;
				
			case CMD_CANCELARVUELO:
				this.CancelarVuelo();
				break;
			
			case CMD_SALIR:
				String ObjButtons[] = {"Si","Cancelar"};
		        int PromptResult = JOptionPane.showOptionDialog(null,"�Seguro que deseas salir?","Inmo M&J - Aviso",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
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
				
			case CMD_CALENDARIO:
				this.Calendario();
				break;
		}
	}

	private void CrearVuelo() 
	{
		Crearvuelo objCrea = new Crearvuelo();	
		objCrea.setVisible(true);
		panel.add(objCrea);	
	}

	private void CancelarVuelo() 
	{		
		CancelaVuelo objCancelar = new CancelaVuelo();	
		objCancelar.setVisible(true);
		panel.add(objCancelar);	
	}

	private void ConsultaVuelo() 
	{
		consultaVuelo objVuelo = new consultaVuelo();	
		objVuelo.setVisible(true);
		panel.add(objVuelo);	
	}

	private void Ingresos() 
	{
		ConsultaIngresos objIngreso = new ConsultaIngresos();	
		objIngreso.setVisible(true);
		panel.add(objIngreso);	
	}

	private void Agenda() 
	{
		AgendaTrabajo objAgenda = new AgendaTrabajo();	
		objAgenda.setVisible(true);
		panel.add(objAgenda);		
	}
	
	public void Calendario()
	{
		CalendarioTrabajo objCalendario = new CalendarioTrabajo();
		objCalendario.setVisible(true);
		panel.add(objCalendario);
	}
	
}
