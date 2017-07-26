package com.training.requesttracker.controller;

import com.training.requesttracker.stats.RequestStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.RescaleOp;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by sony on 7/22/2017.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private RequestStats requestStats;

    @RequestMapping(value = "/", method = {GET, POST, DELETE})
    public ModelAndView requests1(HttpServletRequest request, HttpMethod method) {
        requestStats.calcRequest(request);

//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.exchange(request.getRequestURI(), method, new HttpEntity<String>(body), String.class);

        return new ModelAndView("forward:/stats/statsPageHtml");
    }
}
