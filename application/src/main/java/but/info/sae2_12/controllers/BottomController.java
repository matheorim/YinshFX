package but.info.sae2_12.controllers;

import but.info.sae2_12.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class BottomController {

    public MainController mainController;

    @FXML private Label currentTeam;
    @FXML private Label currentState;

    /** <h1>JavaDoc écrite à la main :)</h1><br>
     * Assigne le contrôleur principal au BottomController
     * @param mainController le contrôleur principal
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;

        //TODO Lier currentTeam et currentState avec le MainController


    }


}
