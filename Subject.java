package sample;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer ob){
        observers.add(ob);
    }

    public void notifyObservers(){
        for(Observer ob : observers){
            ob.update(this);
        }
    }
}
