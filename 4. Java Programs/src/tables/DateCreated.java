package tables;

public class DateCreated {
    private DateCreated[] dateCreated;
    private String startYear;
    private String endYear;
    private String text;

    public DateCreated[] getDateCreated() {
        DateCreated[] defaultDateCreated = new DateCreated[0];
        if (dateCreated == null) {
            return defaultDateCreated;
        }
        return dateCreated;
    }
    public String getStartYear() {
        FixWasCreatedDataStartYear();
        return startYear;}
    public String getEndYear() {
        FixWasCreatedDataEndYear();
        return endYear;}
    public String getText() {
        FixWasCreatedDataEpoch();
        return text;}
    public String toString() {
        return "objects.DateCreated [startYear=" + getStartYear() + "]";
    }

        public DateCreated(String endYear, String startYear, String epoch){
        this.dateCreated = new DateCreated[0];
        this.startYear=startYear;
        this.endYear=endYear;

        this.text=epoch;
    }

    public DateCreated(DateCreated[] dateCreated, String endYear, String startYear, String epoch){
        this.startYear=startYear;
        this.endYear=endYear;

        this.text=epoch;
    }

    public void FixWasCreatedDataStartYear() {
        if (!(startYear.matches("-?\\d+"))) {
            startYear = "NULL";
        } else {
            startYear = "'" + startYear + "'";
        }
    }
    public void FixWasCreatedDataEndYear() {
        if (!(endYear.matches("-?\\d+"))) {
            endYear = "NULL";
        } else {
            endYear = "'" + endYear + "'";
        }
    }
    public void FixWasCreatedDataEpoch() {
        if (text.isEmpty()) {
            text = "NULL";
        } else if (startYear.contains("NULL") && endYear.contains("NULL")) {
            text = "NULL";
        } else {
            text = "'" + text + "'";
        }
    }
}
