import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import dataAccess.DataAccess;
import domain.Admin;
import domain.Bezeroa;
import domain.Langilea;
import domain.Pertsona;
import exceptions.UserAlreadyExist;
import test.dataAccess.TestDataAccess;

class RegisterDAB {

	// sut:system under test
	static DataAccess sut = new DataAccess(true);

	// additional operations needed to execute the test
	static TestDataAccess testDA = new TestDataAccess();

	private Pertsona p;
	private Bezeroa bez;
	private Langilea lan;
	private Admin adm;

	@Test
	void test1() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
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
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate,
					mota);
			testDA.close();

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);

			// verify the results
			assertTrue(bez != null);
			assertEquals(bez.getErabiltzaileIzena(), erabiltzaileIzena);

			// p datu basean dago
			testDA.open();
			boolean exist = testDA.existPertsona(bez);
			assertTrue(exist);
			testDA.close();

			// if the program continues fail
			// fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point fail
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			boolean b = testDA.removePertsona(p);
			b = b && testDA.removePertsona(bez);
			testDA.close();
			System.out.println("Finally " + b);
		}
	}

	@Test
	void test2() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
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
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate,
					mota);
			testDA.close();

			// invoke System Under Test (sut)
			lan = (Langilea) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia,
					emaila, oneDate, mota);

			// verify the results
			assertTrue(lan != null);
			assertEquals(lan.getErabiltzaileIzena(), erabiltzaileIzena);

			// p datu basean dago
			testDA.open();
			boolean exist = testDA.existPertsona(lan);
			assertTrue(exist);
			testDA.close();

			// if the program continues fail
			// fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point fail
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			boolean b = testDA.removePertsona(p);
			b = b && testDA.removePertsona(lan);
			testDA.close();
			System.out.println("Finally " + b);
		}
	}

	@Test
	void test3() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
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
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate,
					mota);
			testDA.close();

			// invoke System Under Test (sut)
			adm = (Admin) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);

			// verify the results
			assertTrue(adm != null);
			assertEquals(adm.getErabiltzaileIzena(), erabiltzaileIzena);

			// p datu basean dago
			testDA.open();
			boolean exist = testDA.existPertsona(adm);
			assertTrue(exist);
			testDA.close();

			// if the program continues fail
			// fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point fail
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			boolean b = testDA.removePertsona(p);
			b = b && testDA.removePertsona(adm);
			testDA.close();
			System.out.println("Finally " + b);
		}
	}

	@Test
	void test4() {
		try {
			// define paramaters
			String izena = null;
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
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
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate,
					mota);
			testDA.close();

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);

			// verify the results
			assertNull(bez);

			// if the program continues fail
			// fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point fail
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			boolean b = testDA.removePertsona(p);
			b = b && testDA.removePertsona(bez);
			testDA.close();
			System.out.println("Finally " + b);
		}
	}

	@Test
	void test5() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = null;
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
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
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate,
					mota);
			testDA.close();

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);

			// verify the results
			assertNull(bez);

			// if the program continues fail
			// fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point fail
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			boolean b = testDA.removePertsona(p);
			b = b && testDA.removePertsona(bez);
			testDA.close();
			System.out.println("Finally " + b);
		}
	}

	@Test
	void test6() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = null;
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
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
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate,
					mota);
			testDA.close();

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);

			// verify the results
			assertTrue(bez != null);
			assertEquals(bez.getErabiltzaileIzena(), erabiltzaileIzena);

			// p datu basean dago
			testDA.open();
			boolean exist = testDA.existPertsona(bez);
			assertTrue(exist);
			testDA.close();

			// if the program continues fail
			// fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point fail
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			boolean b = testDA.removePertsona(p);
			b = b && testDA.removePertsona(bez);
			testDA.close();
			System.out.println("Finally " + b);
		}
	}

	@Test
	void test7() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = null;
			String pasahitza = "unaxPA1234";
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
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate,
					mota);
			testDA.close();

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);

			// verify the results
			assertNull(bez);

			// if the program continues fail
			// fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point fail
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			boolean b = testDA.removePertsona(p);
//			b = b && testDA.removePertsona(bez);
			testDA.close();
			System.out.println("Finally " + b);
		}
	}

	@Test
	void test8() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = null;
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
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate,
					mota);
			testDA.close();

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);

			// verify the results
			assertNull(bez);

			// if the program continues fail
			// fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point fail
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			boolean b = testDA.removePertsona(p);
			b = b && testDA.removePertsona(bez);
			testDA.close();
			System.out.println("Finally " + b);
		}
	}

	@Test
	void test9() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = null;
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
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate,
					mota);
			testDA.close();

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);

			// verify the results
			assertNull(bez);

			// if the program continues fail
			// fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point fail
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			boolean b = testDA.removePertsona(p);
			b = b && testDA.removePertsona(bez);
			testDA.close();
			System.out.println("Finally " + b);
		}
	}

	@Test
	void test10() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = null;
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
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate,
					mota);
			testDA.close();

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);

			// verify the results
			assertNull(bez);

			// if the program continues fail
			// fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point fail
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			boolean b = testDA.removePertsona(p);
			b = b && testDA.removePertsona(bez);
			testDA.close();
			System.out.println("Finally " + b);
		}
	}

	@Test
	void test11() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

