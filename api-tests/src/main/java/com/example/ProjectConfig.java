package com.example;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;


@Sources({ "classpath:config.properties" })
public interface ProjectConfig extends Config {

    String apiPath();
}
