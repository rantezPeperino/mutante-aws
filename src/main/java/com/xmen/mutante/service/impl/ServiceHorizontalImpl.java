package com.xmen.mutante.service.impl;

import org.springframework.stereotype.Service;

import com.xmen.mutante.service.ServiceHorizontal;
import com.xmen.mutante.utils.Constant;
@Service
public class ServiceHorizontalImpl implements ServiceHorizontal {

    @Override
    public boolean checkHorizontal(char[][] matrix, int row, int col) {
        if (matrix == null || matrix.length == 0 || col > matrix[0].length - Constant.SEQUENCE_LENGTH) {
            return false;
        }
        int count = 1;
        char base = matrix[row][col];
        for (int j = 1; j < Constant.SEQUENCE_LENGTH; j++) {
            if (matrix[row][col + j] == base) {
                count++;
            } else {
                break;
            }
        }
        return count == Constant.SEQUENCE_LENGTH;
    }

    @Override
    public boolean hasHorizontalSequence(char[][] matrix) {
        int sequencesFound = 0;

        // Verificar secuencias horizontales
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <= matrix.length - Constant.SEQUENCE_LENGTH; j++) {
                if (checkHorizontal(matrix, i, j)) {
                    sequencesFound++;
                }
                if (sequencesFound >= 1) return true;
            }
        }
       return false;
    }

}
