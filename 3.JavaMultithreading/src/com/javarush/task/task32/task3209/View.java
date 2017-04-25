package com.javarush.task.task32.task3209;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rumata on 25.04.2017.
 */
public class View extends JFrame implements ActionListener {
    private Controller controller;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void init() {}

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void exit() {controller.exit();}
}
