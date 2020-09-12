package pl.exchangeapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.exchangeapp.entities.Customer;


import java.util.function.Consumer;
import java.util.function.Function;

public class CustomerRepository implements CustomerDAO {
    private SessionFactory sessionFactory;

    public CustomerRepository() {
    }

    public CustomerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createCustomer(Customer customer) {
        myQueryConsumer(session -> session.persist(customer));
    }


    private void myQueryConsumer(Consumer<Session> action) {
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

    private <K> K myQueryFunction(Function<Session, K> action) {
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
