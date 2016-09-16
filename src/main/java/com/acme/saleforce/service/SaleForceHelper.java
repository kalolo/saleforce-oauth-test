package com.acme.saleforce.service;

import com.acme.saleforce.response.ForceAuthorizationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
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


    public ForceAuthorizationResponse getAuthorizationResponse(String code) {
        String client_id    = env.getProperty("saleforce.client_id");
        String secret       = env.getProperty("saleforce.client_secret");
        String redirect_uri = env.getProperty("saleforce.redirect_uri");

        RestTemplate rst = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.set("grant_type", "authorization_code");
        params.set("code", code);
        params.set("client_id", client_id);
        params.set("client_secret", secret);
        params.set("redirect_uri", redirect_uri);

        try {

            ForceAuthorizationResponse authorizationResponse = rst.postForObject(env.getProperty("saleforce.auth_login"), params, ForceAuthorizationResponse.class);

            logger.info("Authorization response: " + authorizationResponse);
            return authorizationResponse;

        }catch (HttpClientErrorException e) {

            logger.error("error:  " + e.getResponseBodyAsString());

        } catch (Exception e) {

            logger.error(e.getCause().getMessage());
        }

        return null;
    }
}
