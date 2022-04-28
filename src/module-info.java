module Trello {

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;

    opens front.gui.main;
    opens front.gui.trello;
    opens front.gui.user;
    opens front.gui.task;
    opens front.cli;

    opens back.controller;

    opens back.objects;
}