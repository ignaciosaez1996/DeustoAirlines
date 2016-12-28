package LN;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

	public void CancelarVuelos()
	{
		
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
