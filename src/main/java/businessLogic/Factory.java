package businessLogic;

import java.net.URL;
import configuration.ConfigXML;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class Factory {

	public static BLFacade createBLFacade(int zenbakia) {
		if (zenbakia == 0) {
			return new BLFacadeImplementation();
		}if (zenbakia==1) {
			try {
				ConfigXML c = ConfigXML.getInstance();
				String serviceName = "http://" + c.getBusinessLogicNode() + ":" + c.getBusinessLogicPort() + "/ws/"
						+ c.getBusinessLogicName() + "?wsdl";
				URL url = new URL(serviceName);
				// 1st argument refers to wsdl document above
				// 2nd argument is service name, refer to wsdl document above
				QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
				Service service = Service.create(url, qname);
				return service.getPort(BLFacade.class);
			} catch (Exception e) {
				System.out.println("Error at retrieving the business logic: " + e.toString());
			}
		} 
			return null;
	}
}
