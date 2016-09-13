package com.acme.saleforce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SaleForceHelper {

    private Logger logger = LoggerFactory.getLogger(SaleForceHelper.class);

    @Autowired
    private Environment env;

    public String getAuthUrl() {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(env.getProperty("saleforce.authorize_uri"));
        urlBuilder.queryParam("response_type","code");
        urlBuilder.queryParam("client_id", env.getProperty("saleforce.client_id"));
        urlBuilder.queryParam("redirect_uri", env.getProperty("saleforce.redirect_uri"));
        return urlBuilder.build().encode().toUriString();
    }
}
