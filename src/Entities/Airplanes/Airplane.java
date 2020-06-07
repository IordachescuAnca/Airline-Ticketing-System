package Entities.Airplanes;

public abstract class Airplane {
    protected Integer airplaneId;
    protected String name;
    protected String manufacturingCompany;
    protected Integer numberRows;
    protected Integer numberColumns;

    public Airplane(String name, String manufacturingCompany, Integer numberRows, Integer numberColumns) {
        this.name = name;
        this.manufacturingCompany = manufacturingCompany;
        this.numberRows = numberRows;
        this.numberColumns = numberColumns;
    }
    public abstract void generateSeats(Integer airplaneId);

    public String getName() {
        return name;
    }

    public String getManufacturingCompany() {
        return manufacturingCompany;
    }

    public Integer getNumberRows() {
        return numberRows;
    }

    public Integer getNumberColumns() {
        return numberColumns;
    }

    public Integer getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(Integer airplaneId) {
        this.airplaneId = airplaneId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturingCompany(String manufacturingCompany) {
        this.manufacturingCompany = manufacturingCompany;
    }

    public void setNumberRows(Integer numberRows) {
        this.numberRows = numberRows;
    }

    public void setNumberColumns(Integer numberColumns) {
        this.numberColumns = numberColumns;
    }
}

