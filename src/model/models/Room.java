package model.models;

public class Room {
    private int roomNumber;
    private int rows;
    private int seatsInRow;

    public Room(int roomNumber, int rows, int seatsInRow) {
        this.roomNumber = roomNumber;
        this.rows = rows;
        this.seatsInRow = seatsInRow;
    }

    @Override
    public String toString() {
        return String.valueOf(roomNumber);
    }


    public int getRows() {
        return rows;
    }

    public int getSeatsInRow() {
        return seatsInRow;
    }

}
