package LN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Clase que relaciona la logica de presentacion (LP) con la logica de datos(LD).
 * La mayoria de los metodos que precisan informacion de la Base de Datos se encuentran en esta clase
 */
public class GestorTrabajador 
{
	
	/**
	 * Metodo que se encarga de comprobar si el DNI y la contrasenya introducidos para acceder como trabajador coinciden con alguna que este 
	 * guardada en la Base de Datos
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @param DNI: Variable identificativa de cada trabajador
	 * @param contrasenya: Contrasenya que elige cada trabajador para que solo el pueda acceder a su perfil
	 * @return true en caso de que el DNI y la contrasenya introducidas coincidan con alguna guardada en la Base de Datos
	 */
	public boolean ValidarEntradaTra(Statement state, String DNI, String contrasenya)
	{
		String SelectBD = "select * from TRABAJADOR where (dni_tra = '" + DNI + "' and contrasenya_tra = '" + contrasenya + "')";
		try 
		{
			ResultSet rs = state.executeQuery( SelectBD );
			if(rs.next())
			{
				rs.close();			
				JOptionPane.showMessageDialog(null, "DNI y contraseña correctas", "Correcto",JOptionPane.INFORMATION_MESSAGE);
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
	
	/**
	 * Metrodo que crea una fila en la tabla de Vuelos, es decir crea un vuelo
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @param CodVuelo: Codigo identificativo de cada vuelo
	 * @param capacidad: Capacidad asignada a cada vuelo
	 * @param fecha: Fecha en la que se efectuara el viaje
	 * @param codpost_o: Ciudad de la que saldra el vuelo
	 * @param codpost_d: Ciudad a la que se dirigira el vuelo
	 * @param precio: Precio por asiento asignado
	 * @return true en caso de que se consiga crear un vuelo
	 */
	public boolean CrearVuelos(Statement state, int CodVuelo , String capacidad,String fecha, String codpost_o, String codpost_d, String precio)
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

	/**
	 * Metodo que comprueba si el codigo introducido coincide con alguno introducido anteriormente
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @param codigo: Variable identificativa de cada vuelo
	 * @return true en casod de que haya un vuelo con ese codigo
	 */
	public boolean ExisteVuelo(Statement state, int codigo)
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

	/**
	 * Metodo que elimina el vuelo que se elija
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @param cod_vuelo: Variable identificativa de cada vuelo y con la que se elegira que vuelo se desea eliminar
	 * @return true en caso de que se haya logrado eliminar el vuelo seleccionado
	 */
	public boolean CancelarVuelo(Statement state, String cod_vuelo)
	{
		String SelectBD1 = "select * from vuelo";
		String SelectBD2 = "delete from vuelo where (cod_vuelo = '" + cod_vuelo + "')";
		
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
	/**
	 * Metodo que devuelve los vuelos que tengan el codigo introducido
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @param codigo: Metodo identificativo de cada vuelo y con el que se elegira el vuelo
	 * @return ArrayList<clsVuelo> de vuelos con los datos del vuelo seleccionado
	 */
	public ArrayList<clsVuelo> DevolverVuelos (Statement state, int codigo)
	{
		ArrayList<clsVuelo> ArrayVuelos = new ArrayList<clsVuelo>();
		
		String SelectBD = "select * from vuelo";
		ResultSet rs;
		try
		{
			rs = state.executeQuery(SelectBD);
			while(rs.next())
			{
				ArrayVuelos.add(new clsVuelo(rs.getInt("cod_vuelo"), rs.getInt("capacidad"), rs.getString("fecha"),rs.getString("cod_postal_o"), rs.getString("cod_postal_d"), rs.getInt("precio")));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ArrayVuelos;
	}
	
	/**
	 * Metodo que se ocupa de crear las relaciones entre trabajadores y vuelos, es decir, donde debera trabajar cada trabajador
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @param cod_vuelo: Variable identificativa de cada vuelo en la que debera trabajar cada trabajador
	 * @param dni_tra: Variable identificativa de cada trabajador, mediante la cual sera elegida
	 * @return true en caso de que se consiga guardar la tarea en la Base de Datos
	 */
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
	
	/**
	 * Metodo que se encarga de comprobar si ya hay trabajadores guardados en la Base de Datos
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @return true en caso de que ya existan trabajadores en la Base de Datos
	 */
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
	
	/**
	 * Metodo que se ocupa de devolver mediante un ArrayList de Strings las tareas asignadas a cada trabajador
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @param dni_tra: Variable identificativa de cada trabajador mediante el cual sera elegido para sacar por pantalla sus tareas
	 * @param connection: Objeto que une el DriverManager con el Statement
	 * @return un ArrayList de String que contendrá las tareas asignadas al trabajador. En caso de que no tenga ninguna será null
	 */
	public ArrayList<String> CodTarea(Statement state, String dni_tra, Connection connection)
	{		
		try 
		{
			ArrayList<String> array = new ArrayList<String>();
			String query = "select cod_vuelo from TAREA where (dni_tra = '" + dni_tra + "')";
			PreparedStatement pat = connection.prepareStatement(query);
			ResultSet rs = pat.executeQuery();
	
			//Recorremos el cursor hasta que no haya más registros
			if(rs.next()==true)
			{
				do
				{
					String cod_vuelo= rs.getString("cod_vuelo");
					array.add(cod_vuelo);
				} while(rs.next() == true);
				return array;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "El trabajador seleccionado no tiene tareas asignadas");
				return null;
			}

		} catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "El trabajador seleccionado no tiene tareas asignadas");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo que comprueba si la tarea que se intenta guardar ya esta guardada anteriormente
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @param cod_vuelo: Variable identificativa de cada vuelo
	 * @param dni_tra: Variable identificativa de cada trabajador
	 * @return true en caso de que la tarea ya estuviese guardada en la Base de Datos
	 */
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
	
	/**
	 * Metodo que comprueba si en la tabla de billetes ya existen billetes
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @return true en caso de que haya billetes guardados
	 */
	public boolean ExistenBilletes(Statement state)
	{	
		try 
		{
			String query = "select * from billete";
			ResultSet rs = state.executeQuery(query);
			if(rs.next())
			{
				rs.close();
				return true;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No hay billetes vendidos");
				return false;
			}
		}catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "No hay billetes vendidos");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Metodo encargado de extraer los datos de los billetes ya guardados y calculando la suma de los billetes vendidos saca el total de ingresos conseguidos
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @param connection: Objeto que une el DriverManager con el Statement
	 * @return un int que contiene el total de ingresos sumando los precios de los billetes guardados
	 */
	public int TotalIngresos(Statement state, Connection connection)
	{
		try 
		{
			int total=0;
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
			
			if(array.size()>=1)
			{
				for(int i=1; i<array.size(); i++)
				{
					total = total + array.get(i);
				}
			}
		     return total;

		} catch (SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
}
