package tables;

public class Room {
    private String roomNumber = "NULL";
    private String roomWing = "NULL";
    private String roomLevel = "NULL";

    public Room(Object object)
    {
        if (object.getRoom().length == 3) {
            String roomNumberString = object.getRoom()[0];
            String roomWingString = object.getRoom()[1];
            String roomLevelString = object.getRoom()[2];
            if (roomNumberString.length() != 0) {
                roomNumber = "'" + roomNumberString.split(" ")[1] + "'";
                roomWing = "'" + roomWingString + "'";
                roomLevel = "'" + roomLevelString.split(" ")[1] + "'";
            }
        }
    }


    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomWing() {
        return roomWing;
    }

    public String getRoomLevel() {
        return roomLevel;
    }
}
