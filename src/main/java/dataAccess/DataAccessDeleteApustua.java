package dataAccess;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Admin;
import domain.Apustua;
import domain.ArretaElkarrizketa;
import domain.ArretaMezua;
import domain.Bezeroa;
import domain.BezeroaContainer;
import domain.BezeroartekoMezua;
import domain.DatuErrefaktorizatuERREPIKAPEN;
import domain.Errepikapena;
import domain.ErrepikatuakContainer;
import domain.Event;
import domain.Langilea;
import domain.Mezua;
import domain.Mugimendua;
import domain.Pertsona;
import domain.Pronostikoa;
import domain.PronostikoaContainer;
import domain.Question;
import exceptions.EventAlreadyExist;
import exceptions.EventFinished;
import exceptions.PronosticAlreadyExist;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExist;

//DATU-BASEA ERREFAKTORIZAZIOA

//2021/10/19AN SORTUA
/**
 * It implements the data access to the objectDb database
 */
public class DataAccessDeleteApustua {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public DataAccessDeleteApustua(boolean initializeMode) {

		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());
		open(initializeMode);
		
		if (initializeMode)
			initializeDB();

	}

	
	public DataAccessDeleteApustua() {
		this(false);
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();
		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}

			Event ev1 = new Event(1,"Atl�tico-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event(3, "Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event(4, "Alav�s-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event(5, "Espa�ol-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event(7, "Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event(8, "Girona-Legan�s", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event(10, "Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event(11, "Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event(12, "Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event(13, "Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event(14, "Alav�s-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event(15, "Espa�ol-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = new Event(17, "M�laga-Valencia", UtilDate.newDate(year, month + 1, 28));
			Event ev18 = new Event(18, "Girona-Legan�s", UtilDate.newDate(year, month + 1, 28));
			Event ev19 = new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year, month + 1, 28));
			Event ev20 = new Event(20, "Betis-Real Madrid", UtilDate.newDate(year, month + 1, 28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);
			}

			Admin a1 = new Admin("Tarek", "Chamkhi", "Ermina", "Tarek12301", "txakurra", "666666666","tchamkhi@gmail.com", UtilDate.newDate(2001,2,12));
			Langilea l1 = new Langilea("Oier", "Elola", "Urkizu", "ll", "ll", "987654321", "oierurkizu@gmail.com", UtilDate.newDate(2001,7,23));
			Langilea l2 = new Langilea("Oier", "Elola", "Urkizu", "l", "l", "987654321", "oierurkizu@gmail.com", UtilDate.newDate(2001,7,23));
			
			Bezeroa b1 = new Bezeroa("Josu", "Loidi", "Gorostidi", "b", "b", "123456789", "josulo@gmail.com",UtilDate.newDate(2001,8,9));
			Bezeroa b2 = new Bezeroa("Josu", "Loidi", "Gorostidi", "Josulo", "aaaaaaaa", "123456789", "josulo@gmail.com",UtilDate.newDate(2001,8,9));
			
			
			Event event1 = new Event(21,"Eibar-Celta", UtilDate.newDate(2021, 2, 17));
			Event event2 = new Event(22,"Granada-Athletic", UtilDate.newDate(2021, 2, 17));
			
			Question ques1 = event1.addQuestion("Zeinek irabaziko du partidua?", 1);
			Question ques2 = event1.addQuestion("Zeinek sartuko du lehenengo gola?", 1);
			Question ques3 = event2.addQuestion("Zeinek irabaziko du partidua?", 1);
			Question ques4 = event2.addQuestion("Golik sartuko al da lehen zatian?", 1);
			
			Pronostikoa pronos1, pronos2, pronos3, pronos4, pronos5, pronos6, pronos7, pronos8, pronos9, pronos10, pronos11, pronos12, pronos13, pronos14, pronos15, pronos16, pronos17;
			pronos1 = ques1.addPronostic("1", 1.2);
			pronos2 = ques1.addPronostic("X", 1.5);//
			pronos3 = ques1.addPronostic("2", 1.8);
			pronos4 = ques2.addPronostic("1", 1.2);//
			pronos5 = ques2.addPronostic("2", 1.6);
			pronos6 = ques2.addPronostic("Golik ez",1.8);
			pronos7 = ques3.addPronostic("1",2.2);//
			pronos8 = ques3.addPronostic("X",1.4);
			pronos9 = ques3.addPronostic("2",1.2);
			pronos10 = ques4.addPronostic("Bai",1.3);
			pronos11 = ques4.addPronostic("Ez",2.5);//
		
			pronos12 = q1.addPronostic("1", 1.2);
			pronos13 = q1.addPronostic("X",1.5);//
			pronos14 = q1.addPronostic("2",1.8);
			pronos15 = q4.addPronostic("1",1.2);//
			pronos16 = q4.addPronostic("2",1.6);
			pronos17 = q4.addPronostic("Golik ez",1.8);
			

			
			ArrayList<Pronostikoa> p = new ArrayList<Pronostikoa>();
			p.add(pronos2);
			p.add(pronos4);
			Apustua apustua1 = b1.addApustua(p, 2, null);
			Apustua apustu2=b2.addApustua(p, 4, b1);
			
			pronos2.addApustua(apustua1);
			pronos2.addApustua(apustu2);
			pronos4.addApustua(apustu2);
			pronos4.addApustua(apustua1);
			
			db.persist(apustua1);
			db.persist(apustu2);
			
			
			Mugimendua m1,m2,m3,m4,m5;
			m1 = b1.addMugimendua("Bankuko diru-sarrera", 16, "bankua", UtilDate.newDate(2021, 2, 15));
			m2 = b1.addMugimendua("Apustua egin -> "+event1+" -> "+ques1+" -> "+pronos2, -3, "jokatu", UtilDate.newDate(2021, 2, 16));
			m3 = b1.addMugimendua("Apustua egin -> "+event1+" -> "+ques2+" -> "+pronos4, -3, "jokatu", UtilDate.newDate(2021, 2, 16));
			m4 = b1.addMugimendua("Apustua egin -> "+event2+" -> "+ques3+" -> "+pronos7, -2, "jokatu", UtilDate.newDate(2021, 2, 16));
			m5 = b1.addMugimendua("Apustua egin -> "+event2+" -> "+ques4+" -> "+pronos11, -8, "jokatu", UtilDate.newDate(2021, 2, 16));
			
			db.persist(event1);
			db.persist(event2);
			
			db.persist(ques1);
			db.persist(ques2);
			db.persist(ques3);
			db.persist(ques4);
			
			db.persist(pronos1);
			db.persist(pronos2);
			db.persist(pronos3);
			db.persist(pronos4);
			db.persist(pronos5);
			db.persist(pronos6);
			db.persist(pronos7);
			db.persist(pronos8);
			db.persist(pronos9);
			db.persist(pronos10);
			db.persist(pronos11);
			
			
			db.persist(m1);
			db.persist(m2);
			db.persist(m3);
			db.persist(m4);
			db.persist(m5);	
			
			db.persist(a1);
			db.persist(l2);
			db.persist(l1);
			db.persist(b1);
			db.persist(b2);

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);

			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);
			
			db.persist(pronos12);
			db.persist(pronos13);
			db.persist(pronos14);
			db.persist(pronos15);
			db.persist(pronos16);
			db.persist(pronos17);

			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */

	public void open(boolean initializeMode) {

		System.out.println("Opening DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String fileName = c.getDbFilename();
		if (initializeMode) {
			fileName = fileName + ";drop";
			System.out.println("Deleting the DataBase");
		}

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
	
	
	
	public Bezeroa deleteApustua(Apustua apustua) throws EventFinished{
		try {
		db.getTransaction().begin();
		Apustua a=db.find(Apustua.class, apustua.getIdentifikadorea());
		ArrayList<Pronostikoa> pronostikoak = a.getPronostikoak();
		Date today = new Date();
		for(Pronostikoa p : pronostikoak) {
			Date eventDate = p.getQuestion().getEvent().getEventDate();
			if(!eventDate.after(today)) {
				db.getTransaction().commit();
				throw new EventFinished();
			}
		}
		Bezeroa bezeroa = a.getBezeroa();
		bezeroa.removeApustua(a);
		if(a.getErrepikatua()!=null) {
			Errepikapena errepikapen=bezeroa.getErrepikapena(a.getErrepikatua());
			errepikapen.eguneratuHilHonetanGeratzenDena(a.getKopurua());
		}
		bezeroa.addMugimendua("Apustua ezabatu ("+a.getIdentifikadorea()+")",a.getKopurua(),"bueltatu");
		for(Pronostikoa p : pronostikoak) {
			p.removeApustua(a);
		}
		Vector<Errepikapena> errepikatzaileak= bezeroa.getErrepikatzaileak();
		for(Errepikapena er : errepikatzaileak) {
			Bezeroa bez = er.getNork();
			Apustua apusErr = bez.baduApustua(a);
			if(apusErr!=null) {
				bez.removeApustua(apusErr);
				bez.addMugimendua("Apustu errepikatua ezabatu ("+bezeroa+")", apusErr.getKopurua(), "bueltatu");
				for (Pronostikoa p: apusErr.getPronostikoak()) {
					p.removeApustua(apusErr);
				}
				er.eguneratuHilHonetanGeratzenDena(apusErr.getKopurua());
				db.remove(apusErr);
			}
		}
		db.remove(a);
		db.getTransaction().commit();
		return bezeroa;
		}catch(NullPointerException e) {
			db.getTransaction().commit();
			throw new NullPointerException();
		}
	}
}
