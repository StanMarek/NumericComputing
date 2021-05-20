package lib.integral;

import java.util.function.Function;

public class Integral {

    public static double trapeze(Function fun, double n, double x_start, double x_end) {
        double result = 0;
        double step = (x_end - x_start) / n;
        for(double x = x_start ; x < x_end - step ; x += step) {
            result += (((double)fun.apply(x) + (double)fun.apply(x + step)) * step)/2.;
        }
        return result;
    }

    public static double simpson(Function fun, double n, double x_start, double x_end) {
        double output = 0;
        double dx = (x_end - x_start) / n;
        double st = 0;

        for(int i = 1; i <= n; i++){
            double x = x_start + i * dx;
            st += (double)fun.apply(x - dx / 2);
            if(i < n)
                output += (double)fun.apply(x);
        }

        output = dx / 6 * ((double)fun.apply(x_start) + (double)fun.apply(x_end) + 2 * output + 4 * st);

        return output;
    }

    public static double Horner(double[] coef, int degree, double x) {
        if(degree == 0)
            return coef[0];
        return x*Horner(coef, degree - 1, x) + coef[degree];
    }
}
