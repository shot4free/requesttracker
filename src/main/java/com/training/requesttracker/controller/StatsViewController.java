package com.training.requesttracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sony on 7/23/2017.
 */
@Controller
@RequestMapping("/stats")
public class StatsViewController {

    @GetMapping("/statsPage")
    public ModelAndView showStatsPage() {
        return new ModelAndView("stats");
    }

    @GetMapping("/statsPageHtml")
    public String showStatsPageHtml() {
        return "stats";
    }
}
