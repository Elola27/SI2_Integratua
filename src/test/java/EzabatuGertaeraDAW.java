import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Apustua;
import domain.Bezeroa;
import domain.Event;
import domain.Pertsona;
import domain.Pronostikoa;
import domain.Question;
import test.dataAccess.TestDataAccess;

public class EzabatuGertaeraDAW {

	// sut:system under test
	static DataAccess sut = new DataAccess(true);

	// additional operations needed to execute the test
	static TestDataAccess testDA = new TestDataAccess();

	private Bezeroa bez;
	private Bezeroa bez2;
	private Event ev1;
	private Event ev2;
	private Question q1;
	private Question q2;
	private Pronostikoa pronos1;
	private Pronostikoa pronos2;
	private Apustua apu;
	private Apustua apu2;

	@Test
	public void test1() {
		try {
			// Lehengo datuak gorde datu basean
			System.out.println("Lehen testa. Azken pronostikoa eta errepikatzailearekin");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ArrayList<Pronostikoa> ap = new ArrayList<Pronostikoa>();

			testDA.open();
			bez = testDA.addBezeroa("Jon", "Smith", "Clark", "SmithClark", "ClarkSmith", "123456789", "jonsc@gmail.com",
					new Date(2001, 8, 1));
			bez2 = testDA.addBezeroa("Marc", "Smith", "Jones", "SmithJones", "JonesSmith", "978123465",
					"vdsmarc@gmail.com", new Date(1999, 8, 1));
			testDA.addBezeroErrepikapen(bez, bez2);
			ev1 = testDA.addEventWithQuestion("event1", oneDate, "question1", 2);
			Vector<Question> ques = ev1.getQuestions();
			q1 = ques.get(0);
			pronos1 = testDA.addPronostikoatoQuestion(q1, "pronos1", 4);
			ap.add(pronos1);
			bez = testDA.addApustua(ap, 4, bez);
			apu = testDA.getBezeroApustu(bez, ap);
			apu2 = testDA.getBezeroApustu(bez2, ap);
			testDA.close();
			// System.out.println(apu2.getErrepikatua().erabiltzaileIzena.compareTo(apu.getBezeroa().getErabiltzaileIzena())==0);
			System.out.println("Erabiltzaile errepikatzailea:");
			System.out.print(bez.getErrepikatzaileak().get(0).getNork().getErabiltzaileIzena() + "\n");
			System.out.print(bez.getErrepikatzaileak().get(0).getNori().getErabiltzaileIzena() + "\n");

			// DataAccess metodoari dei egin
			sut.ezabatuGertaera(ev1);

			Apustua ezabatua = bez.baduApustua(apu);
			Apustua ezabatua2 = bez2.baduApustua(apu2);
			if (ezabatua == null && ezabatua2 == null) {
				System.out.println("Gertaerako apustuak ongi ezabatu dira");
				assertTrue(true);
			}

			// Gertaera, galdera eta pronostikoa ezabatu direla ziurtatu
			testDA.open();
			assertTrue(!testDA.existEvent(ev1));
			assertTrue(!testDA.existQuestion(ev1, q1));
			assertTrue(!testDA.existPronostikoa(pronos1, q1));
			testDA.close();
		} catch (Exception e) {
			System.out.println(e);
			fail();
		} finally {
			testDA.open();
			boolean d = testDA.removeBezeroa(bez2);
			boolean c = testDA.removeBezeroa(bez);
			testDA.close();
			System.out.println(c + " " + d);
		}
	}

	@Test
	public void test2() {
		try {
			// Lehengo datuak gorde datu basean
			System.out.println("Lehen testa. Azken pronostikoa eta errepikatzaile gabe");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ArrayList<Pronostikoa> ap = new ArrayList<Pronostikoa>();

			testDA.open();
			bez = testDA.addBezeroa("Jon", "Smith", "Clark", "JonSmith", "ClarkSmith", "123456789", "jonsc@gmail.com",
					new Date(2001, 8, 1));

			ev1 = testDA.addEventWithQuestion("event1", oneDate, "question1", 2);
			Vector<Question> ques = ev1.getQuestions();
			q1 = ques.get(0);
			pronos1 = testDA.addPronostikoatoQuestion(q1, "pronos1", 4);
			ap.add(pronos1);
			bez = testDA.addApustua(ap, 4, bez);
			apu = testDA.getBezeroApustu(bez, ap);
			testDA.close();
			

			// DataAccess metodoari dei egin
			sut.ezabatuGertaera(ev1);

			Apustua ezabatua = bez.baduApustua(apu);
			if (ezabatua == null) {
				System.out.println("Gertaerako apustuak ongi ezabatu dira");
				assertTrue(true);
			}

			// Gertaera, galdera eta pronostikoa ezabatu direla ziurtatu
			testDA.open();
			assertTrue(!testDA.existEvent(ev1));
			assertTrue(!testDA.existQuestion(ev1, q1));
			assertTrue(!testDA.existPronostikoa(pronos1, q1));
			testDA.close();
		} catch (Exception e) {

			System.out.println(e);
			fail();
		} finally {
			testDA.open();
			boolean c = testDA.removeBezeroa(bez);
			testDA.close();
			System.out.println(c);
		}
	}

