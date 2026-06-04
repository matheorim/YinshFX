package but.info.sae2_12.controllers;

import but.info.sae2_12.MainController;
import but.info.sae2_12.model.state.IState;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;

public class MenuController {

    private MainController mainController;

    @FXML private MenuBar menuBar;
    @FXML private MenuItem aPropos;
    @FXML private MenuItem charger;
    @FXML private MenuItem sauvegarderSous;
    @FXML private MenuItem quitter;

    /**
     * Reçoit la référence du MainController
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void onAPropos() {
        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle("À propos");
        alerte.setHeaderText("YINSH - SAE 2.12");
        alerte.setContentText("Développé par :\n\t- LANGLOIS Kylian\n\t- MATHIEU Alix\n\t- FRAISSE Eliot\n\t- RIMBAULT Matheo\n\t- QUAGHEBEUR Noah");
        alerte.showAndWait();
    }

    @FXML
    public void onsauvegarderSous() {
        if (mainController == null || mainController.getModel() == null) {
            afficherErreur("Erreur critique", "Le contrôleur principal ou le modèle n'est pas initialisé.");
            return;
        }

        FileChooser fc = new FileChooser();
        fc.setTitle("Sauvegarder la partie");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier YINSH (*.yns)", "*.yns"));

        Window window = null;
        if (menuBar != null && menuBar.getScene() != null) {
            window = menuBar.getScene().getWindow();
        }

        File file = fc.showSaveDialog(window);

        if (file != null) {
            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {

                oos.writeUTF("SAE212");

                oos.writeObject(mainController.getModel().getCurrentState());
                System.out.println("Partie sauvegardée avec succès !");

            } catch (IOException e) {
                afficherErreur("Erreur de sauvegarde", "Impossible d'écrire dans le fichier : " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void onCharger() {

        FileChooser fc = new FileChooser();
        fc.setTitle("Charger une partie");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier YINSH (*.yns)", "*.yns"));

        Window window = null;
        if (menuBar != null && menuBar.getScene() != null) {
            window = menuBar.getScene().getWindow();
        }

        File file = fc.showOpenDialog(window);

        if (file != null) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {

                String magic = ois.readUTF();
                if (!"SAE212".equals(magic)) {
                    afficherErreur("Fichier invalide", "Ce fichier n'est pas une sauvegarde YINSH valide.");
                    return;
                }

                IState etatCharge = (IState) ois.readObject();

                mainController.getModel().setCurrentState(etatCharge);
                System.out.println("Partie chargée avec succès !");

            } catch (Exception e) {
                afficherErreur("Erreur de chargement", "Le fichier est corrompu ou incompatible : " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void onQuit() {
        Platform.exit();
        System.exit(0);
    }

    private void afficherErreur(String titre, String message) {
        Alert alerte = new Alert(Alert.AlertType.ERROR);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(message);
        alerte.showAndWait();
    }
}