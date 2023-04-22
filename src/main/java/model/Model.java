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

    public void setAileron(double x){

    }
    public void setElevators(double x){

    }
    public void setRudder(double x){

    }
    public void setThrottle(double x){

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

