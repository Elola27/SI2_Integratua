import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Admin;
import domain.Bezeroa;
import domain.Event;
import domain.Langilea;
import domain.Pertsona;
import exceptions.UserAlreadyExist;
import test.businessLogic.TestFacadeImplementation;

public class RegisterInt {
	static BLFacadeImplementation sut;
	static TestFacadeImplementation testBL;

	private Pertsona p;
	private Bezeroa bez;
	private Langilea lan;
	private Admin adm;

	@BeforeClass
	public static void setUpClass() {
		// sut= new BLFacadeImplementation();

		// you can parametrize the DataAccess used by BLFacadeImplementation
		// DataAccess da= new
		// DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
		DataAccess da = new DataAccess(false);

		sut = new BLFacadeImplementation(da);

		testBL = new TestFacadeImplementation();
	}
	
	
	@Test
	public void test1() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			
			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure the state of the system (create object in the dabatase)
			p = testBL.addPertsona(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate, mota);

			// invoke System Under Test (sut)
			sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate, mota);

			// if the program continues fail
			fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point OK
			// fail();
			assertTrue(true);
		} finally {
			// Remove the created objects in the database (cascade removing)
			boolean b = testBL.removePertsona(p);
			System.out.println("Finally " + b);
		}
	}

	
	@Test
	public void test2() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			
			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure the state of the system (create object in the dabatase)
			p = testBL.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate, mota);

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate, mota);

			assertTrue(bez != null);
			assertEquals(bez.getErabiltzaileIzena(), erabiltzaileIzena);
			
			boolean exist = testBL.existPertsona(bez);
			assertTrue(exist);
			// if the program continues fail
			//fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point OK
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			boolean b = testBL.removePertsona(p);
			b = b && testBL.removePertsona(bez);
			System.out.println("Finally " + b);
		}
	}
	
	@Test
	public void test3() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			
			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "langilea";

			// configure the state of the system (create object in the dabatase)
			p = testBL.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate, mota);

			// invoke System Under Test (sut)
			lan = (Langilea) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate, mota);

			assertTrue(lan != null);
			assertEquals(lan.getErabiltzaileIzena(), erabiltzaileIzena);
			
			boolean exist = testBL.existPertsona(lan);
			assertTrue(exist);
			// if the program continues fail
			//fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point OK
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			boolean b = testBL.removePertsona(p);
			b = b && testBL.removePertsona(lan);
			System.out.println("Finally " + b);
		}
	}
	
	@Test
	public void test4() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			
			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "admin";

			// configure the state of the system (create object in the dabatase)
			p = testBL.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate, mota);

			// invoke System Under Test (sut)
			adm = (Admin) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate, mota);

			assertTrue(adm != null);
			assertEquals(adm.getErabiltzaileIzena(), erabiltzaileIzena);
			
			boolean exist = testBL.existPertsona(adm);
			assertTrue(exist);
			// if the program continues fail
			//fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point OK
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			boolean b = testBL.removePertsona(p);
			b = b && testBL.removePertsona(adm);
			System.out.println("Finally " + b);
		}
	}
	
	@Test
	public void test5() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			
			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "gaizki";

			// configure the state of the system (create object in the dabatase)
			p = testBL.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate, "bezeroa");

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate, mota);

			assertTrue(bez != null);
			assertEquals(bez.getErabiltzaileIzena(), erabiltzaileIzena);
			
			boolean exist = testBL.existPertsona(bez);
			assertTrue(exist);
			// if the program continues fail
			fail();
		} catch (Exception e) {
			// if the program goes to this point OK
			assertNull(bez);
		} finally {
			// Remove the created objects in the database (cascade removing)
			boolean b = testBL.removePertsona(p);
			System.out.println("Finally " + b);
		}
	}
}
