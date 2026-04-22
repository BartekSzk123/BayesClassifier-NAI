import java.util.*;

public class BayesClassifer {

    private final List<Observation> observations;

    public BayesClassifer(List<Observation> observations) {
        this.observations = observations;
    }

    public Map<String, Integer> countClasses() {
        Map<String, Integer> classificationCount = new HashMap<>();
        for (Observation obs : observations) {
            classificationCount.put(
                    obs.getType(),
                    classificationCount.getOrDefault(obs.getType(), 0) + 1);
        }
        return classificationCount;
    }

    public int countFeature(String type, String feature, String value) {
        int count = 0;
        for (Observation observation : observations) {
            if (!observation.getType().equals(type)) continue;
            String val = observation.getAttributes().get(feature);
            if (val != null && val.trim().equals(value.trim())) count++;
        }
        return count;
    }

    public int countPossibleAttributes(String feature) {
        Set<String> possibleAttributes = new HashSet<>();
        for (Observation observation : observations) {
            String val = observation.getAttributes().get(feature);
            if (val != null) possibleAttributes.add(val.trim());
        }
        return possibleAttributes.size();
    }

    public List<String> classifyTest(List<Observation> observationsTest) {
        List<String> classifications = new ArrayList<>();
        for (Observation observation : observationsTest) {
            classifications.add(classify(observation));
        }
        return classifications;
    }

    public String classify(Observation observation) {
        Map<String, Integer> classificationCount = countClasses();
        int total = observations.size();

        String bestClass = null;
        double bestProb = -1;

        for (String classification : classificationCount.keySet()) {
            int classTotal = classificationCount.get(classification);

            double p = (double) classTotal / total;

            for (String feature : observation.getAttributes().keySet()) {
                String val = observation.getAttributes().get(feature);
                int count = countFeature(classification, feature, val);
                int k = countPossibleAttributes(feature);
                p *= (count + 1.0) / (classTotal + k);
            }

            if (p > bestProb) {
                bestClass = classification;
                bestProb = p;
            }
        }
        return bestClass;
    }
}