package com.xmen.mutante.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.xmen.mutante.entity.AdnSecuencia;
import com.xmen.mutante.exception.InvalidMutanteException;
import com.xmen.mutante.repository.ADNRepositorio;
import com.xmen.mutante.service.ServiceMutante;
import com.xmen.mutante.service.ServiceSecuenciaAdn;



@Service
public class ServiceMutanteImpl implements ServiceMutante {

    @Autowired
    ServiceSecuenciaAdn serviceSecuenciaAdn;

    @Autowired
    ADNRepositorio adnRepositorio;

    @Override
    @SuppressWarnings("unreachable")
    public boolean isMutant(String[] dna) {





        boolean isMutant = serviceSecuenciaAdn.isMutant(dna);

        // Guardar en la base de datos
        try {
            AdnSecuencia adnEntity = new AdnSecuencia(dna, isMutant ? 1 : 0);
            adnRepositorio.save(adnEntity);
            return isMutant;
            
          } catch (InvalidMutanteException | DataIntegrityViolationException e ) {
            throw new InvalidMutanteException("La secuencia de ADN existe en la base de datos");
          }
    }


}
