package filesController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

    public static double[] readArray(char choice) {
        Scanner scanner = null;
        File x_file = new File("src/main/resources/x_data.txt");
        File y_file = new File("src/main/resources/y_data.txt");
        ArrayList<Double> axis = new ArrayList<>();

        if(choice == 'x'){
            try {
                scanner = new Scanner(x_file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Double x;
            while(scanner.hasNextDouble()){
                x = scanner.nextDouble();
                if(scanner.hasNextDouble()) {
                    axis.add(x);
                }
            }
        }
        else if(choice == 'y'){
            try {
                scanner = new Scanner(y_file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Double y;
            while(scanner.hasNextDouble()){
                y = scanner.nextDouble();
                if(scanner.hasNextDouble()) {
                    axis.add(y);
                }
            }
        }
        double array[] = new double[axis.size()];
        for(int i = 0; i < axis.size(); i++)
            array[i] = (double) axis.get(i);
        return array;
    }
}
