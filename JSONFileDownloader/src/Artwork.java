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

    public String getArkId() {
        return arkId;
    }

    public String getURL() {return url;
    }

}
