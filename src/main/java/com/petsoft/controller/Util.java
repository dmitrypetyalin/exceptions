package com.petsoft.controller;

import com.petsoft.domain.Person;
import com.petsoft.exception.IncorrectArgumentLengthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

/**
 * @author PetSoft
 * @date 01.05.2024 10:50
 */
public class Util {
    private static final Logger LOG = LoggerFactory.getLogger(Util.class);
    private static final String fileSeparator = File.separator;
    private static final String path = "C:" + fileSeparator + "Users" + fileSeparator + "dmitr" + fileSeparator + "IdeaProjects"
            + fileSeparator + "exceptions" + fileSeparator + "src" + fileSeparator + "main" + fileSeparator + "java" + fileSeparator + "files";

    //Creating new file if there aren't Создание нового файла, если такового не существует
    public static File createFile(String fileName) {
        File file = null;
        try {
            file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return file;
    }

    public static void writePersonsToFiles(List<Person> people) {
        for (Person person : people) {
            String fileName = path + person.getSurname();
            File file = createFile(fileName);
            writeFile(file, person);
        }
    }

    private static void writeFile(File file, Person p) {
        //TODO closing file with try-with-resources or with finally
        try (FileWriter fileWriter = new FileWriter(file, true)) {

            fileWriter.write(p.getSurname() + " " + p.getName() + " " + p.getPatronymic() + " " + p.getBirthDate() + " " + p.getPhoneNumber() + " " + p.getSex());

        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static List<Person> readFiles() {
        //TODO this method
        List<Person> persons = new ArrayList<>();
        File directory = new File(path);
        List<File> files = List.of(Objects.requireNonNull(directory.listFiles()));
        for (File file : files) {
            persons.addAll(readFile(file));
        }
        return persons;
    }

    private static List<Person> readFile(File file) {
        //TODO this method
        List<Person> persons = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(" ");
                persons.add(new Person(arr[0], arr[1], arr[2], arr[3], Integer.parseInt(arr[4]), arr[5]));
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

        return persons;
    }

    static String[] parseString(String details) throws ParseException, IncorrectArgumentLengthException {
        String[] params = details.split(" ");

        if (params.length != 6) {
            throw new IncorrectArgumentLengthException("Incorrect quantity of person's details : " + params.length);
        }

        if (params[0].length() < 2) {
            throw new IncorrectArgumentLengthException("Surname is too short: " + params[0].length());
        }

        if (params[1].length() < 2) {
            throw new IncorrectArgumentLengthException("Name is too short: " + params[1].length());
        }

        if (params[3].length() < 2) {
            throw new IncorrectArgumentLengthException("Patronymic is too short: " + params[2].length());
        }

        new SimpleDateFormat("dd.MM.yyyy").parse(params[3]);

        String sex = params[5];

        if (sex.length() > 1) {
            throw new IncorrectArgumentLengthException("Sex must contain one symbol (m/f)");
        }

        if (!(sex.equals("m") || sex.equals("f"))) {
            throw new IllegalArgumentException("Sex must contain one symbol (m/f)");
        }

        return params;
    }

    static int getPhoneNumber(String number) throws IncorrectArgumentLengthException {
        if (number.length() < 7 || number.length() > 11) {
            throw new IncorrectArgumentLengthException("Phone number is incorrect: " + number.length());
        }

        int phoneNumber = Integer.parseInt(number);

        if (phoneNumber < 0) {
            throw new IllegalArgumentException("Number must be positive");
        }

        return phoneNumber;
    }
}