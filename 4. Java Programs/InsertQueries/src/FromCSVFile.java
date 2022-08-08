import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class FromCSVFile {
    private String name;
    private String arkId;


    public static HashMap<String, String> addFromCSVFile(String filepath) {
        HashMap<String,String> fromCSV = new HashMap<String,String>();
        File file = new File(filepath);
        FileReader filereader = null;
        try {
            filereader = new FileReader(file);
        } catch (Exception e) {
            System.out.println("Datei nicht gefunden");
            System.exit(1);
        }
        BufferedReader reader = new BufferedReader(filereader);

        try {
            while (reader.ready()) {
                String line = reader.readLine();
                for(int i=1; (line = reader.readLine()) != null; i++)
                {
                    String[] parts = line.split(";");
                    String artworkName = parts[0];
                    String artworkArkId = parts[1];
                    fromCSV.put(artworkArkId,artworkName);
                }
            }

        } catch (Exception e) {
            System.out.println("Fehler beim Lesen der Datei.");
            e.printStackTrace();
        }
        return fromCSV;
    }

}
