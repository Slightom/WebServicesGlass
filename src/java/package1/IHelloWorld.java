package package1;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

//Service Endpoint Interface
@WebService()
//@WebService(name="masloOrzechowe") //zmienil sie portType
//@WebService(portName = "portNameOrzechowy") // nic
//@WebService(serviceName = "serviceNameOrzechowy") 
//@WebService(targetNamespace = "targetNamespaceOrzechowa") // zmienila sie namespace

@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL) //optional

public interface IHelloWorld {

    //@WebMethod
    //@WebMethod(action = "aleAkcja") // zmienia sie input wsam:Action
    //@WebMethod(operationName = "aleOperacja")
    @WebMethod(exclude = false)
    String getHelloWorldAsString (String name);
    
    @WebMethod(exclude = false)
    String getHelloDarknessMyOldFriend (String name) throws InvalidInputException ;

    @WebMethod
    List<Product> getProducts();
}
