package org.nhandy;

import org.nhandy.gameobjects.Observer;

public interface Observable {

    void setChanged();

    void notifyObservers();

    void attachObserver(Observer obv);

    void detachObserver(Observer obv);

}
