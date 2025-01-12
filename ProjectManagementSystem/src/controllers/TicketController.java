package controllers;

import dtos.IssueTicketRequestDto;
import dtos.IssueTicketResponseDto;
import dtos.ResponseStatus;
import models.Ticket;
import services.TicketService;


public class TicketController {

    TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDto issueTicket(IssueTicketRequestDto request) {

        IssueTicketResponseDto issueTicketResponseDto = new IssueTicketResponseDto();

        Ticket ticket;

        try {
            ticket=ticketService.issueTicket(
                    request.getVehicleNumber(),
                    request.getVehicleType(),
                    request.getNameOfOwner(),
                    request.getGateId());
        }
        catch (Exception e) {
            issueTicketResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            issueTicketResponseDto.setErrorMessage(e.getMessage());
            return issueTicketResponseDto;
        }

        issueTicketResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        issueTicketResponseDto.setTicket(ticket);

        return issueTicketResponseDto;

    }
}
