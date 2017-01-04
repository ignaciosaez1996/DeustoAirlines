package LN;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class GestorTrabajador 
{
	
	//Devolvera la true si existe alg�n trabajador con el DNI y contrase�a introducidos
	public boolean ValidarEntradaTra(Statement state, String DNI, String contrasenya)
	{
		String SelectBD = "select * from TRABAJADOR where (dni_tra = '" + DNI + "' and contrasenya_tra = '" + contrasenya + "')";
		try 
		{
			ResultSet rs = state.executeQuery( SelectBD );
			if(rs.next())
			{
				rs.close();			
				JOptionPane.showMessageDialog(null, "DNI y contrase�a correctas", "Correcto",JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	//Devuelve true si crea un vuelo
	public boolean CrearVuelos(Statement state, String CodVuelo , String capacidad,String fecha, String codpost_o, String codpost_d, double precio)
	{
		try 
		{
			String SelectBD = "insert into VUELO values(" + "'" + CodVuelo + "', " + "'" + capacidad + "', "+ "'" + fecha +  "', "+ "'" + codpost_o + "', "+ "'" + codpost_d  + "'," +  "'" + precio  + "')";
			int val;
			val = state.executeUpdate( SelectBD );
			
			if (val!= 1)
			{
				return false;  
			}else
			{
				return true;
			}
		}catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	//Devolvera true en caso de que ya halla un vuelo con ese codigo
	public boolean ExisteVuelo(Statement state, String codigo)
	{
		String SelectBD = "select * from VUELO where (cod_vuelo = '" + codigo + "')";
		try 
		{
			ResultSet rs = state.executeQuery( SelectBD );
			if(rs.next())
			{
				rs.close();
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	//Devolvera true si se ha logrado eliminar 1 fila
	public boolean CancelarVuelo(Statement state, String cod_vuelo)
	{
		String SelectBD1 = "select * from vuelo";
		String SelectBD2 = "delete * from VUELO where (cod_vuelo = '" + cod_vuelo + "')";
		
		try 
		{
			ResultSet rs1 = state.executeQuery( SelectBD1 );
			ResultSet rs2 = state.executeQuery( SelectBD2 );
			
			if(rs1==rs2)
			{
				return false;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Se ha eliminado el vuelo", "Correcto",JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	//Devuelve los vuelos existentes en un ArrayList
	public ArrayList<clsVuelo> DevolverVuelos (Statement state, String codigo)
	{
		ArrayList<clsVuelo> ArrayVuelos = new ArrayList<clsVuelo>();
		
		String SelectBD = "select * from vuelo";
		ResultSet rs;
		try
		{
			rs = state.executeQuery(SelectBD);
			while(rs.next())
			{
				ArrayVuelos.add(new clsVuelo(rs.getString("cod_vuelo"), rs.getInt("capacidad"), rs.getString("fecha"),rs.getString("cod_postal_o"), rs.getString("cod_postal_d"), rs.getDouble("precio")));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ArrayVuelos;
	}
	
	//Devuelve true si crea la tarea
	public boolean CrearTarea(Statement state, String cod_vuelo, String dni_tra)
	{
		try 
		{
			String SelectBD = "insert into TAREA values(" + "'" + cod_vuelo + "', " + "'" + dni_tra + "')";
			int val;
			val = state.executeUpdate( SelectBD );
			
			if (val!= 1)
			{
				return false;  
			}else
			{
				return true;
			}
		}catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	//Devolver� true si ya estan guardados los trabajadores
	public boolean ExistenTrabajadores(Statement state)
	{		
		try
		{
			String SelectBD = "select * from trabajador";
			ResultSet rs;
			rs = state.executeQuery(SelectBD);
			if(rs.next())
			{
				rs.close();
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<String> CodTarea(Statement state, String dni_tra)
	{
		ArrayList<String> array = new ArrayList<String>();
		try
		{
			String query = "select cod_vuelo from TAREA where (dni_tra = '" + dni_tra + "')";
			ResultSet rs;
			rs = state.executeQuery(query);
			if(rs.next())
			{
				array.add(rs.getString("cod_vuelo"));
			}
			else
			{
				JOptionPane.showMessageDialog(null, "El trabajador seleccionado no tiene tareas asignadas");
				return null;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "El trabajador seleccionado no tiene tareas asignadas");
			return null;
		}
		return array;
	}
	
	//Devolver� true si la tarea ya est� registrada
	public boolean TareaRepetida (Statement state, String cod_vuelo, String dni_tra)
	{
		String SelectBD = "select * from tarea where (dni_tra = '" + dni_tra + "' and cod_vuelo = '" + cod_vuelo + "')";
		try
		{
			ResultSet rs = state.executeQuery( SelectBD );
			if(rs.next())
			{
				rs.close();			
				JOptionPane.showMessageDialog(null, "Tarea anteriormente registrada");
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
