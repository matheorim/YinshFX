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

    @FXML private MenuItem aPropos;
    @FXML private MenuItem charger;
    @FXML private MenuItem sauvegarder;

    /** <h1>JavaDoc écrite à la main :)</h1><br>
     * Assigne le contrôleur principal au BottomController
     * @param mainController le contrôleur principal
     */
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

    /**
     * Sauvegarde la partie actuelle en demandant à l'utilisateur où placer le fichier. <br>
     * Enregistre tout d'abord un Magic Word pour vérifier l'état du fichier,
     * puis enregistre l'état global de la partie, puis l'état du terrain.
     */
    public void onSauvegarder() {
        //TODO Créer le système de sauvegarde

        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Sauvegarde de partie (.yns, .yin)", "*.yns","*.yin");
        fc.getExtensionFilters().add(filter);

        fc.setTitle("Sauvegarder la partie");
        File save = fc.showSaveDialog(sauvegarder.getParentPopup().getOwnerWindow());

        addMagicWord(save);

        //TODO Faire le reste de la sauvegarde d'une partie
    }

    /**
     * Demande le fichier de sauvegarde de la partie à charger à l'utilisateur. <br>
     * Charge ensuite la partie en faisant des appels aux méthodes du {@code mainController} de la classe.
     */
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

    /**
     * Ajoute le Magic Word (SAE212) au fichier de sauvegarde {@code file}.
     * @param file le fichier de sauvegarde dans lequel ajouter le Magic Word.
     */
    private void addMagicWord(File file) {
        String s = "SAE212";
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

    /**
     * Vérifie l'intégrité du fichier de sauvegarde {@code file} en vérifiant le Magic Word au tout début de celui-ci.
     * @param file le fichier de sauvegarde à vérifier.
     * @return {@code true} si le fichier n'est pas corrompu/bonne extension et que le Magic Word apparaît au 6 premiers octets du fichier, {@code false} dans le cas contraire.
     */
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
