package com.xmen.mutante.model;

public class Stats {


    Integer count_mutante_dna;
    Integer count_human_dna;
    Float ratio;

    public Stats(Integer count_human_dna, Integer count_mutante_dna, Float ratio) {
        this.count_human_dna = count_human_dna;
        this.count_mutante_dna = count_mutante_dna;
        this.ratio = ratio;
    }

    public Integer getCount_mutante_dna() {
        return count_mutante_dna;
    }

    public Integer getCount_human_dna() {
        return count_human_dna;
    }

    public Float getRatio() {
        return ratio;
    }

    public void setCount_mutante_dna(Integer count_mutante_dna) {
        this.count_mutante_dna = count_mutante_dna;
    }

    public void setCount_human_dna(Integer count_human_dna) {
        this.count_human_dna = count_human_dna;
    }

    public void setRatio(Float ratio) {
        this.ratio = ratio;
    }




}
