package repositories;

import models.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VehicleRepository {

    private Map<Long,Vehicle> vehicles=new HashMap<>();
    int vehicleId=0;
    public Optional<Vehicle> findVehicleByNumber(String vehicleNumber) {
        for(Vehicle vehicle:vehicles.values()) {
            if(vehicle.getVehicleNumber().equals(vehicleNumber)) {
                return Optional.of(vehicle);
            }
        }
        return Optional.empty();
    }

    public Vehicle save(Vehicle vehicle) {
        vehicleId++;
        vehicle.setId((long)vehicleId);
        vehicles.put((long)vehicleId,vehicle);
        return vehicle;
    }
}
