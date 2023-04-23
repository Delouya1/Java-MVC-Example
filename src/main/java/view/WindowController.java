package view;


import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.Observable;

public class WindowController extends Observable {

    @FXML
    public Canvas joystick;
    boolean mousePressed;
    double jx,jy;
    double mx,my;
    double aileron,elevator;

    public double getAileron() {
        return aileron;
    }

    public double getElevator() {
        return elevator;
    }

    public WindowController() {
        this.mousePressed = false;
        jx = 0;
        jy = 0;
        mx = joystick.getWidth()/2;
        my = joystick.getHeight()/2;
        aileron = 0;
        elevator = 0;
    }

    public void initialize() {
        joystick = new Canvas(100, 100);
    }
    void paint(){
        GraphicsContext gc = joystick.getGraphicsContext2D();
        gc.clearRect(0,0,joystick.getWidth(),joystick.getHeight());
        gc.strokeOval(jx-50,jx-50,100,100);
        aileron = (jx-mx)/mx; //normalize
        elevator = (jy-my)/my;
        setChanged();
        notifyObservers();
        System.out.println(aileron+","+elevator);
    }

    public void mouseDown(MouseEvent me){
        if (!mousePressed){
            mousePressed = true;
            System.out.println("dbg:mouse is down");

        }
    }
    public void mouseUp(MouseEvent me){
        if (mousePressed){
            mousePressed = false;
            System.out.println("dbg:mouse is up");
            jx = mx;
            jy = my;
            paint();
        }
    }
    public void mouseMove(MouseEvent me){
        if (mousePressed){
            mousePressed = false;
            jx = me.getX();
            jy = me.getY();
            paint();
        }
    }
}
