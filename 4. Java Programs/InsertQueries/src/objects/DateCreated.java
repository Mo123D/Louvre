package objects;

public class DateCreated {
    private String startYear;
    private String endYear;
    private String text;
    private String doubt;
    public String getStartYear() { return startYear;}
    public String getEndYear() {return endYear;}
    public String getText() {return text;}
    public String getDoubt() {
        if (doubt.isEmpty()) {
            return "NULL";
        }
        return doubt;}
    public String toString() {
        return "objects.DateCreated [startYear=" + getStartYear() + "]";
    }
}
