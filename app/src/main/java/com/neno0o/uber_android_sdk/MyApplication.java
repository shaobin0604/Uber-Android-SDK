package com.neno0o.uber_android_sdk;

import com.neno0o.ubersdk.Uber;

public class MyApplication extends android.app.Application {

    public static final String CLIENT_ID = "CZ44gdCJHGMJAyUVIlNrOq5ONBXUpo8a";
    public static final String CLIENT_SECRET = "uUKd6_zmr7Jb4T0GKlpe2ifHfiG7JsQtFAjGYiXv";
    public static final String SERVER_TOKEN = "_iijlufEMJwX4Yb_MMEHPaPIernaxoddKpZ-yRGV";
    public static final String REDIRECT_URL = "REDIRECT_URL";

    @Override
    public void onCreate() {
        super.onCreate();
        Uber.getInstance().init(CLIENT_ID,
                CLIENT_SECRET,
                SERVER_TOKEN,
                REDIRECT_URL);
    }
}
