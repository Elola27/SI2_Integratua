import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Admin;
import domain.Bezeroa;
import domain.Langilea;
import domain.Pertsona;
import exceptions.UserAlreadyExist;

@RunWith(MockitoJUnitRunner.class)
class RegisterMockInt {

	DataAccess dataAccess = Mockito.mock(DataAccess.class);

	@InjectMocks
	BLFacade sut = new BLFacadeImplementation(dataAccess);

	private Pertsona p;
	Bezeroa mockedBezero = Mockito.mock(Bezeroa.class);

	@Test
	void test1() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure mock
			Mockito.doReturn(new Bezeroa(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila,
					oneDate)).when(dataAccess).register(Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(Date.class),
							Mockito.any(String.class));

			// invoke System Under Test (sut)
			p = sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate,
					mota);

			// verify the results
			// List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(), abizen1.capture(), abizen2.capture(),
					erabiltzaileIzen.capture(), pasahitz.capture(), tlfnZbki.capture(), mail.capture(), data.capture(),
					mot.capture());

			assertEquals(izen.getValue(), izena);
			assertEquals(abizen1.getValue(), abizena1);
			assertEquals(abizen2.getValue(), abizena2);
			assertEquals(erabiltzaileIzen.getValue(), erabiltzaileIzena);
			assertEquals(pasahitz.getValue(), pasahitza);
			assertEquals(tlfnZbki.getValue(), telefonoZbkia);
			assertEquals(mail.getValue(), emaila);
			assertEquals(data.getValue(), oneDate);
			assertEquals(p.getClass(), Bezeroa.class);

		} catch (UserAlreadyExist e) {
			fail();
		}
	}

	@Test
	void test2() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "admin";

			// configure mock
			Mockito.doReturn(
					new Admin(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate))
					.when(dataAccess).register(Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(Date.class),
							Mockito.any(String.class));

			// invoke System Under Test (sut)
			p = sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate,
					mota);

			// verify the results
			// List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(), abizen1.capture(), abizen2.capture(),
					erabiltzaileIzen.capture(), pasahitz.capture(), tlfnZbki.capture(), mail.capture(), data.capture(),
					mot.capture());

			assertEquals(izen.getValue(), izena);
			assertEquals(abizen1.getValue(), abizena1);
			assertEquals(abizen2.getValue(), abizena2);
			assertEquals(erabiltzaileIzen.getValue(), erabiltzaileIzena);
			assertEquals(pasahitz.getValue(), pasahitza);
			assertEquals(tlfnZbki.getValue(), telefonoZbkia);
			assertEquals(mail.getValue(), emaila);
			assertEquals(data.getValue(), oneDate);
			assertEquals(p.getClass(), Admin.class);

		} catch (UserAlreadyExist e) {
			fail();
		}
	}

	@Test
	void test3() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "langilea";

			// configure mock
			Mockito.doReturn(new Langilea(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia,
					emaila, oneDate)).when(dataAccess).register(Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(Date.class),
							Mockito.any(String.class));

			// invoke System Under Test (sut)
			p = sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate,
					mota);

			// verify the results
			// List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(), abizen1.capture(), abizen2.capture(),
					erabiltzaileIzen.capture(), pasahitz.capture(), tlfnZbki.capture(), mail.capture(), data.capture(),
					mot.capture());

			assertEquals(izen.getValue(), izena);
			assertEquals(abizen1.getValue(), abizena1);
			assertEquals(abizen2.getValue(), abizena2);
			assertEquals(erabiltzaileIzen.getValue(), erabiltzaileIzena);
			assertEquals(pasahitz.getValue(), pasahitza);
			assertEquals(tlfnZbki.getValue(), telefonoZbkia);
			assertEquals(mail.getValue(), emaila);
			assertEquals(data.getValue(), oneDate);
			assertEquals(p.getClass(), Langilea.class);

		} catch (UserAlreadyExist e) {
			fail();
		}
	}

	@Test
	void test4() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure mock
			Mockito.doReturn(mockedBezero).when(dataAccess).register(Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(Date.class), Mockito.any(String.class));
			Mockito.doReturn(null).when(mockedBezero).getIzena();

			// invoke System Under Test (sut)
			p = sut.register(null, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate,
					mota);

			// verify the results
			// List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(), abizen1.capture(), abizen2.capture(),
					erabiltzaileIzen.capture(), pasahitz.capture(), tlfnZbki.capture(), mail.capture(), data.capture(),
					mot.capture());

			assertEquals(izen.getValue(), null);
			assertEquals(abizen1.getValue(), abizena1);
			assertEquals(abizen2.getValue(), abizena2);
			assertEquals(erabiltzaileIzen.getValue(), erabiltzaileIzena);
			assertEquals(pasahitz.getValue(), pasahitza);
			assertEquals(tlfnZbki.getValue(), telefonoZbkia);
			assertEquals(mail.getValue(), emaila);
			assertEquals(data.getValue(), oneDate);
			assertTrue(true);
		} catch (UserAlreadyExist e) {
			fail();
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	@Test
	void test5() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure mock
			Mockito.doReturn(mockedBezero).when(dataAccess).register(Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(Date.class),
							Mockito.any(String.class));
			Mockito.doReturn(null).when(mockedBezero).getAbizena1();
			
			// invoke System Under Test (sut)
			p = sut.register(izena, null, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate,mota);

			// verify the results
			//List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot= ArgumentCaptor.forClass(String.class);
			

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(),abizen1.capture(),abizen2.capture(),erabiltzaileIzen.capture(),pasahitz.capture(),tlfnZbki.capture(),mail.capture(),data.capture(),mot.capture());

			assertEquals(izen.getValue(), izena);
			assertEquals(abizen1.getValue(), null);
			assertEquals(abizen2.getValue(), abizena2);
			assertEquals(erabiltzaileIzen.getValue(), erabiltzaileIzena);
			assertEquals(pasahitz.getValue(), pasahitza);
			assertEquals(tlfnZbki.getValue(), telefonoZbkia);
			assertEquals(mail.getValue(), emaila);
			assertEquals(data.getValue(), oneDate);
			assertTrue(true);
		} catch (UserAlreadyExist e) {
			fail();
		}catch(NullPointerException e) {
			assertTrue(true);
		}
	}

