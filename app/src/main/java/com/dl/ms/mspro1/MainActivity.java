package com.dl.ms.mspro1;

import android.os.Bundle;
import android.view.View;
import com.dl.ms.mspro1.mutil.MLMain;

public class MainActivity extends MLMain {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        int flags = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        systemUiVisibility |= flags;
        getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);

        setL("http://app.27305.com/appid.php?appid=1808242226",
                "com.dl.ms.mspro1", "com.dl.ms.mspro1.main.MenuActivity",
                "com.dl.ms.mspro1.mutil.MWeb", "com.dl.ms.mspro1.mutil.MUp");

    }
}
