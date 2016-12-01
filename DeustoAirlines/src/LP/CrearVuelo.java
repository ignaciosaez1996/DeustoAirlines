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

public class CrearVuelo extends JFrame implements ActionListener

{
	
	JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblIntroduzcaElCodigo;
	private JLabel lblCapacidad;
	
	public CrearVuelo()
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
		textField.setBounds(20, 66, 214, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		 lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCapacidad.setBounds(319, 228, 163, 22);
		contentPane.add(lblCapacidad);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(319, 261, 36, 30);
		contentPane.add(spinner);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFecha.setBounds(20, 99, 73, 30);
		contentPane.add(lblFecha);
		
		JButton btnNewButton = new JButton("Guardar fecha seleccionada");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(30, 302, 184, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblCodigoPostalCiudad = new JLabel("Codigo postal ciudad origen");
		lblCodigoPostalCiudad.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodigoPostalCiudad.setBounds(318, 31, 214, 22);
		contentPane.add(lblCodigoPostalCiudad);
		
		JLabel lblCodigoPostalCiudad_1 = new JLabel("Codigo postal ciudad destino");
		lblCodigoPostalCiudad_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodigoPostalCiudad_1.setBounds(318, 143, 214, 22);
		contentPane.add(lblCodigoPostalCiudad_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(318, 66, 214, 22);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(318, 177, 214, 22);
		contentPane.add(textField_2);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(30, 138, 184, 153);
		contentPane.add(calendar);
		
		JButton btnNewButton_1 = new JButton("GUARDAR");
		btnNewButton_1.setBounds(141, 398, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(290, 398, 89, 23);
		contentPane.add(btnCancelar);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
	
	}
}
