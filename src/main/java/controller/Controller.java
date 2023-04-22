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
        if (o == this.m){
            int res = m.getRes();
            wc.display(res);
        }else if (o == wc){
            m.calculate();
        }
    }
}
