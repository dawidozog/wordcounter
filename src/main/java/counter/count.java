package counter;


import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.stream.Collectors;


  
public class count {

    public static Map<String, Integer> sortByValue(final Map<String, Integer> wordCounts) {

        return wordCounts.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static String countFrequencies(String x) throws FileNotFoundException {

        Scanner s = new Scanner(new File("upload-dir/" + x));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()) {
            list.add(s.next());
        }
        s.close();

        Map<String, Integer> hm = new HashMap<String, Integer>();
        for (String i : list) {
            Integer j = hm.get(i);
            hm.put(i, (j == null) ? 1 : j + 1);
        }

        final Map<String, Integer> sortedByCount = sortByValue(hm);

        Gson json = new Gson();

        String response = json.toJson(sortedByCount);

        return response;
    }

}
