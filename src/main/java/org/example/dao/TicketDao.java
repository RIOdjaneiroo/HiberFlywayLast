package org.example.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.hibernate.HibernateUtils;

import java.time.LocalDateTime;
import java.util.List;

public class TicketDao {
    //ініціалізуємо SessionFactory, яка використовується для створення Session, необхідної для взаємодії з базою даних через Hibernate.
    private SessionFactory sessionFactory = HibernateUtils.getInstance().getSessionFactory();
    private ClientDao clientDao = new ClientDao();
    // ініціалізація обєктів ClientDao і PlanetDao, які використовуються для взаємодії з клієнтами і планетами в базі даних.
    private PlanetDao planetDao = new PlanetDao();
    public List<Ticket> getAllTickets() {
        try (Session session = sessionFactory.openSession()) {
            Query<Ticket> query = session.createQuery("FROM Ticket", Ticket.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error getting all tickets", e);
        }
    }
    public Ticket createTicket(Client client, Planet planetFrom, Planet planetTo) {
        try (Session session = sessionFactory.openSession()) {  // відкриваємо сесію
            Transaction tx = null; // ініціалізуємо Transaction, будем використовувати для управління транзакцією бази даних.
            try {
                tx = session.beginTransaction(); // початок транзакції

                Client persistedClient = clientDao.assertClient(client);// створюємо або отримуємо клієнта

                Planet persistedPlanetFrom = planetDao.findPlanetById(planetFrom.getId()); // для отримання планет за ідентифікаторами.
                Planet persistedPlanetTo = planetDao.findPlanetById(planetTo.getId());     // для отримання планет за ідентифікаторами.

                Ticket ticket = new Ticket();                 //створюємо квиток
                ticket.setCreatedAt(LocalDateTime.now());     //встановлюємо дату та часу створення.
                ticket.setClient(persistedClient);            //встановлення звязку між квитком, клієнтом та планетами.
                ticket.setFromPlanet(persistedPlanetFrom);
                ticket.setToPlanet(persistedPlanetTo);

                session.persist(ticket);                      // збереження квитка в БД
                tx.commit();                                  // закриття транзакції, збереження змін
                return ticket;                                // повернення створеного квитка
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();                            // коли щось пішло не так
                }
                e.printStackTrace();
                throw new RuntimeException("Error creating Ticket", e);
            }
        }
    }
    public boolean deleteTicketById(Long ticketId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Ticket ticket = session.get(Ticket.class, ticketId);
                if (ticket != null) {
                    session.remove(ticket);
                    tx.commit();
                    return true;
                } else {
                    tx.rollback();
                    return false;
                }
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
                throw new RuntimeException("Error deleting Ticket", e);
            }
        }
    }
    public Ticket updateTicketById(Long ticketId, Client newClient, Planet newPlanetFrom, Planet newPlanetTo) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Ticket ticket = session.get(Ticket.class, ticketId);

                if (ticket != null) {
                    // Оновлення полів квитка
                    ticket.setFromPlanet(newPlanetFrom);
                    ticket.setToPlanet(newPlanetTo);

                    // оновлюємо дату
                    ticket.setCreatedAt(LocalDateTime.now());

                    // Оновлення клієнта
                    ClientDao clientDao = new ClientDao();
                    Client persistedClient = clientDao.assertClient(newClient);
                    ticket.setClient(persistedClient);

                    session.merge(ticket); // Збереження оновленого об'єкта у базу даних
                    tx.commit();

                    return ticket;
                } else {
                    tx.rollback();
                    return null; // Якщо квиток із заданим ID не знайдено
                }
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
                throw new RuntimeException("Error updating Ticket", e);
            }
        }
    }

}

