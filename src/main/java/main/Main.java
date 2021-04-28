package main;

import filesController.FileSaver;
import integral.Functions;
import integral.Integral;
import integral.Quadrature;
import interpolation.Lagrange;
import interpolation.Newton;
import setOfEquations.Matrix;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean running = true;
        while (running) {
            System.out.print("0. Exit\n" +
                    "1. Lagrange\n" +
                    "2. Newton\n" +
                    "3. Gauss elimination\n" +
                    "4. Gauss Croute elimination\n" +
                    "5. Integration\n" +
                    "6. Quadrature\n" +
                    "Choice: ");
            Scanner program = new Scanner(System.in);
            int choice = program.nextInt();

            switch (choice){
                case 0 -> { running = false; break; }
                case 1 -> runLagrange();
                case 2 -> runNewton();
                case 3 -> runGauss();
                case 4 -> runGaussCroute();
                case 5 -> runIntegration();
                case 6 -> runGaussLegendreQuadrature();
            }
        }
    }

    static void runLagrange() throws IOException {

        System.out.println("--- LAGRANGE ---");

        Lagrange lagrange = new Lagrange();
        //double output = lagrange.calculate();
        double output = lagrange.calculateSin();
        FileSaver.saveToFile("outcometest3", lagrange);
        System.out.println(output);
    }

    static void runNewton(){

        System.out.println("--- NEWTON ---");

        Newton.calculate();
        Newton.calculateSin();
    }

    static void runGauss(){

        System.out.println("--- GAUSS ELIMINATION ---");
        int numberOfGauss = 4;

        Matrix matrix = new Matrix(numberOfGauss, 'g');
        boolean isOk = matrix.gaussElimination();
        double[] output = matrix.getX();
        if(isOk){
            for(int i = 0; i < numberOfGauss; i++)
                System.out.println("X" + (i+1) + " = " + output[i]);
        }
        else
            System.out.println("Blad");
    }

    static void runGaussCroute(){

        System.out.println("--- GAUSS CROUTE ELIMINATION ---");
        int numberOfGauss = 4;

        Matrix matrix = new Matrix(numberOfGauss, 'c');
        boolean isOk = matrix.gaussCrouteElimination();
        double[] output = matrix.getX();
        if(isOk){
                for(int i = 0; i < numberOfGauss; i++)
                    System.out.println("X" + (i+1) + " = " + output[i]);
            }
            else
                System.out.println("Blad");
    }

    static void runIntegration(){

        int n = 10;

        for(long i = n; i < 10000000; i*=10 ) {
            System.out.println("f1 + trapez: " + Integral.trapeze(x -> Functions.x2sin3x((double)x), i, 0, 4.5) + "n = " + i);
            System.out.println("f2 + trapez: " + Integral.trapeze(x -> Functions.expx2xminus1((double)x), i, -2, 2) + "n = " + i);
            System.out.println("f1 + simpson: " + Integral.simpson(x -> Functions.x2sin3x((double)x), i, 0, 4.5) + "n = " + i);
            System.out.println("f2 + simpson: " + Integral.simpson(x -> Functions.expx2xminus1((double)x), i, -2, 2) + "n = " + i);
        }

        double[] coefficients = new double[]{0.4, 5, -6, -2, 55};
        double x_horner = 2;
        System.out.println("Horner: " + Integral.Horner(coefficients,4, x_horner) + " dla x = " + x_horner);
    }

    static void runGaussLegendreQuadrature(){

        double simpsonF1 = Integral.simpson(fun -> Functions.x2sin3x((double) fun), Math.pow(10, 5), 0, 1);
        double simpsonF2 =  Integral.simpson(fun -> Functions.expx2xminus1((double) fun), Math.pow(10, 5), -1, 1);

        System.out.println("Simpson 10000 iteracji");
        System.out.println("f1 - simpson: " + simpsonF1);
        System.out.println("f2 - simpson: " + simpsonF2);

        {
            double[] x = { -0.577350, 0.577350};
            double[] A = { 1, 1};
            System.out.println("f1 n: " + x.length + " = " + Quadrature.integrateGaussQuadrature(fun -> Functions.x2sin3x((double) fun), x, A, 0, 1));
            System.out.println("f2 n: " + x.length + " = " + Quadrature.integrateGaussQuadrature(fun -> Functions.expx2xminus1((double)fun), x, A,-1, 1));
        }

        {
            double[] x = { -0.774597, 0.0, 0.774597 };
            double[] A = { 5.0 / 9.0, 8.0 / 9.0, 5.0 / 9.0 };
            System.out.println("f1 n: " + x.length + " = " + Quadrature.integrateGaussQuadrature(fun -> Functions.x2sin3x((double) fun), x, A, 0, 1));
            System.out.println("f2 n: " + x.length + " = " + Quadrature.integrateGaussQuadrature(fun -> Functions.expx2xminus1((double)fun), x, A,-1, 1));
        }

        {
            double[] x = { -0.861136, -0.339981, 0.339981, 0.861136};
            double[] A = { 0.347855, 0.652145, 0.652145, 0.347855};
            System.out.println("f1 n: " + x.length + " = " + Quadrature.integrateGaussQuadrature(fun -> Functions.x2sin3x((double) fun), x, A, 0, 1));
            System.out.println("f2 n: " + x.length + " = " + Quadrature.integrateGaussQuadrature(fun -> Functions.expx2xminus1((double)fun), x, A,-1, 1));
        }

        {
            double[] x = { -0.906180, -0.538469, 0, 0.538469, 0.906180};
            double[] A = { 0.236927, 0.478629, 0.568889, 0.478629, 0.236927};
            System.out.println("f1 n: " + x.length + " = " + Quadrature.integrateGaussQuadrature(fun -> Functions.x2sin3x((double) fun), x, A, 0, 1));
            System.out.println("f2 n: " + x.length + " = " + Quadrature.integrateGaussQuadrature(fun -> Functions.expx2xminus1((double)fun), x, A,-1, 1));
        }
    }
}
