package LP;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import LD.BasesDeDatos;
import LN.clsVuelo;

import javax.swing.JList;

import java.awt.BorderLayout;

import javax.swing.JSpinner;



public class consultaVuelo extends JFrame 
{
	public consultaVuelo() {
	}
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* public static ArrayList<clsVuelo> getAllCustomer() throws ClassNotFoundException, SQLException {
	   
		Connection conn = BasesDeDatos.getConnection();
	    Statement stm;
	    stm = conn.createStatement();
	    String sql = "Select * From ...";
	    ResultSet rst;
	    rst = stm.executeQuery(sql);
	    ArrayList<clsVuelo> customerList = new ArrayList<>();
	    while (rst.next()) {
	    clsVuelo customer = new clsVuelo( rst.getInt("capacidad") ,rst.getString("cod_vuelo"),rst.getString("fecha"));
	    customerList.add(customer);
	    }
	    return customerList;
	} */
	
	
	
	DefaultListModel lm = new DefaultListModel();
		
		
	
}