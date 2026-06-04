package but.info.sae2_12.controllers;

import but.info.sae2_12.MainController;
import but.info.sae2_12.form.HexSquare;
import but.info.sae2_12.model.Model;
import coordinates.Coordinate;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;


public class CentralController extends Polygon implements Initializable {
    private MainController mainController;

    @FXML
    private Pane grille;
    private boolean afficherCoords = false;
    public javafx.scene.paint.Color couleurC = javafx.scene.paint.Color.web("#E0E0E0");
    public javafx.scene.paint.Color couleurB = javafx.scene.paint.Color.web("#A0A0A0");
    public javafx.scene.paint.Color couleurA = javafx.scene.paint.Color.web("#707070");

    public void setController(MainController c){
        this.mainController=c;

        if (grille != null && grille.getWidth() > 0) {
            desinerGrille();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChangeListener<Number> sizeListener = (obs, oldVal, newVal) -> {
            if (grille.getWidth() > 0 && grille.getHeight() > 0) {
                desinerGrille();
            }
        };

        grille.widthProperty().addListener(sizeListener);
        grille.heightProperty().addListener(sizeListener);

        Platform.runLater(() -> {
            if (grille != null && grille.getWidth() > 0) {
                desinerGrille();
            }
        });
    }

    public void setAfficherCoords(boolean afficher) {
        this.afficherCoords = afficher;
        desinerGrille();
    }

    public void setPartie(TypePartie mode){

    }

    public void desinerGrille(){
        if (mainController == null || mainController.getModel() == null) return;
        Model model = mainController.getModel();
        grille.getChildren().clear();

        Set<Coordinate> coordinatesListe = model.getBoard().keySet();
        if (coordinatesListe.isEmpty()) return;

        double sommeX = 0;
        double sommeY = 360;
        for (Coordinate coord : coordinatesListe) {
            sommeX += coord.to2DCoordinate().x();
            sommeY += coord.to2DCoordinate().y();
        }

        double doubleZoom = 32.0;
        double plateauCentreX = (sommeX / coordinatesListe.size()) * doubleZoom;
        double plateauCentreY = (sommeY / coordinatesListe.size()) * doubleZoom;

        double ecranCentreX = grille.getWidth() / 2.0;
        double ecranCentreY = grille.getHeight() / 2.0;

        double offsetX = ecranCentreX - plateauCentreX;
        double offsetY = ecranCentreY - plateauCentreY;



        for (Coordinate coord : coordinatesListe) {
            javafx.scene.paint.Color couleurChoisie;

            switch (coord.to2DCoordinate().x()%3) {
                case 0:
                    couleurChoisie = mainController.getCouleur1();
                    break;
                case 1:
                    couleurChoisie = mainController.getCouleur2();
                    break;
                case 2:
                    couleurChoisie = mainController.getCouleur3();
                    break;
                default:
                    couleurChoisie = mainController.getCouleur3();
                    break;
            }

            HexSquare hex = new HexSquare(coord, couleurChoisie,mainController);
            hex.setTranslateX(offsetX);
            hex.setTranslateY(offsetY);

            if (afficherCoords){
                Text text = new Text(coord.toString());
                text.setMouseTransparent(true);
                text.setLayoutX(hex.getLayoutX() + offsetX - 15);
                text.setLayoutY(hex.getLayoutY() + offsetY + 5);
                grille.getChildren().addAll(hex,text);
            }else {
                Text text = new Text("");
                text.setMouseTransparent(true);
                grille.getChildren().addAll(hex,text);
            }
            Shape pieceGraphique = hex.getForme();
            if (pieceGraphique != null) {
                pieceGraphique.setTranslateX(offsetX);
                pieceGraphique.setTranslateY(offsetY);
                grille.getChildren().add(pieceGraphique);
            }
        }
    }
}
