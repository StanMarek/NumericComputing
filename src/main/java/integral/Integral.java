package integral;

import java.util.function.Function;

public class Integral {

    public double function1(double x) {
        double x2 = Math.pow(x, 2);
        double sin3x = Math.pow(Math.sin(x), 3);

        return x2 * sin3x;
    }

    public double function2(double x){
        double ex2 = Math.exp(Math.pow(x, 2));
        double polynomial = x - 1;

        return ex2 * polynomial;
    }

    public double function3(double x){
        double a0 = 55;
        double a1 = -2;
        double a2 = -6;
        double a3 = 5;
        double a4 = 0.4;

        return a4*Math.pow(x, 4)+a3*Math.pow(x, 3)+a2*Math.pow(x, 2)+a1*x+a0;
    }

    public double trapeze(Function fun, double n, double x_start, double x_end) {
        double result = 0;
        double step = (x_end - x_start) / n;
        for(double x = x_start ; x < x_end - step ; x += step) {
            result += (((double)fun.apply(x) + (double)fun.apply(x + step)) * step)/2.;
        }
        return result;
    }

    public double simpson(Function fun, double n, double x_start, double x_end) {

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
