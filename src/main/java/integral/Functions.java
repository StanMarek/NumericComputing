package integral;

public interface Functions {

    static double x2sin3x(double x) {
        double x2 = Math.pow(x, 2);
        double sin3x = Math.pow(Math.sin(x), 3);

        return x2 * sin3x;
    }

    static double expx2xminus1(double x){
        double ex2 = Math.exp(Math.pow(x, 2));
        double polynomial = x - 1;

        return ex2 * polynomial;
    }

    static double polynomial4degree(double x){
        double a0 = 55;
        double a1 = -2;
        double a2 = -6;
        double a3 = 5;
        double a4 = 0.4;

        return a4*Math.pow(x, 4)+a3*Math.pow(x, 3)+a2*Math.pow(x, 2)+a1*x+a0;
    }
}
