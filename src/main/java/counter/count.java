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

    public static String countFrequencies(String fileName) throws FileNotFoundException {

        // Uploading file to ArrayList
        
        Scanner s = new Scanner(new File("upload-dir/" + fileName));
        ArrayList<String> wordSlist = new ArrayList<String>();
        while (s.hasNext()) {
            wordSlist.add(s.next());
        }
        s.close();

        
        // Counting words 
        
        Map<String, Integer> countedWords = new HashMap<String, Integer>();
        for (String i : wordSlist) {
            Integer j = countedWords.get(i);
            countedWords.put(i, (j == null) ? 1 : j + 1);
        }

        
        // Sorting 
        
        final Map<String, Integer> sortedByCount = sortByValue(countedWords);
        
        
        // Convert to JSON file

        Gson json = new Gson();

        String response = json.toJson(sortedByCount);
        
        // Returning response

        return response;
    }

}
