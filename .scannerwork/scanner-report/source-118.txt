import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataAccess.DataAccess;
import domain.Apustua;
import domain.Bezeroa;
import domain.Event;
import domain.Pertsona;
import domain.Pronostikoa;
import domain.Question;
import exceptions.EventFinished;
import test.dataAccess.TestDataAccess;

class DeleteApustuaDAW {

	// sut:system under test
	static DataAccess sut = new DataAccess(true);

	// additional operations needed to execute the test
	static TestDataAccess testDA = new TestDataAccess();

	private Bezeroa bez;
	private Bezeroa bez2;
	private Pertsona p;
	private Apustua apus;
	private Event ev;
	private Question q;
	private Pronostikoa pronos;
	private Apustua apu;
	private Apustua apu2;
	

	@Test
	void testEventFinished() {
		try {
			System.out.println("TEST EVENT FINISHED");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			try {
				oneDate = sdf.parse("05/10/2020");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ArrayList<Pronostikoa> ap = new ArrayList<Pronostikoa>();
			testDA.open();
			bez = testDA.addBezeroa("Jon", "Smith", "Clark", "Jones", "ClarkSmith", "123456789", "jonsc@gmail.com",
					new Date(2001, 8, 1));
			ev = testDA.addEventWithQuestion("event1", oneDate, "question1", 2);
			Vector<Question> ques = ev.getQuestions();
			q = ques.get(0);
			pronos = testDA.addPronostikoatoQuestion(q, "pronos1", 4);
			ap.add(pronos);
			bez = testDA.addApustua(ap, 4, bez);
			apu = testDA.getBezeroApustu(bez, ap);
			testDA.close();
			sut.deleteApustua(apu);
			bez.baduApustua(apu);
			fail();
		} catch (EventFinished e) {
			//e.printStackTrace();
			System.out.println("Salbuespena jaurti du");
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			testDA.open();
			boolean b=testDA.removeEvent(ev);
			boolean c=testDA.removeBezeroa(bez);
			testDA.close();
			System.out.println(b + " " + c);		
		}
	}
	
	@Test
	void testOngiEgin2() {
		try {
			System.out.println("TEST ONGI EGIN 2");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			Date twoDate=null;
			;
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
			bez2 = testDA.addBezeroa("Marc", "Smith", "Jones", "SmithJones", "JonesSmith", "978123465", "vdsmarc@gmail.com",
					new Date(1999, 8, 1));
			testDA.addBezeroErrepikapen(bez, bez2);
			ev = testDA.addEventWithQuestion("event1", oneDate, "question1", 2);
			Vector<Question> ques = ev.getQuestions();
			q = ques.get(0);
			pronos = testDA.addPronostikoatoQuestion(q, "pronos1", 4);
			ap.add(pronos);
			bez = testDA.addApustua(ap, 4, bez);
			apu=testDA.getBezeroApustu(bez, ap);
			apu2=testDA.getBezeroApustu(bez2, ap);
			testDA.close();
			//System.out.println(apu2.getErrepikatua().erabiltzaileIzena.compareTo(apu.getBezeroa().getErabiltzaileIzena())==0);
			System.out.println("Erabiltzaile errepikatzailea:");
			System.out.print(bez.getErrepikatzaileak().get(0).getNork().getErabiltzaileIzena() +"\n");
			System.out.print(bez.getErrepikatzaileak().get(0).getNori().getErabiltzaileIzena() +"\n");
			//System.out.print(bez.getErrepikatzaileak().get(0).getNori().getErabiltzaileIzena());
			// System.out.println(APUSTUA.getIdentifikadorea());
			// assertTrue(APUSTUA.getKopurua().compareTo(APU.getKopurua())==0);
			
		
			sut.deleteApustua(apu);
			Apustua ezabatua = bez.baduApustua(apu);
			Apustua ezabatua2= bez2.baduApustua(apu2);
			if (ezabatua==null && ezabatua2==null) {
				System.out.println("Apustua ezabatu du");
				assertTrue(true);
			}
			assertTrue(true);
		} catch (EventFinished e) {
			System.out.println("Salbuespena jaurti du");
			fail();

		} catch (NullPointerException e) {
			e.printStackTrace();
			fail();
		} finally {
			testDA.open();
			boolean b=testDA.removeEvent(ev);
			boolean c=testDA.removeBezeroa(bez);
			boolean d=testDA.removeBezeroa(bez2);
			testDA.close();
			System.out.println(b + " " + c + " " + d);
		}
	}
	
	@Test
	void testOngiEgin3() {
		try {
			System.out.println("TEST ONGI EGIN 3");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			Date twoDate=null;
			;
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
			bez2 = testDA.addBezeroa("Josh", "Smith", "Jones", "Smith", "JonesSmith", "978123465", "vdsmarc@gmail.com",
					new Date(1999, 8, 1));
			testDA.addBezeroErrepikapen(bez, bez2);
			ev = testDA.addEventWithQuestion("event1", oneDate, "question1", 2);
			Vector<Question> ques = ev.getQuestions();
			q = ques.get(0);
			pronos = testDA.addPronostikoatoQuestion(q, "pronos1", 4);
			ap.add(pronos);
			bez = testDA.addApustua(ap, 4, bez);
			apu=testDA.getBezeroApustu(bez, ap);
			apu2=testDA.getBezeroApustu(bez2, ap);
			System.out.println(apu2);
			testDA.close();
			sut.deleteApustua(apu2);
			Apustua ezabatua2=bez2.baduApustua(apu2);
			System.out.println("Apustua ezabatu du");
			assertNull(ezabatua2);
			//assertNull(ezabatua);
		} catch (EventFinished e) {
			System.out.println("Salbuespena jaurti du");
			fail();

		} catch (NullPointerException e) {
			e.printStackTrace();
			fail();
		} finally {
			testDA.open();
			boolean b=testDA.removeEvent(ev);
			boolean c=testDA.removeBezeroa(bez);
			boolean d=testDA.removeBezeroa(bez2);
			testDA.close();
			System.out.println(b + " " + c + " " + d);
		}
	}
	
	@Test
	void testOngiEgin4() {
		try {
			System.out.println("TEST ONGI EGIN 3");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			Date twoDate=null;
			;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ArrayList<Pronostikoa> ap = new ArrayList<Pronostikoa>();
			testDA.open();
			bez = testDA.addBezeroa("Jon", "Smith", "Clark", "Clark", "ClarkSmith", "123456789", "jonsc@gmail.com",
					new Date(2001, 8, 1));
			bez2 = testDA.addBezeroa("Josh", "Smith", "Jones", "Smith", "JonesSmith", "978123465", "vdsmarc@gmail.com",
					new Date(1999, 8, 1));
			testDA.addBezeroErrepikapen(bez, bez2);
			ev = testDA.addEventWithQuestion("event1", oneDate, "question1", 2);
			Vector<Question> ques = ev.getQuestions();
			q = ques.get(0);
			pronos = testDA.addPronostikoatoQuestion(q, "pronos1", 4);
			ap.add(pronos);
			bez = testDA.addApustua(ap, 4, bez);
			apu=testDA.getBezeroApustu(bez, ap);
			apu2=testDA.getBezeroApustu(bez2, ap);
			System.out.println(apu2);
			bez2.removeApustua(apu2);
			boolean b=testDA.removeApustua(apu2);
			testDA.close();
			
			System.out.println("EZabatua: " + b);
			//Apustua apu3=testDA.getBezeroApustu(bez2, ap);
			//System.out.println(apu3);
			assertNull(bez2.baduApustua(apu2));
			
			System.out.println(apu);
			
			bez=sut.deleteApustua(apu);
			Apustua ezabatua2=bez.baduApustua(apu);
			System.out.println("Apustua ezabatu du");
			assertNull(ezabatua2);
			//assertNull(ezabatua);
		} catch (EventFinished e) {
			System.out.println("Salbuespena jaurti du");
			fail();

		} catch (NullPointerException e) {
			e.printStackTrace();
			fail();
		} catch (Exception e){
			e.printStackTrace();
			fail();
		}	finally {
			testDA.open();
			boolean b=testDA.removeEvent(ev);
			boolean c=testDA.removeBezeroa(bez);
			boolean d=testDA.removeBezeroa(bez2);
			testDA.close();
			System.out.println(b + " " + c + " " + d);
		}
	}
}