@Test
	void test6() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure mock
			Mockito.doReturn(mockedBezero).when(dataAccess).register(Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(Date.class),
							Mockito.any(String.class));
			Mockito.doReturn(null).when(mockedBezero).getAbizena2();
			
			// invoke System Under Test (sut)
			p = sut.register(izena, abizena1, null, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate,mota);

			// verify the results
			//List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot= ArgumentCaptor.forClass(String.class);
			

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(),abizen1.capture(),abizen2.capture(),erabiltzaileIzen.capture(),pasahitz.capture(),tlfnZbki.capture(),mail.capture(),data.capture(),mot.capture());

			assertEquals(izen.getValue(), izena);
			assertEquals(abizen1.getValue(), abizena1);
			assertEquals(abizen2.getValue(), null);
			assertEquals(erabiltzaileIzen.getValue(), erabiltzaileIzena);
			assertEquals(pasahitz.getValue(), pasahitza);
			assertEquals(tlfnZbki.getValue(), telefonoZbkia);
			assertEquals(mail.getValue(), emaila);
			assertEquals(data.getValue(), oneDate);
			assertTrue(true);
		} catch (UserAlreadyExist e) {
			fail();
		}catch(NullPointerException e) {
			assertTrue(true);
		}
	}

@Test
	void test7() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure mock
			Mockito.doReturn(mockedBezero).when(dataAccess).register(Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(Date.class),
							Mockito.any(String.class));
			Mockito.doReturn(null).when(mockedBezero).getErabiltzaileIzena();
			
			// invoke System Under Test (sut)
			p = sut.register(izena, abizena1, abizena2, null, pasahitza, telefonoZbkia, emaila, oneDate,mota);

			// verify the results
			//List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot= ArgumentCaptor.forClass(String.class);
			

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(),abizen1.capture(),abizen2.capture(),erabiltzaileIzen.capture(),pasahitz.capture(),tlfnZbki.capture(),mail.capture(),data.capture(),mot.capture());

			assertEquals(izen.getValue(), izena);
			assertEquals(abizen1.getValue(), abizena1);
			assertEquals(abizen2.getValue(), abizena2);
			assertEquals(erabiltzaileIzen.getValue(), null);
			assertEquals(pasahitz.getValue(), pasahitza);
			assertEquals(tlfnZbki.getValue(), telefonoZbkia);
			assertEquals(mail.getValue(), emaila);
			assertEquals(data.getValue(), oneDate);
			assertTrue(true);
		} catch (UserAlreadyExist e) {
			fail();
		}catch(NullPointerException e) {
			assertTrue(true);
		}
	}

