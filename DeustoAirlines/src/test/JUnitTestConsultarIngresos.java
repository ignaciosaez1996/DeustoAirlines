package test;

import static org.junit.Assert.*;


import junit.framework.TestCase;

import org.junit.Test;

import LD.BasesDeDatos;
import LN.GestorCliente;
import LN.GestorTrabajador;

public class JUnitTestConsultarIngresos extends TestCase
{

	GestorTrabajador gesTra;
	GestorCliente gesCli;
	
	@Override
	protected void setUp() throws Exception 
	{
		gesTra = new GestorTrabajador();
		gesCli = new GestorCliente();
		BasesDeDatos.initBD("DeustoAirlinesTestBD");
		BasesDeDatos.crearTablaBilleteBD();
	}

	@Override
	protected void tearDown() throws Exception
	{
		BasesDeDatos.close();
	}

	@Test
	public void testComprarBilletes()
	{	
		
		gesCli.Comprar(BasesDeDatos.getStatement(), 787656, 50, "56987", "a@a.com");
		gesCli.Comprar(BasesDeDatos.getStatement(), 796824, 150, "02367", "b@b.com");
		gesCli.Comprar(BasesDeDatos.getStatement(), 896541, 180, "02367", "c@c.com");
		gesCli.Comprar(BasesDeDatos.getStatement(), 002321, 20, "02367", "d@d.com");	
		gesCli.Comprar(BasesDeDatos.getStatement(), 957821, 220, "33467", "e@e.com");
		gesCli.Comprar(BasesDeDatos.getStatement(), 957000, 220, "47227", "f@f.com");
		
		int total = 50 + 150 + 180 + 20 + 220 + 220;
		
		int totalGestor = gesTra.TotalIngresos(BasesDeDatos.getStatement(), BasesDeDatos.getConnection());
		
		assertEquals(total, totalGestor);
	}

}

