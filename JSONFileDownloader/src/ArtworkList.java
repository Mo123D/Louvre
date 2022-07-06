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
        return artworks.get(index).getArkId();
    }

    public String getArtworkURL(int index) {
        return artworks.get(index).getURL();
    }
}
