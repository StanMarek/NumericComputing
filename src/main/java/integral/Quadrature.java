package integral;

import java.util.function.Function;

public class Quadrature {

    public static double integrateGaussQuadrature(Function f, double[]x, double []A, double a, double b) {
        if (b < a) {
            double temp = a;
            a = b;
            b = temp;
        }

        double average = (a + b)*0.5;
        double length = (b - a)*0.5;
        double sum = 0.0;

        for (int k = 0; k <x.length; ++k) {
            double t = average + length*x[k];
            sum += A[k] * (double)f.apply(t);
        }

        return sum*length;
    }
}
