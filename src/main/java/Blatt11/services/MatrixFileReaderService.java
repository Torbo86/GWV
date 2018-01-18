package Blatt11.services;

import Blatt11.construct.GameValue;
import Blatt11.construct.Matrix;
import Blatt11.construct.Place;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixFileReaderService {

    public Matrix readFile(final File file) {
        Matrix matrix = null;
        if (file.exists()) {
            try {
                final List<String> lineList = Files.readAllLines(file.toPath());
                final int size = lineList.size();
                final List<String> gameValuesForPlaceList = new ArrayList<>();
                lineList.forEach(s -> gameValuesForPlaceList.addAll(Arrays.asList(s.split(","))));

                matrix = new Matrix(size);
                fillMatrixWithGameValues(matrix, gameValuesForPlaceList, size);
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }


        return matrix;
    }

    private void fillMatrixWithGameValues(Matrix matrix, List<String> gameValuesForPlaceList, int size) {
        Place place[][] = matrix.getMatrix();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final GameValue gameValue = new GameValue(gameValuesForPlaceList.get(size * i + j));
                place[i][j].setCurrentGameValue(gameValue);

                if (size * i + j + 1 == 16) {
                    place[i][j].setSolutionGameValueName(new GameValue("blank"));
                } else {
                    place[i][j].setSolutionGameValueName(new GameValue(String.valueOf(size * i + j + 1)));
                }
            }
        }
    }
}
