import java.io.*;
import java.util.*;

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
                        unsortWords.put(word.toString(), (unsortWords.get(word.toString())+1));
                    } else {
                        unsortWords.put(word.toString(), 1);
                    }
                    word.delete(0, word.length());
                    ++count;
                } else if ((i+1 == line.length()) & Character.isLetterOrDigit(line.charAt(i))) {
                    word.append(line.charAt(i));
                    if (unsortWords.containsKey(word.toString())) {
                        unsortWords.put(word.toString(), (unsortWords.get(word.toString())+1));
                    } else {
                        unsortWords.put(word.toString(), 1);
                    }
                    word.delete(0, word.length());
                    ++count;
                }else {
                    word.append(line.charAt(i));
                }
            }
        }
        ArrayList <Map.Entry<String, Integer>> words = new ArrayList<>();
        for(Map.Entry<String, Integer> e: unsortWords.entrySet()) {
            words.add(e);
        }
        Comparator<Map.Entry<String, Integer>> myComparator = new Comparator<>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                        Integer int1 = e1.getValue();
                        Integer int2 = e2.getValue();
                        return int2.compareTo(int1);
                    }
                };

        Collections.sort (words, myComparator);
        writeData(words);
    }


    public void writeData(List <Map.Entry<String, Integer>> list)  {
        FileWriter writer = null;
         try {
             writer = new FileWriter("C:\\Users\\Darya\\IdeaProjects\\lab1Java\\src\\out.csv");
             for (Map.Entry<String, Integer> e: list) {
                 writer.write(e.getKey() + "," + e.getValue() + "," + e.getValue()*100/count + "%\n");
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
