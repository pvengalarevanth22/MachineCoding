package services;

import exceptions.GateNotFoundException;
import models.*;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import repositories.TicketRepository;
import repositories.VehicleRepository;
import strategies.SlotAllotmentStrategy;
import strategies.SlotAllotmentStrategyFactory;

import java.util.Date;
import java.util.Optional;

public class TicketService {

    GateRepository gateRepository;

    VehicleRepository vehicleRepository;

    ParkingLotRepository parkingLotRepository;

    TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository,TicketRepository ticketRepository, ParkingLotRepository parkingLotRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public Ticket issueTicket(String vehicleNumber, VehicleType vehicleType, String nameOfOwner,Long gateId) throws GateNotFoundException {

        //create a ticket object
        //Assign time
        //Assign spot
        //Save to Db
        //Return ticket

        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        //fetch gate based on gateId from Db
        Optional<Gate> optionalGate = gateRepository.getGateById(gateId);
        if (optionalGate.isEmpty()) {
            throw new GateNotFoundException();
        }
        Gate gate = optionalGate.get();
        ticket.setGate(gate);

        ticket.setOperator(gate.getOperator());

        //If Vehicle is found in db return that db otherwise create new vehicle in db
        Vehicle savedVehicle;
        Optional<Vehicle> OptionalVehicle = vehicleRepository.findVehicleByNumber(vehicleNumber);
        if (OptionalVehicle.isEmpty()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleType(vehicleType);
            vehicle.setNameOfOwner(nameOfOwner);
            vehicle.setVehicleNumber(vehicleNumber);
            savedVehicle = vehicleRepository.save(vehicle);
        } else {
            savedVehicle = OptionalVehicle.get();
        }
        ticket.setVehicle(savedVehicle);

            //get slotAllotment Type from Parking Repository
            SlotAllotmentStrategyType slotAllotmentStrategyType = parkingLotRepository.getParkingLotForGate(gate).getSlotAllotmentStrategyType();

            //get SlotAllotmentStrategy from factory
            SlotAllotmentStrategy slotAllotmentStrategy = SlotAllotmentStrategyFactory.getSlotAllotmentStrategy(slotAllotmentStrategyType);

            ticket.setParkingSpot(
                    slotAllotmentStrategy.getSlot(vehicleType, gate)
            );

            ticket.setTicketNumber("TICKET -" + ticket.getId());

            return ticketRepository.save(ticket);

    }
}
