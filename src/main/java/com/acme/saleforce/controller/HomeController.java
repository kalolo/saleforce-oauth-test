package com.acme.saleforce.controller;

import com.acme.saleforce.service.SaleForceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private Environment env;

    @Autowired
    private SaleForceHelper saleForceHelper;

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("oauth_url", saleForceHelper.getAuthUrl());
        return "home";
    }
}
