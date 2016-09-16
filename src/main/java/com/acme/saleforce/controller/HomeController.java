package com.acme.saleforce.controller;

import com.acme.saleforce.response.ForceAuthorizationResponse;
import com.acme.saleforce.service.SaleForceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private Environment env;

    @Autowired
    private SaleForceHelper saleForceHelper;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("oauth_url", saleForceHelper.getAuthUrl());
        return "home";
    }


    @RequestMapping("/authorize")
    public String doPost(String code, Model model) {

        logger.info("Code " + code);

        ForceAuthorizationResponse authorizationResponse = saleForceHelper.getAuthorizationResponse(code);

        String instanceUrl = null;
        String accessToken = null;
        String refreshToken = null;
        String id = null;

        if (authorizationResponse != null) {

            instanceUrl = authorizationResponse.getInstance_url();
            accessToken = authorizationResponse.getAccess_token();
            refreshToken = authorizationResponse.getRefresh_token();
            id = authorizationResponse.getId();

        }

        logger.info(instanceUrl);

        model.addAttribute("instance_url", instanceUrl);
        model.addAttribute("access_token", accessToken);
        model.addAttribute("refresh_token", refreshToken);
        model.addAttribute("token_id", id);
        model.addAttribute("action_url", instanceUrl + "/apex/landingpage");

        return "authorize";
    }

}
