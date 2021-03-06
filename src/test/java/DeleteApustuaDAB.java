
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import org.junit.Test;

import configuration.UtilDate;
import dataAccess.DataAccess;
import exceptions.EventFinished;
import test.dataAccess.TestDataAccess;
import domain.*;


public class DeleteApustuaDAB {

	// sut:system under test
	static DataAccess sut = new DataAccess(true);

	// additional operations needed to execute the test
	static TestDataAccess testDA = new TestDataAccess();

	// private Bezeroa bez=new
	// Bezeroa("Jon","Smith","Clark","SmithClark","ClarkSmith","123456789","jonsc@gmail.com",
	// new Date(2001,8,1));
	private Bezeroa bez;
	private Pertsona p;
	private Apustua apus;
	// private Event ev1=new Event("event1",new Date(2020,8,2));
	// private Event ev2=new Event("event2",new Date(2021,11,2));
	// private Question galdera= new Question(1,"question1",new Double(1.5),ev1);
	// private Pronostikoa pron=new Pronostikoa("pronostic",new Double(2),galdera);
	private Event ev;
	private Question q;
	private Pronostikoa pronos;
	private Apustua apu;
	private Bezeroa bez2;
	private Apustua apu2;

	/*
	 * @BeforeEach void setUp() throws Exception { bez.eguneratuDirua(new
	 * Double(15)); }
	 */
	@Test
	public void testOngiEgin() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
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
			ev = testDA.addEventWithQuestion("event1", oneDate, "question1", 2);
			Vector<Question> ques = ev.getQuestions();
			q = ques.get(0);
			pronos = testDA.addPronostikoatoQuestion(q, "pronos1", 4);
			ap.add(pronos);
			bez = testDA.addApustua(ap, 4, bez);
			apu = testDA.getBezeroApustu(bez, ap);
			testDA.close();
			// Apustua APU=new Apustua(4,bez,ap,null);
			// System.out.println(apu.getBezeroa());
			// System.out.println(bez.getApustuak());
			// Apustua APUSTUA=bez.baduApustua(apu);
			// if (APUSTUA==null) {
			// System.out.println("Hona?o gaizki");
			// }
			// System.out.println(APUSTUA.getIdentifikadorea());
			// assertTrue(APUSTUA.getKopurua().compareTo(APU.getKopurua())==0);

