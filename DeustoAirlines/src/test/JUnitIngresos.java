package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;

import LD.BasesDeDatos;
import LN.GestorTrabajador;

public class JUnitIngresos 
{
	
	@Before

	public void TotalIngresos()
	{
		Connection connection = null;
		GestorTrabajador  gesTra;
		connection = BasesDeDatos.getConnection();
		Statement state = BasesDeDatos.getStatement();
		
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
	public ArrayList<Integer> TotalIngresos(Statement state, Connection connection)
	{
		try 
		{
			ArrayList<Integer> array = new ArrayList<Integer>();
			String query = "select * from billete";
			PreparedStatement pat = connection.prepareStatement(query);
			ResultSet rs = pat.executeQuery();
	
			//Recorremos el cursor hasta que no haya más registros
			do
			{
				int precio= rs.getInt("precio");
			    array.add(precio);
			} while(rs.next() == true);
		     return array;

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Test
	public void TestSumaIngresos()
	{
		Connection connection = null;
		GestorTrabajador  gesTra;
		gesTra = new GestorTrabajador();
		connection = BasesDeDatos.getConnection();
		Statement state = BasesDeDatos.getStatement();
		ArrayList<Integer> arrayTest = new ArrayList<Integer>();
		arrayTest= gesTra.TotalIngresos(state, connection);
		int sumabilletes;
		int total=0;
		if(arrayTest.size()>=1)
		{
			for(int i=0; i<arrayTest.size()-1; i++)
			{
				total = total + arrayTest.get(i);
			}
		}
		
			
			
		
		
		
	}
}
