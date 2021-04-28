package interpolation;

import filesController.FileReader;

import java.util.Arrays;
import java.util.Scanner;

public class Lagrange {

    private static double[] x;
    private static double[] y;
    private static double outcome;
    private static double xp;

    public static double calculate() {

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
        System.out.println(Arrays.toString(x) + "\t" + Arrays.toString(y));
        System.out.print("Punkt do obliczenia: ");
        double XP = scanner.nextDouble();
        xp = XP;
        double p;
        for (int i = 0; i < x.length; i++) {
            p = 1.0;
            for (int j = 0; j < x.length; j++) {
                if (j != i)
                    p = p * ((xp - x[j]) / (x[i] - x[j]));
            }
            outcome += p * y[i];
        }
        return Math.round(outcome);
    }

    public static double calculateSin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Liczba punktow: ");
        int amountOfPoints = scanner.nextInt();
        x = new double[amountOfPoints];
        y = new double[amountOfPoints];
        System.out.println(Arrays.toString(x) + "\t" + Arrays.toString(y));
        System.out.print("Wynik to sin(x)\nPunkt do obliczenia: x = ");
        double XP = scanner.nextDouble();
        xp = XP;
        for (int i = 0; i < amountOfPoints; i++) {
            x[i] = Math.PI * 1/2 * i;
            y[i] = Math.sin(x[i]);
        }

        double p;
        for (int i = 0; i < x.length; i++) {
            p = 1.0;
            for (int j = 0; j < x.length; j++) {
                if (j != i)
                    p = p * ((xp - x[j]) / (x[i] - x[j]));
            }
            outcome += p * y[i];
        }
        return Math.round(outcome);
    }

    /*public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }

    public double getOutcome() {
        return outcome;
    }

    public double getXp() {
        return xp;
    }*/
}