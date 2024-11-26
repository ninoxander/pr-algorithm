package ninoxit.pr.src;

import ninoxit.pr.discretemaths.MatrixOperations;


public class PolynomialRegression {

    public static double[][] createDesignMatrix(double[] x, int degree) {
        int n = x.length;
        double[][] X = new double[n][degree + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= degree; j++) {
                X[i][j] = Math.pow(x[i], j);
            }
        }
        return X;
    }

    public static double[] polynomialRegression(double[] x, double[] y, int degree) {
        double[][] X = createDesignMatrix(x, degree);
        double[][] X_transposed = MatrixOperations.transpose(X);
        double[][] XTX = MatrixOperations.multiply(X_transposed, X);
        double[][] XTX_inv = MatrixOperations.invert(XTX);
        double[] XTy = MatrixOperations.multiply(X_transposed, y);
        double[] beta = MatrixOperations.multiply(XTX_inv, XTy);
        return beta;
    }

    public static double[] predict(double[] x, double[] beta) {
        int n = x.length;
        int degree = beta.length - 1;
        double[] y_pred = new double[n];

        for (int i = 0; i < n; i++) {
            y_pred[i] = 0;
            for (int j = 0; j <= degree; j++) {
                y_pred[i] += beta[j] * Math.pow(x[i], j);
            }
        }
        return y_pred;
    }

    public static double calculateRSquared(double[] y, double[] y_pred) {
        double sumSquaredResiduals = 0.0;
        double sumSquaredTotal = 0.0;
        double y_mean = 0.0;

        for (double value : y) {
            y_mean += value;
        }
        y_mean /= y.length;

        for (int i = 0; i < y.length; i++) {
            sumSquaredResiduals += Math.pow(y[i] - y_pred[i], 2);
            sumSquaredTotal += Math.pow(y[i] - y_mean, 2);
        }

        return 1 - (sumSquaredResiduals / sumSquaredTotal);
    }

    public static double calculateAdjustedRSquared(double rSquared, int n, int p) {
        return 1 - ((1 - rSquared) * (n - 1)) / (n - p - 1);
    }

    public static double[] calculateStandardError(double[] x, double[] y, double[] beta, int degree) {
        int n = x.length;
        double[] y_pred = predict(x, beta);

        double sumSquaredResiduals = 0.0;
        for (int i = 0; i < n; i++) {
            sumSquaredResiduals += Math.pow(y[i] - y_pred[i], 2);
        }
        double residualVariance = sumSquaredResiduals / (n - degree - 1);

        double[] standardErrors = new double[beta.length];
        for (int j = 0; j < beta.length; j++) {
            double sumSquaredXi = 0.0;
            for (int i = 0; i < n; i++) {
                sumSquaredXi += Math.pow(x[i], j);
            }
            standardErrors[j] = Math.sqrt(residualVariance / sumSquaredXi);
        }

        return standardErrors;
    }

    public static void main(String[] args) {
        double[] x = {1, 2, 3, 4, 5};
        double[] y = {2, 4, 9, 16, 25};

        int degree = 2;

        double[] coefficients = polynomialRegression(x, y, degree);

        double[] y_pred = predict(x, coefficients);

        System.out.println(" ");
        double rSquared = calculateRSquared(y, y_pred);
        System.out.println("R^2: " + rSquared);

        double adjustedRSquared = calculateAdjustedRSquared(rSquared, x.length, degree);
        System.out.println("Adjusted R^2: " + adjustedRSquared);

        double[] standardErrors = calculateStandardError(x, y, coefficients, degree);
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
