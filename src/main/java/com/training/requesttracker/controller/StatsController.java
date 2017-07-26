package com.training.requesttracker.controller;

import com.training.requesttracker.entity.TimeUnit;
import com.training.requesttracker.stats.RequestStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sony on 7/23/2017.
 */
@RestController
@RequestMapping("stats")
public class StatsController {

    @Autowired
    private RequestStats requestStats;

    @GetMapping(value = "getStats", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> getStats() {
        StringBuilder result = new StringBuilder();
        requestStats.getStats().entrySet().stream().forEach(entry -> {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        });
        return new ResponseEntity<>(result.toString().trim(), HttpStatus.OK);
    }

    @GetMapping(value = "getStatsPerSecond", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Double> getStatsPerSecond() {
        double result = 0.00000;
        if (!requestStats.getStats().isEmpty()) {
            for (Map.Entry<TimeUnit, AtomicInteger> entry : requestStats.getStats().entrySet()) {
                TimeUnit key = entry.getKey();
                AtomicInteger value = entry.getValue();
                result += (double)value.get() / 60;
            }

            result = result / requestStats.getStats().size();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
