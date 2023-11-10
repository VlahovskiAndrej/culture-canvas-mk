import filters.ExcludeBoundaryStonesFilter;
import filters.ExcludeForeignPlacesFilter;
import filters.ExcludeNullNamesFilter;

import java.io.*;
import java.util.Scanner;

public class PipeAndFilterMain {

    public static void main(String[] args) throws FileNotFoundException {
        ClassLoader loader = PipeAndFilterMain.class.getClassLoader();
        InputStream inputStream = loader.getResourceAsStream("./resources/NasledstvaInput.csv");

        if (inputStream == null) {
            throw new IllegalArgumentException("File not found!'NasledstvaInput.csv' is not in the resources folder.");
        }
        Scanner scanner = new Scanner(inputStream);

        File outputFile = new File("src/resources/Nasledstva.csv");
        PrintWriter outputStreamName = new PrintWriter(new
                FileOutputStream(outputFile));

        Pipe pipe = new Pipe();
        ExcludeNullNamesFilter excludeNullNamesFilter = new ExcludeNullNamesFilter();
        ExcludeBoundaryStonesFilter excludeBoundaryStonesFilter = new ExcludeBoundaryStonesFilter();
        ExcludeForeignPlacesFilter excludeForeignPlacesFilter = new ExcludeForeignPlacesFilter();

        pipe.addFilter(excludeNullNamesFilter);
        pipe.addFilter(excludeBoundaryStonesFilter);
        pipe.addFilter(excludeForeignPlacesFilter);

        while (scanner.hasNextLine()) {
            String output = (String) pipe.runFilters(scanner.nextLine());
            if (output != null)
                if (scanner.hasNextLine()) {
                    outputStreamName.println(output);
                } else {
                    outputStreamName.print(output);
                }
        }
        outputStreamName.flush();
        outputStreamName.close();
    }
}
