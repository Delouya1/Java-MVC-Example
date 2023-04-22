package model;

import java.util.Observable;
import java.util.Random;

public class Model extends Observable {
    int res;
    Random r = new Random();

    public void calculate() {
        res = r.nextInt(101);
        setChanged();
        notifyObservers();
    }

    public int getRes() {
        return res;
    }
}

