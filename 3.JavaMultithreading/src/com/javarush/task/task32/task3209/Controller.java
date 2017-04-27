package com.javarush.task.task32.task3209;


import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.File;

import java.io.StringReader;
import java.io.StringWriter;


public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    // конструктор принимает представление
    public Controller(View view) {
        this.view = view;
    }

    public void init() {
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    // выход
    public void exit() {
        System.exit(0);
    }

    //геттер для документа
    public HTMLDocument getDocument() {
        return document;
    }

    // удаляет существующий документ и создает пустой
    public void resetDocument() {
        if (document != null) {
            // удаляет существующий документ
            document.removeUndoableEditListener(view.getUndoListener());
        }
        // создает документ по умолчанию
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }


    //записывает переданный текст с html тегами в документ document
    public void setPlainText(String text) {
        resetDocument();
        StringReader stringReader = new StringReader(text);
        try {
            new HTMLEditorKit().read(stringReader, document, 0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    // получает текст из документа со всеми html тегами.
    public String getPlainText() {
        StringWriter stringWriter = new StringWriter();
        try {
            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());
        } catch (Exception e) {ExceptionHandler.log(e);}
        return stringWriter.toString();
    }

    // Новый документ
    public void createNewDocument(){}

    // открыть документ
    public void openDocument() {}

    //сохранить документ
    public void saveDocument() {}

    //сохранить документ как..
    public void saveDocumentAs() {}


}
