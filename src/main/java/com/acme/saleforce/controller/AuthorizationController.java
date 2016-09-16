package com.acme.saleforce.controller;

import com.acme.saleforce.response.ForceAuthorizationResponse;
import com.acme.saleforce.service.SaleForceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private SaleForceHelper saleForceHelper;

    @CrossOrigin
    @RequestMapping(value="/auth/saleforce", method = RequestMethod.GET)
    public void getAuthGet(String code) {

        logger.info("Code " + code);

        ForceAuthorizationResponse authorizationResponse = saleForceHelper.getAuthorizationResponse(code);


    }
}