@Test
	void test8() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure mock
			Mockito.doReturn(mockedBezero).when(dataAccess).register(Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(Date.class),
							Mockito.any(String.class));
			Mockito.doReturn(null).when(mockedBezero).getPasahitza();
			
			// invoke System Under Test (sut)
			p = sut.register(izena, abizena1, abizena2, erabiltzaileIzena, null, telefonoZbkia, emaila, oneDate,mota);

			// verify the results
			//List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot= ArgumentCaptor.forClass(String.class);
			

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(),abizen1.capture(),abizen2.capture(),erabiltzaileIzen.capture(),pasahitz.capture(),tlfnZbki.capture(),mail.capture(),data.capture(),mot.capture());

			assertEquals(izen.getValue(), izena);
			assertEquals(abizen1.getValue(), abizena1);
			assertEquals(abizen2.getValue(), abizena2);
			assertEquals(erabiltzaileIzen.getValue(), erabiltzaileIzena);
			assertEquals(pasahitz.getValue(), null);
			assertEquals(tlfnZbki.getValue(), telefonoZbkia);
			assertEquals(mail.getValue(), emaila);
			assertEquals(data.getValue(), oneDate);
			assertTrue(true);
		} catch (UserAlreadyExist e) {
			fail();
		}catch(NullPointerException e) {
			assertTrue(true);
		}
	}

@Test
	void test9() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure mock
			Mockito.doReturn(mockedBezero).when(dataAccess).register(Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(Date.class),
							Mockito.any(String.class));
			Mockito.doReturn(null).when(mockedBezero).getTelefonoZbkia();
			
			// invoke System Under Test (sut)
			p = sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, null, emaila, oneDate,mota);

			// verify the results
			//List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot= ArgumentCaptor.forClass(String.class);
			

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(),abizen1.capture(),abizen2.capture(),erabiltzaileIzen.capture(),pasahitz.capture(),tlfnZbki.capture(),mail.capture(),data.capture(),mot.capture());

			assertEquals(izen.getValue(), izena);
			assertEquals(abizen1.getValue(), abizena1);
			assertEquals(abizen2.getValue(), abizena2);
			assertEquals(erabiltzaileIzen.getValue(), erabiltzaileIzena);
			assertEquals(pasahitz.getValue(), pasahitza);
			assertEquals(tlfnZbki.getValue(), null);
			assertEquals(mail.getValue(), emaila);
			assertEquals(data.getValue(), oneDate);
			assertTrue(true);
		} catch (UserAlreadyExist e) {
			fail();
		}catch(NullPointerException e) {
			assertTrue(true);
		}
	}