			sut.deleteApustua(apu);
			Apustua ezabatua = bez.baduApustua(apu);
			System.out.println("Apustua ezabatu du");
			assertNull(ezabatua);
		} catch (EventFinished e) {
			System.out.println("Salbuespena jaurti du");
			fail();

		} catch (NullPointerException e) {
			e.printStackTrace();
			fail();
		} finally {
			testDA.open();
			testDA.removeEvent(ev);
			testDA.removeBezeroa(bez);
			testDA.close();
		}
	}
	
	@Test
	public void NullPointerException1() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
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
			ev = testDA.addEventWithQuestion("event1", oneDate, "question1", 2);
			Vector<Question> ques = ev.getQuestions();
			q = ques.get(0);
			pronos = testDA.addPronostikoatoQuestion(q, "pronos1", 4);
			ap.add(pronos);
			bez = testDA.addApustua(ap, 4, bez);
			apu = testDA.getBezeroApustu(bez, ap);
			testDA.close();
			
			// Apustua APU=new Apustua(4,bez,ap,null);
			// System.out.println(apu.getBezeroa());
			// System.out.println(bez.getApustuak());
			// Apustua APUSTUA=bez.baduApustua(apu);
			// if (APUSTUA==null) {
			// System.out.println("Hona?o gaizki");
			// }
			// System.out.println(APUSTUA.getIdentifikadorea());
			// assertTrue(APUSTUA.getKopurua().compareTo(APU.getKopurua())==0);

			sut.deleteApustua(null);
			Apustua ezabatua = bez.baduApustua(apu);
			System.out.println("Apustua ezabatu du");
			fail();
		} catch (EventFinished e) {
			System.out.println("Salbuespena jaurti du");
			fail();

		} catch (NullPointerException e) {
			assertTrue(true);
		} finally {
			testDA.open();
			testDA.removeEvent(ev);
			testDA.removeBezeroa(bez);
			testDA.close();
		}
	}
	@Test
	public void NullPointerException2() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
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
			ev = testDA.addEventWithQuestion("event1", oneDate, "question1", 2);
			Vector<Question> ques = ev.getQuestions();
			q = ques.get(0);
			pronos = testDA.addPronostikoatoQuestion(q, "pronos1", 4);
			ap.add(pronos);
			bez = testDA.addApustua(ap, 4, bez);
			apu = testDA.getBezeroApustu(bez, ap);
			testDA.close();
			// Apustua APU=new Apustua(4,bez,ap,null);
			// System.out.println(apu.getBezeroa());
			// System.out.println(bez.getApustuak());
			// Apustua APUSTUA=bez.baduApustua(apu);
			// if (APUSTUA==null) {
			// System.out.println("Hona?o gaizki");
			// }
			// System.out.println(APUSTUA.getIdentifikadorea());
			// assertTrue(APUSTUA.getKopurua().compareTo(APU.getKopurua())==0);

			Apustua apustu=new Apustua(2, bez, ap, null);
			sut.deleteApustua(apustu);
			Apustua ezabatua = bez.baduApustua(apu);
			System.out.println("Apustua ezabatu du");
			fail();
		} catch (EventFinished e) {
			System.out.println("Salbuespena jaurti du");
			fail();

		} catch (IllegalArgumentException e) {
			System.out.println("Datu-basean ez da aurktizen");
			assertTrue(true);
		} finally {
			testDA.open();
			testDA.removeEvent(ev);
			testDA.removeBezeroa(bez);
			testDA.close();
		}
	}
	
	
	
	

	@Test
	public void testEventFinishedException() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			;
			try {
				oneDate = sdf.parse("05/10/2020");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ArrayList<Pronostikoa> ap = new ArrayList<Pronostikoa>();
			testDA.open();
			bez = testDA.addBezeroa("Jon", "Smith", "Clark", "SmithClark", "ClarkSmith", "123456789", "jonsc@gmail.com",
					new Date(2001, 8, 1));
			ev = testDA.addEventWithQuestion("event1", oneDate, "question1", 2);
			Vector<Question> ques = ev.getQuestions();
			q = ques.get(0);
			pronos = testDA.addPronostikoatoQuestion(q, "pronos1", 4);
			ap.add(pronos);
			bez = testDA.addApustua(ap, 4, bez);
			apu = testDA.getBezeroApustu(bez, ap);
			testDA.close();
			// Apustua APU=new Apustua(4,bez,ap,null);
			// System.out.println(apu.getBezeroa());
			// System.out.println(bez.getApustuak());
			// Apustua APUSTUA=bez.baduApustua(apu);
			// if (APUSTUA==null) {
			// System.out.println("Hona?o gaizki");
			// }
			// System.out.println(APUSTUA.getIdentifikadorea());
			// assertTrue(APUSTUA.getKopurua().compareTo(APU.getKopurua())==0);
			System.out.println("Hona?o ondo");

			sut.deleteApustua(apu);
			fail();
		} catch (EventFinished e) {
			System.out.println("Salbuespena jaurti du");
			assertTrue(true);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			testDA.open();
			testDA.removeEvent(ev);
			testDA.removeBezeroa(bez);
			testDA.close();
		}
	}
	
	
	//MUGA BALIOAK
	@Test
	public void testMuga0() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			;
			try {
				oneDate = sdf.parse("16/09/2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ArrayList<Pronostikoa> ap = new ArrayList<Pronostikoa>();
			testDA.open();
			bez = testDA.addBezeroa("Jon", "Smith", "Clark", "SmithClark", "ClarkSmith", "123456789", "jonsc@gmail.com",
					new Date(2001, 8, 1));
			ev = testDA.addEventWithQuestion("event1", oneDate, "question1", 2);
			Vector<Question> ques = ev.getQuestions();
			q = ques.get(0);
			pronos = testDA.addPronostikoatoQuestion(q, "pronos1", 4);
			ap.add(pronos);
			bez = testDA.addApustua(ap, 4, bez);
			apu = testDA.getBezeroApustu(bez, ap);
			testDA.close();
			// Apustua APU=new Apustua(4,bez,ap,null);
			// System.out.println(apu.getBezeroa());
			// System.out.println(bez.getApustuak());
			// Apustua APUSTUA=bez.baduApustua(apu);
			// if (APUSTUA==null) {
			// System.out.println("Hona?o gaizki");
			// }
			// System.out.println(APUSTUA.getIdentifikadorea());
			// assertTrue(APUSTUA.getKopurua().compareTo(APU.getKopurua())==0);
			System.out.println("Hona?o ondo");

			sut.deleteApustua(apu);
			fail();
		} catch (EventFinished e) {
			System.out.println("Salbuespena jaurti du");
			assertTrue(true);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			testDA.open();
			testDA.removeEvent(ev);
			testDA.removeBezeroa(bez);
			testDA.close();
		}
	}
	@Test
	public void testMuga1() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			;
			try {
				oneDate = sdf.parse("17/9/2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ArrayList<Pronostikoa> ap = new ArrayList<Pronostikoa>();
			testDA.open();
			bez = testDA.addBezeroa("Jon", "Smith", "Clark", "SmithClark", "ClarkSmith", "123456789", "jonsc@gmail.com",
					new Date(2001, 8, 1));
			ev = testDA.addEventWithQuestion("event1", oneDate, "question1", 2);
			Vector<Question> ques = ev.getQuestions();
			q = ques.get(0);
			pronos = testDA.addPronostikoatoQuestion(q, "pronos1", 4);
			ap.add(pronos);
			bez = testDA.addApustua(ap, 4, bez);
			apu = testDA.getBezeroApustu(bez, ap);
			testDA.close();
			// Apustua APU=new Apustua(4,bez,ap,null);
			// System.out.println(apu.getBezeroa());
			// System.out.println(bez.getApustuak());
			// Apustua APUSTUA=bez.baduApustua(apu);
			// if (APUSTUA==null) {
			// System.out.println("Hona?o gaizki");
			// }
			// System.out.println(APUSTUA.getIdentifikadorea());
			// assertTrue(APUSTUA.getKopurua().compareTo(APU.getKopurua())==0);
			System.out.println("Hona?o ondo");

			sut.deleteApustua(apu);
			fail();
		} catch (EventFinished e) {
			System.out.println("Salbuespena jaurti du");
			assertTrue(true);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			testDA.open();
			testDA.removeEvent(ev);
			testDA.removeBezeroa(bez);
			testDA.close();
		}
	}
	@Test
	public void testMuga2() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			;
			try {
				oneDate = sdf.parse("18/09/2021");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ArrayList<Pronostikoa> ap = new ArrayList<Pronostikoa>();
			testDA.open();
			bez = testDA.addBezeroa("Jon", "Smith", "Clark", "SmithClark", "ClarkSmith", "123456789", "jonsc@gmail.com",
					new Date(2001, 8, 1));
			ev = testDA.addEventWithQuestion("event1", oneDate, "question1", 2);
			Vector<Question> ques = ev.getQuestions();
			q = ques.get(0);
			pronos = testDA.addPronostikoatoQuestion(q, "pronos1", 4);
			ap.add(pronos);
			bez = testDA.addApustua(ap, 4, bez);
			apu = testDA.getBezeroApustu(bez, ap);
			testDA.close();
			// Apustua APU=new Apustua(4,bez,ap,null);
			// System.out.println(apu.getBezeroa());
			// System.out.println(bez.getApustuak());
			// Apustua APUSTUA=bez.baduApustua(apu);
			// if (APUSTUA==null) {
			// System.out.println("Hona?o gaizki");
			// }
			// System.out.println(APUSTUA.getIdentifikadorea());
			// assertTrue(APUSTUA.getKopurua().compareTo(APU.getKopurua())==0);
			System.out.println("Hona?o ondo");
			sut.deleteApustua(apu);
			Apustua ezabatua = bez.baduApustua(apu);
			System.out.println("Apustua ezabatu du");
			assertNull(ezabatua);
		} catch (EventFinished e) {
			System.out.println("Salbuespena jaurti du");
			assertTrue(true);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			testDA.open();
			testDA.removeEvent(ev);
			testDA.removeBezeroa(bez);
			testDA.close();
		}
	}

}
