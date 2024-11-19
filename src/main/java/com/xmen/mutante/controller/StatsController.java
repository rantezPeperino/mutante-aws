package com.xmen.mutante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xmen.mutante.model.Stats;
import com.xmen.mutante.service.StatsService;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping("/")
    public ResponseEntity<Stats> getStats() {
        try {
            Stats stats = statsService.calculateStats();
            if (stats == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(stats);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }




}
