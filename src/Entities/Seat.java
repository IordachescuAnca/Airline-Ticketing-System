package Entities;

public class Seat {
    private Character column;
    private Integer row;
    private Boolean available;
    private Character type;
    private String AirplaneID;

    public Seat(Character column, Integer row, Boolean available, Character type, String airplaneID) {
        this.column = column;
        this.row = row;
        this.available = available;
        this.type = type;
        this.AirplaneID = airplaneID;
    }

    public Character getColumn() {
        return column;
    }

    public void setColumn(Character column) {
        this.column = column;
    }

    public Integer getRow() {
        return row;
    }

    public String getAirplaneID() {
        return AirplaneID;
    }

    public void setAirplaneID(String airplaneID) {
        AirplaneID = airplaneID;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }
}

