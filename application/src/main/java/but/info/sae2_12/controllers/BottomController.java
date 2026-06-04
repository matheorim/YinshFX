package but.info.sae2_12.controllers;

import but.info.sae2_12.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import java.util.Arrays;

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

    public void onSauvegarder() {
        //TODO Créer le système de sauvegarde
    }

    public void onCharger() {
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Sauvegarde de partie (.yns, .yin)", "*.yns", "*.yin"); // Création des filtres d'extensions

        fc.getExtensionFilters().add(filter); // Ajout des filtres d'extensions au FileChooser
        fc.setTitle("Charger une partie");

        fc.showOpenDialog(charger.getParentPopup().getOwnerWindow());

        //TODO Lire les premiers octets
        //TODO Vérifier le Magic Number
        //TODO Vérifier la signature
    }
}
