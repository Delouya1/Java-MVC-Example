package model;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Observable;
import java.util.Random;

public class Model extends Observable {

    HashMap<String,String> properties;  // map name of the prop to is value name
    Socket fg;
    PrintWriter outToFG;

    public Model(String propertiesFileName) {
        properties = new HashMap<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(propertiesFileName));
            String line;
            while ((line = in.readLine()) != null){
                String[] sp = line.split(",");
                properties.put(sp[0],sp[1]);
            }
            in.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int port = Integer.parseInt(properties.get("port"));

        try {
            fg = new Socket(properties.get("ip"),port);
            outToFG = new PrintWriter(fg.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    ip,127.0.0.1
//    port,5402
//    aileron,set /controls/flight/aileron
//    elevator,set / controls/flight/elevator
//    rudder,set /controls/flight/rudder
//    throttle,set /controls/engines/current-engine/throttle

    public void setAileron(double x){
        String command = properties.get("aileron");
        outToFG.println(command+" "+x);
        outToFG.flush();
    }
    public void setElevators(double x){
        String command = properties.get("elevator");
        outToFG.println(command+" "+x);
        outToFG.flush();
    }
    public void setRudder(double x){
        String command = properties.get("rudder");
        outToFG.println(command+" "+x);
        outToFG.flush();
    }
    public void setThrottle(double x){
        String command = properties.get("throttle");
        outToFG.println(command+" "+x);
        outToFG.flush();
    }

    public void close(){
        try {
            outToFG.close();
            fg.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

