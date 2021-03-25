package filesController;

import interpolation.Lagrange;

import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;

public class FileSaver {

    /**
     * saves outcome to file in resource folder
     * @param fileName - name of *.txt file
     */
    public static void saveToFile(String fileName, Lagrange lagrange) throws IOException {

        FileWriter writer = new FileWriter("src/main/resources/outcomeLagrange.txt", true);
        Object[] params = new Object[]{
                Arrays.toString(lagrange.getX()),
                Arrays.toString(lagrange.getY()),
                lagrange.getXp(),
                lagrange.getOutcome()
        };

        String msg = MessageFormat.format(
                "X: {0}\n" +
                        "Y: {1}\n" +
                        "Calculated for X = {2}\t" +
                        "Outcome: {3}\n\n ", params);

        writer.write(msg);
        writer.flush();
        writer.close();
    }
}
