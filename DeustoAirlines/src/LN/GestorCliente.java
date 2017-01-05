package LN;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GestorCliente
{
	//Devolvera false en caso de que no se halla podido introducir el cliente
	public boolean ClienteNuevo(Statement state, String correo, String nombre, String contrasenya) 
	{
		try 
		{
			String SelectBD = "insert into CLIENTE values(" + "'" + correo + "', " + "'" + nombre + "', "+ "'" + contrasenya + "')";
			int val;
			val = state.executeUpdate( SelectBD );
			//ExecuteUpdate devuelve el numero de filas que se ven afectadas que en nuestro caso debe ser 1 sino señal de que no esta bien
			if (val!= 1)
			{
				return false;  
			}else
			{
				return true;
			}
		}catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, this, "El correo introducido ya está registrado", 0);
			e.printStackTrace();
			return false;
		}
	}
	
	//Devolvera true en caso de que ya halla un cliente con ese correo
	public boolean ExisteCliente(Statement state, String correo)
	{
		String SelectBD = "select * from CLIENTE where (correo = '" + correo + "')";
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
	
	//Devolvera la true si existe algún cliente con el correo y contraseña introducidos
	public boolean ValidarEntradaCli(Statement state, String correo, String contrasenya)
	{
		String SelectBD = "select * from CLIENTE where (correo = '" + correo + "' and contrasenya = '" + contrasenya + "')";
		try 
		{
			ResultSet rs = state.executeQuery( SelectBD );
			if(rs.next())
			{
				rs.close();
				JOptionPane.showMessageDialog(null, "Nombre de usuario y contraseña correctas", "Correcto",JOptionPane.INFORMATION_MESSAGE);					return true;
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
	
	public boolean EncontrarVueloOrigenDestino(Statement state, String seleccionado1, String seleccionado2)
	{
		String query = "select * from VUELO where (cod_postal_o = '" + seleccionado1 + "' and cod_postal_d = '" + seleccionado2 + "')";
		try 
		{
			ResultSet rs = state.executeQuery(query );
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
	
	public String DevolverPrecio(Statement state, String cod_vuelo)
	{
		try
		{
			String resultado;
			String query = "select precio from VUELO where(cod_vuelo = '" + cod_vuelo + "')";
			ResultSet rs;
			rs = state.executeQuery(query);
			if(rs.next())
			{
				resultado = rs.getString("precio");
				return resultado;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Nunca debería aparecer este mensaje");
				return null;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	//Devolverá false en caso de que no se halla podido comprar el billete
	public boolean Comprar(Statement state, int cod_billete, String precio, String cod_vuelo, String correo)
	{
		
		try 
		{
			String query = "insert into BILLETE values(" + "'" + cod_billete + "'," + "'" + precio + "'," + "'" + cod_vuelo + "'," + "'" + correo + "')";
			int val;
			val = state.executeUpdate( query );
			if (val!= 1)
			{
				JOptionPane.showMessageDialog(null, "No se ha podido comprar el billete");
				return false;  
			}else
			{
				return true;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se ha podido comprar el billete");
			return false;
		}
	}
	
	public ArrayList<String> CodVuelo(Statement state, String correo, Connection connection)
	{
		
		
		//Recorremos el cursor hasta que no haya más registros
		try 
		{
			ArrayList<String> array = new ArrayList<String>();
			String query = "select cod_vuelo from BILLETE where (correo = '" + correo + "')";
			PreparedStatement par = connection.prepareStatement(query);
			ResultSet rs = par.executeQuery();
			if(rs.next()==true)
			{
				do
				{
					String cod_vuelo = rs.getString("Cod_vuelo");
					array.add(cod_vuelo);
				}while(rs.next()==true);
				return array;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No tiene billetes comprados");
				return null;
			}
		}catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "No tiene billetes comprados");
			e.printStackTrace();
			return null;
		}
	}
}
