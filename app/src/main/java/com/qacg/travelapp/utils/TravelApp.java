package com.qacg.travelapp.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TravelApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //Realm initialization.
        Realm.init(getApplicationContext());
        //Delete Realm db if migration is needed.
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public static Context getContext() {
        return context;
    }


    /**
     * <p>Retorna verdadero si existe una conexion a internet.</p>
     *
     * @return Boolean - Estado de la conexion del dispositivo.
     */
    public static boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if( cm != null && cm.getActiveNetworkInfo() != null )
            return cm.getActiveNetworkInfo().isConnected();
        return false;
    }

}