	@Test
	public void test3() {
		try {
			// Lehengo datuak gorde datu basean
			System.out.println("Lehen testa. Ez da azken pronostikoa eta errepikatzailearekin");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ArrayList<Pronostikoa> ap = new ArrayList<Pronostikoa>();

			testDA.open();
			bez = testDA.addBezeroa("Jon", "Smith", "Clark", "SmithJon", "ClarkSmith", "123456789", "jonsc@gmail.com",
					new Date(2001, 8, 1));
			bez2 = testDA.addBezeroa("Marc", "Smith", "Jones", "JonesSmith", "JonesSmith", "978123465",
					"vdsmarc@gmail.com", new Date(1999, 8, 1));
			testDA.addBezeroErrepikapen(bez, bez2);
			
			ev1 = testDA.addEventWithQuestion("event1", oneDate, "question1", 2);
			ev2 = testDA.addEventWithQuestion("event2", oneDate, "question2", 2);
			Vector<Question> ques = ev1.getQuestions();
			q1 = ques.get(0);
			ques = ev2.getQuestions();
			q2 = ques.get(0);
			pronos1 = testDA.addPronostikoatoQuestion(q1, "pronos1", 4);
			pronos2 = testDA.addPronostikoatoQuestion(q2, "pronos2", 5);
			ap.add(pronos1);
			ap.add(pronos2);
			bez = testDA.addApustua(ap, 4, bez);
			apu = testDA.getBezeroApustu(bez, ap);
			apu2 = testDA.getBezeroApustu(bez2, ap);
			testDA.close();
			System.out.println("Erabiltzaile errepikatzailea:");
			System.out.print(bez.getErrepikatzaileak().get(0).getNork().getErabiltzaileIzena() + "\n");
			System.out.print(bez.getErrepikatzaileak().get(0).getNori().getErabiltzaileIzena() + "\n");

			// DataAccess metodoari dei egin
			sut.ezabatuGertaera(ev1);

			Apustua ezabatua = bez.baduApustua(apu);
			Apustua ezabatua2 = bez2.baduApustua(apu2);
			if (ezabatua == null && ezabatua2 == null) {
				System.out.println("Gertaerako apustuak ongi ezabatu dira");
				assertTrue(true);
			}

			// Gertaera, galdera eta pronostikoa ezabatu direla ziurtatu
			testDA.open();
			assertTrue(!testDA.existEvent(ev1));
			assertTrue(!testDA.existQuestion(ev1, q1));
			assertTrue(!testDA.existPronostikoa(pronos1, q1));
			testDA.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			fail();
		} finally {
			testDA.open();
			boolean c = testDA.removeBezeroa(bez);
			boolean d=testDA.removeBezeroa(bez2);
			testDA.close();
			System.out.println(c + " " + d);
		}
	}
	
	@Test
	public void test4() {
		try {
			// Lehengo datuak gorde datu basean
			System.out.println("Lehen testa. Ez da azken pronostikoa eta errepikatzailearekin");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ArrayList<Pronostikoa> ap = new ArrayList<Pronostikoa>();

			testDA.open();
			bez = testDA.addBezeroa("Jon", "Smith", "Clark", "ClarkSmith", "ClarkSmith", "123456789", "jonsc@gmail.com",
					new Date(2001, 8, 1));
			ev1 = testDA.addEventWithQuestion("event1", oneDate, "question1", 2);
			ev2 = testDA.addEventWithQuestion("event2", oneDate, "question2", 2);
			Vector<Question> ques = ev1.getQuestions();
			q1 = ques.get(0);
			ques = ev2.getQuestions();
			q2 = ques.get(0);
			pronos1 = testDA.addPronostikoatoQuestion(q1, "pronos1", 4);
			pronos2 = testDA.addPronostikoatoQuestion(q2, "pronos2", 5);
			ap.add(pronos1);
			ap.add(pronos2);
			bez = testDA.addApustua(ap, 4, bez);
			apu = testDA.getBezeroApustu(bez, ap);
			testDA.close();
			
			// DataAccess metodoari dei egin
			sut.ezabatuGertaera(ev1);

			Apustua ezabatua = bez.baduApustua(apu);
			if (ezabatua == null) {
				System.out.println("Gertaerako apustuak ongi ezabatu dira");
				assertTrue(true);
			}

			// Gertaera, galdera eta pronostikoa ezabatu direla ziurtatu
			testDA.open();
			assertTrue(!testDA.existEvent(ev1));
			assertTrue(!testDA.existQuestion(ev1, q1));
			assertTrue(!testDA.existPronostikoa(pronos1, q1));
			assertTrue(!testDA.existPronostikoa(pronos2, q1));
			testDA.close();
		} catch (Exception e) {
			
			System.out.println(e);
			fail();
		} finally {
			testDA.open();
			boolean c = testDA.removeBezeroa(bez);
			testDA.close();
			System.out.println(c);
		}
	}
}
