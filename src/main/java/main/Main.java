package main;

import filesController.FileSaver;
import interpolation.Lagrange;
import interpolation.Newton;
import setOfEquations.Matrix;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    //TODO: multithreading

    public static void main(String[] args) throws IOException {
        boolean running = true;
        while (running) {
            System.out.print("0. Exit\n" +
                    "1. Lagrange\n" +
                    "2. Newton\n" +
                    "3. Gauss elimination\n" +
                    "4. Gauss Croute elimination\n" +
                    "Choice: ");
            Scanner program = new Scanner(System.in);
            int choice = program.nextInt();

            switch (choice){
                case 0:
                    running = false;
                    break;
                case 1:
                    runLagrange();
                    break;
                case 2:
                    runNewton();
                    break;
                case 3:
                    runGauss();
                    break;
                case 4:
                    runGaussCroute();
                    break;
                default:
                    running = false;
                    break;
            }
        }

    }

    public static void runLagrange() throws IOException {
        System.out.println("--- LAGRANGE ---");

        Lagrange lagrange = new Lagrange();
        //double output = lagrange.calculate();
        double output = lagrange.calculateSin();
        FileSaver.saveToFile("outcometest3", lagrange);
        System.out.println(output);
    }

    public static void runNewton(){
        System.out.println("--- NEWTON ---");

        Newton newton = new Newton();
        //newton.calculate();
        newton.calculateSin();
    }

    public static void runGauss(){
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

    public static void runGaussCroute(){
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
}
