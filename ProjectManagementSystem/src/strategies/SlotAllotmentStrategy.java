package strategies;

import models.Gate;
import models.ParkingSpot;
import models.VehicleType;

public interface SlotAllotmentStrategy {
    public ParkingSpot getSlot(VehicleType vehicleType, Gate gate);
}
