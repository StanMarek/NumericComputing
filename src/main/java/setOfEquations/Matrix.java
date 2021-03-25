package setOfEquations;

import java.util.Arrays;
import java.util.Collections;

public class Matrix {

    private double [][] A;
    private double [] B;
    private double [][] AB;
    private double [] X;

    public Matrix(int n, char x){
        if(x == 'g')
            initializeGauss();
        else if(x == 'c')
            initializeGaussCroute();
        this.X = new double[n];
    }

    private void initializeGauss(){

        this.A = new double[][]{
                {6,-2,	2,	4},
                {12,	-8,	6,	10},
                {3,	-13,	9,	3},
                {-6,	4,	1,	-18}
        };
        this.B = new double[]{
                12,
                34,
                27,
                -38
        };
        this.AB = new double[][]{
                {6,-2,	2,	4, 12},
                {12,	-8,	6,	10, 34},
                {3,	-13,	9,	3, 27},
                {-6,	4,	1,	-18, -38}
        };
    }

    private void initializeGaussCroute(){

        this.AB = new double[][]{
                {0, 2, 3, 4, 49},
                {1, 0, 3, 4, 45},
                {1, 2, 0, 4, 36},
                {1, 2, 3, 0, 23}
        };
    }

    public boolean gaussElimination() {

        final double eps = 1e-12;
        int n = X.length;
        double m;
        double s;

        for (int i = 0; i < n - 1; i++){
            for(int j = i+1; j < n; j++) {
                if (Math.abs(AB[i][i]) < eps)
                    return false;
                m = -AB[j][i] / AB[i][i];
                for(int k = i + 1; k <= n; k++)
                    AB[j][k] += m*AB[i][k];
            }
        }

        for(int i = n - 1; i>= 0; i--){
            s = AB[i][n];
            for(int j = n - 1; j >= i + 1; j--)
                s -= AB[i][j]* X[j];
            X[i] = s/AB[i][i];
        }

        return true;
    }

    public boolean gaussCrouteElimination(){

        final double eps = 1e-12;
        int n = X.length;
        int [] W = new int [n+1];

        for(int i = 0; i <= n; i++)
            W[i] = i;

        double m;
        double s;

        for(int i = 0; i < n-1; i++){
            int k = i;
            for(int j = i +1; j < n; j++)
                if(Math.abs(AB[i][W[k]]) < Math.abs(AB[i][W[j]]))
                    k = j;
            swap(W, k, i);
            for(int j = i +1; j <n; j++){
                if(Math.abs(AB[i][W[i]]) < eps)
                    return false;
                m = -AB[j][W[i]]/AB[i][W[i]];
                for(k = i+1; k<=n; k++){
                    AB[j][W[k]] += m*AB[i][W[k]];
                }
            }
        }

        for(int i = n - 1; i >= 0; i--){
            if(Math.abs(AB[i][W[i]]) < eps)
                return false;
            s = AB[i][n];
            for(int j = n -1; j >= i+1; j--)
                s-= AB[i][W[j]]*X[W[j]];
            X[W[i]] = s/AB[i][W[i]];
        }
        return true;
    }

    public static final void swap (int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public double[] getX() {
        return X;
    }
}
