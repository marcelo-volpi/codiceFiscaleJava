import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StatiEsteri {
    
    public static String getCountryCode(String pathStatiFile, String countryName) {
        Map<String, String> countryCode = new HashMap<>();
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(pathStatiFile))) {
            while ((line = br.readLine()) != null) {
                String[] countryCodeArray = line.split(cvsSplitBy);
                countryCode.put(countryCodeArray[1], countryCodeArray[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String country = countryName.toUpperCase();
        if (countryCode.containsKey(country)) {
            return countryCode.get(country);
        } else {
            return "Country not found.";
        }
    }
}
