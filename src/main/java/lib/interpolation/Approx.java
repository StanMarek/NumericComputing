package lib.interpolation;

public class Approx {

    //private  Point2D[] points;

    public  double[] aproksymacja_wiel(double[][] baza, Point2D[] points) {

        int k = baza.length;
        int n = points.length;
        double[][] macierz = new double[k][];
        for (int i = 0; i < k; i++)
        {
            macierz[i] = new double[k + 1];
            for (int j  = 0; j < k+1; j++)
            {
                macierz[i][j] = 0;
            }
        }
        for (int i = 0; i < k; i++)
        {
            for (int j = 0; j < k; j++)
            {
                for (int g = 0; g < n; g++)
                {
                    macierz[i][j] += Math.pow(points[g].x, i + j);
                    if (j == 0)
                    {
                        macierz[i][k] += Math.pow(points[g].x, i + j) * points[g].y;
                    }
                }
            }
        }
        double[] wynik = ElimGaussCrout(macierz);

        return wynik;
    }

    /*public void loadPoints() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("points: ");
        int amountOfPoints = scanner.nextInt();

        points = new Point2D[amountOfPoints];
        for(int i = 0; i < amountOfPoints; i++) {
            System.out.print(String.format("x{%d}: ", i + 1));
            double x = scanner.nextDouble();
            System.out.print(String.format("y{%d}: ", i + 1));
            double y = scanner.nextDouble();
            points[i] = new Point2D(x, y);
        }
    }
*/
    private static double[] ElimGaussCrout(double AB[][]) {

        int n = AB.length;
        double dokladnosc = 1e-15;
        double[] wynik = new double[n];
        int[] wektor = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            wektor[i] = i;
        }
        for (int i = 0; i < n - 1; i++) {
            int najwiekszy = i;
            boolean zmiana = false;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(AB[i][wektor[najwiekszy]]) < Math.abs(AB[i][wektor[j]])) {
                    najwiekszy = j;
                    zmiana = true;
                }
            }
            if (zmiana) {
                int pom = wektor[i];
                wektor[i] = wektor[najwiekszy];
                wektor[najwiekszy] = pom;
            }
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(AB[i][wektor[i]]) < dokladnosc) {
                    return null;
                }
                double dzielnik = AB[j][wektor[i]] / AB[i][wektor[i]];
                for (int k = i + 1; k < n + 1; k++) {
                    AB[j][wektor[k]] -= (AB[i][wektor[k]] * dzielnik);
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (Math.abs(AB[i][wektor[i]]) < dokladnosc)
                return null;
            for (int j = n - 1; j > i; j--) {
                AB[i][n] -= AB[i][wektor[j]] * wynik[wektor[j]];
            }
            wynik[wektor[i]] = AB[i][n] / AB[i][wektor[i]];

        }
        return wynik;
    }

    private double determinant(double[][] matrix) {
        if(matrix.length == 2) {
            return matrix[0][0]* matrix[1][1]- matrix[0][1]* matrix[1][0];
        }
        if(matrix.length== 3){
            double detSum = 0;
            for(int i = 0; i < 3; i++){
                double detDiagonal = 1;
                for(int j = 0; j < 3; j++)
                    detDiagonal *= matrix[j][(i+j)%3];
                detSum += detDiagonal;
            }
            for(int i = 0; i < 3; i++){
                double detDiagonal = 1;
                for(int j = 0; j < 3; j++)
                    detDiagonal *= matrix[2-j][(i+j)%3];
                detSum -= detDiagonal;
            }
            return detSum;
        }
        return 0.0;
    }

    private void swap (int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}
