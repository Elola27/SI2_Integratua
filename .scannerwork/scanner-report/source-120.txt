import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Apustua;
import domain.Bezeroa;
import domain.Event;
import domain.Pronostikoa;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

@RunWith(MockitoJUnitRunner.class)
class DeleteApustuaMockInt {

	
	DataAccess dataAccess=Mockito.mock(DataAccess.class);
	Event mockedEvent=Mockito.mock(Event.class);
	Apustua apu=Mockito.mock(Apustua.class);
	Pronostikoa pronos=Mockito.mock(Pronostikoa.class);
	Question mockedQuestion=Mockito.mock(Question.class);
	Bezeroa mockedBezero=Mockito.mock(Bezeroa.class);
	
	@InjectMocks
	BLFacade sut=new BLFacadeImplementation(dataAccess);


	@Test
	void testWellDone() {
		try {
		//String queryText="proba galdera";
		Float betMinimum=new Float(2);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;;
		try {
			oneDate = sdf.parse("05/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//b=new Bezeroa("Jon", "Smith", "Clark", "SmithClark", "ClarkSmith", "123456789", "jonsc@gmail.com",new Date(2001, 8, 1));
		Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();
		//Mockito.doReturn(null).when(dataAccess).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));
		Mockito.doReturn(mockedEvent).when(mockedQuestion).getEvent();
		Mockito.doReturn(mockedQuestion).when(pronos).getQuestion();
		ArrayList<Pronostikoa> ap=new ArrayList<Pronostikoa>();
		ap.add(pronos);
		Mockito.doReturn(new Apustua(5,mockedBezero,ap,null)).when(mockedBezero).baduApustua(apu);
		//Mockito.doReturn(null).when(mockedBezero).baduApustua(apu);
		//Apustua apust=new Apustua(5,mockedBezero,ap,null);
		mockedBezero=sut.deleteApustua(apu);
		//System.out.println(apu.getBezeroa());
		System.out.println(apu);
		Apustua apustu=mockedBezero.baduApustua(new Apustua(5,mockedBezero,ap,null));
		//Apustua apustu=null;
		assertTrue(apustu==null);
		//fail();
		}catch(EventFinished e) {
			System.out.println("Proba egokia");
			//assertTrue(true);
			fail();
		}catch(NullPointerException e) {
			e.printStackTrace();
			fail();
			
		}
	}
	@Test
	void testEventFinished() {
		try {
		//String queryText="proba galdera";
		Float betMinimum=new Float(2);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;;
		try {
			oneDate = sdf.parse("05/10/2020");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//b=new Bezeroa("Jon", "Smith", "Clark", "SmithClark", "ClarkSmith", "123456789", "jonsc@gmail.com",new Date(2001, 8, 1));
		Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();
		//Mockito.doReturn(null).when(dataAccess).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));
		Mockito.doReturn(mockedQuestion).when(pronos).getQuestion();
		Mockito.doReturn(mockedEvent).when(mockedQuestion).getEvent();
		ArrayList<Pronostikoa> ap=new ArrayList<Pronostikoa>();
		ap.add(pronos);
		Apustua apustu=new Apustua(5,mockedBezero,ap,null);
		//Mockito.doReturn(apustu).when(b).addApustua(ap, 5,null);
		//Mockito.doReturn(b).when(dataAccess).register("Jon", "Smith", "Clark", "SmithClark", "ClarkSmith", "123456789", "jonsc@gmail.com",new Date(2001, 8, 1),"bezeroa");
		Mockito.doReturn(apu).when(mockedBezero).baduApustua(apustu);
		Mockito.when(dataAccess.deleteApustua(Mockito.any(Apustua.class))).thenThrow(EventFinished.class);
		
		mockedBezero=sut.deleteApustua(apustu);
		//Apustua apu=mockedBezero.baduApustua(apustu);
		
		assertNull(apu);
		fail();
		
		}catch(EventFinished e) {
			System.out.println("Proba egokia");
			assertTrue(true);
		}catch(Exception e) {
			e.printStackTrace();
			fail();
			
		}
	}
	
	@Test
	void testNullApustu() {
		try {
		//String queryText="proba galdera";
		Float betMinimum=new Float(2);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;;
		try {
			oneDate = sdf.parse("05/10/2020");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//b=new Bezeroa("Jon", "Smith", "Clark", "SmithClark", "ClarkSmith", "123456789", "jonsc@gmail.com",new Date(2001, 8, 1));
		Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();
		//Mockito.doReturn(null).when(dataAccess).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));
		Mockito.doReturn(mockedQuestion).when(pronos).getQuestion();
		Mockito.doReturn(mockedEvent).when(mockedQuestion).getEvent();
		ArrayList<Pronostikoa> ap=new ArrayList<Pronostikoa>();
		ap.add(pronos);
		Apustua apustu=new Apustua(5,mockedBezero,ap,null);
		//Mockito.doReturn(apustu).when(b).addApustua(ap, 5,null);
		//Mockito.doReturn(b).when(dataAccess).register("Jon", "Smith", "Clark", "SmithClark", "ClarkSmith", "123456789", "jonsc@gmail.com",new Date(2001, 8, 1),"bezeroa");
		Mockito.doReturn(null).when(mockedBezero).baduApustua(apu);
		Mockito.when(dataAccess.deleteApustua(Mockito.any(Apustua.class))).thenThrow(NullPointerException.class);
		
		mockedBezero=sut.deleteApustua(apustu);
		//Apustua apu=mockedBezero.baduApustua(apustu);
		
		assertNull(apu);
		fail();
		
		}catch(NullPointerException e) {
			System.out.println("Proba egokia");
			assertTrue(true);
		}catch(Exception e) {
			e.printStackTrace();
			fail();
			
		}
	}
	
	@Test
	void testNotInDB() {
		try {
		//String queryText="proba galdera";
		Float betMinimum=new Float(2);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;;
		try {
			oneDate = sdf.parse("05/10/2020");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//b=new Bezeroa("Jon", "Smith", "Clark", "SmithClark", "ClarkSmith", "123456789", "jonsc@gmail.com",new Date(2001, 8, 1));
		Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();
		//Mockito.doReturn(null).when(dataAccess).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));
		Mockito.doReturn(mockedQuestion).when(pronos).getQuestion();
		Mockito.doReturn(mockedEvent).when(mockedQuestion).getEvent();
		ArrayList<Pronostikoa> ap=new ArrayList<Pronostikoa>();
		ap.add(pronos);
		Apustua apustu=new Apustua(5,mockedBezero,ap,null);
		//Mockito.doReturn(apustu).when(b).addApustua(ap, 5,null);
		//Mockito.doReturn(b).when(dataAccess).register("Jon", "Smith", "Clark", "SmithClark", "ClarkSmith", "123456789", "jonsc@gmail.com",new Date(2001, 8, 1),"bezeroa");
		Mockito.doReturn(null).when(mockedBezero).baduApustua(apustu);
		Mockito.when(dataAccess.deleteApustua(Mockito.any(Apustua.class))).thenThrow(NullPointerException.class);
		
		mockedBezero=sut.deleteApustua(apustu);
		//Apustua apu=mockedBezero.baduApustua(apustu);
		
		assertNull(apu);
		fail();
		
		}catch(NullPointerException e) {
			System.out.println("Proba egokia");
			assertTrue(true);
		}catch(Exception e) {
			e.printStackTrace();
			fail();
			
		}
	}
	
	

}
