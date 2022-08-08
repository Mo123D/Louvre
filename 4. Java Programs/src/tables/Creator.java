package tables;

public class Creator {
    private Dates[] dates;
    private String label;
    private String wikidata;

    public Dates[] getDates() {
        Dates[] defaultDate= new Dates[0];
        if(dates==null){
            return defaultDate;
        }
        return dates;}

    public String getLabel() {
        if(label.isEmpty()||label.contains(",")) {
            return label;
        } else {
            return "";
        }
    }
    public String getWikidata() {return wikidata;}


    public Creator(String label, String wikidata){
        this.dates = new Dates[0];
        this.label=label;
        this.wikidata=wikidata;
    }

    public Creator(Dates[] dates, String label, String wikidata){
        this.dates=dates;
        this.label=label;
        this.wikidata=wikidata;
    }

    public void FixCreatorData() {
        label = label.replaceAll("'", "´");

        String creatorDeath = "NULL";
        String creatorBirth = "NULL";
        int counter = 0;

        if(dates != null) {
            for (Dates dates : dates) {
                if (counter < 2) {
                    if (counter == 0) {
                        creatorDeath = dates.getDate();
                    } else {
                        creatorBirth = dates.getDate();
                    }
                    counter++;
                }
            }

            int birthYear = 0, deathYear = 0;

            if (creatorBirth.matches("-?\\d+")) {
                birthYear = Integer.parseInt(creatorBirth);
            }
            if (creatorDeath.matches("-?\\d+")) {
                deathYear = Integer.parseInt(creatorDeath);
            }
            if (birthYear > deathYear) {
                String swap = creatorDeath;
                creatorDeath = creatorBirth;
                creatorBirth = swap;
            }
            if (creatorDeath.isEmpty() || !(creatorDeath.matches("-?\\d+"))) {
                dates[0].setDate("NULL");

            } else {
                dates[0].setDate("'" + creatorDeath + "'");
            }
            if (creatorBirth.isEmpty() || !(creatorBirth.matches("-?\\d+"))) {
                dates[1].setDate("NULL");
            } else {
                dates[1].setDate("'" + creatorBirth + "'");
            }
        }
    }

}
