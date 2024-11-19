package com.xmen.mutante.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmen.mutante.model.Stats;
import com.xmen.mutante.repository.ADNRepositorio;
import com.xmen.mutante.service.StatsService;
import com.xmen.mutante.utils.Constant;

@Service
public class StatsServiceImpl implements StatsService{

    @Autowired
    private ADNRepositorio adnRepositorio;

    @Override
    public Stats calculateStats() {
        Integer mutantCount = adnRepositorio.countByIsMutante(Constant.UNO);
        Integer humanCount = adnRepositorio.countByIsMutante(Constant.CERO);
        float ratio = 0.0f;
          // Calcular ratio    
      
        if (humanCount > 0) {
            ratio = (float) mutantCount / humanCount;
        }
      
        
        return new Stats(humanCount, mutantCount, ratio);
    }

}
