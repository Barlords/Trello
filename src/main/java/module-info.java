module Trello {

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;

    //exports front.gui.main to javafx.graphics;

    opens front.gui.main;
    opens front.gui.trello;
    opens front.gui.user;
    opens front.gui.task;
    opens front.gui.flag;
    opens front.cli;

    opens back.controller;

    opens back.objects;
}