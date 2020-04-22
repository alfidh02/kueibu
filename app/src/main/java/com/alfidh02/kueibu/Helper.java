package com.alfidh02.kueibu;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class Helper {

    public static void statusBarTranslucent(Activity activity){
        Window window = activity.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

}
