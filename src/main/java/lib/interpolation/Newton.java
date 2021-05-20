package lib.interpolation;

import java.util.Arrays;
import java.util.Scanner;

public class Newton {

    private static double[] x;
    private static double[] y;
    private static double[] coefficients;

    private static void printPolynomial(double[] output){

        System.out.print("f(x) = ");
        int leng = output.length;

        for(int i = 0; i < leng; i++){
            if(i == leng-1)
                System.out.print("+" + String.valueOf(output[i]));
            else if(output[i] < 0)
                System.out.print(String.valueOf(output[i]) + "x^" + String.valueOf((leng - i -1)));
            else if(output[i] > 0)
                System.out.print("+" + String.valueOf(output[i]) + "x^" + String.valueOf((leng - i -1)));
        }
        System.out.println();
    }

    public static void calculate(){
        getXFromInput();
        System.out.println(Arrays.toString(x) + "\t" + Arrays.toString(y));
        double[] array = new double[y.length];
        System.arraycopy(y, 0, array, 0, y.length);
        array = fillArray(array, x);
        double[] output = calculateCoefficients(array, x);
        printPolynomial(output);
    }

    public static void calculateSin(){
        fillWithSinus();
        System.out.println(Arrays.toString(x) + "\t" + Arrays.toString(y));
        System.out.println(Arrays.toString(x) + "\t" + Arrays.toString(y));
        //System.out.print("Punkt do obliczenia: ");
        //double point = scanner.nextDouble();
        double[] array = new double[y.length];
        System.arraycopy(y, 0, array, 0, y.length);
        array = fillArray(array, x);
        double[] output = calculateCoefficients(array, x);
        printPolynomial(output);
    }

    private static double[] calculateCoefficients(double[] bk, double[] xs) {

        coefficients = bk;
        int length = bk.length;
        for(int i = length - 2; i > -1; i--) {
            for(int j = i; j < length - 1; j++){
                coefficients[j] = coefficients[j] - (xs[i] * coefficients[j+1]);
            }
        }
        double[] output = new double[bk.length];
        int j = 0;
        for(int i = bk.length - 1; i >= 0; i--){
            output[j] = bk[i];
            j++;
        }
        return output;
    }

    private static double[] fillArray(double[] array, double[] ys){

        for(int i = 0; i < ys.length; i++){
            for(int j = ys.length -1 ; j > i; j--){
                array[j] = (array[j] - array[j-1]) / (ys[j] - ys[j - i -1]);
            }
        }

        return array;
    }

    private static void getXFromInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Liczba punktow: ");
        int amountOfPoints = scanner.nextInt();
        x = new double[amountOfPoints];
        y = new double[amountOfPoints];
        for (int i = 0; i < amountOfPoints; i++) {
            System.out.print(String.format("x{%d}: ", i + 1));
            x[i] = scanner.nextDouble();
            System.out.print(String.format("y{%d}: ", i  + 1));
            y[i] = scanner.nextDouble();
        }
    }

    private static void fillWithSinus(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Liczba punktow: ");
        int amountOfPoints = scanner.nextInt();
        x = new double[amountOfPoints];
        y = new double[amountOfPoints];
        for (int i = 0; i < amountOfPoints; i++) {
            x[i] = Math.PI * 1/2 * i;
            y[i] = Math.sin(x[i]);
        }
    }

   /* public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public double getOutcome() {
        return outcome;
    }

    public double getXp() {
        return xp;
    }*/
}
