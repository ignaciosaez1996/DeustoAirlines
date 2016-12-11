package LP;


import static COMUN.Definiciones.CMD_BTN_ACEPTAR;
import static COMUN.Definiciones.CMD_BTN_CANCELAR;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JSpinner;

import LD.BasesDeDatos;
import LN.GestorTrabajador;

import com.toedter.calendar.JCalendar;

import javax.swing.JButton;


public class Crearvuelo extends JFrame implements ActionListener

{
	private static final long serialVersionUID = 1L;
	
	JPanel contentPane;
	private JTextField txtcodvuelo;
	private JTextField txtcodpost_o;
	private JTextField txtcodpost_d;
	
	private JLabel lblIntroduzcaElCodigo;
	private JLabel lblCapacidad;
	private JSpinner spinner;
	private JLabel lblFecha;
	private JButton btnNewButton;
	private JLabel lblCodigoPostalCiudad;
	private  JLabel lblCodigoPostalCiudad_1;
	private JCalendar calendar;
	private JButton btnNewButton_1;
	private JButton btnCancelar;
	
	private final static int x = (1400) - ((int)465);
	private final static int y = (680) - (480);	
	
	public Crearvuelo()
	{
		createAndShowGUI();
	}
	
	
	
	private void createAndShowGUI()
	{
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblIntroduzcaElCodigo = new JLabel("Codigo del vuelo");
		lblIntroduzcaElCodigo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIntroduzcaElCodigo.setBounds(20, 31, 214, 22);
		contentPane.add(lblIntroduzcaElCodigo);
		
		txtcodvuelo = new JTextField();
		txtcodvuelo.setBounds(20, 58, 225, 30);
		contentPane.add(txtcodvuelo);
		txtcodvuelo.setColumns(10);
		
		 lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCapacidad.setBounds(349, 227, 163, 22);
		contentPane.add(lblCapacidad);
		
		spinner = new JSpinner();
		spinner.setBounds(349, 267, 53, 42);
		contentPane.add(spinner);
		
		 lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFecha.setBounds(20, 99, 73, 30);
		contentPane.add(lblFecha);
		
		 btnNewButton = new JButton("Guardar fecha seleccionada");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(63, 341, 195, 30);
		contentPane.add(btnNewButton);
		
		lblCodigoPostalCiudad = new JLabel("Codigo postal ciudad origen");
		lblCodigoPostalCiudad.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodigoPostalCiudad.setBounds(349, 31, 214, 22);
		contentPane.add(lblCodigoPostalCiudad);
		
		 lblCodigoPostalCiudad_1 = new JLabel("Codigo postal ciudad destino");
		lblCodigoPostalCiudad_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodigoPostalCiudad_1.setBounds(349, 130, 214, 22);
		contentPane.add(lblCodigoPostalCiudad_1);
		
		txtcodpost_o = new JTextField();
		txtcodpost_o.setColumns(10);
		txtcodpost_o.setBounds(349, 58, 214, 30);
		contentPane.add(txtcodpost_o);
		
		txtcodpost_d = new JTextField();
		txtcodpost_d.setColumns(10);
		txtcodpost_d.setBounds(349, 163, 214, 32);
		contentPane.add(txtcodpost_d);
		
		calendar = new JCalendar();
		calendar.setBounds(10, 130, 300, 200);
		contentPane.add(calendar);
		
		btnNewButton_1 = new JButton("GUARDAR");
		btnNewButton_1.setBounds(156, 409, 102, 30);
		btnNewButton_1.setActionCommand(CMD_BTN_ACEPTAR);
		btnNewButton_1.addActionListener(this);
		this.getRootPane().setDefaultButton(btnNewButton_1);
		contentPane.add(btnNewButton_1);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(283, 409, 102, 30);
		btnCancelar.setActionCommand(CMD_BTN_CANCELAR);
		btnCancelar.addActionListener(this);
		this.getRootPane().setDefaultButton(btnCancelar);
		contentPane.add(btnCancelar);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Crear un vuelo");
		setBounds(x, y, 802, 597);
		getContentPane().setLayout(null);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch(arg0.getActionCommand())
		 {
			case CMD_BTN_ACEPTAR:
				
				if(txtcodvuelo.getText().isEmpty()|| txtcodpost_o.getText().isEmpty() ||txtcodpost_d.getText().isEmpty() || spinner.getValue().toString().isEmpty() || calendar.getDate().toString().isEmpty() )
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
	
	
	private void Vuelo()
	{
		BasesDeDatos.crearTablaVueloBD();
		GestorTrabajador  gesTra = new GestorTrabajador();
		
		String CodVuelo = txtcodvuelo.getText();
		String capacidad = spinner.getValue().toString();
		String fecha = calendar.getDate().toString();
		String codpost_o = txtcodpost_o.getText();
		String codpost_d = txtcodpost_d.getText();
		
		
		boolean existe;
	
		Statement state = BasesDeDatos.getStatement();
	
		existe=gesTra.ExisteVuelo(state, CodVuelo);
		if(existe==true)
		{
			JOptionPane.showMessageDialog(this, "El codigo introducido ya est� registrado");
		}
		else
		{
			existe=gesTra.CrearVuelos(state, CodVuelo , capacidad, fecha , codpost_o, codpost_d );
			if(existe==true)
			{
				JOptionPane.showMessageDialog(this, "Vuelo registrado");
			}
			PrincipalTrabajador objTrabajador = new PrincipalTrabajador( );
			objTrabajador.setVisible(true);
		}
		this.dispose();
		
		
	}
	
}