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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

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

        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Sauvegarde de partie (.yns, .yin)", "*.yns","*.yin");
        fc.getExtensionFilters().add(filter);

        fc.setTitle("Sauvegarder la partie");
        File save = fc.showSaveDialog(sauvegarder.getParentPopup().getOwnerWindow());

        addMagicWord(save);

    }

    public void onCharger() {
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Sauvegarde de partie (.yns, .yin)", "*.yns", "*.yin"); // Création des filtres d'extensions
        fc.getExtensionFilters().add(filter); // Ajout des filtres d'extensions au FileChooser

        fc.setTitle("Charger une partie");
        File save = fc.showOpenDialog(charger.getParentPopup().getOwnerWindow());

        // Si la lecture du Magic Word n'a pas fonctionné on prévient l'utilisateur
        if (!checkMagicWord(save)) {
            Alert alerte = new Alert(Alert.AlertType.ERROR);
            alerte.setTitle("Attention !");
            alerte.setHeaderText("Partie corrompue");
            alerte.setContentText("Votre partie a été corrompue et n'a pas pu être chargée.");
            alerte.showAndWait();
        }

        //TODO Vérifier la signature
    }

    private void addMagicWord(File file) {
        String s = "SAE213";
        byte[] data = s.getBytes(StandardCharsets.UTF_8);

        try (BufferedOutputStream out = new BufferedOutputStream(Files.newOutputStream(
                file.getAbsoluteFile().toPath(),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE)
        )) {

            out.write(data, 0, data.length);

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private boolean checkMagicWord(File file) {
        String s = "SAE212";
        byte[] bytes = new byte[s.length()];

        try(FileInputStream fis = new FileInputStream(file)) {

            fis.read(bytes);
            fis.close();

//            System.out.println(Arrays.toString(s.getBytes(StandardCharsets.UTF_8)) + " : " + Arrays.toString(bytes));

            return (Arrays.toString(s.getBytes()).equals(Arrays.toString(bytes)));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
