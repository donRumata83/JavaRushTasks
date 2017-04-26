package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rumata on 25.04.2017.
 */
public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();

    // конструктор
    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }

    }

    //Геттер и сеттер для контроллера
    public Controller getController() {
        return controller;
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

    // Инициализация всего окна
    public void init() {
        initGui();
        this.addWindowListener(new FrameListener(this));
        this.setVisible(true);

    }

    // Инициализирует кнопки меня в окне
    public void initMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        MenuHelper.initFileMenu(this, jMenuBar); //file
        MenuHelper.initEditMenu(this, jMenuBar); // edit
        MenuHelper.initStyleMenu(this, jMenuBar); //style
        MenuHelper.initAlignMenu(this, jMenuBar); // align
        MenuHelper.initColorMenu(this, jMenuBar); //colorFont
        MenuHelper.initFontMenu(this, jMenuBar); // fonts
        MenuHelper.initHelpMenu(this, jMenuBar); //help

        getContentPane().add(jMenuBar, BorderLayout.NORTH);

    }

    // Инициализация закладок окна. ХТМЛ/текст
    public void initEditor() {
        htmlTextPane.setContentType("text/html");

        tabbedPane.addTab("HTML", new JScrollPane(htmlTextPane));

        tabbedPane.addTab("Текст", new JScrollPane(plainTextPane));

        tabbedPane.setPreferredSize(new Dimension(100, 100));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    // Инициализация всего ГУИ
    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // Выход  из системы, пробрасывет закрытие в контроллер
    public void exit() {
        controller.exit();
    }

    public void selectedTabChanged() {
    }

    // Проверка возможности отменить действие
    public boolean canUndo() {return false;}
    //Проверка возможности перейти на действие вперед
    public boolean canRedo() {return  false;}

}
