package net.joaoqalves.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbstractDAO<T1 extends Serializable, T2> {

    // Not elegant. Don't know a better way to do it
    private Class<T2> clazz;

    public AbstractDAO(final Class<T2> clazz) {
        this.clazz = clazz;
    }

    public List<T2> getAll(final Session session) {
        List<T2> result = new ArrayList<>();
        try {
            result = session.createCriteria(clazz).list();
        } catch (Exception e) {
            e.printStackTrace(); //FIXME substitute by logger
        }
        return result;
    }

    public Optional<T2> getOne(final Session session, final T1 id) {
        Optional<T2> result = Optional.empty();
        try {
            result = Optional.ofNullable((T2)session.get(clazz.getCanonicalName(), id));
        } catch (Exception e) {
            e.printStackTrace(); //FIXME substitute by logger
        }
        return result;
    }

    public T2 save(final Session session, T2 obj) throws HibernateException {
        Transaction tx = session.beginTransaction();
        try {
            session.persist(obj);
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            throw e;
        }
        return obj;
    }

    public Optional<T2> delete(final Session session, final T1 id) {
        Transaction tx = session.beginTransaction();
        try {
            Optional<T2> obj = Optional.ofNullable((T2)session.get(clazz.getCanonicalName(), id));
            obj.ifPresent(session::delete);
            tx.commit();
            return obj;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace(); //FIXME substitute by logger
        } finally {
            session.close();
        }
        return Optional.empty();
    }
}
