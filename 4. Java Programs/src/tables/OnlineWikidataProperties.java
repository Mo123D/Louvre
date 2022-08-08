package tables;

import org.wikidata.wdtk.datamodel.interfaces.*;
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import java.io.IOException;

public class OnlineWikidataProperties {
    private String countryOfCitizenship;
    private String creationRole;
    private String genre;
    private String movement;
    //private String[] creationRoles;

    private EntityDocument entityDocument;
    private WikibaseDataFetcher wikidataDataFetcher;

    public OnlineWikidataProperties(String creatorWikiData) throws IOException, MediaWikiApiErrorException {
        wikidataDataFetcher = WikibaseDataFetcher.getWikidataDataFetcher();
        entityDocument = wikidataDataFetcher.getEntityDocument(creatorWikiData);

        creationRole = readCreationRole();
        countryOfCitizenship = readCountryOfCitizenship();
        genre = readGenre();
        movement = readMovement();
    }

    private String readCreationRole() throws IOException, MediaWikiApiErrorException {
        String creationRole="";
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
                return "NULL";
            }
        }
        String[] creationRoles = creationRole.split(";");

        if (creationRoles.length > 1) {
            creationRole = creationRoles[1];
        } else {
            creationRole = "NULL";
        }
        return creationRole;
    }


    private String readCountryOfCitizenship() throws IOException, MediaWikiApiErrorException {
        String countryOfCitizenship=null;
        try {
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

    public String getCreationRole() {
        return creationRole;
    }

    public String getCountryOfCitizenship()
    {
        return countryOfCitizenship;
    }

    public String getGenre()
    {
        return genre;
    }

    public String readGenre() throws IOException, MediaWikiApiErrorException {
        String genre=null;
            try {
                StatementGroup group = ((ItemDocument) entityDocument).findStatementGroup("P136");
                if(group == null) return "NULL,";
                for (Statement statement : group.getStatements()) {
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

    public String getMovement()
    {
        return movement;
    }

    public String readMovement() throws IOException, MediaWikiApiErrorException {
        String movement=null;
        try {
            StatementGroup group = ((ItemDocument) entityDocument).findStatementGroup("P135");
            if(group == null) return "NULL,";
            for (Statement statement : group.getStatements()) {
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
