package com.xmen.mutante.service.impl;

import org.springframework.stereotype.Service;

import com.xmen.mutante.service.ServiceDiagonal;
import com.xmen.mutante.utils.Constant;

@Service
public class ServiceDiagonalImpl implements ServiceDiagonal {
    
    @Override
    public boolean checkDiagonal(char[][] matrix, int row, int col) {
        if (!isValidPosition(matrix, row, col)) {
            return false;
        }
        return checkSequence(matrix, row, col, 1, 1);
    }
    
    @Override
    public boolean checkInverseDiagonal(char[][] matrix, int row, int col) {
        if (!isValidInversePosition(matrix, row, col)) {
            return false;
        }
        return checkSequence(matrix, row, col, 1, -1);
    }

    @Override
    public boolean hasDiagonalSequence(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        return checkMainDiagonals(matrix) || checkInverseDiagonals(matrix);
    }

    private boolean isValidPosition(char[][] matrix, int row, int col) {
        return matrix != null && 
               row + Constant.SEQUENCE_LENGTH <= matrix.length && 
               col + Constant.SEQUENCE_LENGTH <= matrix.length &&
               matrix[row][col] != ' ';
    }
    
    private boolean isValidInversePosition(char[][] matrix, int row, int col) {
        return matrix != null && 
               row + Constant.SEQUENCE_LENGTH <= matrix.length && 
               col >= Constant.SEQUENCE_LENGTH - 1 &&
               matrix[row][col] != ' ';
    }
    
    private boolean checkSequence(char[][] matrix, int row, int col, int rowInc, int colInc) {
        char base = matrix[row][col];
        int count = 1;
        
        for (int i = 1; i < Constant.SEQUENCE_LENGTH; i++) {
            if (matrix[row + i * rowInc][col + i * colInc] != base) {
                return false;
            }
            count++;
        }
        return count == Constant.SEQUENCE_LENGTH;
    }

    private boolean checkMainDiagonals(char[][] matrix) {
        for (int i = 0; i <= matrix.length - Constant.SEQUENCE_LENGTH; i++) {
            for (int j = 0; j <= matrix.length - Constant.SEQUENCE_LENGTH; j++) {
                if (checkDiagonal(matrix, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkInverseDiagonals(char[][] matrix) {
        for (int i = 0; i <= matrix.length - Constant.SEQUENCE_LENGTH; i++) {
            for (int j = matrix.length - 1; j >= Constant.SEQUENCE_LENGTH - 1; j--) {
                if (checkInverseDiagonal(matrix, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
}