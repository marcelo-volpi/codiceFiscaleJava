import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProvinciaComune {
    
    public static String getComuneCode(String pathCommuniFile, String comuneName) {
        Map<String, String> comuneCode = new HashMap<>();
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(pathCommuniFile))) {
            while ((line = br.readLine()) != null) {
                String[] codigoComuneArray = line.split(cvsSplitBy);
                comuneCode.put(codigoComuneArray[1], codigoComuneArray[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String comune = comuneName.toUpperCase();
        if (comuneCode.containsKey(comune)) {
            return comuneCode.get(comune);
        } else {
            return "Comune not found.";
        }
    }
}
