package com.javarush.task.task35.task3513;


import javax.swing.*;

/**
 * Created by Rumata on 18.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        JFrame window = new JFrame();
        window.setTitle("2048");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(450, 500);
        window.setResizable(false);

        window.add(controller.getView());

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
