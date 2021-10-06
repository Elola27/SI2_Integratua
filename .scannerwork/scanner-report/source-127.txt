package test.dataAccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import domain.Pertsona;
import domain.Admin;
import domain.Langilea;
import configuration.ConfigXML;
import domain.Apustua;
import domain.Bezeroa;
import domain.Errepikapena;
import domain.Event;
import domain.Pronostikoa;
import domain.Question;
import exceptions.EventFinished;

public class TestDataAccess {
	protected EntityManager db;
	protected EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public TestDataAccess() {

		System.out.println("Creating TestDataAccess instance");

		open();

	}

	public void open() {

		System.out.println("Opening TestDataAccess instance ");

		String fileName = c.getDbFilename();

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}

	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e != null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			System.out.println(db.getTransaction().isActive());
			return true;
		} else
			return false;
	}

	public boolean removeBezeroa(Bezeroa ev) {
		System.out.println(">> DataAccessTest: removeBezeroa");
		Bezeroa e = db.find(Bezeroa.class, ev.getErabiltzaileIzena());
		if (e != null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			System.out.println(db.getTransaction().isActive());
			return true;
		} else
			return false;
	}

	public boolean removeApustua(Apustua a) {
		System.out.println(">> DataAccessTest: removeApustua");
		Apustua ap = db.find(Apustua.class, a.getIdentifikadorea());
		if (ap != null) {
			db.getTransaction().begin();
			db.remove(ap);
			db.getTransaction().commit();
			System.out.println(db.getTransaction().isActive());
			return true;
		} else {
			return false;
		}
	}

	public Event addEventWithQuestion(String desc, Date d, String question, float qty) {
		System.out.println(">> DataAccessTest: addEvent");
		Event ev = null;
		db.getTransaction().begin();
		try {
			ev = new Event(desc, d);
			ev.addQuestion(question, qty);
			db.persist(ev);
			db.getTransaction().commit();
			System.out.println(db.getTransaction().isActive());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ev;
	}

	public boolean existQuestion(Event ev, Question q) {
		System.out.println(">> DataAccessTest: existQuestion");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e != null) {
			return e.DoesQuestionExists(q.getQuestion());
		} else
			return false;

	}

	public boolean existPronostikoa(Pronostikoa p, Question q) {
		System.out.println(">> DataAccessTest: existQuestion");
		Question e = db.find(Question.class, q.getQuestionNumber());
		if (e != null) {
			return e.DoesPronosticExists(p.getDeskripzioa());
		} else
			return false;

	}

	public boolean existApustua(Bezeroa b, Apustua apu) {
		System.out.println(">> DataAccessTest: existQuestion");
		Bezeroa bezeroa = db.find(Bezeroa.class, b.getErabiltzaileIzena());
		if (bezeroa != null) {
			if (bezeroa.baduApustua(apu) != null) {
				return true;
			} else {
				return false;
			}
		} else
			return false;

	}

	public Apustua getBezeroApustu(Bezeroa bez, ArrayList<Pronostikoa> ap) {
		System.out.println(">> DataAccessTest: getBezeroApustu");
		Bezeroa b = db.find(Bezeroa.class, bez.getErabiltzaileIzena());
		Vector<Apustua> apustuak = b.getApustuak();
		Apustua apustu = null;
		for (Apustua apu : apustuak) {
			ArrayList<Pronostikoa> pron = apu.getPronostikoak();
			if (ap.size() == pron.size()) {
				int i = 0;
				while (i < ap.size()) {
					if (ap.get(i).equals(pron.get(i))) {
						i++;
					}
				}
				if (i == ap.size()) {
					apustu = apu;
				}

			}
		}
		System.out.println(db.getTransaction().isActive());
		return apustu;
	}

	public Pronostikoa addPronostikoatoQuestion(Question q, String deskrip, double kuot) {
		System.out.println(">> DataAccessTest: addPronostikoa");
		Pronostikoa pron = null;
		Question qu = db.find(Question.class, q.getQuestionNumber());
		db.getTransaction().begin();
		try {
			pron = qu.addPronostic(deskrip, kuot);
			db.persist(qu);
			db.getTransaction().commit();
			System.out.println(db.getTransaction().isActive());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pron;
	}

	public Bezeroa addApustua(ArrayList<Pronostikoa> pronostikoak, double a, Bezeroa bezero) {
		System.out.println(">> DataAccessTest: addApustua");
		Bezeroa bez = db.find(Bezeroa.class, bezero.getErabiltzaileIzena());
		Apustua apu = null;
		Apustua apu2=null;
		Pronostikoa pron=null;
		db.getTransaction().begin();
		try {
			apu = bez.addApustua(pronostikoak, a, null);
			db.persist(apu);
			for (Pronostikoa i : pronostikoak) {
				pron=db.find(Pronostikoa.class, i.getIdentifikadorea());
				pron.addApustua(apu);
			}
			for(Errepikapena errepikapen:bez.getErrepikatzaileak()) {
				Bezeroa nork=errepikapen.getNork();
				apu2=nork.addApustua(pronostikoak, a, bez);
				for (Pronostikoa i: pronostikoak) {
					pron=db.find(Pronostikoa.class, i.getIdentifikadorea());
					i.addApustua(apu2);
				}
				db.persist(apu2);
			}		
			db.getTransaction().commit();
			//System.out.println(db.getTransaction().isActive());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bez;
	}

	public Bezeroa addBezeroa(String izena, String abizena1, String abizena2, String erabiltzaileIzena,
			String pasahitza, String telefonoZbkia, String emaila, Date jaiotzeData) {
		System.out.println(">> DataAccessTest: addBezeroa");
		Bezeroa bez = null;
		db.getTransaction().begin();
		try {
			bez = new Bezeroa(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					jaiotzeData);
			db.persist(bez);
			db.getTransaction().commit();
			System.out.println(db.getTransaction().isActive());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bez;
	}

	public void addBezeroErrepikapen(Bezeroa a, Bezeroa b) {
		System.out.println(">> DataAccessTest: addErrepikapen");
		Bezeroa bez = db.find(Bezeroa.class, a.getErabiltzaileIzena());
		Bezeroa bez2 = db.find(Bezeroa.class, b.getErabiltzaileIzena());
		db.getTransaction().begin();
		try {
			Errepikapena errepikapen = bez.addErrepikatzailea(b, 1.5, 100, 0.2);
			bez2.addErrepikatua(errepikapen);
			db.persist(errepikapen);
			db.getTransaction().commit();
			System.out.println(db.getTransaction().isActive());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Pertsona addPertsona(String izena, String abizena1, String abizena2, String erabiltzaileIzena,
			String pasahitza, String telefonoZbkia, String emaila, Date jaiotzeData, String mota) {
		System.out.println(">> DataAccessTest: addPertsona");
		Pertsona p = null;
		db.getTransaction().begin();
		try {
			if (mota == "bezeroa")
				p = new Bezeroa(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
						jaiotzeData);
			else if (mota == "langilea")
				p = new Langilea(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
						jaiotzeData);
			else if (mota == "admin")
				p = new Admin(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
						jaiotzeData);
			if (p != null) {
				db.persist(p);
			}
			db.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	public boolean removePertsona(Pertsona p) {
		System.out.println(">> DataAccessTest: removePertsona");
		Pertsona per = db.find(Pertsona.class, p.getErabiltzaileIzena());
		if (per != null) {
			db.getTransaction().begin();
			db.remove(per);
			db.getTransaction().commit();
			return true;
		} else {
			return false;
		}
	}

	public boolean existPertsona(Pertsona p) {
		System.out.println(">> DataAccessTest: existPertsona");
		Pertsona per = db.find(Pertsona.class, p.getErabiltzaileIzena());
		if (per == null) {
			return false;
		} else {
			return true;
		}
	}

}
