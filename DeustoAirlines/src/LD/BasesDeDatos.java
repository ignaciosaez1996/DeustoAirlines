package LD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BasesDeDatos 
{
	private static Connection connection = null;
	private static Statement statement = null;
	
	/** 
	 * Inicializa una BD SQLITE y devuelve una conexion con ella. Debe llamarse a este 
	 * metodo antes que ningun otro, y debe devolver no null para poder seguir trabajando con la BD.
	 * @param nombreBD	Nombre de fichero de la base de datos
	 * @return	Conexion con la base de datos indicada. Si hay algún error, se devuelve null
	 */
	public static Connection initBD (String nombreBD)
	{
		return connection;
	}
	
	/** 
	 * Cierra la conexion con la Base de Datos
	 */
	public static void close()
	{
	
	}
	
	/** 
	 * Devuelve la conexion si ha sido establecida previamente (#initBD()).
	 * @return	Conexion con la BD, null si no se ha establecido correctamente.
	 */
	public static Connection getConnection()
	{
		return connection;
	}
	
	/** 
	 * Devuelve una sentencia para trabajar con la BD,
	 * si la conexion si ha sido establecida previamente (#initBD()).
	 * @return	Sentencia de trabajo con la BD, null si no se ha establecido correctamente.
	 */
	public static Statement getStatement()
	{
		return statement;
	}
	
	/** 
	 * Crea una tabla de clientes en una base de datos, si no existía ya.
	 * Debe haberse inicializado la conexión correctamente.
	 */
	public static void crearTablaClienteBD()
	{
		if (statement==null) return; 
		try 
		{
			// Hay que poner cual es la clave primaria, las claves primarias de las demas?
			statement.executeUpdate("create table if not exists CLIENTE (correo string, nombre_cliente string, contrasenya_cliente string)");
		} catch (SQLException e) 
		{
			e.printStackTrace();  
		}
	}
	
	/** 
	 * Crea una tabla de trabajadores en una base de datos, si no existía ya.
	 * Debe haberse inicializado la conexión correctamente.
	 */
	public static void crearTablaTrabajadorBD()
	{
		if (statement==null) return; 
		try 
		{
			// Hay que poner cual es la clave primaria, las claves primarias de las demas?
			statement.executeUpdate("create table if not exists TRABAJADOR (dni string, nombre_trabajador string, contrasenya_trabajador string, categoria string)");
		} catch (SQLException e) 
		{
			e.printStackTrace();  
		}
	}
	
	/** 
	 * Crea una tabla de vuelos en una base de datos, si no existía ya.
	 * Debe haberse inicializado la conexión correctamente.
	 */
	public static void crearTablaVueloBD()
	{
		if (statement==null) return; 
		try 
		{
			// Hay que poner cual es la clave primaria, las claves primarias de las demas?
			statement.executeUpdate("create table if not exists VUELO (cod_vuelo string, capacidad int, fecha date)");
		} catch (SQLException e) 
		{
			e.printStackTrace();  
		}
	}
	
	/** 
	 * Crea una tabla de billetes en una base de datos, si no existía ya.
	 * Debe haberse inicializado la conexión correctamente.
	 */
	public static void crearTablaBilleteBD()
	{
		if (statement==null) return; 
		try 
		{
			// Hay que poner cual es la clave primaria, las claves primarias de las demas?
			statement.executeUpdate("create table if not exists BILLETE (cod_billete string, precio double)");
		} catch (SQLException e) 
		{
			e.printStackTrace();  
		}
	}
	
	/** 
	 * Crea una tabla de ciudades de origen en una base de datos, si no existía ya.
	 * Debe haberse inicializado la conexión correctamente.
	 */
	public static void crearTablaOrigenBD()
	{
		if (statement==null) return; 
		try 
		{
			// Hay que poner cual es la clave primaria, las claves primarias de las demas?
			statement.executeUpdate("create table if not exists CIU_ORIGEN (cod_postal_ori int, nombre_ori double)");
		} catch (SQLException e) 
		{
			e.printStackTrace();  
		}
	}
	
	/** 
	 * Crea una tabla de ciudades destino en una base de datos, si no existía ya.
	 * Debe haberse inicializado la conexión correctamente.
	 */
	public static void crearTablaDestinoBD()
	{
		if (statement==null) return; 
		try 
		{
			// Hay que poner cual es la clave primaria, las claves primarias de las demas?
			statement.executeUpdate("create table if not exists CIU_DESTINO (cod_postal_des int, nombre_des double)");
		} catch (SQLException e) 
		{
			e.printStackTrace();  
		}
	}
}