//			try {
//				oneDate = sdf.parse("19/10/2001");
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			String mota = "bezeroa";

			// configure the state of the system (create object in the dabatase)
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate,
					mota);
			testDA.close();

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);

			// verify the results
			assertNull(bez);

			// if the program continues fail
			// fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point fail
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			boolean b = testDA.removePertsona(p);
			b = b && testDA.removePertsona(bez);
			testDA.close();
			System.out.println("Finally " + b);
		}
	}

	@Test
	void test12() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
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
			String mota = null;

			// configure the state of the system (create object in the dabatase)
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate,
					mota);
			testDA.close();

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);

			// verify the results
			assertTrue(bez != null);

			assertEquals(bez.getErabiltzaileIzena(), erabiltzaileIzena);

			// p datu basean dago
			testDA.open();
			boolean exist = testDA.existPertsona(bez);
			assertTrue(exist);
			testDA.close();

			// if the program continues fail
			fail();
		} catch (Exception e) {
			assertNull(bez);

		}
//		finally {
		// Remove the created objects in the database (cascade removing)
//			testDA.open();
//			boolean b = testDA.removePertsona(p);
//			b = b && testDA.removePertsona(bez);
//			testDA.close();
//			System.out.println("Finally " + b);
//		}
	}

	@Test
	void test13() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2008");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure the state of the system (create object in the dabatase)
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate,
					mota);
			testDA.close();

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);

			// verify the results
			assertNull(bez);

			// if the program continues fail
			// fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point fail
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			boolean b = testDA.removePertsona(p);
			b = b && testDA.removePertsona(bez);
			testDA.close();
			System.out.println("Finally " + b);
		}
	}

	@Test
	void test14() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "hauEmailaDa";
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
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate,
					mota);
			testDA.close();

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);

			// verify the results
			assertNull(bez);

			// if the program continues fail
			// fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point fail
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			boolean b = testDA.removePertsona(p);
			b = b && testDA.removePertsona(bez);
			testDA.close();
			System.out.println("Finally " + b);
		}
	}

	@Test
	void test15() {
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
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate,
					mota);
			testDA.close();

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);

			// verify the results
			assertNull(bez);

			// if the program continues fail
			// fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point fail
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			boolean b = testDA.removePertsona(p);
			b = b && testDA.removePertsona(bez);
			testDA.close();
			System.out.println("Finally " + b);
		}
	}

	@Test
	void test16() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "una";
			String pasahitza = "unaxPA1234";
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
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate,
					mota);
			testDA.close();

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);

			// verify the results
			assertNull(bez);

			// if the program continues fail
			// fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point fail
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			boolean b = testDA.removePertsona(p);
			b = b && testDA.removePertsona(bez);
			testDA.close();
			System.out.println("Finally " + b);
		}
	}

	@Test
	void test17() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "6888240120";
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
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, "peterpan", pasahitza, telefonoZbkia, emaila, oneDate,
					mota);
			testDA.close();

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);

			// verify the results
			assertNull(bez);

			// if the program continues fail
			// fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point fail
			fail();
		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			boolean b = testDA.removePertsona(p);
			b = b && testDA.removePertsona(bez);
			testDA.close();
			System.out.println("Finally " + b);
		}
	}

	@Test
	void test18() {
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
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
			testDA.open();
			p = testDA.addPertsona(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);
			testDA.close();

			// invoke System Under Test (sut)
			bez = (Bezeroa) sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate, mota);

			// if the program continues fail
			fail();
		} catch (UserAlreadyExist e) {
			// if the program goes to this point fail
			assertTrue(true);
		} finally {
			// Remove the created objects in the database (cascade removing)
			testDA.open();
			boolean b = testDA.removePertsona(p);
			testDA.close();
			System.out.println("Finally " + b);
		}
	}
}
