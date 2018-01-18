package Blatt11.construct;

public class Matrix {

    private Place matrix[][];

    public Matrix(int size){
        matrix = new Place[size][size];
        createPlaces(size);
    }

    private void createPlaces(final int size) {
        for(int i = 1; i <= size; i++){
            for(int j = 1; j<= size; j++){
                final int placeIdName = (i - 1) * size + j;
                matrix[i-1][j-1] = new Place(String.valueOf(placeIdName));
            }
        }
    }

    public Place[][] getMatrix() {
        return matrix;
    }


}
