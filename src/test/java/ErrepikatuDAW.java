import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Apustua;
import domain.Bezeroa;
import domain.DatuErrefaktorizatuERREPIKAPEN;
import domain.Errepikapena;
import domain.Event;
import domain.Pertsona;
import domain.Pronostikoa;
import domain.Question;
import test.dataAccess.TestDataAccess;

public class ErrepikatuDAW {

	// sut:system under test
	static DataAccess sut = new DataAccess(true);

	// additional operations needed to execute the test
	static TestDataAccess testDA = new TestDataAccess();

	private Bezeroa bez;
	private Bezeroa bez2;
	private Errepikapena b;

	@Test
	public void test() {
		try {
			
		
		testDA.open();
		bez = testDA.addBezeroa("Jon", "Smith", "Clark", "SmithClark", "ClarkSmith", "123456789", "jonsc@gmail.com",
				new Date(2001, 8, 1));
		bez2 = testDA.addBezeroa("Josh", "Smith", "Jones", "Smith", "JonesSmith", "978123465", "vdsmarc@gmail.com",
				new Date(1999, 8, 1));
		testDA.close();
		
		DatuErrefaktorizatuERREPIKAPEN a= new DatuErrefaktorizatuERREPIKAPEN(1.5,15,0.1);
		sut.errepikatu(bez,bez2,a);
		
		testDA.open();
		b= testDA.getErrepikapena(bez,bez2);
		System.out.println(b.getNork().getErabiltzaileIzena());
		testDA.close();
		assertTrue(b.getNork().getErabiltzaileIzena().compareTo(bez.getErabiltzaileIzena())==0);
		assertTrue(b.getNori().getErabiltzaileIzena().compareTo(bez2.getErabiltzaileIzena())==0);
		}catch(NullPointerException e) {
			e.printStackTrace();
			fail();
		}finally {
			testDA.open();
			boolean a=testDA.removeErrepikapena(b);
			boolean h=testDA.removeBezeroa(bez);
			boolean c=testDA.removeBezeroa(bez2);
			testDA.close();
			System.out.println(c + "" + h + " " + a);
		}
		
	}
}
