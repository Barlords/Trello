module Trello {

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;

    opens front.main;
    opens front.home;
    opens front.team;
    opens front.task;
    opens front.flag;
}