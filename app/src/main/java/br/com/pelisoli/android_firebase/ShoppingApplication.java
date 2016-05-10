package br.com.pelisoli.android_firebase;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by pelisoli on 27/04/16.
 */
public class ShoppingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
