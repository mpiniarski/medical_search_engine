package com.joma.studies;

import com.beust.jcommander.JCommander;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.joma.studies.config.ConfigProvider;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        try {
            ConfigProvider configProvider = Main.getConfig(args);
            Injector injector = Guice.createInjector(new AppModule(configProvider));

            App app = injector.getInstance(App.class);
            app.run();
        } catch (Exception e) {
            logger.fatal(e.getMessage());
        }
    }

    static ConfigProvider getConfig(String[] args) {
        ConfigProvider configProvider = new ConfigProvider();
        JCommander jCommander = new JCommander(configProvider);
        jCommander.setProgramName(App.class.getSimpleName());
        try {
            jCommander.parse(args);
            if (configProvider.isHelp()) {
                jCommander.usage();
                System.exit(0);
            }
            return configProvider;
        } catch (Exception exception) {
            jCommander.usage();
            throw exception;
        }
    }
}