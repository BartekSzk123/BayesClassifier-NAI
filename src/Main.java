import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CsvFileLoader csvFileLoader = new CsvFileLoader();
        List<Observation> trainSet = csvFileLoader.loadData("bayes_train.csv", true);
        List<Observation> testSet = csvFileLoader.loadData("bayes_test.csv", false);
        BayesClassifer bayesClassifer = new BayesClassifer(trainSet);
        List<String> testSetClassified = bayesClassifer.classifyTest(testSet);

        for(String str : testSetClassified) {
            System.out.println(str);
        }

        Scanner scanner = new Scanner(System.in);
        Map<String, String> attributes = new HashMap<>();

        System.out.print("Podaj Klasę (mały/miejski/duży/kompakt) ");
        attributes.put("Klasa", scanner.nextLine());

        System.out.print("Podaj Cenę (niska/umiarkowana/wysoka): ");
        attributes.put("Cena", scanner.nextLine());

        System.out.print("Podaj Osiągi (słabe/przeciętne/dobre): ");
        attributes.put("Osiągi", scanner.nextLine());

        System.out.print("Podaj Niezawodność (mała/przeciętna/duża): ");
        attributes.put("Niezawodność", scanner.nextLine());

        Observation observation = new Observation("?", attributes);
        String result = bayesClassifer.classify(observation);

        System.out.println("Przewidywana akceptacja: " + result);

        scanner.close();
    }
}
