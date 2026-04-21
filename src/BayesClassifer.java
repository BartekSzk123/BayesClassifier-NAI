import java.util.*;

public class BayesClassifer {

    private final List<Observation> observations;

    public BayesClassifer(List<Observation> observations) {
        this.observations = observations;
    }

    public Map<String, Integer> countClasses() {
        Map<String, Integer> classificationCount = new HashMap<>();
        for (int i = 0; i < observations.size(); i++) {
            classificationCount.put(
                    observations.get(i).getType(),
                    classificationCount.getOrDefault(observations.get(i).getType(), 0) + 1);
        }
        return classificationCount;
    }

    public int countFeature(String type, String feature, String value) {
        int count = 0;
        for (Observation observation : observations) {
            if (!observation.getType().equals(type)) continue;

            String val = observation.getAttributes().get(feature);

            if (val.equals(value)) count++;
        }
        return count;
    }

    public int countPossibleAttributes(String feature) {
        Set<String> possibleAttributes = new HashSet<>();
        for (Observation observation : observations) {
            possibleAttributes.add(observation.getAttributes().get(feature));
        }
        return possibleAttributes.size();
    }

    public List<String> classifyTest(List<Observation> observationsTest) {

        List<String> classifications = new ArrayList<>();
        Map<String, Integer> classificationCount = countClasses();

        int total = observations.size();

        for (Observation observation : observationsTest) {

            String currentClass = null;
            double prob = -1;

            for (String classification : classificationCount.keySet()) {
                int classTotal = classificationCount.get(classification);
                double p = (double) classTotal / total;

                for (String feature : observation.getAttributes().keySet()) {

                    String val = observation.getAttributes().get(feature);
                    int count = countFeature(classification, feature, val);

                    if (count == 0) {
                        p *= ((count + 1.0) / (classTotal + countPossibleAttributes(feature)));
                    } else {
                        p *= ((double) count / classTotal);
                    }

                }

                if (p > prob) {
                    currentClass = classification;
                    prob = p;
                }

            }
            classifications.add(currentClass);
        }
        return classifications;
    }
}
