package com.xmen.mutante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xmen.mutante.entity.AdnSecuencia;


@Repository
public interface ADNRepositorio extends JpaRepository<AdnSecuencia, Long>{


    List<AdnSecuencia> findByIsMutante(Integer isMutante);

    Integer countByIsMutante(Integer isMutante);
}
