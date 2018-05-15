package com.kong.datas.jdbc;

import com.kong.datas.models.UserEntity;
import com.kong.support.toolboxes.CryptoTool;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class UserHibernateDaoTest {

    private SessionFactory sessionFactory;

    @Before
    public void init(){
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(standardServiceRegistry)
                .buildMetadata();
        sessionFactory = metadata.buildSessionFactory();
        assert  sessionFactory != null;
    }

    @Test
    public void test() throws NoSuchAlgorithmException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("kongw");
        userEntity.setPassword(CryptoTool.SHA1("123456".getBytes()));
        userEntity.setEmail("18551061885@163.com");
        session.persist(userEntity);
        transaction.commit();

        session.close();

    }

    @After
    public void destory(){
        sessionFactory.close();
    }
}
