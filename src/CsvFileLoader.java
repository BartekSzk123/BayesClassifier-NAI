import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileLoader {

    public static List<Observation> loadFile(String fileName) {

        List<Observation> observations = new ArrayList<>();
        String[] parts;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                parts = line.split(",");
                String[] attributes = new String[parts.length];
                for (int i = 1; i < parts.length; i++) {
                    attributes[i - 1] = parts[i];
                }
                observations.add(new Observation(parts[0], attributes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return observations;
    }
}
