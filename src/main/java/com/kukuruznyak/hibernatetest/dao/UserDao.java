package com.kukuruznyak.hibernatetest.dao;

import com.kukuruznyak.hibernatetest.entity.User;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDao {
    public User create(User user) {
        EntityManager entityManager = DaoFactory.getEntityManagerFactory().createEntityManager();
        Session session = (Session) entityManager.getDelegate();
        session.beginTransaction();
        User result = entityManager.find(User.class, session.save(user));
        session.getTransaction().commit();
        entityManager.close();
        return result;
    }

    public User update(User user) {
        EntityManager entityManager = DaoFactory.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        User result = entityManager.find(User.class, user.getId());
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    public void remove(User user) {
        if (user != null) {
            removeById(user.getId());
        }
    }

    public void removeById(int id) {
        EntityManager entityManager = DaoFactory.getEntityManagerFactory().createEntityManager();
        if (entityManager.contains(entityManager.find(User.class, id))) {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.getReference(User.class, id));
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }

    public User findById(int id) {
        EntityManager entityManager = DaoFactory.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        User result = entityManager.find(User.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    public List<User> getAll() {
        EntityManager entityManager = DaoFactory.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> result = cq.from(User.class);
        cq.select(result);
        TypedQuery<User> q = entityManager.createQuery(cq);
        List<User> resultList = q.getResultList();
        entityManager.getTransaction().commit();
        return resultList;
    }
}
