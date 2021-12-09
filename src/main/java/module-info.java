module com.mateen.gameoflife {
    requires javafx.controls;
    requires javafx.media;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.mateen.gameoflife to javafx.fxml;
    exports com.mateen.gameoflife;
    exports com.mateen.gameoflife.controller;
    exports com.mateen.gameoflife.model;
    exports com.mateen.gameoflife.util;
}