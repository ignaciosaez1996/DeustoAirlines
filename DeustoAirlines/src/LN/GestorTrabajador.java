package LN;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GestorTrabajador 
{
	
	//Devolvera la true si existe algún trabajador con el DNI y contraseña introducidos
	public boolean ValidarEntradaTra(Statement state, String DNI, String contrasenya)
	{
		String SelectBD = "select * from TRABAJADOR where (dni_tra = '" + DNI + "' and contrasenya_tra = '" + contrasenya + "')";
		try 
		{
			ResultSet rs = state.executeQuery( SelectBD );
			if(rs.next())
			{
				rs.close();			
				JOptionPane.showMessageDialog(null, "DNI y contraseña correctas", "Correcto",JOptionPane.INFORMATION_MESSAGE);					return true;
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
		
	public boolean CrearVuelos(Statement state, String CodVuelo , String capacidad,String fecha, String codpost_o, String codpost_d) //Ciudad de origen y destino distintas // maitane
	{
		try 
		{
			String SelectBD = "insert into VUELO values(" + "'" + CodVuelo + "', " + "'" + capacidad + "', "+ "'" + fecha +  "', "+ "'" + codpost_o + "', "+ "'" + codpost_d  +   "')";
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
	public ArrayList<clsVuelo> DevolverVuelos (Statement state)
	{
		ArrayList<clsVuelo> ArrayVuelos = new ArrayList<clsVuelo>();
		
		String SelectBD = "select * from vuelo";
		ResultSet rs;
		try
		{
			rs = state.executeQuery(SelectBD);
			while(rs.next())
			{
				ArrayVuelos.add(new clsVuelo(rs.getString("cod_vuelo"), rs.getInt("capacidad"), rs.getString("fecha"),rs.getString("cod_postal_o"), rs.getString("cod_postal_d")));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ArrayVuelos;
		
		
	}
	public void AsignarAgendaDeTrabajo()//Calendario
	{
		
	}
	public void VerAgendaDeTrabajo()
	{
		
	}
	public void ConsultarIngresos()
	{
		
	}
}
