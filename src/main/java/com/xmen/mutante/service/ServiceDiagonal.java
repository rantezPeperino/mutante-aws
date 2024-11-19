package com.xmen.mutante.service;

public interface ServiceDiagonal {

         // Método para verificar diagonal principal
        public boolean checkDiagonal(char[][] matrix, int row, int col);

             // Método para verificar diagonal inversa
        public boolean checkInverseDiagonal(char[][] matrix, int row, int col);

            // Método para verificar diagonales en toda la matriz
        public boolean hasDiagonalSequence(char[][] matrix);

}
