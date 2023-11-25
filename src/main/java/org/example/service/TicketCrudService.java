package org.example.service;

import org.example.dao.TicketDao;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import java.util.List;

public class TicketCrudService {
    private TicketDao ticketDao = new TicketDao();
    private PlanetCrudService planetService = new PlanetCrudService();

    public Ticket createTicket(Client client, Planet planetFrom, Planet planetTo) {
        if (arePlanetsEqual(planetFrom, planetTo)) {
            throw new IllegalArgumentException("From and To planets cannot be the same.");
        }

        validatePlanetIdsExist(planetFrom.getId(), planetTo.getId());

        return ticketDao.createTicket(client, planetFrom, planetTo);
    }

    public Ticket updateTicketById(Long ticketId, Client newClient, Planet newPlanetFrom, Planet newPlanetTo) {
        if (arePlanetsEqual(newPlanetFrom, newPlanetTo)) {
            throw new IllegalArgumentException("From and To planets cannot be the same.");
        }

        validatePlanetIdsExist(newPlanetFrom.getId(), newPlanetTo.getId());

        return ticketDao.updateTicketById(ticketId, newClient, newPlanetFrom, newPlanetTo);
    }

    private void validatePlanetIdsExist(String planetFromId, String planetToId) {
        Planet planetFrom = planetService.getPlanetById(planetFromId);
        Planet planetTo = planetService.getPlanetById(planetToId);

        if (planetFrom == null || planetTo == null) {
            throw new IllegalArgumentException("One or both planets do not exist in the database.");
        }
    }

    private boolean arePlanetsEqual(Planet planet1, Planet planet2) {
        return planet1 != null && planet2 != null && planet1.getId().equals(planet2.getId());
    }

    public boolean deleteTicketById(Long ticketId) {
        return ticketDao.deleteTicketById(ticketId);
    }

    public List<Ticket> getAllTickets() {
        return ticketDao.getAllTickets();
    }
}