package tables;

public class Object {
    public String arkId;
    public String title;
    public String materialsAndTechniques;
    public String collection;
    public String objectType;
    public String room;
    private String roomNumber;
    private String roomWing;
    private String roomLevel;
    public String heldBy;
    public String placeOfCreation;
    private Creator[] creator;
    private DateCreated[] dateCreated;

    public Object(String arkId, String title, String materialsAndTechniques, String collection,
                  String objectType, String room, String heldBy, String placeOfCreation, Creator[] creator){
        this.arkId=arkId;
        this.title=title;
        this.materialsAndTechniques=materialsAndTechniques;
        this.collection=collection;
        this.objectType=objectType;
        this.room=room;
        this.heldBy=heldBy;
        this.placeOfCreation=placeOfCreation;
        this.creator=creator;
    }

    public String getArkId() {
        return arkId;
    }
    public String getTitle() {return title;}
    public String getMaterialsAndTechniques() { return materialsAndTechniques;}
    public String getCollection() { return collection;}
    public String getObjectType() {
        if(objectType.isEmpty()){
            return "NULL";
        } else
        return objectType;}
    public String[] getRoom(){
        String[] parts = room.split(", ");
        return parts;}
    public String getRoomNumber(){return roomNumber;}
    public String getRoomWing(){return roomWing;}
    public String getRoomLevel(){return roomLevel;}
    public String getHeldBy() {return heldBy;}
    public String getPlaceOfCreation() {return placeOfCreation;}
    public Creator[] getCreator() {return creator;}
    public DateCreated[] getDateCreated() {return dateCreated;}

}

