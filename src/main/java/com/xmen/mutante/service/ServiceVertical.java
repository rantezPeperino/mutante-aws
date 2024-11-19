package com.xmen.mutante.service;

public interface ServiceVertical {


        // Método principal para verificar secuencias verticales
        public boolean checkVertical(char[][] matrix, int row, int col);


        // Método para verificar toda la matriz
        public boolean hasVerticalSequence(char[][] matrix);

}
