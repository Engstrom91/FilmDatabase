package controllers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.persistence.*;
import java.util.List;

public class TableController {
    //Skapar entity manager factory
    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

}
