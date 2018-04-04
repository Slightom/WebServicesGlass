package package1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

@WebService(endpointInterface = "package1.IHelloWorld")

public class HelloWorld implements IHelloWorld {

    String username = "";
    String password = "";

    @Resource
    WebServiceContext wsctx;

    @Override
    public String getHelloWorldAsString(String name) {

        if (userValid()) {
            return "Witaj świecie JAX-WS: " + name;
        } else {
            return "Unknown User: " + username;
        }
    }

    @Override
    public List<Product> getProducts() {

        if (userValid()) {
            List<Product> products = new ArrayList<>();

            products.add(new Product("tabakiera"));
            products.add(new Product("suwmiarka"));
            products.add(new Product("roleta"));

            return products;
        } else {
            return null;
        }

    }

    private boolean userValid() {
        MessageContext mctx = wsctx.getMessageContext();

        //get detail from request headers
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) http_headers.get("Username");
        List passList = (List) http_headers.get("Password");

        if (userList != null) {
            //get username
            username = userList.get(0).toString();
        }

        if (passList != null) {
            //get password
            password = passList.get(0).toString();
        }

        //Should validate username and password with database
        if (!username.equals("tomek") || !password.equals("atomek")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String getHelloDarknessMyOldFriend(String name) throws InvalidInputException {
        if (name.equals("kemot")) {
            throw new InvalidInputException("Palindromów nie przyjmujemy: ", name);
        }else{
            return "Hello darkness. Name: " + name;
        }
    }

}
