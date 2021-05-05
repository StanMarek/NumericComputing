package interpolation;

import org.decimal4j.util.DoubleRounder;

import java.util.ArrayList;

public class Orthogonalization {

    private double[][] A;
    private double[][] Q;
    private double[][] R;

    public void QRDecomposition(double[][] a) {
        this.A = a;
        printMatrix(A, "A");
        double[][] r = new double[a.length][a.length];
        double[][] q = new double[a.length][a.length];
        int m = a.length;

        for (int k = 0; k < m; k++) {
            r[k][k] = 0;
            for (int i = 0; i < m; i++)
                r[k][k] = r[k][k] + a[i][k] * a[i][k];
            r[k][k] = Math.sqrt(r[k][k]);
            for (int i = 0; i < 3; i++)
            {
                q[i][k] = a[i][k] / r[k][k];
            }
            for (int j = k + 1; j < m; j++)
            {
                r[k][j] = 0;
                for (int i = 0; i < m; i++)
                    r[k][j] += q[i][k] * a[i][j];
                for (int i = 0; i < m; i++)
                    a[i][j] = a[i][j] - r[k][j] * q[i][k];

            }
        }
        this.Q = q;
        this.R = r;
        printMatrix(Q, "Q");
        printMatrix(R, "R");
    }

    public double threePartRule(double[] vector, int x, int degree) {
        if(degree == -1)
            return 0;
        if(degree == 0)
            return 0;
        return alpha(vector, degree, degree-1, x);
    }

    private double alpha(double[] vector, int x, int m, int n) {
        double[] scalar = new double[vector.length];
        for(int i = 0; i < vector.length; i++){
            scalar[i] = x * vector[i];
        }
        //return scalarProd(scalar, vector[n])/(Math.sqrt(scalarProd(vector[n], vector[n])));
        return 0;
    }

    private double scalarProd(double[] vec1, double[] vec2) {
        double scalar = 0;
        for(int i = 0; i < vec2.length; i++){
            scalar += vec1[i] * vec2[i];
        }
        return scalar;
    }

    public static void printMatrix(double[][] mat, String matName) {
        System.out.println("Matrix " + matName);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(DoubleRounder.round(mat[i][j], 4) + ",\s");
            }
            System.out.println();
        }
    }

    public ArrayList<double[]> gramSchmidt(ArrayList<double[]> array)
    {
        ArrayList< double[] > finalArray= new ArrayList<>();

        finalArray.add(multiplyScalarPerVector(1/(calculateVectorLength(array.get(0))), array.get(0)));

        for(int i=1; i<array .size(); i++)
        {
            double[] newVector= substractVectors(array .get(i), proyection(finalArray.get(i-1),array .get(i)));
            for(int e=i-1;e>0;e--)
            {
                newVector= substractVectors(newVector, proyection(finalArray.get(e-1),array .get(i)));
            }
            newVector= multiplyScalarPerVector(1/(calculateVectorLength(newVector)), newVector);
            finalArray.add(newVector);
        }
        return finalArray;
    }

    public double[] proyection(double[] array1, double[] array2)
    {
        double dotProductResult= dotProduct(array1,array2);
        double[] finalVector= multiplyScalarPerVector(dotProductResult, array1);
        return finalVector;
    }

    public double dotProduct(double[] vector1, double[] vector2)
    {
        double result = 0;
        for(int i=0; i<vector1.length; i++)
        {
            result +=vector1[i]*vector2[i];
        }
        return result ;
    }

    public double[] multiplyScalarPerVector(double scalar, double[] vector)
    {
        double[] newVector = new double[vector.length];
        for(int i=0; i<vector.length; i++)
        {
            newVector[i] = scalar*vector[i];
        }
        return newVector;
    }

    public double[] substractVectors(double[] vector1, double[] vector2)
    {
        double[] finalVector= new double[vector1.length];
        for(int i=0; i<vector1.length; i++)
        {
            finalVector[i] = vector1[i] - vector2[i];
        }
        return finalVector;
    }

    public double calculateVectorLength(double[] vector)
    {
        double result = 0;
        for(int i=0; i<vector.length; i++)
        {
            result +=Math.pow(vector[i], 2);
        }
        return Math.sqrt(result );
    }
}