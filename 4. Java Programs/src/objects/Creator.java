package objects;

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
}
