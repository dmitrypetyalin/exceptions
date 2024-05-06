package com.petsoft;

import com.petsoft.controller.Controller;
import com.petsoft.view.View;

/**
 * @author PetSoft
 * @date 01.05.2024 9:39
 */
public class App {
    public static void main(String[] args) {
        new View(Controller.getInstance()).run();
    }
}
