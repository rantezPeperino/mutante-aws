package com.xmen.mutante.utils;

import com.xmen.mutante.exception.InvalidMutanteException;

public class Utils {

    public static boolean isValidMatNxN(String[] dna) {
        if (dna == null || dna.length == 0) {
            throw new InvalidMutanteException("La secuencia ADN no puede ser null o vacia");
        }

        int n = dna.length;

        // Verificar que la matriz sea NxN
        for (String row : dna) {

            if(row == null){
                throw new InvalidMutanteException("Row " + row + " is null");
            }
            if (row.length() != n) {
                throw new InvalidMutanteException("No coincide las dimensiones de la matriz");
            }

            // Verificar que solo contenga bases válidas
            for (char c : row.toCharArray()) {
                if (!isValidBase(c)) {
                    throw new InvalidMutanteException("Invalido ADN, debe contener A, T, C, G");
                }
            }
        }

        return true;
    }

    public static boolean isValidBase(char c) {
        for (char validBase : Constant.VALID_BASES) {
            if (c == validBase) {
                return true;
            }
        }
        return false;
    }

    // Método auxiliar para convertir array de Strings a matriz
    public static char[][] convertToMatrix(String[] dna) {
        char[][] matrix = new char[dna.length][dna[0].length()];
        for (int i = 0; i < dna.length; i++) {
            matrix[i] = dna[i].toCharArray();
        }
        return matrix;
    }
}
