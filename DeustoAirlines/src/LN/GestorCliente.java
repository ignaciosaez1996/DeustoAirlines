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
public class GestorCliente
{

	/**
	 * Metodo que lleva a cabo la creacion de una fila nueva en la tabla de clientes
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @param correo: Correo del cliente que es el identificativo de cada uno 
	 * @param nombre: Nombre del cliente
	 * @param contrasenya: Contrasenya fijada por el cliente que sirve para que solo el pueda entrar en su cuenta
	 * @return false en caso de que no se haya podido guardar el cliente en la Base de Datos
	 */
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
	
	/**
	 * Metodo que comprueba si existe algun cliente que coincida con el correo introducido, que es el identificativo de cada cliente
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @param correo: Correo del cliente que es identificativo de cada uno
	 * @return true en caso de que haya algun cliente con el correo introducido
	 */
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
	
	/**
	 * Metodo que valida la entrada del cliente al programa, es decir, valida si el correo introducido coincide con la contrasenya introducida
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @param correo: Correo del cliente que es identificativo de cada uno
	 * @param contrasenya: Contrasenuya fijada por el cliente que sirve para que solo el pueda entrar en su cuenta
	 * @return true si existe algun cliente con la contrasenya y correo introducidos
	 */
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
	
	/**
	 * Metodo que comprueba si existe algun vuelo con la ciudad de origen y destino introducidas
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @param seleccionado1: Ciudad de origen del vuelo
	 * @param seleccionado2: Ciudad de destino del vuelo
	 * @return true si existe el vuelo con la ciudad de origen y destino introducidas
	 */
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
	
	/**
	 * Metodo que devuelve el precio por asiento del vuelo introducido
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @param cod_vuelo: Codigo del vuelo del que se quiere obtener el precio, variable identificativa de cada vuelo
	 * @return el precio que tenga el vuelo introducido. Si no existiera devolveria 0.
	 */
	public int DevolverPrecio(Statement state, String cod_vuelo)
	{
		try
		{
			int resultado;
			String query = "select precio from VUELO where(cod_vuelo = '" + cod_vuelo + "')";
			ResultSet rs;
			rs = state.executeQuery(query);
			if(rs.next())
			{
				resultado = rs.getInt("precio");
				return resultado;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Nunca debería aparecer este mensaje");
				return 0;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Metodo que crea una fila en la tabla de billetes creando el billete a cada cliente
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @param cod_billete: Codigo del billete que es identificativo de cada uno
	 * @param precio: Precio del billete que viene fijado desde el vuelo. Cada vuelo tiene un precio por asiento
	 * @param cod_vuelo: Codigo del vuelo del que se esta obteniendo el billete
	 * @param correo: Correo del cliente que esta comprando el billete, es identificativo de cada cliente
	 * @return false en caso de que no se haya podido comprar el billete
	 */
	public boolean Comprar(Statement state, int cod_billete, int precio, String cod_vuelo, String correo)
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
	
	/**
	 * Metodo que devuelve los codigos de vuelo de los billetes que haya comprado UN cliente en un ArrayList de Strings
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @param correo: Correo del cliente del que se quieren extraer los billetes que haya comprado
	 * @param connection: Objeto que une el DriverManager con el Statement
	 * @return ArrayList de Strings que contienen los codigos de vuelo de los billetes que haya comprado el cliente
	 */
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
	
	/**
	 * Metodo encargado de eliminar los billetes enviados mediante el codigo de billete
	 * @param state: Objeto necesario para ejecutar una sentencia de SQL sobre la Base de Datos que debe estar abierta anteriormente mediante connection
	 * @param cod_billete: Variable identificativa de cada billete, es con el que se localiza el billete 
	 * @return false en caso de que no se haya podido eliminar el billete
	 */
	public boolean CancelarBillete(Statement state, String cod_billete)
	{
		String SelectBD1 = "select * from billete";
		String SelectBD2 = "delete from billete where (cod_billete = '" + cod_billete + "')";
		
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
				JOptionPane.showMessageDialog(null, "Se ha eliminado el billete", "Correcto",JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}

	}
	}
