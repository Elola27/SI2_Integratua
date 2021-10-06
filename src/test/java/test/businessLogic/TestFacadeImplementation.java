package test.businessLogic;


import java.util.ArrayList;
import java.util.Date;

import javax.jws.WebMethod;

import configuration.ConfigXML;
import domain.Apustua;
import domain.Bezeroa;
import domain.Event;
import domain.Pertsona;
import domain.Pronostikoa;
import domain.Question;
import exceptions.EventFinished;
import test.dataAccess.TestDataAccess;

public class TestFacadeImplementation {
	TestDataAccess dbManagerTest;
 	
    
	   public TestFacadeImplementation()  {
			
			System.out.println("Creating TestFacadeImplementation instance");
			ConfigXML c=ConfigXML.getInstance();
			dbManagerTest=new TestDataAccess(); 
			dbManagerTest.close();
		}
		
		 
		public boolean removeEvent(Event ev) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removeEvent(ev);
			dbManagerTest.close();
			return b;

		}
		
		public Event addEventWithQuestion(String desc, Date d, String q, float qty) {
			dbManagerTest.open();
			Event o=dbManagerTest.addEventWithQuestion(desc,d,q, qty);
			dbManagerTest.close();
			return o;

		}
		
		public boolean removeBezeroa(Bezeroa b) {
			dbManagerTest.open();
			boolean ba=dbManagerTest.removeBezeroa(b);
			dbManagerTest.close();
			return ba;
		}
		
		public boolean removeApustua(Apustua b) {
			dbManagerTest.open();
			boolean ba=dbManagerTest.removeApustua(b);
			dbManagerTest.close();
			return ba;
		}
		
		public Apustua getBezeroApustu(Bezeroa bez, ArrayList<Pronostikoa> ap) {
			dbManagerTest.open();
			Apustua ba=dbManagerTest.getBezeroApustu(bez,ap);
			dbManagerTest.close();
			return ba;
		}
		public Pronostikoa addPronostikoatoQuestion(Question q, String deskrip, double kuot)  {
			dbManagerTest.open();
			Pronostikoa ba=dbManagerTest.addPronostikoatoQuestion(q, deskrip, kuot);
			dbManagerTest.close();
			return ba;
		}
		public Bezeroa addApustua(ArrayList<Pronostikoa> pronostikoak, double a, Bezeroa bezero)  {
			dbManagerTest.open();
			Bezeroa ba=dbManagerTest.addApustua(pronostikoak,a,bezero);
			dbManagerTest.close();
			return ba;
		}
		public Bezeroa addBezeroa(String izena, String abizena1, String abizena2, String erabiltzaileIzena, String pasahitza, String telefonoZbkia, String emaila, Date jaiotzeData) {
			dbManagerTest.open();
			Bezeroa ba=dbManagerTest.addBezeroa(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, jaiotzeData);
			dbManagerTest.close();
			return ba;
		}
		
		public void addBezeroErrepikapen(Bezeroa a,Bezeroa b) {
			dbManagerTest.open();
			dbManagerTest.addBezeroErrepikapen(a, b);
			dbManagerTest.close();
		}
		
		public Pertsona addPertsona(String izena, String abizena1, String abizena2, String erabiltzaileIzena,
				String pasahitza, String telefonoZbkia, String emaila, Date jaiotzeData, String mota) {
			dbManagerTest.open();
			Pertsona p = dbManagerTest.addPertsona(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, jaiotzeData, mota);
			dbManagerTest.close();
			return p;
		}

		
		public boolean removePertsona(Pertsona p) {
			dbManagerTest.open();
			boolean b = dbManagerTest.removePertsona(p);
			dbManagerTest.close();
			return b;
		}
		
		public boolean existPertsona(Pertsona p) {
			dbManagerTest.open();
			boolean b = dbManagerTest.existPertsona(p);
			dbManagerTest.close();
			return b;
		}
		
		

}
