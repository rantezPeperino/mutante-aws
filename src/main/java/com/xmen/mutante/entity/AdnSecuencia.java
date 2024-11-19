package com.xmen.mutante.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "ADN", uniqueConstraints = {@UniqueConstraint(columnNames = "adn_sequence", name = "uk_adn_sequence")})
public class AdnSecuencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "adn_sequence", columnDefinition = "VARCHAR(250)")
    private String adnSequence;
    
    @Column(name = "is_mutante")
    private Integer isMutante;
    
    // Constructores
    public AdnSecuencia() {}
    
    public AdnSecuencia(String[] adn, Integer isMutante) {
        this.adnSequence = String.join(",", adn);
        this.isMutante = isMutante;
    }
    
    // Getters y Setters (los mismos que tenías)
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String[] getAdnSequence() {
        return adnSequence != null ? adnSequence.split(",") : new String[0];
    }
    
    public void setAdnSequence(String[] adn) {
        this.adnSequence = String.join(",", adn);
    }

    public Integer getIsMutante() {
        return isMutante;
    }

    public void setIsMutante(Integer isMutante) {
        this.isMutante = isMutante;
    }
}