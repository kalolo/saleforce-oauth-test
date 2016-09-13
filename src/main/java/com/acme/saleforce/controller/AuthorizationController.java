package com.acme.saleforce.controller;

import com.acme.saleforce.response.ForceAuthorizationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class AuthorizationController {

    Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

    @Autowired
    private Environment env;


    @CrossOrigin
    @RequestMapping(value="/auth/saleforce", method = RequestMethod.GET)
    public void getAuthGet(String code) {

        logger.info("Code " + code);
        logger.info(env.getProperty("saleforce.auth_login"));

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

        }catch (HttpClientErrorException e) {

            logger.error("error:  " + e.getResponseBodyAsString());

        } catch (Exception e) {

            logger.error(e.getCause().getMessage());
        }


    }
}
