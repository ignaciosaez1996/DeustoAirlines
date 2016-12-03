package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import com.toedter.calendar.JCalendar;
import javax.swing.JButton;
import com.toedter.calendar.JDayChooser;

public class Crearvuelo extends JFrame implements ActionListener

{
	private static final long serialVersionUID = 1L;
	
	JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
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
		
		textField = new JTextField();
		textField.setBounds(20, 58, 225, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		
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
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(349, 58, 214, 42);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(349, 163, 214, 40);
		contentPane.add(textField_2);
		
		 calendar = new JCalendar();
		calendar.setBounds(10, 130, 300, 200);
		contentPane.add(calendar);
		
		 btnNewButton_1 = new JButton("GUARDAR");
		btnNewButton_1.setBounds(156, 409, 102, 30);
		contentPane.add(btnNewButton_1);
		
		 btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(283, 409, 119, 27);
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
	
	}
}
