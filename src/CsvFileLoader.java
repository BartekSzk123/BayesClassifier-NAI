import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvFileLoader {

    public List<Observation> loadData(String fileName, boolean hasLabel) {

        List<Observation> observations = new ArrayList<>();
        String[] header = null;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            if ((line = br.readLine()) != null) {
                header = line.split(";");
            }

            if (header == null) {
                throw new IllegalArgumentException("CSV file does not contain header");
            }

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                Map<String, String> features = new HashMap<>();
                String type = null;

                if (hasLabel) {
                    // ostatnia kolumna to etykieta (Akceptacja)
                    type = parts[parts.length - 1].trim();
                    for (int i = 0; i < parts.length - 1; i++) {
                        features.put(header[i].trim(), parts[i].trim());
                    }
                } else {
                    // brak etykiety - wszystkie kolumny to cechy
                    type = "?";
                    for (int i = 0; i < parts.length; i++) {
                        features.put(header[i].trim(), parts[i].trim());
                    }
                }

                observations.add(new Observation(type, features));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return observations;
    }
}