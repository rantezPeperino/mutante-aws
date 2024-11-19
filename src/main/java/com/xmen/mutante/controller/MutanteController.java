package com.xmen.mutante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xmen.mutante.exception.InvalidMutanteException;
import com.xmen.mutante.model.AdnRequest;
import com.xmen.mutante.service.ServiceMutante;
import com.xmen.mutante.utils.Utils;

@RestController
@RequestMapping("/mutante")
public class MutanteController {

@Autowired
public ServiceMutante serviceMutante;


    @PostMapping("/")
    public ResponseEntity<String> analyzeDna(@RequestBody AdnRequest dna) {
        try {
            Utils.isValidMatNxN(dna.getDna());
            boolean isMutant = serviceMutante.isMutant(dna.getDna());
            return isMutant ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (InvalidMutanteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
