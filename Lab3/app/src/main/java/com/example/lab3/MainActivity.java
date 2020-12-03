package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MainService mService;
    boolean mBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MainService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        mBound = false;
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            MainService.LocalBinder binder = (MainService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EditText e1 = findViewById(R.id.e1);
        EditText e2 = findViewById(R.id.e2);
        TextView text1 = findViewById(R.id.text1);
        int number1 = Integer.parseInt(e1.getText().toString());
        int number2 = Integer.parseInt(e2.getText().toString());

        switch (item.getItemId()) {
            case R.id.menu_plus:
                text1.setText(mService.plus(number1, number2)+"");
                return true;
            case R.id.menu_minus:
                text1.setText(mService.minus(number1, number2)+"");
                return true;
            case R.id.menu_multiply:
                text1.setText(mService.multiply(number1, number2)+"");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}