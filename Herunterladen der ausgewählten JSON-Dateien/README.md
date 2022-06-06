# Herunterladen der ausgewählten JSON-Dateien

Ich habe einen Java-Code geschrieben, um die JSON-Dateien von Lanas Auswahl automatisch herunterzuladen, indem ich ihre Excel-Datei in CSV umwandle (Excel-Tabellen sind schrecklich für Datenbanken, sie werden normalerweise für Berechnungen verwendet) und sie so parse, dass ich nur die URL erhalte und sie automatisch in einen Ordner auf meinem Computer hochlade.
Ich habe also einen Ordner mit allen JSON-Dateien, die wir brauchen!

```java
public class Artwork {
    private String name;
    private String arkId;
    private String url;

    private Artwork(){
    }

    public static Artwork parseCSVLine(String line) {
        Artwork res = new Artwork();
        String[] parts = line.split(";");
        res.name = parts[0];
        res.arkId = parts[1];
        res.url = parts[2];
        return res;
    }

    public String getName() {
        return name;
    }

    public String getarkId() {
        return arkId;
    }

    public String geturl() {
        return url;
    }
}
```

```java
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class ArtworkList {
    private ArrayList<Artwork> artworks = new ArrayList<Artwork>();

    public ArtworkList() {
    }

    public void addArtwork(Artwork a) {
        artworks.add(a);
    }

    public void addFromCSVFile(String filepath) {
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
                    Artwork artwork = Artwork.parseCSVLine(line);
                    artworks.add(artwork);
                }
            }

        } catch (Exception e) {
            System.out.println("Fehler beim Lesen der Datei.");
            e.printStackTrace();
        }
    }

    public int getNumArtworks() {
        return artworks.size();
    }

    public String getArtworkArkId(int index) {
        return artworks.get(index).getarkId();
    }

    public String getArtworkURL(int index) {
        return artworks.get(index).geturl();
    }
}
```

```java
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public class Downloader {
    
    public static void openStream(URL url, FileOutputStream fileOS) throws IOException {
        ReadableByteChannel readChannel = Channels.newChannel(url.openStream());
        FileChannel writeChannel = fileOS.getChannel();
        writeChannel.transferFrom(readChannel, 0, Long.MAX_VALUE);
    }
    
    public static void main(String[] args) throws IOException {
        ArtworkList artworkList = new ArtworkList();
        artworkList.addFromCSVFile("C:\\Users\\Marie\\IdeaProjects\\JSONFileDownloader\\data\\JSONFileURL.csv");
        System.out.println(artworkList.getNumArtworks());
        String[] fileOutput = new String[artworkList.getNumArtworks()];
        System.out.println(fileOutput.length);
        for (int i=0; i< artworkList.getNumArtworks();i++) {
            System.out.println(artworkList.getArtworkArkId(i));
            fileOutput[i] = "C:\\Users\\Marie\\Documents\\Studium\\SoSe2022\\" +
                    "B22 Datenbanken\\PostgreSQL Datenbanken\\JSONDocumentation\\"+ artworkList.getArtworkArkId(i) + ".json";
            System.out.println(fileOutput[i]);

            FileOutputStream fileOS = new FileOutputStream("C:\\Users\\Marie\\Documents\\Studium\\SoSe2022\\" +
                    "B22 Datenbanken\\PostgreSQL Datenbanken\\JSONDocumentation\\"+ artworkList.getArtworkArkId(i) + ".json");
            URL url = new URL(artworkList.getArtworkURL(i));
            openStream(url, fileOS);
        }
    }
}
```

[JSONFileURL.csv](Herunterladen%20der%20ausgewa%CC%88hlten%20JSON-Dateien%205e94ff88bc96495ea310744000d3694b/JSONFileURL.csv)

[JSONDocumentation.zip](Herunterladen%20der%20ausgewa%CC%88hlten%20JSON-Dateien%205e94ff88bc96495ea310744000d3694b/JSONDocumentation.zip)

[JSONFileDownloader.zip](Herunterladen%20der%20ausgewa%CC%88hlten%20JSON-Dateien%205e94ff88bc96495ea310744000d3694b/JSONFileDownloader.zip)