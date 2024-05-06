package com.petsoft.controller;

import com.petsoft.domain.Person;

import java.util.ArrayList;
import java.util.List;

import static com.petsoft.controller.Util.*;

/**
 * @author PetSoft
 * @date 01.05.2024 9:43
 */
public class Controller {
    private static Controller INSTANCE;
    private List<Person> persons;
    private List<Person> newPersons;


    private Controller() {
//        this.persons = new ArrayList<>();
        this.persons = getPersonsFromFile();//TODO fill in persons from files
        this.newPersons = new ArrayList<>();
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

    private List<Person> getPersonsFromFile() {
        return readFiles();
    }

    public String addPerson(String details) {
        if (details == null || details.isEmpty()) {
            return "String is empty";
        }
        try {
            String[] params = Util.parseString(details);
            newPersons.add(new Person(params[0], params[1], params[2], params[3], getPhoneNumber(params[4]), params[5]));
        } catch (Exception ex) {
            return ex.toString();
        }
        return "Person was successfully added";
    }

    public String writePersons() {
        writePersonsToFiles(newPersons);
        persons.addAll(newPersons);
        newPersons.clear();
        return "Persons successfully wrote";
    }


}
