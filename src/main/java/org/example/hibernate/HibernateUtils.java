package org.example.hibernate;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.props.PropertyReader;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static final HibernateUtils INSTANCE = new HibernateUtils();
    private final SessionFactory sessionFactory;
    private HibernateUtils() {
        this.sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
    }
    public static HibernateUtils getInstance() {return INSTANCE;}
    public SessionFactory getSessionFactory() {return this.sessionFactory;}
    public void closeSessionFactory() {
        if (this.sessionFactory != null) {
            this.sessionFactory.close();
        }
    }
}
