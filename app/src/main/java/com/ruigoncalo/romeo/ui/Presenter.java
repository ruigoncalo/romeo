package com.ruigoncalo.romeo.ui;

/**
 * Created by ruigoncalo on 28/02/16.
 */
public abstract class Presenter<P> {

    private P presented;

    public void onStart(P presented){
        this.presented = presented;
    }

    public void onStop(){
        this.presented = null;
    }

    public P getPresented() {
        return presented;
    }
}
