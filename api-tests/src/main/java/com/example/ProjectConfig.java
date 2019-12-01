package com.example;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;


@Sources({ "file:~/.myapp.config",
        "file:/etc/myapp.config",
        "classpath:config.properties" })
public interface ProjectConfig extends Config {

    String apiPath();
}
