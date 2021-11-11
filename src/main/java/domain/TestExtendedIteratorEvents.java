package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import businessLogic.BLFacade;
import businessLogic.Factory;

public class TestExtendedIteratorEvents {
	public static void main(String[] args) {
		int isLocal = 0;
		BLFacade blFacade = Factory.createBLFacade(isLocal);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = sdf.parse("17/12/2021"); //17 del mes que viene
			ExtendedIterator<Event> i = blFacade.getEventsIterator(date);
			Event e;
			System.out.println("_____________________");
			System.out.println("ATZETIK AURRERAKA");
			i.goLast();//Azkeneko elementuan kokatu
			while (i.hasPrevious()) {
				e = i.previous();
				System.out.println(e.toString());
			}
			System.out.println();
			System.out.println("_____________________");
			System.out.println("AURRETIK ATZERAKA");
			i.goFirst(); // Lehen elem. kokatu
			while (i.hasNext()) {
				e = i.next();
				System.out.println(e.toString());
			}
		}catch(Exception e) {
			System.out.println("Error at the procedure:" + e.toString());
		}
	}
}
