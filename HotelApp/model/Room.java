package model;

public class Room implements IRoom {
    private final String roomNumber;
    private final Double roomPrice;
    private final RoomType roomType;

    public Room(String roomNumber, RoomType roomType, Double roomPrice) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return roomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", roomType=" + roomType +
                ", roomPrice=$" + roomPrice +
                ", isFree=" + isFree() +
                '}';
    }
}