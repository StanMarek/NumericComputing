package interpolation;

import filesController.FileReader;

import java.util.Arrays;
import java.util.Scanner;

public class Newton {

    double[] x;
    double[] y;
    double[] coefficients;
    double outcome;
    double xp;

    public Newton(){
        this.x = null;
        this.y = null;
        this.coefficients = null;
        this.outcome = 0;
    }

    private void printPolynomial(double[] output){

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
    }

    public void calculate(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Liczba punktow: ");
        int amountOfPoints = scanner.nextInt();
        this.x = new double[amountOfPoints];
        this.y = new double[amountOfPoints];
        for (int i = 0; i < amountOfPoints; i++) {
            System.out.print(String.format("x{%d}: ", i + 1));
            x[i] = scanner.nextDouble();
            System.out.print(String.format("y{%d}: ", i  + 1));
            y[i] = scanner.nextDouble();
        }
        System.out.println(Arrays.toString(x) + "\t" + Arrays.toString(y));
        //System.out.print("Punkt do obliczenia: ");
        //double point = scanner.nextDouble();
        double[] array = new double[y.length];
        System.arraycopy(y, 0, array, 0, y.length);
        array = fillArray(array, x);
        double[] output = calculateCoefficients(array, x);
        printPolynomial(output);
        System.out.println();
    }

    public void calculateSin(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Liczba punktow: ");
        int amountOfPoints = scanner.nextInt();
        this.x = new double[amountOfPoints];
        this.y = new double[amountOfPoints];

        //System.out.print("Wynik to sin(x)\nPunkt do obliczenia: x = ");
        //double XP = scanner.nextDouble();
        //this.xp = XP;
        for (int i = 0; i < amountOfPoints; i++) {
            x[i] = Math.PI * 1/2 * i;
            y[i] = Math.sin(x[i]);
        }
        System.out.println(Arrays.toString(x) + "\t" + Arrays.toString(y));
        System.out.println(Arrays.toString(x) + "\t" + Arrays.toString(y));
        //System.out.print("Punkt do obliczenia: ");
        //double point = scanner.nextDouble();
        double[] array = new double[y.length];
        System.arraycopy(y, 0, array, 0, y.length);
        array = fillArray(array, x);
        double[] output = calculateCoefficients(array, x);
        printPolynomial(output);
        System.out.println();
    }

    private double[] calculateCoefficients(double[] bk, double[] xs) {

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

    private double[] fillArray(double[] array, double[] ys){

        for(int i = 0; i < ys.length; i++){
            for(int j = ys.length -1 ; j > i; j--){
                array[j] = (array[j] - array[j-1]) / (ys[j] - ys[j - i -1]);
            }
        }

        return array;
    }

    public double[] getX() {
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
    }
}
