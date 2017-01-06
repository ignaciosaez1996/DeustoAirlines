package LP;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

import static COMUN.Definiciones.CMD_BTN_ACEPTAR;
import static COMUN.Definiciones.CMD_BTN_CANCELAR;
import static COMUN.Definiciones.CMD_TOTALINGRESOS;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;

import LD.BasesDeDatos;
import LN.GestorTrabajador;

import javax.swing.JLabel;

public class ConsultaIngresos extends JInternalFrame implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable table;
	Connection connection = null;
	private JScrollPane scrollPane;
	private JTextField textField;
	private JButton btnNewButton ;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;
	private JButton button_8;
	private JButton button_9;
	private JButton button_10;
	private JButton button_11;
	private JButton button_12;
	private JButton button_13;
	private JButton button_14;
	private JButton button_15;
	private JButton btnC;
	private JButton button_17;
	private JButton button_18;
	
	double firstnum;
	double secondnum;
	String operations;
	double result;
	private JButton btnNewButton_1;
	Statement state = BasesDeDatos.getStatement();
	GestorTrabajador  gesTra;
	
	public ConsultaIngresos()
	{
		connection = BasesDeDatos.getConnection();
		createAndShowGUI();
		CargarTabla();
		
	}

	public void createAndShowGUI()
	{
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	
	
	
	
	textField = new JTextField();
	textField.setBounds(634, 102, 260, 50);
	contentPane.add(textField);
	textField.setColumns(10);
	
	 btnNewButton = new JButton("7");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			String EnterNumber = textField.getText() + btnNewButton.getText();
			textField.setText(EnterNumber);
		}
	});
	btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
	btnNewButton.setBounds(635, 221, 54, 50);
	contentPane.add(btnNewButton);
	
	button = new JButton("8");
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			String EnterNumber = textField.getText() + button.getText();
			textField.setText(EnterNumber);
			
			
		}
	});
	button.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button.setBounds(699, 221, 54, 50);
	contentPane.add(button);
	
	button_1 = new JButton("9");
	button_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			String EnterNumber = textField.getText() + button_1.getText();
			textField.setText(EnterNumber);
		}
	});
	button_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button_1.setBounds(763, 221, 54, 50);
	contentPane.add(button_1);
	
	button_2 = new JButton("+");
	button_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			firstnum = Double.parseDouble(textField.getText());
			textField.setText("");
			operations = "+";
			
		}
	});
	button_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button_2.setBounds(840, 163, 54, 50);
	contentPane.add(button_2);
	
	button_3 = new JButton("4");
	button_3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
		
		String EnterNumber = textField.getText() + button_3.getText();
		textField.setText(EnterNumber);
		}
	});
	button_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button_3.setBounds(635, 282, 54, 50);
	contentPane.add(button_3);
	
	button_4 = new JButton("5");
	button_4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			String EnterNumber = textField.getText() + button_4.getText();
			textField.setText(EnterNumber);
		}
	});
	button_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button_4.setBounds(699, 282, 54, 50);
	contentPane.add(button_4);
	
	button_5 = new JButton("6");
	button_5.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			String EnterNumber = textField.getText() + button_5.getText();
			textField.setText(EnterNumber);
		}
	});
	button_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button_5.setBounds(763, 282, 54, 50);
	contentPane.add(button_5);
	
	button_6 = new JButton("-");
	button_6.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			firstnum = Double.parseDouble(textField.getText());
			textField.setText("");
			operations = "-";
		}
	});
	button_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button_6.setBounds(840, 282, 54, 50);
	contentPane.add(button_6);
	
	button_7 = new JButton("1");
	button_7.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
		String EnterNumber = textField.getText() + button_7.getText();
		textField.setText(EnterNumber);
		}
	});
	button_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button_7.setBounds(634, 343, 54, 50);
	contentPane.add(button_7);
	
	button_8 = new JButton("2");
	button_8.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			String EnterNumber = textField.getText() + button_8.getText();
			textField.setText(EnterNumber);
		}
	});
	button_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button_8.setBounds(699, 343, 54, 50);
	contentPane.add(button_8);
	
	button_9 = new JButton("3");
	button_9.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			String EnterNumber = textField.getText() + button_9.getText();
			textField.setText(EnterNumber);
		}
	});
	button_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button_9.setBounds(763, 343, 54, 50);
	contentPane.add(button_9);
	
	button_10 = new JButton("*");
	button_10.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			firstnum = Double.parseDouble(textField.getText());
			textField.setText("");
			operations = "*";
		}
	});
	button_10.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button_10.setBounds(840, 343, 54, 50);
	contentPane.add(button_10);
	
	button_11 = new JButton("0");
	button_11.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			String EnterNumber = textField.getText() + button_11.getText();
			textField.setText(EnterNumber);
		}
	});
	button_11.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button_11.setBounds(634, 407, 54, 50);
	contentPane.add(button_11);
	
	button_12 = new JButton(".");
	button_12.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button_12.setBounds(699, 407, 54, 50);
	contentPane.add(button_12);
	
	button_13 = new JButton("...");
	button_13.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button_13.setBounds(763, 407, 54, 50);
	contentPane.add(button_13);
	
	button_14 = new JButton("=");
	button_14.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			String answer;
			secondnum = Double.parseDouble(textField.getText());
			if ( operations == "+")
			{
				result = firstnum + secondnum;
				answer = String.format("%.2f", result);
				textField.setText(answer);
			}

			else if ( operations == "-")
			{
				result = firstnum - secondnum;
				answer = String.format("%.2f", result);
				textField.setText(answer);
			}
			
			else if ( operations == "*")
			{
				result = firstnum * secondnum;
				answer = String.format("%.2f", result);
				textField.setText(answer);
			}
			
			else if ( operations == "/")
			{
				result = firstnum / secondnum;
				answer = String.format("%.2f", result);
				textField.setText(answer);
			}
			else if ( operations == "%")
			{
				result = firstnum % secondnum;
				answer = String.format("%.2f", result);
				textField.setText(answer);
			}
			
			
		}
	});
	button_14.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button_14.setBounds(840, 407, 54, 50);
	contentPane.add(button_14);
	
	button_15 = new JButton("<-");
	button_15.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			String backspace = null;
			
			if ( textField.getText().length()> 0)
			{
				StringBuilder strB = new StringBuilder(textField.getText());
				strB.deleteCharAt(textField.getText().length() - 1);
				backspace = strB.toString();
				textField.setText(backspace);
			}
		}
	});
	button_15.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button_15.setBounds(634, 163, 54, 50);
	contentPane.add(button_15);
	
	btnC = new JButton("C");
	btnC.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
		
		textField.setText(null);
			
		}
	});
	btnC.setFont(new Font("Tahoma", Font.PLAIN, 18));
	btnC.setBounds(699, 163, 54, 50);
	contentPane.add(btnC);
	
	button_17 = new JButton("%");
	button_17.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			firstnum = Double.parseDouble(textField.getText());
			textField.setText("");
			operations = "%";
		}
	});
	button_17.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button_17.setBounds(763, 163, 54, 50);
	contentPane.add(button_17);
	
	button_18 = new JButton("/");
	button_18.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
		firstnum = Double.parseDouble(textField.getText());
		textField.setText("");
		operations = "/";
		}
	});
	button_18.setFont(new Font("Tahoma", Font.PLAIN, 18));
	button_18.setBounds(840, 224, 54, 50);
	contentPane.add(button_18);
	
	scrollPane = new JScrollPane();
	scrollPane.setBounds(39, 102, 529, 280);
	contentPane.add(scrollPane);
	
	table = new JTable()
	{
		public boolean isCellEditable ( int rowIndez, int colIndex)
		{
			return false;
		}
	};
	scrollPane.setViewportView(table);
	
	btnNewButton_1 = new JButton("CANCELAR");
	btnNewButton_1.setActionCommand(CMD_BTN_CANCELAR);
	btnNewButton_1.addActionListener(this);
	this.getRootPane().setDefaultButton(btnNewButton_1);
	btnNewButton_1.setBounds(412, 550, 121, 35);
	contentPane.add(btnNewButton_1);
	
	JLabel lblUtiliceLaCalculadora = new JLabel("Utilice la calculadora para calcular los ingresos");
	lblUtiliceLaCalculadora.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblUtiliceLaCalculadora.setBounds(616, 32, 290, 58);
	contentPane.add(lblUtiliceLaCalculadora);
	
	JButton btnTotalIngresos = new JButton("TOTAL INGRESOS");
	btnTotalIngresos.setActionCommand(CMD_TOTALINGRESOS);
	btnTotalIngresos.addActionListener(this);
	btnTotalIngresos.setBounds(169, 550, 170, 35);
	contentPane.add(btnTotalIngresos);
	
	setVisible(true);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setTitle("Consulta de ingresos");
	setBounds(70, 10, 950, 650);
	
	}
	
	
	
	public void CargarTabla()
	{
		try
		{
			String query = "select * from billete";
			PreparedStatement pat = connection.prepareStatement(query);
			ResultSet rs = pat.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
					
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case CMD_BTN_CANCELAR:
				this.dispose();
				break;
				
			case CMD_TOTALINGRESOS:
				boolean existenBilletes; //Devolvera false en caso de que no existan
				gesTra = new GestorTrabajador();
				existenBilletes = gesTra.ExistenBilletes(state);
				if(existenBilletes == true)
				{
					TotalIngresos();
				}
				break;
		}
	}
	
	public void TotalIngresos()
	{
		gesTra = new GestorTrabajador();
		ArrayList<Integer> totalIngresos = new ArrayList<Integer>();
		totalIngresos = gesTra.TotalIngresos(state, connection);
		System.out.println("Total Ingresos = "  + totalIngresos.toString());
		int total=0;
		if(totalIngresos.size()>=1)
		{
			for(int i=0; i<totalIngresos.size()-1; i++)
			{
				total = total + totalIngresos.get(i);
			}
		}
		System.out.println("Total = " +total);
		JOptionPane.showMessageDialog(null, "El total de ingresos ha sido de " +total+ "€");
	}
}
