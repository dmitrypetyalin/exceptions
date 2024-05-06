package com.petsoft.view;

import com.petsoft.controller.Controller;
import com.petsoft.domain.Person;

import java.util.List;
import java.util.Scanner;

/**
 * @author PetSoft
 * @date 01.05.2024 9:42
 */
public class View implements iView {

    private final Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        Command com;

        boolean flag = true;
        while (flag) {
            String command = prompt("Type the command: ");
            com = Command.valueOf(command.toUpperCase());
            switch (com) {
                case EXIT:
                    flag = false;
                    System.out.println("Exit the program!");
                    break;

                case LIST:
                    printAllPersons(controller.getPersons());
                    break;

                case ADD:
                    String details = prompt("Input person's details: ");
                    System.out.println(controller.addPerson(details));
                    break;

                case WRITE:
                    System.out.println(controller.writePersons());
                    break;
            }
        }
    }

    public void printAllPersons(List<Person> persons) {
        System.out.println("---------------------- Persons list ------------------------------");
        for (Person p : persons) {
            System.out.println(p);
        }
        System.out.println("------------------------------------------------------------------");
    }

    public String prompt(String msg) {
        Scanner in = new Scanner(System.in);
        System.out.print(msg + " ");
        return in.nextLine();
    }
}
