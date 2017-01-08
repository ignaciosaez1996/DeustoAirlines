package LP;

import static COMUN.Definiciones.CMD_BTN_ACEPTAR;
import static COMUN.Definiciones.CMD_BTN_CANCELAR;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;



import java.awt.Font;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JTextField;
import javax.swing.JSpinner;

import LD.BasesDeDatos;
import LN.GestorTrabajador;

import com.toedter.calendar.JCalendar;

import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;

/**
 * Clase que crea un formulario que será llamado desde PrincipalTrabajador y recogerá los datos necesarios para crear un nuevo vuelo
 * Después se los mandará al GestorTrabajador para que lo guarde en la Base de Datos
 */
public class Crearvuelo extends JInternalFrame implements ActionListener

{
	private static final long serialVersionUID = 1L;
	
	JPanel contentPane;
	private JTextField txtcodpost_o;
	private JTextField txtcodpost_d;
	
	private JLabel lblPrecio;
	private JSpinner spinnerPrecio;
	private JLabel lblFecha;
	private JLabel lblCodigoPostalCiudad;
	private JLabel lblCodigoPostalCiudad_1;
	private JCalendar calendar;
	private JButton btnNewButton_1;
	private JButton btnCancelar;

	
	private final static int x = (1400) - ((int)465);
	private final static int y = (680) - (480);	
	
	/**
	 * Crea la ventana de JInternalFrame
	 */
	public Crearvuelo()
	{
		createAndShowGUI();
	}
	
	/**
	 * Crea las etiquetas, campos de texto y botones y los agrega a la ventana de CrearVuelo
	 */
	private void createAndShowGUI()
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		lblPrecio = new JLabel("Precio por asiento");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrecio.setBounds(505, 198, 175, 22);
		contentPane.add(lblPrecio);
		
		spinnerPrecio = new JSpinner();
		spinnerPrecio.setModel(new SpinnerNumberModel(150, 20, 250, 1));
		spinnerPrecio.setBounds(538, 231, 53, 42);
		spinnerPrecio.setValue(150);
		contentPane.add(spinnerPrecio);
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFecha.setBounds(21, 149, 73, 30);
		contentPane.add(lblFecha);
		
		lblCodigoPostalCiudad = new JLabel("Nombre de la ciudad origen");
		lblCodigoPostalCiudad.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodigoPostalCiudad.setBounds(37, 43, 280, 22);
		lblCodigoPostalCiudad.setToolTipText("Escriba el nombre de la ciudad origen");
		contentPane.add(lblCodigoPostalCiudad);
		
		lblCodigoPostalCiudad_1 = new JLabel("Nombre de la ciudad destino");
		lblCodigoPostalCiudad_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodigoPostalCiudad_1.setBounds(450, 43, 300, 22);
		lblCodigoPostalCiudad_1.setToolTipText("Escriba el nombre de la ciudad destino");
		contentPane.add(lblCodigoPostalCiudad_1);
		
		txtcodpost_o = new JTextField();
		txtcodpost_o.setColumns(10);
		txtcodpost_o.setBounds(37, 76, 214, 30);
		txtcodpost_o.setToolTipText("Escriba codigo postal - Nombre ciudad");
		contentPane.add(txtcodpost_o);
		
		txtcodpost_d = new JTextField();
		txtcodpost_d.setColumns(10);
		txtcodpost_d.setBounds(450, 75, 214, 32);
		txtcodpost_d.setToolTipText("Escriba codigo postal - Nombre ciudad");
		contentPane.add(txtcodpost_d);
		
		calendar = new JCalendar();
		calendar.setBounds(10, 190, 380, 280);
		contentPane.add(calendar);
		
		btnNewButton_1 = new JButton("GUARDAR");
		btnNewButton_1.setBounds(260, 486, 102, 30);
		btnNewButton_1.setActionCommand(CMD_BTN_ACEPTAR);
		btnNewButton_1.addActionListener(this);
		this.getRootPane().setDefaultButton(btnNewButton_1);
		contentPane.add(btnNewButton_1);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(450, 486, 102, 30);
		btnCancelar.setActionCommand(CMD_BTN_CANCELAR);
		btnCancelar.addActionListener(this);
		this.getRootPane().setDefaultButton(btnCancelar);
		contentPane.add(btnCancelar);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Crear un vuelo");
		this.setBounds(260, 30, 729, 572);
		this.getContentPane().setLayout(null);
		
		JLabel lblPrecioPorAsiento = new JLabel("Capacidad");
		lblPrecioPorAsiento.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrecioPorAsiento.setBounds(505, 316, 130, 19);
		contentPane.add(lblPrecioPorAsiento);
		
		JSpinner spinnerCapacidad = new JSpinner();
		spinnerCapacidad.setModel(new SpinnerNumberModel(30, 15, 3000, 1));
		spinnerCapacidad.setBounds(538, 346, 53, 42);
		contentPane.add(spinnerCapacidad);
		
	}

	/**
	 * Metodo para poder detectar cuando un boton es pulsado.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch(arg0.getActionCommand())
		 {
			case CMD_BTN_ACEPTAR:
				
				if(txtcodpost_o.getText().isEmpty() ||txtcodpost_d.getText().isEmpty() || calendar.getDate().toString().isEmpty() )
				{
					JOptionPane.showMessageDialog(this, "Rellene todos los campos");
				}
				else
				{
					this.Vuelo();
				}
				break;
				
				
			case CMD_BTN_CANCELAR:
				this.dispose();
				break;
		} 
	}

	/**
	 * Metodo encargado de guardar los datos recogidos en el formulario en la Base de Datos. Para elllo llama a gestor Trabajador.
	 */
	public void Vuelo()
	{
		BasesDeDatos.crearTablaVueloBD();
		GestorTrabajador  gesTra = new GestorTrabajador();
		
		Random rnd = new Random();
		int CodVuelo = rnd.nextInt(100000);
		String capacidad = spinnerPrecio.getValue().toString();
		String precio = spinnerPrecio.getValue().toString();
		String fecha = calendar.getDate().toString();
		String codpost_o = txtcodpost_o.getText();
		String codpost_d = txtcodpost_d.getText();
		
		boolean existe;
	
		Statement state = BasesDeDatos.getStatement();
	
		existe=gesTra.ExisteVuelo(state, CodVuelo);
		
		if(existe==true)
		{
			JOptionPane.showMessageDialog(null, " El codigo introducido ya está registrado", "ERROR", JOptionPane.ERROR_MESSAGE);
			
		}
		else
		{
			existe = gesTra.CrearVuelos(state, CodVuelo , capacidad, fecha , codpost_o, codpost_d, precio );
			if(existe==true)
			{
				JOptionPane.showMessageDialog(this, "Vuelo registrado");
			}
			this.dispose();
		}
	}
}
