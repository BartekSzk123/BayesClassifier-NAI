import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvFileLoader {

    public List<Observation> loadData(String fileName) {

        List<Observation> observations = new ArrayList<Observation>();
        String[] header = null;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            if((line = br.readLine()) != null){
                header = line.split(",");
            }

            if(header == null){
                throw new IllegalArgumentException("CSV file does not contain header");
            }

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];

                Map<String, String> features = new HashMap<>();

                for(int i = 1; i < parts.length; i++){
                    features.put(header[i - 1], parts[i]);
                }
                observations.add(new Observation(type,features));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return observations;
    }
}
