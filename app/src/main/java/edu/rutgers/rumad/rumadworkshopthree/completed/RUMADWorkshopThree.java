package edu.rutgers.rumad.rumadworkshopthree.completed;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by shreyashirday on 4/22/15.
 */
public class RUMADWorkshopThree extends Application {

    //important keys to register app with parse
    private final String PARSE_APP_ID = "g8ZH6GL6C65dOQOEog4jXSxpcJPoiRHRmHEGK72C";
    private final String PARSE_CLIENT_KEY = "gP7EtH32dUYGWem8SlGnSmOWW43B4WQtkiaqbwMO";

    @Override
    public void onCreate(){
        super.onCreate();

        //initialize parse service
        Parse.initialize(this,PARSE_APP_ID,PARSE_CLIENT_KEY);


    }
}
