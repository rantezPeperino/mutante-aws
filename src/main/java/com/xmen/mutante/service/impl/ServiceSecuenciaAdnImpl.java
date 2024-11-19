package com.xmen.mutante.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmen.mutante.exception.InvalidMutanteException;
import com.xmen.mutante.service.ServiceDiagonal;
import com.xmen.mutante.service.ServiceHorizontal;
import com.xmen.mutante.service.ServiceSecuenciaAdn;
import com.xmen.mutante.service.ServiceVertical;
import com.xmen.mutante.utils.Utils;

@Service
public class ServiceSecuenciaAdnImpl implements ServiceSecuenciaAdn{

    @Autowired
    ServiceHorizontal serviceHorizontal;

    @Autowired
    ServiceDiagonal serviceDiagonal;

    @Autowired
    ServiceVertical serviceVertical;

    @Override
    public boolean isMutant(String[] dna) {
        if (dna == null || dna.length == 0) {
           throw new InvalidMutanteException("La secuencia ADN no puede ser null o vacia");
        }
                boolean isMutant = false;
        char[][] matrix = Utils.convertToMatrix(dna);
        if (serviceDiagonal.hasDiagonalSequence(matrix) || serviceHorizontal.hasHorizontalSequence(matrix) || serviceVertical.hasVerticalSequence(matrix)) {
            isMutant = true;
        }
        return isMutant;
    }



}
