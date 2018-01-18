package Blatt11.services;

import Blatt11.construct.Matrix;

public class MatrixOutputService {

    Matrix matrix;

    public MatrixOutputService(final Matrix matrix){
        this.matrix = matrix;
    }

    public void printOutput(){
        for(int i = 0; i < matrix.getMatrix().length; i++){
            for(int j = 0; j < matrix.getMatrix().length; j++){
                System.out.print("| " + matrix.getMatrix()[i][j].getCurrentGameValue() + " ");
            }
            System.out.print(" |\n");
        }
    }
}