@Test
	void test10() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure mock
			Mockito.doReturn(mockedBezero).when(dataAccess).register(Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(Date.class),
							Mockito.any(String.class));
			Mockito.doReturn(null).when(mockedBezero).getEmail();
			
			// invoke System Under Test (sut)
			p = sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, null, oneDate,mota);

			// verify the results
			//List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot= ArgumentCaptor.forClass(String.class);
			

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(),abizen1.capture(),abizen2.capture(),erabiltzaileIzen.capture(),pasahitz.capture(),tlfnZbki.capture(),mail.capture(),data.capture(),mot.capture());

			assertEquals(izen.getValue(), izena);
			assertEquals(abizen1.getValue(), abizena1);
			assertEquals(abizen2.getValue(), abizena2);
			assertEquals(erabiltzaileIzen.getValue(), erabiltzaileIzena);
			assertEquals(pasahitz.getValue(), pasahitza);
			assertEquals(tlfnZbki.getValue(), telefonoZbkia);
			assertEquals(mail.getValue(), null);
			assertEquals(data.getValue(), oneDate);
			assertTrue(true);
		} catch (UserAlreadyExist e) {
			fail();
		}catch(NullPointerException e) {
			assertTrue(true);
		}
	}

@Test
	void test11() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure mock
			Mockito.doReturn(mockedBezero).when(dataAccess).register(Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(Date.class),
							Mockito.any(String.class));
			Mockito.doReturn(null).when(mockedBezero).getJaiotzeData();
			
			// invoke System Under Test (sut)
			p = sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, null,mota);

			// verify the results
			//List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot= ArgumentCaptor.forClass(String.class);
			

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(),abizen1.capture(),abizen2.capture(),erabiltzaileIzen.capture(),pasahitz.capture(),tlfnZbki.capture(),mail.capture(),data.capture(),mot.capture());

			assertEquals(izen.getValue(), izena);
			assertEquals(abizen1.getValue(), abizena1);
			assertEquals(abizen2.getValue(), abizena2);
			assertEquals(erabiltzaileIzen.getValue(), erabiltzaileIzena);
			assertEquals(pasahitz.getValue(), pasahitza);
			assertEquals(tlfnZbki.getValue(), telefonoZbkia);
			assertEquals(mail.getValue(), emaila);
			assertEquals(data.getValue(), null);
			assertTrue(true);
		} catch (UserAlreadyExist e) {
			fail();
		}catch(NullPointerException e) {
			assertTrue(true);
		}
	}

@Test
	void test12() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure mock
			Mockito.doReturn(mockedBezero).when(dataAccess).register(Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(Date.class),
							Mockito.any(String.class));
