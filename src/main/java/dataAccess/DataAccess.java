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
/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public DataAccess(boolean initializeMode) {

		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());
		open(initializeMode);
		
		if (initializeMode)
			initializeDB();

	}

	
	public DataAccess() {
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

			Event ev1 = new Event(1,"Atl?tico-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event(3, "Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event(4, "Alav?s-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event(5, "Espa?ol-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event(7, "Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event(8, "Girona-Legan?s", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event(10, "Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event(11, "Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event(12, "Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event(13, "Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event(14, "Alav?s-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event(15, "Espa?ol-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = new Event(17, "M?laga-Valencia", UtilDate.newDate(year, month + 1, 28));
			Event ev18 = new Event(18, "Girona-Legan?s", UtilDate.newDate(year, month + 1, 28));
			Event ev19 = new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year, month + 1, 28));
			Event ev20 = new Event(20, "Betis-Real Madrid", UtilDate.newDate(year, month + 1, 28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("??Qui??n ganar?? el partido?", 1);
				q2 = ev1.addQuestion("??Qui??n meter?? el primer gol?", 2);
				q3 = ev11.addQuestion("??Qui??n ganar?? el partido?", 1);
				q4 = ev11.addQuestion("??Cu??ntos goles se marcar??n?", 2);
				q5 = ev17.addQuestion("??Qui??n ganar?? el partido?", 1);
				q6 = ev17.addQuestion("??Habr?? goles en la primera parte?", 2);
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
	public Question createQuestion(Event event, String question, double betMinimum) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question))
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);

		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions
						// property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1", Event.class);
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		TypedQuery<Date> query = db.createQuery(
				"SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2", Date.class);
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d : dates) {
			System.out.println(d.toString());
			res.add(d);
		}
		return res;
	}

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

	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= " + event + " question= " + question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);

	}
	
	public Pertsona isLogin(String erabiltzaileIzena, String pasahitza) {
		TypedQuery<Pertsona> query = db.createQuery("SELECT p FROM Pertsona p WHERE p.erabiltzaileIzena=?1 AND p.pasahitza=?2", Pertsona.class);
		query.setParameter(1, erabiltzaileIzena);
		query.setParameter(2, pasahitza);
		List<Pertsona> pertsona = query.getResultList();
		if(pertsona.isEmpty()) {
			return null;
		}else {
			return pertsona.get(0);
		}
	}
	
	public Pertsona register(String izena, String abizena1, String abizena2, String erabiltzaileIzena, String pasahitza, String telefonoZbkia, String emaila, Date jaiotzeData, String mota) throws UserAlreadyExist{
		TypedQuery<Pertsona> query = db.createQuery("SELECT p FROM Pertsona p WHERE p.erabiltzaileIzena=?1", Pertsona.class);
		query.setParameter(1, erabiltzaileIzena);
		List<Pertsona> pertsona = query.getResultList();
		if(!pertsona.isEmpty()) {
			throw new UserAlreadyExist();
		}else {
			Pertsona berria = null;
			if(mota.equals("admin")) {
				berria = new Admin(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, jaiotzeData);
			}else if (mota.equals("langilea")) {
				berria = new Langilea(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, jaiotzeData);
			}else if (mota.equals("bezeroa")) {
				berria = new Bezeroa(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, jaiotzeData);
			}
			db.getTransaction().begin();
			db.persist(berria);
			db.getTransaction().commit();
			return berria;
		}
	}
	
	public void createEvent(String description, Date eventDate) throws EventAlreadyExist{
		TypedQuery<Event> query = db.createQuery("SELECT e FROM Event e WHERE e.description=?1 AND e.eventDate=?2", Event.class);
		query.setParameter(1, description);
		query.setParameter(2, eventDate);
		List<Event> gertaera = query.getResultList();
		if(!gertaera.isEmpty()) {
			throw new EventAlreadyExist();
		}else {
			Event berria = new Event(description, eventDate);
			db.getTransaction().begin();
			db.persist(berria);
			db.getTransaction().commit();
		}

	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}
	
	public Vector<Question> getQuestions(Event event) {
		System.out.println(">> DataAccess: getQuestions");
		Vector<Question> res = new Vector<Question>();
		TypedQuery<Question> query = db.createQuery("SELECT q FROM Question q WHERE q.event=?1", Question.class);
		query.setParameter(1, event);
		List<Question> events = query.getResultList();
		for (Question q : events) {
			System.out.println(q.toString());
			res.add(q);
		}
		return res;
	}

	public Pronostikoa createPronostic(Question question, String description, double kuota) throws PronosticAlreadyExist {

		Question q = db.find(Question.class, question.getQuestionNumber());
		
		if(q.DoesPronosticExists(description))
			throw new PronosticAlreadyExist();

		db.getTransaction().begin();
		Pronostikoa p = q.addPronostic(description, kuota);
		db.persist(q);
		db.getTransaction().commit();
		return p;

	}
	
	public void emaitzaIpini(Question question, Pronostikoa pronostikoa){
		Pronostikoa p = db.find(Pronostikoa.class, pronostikoa.getIdentifikadorea());
		Question q = db.find(Question.class, question.getQuestionNumber());
		db.getTransaction().begin();
		q.setResult(pronostikoa.getDeskripzioa());
		Vector<Apustua> apustuak = p.getApustuak();
		Bezeroa bezeroa;
		double irabazia;
		boolean irabazi;
		double komisioa;
		for(Apustua a : apustuak) {
			irabazi=a.eguneratuAsmatutakoKop();
			komisioa=0;
			if(irabazi) {
				if (a.getErrepikatua()!=null) {
					Bezeroa bez = a.getErrepikatua();
					bezeroa = a.getBezeroa();
					Errepikapena errepikapen=bezeroa.getErrepikapena(bez);
					komisioa=(a.getKopurua()*a.getKuotaTotala()-a.getKopurua())*errepikapen.getKomisioa();
					bez.addMugimendua("Apustu errepikatuaren komisioa ("+bezeroa+")", komisioa,"irabazi");
				}
				bezeroa=a.getBezeroa();
				irabazia=a.getKopurua()*a.getKuotaTotala()-komisioa;
				bezeroa.addMugimendua("Apustua irabazi ("+a.getIdentifikadorea()+")", irabazia, "irabazi");
			}
		}	
		db.getTransaction().commit();
	}
	
	public Bezeroa apustuaEgin(ArrayList<Pronostikoa> pronostikoak, double a, Bezeroa bezero) {
		Bezeroa erabiltzaile = db.find(Bezeroa.class, bezero.getErabiltzaileIzena());
		Pronostikoa pronos;
		ArrayList<Pronostikoa> pronostikoSorta = new ArrayList<Pronostikoa>();
		for(Pronostikoa p : pronostikoak) {
			pronos = db.find(Pronostikoa.class, p.getIdentifikadorea());
			pronostikoSorta.add(pronos);
		}
		db.getTransaction().begin();
		Apustua apus = erabiltzaile.addApustua(pronostikoSorta, a, null);
		for(Pronostikoa p : pronostikoSorta) {
			p.addApustua(apus);
		}
		db.persist(apus);
		Vector<Errepikapena> jarraitzaile=erabiltzaile.getErrepikatzaileak();
		for(Errepikapena er: jarraitzaile) {
			double apustudiru=0;
			if (er.getHilabeteHonetanGeratzenDena()>0) {
				if (er.getHilabeteHonetanGeratzenDena()>=er.getApustatukoDena()*a) {
					apustudiru=er.getApustatukoDena()*a;
				} else {
					apustudiru=er.getHilabeteHonetanGeratzenDena();
				}
				if (er.getNork().getDirua() >= apustudiru) {
					Apustua apustu = er.getNork().addApustua(pronostikoSorta, apustudiru, erabiltzaile);
					for (Pronostikoa p : pronostikoSorta) {
						p.addApustua(apustu);
					}
					er.getNork().addMugimendua("Apustu errepikatua egin ("+erabiltzaile+")", -apustudiru, "jokatu");
					er.eguneratuHilHonetanGeratzenDena(-apustudiru);
					db.persist(apus);
				}
			}
		}
		erabiltzaile.addMugimendua("Apustua egin ", -a, "jokatu");
		db.getTransaction().commit();
		return erabiltzaile;
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
	
	public Bezeroa diruaSartu(double u, Bezeroa bezeroa) {
		db.getTransaction().begin();
		Bezeroa erabiltzaile = db.find(Bezeroa.class, bezeroa.getErabiltzaileIzena());
		erabiltzaile.addMugimendua("Bankuko diru-sarrera", u, "bankua");
		db.persist(erabiltzaile);
		db.getTransaction().commit();
		return erabiltzaile;
	}
	
	/*public void ezabatuGertaera(Event event) {
		Bezeroa bezeroa;
		int x;
=======
	public void ezabatuGertaera(Event event) {
>>>>>>> branch 'master' of https://github.com/Elola27/SI2_Integratua.git
		Event e = db.find(Event.class, event.getEventNumber());
		db.getTransaction().begin();
		borratuGertaerarenApustuak(e);
		db.remove(e);
		db.getTransaction().commit();
	}


	private void borratuGertaerarenApustuak(Event e) {
		int x;
		for (Question question : e.getQuestions()) {
			for (Pronostikoa pronostic : question.getPronostics()) {
				for (Apustua bet : pronostic.getApustuak()) {
					x = bet.removePronostikoa(pronostic);
					if(x==0) {
						etendakoApustua(bet);
					}else{
						apustuaKobratu(bet);
					}
				}
			}
		}
<<<<<<< HEAD
		db.remove(e);
		db.getTransaction().commit();
		Apustua a = db.find(Apustua.class, 53);
		System.out.println(a);
	}*/
	
	public void ezabatuGertaera(Event event) {
		Event e = db.find(Event.class, event.getEventNumber());
		db.getTransaction().begin();
		borratuGertaerarenApustuak(e);
		db.remove(e);
		db.getTransaction().commit();
	}


	private void borratuGertaerarenApustuak(Event e) {
		int x;
		for (Question question : e.getQuestions()) {
			for (Pronostikoa pronostic : question.getPronostics()) {
				for (Apustua bet : pronostic.getApustuak()) {
					x = bet.removePronostikoa(pronostic);
					pronostikoKopBaldintza(x, bet);
				}
			}
		}
	}


	private void pronostikoKopBaldintza(int x, Apustua bet) {
		Bezeroa bezeroa=bet.getBezeroa();
		double komisioa = 0;
		if(x==0) {
			etendakoErrepikapena(bet, bezeroa);
			bezeroa.addMugimendua("Apustuaren dirua itzuli ("+bet.getIdentifikadorea()+")", bet.getKopurua(),"bueltatu");
			bezeroa.removeApustua(bet);
			db.remove(bet);
		}else{
			komisioa = komisioaOrdaindu(bet, bezeroa, komisioa);
			double irabazia=bet.getKopurua()*bet.getKuotaTotala()-komisioa;
			bezeroa.addMugimendua("Apustua irabazi ("+bet.getIdentifikadorea()+")", irabazia, "irabazi");
		}
	}


//	private void apustuaKobratu(Apustua bet) {
//		Bezeroa bezeroa;
//		bezeroa = bet.getBezeroa();
//		double komisioa = 0;
//		komisioa = komisioaOrdaindu(bet, bezeroa, komisioa);
//		double irabazia=bet.getKopurua()*bet.getKuotaTotala()-komisioa;
//		bezeroa.addMugimendua("Apustua irabazi ("+bet.getIdentifikadorea()+")", irabazia, "irabazi");
//	}


	private double komisioaOrdaindu(Apustua bet, Bezeroa bezeroa, double komisioa) {
		if (bet.getErrepikatua()!=null) {
			Bezeroa bez = bet.getErrepikatua();
			Errepikapena errepikapen=bezeroa.getErrepikapena(bez);
			komisioa=(bet.getKopurua()*bet.getKuotaTotala()-bet.getKopurua())*errepikapen.getKomisioa();
			bez.addMugimendua("Apustu errepikatuaren komisioa ("+bezeroa+")", komisioa,"irabazi");
		}
		return komisioa;
	}


//	private void etendakoApustua(Apustua bet) {
//		Bezeroa bezeroa;
//		bezeroa=bet.getBezeroa();
//		etendakoErrepikapena(bet, bezeroa);
//		bezeroa.addMugimendua("Apustuaren dirua itzuli ("+bet.getIdentifikadorea()+")", bet.getKopurua(),"bueltatu");
//		bezeroa.removeApustua(bet);
//		db.remove(bet);
//	}

	//Errepikapena eten da eta hilabeteko dirua zuzendu behar da
	private void etendakoErrepikapena(Apustua bet, Bezeroa bezeroa) {
		if (bet.getErrepikatua()!=null) {
			Bezeroa erre=bet.getErrepikatua();
			Errepikapena er=bezeroa.getErrepikapena(erre);
			er.eguneratuHilHonetanGeratzenDena(bet.getKopurua());
		}
	}
	
	public Bezeroa getBezeroa(String ErabiltzaileIzena) {
		Bezeroa erabiltzaile = db.find(Bezeroa.class, ErabiltzaileIzena);
		return erabiltzaile;
	}
	
	public Langilea getLangilea(String ErabiltzaileIzena) {
		Langilea erabiltzaile = db.find(Langilea.class, ErabiltzaileIzena);
		return erabiltzaile;
	}
	
	public Vector<Bezeroa> getBezeroak(String username, Bezeroa bezeroa){
		Bezeroa erabiltzaile = db.find(Bezeroa.class, bezeroa.getErabiltzaileIzena());
		Vector<Bezeroa> res = new Vector<Bezeroa>();
		TypedQuery<Bezeroa> query = db.createQuery("SELECT b FROM Bezeroa b", Bezeroa.class);
		List<Bezeroa> bezeroak = query.getResultList();
		for (Bezeroa b : bezeroak) {
			if(b.isPublikoa() && !b.getErabiltzaileIzena().equals(erabiltzaile.getErabiltzaileIzena()) && b.getErabiltzaileIzena().contains(username) && !erabiltzaile.baduMezua(b) && !erabiltzaile.errepikapenErlazioaDu(b)) {
				res.add(b);
			}
		}
		return res;
	}
	
	public Bezeroa bidaliMezua(Bezeroa nork, Bezeroa nori, String mezua, String gaia, String mota, double zenbatApostatu, double hilabeteanZenbat, double zenbatErrepikatuarentzat) {
		Bezeroa igorlea = db.find(Bezeroa.class, nork.getErabiltzaileIzena());
		Bezeroa hartzailea = db.find(Bezeroa.class, nori.getErabiltzaileIzena());
		db.getTransaction().begin();
		BezeroartekoMezua mezuBerria = igorlea.addBidalitakoBezeroMezua(nori, mezua, gaia, mota, zenbatApostatu, hilabeteanZenbat, zenbatErrepikatuarentzat);
		hartzailea.addJasotakoBezeroMezua(mezuBerria);
		db.persist(mezuBerria);
		db.getTransaction().commit();
		return igorlea;
    }
	
	
	//ERREFAKTORIZATUTAKOA
	public void errepikatu(Bezeroa nork, Bezeroa nori,DatuErrefaktorizatuERREPIKAPEN a){
		Bezeroa errepikatzailea = db.find(Bezeroa.class, nork.getErabiltzaileIzena());
		Bezeroa errepikatua = db.find(Bezeroa.class, nori.getErabiltzaileIzena());
		db.getTransaction().begin();
		Errepikapena errepikapenBerria = errepikatua.addErrepikatzailea(nork,a);
		errepikatzailea.addErrepikatua(errepikapenBerria);
		db.persist(errepikapenBerria);
		db.getTransaction().commit();
	}
	
	public Vector<Mezua> getMezuak(Bezeroa bezeroa){
		Bezeroa erabiltzailea = db.find(Bezeroa.class, bezeroa.getErabiltzaileIzena());
		return erabiltzailea.getMezuak();
	}
	
	public void mezuaIrakurri(Mezua mezua) {
		Mezua m = db.find(Mezua.class, mezua.getIdentifikadorea());
		db.getTransaction().begin();
		m.setIrakurrita(true);
		db.getTransaction().commit();
	}
	
	public void removeMezua(Mezua mezua) {
		if(mezua instanceof BezeroartekoMezua) {
			BezeroartekoMezua m = db.find(BezeroartekoMezua.class, mezua.getIdentifikadorea());
			Bezeroa nork = m.getIgorlea();
			Bezeroa nori = m.getHartzailea();
			db.getTransaction().begin();
			nork.ezabatuBidalitakoBezeroMezua(m);
			nori.ezabatuJasotakoBezeroMezua(m);
			db.remove(m);
			db.getTransaction().commit();
		}else {
			ArretaMezua m = db.find(ArretaMezua.class, mezua.getIdentifikadorea());
			ArretaElkarrizketa elkarrizketa = m.getElkarrizketa();
			db.getTransaction().begin();
			if(elkarrizketa.isAmaituta()) {
				elkarrizketa.removeMezua(m);
				db.remove(m);
				if(elkarrizketa.mezurikEz()) {
					db.remove(elkarrizketa);
				}
			}else {
				m.setIkusgaiBezeroarentzat(false);
			}
			db.getTransaction().commit();
		}
    }
	
	public Bezeroa eguneratuEzarpenak(Bezeroa b, double komisioa, boolean publikoa) {
		Bezeroa erabiltzailea = db.find(Bezeroa.class, b.getErabiltzaileIzena());
		db.getTransaction().begin();
		erabiltzailea.eguneratuEzarpenak(publikoa, komisioa);
		db.getTransaction().commit();
		return erabiltzailea;
	}
	
	public Vector<PronostikoaContainer> getPronostikoak(Apustua a){
		Apustua ap = db.find(Apustua.class, a.getIdentifikadorea());
		ArrayList<Pronostikoa> pronostikoak = ap.getPronostikoak();
		Vector<PronostikoaContainer> emaitza = new Vector<PronostikoaContainer>();
		for(Pronostikoa p : pronostikoak) {
			emaitza.add(new PronostikoaContainer(p));
		}
		return emaitza;
	}
	
	public ArretaElkarrizketa arretaElkarrizketaSortu(Bezeroa bezeroa, String gaia, String mezua) {
		Bezeroa erabiltzailea = db.find(Bezeroa.class, bezeroa.getErabiltzaileIzena());
		db.getTransaction().begin();
		ArretaElkarrizketa elkarrizketa = erabiltzailea.addElkarrizketa(gaia);
		elkarrizketa.addMezua(mezua, true);
		db.persist(elkarrizketa);
		db.getTransaction().commit();
		return elkarrizketa;
	}
	
	public ArretaElkarrizketa arretaMezuaBidali(ArretaElkarrizketa elkarrizketa, String mezua, boolean langileari) {
		ArretaElkarrizketa elkar = db.find(ArretaElkarrizketa.class, elkarrizketa.getIdentifikadorea());
		db.getTransaction().begin();
		elkar.addMezua(mezua, langileari);
		db.getTransaction().commit();
		return elkar;
	}
	
	public ArretaElkarrizketa bezeroaEsleitu(Langilea langilea) {
		TypedQuery<ArretaElkarrizketa> query = db.createQuery("SELECT ae FROM ArretaElkarrizketa ae WHERE ae.langilea IS NULL AND ae.amaituta=false", ArretaElkarrizketa.class);
		List<ArretaElkarrizketa> elkarrizketak = query.getResultList();
		if(elkarrizketak.isEmpty()) {
			return null;
		}
		Langilea l = db.find(Langilea.class, langilea.getErabiltzaileIzena());
		db.getTransaction().begin();
	    ArretaElkarrizketa elkarrizketa = elkarrizketak.get(0);
	    elkarrizketa.setLangilea(l);
		l.addElkarrizketa(elkarrizketa);
		db.getTransaction().commit();
		return elkarrizketa;
	}
	
	public BezeroaContainer getBezeroaContainer(Bezeroa b){
		Bezeroa bezeroa = db.find(Bezeroa.class, b.getErabiltzaileIzena());
		return new BezeroaContainer(bezeroa);
	}
	
	public void geldituElkarrizketa(ArretaElkarrizketa ae) {
		ArretaElkarrizketa elkar = db.find(ArretaElkarrizketa.class, ae.getIdentifikadorea());
		db.getTransaction().begin();
		elkar.gelditu();
		db.getTransaction().commit();
	}
	
	public void amaituElkarrizketa(ArretaElkarrizketa ae) {
		ArretaElkarrizketa elkar = db.find(ArretaElkarrizketa.class, ae.getIdentifikadorea());
		db.getTransaction().begin();
		for(ArretaMezua am : elkar.getBezeroakBidalitakoak()) {
			db.remove(am);
		}
		elkar.getLangilea().removeElkarrizketa(elkar);
		for(ArretaMezua am : elkar.getLangileakBidalitakoak()) {
			if(!am.isIkusgaiBezeroarentzat()) {
				db.remove(am);
			}else {
				am.setIrakurrita(true);
			}
		}
		ArretaMezua m = elkar.addMezua("", false);
		m.setAzkena(true);
		elkar.setAmaituta(true);
		db.getTransaction().commit();
	}
	
	public void gehituPuntuazioa(ArretaElkarrizketa l, Integer x) {
		ArretaElkarrizketa arretaElkarrizketa = db.find(ArretaElkarrizketa.class, l.getIdentifikadorea());
		Langilea langilea = arretaElkarrizketa.getLangilea();
		db.getTransaction().begin();
		langilea.addBalorazioa(x);
		db.getTransaction().commit();
	}
	
	public void eguneratuErrepikapenak() {
		TypedQuery<Errepikapena> query = db.createQuery("SELECT e FROM Errepikapena e", Errepikapena.class);
		List<Errepikapena> lista=query.getResultList();
		db.getTransaction().begin();
		for (Errepikapena i: lista) {
				i.setHilHonetanGeratzenDena(i.getHilabetekoMax());
		}
		db.getTransaction().commit();
	}
	
	public Vector<Langilea> getLangileak() {
		Vector<Langilea> langileak = new Vector<Langilea>();
		TypedQuery<Langilea> query = db.createQuery("SELECT l FROM Langilea l", Langilea.class);
		List<Langilea> list = query.getResultList();
		for (Langilea l : list) {
			langileak.add(l);
		}
		return langileak;
	}
	
	public ArrayList<ErrepikatuakContainer> getErrepikatzaileak(Bezeroa bezeroa) {
		ArrayList<ErrepikatuakContainer> emaitza = new ArrayList<ErrepikatuakContainer>();
		Bezeroa erabiltzailea = db.find(Bezeroa.class, bezeroa.getErabiltzaileIzena());
		Vector<Errepikapena> mezuak = erabiltzailea.getErrepikatzaileak();
		ErrepikatuakContainer x;

		for (Errepikapena m : mezuak) {
			x = new ErrepikatuakContainer(m);
			emaitza.add(x);
		}
		return emaitza;
	}

	public ArrayList<ErrepikatuakContainer> getErrepikapenak(Bezeroa bezeroa) {
		ArrayList<ErrepikatuakContainer> emaitza = new ArrayList<ErrepikatuakContainer>();
		Bezeroa erabiltzailea = db.find(Bezeroa.class, bezeroa.getErabiltzaileIzena());
		Vector<Errepikapena> mezuak = erabiltzailea.getErrepikatuak();
		ErrepikatuakContainer x;

		for (Errepikapena m : mezuak) {
			x = new ErrepikatuakContainer(m);
			emaitza.add(x);
		}
		return emaitza;
	}

	public void jarraitzeariUtzi(Errepikapena errepikapena) {
		Errepikapena e = db.find(Errepikapena.class, errepikapena.getIdentifikadorea());
		Bezeroa nork = e.getNork();
		Bezeroa nori = e.getNori();
		db.getTransaction().begin();
		nork.ezabatuErrepikatua(e);
		nori.ezabatuErrepikatzailea(e);
		db.remove(e);
		db.getTransaction().commit();
	}
}
