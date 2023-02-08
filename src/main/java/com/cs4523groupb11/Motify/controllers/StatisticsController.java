package com.cs4523groupb11.Motify.controllers;


import com.cs4523groupb11.Motify.entities.StatisticsData;
import com.cs4523groupb11.Motify.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService){
        this.statisticsService = statisticsService;
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<StatisticsData> getStatisticsByUserId(@PathVariable String userId){
        return ResponseEntity.ok(new StatisticsData());
    }
}
