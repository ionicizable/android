package com.example.lab3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class MainService extends Service {
    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        MainService getService() {
            return MainService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public int plus(int a, int b){
        return a+b;
    }

    public int minus(int a, int b){
        return a-b;
    }

    public int multiply(int a, int b){
        return a*b;
    }

}
