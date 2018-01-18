package Blatt11;

import Blatt11.construct.Matrix;
import Blatt11.services.HeuristicSearchService;
import Blatt11.services.MatrixFileReaderService;

import java.io.File;

public class Main {

    private static String MATRIX_LOESBAR_1 = "src/main/resources/Blatt11/matrix1_loesbar";
    private static String MATRIX_UNLOESBAR_1 = "src/main/resources/Blatt11/matrix1_unloesbar";

    public static void main(String... args){
        MatrixFileReaderService matrixFileReaderService = new MatrixFileReaderService();

        final Matrix matrix = matrixFileReaderService.readFile(new File(MATRIX_LOESBAR_1));

        HeuristicSearchService heuristicSearchService = new HeuristicSearchService();

        heuristicSearchService.search(matrix);

        System.out.print("DebugPoint");
    }

}
