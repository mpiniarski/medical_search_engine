package com.joma.studies;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) {
        if(args.length<0){
            return;
        }
        Injector injector = Guice.createInjector(new AppModule(args[0]));
        App app = injector.getInstance(App.class);
        app.run(args);
    }
}
