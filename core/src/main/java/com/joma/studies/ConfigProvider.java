package com.joma.studies;

import com.beust.jcommander.Parameter;

public class ConfigProvider {

    @Parameter(names = {"-x", "--inputXmlPath"}, required = true, description = "Path to .xml file which will be indexed")
    private String inputXmlPath;

    @Parameter(names = {"-i", "--indexDir"}, required = true, description = "Path to directory with indexes")
    private String indexDirectory;

    @Parameter(names = {"-h", "--help"}, help = true, description = "Show help message")
    private Boolean help = false;

    public String getIndexDirectory() {
        return indexDirectory;
    }

    public String getInputXmlPath() {
        return inputXmlPath;
    }

    public Boolean isHelp() {
        return help;
    }
}
