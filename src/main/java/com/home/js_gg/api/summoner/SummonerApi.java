package com.home.js_gg.api.summoner;

import com.google.common.collect.Maps;
import com.home.js_gg.api.HttpConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * summoner api
 */
@RestController
@PropertySource("classpath:js.properties")
@RequestMapping(value = "summoner")
public class SummonerApi {

    @Value("${api.basic.url}")
    private String basicUrl;

    @Value("${api.key}")
    private String key;

    /**
     * The Http connection.
     */
    @Autowired
    HttpConnection httpConnection;

    /**
     * Gets summoners info by name.
     *
     * @param userName the user name
     * @return the summoners info by name
     */
    @RequestMapping(value = "byName/{userName}", method = RequestMethod.GET)
    public String getSummonersInfoByName(
            @PathVariable(value = "userName", required = true) String userName
    ) throws IOException {
//        String url = basicUrl + "summoner/v4/summoners/by-name/" + userName + "?api_key=" + key;
        String url = basicUrl + "summoner/v4/summoners/by-name/" + userName;
        Map<String, Object> params = Maps.newHashMap();
        params.put("key", key);
        String data = httpConnection.connectHttp(url, params, "GET");
        return data;
    }

}
