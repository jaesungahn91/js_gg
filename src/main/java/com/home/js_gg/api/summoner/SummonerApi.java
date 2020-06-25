package com.home.js_gg.api.summoner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:js.properties")
@RequestMapping(value = "summoner")
public class SummonerApi {

    @Value("${api.basic.url}")
    private String basicUrl;

    @RequestMapping(value = "byName")
    public String getSummonersInfoByName(){
        return basicUrl;
    }

}
