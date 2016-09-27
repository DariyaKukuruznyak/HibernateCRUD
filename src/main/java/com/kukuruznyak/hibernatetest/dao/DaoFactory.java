package com.kukuruznyak.hibernatetest.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoFactory {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
    public static void closeEntityManagerFactory(){
        entityManagerFactory.close();
    }
}
