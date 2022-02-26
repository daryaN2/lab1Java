import java.io.*;

public class Main {
    public static void main(String args[]) {
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream("C:\\Users\\Darya\\IdeaProjects\\lab1Java\\src\\in.txt"));
            BufferedReader breader = new BufferedReader(reader);
            Counter counter = new Counter();
            counter.Count(breader);
        }
        catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        finally {
            if (null != reader) {
                try {
                    reader.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}
