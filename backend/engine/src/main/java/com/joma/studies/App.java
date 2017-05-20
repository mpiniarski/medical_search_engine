package com.joma.studies;

import com.google.inject.Guice;

public class App {
    public static void main(String[] args) {
        Guice.createInjector(new AppModule());
    }
}
