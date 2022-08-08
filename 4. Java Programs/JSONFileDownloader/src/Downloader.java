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
            FileOutputStream fileOS = new FileOutputStream("C:\\Users\\Marie\\Desktop\\JSONFiles\\"+ artworkList.getArtworkArkId(i) + ".json");
            URL url = new URL(artworkList.getArtworkURL(i)+".json");
            openStream(url, fileOS);
        }
    }
}
