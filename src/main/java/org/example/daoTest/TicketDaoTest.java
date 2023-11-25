package org.example.daoTest;
import org.example.dao.ClientDao;
import org.example.dao.PlanetDao;
import org.example.dao.TicketDao;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TicketDaoTest {

    public static void main(String[] args) {
        // cтворюємо клієнта та планети
        Client client = new Client();
        client.setName("KoKoJambo");

        Planet fromPlanet = new Planet();
        fromPlanet.setId("EARTH1");

        Planet toPlanet = new Planet();
        toPlanet.setId("MARS1");

        // cтворюємо ДАО для клієнта
        ClientDao clientDao = new ClientDao();

        // cтворюємо клієнта або отримайте існуючого
        Client persistedClient = clientDao.assertClient(client);

        // cтворюємо DAO для планети
        PlanetDao planetDao = new PlanetDao();

        // якщо ІД не вірний буде помилка
        Planet persistedPlanetFrom = planetDao.findPlanetById(fromPlanet.getId());
        Planet persistedPlanetTo = planetDao.findPlanetById(toPlanet.getId());

        // cтворюємо ДАО для квитків
        TicketDao ticketDao = new TicketDao();

        // створюємо квиток та додаємо його в базу даних
        Ticket ticket = ticketDao.createTicket(persistedClient, persistedPlanetFrom, persistedPlanetTo);

        // друкуємо квиток
        System.out.println("Ticket ID: " + ticket.getId());
        System.out.println("Client ID: " + ticket.getClient().getId()+ " Client: " + ticket.getClient().getName());
        System.out.println("From Planet_ID: " + ticket.getFromPlanet().getId() + " From Planet: " + ticket.getFromPlanet().getName());
        System.out.println("To Planet_ID: " + ticket.getToPlanet().getId() + " To Planet: " + ticket.getToPlanet().getName());
        System.out.println("Created At: " + ticket.getCreatedAt());


//        // проба видалити
//        Long ticketIdToDelete = 1L;
//        boolean delTicket = ticketDao.deleteTicketById(ticketIdToDelete);
//        System.out.println("Ticket deleted " + delTicket);

//        // oновлення квитка зa ідентифікатором
//        Long ticketIdToUpdate = 2L;
//        Ticket updatedTicket = ticketDao.updateTicketById(ticketIdToUpdate, client, fromPlanet, toPlanet);
//        if (updatedTicket != null) {
//            System.out.println("Updated Ticket: " + updatedTicket);
//        } else {
//            System.out.println("Ticket with ID " + ticketIdToUpdate + " not found.");
//        }


        // читаємо всі квитки
        List<Ticket> allClients = ticketDao.getAllTickets();
        System.out.println("All tickets:");
        for (Ticket ticket1 : allClients) {
            System.out.println(ticket1);
        }

    }
}
