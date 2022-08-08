import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import objects.Creator;
import objects.DateCreated;
import objects.Dates;
import objects.Object;
import org.wikidata.wdtk.datamodel.interfaces.*;
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Converter {
    private static String creatorLabel;
    private static String creatorBirth;
    private static String creatorDeath;
    public static String creatorWikidata;
    private static String startYear;
    private static String endYear;
    private static String epoch;
    private static String doubt;

    private static HashMap<String, String> fromCSV;


    public static void main(String[] args) throws FileNotFoundException, IOException, MediaWikiApiErrorException {
        File currentFile = null;
        try {
            File dataFile = new File("C:\\Users\\Marie\\IdeaProjects\\JSONToInsertValues\\data");
            File filesList[] = dataFile.listFiles();
            fromCSV = FromCSVFile.addFromCSVFile("C:\\Users\\Marie\\IdeaProjects\\JSONToInsertValues\\JSONFileURL.csv");


            for (int j = 0; j < filesList.length; j++) {
                File file = filesList[j];
                currentFile = file;
                Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
                Object objects = new Gson().fromJson(reader, new TypeToken<Object>() {
                }.getType());
                String roomNumber = "NULL";
                String roomWing = "NULL,";
                String roomLevel = "NULL,";
                if (objects.getRoom().length == 3) {
                    String roomNumberString = objects.getRoom()[0];
                    String roomWingString = objects.getRoom()[1];
                    String roomLevelString = objects.getRoom()[2];
                    if (roomNumberString.length() != 0) {
                        roomNumber = roomNumberString.split(" ")[1];
                        roomNumber = "'" + roomNumber + "'";
                        roomWing = roomWingString;
                        roomLevel = roomLevelString.split(" ")[1];
                    }
                }


//                System.out.println("INSERT INTO room(number, wing, level, museumId)" + "\nVALUES('" + roomNumber + "','" + roomWing + "','"
//                        + roomLevel + "','Q19675');\n");


//                System.out.println("INSERT INTO artwork(arkId, title, materialsAndTechniques, " +
//                        "collection, roomId)" + "\nVALUES('" + objects.getArkId() + "','" + fromCSV.get(objects.getArkId()) + "','"
//                        + objects.getMaterialsAndTechniques() + "','" + objects.getCollection() + "'," + roomNumber + ");\n");
//
//                for (DateCreated dateCreated : objects.getDateCreated()) {
//                    startYear = dateCreated.getStartYear();
//                    endYear = dateCreated.getEndYear();
//                    epoch = dateCreated.getText();
//                    doubt = dateCreated.getDoubt();
//                }
//                if (epoch.isEmpty()) {
//                    epoch = "NULL,";
//                } else {
//                    epoch = "'" + epoch + "',";
//                }
//                if (endYear.isEmpty() || endYear.contains("vers")) {
//                    endYear = "NULL";
//                } else {
//                    endYear = "'" + endYear + "'";
//                }

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

//                if (creatorDeath.isEmpty() || creatorDeath.contains("vers")) {
//                    creatorDeath = "NULL";
//                } else {
//                    creatorDeath = "'" + creatorDeath + "'";
//                }
//                if (creatorBirth.isEmpty() || creatorBirth.contains("après")) {
//                    creatorBirth = "NULL,";
//                } else {
//                    creatorBirth = "'" + creatorBirth + "',";
//                }
//
                String[] CreationRoles = OnlineWikidataProperties.getCreationRoles();
                String creationRole = "{";
                for (int i = 1; i < CreationRoles.length; i++) {
                    if (i != CreationRoles.length - 1) {
                        creationRole = creationRole + OnlineWikidataProperties.getCreationRoles()[i] + ",";
                    } else {
                        creationRole = creationRole + OnlineWikidataProperties.getCreationRoles()[i];
                    }
                }
                creationRole = creationRole + "}";
                System.out.println("INSERT INTO creator(id, name, countryOfCitizenship, creationRole, " +
                        "movement, genre, birth, death)" + "\nVALUES('"
                        + creatorWikidata + "','" + creatorLabel + "'," +
                        OnlineWikidataProperties.getCountryOfCitizenship() + "'" + creationRole + "'," +
                        OnlineWikidataProperties.getMovement() +
                        OnlineWikidataProperties.getGenre() + "'" +creatorBirth + "','" +
                        creatorDeath + "');\n");
            }
//            }

//
//                System.out.println("INSERT INTO wasCreated(artworkId, creatorId, startYear, endYear, epoch, doubt)"
//                        + "\nVALUES('" + objects.getArkId() + "','" + creatorWikidata + "','" + startYear + "'," + endYear + "," +
//                        epoch + doubt + ");\n");


//
//            WikibaseDataFetcher wikidataDataFetcher = WikibaseDataFetcher.getWikidataDataFetcher();
//            EntityDocument entityDocument = wikidataDataFetcher.getEntityDocument("Q587078");
//            String creationRole = "{";
//
//            for (int i = 0; i < OnlineWikidataProperties.getCreationRoles().length ; i++) {
//                creationRole = creationRole + OnlineWikidataProperties.getCreationRoles()[i] + ",";
//            }
//            creationRole = creationRole + "}";
//            System.out.println(creationRole);
        }
        finally{
                String roomNumber = "NULL";
                String roomWing = "NULL,";
                String roomLevel = "NULL,";
                System.out.println(currentFile.getAbsolutePath());
            }
//        catch(Exception ex){
//            System.out.println(currentFile.getAbsolutePath());
//            ex.printStackTrace();
        }
    }

