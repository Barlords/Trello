module Trello {

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens main;
    opens home;
    opens team;
    opens task;
    opens flag;
}