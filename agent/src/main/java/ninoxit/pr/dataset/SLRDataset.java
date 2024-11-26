package ninoxit.pr.dataset;

public class SLRDataset {
    private static double[] X = {23, 26, 30, 34, 43, 48, 52, 57, 58};
    private static double[] Y = {651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};

    // Obtener los valores de X
    public static double[] getX() {
        return X;
    }

    // Obtener los valores de Y
    public static double[] getY() {
        return Y;
    }

    // Método para mostrar los datos
    public void printData() {
        for (int i = 0; i < X.length; i++) {
            System.out.println("X: " + X[i] + ", Y: " + Y[i]);
        }
    }
}
