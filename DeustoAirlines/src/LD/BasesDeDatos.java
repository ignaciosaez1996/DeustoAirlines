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
			statement.executeUpdate("CREATE TABLE CLIENTE CORREO VARCHAR(40) NOT NULL PRIMARY KEY,"+
									"NOMBRE_CLI VARCHAR (30),"+
									"CONTRASEÑA_CLI VARCHAR(30))");
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
			statement.executeUpdate("CREATE TABLE TRABAJADOR DNI_TRABAJADOR VARCHAR(9) NOT NULL PRIMARY KEY,"+
									"NOMBRE_TRA VARCHAR (30),"+
									"CATEGORIA VARCHAR (40),"+
									"CONTRASEÑA_TRA VARCHAR(30))");
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
			statement.executeUpdate("CREATE TABLE BILLETE COD_BILLETE VARCHAR(10) NOT NULL PRIMARY KEY,"+
									"PRECIO NUMBER(3) DEFAULT 100,"+
									"COD_VUELO VARCHAR (10) NOT NULL REFERENCES VUELO(COD_VUELO) ON DELETE CASCADE,"+
									"CORREO VARCHAR(40) NOT NULL REFERENCES CLIENTE(CORREO)");
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
			statement.executeUpdate("CREATE TABLE CIUDAD_ORIGEN COD_POSTAL_O NUMBER(5) NOT NULL PRIMARY KEY,"+
									"NOMBRE_CIU_O VARCHAR(30))");
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
			statement.executeUpdate("CREATE TABLE CIUDAD_DESTINO COD_POSTAL_D NUMBER(5) NOT NULL PRIMARY KEY,"+
									"NOMBRE_CIU_D VARCHAR(30))");
		} catch (SQLException e) 
		{
			e.printStackTrace();  
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
			statement.executeUpdate("CREATE TABLE TAREA COD_VUELO VARCHAR(10) NOT NULL REFERENCES VUELO(COD_VUELO) ON DELETE CASCADE,"+
									"DNI_TRABAJADOR VARCHAR(9) NOT NULL REFERENCES TRABAJADOR(DNI_TRABAJADOR) ON DELETE CASCADE)" +
									"PRIMARY KEY (COD_VUELO, DNI_TRABAJADOR))");
		} catch (SQLException e) 
		{
			e.printStackTrace();  
		}
	}
	
	/**
	 * Crea filas para la tabla de trabajadores.
	 * Debe haberse inicializada la conexión correctamente.
	 */
	public static void InsertarTrabajadores()
	{
		if(statement==null)
		{
			
		}
		else
		{
			try 
			{
				statement.executeUpdate("INSERT INTO TRABAJADOR VALUES (“72545454B” ,  “Antonio” , “Piloto” , “7254”)");
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
