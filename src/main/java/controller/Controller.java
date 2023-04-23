package controller;

import model.Model;
import view.WindowController;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {
    Model m;
    WindowController wc;

    public Controller(Model m, WindowController wc) {
        this.m = m;
        m.addObserver(this);
        this.wc = wc;
        wc.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
            m.setAileron(wc.getAileron());
            m.setElevators(wc.getElevator());
    }
}
