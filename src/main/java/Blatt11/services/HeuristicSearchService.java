package Blatt11.services;

import Blatt11.construct.Matrix;

public class HeuristicSearchService {

    public void search(final Matrix matrix){
        MatrixOutputService matrixOutputService = new MatrixOutputService(matrix);
        matrixOutputService.printOutput();
    }
}
