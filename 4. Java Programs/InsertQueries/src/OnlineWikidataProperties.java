import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;
import java.io.IOException;

import org.wikidata.wdtk.datamodel.interfaces.*;
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

public class OnlineWikidataProperties {
    private static String countryOfCitizenship;
    private static String creationRole;
    private static String genre;
    private static String movement;
    private static String[] creationRoles;

    public static String[] getCreationRoles() throws IOException, MediaWikiApiErrorException {
        WikibaseDataFetcher wikidataDataFetcher = WikibaseDataFetcher.getWikidataDataFetcher();
        EntityDocument entityDocument = wikidataDataFetcher.getEntityDocument(Converter.creatorWikidata);

            creationRole="";
            String[] creationNull =  new String[]{""};
            try {
                for (Statement statement : ((ItemDocument) entityDocument).findStatementGroup("P106").getStatements()) {
                    Value value = statement.getValue();
                    if (value instanceof ItemIdValue) {
                        ItemDocument depict = (ItemDocument) wikidataDataFetcher.getEntityDocument(((ItemIdValue) value).getId());
                        creationRole = creationRole + ";" + depict.getLabels().get("en").getText();
                    }
                }
            } finally {
                if(creationRole==""){
                    return creationNull;
                }
            }
            String[] creationRoles;
            creationRoles = creationRole.split(";");
            return creationRoles;
    }


    public static String getCountryOfCitizenship() throws IOException, MediaWikiApiErrorException {
        WikibaseDataFetcher wikidataDataFetcher = WikibaseDataFetcher.getWikidataDataFetcher();
        EntityDocument entityDocument = wikidataDataFetcher.getEntityDocument(Converter.creatorWikidata);

        try {
            countryOfCitizenship=null;
            for (Statement statement : ((ItemDocument) entityDocument).findStatementGroup("P27").getStatements()) {
                Value value = statement.getValue();
                if (value instanceof ItemIdValue) {
                    ItemDocument depict = (ItemDocument) wikidataDataFetcher.getEntityDocument(((ItemIdValue) value).getId());
                    countryOfCitizenship = depict.getLabels().get("en").getText();
                }
            }
            return "'" + countryOfCitizenship + "',";

        } finally {
            if(countryOfCitizenship==null){
                return "NULL,";
            }

        }
    }



    public static String getGenre() throws IOException, MediaWikiApiErrorException {
        WikibaseDataFetcher wikidataDataFetcher = WikibaseDataFetcher.getWikidataDataFetcher();
        EntityDocument entityDocument = wikidataDataFetcher.getEntityDocument(Converter.creatorWikidata);

//        if ((!(Converter.creatorWikidata.contains("Q4233718"))) && (!(Converter.creatorWikidata.contains("Q1384115"))) && (!(Converter.creatorWikidata.contains("Q1358136")))) {
//            System.out.println("test");
            try {
                genre=null;
                for (Statement statement : ((ItemDocument) entityDocument).findStatementGroup("P136").getStatements()) {
                Value value = statement.getValue();
                if (value instanceof ItemIdValue) {
                    ItemDocument depict = (ItemDocument) wikidataDataFetcher.getEntityDocument(((ItemIdValue) value).getId());
                    genre = depict.getLabels().get("en").getText();
                }
            }
            return "'" + genre + "',";
            }
            finally {
                if(genre ==null) {
                    return "NULL,";
                }
            }
    }

    public static String getMovement() throws IOException, MediaWikiApiErrorException {
        WikibaseDataFetcher wikidataDataFetcher = WikibaseDataFetcher.getWikidataDataFetcher();
        EntityDocument entityDocument = wikidataDataFetcher.getEntityDocument(Converter.creatorWikidata);
//        System.out.println(Converter.creatorWikidata);

//        if ((!(Converter.creatorWikidata.contains("Q4233718")))&&(!(Converter.creatorWikidata.contains("Q388619")))
//                &&(!(Converter.creatorWikidata.contains("Q587078")))&&(!(Converter.creatorWikidata.contains("Q1358136")))
//                &&(!(Converter.creatorWikidata.contains("Q1384115")))&&(!(Converter.creatorWikidata.contains("Q207447")))
//                &&(!(Converter.creatorWikidata.contains("Q532906")))&&(!(Converter.creatorWikidata.contains("Q469056")))
//                &&(!(Converter.creatorWikidata.contains("Q1871782")))) {
//            System.out.println("test");
        try {
            movement=null;
            for (Statement statement : ((ItemDocument) entityDocument).findStatementGroup("P135").getStatements()) {
                Value value = statement.getValue();
                if (value instanceof ItemIdValue) {
                    ItemDocument depict = (ItemDocument) wikidataDataFetcher.getEntityDocument(((ItemIdValue) value).getId());
                    movement = depict.getLabels().get("en").getText();
                }
            }
            return "'" + movement + "',";
        }
        finally {
            if(movement==null) {
                return "NULL,";
            }
        }
    }
}
