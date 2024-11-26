import dataset.SLRDataset;
import src.PolynomialRegression;

public class Main {
    public static void main(String[] args) {
        double[] x = SLRDataset.getX();
        double[] y = SLRDataset.getY();

        int degree = 3;

        double[] coefficients = PolynomialRegression.polynomialRegression(x, y, degree);
        
        double[] y_pred = PolynomialRegression.predict(x, coefficients);

        System.out.println(" ");
        double rSquared = PolynomialRegression.calculateRSquared(y, y_pred);
        System.out.println("R^2: " + rSquared);

        double adjustedRSquared = PolynomialRegression.calculateAdjustedRSquared(rSquared, x.length, degree);
        System.out.println("Adjusted R^2: " + adjustedRSquared);

        double[] standardErrors = PolynomialRegression.calculateStandardError(x, y, coefficients, degree);
        System.out.println("Standard Errors of Beta coefficients:");
        for (int i = 0; i < standardErrors.length; i++) {
            System.out.println("SE(Beta " + i + "): " + standardErrors[i]);
        }

        System.out.println("\nCoeficientes:");
        for (int i = 0; i < coefficients.length; i++) {
            System.out.println("Beta " + i + ": " + coefficients[i]);
        }
    }
}