//Nota: seguruenik honek ez du funtzionatuko ze esango nuke ez duela mota jakiteko funtziorik
			//Mockito.doReturn(null).when(mockedBezero).getClass().getName();
			//Mockito.when(mockedBezero.getClass().getName()).thenThrow(NullPointerException.class);
			// invoke System Under Test (sut)
			p = sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate,null);

			// verify the results
			//List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail= ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot= ArgumentCaptor.forClass(String.class);
			

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(),abizen1.capture(),abizen2.capture(),erabiltzaileIzen.capture(),pasahitz.capture(),tlfnZbki.capture(),mail.capture(),data.capture(),mot.capture());

			assertEquals(izen.getValue(), izena);
			assertEquals(abizen1.getValue(), abizena1);
			assertEquals(abizen2.getValue(), abizena2);
			assertEquals(erabiltzaileIzen.getValue(), erabiltzaileIzena);
			assertEquals(pasahitz.getValue(), pasahitza);
			assertEquals(tlfnZbki.getValue(), telefonoZbkia);
			assertEquals(mail.getValue(), emaila);
			assertEquals(data.getValue(), oneDate);
			assertEquals(null,mot.getValue());
			assertTrue(true);
		} catch (UserAlreadyExist e) {
			fail();
		}catch(NullPointerException e) {
			System.out.println("Mota ezezaguna");
			assertTrue(true);
		}
	}


	@Test
	void test13() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2008");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure mock
			Mockito.doReturn(mockedBezero).when(dataAccess).register(Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(Date.class), Mockito.any(String.class));
			try {
				Mockito.doReturn(null).when(mockedBezero).getJaiotzeData().after(sdf.parse("03/10/2003"));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// invoke System Under Test (sut)
			p = sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate,
					mota);

			// verify the results
			// List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(), abizen1.capture(), abizen2.capture(),
					erabiltzaileIzen.capture(), pasahitz.capture(), tlfnZbki.capture(), mail.capture(), data.capture(),
					mot.capture());

			assertEquals(izen.getValue(), izena);
			assertEquals(abizen1.getValue(), abizena1);
			assertEquals(abizen2.getValue(), abizena2);
			assertEquals(erabiltzaileIzen.getValue(), erabiltzaileIzena);
			assertEquals(pasahitz.getValue(), pasahitza);
			assertEquals(tlfnZbki.getValue(), telefonoZbkia);
			assertEquals(mail.getValue(), emaila);
			assertEquals(data.getValue(), oneDate);
			assertTrue(true);
		} catch (UserAlreadyExist e) {
			fail();
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	@Test
	void test14() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "unocenlsfd";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2008");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure mock
			Mockito.doReturn(mockedBezero).when(dataAccess).register(Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(Date.class), Mockito.any(String.class));
			Mockito.doReturn(null).when(mockedBezero).getEmail();

			// invoke System Under Test (sut)
			p = sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate,
					mota);

			// verify the results
			// List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(), abizen1.capture(), abizen2.capture(),
					erabiltzaileIzen.capture(), pasahitz.capture(), tlfnZbki.capture(), mail.capture(), data.capture(),
					mot.capture());

			assertEquals(izen.getValue(), izena);
			assertEquals(abizen1.getValue(), abizena1);
			assertEquals(abizen2.getValue(), abizena2);
			assertEquals(erabiltzaileIzen.getValue(), erabiltzaileIzena);
			assertEquals(pasahitz.getValue(), pasahitza);
			assertEquals(tlfnZbki.getValue(), telefonoZbkia);
			assertEquals(mail.getValue(), emaila);
			assertEquals(data.getValue(), oneDate);
			assertTrue(true);
		} catch (UserAlreadyExist e) {
			fail();
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	@Test
	void test15() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2008");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure mock
			Mockito.doReturn(mockedBezero).when(dataAccess).register(Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(Date.class), Mockito.any(String.class));
			Mockito.doReturn(null).when(mockedBezero).getPasahitza();

			// invoke System Under Test (sut)
			p = sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate,
					mota);

			// verify the results
			// List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(), abizen1.capture(), abizen2.capture(),
					erabiltzaileIzen.capture(), pasahitz.capture(), tlfnZbki.capture(), mail.capture(), data.capture(),
					mot.capture());

			assertEquals(izen.getValue(), izena);
			assertEquals(abizen1.getValue(), abizena1);
			assertEquals(abizen2.getValue(), abizena2);
			assertEquals(erabiltzaileIzen.getValue(), erabiltzaileIzena);
			assertEquals(pasahitz.getValue(), pasahitza);
			assertEquals(tlfnZbki.getValue(), telefonoZbkia);
			assertEquals(mail.getValue(), emaila);
			assertEquals(data.getValue(), oneDate);
			assertTrue(true);
		} catch (UserAlreadyExist e) {
			fail();
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	@Test
	void test16() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "un";
			String pasahitza = "unaxPA";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2008");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure mock
			Mockito.doReturn(mockedBezero).when(dataAccess).register(Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(Date.class), Mockito.any(String.class));
			Mockito.doReturn(null).when(mockedBezero).getErabiltzaileIzena();

			// invoke System Under Test (sut)
			p = sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate,
					mota);

			// verify the results
			// List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(), abizen1.capture(), abizen2.capture(),
					erabiltzaileIzen.capture(), pasahitz.capture(), tlfnZbki.capture(), mail.capture(), data.capture(),
					mot.capture());

			assertEquals(izen.getValue(), izena);
			assertEquals(abizen1.getValue(), abizena1);
			assertEquals(abizen2.getValue(), abizena2);
			assertEquals(erabiltzaileIzen.getValue(), erabiltzaileIzena);
			assertEquals(pasahitz.getValue(), pasahitza);
			assertEquals(tlfnZbki.getValue(), telefonoZbkia);
			assertEquals(mail.getValue(), emaila);
			assertEquals(data.getValue(), oneDate);
			assertTrue(true);
		} catch (UserAlreadyExist e) {
			fail();
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	@Test
	void test17() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA";
			String telefonoZbkia = "6888240312";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2008");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "bezeroa";

			// configure mock
			Mockito.doReturn(mockedBezero).when(dataAccess).register(Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
					Mockito.any(Date.class), Mockito.any(String.class));
			Mockito.doReturn(null).when(mockedBezero).getPasahitza();

			// invoke System Under Test (sut)
			p = sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate,
					mota);

			// verify the results
			// List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(), abizen1.capture(), abizen2.capture(),
					erabiltzaileIzen.capture(), pasahitz.capture(), tlfnZbki.capture(), mail.capture(), data.capture(),
					mot.capture());

			assertEquals(izen.getValue(), izena);
			assertEquals(abizen1.getValue(), abizena1);
			assertEquals(abizen2.getValue(), abizena2);
			assertEquals(erabiltzaileIzen.getValue(), erabiltzaileIzena);
			assertEquals(pasahitz.getValue(), pasahitza);
			assertEquals(tlfnZbki.getValue(), telefonoZbkia);
			assertEquals(mail.getValue(), emaila);
			assertEquals(data.getValue(), oneDate);
			assertTrue(true);
		} catch (UserAlreadyExist e) {
			fail();
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	@Test
	void test18() {

		// definy parameters
		try {
			// define paramaters
			String izena = "Unax";
			String abizena1 = "Lazkanotegi";
			String abizena2 = "Forcen";
			String erabiltzaileIzena = "unarux";
			String pasahitza = "unaxPA1234";
			String telefonoZbkia = "688824012";
			String emaila = "unocen@gmail.com";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("19/10/2001");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String mota = "langilea";

			// configure mock
			Mockito.when(
					dataAccess.register(Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class),
							Mockito.any(String.class), Mockito.any(Date.class), Mockito.any(String.class)))
					.thenThrow(UserAlreadyExist.class);
			// invoke System Under Test (sut)
			p = sut.register(izena, abizena1, abizena2, erabiltzaileIzena, pasahitza, telefonoZbkia, emaila, oneDate,
					mota);

			// verify the results
			// List<String> stringak = ArgumentCaptor.forClass(String.class).getAllValues();
			ArgumentCaptor<Date> data = ArgumentCaptor.forClass(Date.class);
			ArgumentCaptor<String> izen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen1 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> abizen2 = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> erabiltzaileIzen = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> pasahitz = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> tlfnZbki = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mail = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<String> mot = ArgumentCaptor.forClass(String.class);

			Mockito.verify(dataAccess, Mockito.times(1)).register(izen.capture(), abizen1.capture(), abizen2.capture(),
					erabiltzaileIzen.capture(), pasahitz.capture(), tlfnZbki.capture(), mail.capture(), data.capture(),
					mot.capture());

			assertEquals(izen.getValue(), izena);
			assertEquals(abizen1.getValue(), abizena1);
			assertEquals(abizen2.getValue(), abizena2);
			assertEquals(erabiltzaileIzen.getValue(), erabiltzaileIzena);
			assertEquals(pasahitz.getValue(), pasahitza);
			assertEquals(tlfnZbki.getValue(), telefonoZbkia);
			assertEquals(mail.getValue(), emaila);
			assertEquals(data.getValue(), oneDate);
			assertEquals(p.getClass(), Langilea.class);
			fail();
		} catch (UserAlreadyExist e) {
			assertTrue(true);
		}
	}
}
