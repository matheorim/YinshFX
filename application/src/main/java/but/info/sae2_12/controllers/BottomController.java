package but.info.sae2_12.controllers;

import but.info.sae2_12.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

public class BottomController {

    public MainController mainController;

    @FXML private Label currentTeam;
    @FXML private Label currentState;

    @FXML private MenuItem aPropos;
    @FXML private MenuItem charger;
    @FXML private MenuItem sauvegarder;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;

        //TODO Lier currentTeam et currentState avec le MainController

        aPropos.setOnAction(e -> {
            Alert alerte = new Alert(Alert.AlertType.INFORMATION);
            alerte.setTitle("À propos");
            alerte.setHeaderText("Information");
            alerte.setContentText("Groupe 1 :\n\t- LANGLOIS Kylian\n\t- MATHIEU Alix\n\t- FRAISSE Eliot\n\t- RIMBAULT Matheo\n\t- QUAGHEBEUR Noah");

            alerte.show();
        });
    }


}
