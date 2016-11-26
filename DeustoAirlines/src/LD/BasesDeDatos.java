package LD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

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
		try 
		{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			statement = connection.createStatement();
			statement.setQueryTimeout(30); //poner timeout 30 msg
			JOptionPane.showMessageDialog(null, "La Base de Datos se ha conectado");
			return connection;
		} catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "La base de datos no se ha podido conectar");
			return null;
		}
	}
	  
	
	/** 
	 * Cierra la conexion con la Base de Datos
	 */
	public static void close()
	{
		 	if(statement != null)
			try
		 	{
				statement.close();
				connection.close();
			
		 	} catch (SQLException e)
		 	{
				JOptionPane.showMessageDialog(null, "El cierre de la conexión ha fallado");
			}
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
			statement.executeUpdate("create table CLIENTE ( correo string, nombre_cli string, contrasenya string )");
	} catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "La creación de tabla CLIENTE ha fallado");  
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
			statement.executeUpdate("create table if not exists TRABAJADOR( dni_tra string, nombre_tra string, contrasenya_tra string )");
		} catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "La creación de tabla TRABAJADOR ha fallado");  //Señal de que la tabla ya existe
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
			statement.executeUpdate("CREATE TABLE VUELO COD_VUELO VARCHAR(10) NOT NULL PRIMARY KEY,"+
									"CAPACIDAD NUMBER (4),"+
									"FECHA TIMESTAMP" +
									"COD_POSTAL_O VARCHAR(40) NOT NULL REFERENCES CIUDAD_ORIGEN(COD_CIUDAD_O)" +
									"COD_POSTAL_D VARCHAR(40) NOT NULL REFERENCES CIUDAD_DESTINO(COD_CIUDAD_D)");
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
			statement.executeUpdate("create table BILLETE ( cod_billete string, precio int, cod_vuelo string, correo string )");
		} catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "La creación de tabla BILLETE ha fallado");
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
			statement.executeUpdate("create table CIUDAD_ORIGEN ( cod_postal_o int, nombre_ciu_o string )");
		} catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "La creación de tabla CIUDAD_ORIGEN ha fallado");  
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
			statement.executeUpdate("create table CIUDAD_DESTINO ( cod_postal_d int, nombre_ciu_d string )");
		} catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "La creación de tabla CIUDAD_DESTINO ha fallado");  
		}
	}
	
	/** 
	 * Crea una tabla de ciudades destino en una base de datos, si no existía ya.
	 * Debe haberse inicializado la conexión correctamente.
	 */
	public static void crearTablaTareaBD()
	{
		if (statement==null) return; 
		try 
		{
			statement.executeUpdate("create table TAREA ( cod_vuelo string, dni_trabajador string )");
		} catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "La creación de tabla TAREA ha fallado");   
		}
	}
	
	/**
	 * Crea filas para la tabla de trabajadores.
	 * Debe haberse inicializada la conexión correctamente.
	 */
	public static void InsertarTrabajadores(Statement statement)
	{
		if(statement==null)
		{
			
		}
		else
		{
			try 
			{
				statement.executeUpdate("insert into TRABAJADOR values('72545454B','Antonio', 'Piloto', '7254");
				statement.executeUpdate("INSERT INTO TRABAJADOR VALUES (“7255555C” ,  “Laura” , “Azafata” , “7255”)");
				statement.executeUpdate("INSERT INTO TRABAJADOR VALUES (“34565758D” ,  “Belen” , “Recepcionista” , “3456”)");
				statement.executeUpdate("INSERT INTO TRABAJADOR VALUES (“56544323E” ,  “Mikel” , “Controlador Aéreo” , “5654”)");
				statement.executeUpdate("INSERT INTO TRABAJADOR VALUES (“23234556L” ,  “Jon” , “Mecanico” , “2323”)");
				statement.executeUpdate("INSERT INTO TRABAJADOR VALUES (“72437891O” ,  “Arritxu” , “Azafata” , “7243”)");
				statement.executeUpdate("INSERT INTO TRABAJADOR VALUES (“54434356K” ,  “Gorka” , “Piloto” , “5443”)");
				statement.executeUpdate("INSERT INTO TRABAJADOR VALUES (“89765643T” ,  “Miren” , “Mecanica” , “8976”)");
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}		
	}
}

