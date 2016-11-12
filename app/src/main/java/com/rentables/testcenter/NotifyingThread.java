
//A class for threads to extend. When the thread is finished executing it will call the
//notifyListeners method and then it will call each individual listener's notifyOfThreadCompletion method.

package com.rentables.testcenter;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class NotifyingThread implements Runnable {

    private final Set<ThreadListener> listeners = new CopyOnWriteArraySet<>();

    public void addListener(ThreadListener listenerToAdd){
        listeners.add(listenerToAdd);
    }

    public void removeListener(ThreadListener listenerToRemove){
        listeners.remove(listenerToRemove);
    }

    public void notifyListeners(){

        for(ThreadListener listener: listeners){

            listener.notifyOfThreadCompletion(this);
        }
    }

    @Override
    public void run(){
        try{

            doRun();
        }finally{

            notifyListeners();
        }
    }

    public abstract void doRun();
}
