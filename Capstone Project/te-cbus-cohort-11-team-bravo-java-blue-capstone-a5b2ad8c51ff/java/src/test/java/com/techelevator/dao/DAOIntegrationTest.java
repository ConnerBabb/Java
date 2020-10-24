package com.techelevator.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.User;

public abstract class DAOIntegrationTest {

    /* Using this particular implementation of DataSource so that
     * every database interaction is part of the same database
     * session and hence the same database transaction */
    private static SingleConnectionDataSource dataSource;

    /* Before any tests are run, this method initializes the datasource for testing. */
    @BeforeClass
    public static void setupDataSource() {
        dataSource = new SingleConnectionDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/final_capstone");
        dataSource.setUsername("final_capstone_appuser");
        dataSource.setPassword("finalcapstone");
        /* The following line disables autocommit for connections
         * returned by this DataSource. This allows us to rollback
         * any changes after each test */
        dataSource.setAutoCommit(false);
    }

    /* After all tests have finished running, this method will close the DataSource */
    @AfterClass
    public static void closeDataSource() throws SQLException {
        dataSource.destroy();
    }

    /* After each test, we rollback any changes that were made to the database so that
     * everything is clean for the next test */
    @After
    public void rollback() throws SQLException {
        dataSource.getConnection().rollback();
    }

    /* This method provides access to the DataSource for subclasses so that
     * they can instantiate a DAO for testing */
    protected DataSource getDataSource() {
        return dataSource;
    }
    
//    @Test
//    public void createNewUser() {
//        boolean userCreated = userSqlDAO.create("TEST_USER","test_password","user");
//        Assert.assertTrue(userCreated);
//        User user = userSqlDAO.findByUsername("TEST_USER");
//        Assert.assertEquals("TEST_USER", user.getUsername());
//    }
}
