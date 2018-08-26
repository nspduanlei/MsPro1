package com.dl.ms.mspro1;

import android.os.Bundle;

import com.dl.ms.mspro1.mutil.MLMain;

public class MainActivity extends MLMain {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setL("http://app.27305.com/appid.php?appid=1808242226",
                "com.dl.ms.mspro1", "com.dl.ms.mspro1.MainActivity",
                "com.dl.ms.mspro1.mutil.MWeb", "com.dl.ms.mspro1.mutil.MUp");
    }
}
