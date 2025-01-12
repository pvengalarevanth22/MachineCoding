package models;

public class Vehicle extends BaseModel{
    private String VehicleNumber;
    private VehicleType VehicleType;
    private String nameOfOwner;

    public String getVehicleNumber() {
        return VehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        VehicleNumber = vehicleNumber;
    }

    public models.VehicleType getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(models.VehicleType vehicleType) {
        VehicleType = vehicleType;
    }

    public String getNameOfOwner() {
        return nameOfOwner;
    }

    public void setNameOfOwner(String nameOfOwner) {
        this.nameOfOwner = nameOfOwner;
    }
}
