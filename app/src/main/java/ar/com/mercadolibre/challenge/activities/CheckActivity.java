package ar.com.mercadolibre.challenge.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import ar.com.mercadolibre.challenge.R;
import ar.com.mercadolibre.challenge.exceptions.UndefinedException;
import ar.com.mercadolibre.challenge.utilities.ActivitiesUtil;
import ar.com.mercadolibre.challenge.utilities.NetworkUtil;

public class CheckActivity extends MCActivity  {

    private Activity me;
    private int countFails = 0;

    /***************************************
     *            GUI METHODS
     ***************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            me = this;
            setContentView(R.layout.activity_check);
            waitToConnection(2000);
        }
        catch(Exception e) {
            undefinedFailedEvent(new UndefinedException(this).getMessage());
        }
    }

    private void waitToConnection(final int miliseconds) {
        try {
            Thread timer = new Thread() {
                public void run() {
                    try {
                        sleep(miliseconds);
                    } catch (InterruptedException e) {
                    } finally {
                        moveToNextActivity.sendEmptyMessage(0);
                    }
                }
            };
            timer.start();
        }
        catch(Exception e) {
            throw e;
        }
    }

    private Handler moveToNextActivity = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(NetworkUtil.isConnected(me)) {
                moveToSearch();
            }
            else {
                if(countFails>=3) finish();
                else {
                    countFails++;
                    withoutConnectionEvent();
                    waitToConnection(5000);
                }
            }
        }
    };


    /***************************************
     *            EVENT METHODS
     ***************************************/
    private void moveToSearch() {
        try {
            ActivitiesUtil.moveTo(this, SearchActivity.class, true);
        }
        catch(Exception ignored) { }
    }
}
