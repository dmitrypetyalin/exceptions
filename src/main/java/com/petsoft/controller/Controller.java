package com.petsoft.controller;

import com.petsoft.domain.Person;

import java.util.ArrayList;
import java.util.List;

import static com.petsoft.controller.Util.getPhoneNumber;

/**
 * @author PetSoft
 * @date 01.05.2024 9:43
 */
public class Controller {
    private static Controller INSTANCE;
    private List<Person> persons;
    private List<Person> addedPersons;


    private Controller() {
        this.persons = new ArrayList<>();
        this.persons = Util.readFiles();//TODO fill in persons from files
        this.addedPersons = new ArrayList<>();
    }

    public static Controller getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Controller();
        }
        return INSTANCE;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public String addPerson(String details) {
        try {
            String[] params = Util.parseString(details);
            addedPersons.add(new Person(params[0], params[1], params[2], params[3], getPhoneNumber(params[4]), params[5]));
        } catch (Exception ex) {
            return ex.toString();
        }
        return "Person was successfully added";
    }

    public void writePersons() {

    }


}
