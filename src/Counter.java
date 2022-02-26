import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Counter {
    private int count;

    public void Count (BufferedReader breader) throws IOException {
        Map <String, Integer> unsortWords = new HashMap <>();
        StringBuilder word = new StringBuilder();
        String line;
        while ((line = breader.readLine()) != null) {
            for (int i = 0; i < line.length(); ++i) {
                if (!Character.isLetterOrDigit(line.charAt(i))) {
                    if (unsortWords.containsKey(word.toString())) {
                        System.out.println("Repeat");
                        unsortWords.put(word.toString(), (unsortWords.get(word.toString())+1));
                    } else {
                        System.out.println("New");
                        unsortWords.put(word.toString(), 1);
                    }
                    System.out.println("Word " + word);
                    word.delete(0, word.length());
                    ++count;
                } else if ((i+1 == line.length()) & Character.isLetterOrDigit(line.charAt(i))) {
                    word.append(line.charAt(i));
                    if (unsortWords.containsKey(word.toString())) {
                        System.out.println("Repeat");
                        unsortWords.put(word.toString(), (unsortWords.get(word.toString())+1));
                    } else {
                        System.out.println("New");
                        unsortWords.put(word.toString(), 1);
                    }
                    System.out.println("Word " + word);
                    word.delete(0, word.length());
                    ++count;
                }else {
                    System.out.println("Symbol " + line.charAt(i));
                    word.append(line.charAt(i));
                }
            }
        }
        Map <String, Integer> words = new TreeMap<>(unsortWords);
        for (Map.Entry<String, Integer> entry : words.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ". Value: " + entry.getValue());
        }
        writeData(words);
    }


    public void writeData(Map<String, Integer> map)  {
        FileWriter writer = null;
         try {
             writer = new FileWriter("C:\\Users\\Darya\\IdeaProjects\\lab1Java\\src\\out.csv");
             for (Map.Entry<String, Integer> entry : map.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "," + entry.getValue()*100/count + "%\n");
             }
         }
         catch (IOException e) {
             System.err.println("Error while reading file: " + e.getLocalizedMessage());
         }
         finally {
             if (null != writer) {
                 try {
                     writer.close();
                 }
                 catch (IOException e) {
                     e.printStackTrace(System.err);
                 }
             }
         }
    }

}
