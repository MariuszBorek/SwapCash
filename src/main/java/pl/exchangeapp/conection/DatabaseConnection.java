package pl.exchangeapp.conection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.exchangeapp.entities.Account;
import pl.exchangeapp.entities.Address;
import pl.exchangeapp.entities.Customer;
import org.hibernate.cfg.Configuration;
import pl.exchangeapp.entities.PaymentTransaction;

import java.util.function.Consumer;
import java.util.function.Function;

public class DatabaseConnection {
    private SessionFactory sessionFactory;

    public DatabaseConnection() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(PaymentTransaction.class)
                .addAnnotatedClass(Address.class)
                .buildSessionFactory();
    }

    public void myQueryConsumer(Consumer<Session> action) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            action.accept(session);

            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.getRollbackOnly()) {
                tx.rollback();
            }
        }
    }

    public <K> K myQueryFunction(Function<Session, K> action) {
        Transaction tx = null;
        K apply = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            apply = action.apply(session);

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.getRollbackOnly()) {
                tx.rollback();
            }
        }
        return apply;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}