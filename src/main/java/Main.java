import lib.filescontroller.FileSaver;
import lib.integral.Functions;
import lib.integral.Integral;
import lib.integral.Quadrature;
import lib.interpolation.*;
import lib.setoffequations.Matrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static void printMenu() {
        System.out.print("0. Exit\n" +
                "1. Lagrange\n" +
                "2. Newton\n" +
                "3. Gauss elimination\n" +
                "4. Gauss Croute elimination\n" +
                "5. Doolittle algorith\n" +
                "6. Integration\n" +
                "7. Quadrature\n" +
                "8. QR Decomposition, Gram-Schmidt\n" +
                "9. Approximation\n" +
                "Choice: ");
    }

    public static void main(String[] args) throws IOException {
        boolean running = true;
        Scanner program = new Scanner(System.in);
        int choice;

        while (running) {
            printMenu();
            choice = program.nextInt();
            switch (choice){
                case 0 -> running = false;
                case 1 -> runLagrange();
                case 2 -> runNewton();
                case 3 -> runGauss();
                case 4 -> runGaussCroute();
                case 5 -> runDoolittle();
                case 6 -> runIntegration();
                case 7 -> runGaussLegendreQuadrature();
                case 8 -> runGramSchmidt();
                case 9 -> runApprox();
            }
        }
    }

    static void runLagrange() throws IOException {

        System.out.println("--- LAGRANGE ---");

        Lagrange lagrange = new Lagrange();
        double output = lagrange.calculate();
        double outputSin = lagrange.calculateSin();
        FileSaver.saveToFile("outcometest3", lagrange);
        System.out.println(output);
        System.out.println(outputSin);
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

    static void runDoolittle() {
        double[][] a = new double[][]{
            {60,30,20},
            {30,20,15},
            {20,15,12},
        };
        double[][] b = new double[][]{
                {3,0,1},
                {0,-1,3},
                {1,3,0},
        };
        double[][] c = new double[][]{
                {4,1/2,1},
                {1/2,17,16,1/4},
                {1,1/4,33/64}
        };
        double[][] d = new double[][]{
                {2,1,-2},
                {4,2,-1},
                {6,3,11}
        };

        Matrix.print(Matrix.DoolittleAlgorithm(a));
        Matrix.print(Matrix.DoolittleAlgorithm(b));
        Matrix.print(Matrix.DoolittleAlgorithm(c));
        Matrix.print(Matrix.DoolittleAlgorithm(d));

    }

    static void runIntegration(){

        System.out.println("--- Integration ---");
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

        System.out.println("--- GAUSS-LEGENDRE QUADRATURE ---");
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

    static void runGramSchmidt(){

        System.out.println("--- QR DECOMP, GRAM-SCHMIDT ---");
        double[][] A = new double[][]{
                {12,-51,4},
                {6, 167,-68},
                {-4,24,-41}
        };
        double[][] A2 = new double[][]{
                {0,1,1},
                {3,0,1},
                {4,1,3}
        };
        double[][] A3 = new double[][]{
                {2,0,2},
                {1,3,1},
                {2,2,3}
        };
        Orthogonalization gr = new Orthogonalization();
        gr.QRDecomposition(A);
        gr.QRDecomposition(A2);
        gr.QRDecomposition(A3);

        ArrayList<double[]> Am = new ArrayList<double[]>();
        Am.add(new double[]{12,-51,4});
        Am.add(new double[]{6, 167,-68});
        Am.add(new double[]{-4,24,-41});
        ArrayList<double[]> out =  gr.gramSchmidt(Am);
        for(int i = 0; i < out.size(); i++){
            System.out.println(Arrays.toString(out.get(i)));
        }
    }

    static void runApprox() {
        double[][] base = new double[5][5];
        Approx a = new Approx();

        Point2D[] point = new Point2D[5];

        point[0] = new Point2D(-1, 4.55);
        point[1] = new Point2D(-0.5, 2.25);
        point[2] = new Point2D(0, 1);
        point[3] = new Point2D(0.5, 0.02);
        point[4] = new Point2D(1, -1.47);
        /*point[0] = new Point2D(-1, -1);
        point[1] = new Point2D(-0.5, -0.5);
        point[2] = new Point2D(0, 0);
        point[3] = new Point2D(0.5, 0.5);
        point[4] = new Point2D(1, -1);*/
       /* point[0] = new Point2D(-1, 3);
        point[1] = new Point2D(-0.5, -1);
        point[2] = new Point2D(0, 0);
        point[3] = new Point2D(0.5, 5);
        point[4] = new Point2D(1, -7);*/
        double[] out =a.aproksymacja_wiel(base, point);

        System.out.println(Arrays.toString(out));
        for(int i = 0 ; i < out.length; i++){
            int power = out.length - (i + 1);
            if(out[i] < 0)
                System.out.print(" " + out[i] + "x^" + power);
            if(out[i] >= 0)
                System.out.println(" + " + out[i] + "x^" + power);
        }
        System.out.println();
    }
}
