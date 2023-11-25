package org.example;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.hibernate.HibernateUtils;
import org.example.props.DbFlywayMigration;
import org.example.service.TicketCrudService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.example.daoTest.PlanetServiceTest;
public class Main {

    public static void main(String[] args) {
        DbFlywayMigration.getInstance().migrateDatabase(); // виконуємо міграції

//        PlanetServiceTest planetServiceTest = new PlanetServiceTest();  // приклад екземпляру класу PlanetServiceTest
//        planetServiceTest.main(args);   // викличте його метод main

        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // створюємо нового клієнта
            Client newClient = new Client();
            newClient.setName("Venom7 junior");

            // створюємо планет
            Planet fromPlanet = new Planet();
            fromPlanet.setId("MERCURY1");

            Planet toPlanet = new Planet();
            toPlanet.setId("EARTH1");
            //toPlanet.setId("MERCURY1");

            // створюємо квиток та вводимо клієнта, планети
            TicketCrudService ticketService = new TicketCrudService();
            Ticket newTicket = ticketService.createTicket(newClient, fromPlanet, toPlanet);

            System.out.println("newTicket = " + newTicket);

            // завершення транзакції збереження змін в таблицях
            transaction.commit();

            System.out.println("Клієнт та квиток були успішно додані до бази даних.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtils.getInstance().closeSessionFactory();
        }
    }
}