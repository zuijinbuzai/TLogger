package com.zuijinbuzai.lib.tlogger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.zuijinbuzai.lib.TLogger;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random mRandom = new Random();
    private TLogger mTLogger = new TLogger();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTLogger.debug(BuildConfig.DEBUG);
        findViewById(R.id.btn_begin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTask();
            }
        });

        mTLogger = new TLogger();
        mTLogger.begin("doTask");
        mTLogger.addSplit("doTaskA");
        mTLogger.addSplit("doTaskB");
        mTLogger.dumpToLog();
    }

    private void doTask() {
        new Thread() {
            @Override
            public void run() {
                mTLogger.begin("doTask");
                doTaskA();
                doTaskB();
                doTaskVeryLongHaha();
                doTaskC();
                mTLogger.dumpToLog();
            }
        }.start();
    }

    private void doTaskA() {
        sleep();
        mTLogger.addSplit("doTaskA");
    }

    private void doTaskB() {
        sleep();
        mTLogger.addSplit("doTaskB");
    }

    private void doTaskVeryLongHaha() {
        sleep();
        mTLogger.addSplit("doTaskVeryLongHaha");
    }

    private void doTaskC() {
        sleep();
        mTLogger.addSplit("doTaskC");
    }

    private void sleep() {
        try {
            Thread.sleep(mRandom.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}