import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;
import tables.Object;
import tables.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InsertQueries {
    private static String creatorLabel;
    private static String creatorBirth;
    private static String creatorDeath;
    public static String creatorWikidata;
    private static String startYear;
    private static String endYear;
    private static String epoch;
    private static String creationRole;

    private static HashMap<String, String> fromCSV;
    private static List<String> printedCreatorIds = new ArrayList<>();
    private static int anonymousCounter = 1;
    private static int anonymousCounterWasCreated = 1;
    private static int birthYear;
    private static int deathYear;

    public static void main(String[] args) throws FileNotFoundException, IOException, MediaWikiApiErrorException, InterruptedException {
        File currentFile = null;
        try {
            //READING JSON AND CSV DATA
            File dataFile = new File("data");
            File filesList[] = dataFile.listFiles();
            fromCSV = FromCSVFile.addFromCSVFile("JSONFileURL.csv");

            //ITERATING THROUGH JSON FILES
            for (int j = 0; j < filesList.length; j++) {
                File file = filesList[j];
                currentFile = file;
                Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
                Object objects = new Gson().fromJson(reader, new TypeToken<Object>() {
                }.getType());


                //ROOM
                Room room = new Room(objects);

                //ITERATING THROUGH CREATOR
                String countryOfCitizenship = "NULL";
                String movement = "NULL";
                String genre = "NULL";
                int counter = 0;
                for (Creator creator : objects.getCreator()) {

                    if (!(creator.getLabel().isBlank())) {
                        creatorLabel = creator.getLabel();
                    }
                    if (!(creator.getWikidata().isBlank())) {
                        creatorWikidata = creator.getWikidata();
                    }

                    for (Dates dates : creator.getDates()) {
                        if (counter < 2) {
                            if (counter == 0) {
                                creatorDeath = dates.getDate();
                            } else {
                                creatorBirth = dates.getDate();
                            }
                            counter++;
                        }
                    }
                }

                OnlineWikidataProperties wikiDataProps = new OnlineWikidataProperties(creatorWikidata);

                creationRole = wikiDataProps.getCreationRole();
                countryOfCitizenship = wikiDataProps.getCountryOfCitizenship();
                movement = wikiDataProps.getMovement();
                genre = wikiDataProps.getGenre();


                //ITERATING THROUGH DATECREATED (WASCREATED)
                for (DateCreated dateCreated : objects.getDateCreated()) {
                    epoch="NULL";
                    startYear = dateCreated.getStartYear();
                    endYear = dateCreated.getEndYear();
                    epoch = dateCreated.getText();
                }


                //INSERT QUERIES PRINTING
                //ROOM
                System.out.println("INSERT INTO room(number, wing, level, museumId)" + "\nVALUES("
                        + room.getRoomNumber() + "," + room.getRoomWing() + "," + room.getRoomLevel() + ",'Q19675');\n");

                //ARTWORK
                System.out.println("INSERT INTO artwork(arkId, title, materialsAndTechniques, " +
                        "collection, roomId)" + "\nVALUES('" + objects.getArkId() + "','" + fromCSV.get(objects.getArkId()) + "','"
                        + objects.getMaterialsAndTechniques() + "','" + objects.getCollection() + "'," + room.getRoomNumber() + ");\n");

                //CREATOR
                //setting unique keys for anonymous creators
                if (creatorWikidata.contains("Q4233718")) {
                    System.out.print("INSERT INTO creator(id, name, countryOfCitizenship, creationRole, " +
                            "movement, genre, birth, death)\nVALUES('");
                    System.out.print(creatorWikidata + "-" + anonymousCounter + "','" + "anonymous" + "',");
                    anonymousCounter++;
                    System.out.print(countryOfCitizenship + "'" + creationRole + "'," + movement + genre + creatorBirth + ",");
                    System.out.print(creatorDeath + ");\n\n");
                }

                //avoiding redundancy of creators
                if (!(printedCreatorIds.contains(creatorWikidata))&&!(creatorWikidata.contains("Q4233718"))) {
                    System.out.print("INSERT INTO creator(id, name, countryOfCitizenship, creationRole, " +
                            "movement, genre, birth, death)\nVALUES('");
                    System.out.print(creatorWikidata + "','" + creatorLabel + "',");
                    System.out.print(countryOfCitizenship + "'" + creationRole + "'," + movement + genre + creatorBirth + ",");
                    System.out.print(creatorDeath + ");\n\n");
                    printedCreatorIds.add(creatorWikidata);
                }

                //WASCREATED
                //setting unique keys for anonymous creators
                if (creatorWikidata.contains("Q4233718")) {
                    System.out.println("INSERT INTO wasCreated(artworkId, creatorId, startYear, endYear, epoch)"
                            + "\nVALUES('" + objects.getArkId() + "','" + creatorWikidata + "-" + anonymousCounterWasCreated +
                            "'," + startYear + "," + endYear + "," + epoch + ");\n");
                    anonymousCounterWasCreated++;
                } else {
                    System.out.println("INSERT INTO wasCreated(artworkId, creatorId, startYear, endYear, epoch)"
                            + "\nVALUES('" + objects.getArkId() + "','" + creatorWikidata + "'," + startYear + "," + endYear + "," +
                            epoch + ");\n");
                }
            }
        } catch (Exception ex) {
            System.out.println(currentFile.getAbsolutePath());
            ex.printStackTrace();

        }
    }
}



