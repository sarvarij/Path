package com.example.path.view;

import android.os.Handler;

public class AutocompleteThrottle implements Runnable{

    private static final long DELAY = 2000;

    private final Handler handler = new Handler();
    private Runnable task = null;

    private long lastRequestMillis = System.currentTimeMillis();

    private boolean isReady(){
        return System.currentTimeMillis() - lastRequestMillis >= DELAY;
    }

    private void reset(){
        lastRequestMillis = System.currentTimeMillis();
    }

    public void schedule(Runnable runnable){
        if (isReady()){
            reset();
            runnable.run();
        }else{
            synchronized (handler) {
                if (task == null) {
                    task = runnable;
                    handler.postDelayed(this, lastRequestMillis + DELAY - System.currentTimeMillis());
                } else {
                    task = runnable;
                }
            }
        }
    }


    @Override
    public void run() {
        synchronized (handler){
            reset();
            task.run();
            task = null;
        }
    }
}
