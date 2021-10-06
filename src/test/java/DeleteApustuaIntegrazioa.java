import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import org.junit.BeforeClass;
import org.junit.Test;

import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;
import domain.*;
public class DeleteApustuaIntegrazioa {
	 static BLFacadeImplementation sut;
	 static TestFacadeImplementation testBL;

	private Event ev;
	private Bezeroa bez;
	private ArrayList<Pronostikoa> ap;
	private Question q;
	private Pronostikoa pronos;
	private Apustua apu;
	private Vector<Question> questions;
	
	@BeforeClass
	public static void setUpClass() {
		//sut= new BLFacadeImplementation();
		
		// you can parametrize the DataAccess used by BLFacadeImplementation
		//DataAccess da= new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
		DataAccess da= new DataAccess(false);

		sut=new BLFacadeImplementation(da);
		
		testBL= new TestFacadeImplementation();
	}
	
	@Test
	//sut.createQuestion:  The event has one question with a queryText. 
	public void test1() {
		try {
			
			//define paramaters
			String eventText="event1";
			String queryText="query1";
			Float betMinimum=new Float(2);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			//configure the state of the system (create object in the dabatase)
			ev = testBL.addEventWithQuestion(eventText,oneDate,queryText,betMinimum );
			
			
			//invoke System Under Test (sut)  
			sut.createQuestion(ev, queryText, betMinimum);
			
			
			//if the program continues fail
		    fail();
		   } catch (QuestionAlreadyExist e) {
			// TODO Auto-generated catch block
			// if the program goes to this point OK  
			assertTrue(true);
			} catch (EventFinished e) {
				// if the program goes to this point fail
			    fail();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				  //Remove the created objects in the database (cascade removing)   
		          boolean b=testBL.removeEvent(ev);
		           System.out.println("Finally "+b);          
		        }
		   }
	
	@Test
	//sut.createQuestion:  The event has NOT one question with a queryText. 
	public void test2() {
		try {
			
			//define paramaters
			String eventText="event1";
			String queryText="query1";
			Float betMinimum=new Float(2);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			//configure the state of the system (create object in the dabatase)
			ev = testBL.addEventWithQuestion(eventText,oneDate,"query2",betMinimum );
			
			//invoke System Under Test (sut)  
			Question q=sut.createQuestion(ev, queryText, betMinimum);
			
			
			//verify the results
			assertTrue(q!=null);
			assertEquals(q.getQuestion(),queryText);
			assertEquals(q.getBetMinimum(),betMinimum,0);
			
			
		   } catch (QuestionAlreadyExist e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
			} catch (EventFinished e) {
				// if the program goes to this point fail
			    fail();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				  //Remove the created objects in the database (cascade removing)   
		          boolean b=testBL.removeEvent(ev);
		           System.out.println("Finally "+b);          
		        }
		   }
	@Test
	public void testONA() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			ap= new ArrayList<>();
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bez=testBL.addBezeroa("Jon", "Smith", "Clark", "SmithClark", "ClarkSmith", "123456789", "jonsc@gmail.com",
					new Date(2001, 8, 1));
			ev=testBL.addEventWithQuestion("event1", oneDate, "question1", 2);
			System.out.println(bez);
			q=ev.getQuestions().get(0);
			System.out.println(q.getEvent());
			pronos=testBL.addPronostikoatoQuestion(q, "pronostic1", 2);
			ap.add(pronos);
			System.out.println(pronos.getIdentifikadorea());
			bez=testBL.addApustua(ap, 4, bez);
			apu=testBL.getBezeroApustu(bez, ap);
			System.out.println(pronos);
			System.out.println(ap);
			System.out.println(apu);
			System.out.println(apu.getPronostikoak());
			System.out.println(testBL.getBezeroApustu(bez, ap));
			bez=sut.deleteApustua(apu);
			assertTrue(bez.baduApustua(apu)==null);
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}finally {
			testBL.removeBezeroa(bez);
			testBL.removeEvent(ev);
			//testBL.removeApustua(apu);
		}
	}
	
	
}