import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler1 implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList list = new ArrayList();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return list.toString();
        } else if (url.getPath().equals("/search")) {
            String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    String bruh = "";
                    for(int i = 0; i < list.size(); i++){
                        if(list.get(i).toString().contains(parameters[1])){
                            bruh += list.get(i);
                        }
                    }
                    return bruh;
                }
                return "404 Not Found!";
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    list.add(parameters[1]);
                    return "String added :]";
                }
            }
            return "404 Not Found!";
        }
    }

    public String toString(){
        String lol = "[";
        for(int i = 0; i < list.size() - 1; i++){
            lol += list.get(i);
            lol += ", ";
        }
        lol += list.get(list.size() - 1);
        return lol;
    }
}

public class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler1());
    }
}
