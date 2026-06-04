package but.info.sae2_12.form;

import but.info.sae2_12.Controllers.CentralController;
import but.info.sae2_12.MainController;
import but.info.sae2_12.model.Team;
import but.info.sae2_12.model.tokens.Pawn;
import but.info.sae2_12.model.tokens.Ring;
import but.info.sae2_12.model.tokens.Token;
import coordinates.Coordinate;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;

import static javafx.scene.text.Font.font;

public class HexSquare extends Polygon {

    public Coordinate coordonnee;
    private MainController mainController;

    private static final double ZOOM = 32.0;
    private Color couleur;
    private Shape forme;


    private static final double LARGEUR = ZOOM * 2.0;
    private static final double HAUTEUR = (2.0 / Math.sqrt(3.0)) * LARGEUR;

    public HexSquare(Coordinate coordonnee,Color couleur,MainController ctrl) {
        this.coordonnee = coordonnee;
        this.couleur=couleur;
        this.mainController=ctrl;

        double xBrut = coordonnee.to2DCoordinate().x();
        double yBrut = coordonnee.to2DCoordinate().y();

        double centreX = xBrut * (LARGEUR*0.55);
        double centreY = yBrut * (HAUTEUR * 0.8);

        getPoints().addAll(-LARGEUR / 2.0,-HAUTEUR / 4.0, 0.0,-HAUTEUR / 2.0, LARGEUR / 2.0,-HAUTEUR / 4.0, LARGEUR / 2.0,HAUTEUR / 4.0, 0.0,HAUTEUR / 2.0, -LARGEUR / 2.0,HAUTEUR / 4.0);

        setLayoutX(centreX);
        setLayoutY(centreY);

        this.setFill(couleur);
        this.setStroke(javafx.scene.paint.Color.rgb(60, 60, 60));
        this.setStrokeWidth(1.5);


        if (this.mainController != null && this.mainController.getModel() != null) {
            Token piece = this.mainController.getModel().getTokenAt(coordonnee);
            if (piece instanceof Pawn) {
                Circle pion = new Circle(ZOOM * 0.5);
                if(piece.getTeam().equals(Team.BLACK)){pion.setFill(Color.BLACK);}else{pion.setFill(Color.WHITE);}
                pion.setStroke(Color.BLACK);
                pion.setLayoutX(centreX);
                pion.setLayoutY(centreY);
                pion.setMouseTransparent(true);
                this.forme = pion;

            } else if (piece instanceof Ring) {
                Circle anneau = new Circle(ZOOM * 0.6);
                anneau.setFill(Color.TRANSPARENT);
                if(piece.getTeam().equals(Team.BLACK)){anneau.setStroke(Color.BLACK);}else{anneau.setStroke(Color.WHITE);}
                anneau.setStrokeWidth(4);
                anneau.setLayoutX(centreX);
                anneau.setLayoutY(centreY);
                anneau.setMouseTransparent(true);
                this.forme = anneau;
            }
        }

        this.setOnMouseEntered(event -> {
            this.setFill(Color.BROWN);
        });

        this.setOnMouseExited(event -> {
            this.setFill(this.couleur);
        });

        this.setOnMouseClicked(event -> {

        });
    }

    public Shape getForme(){
        return this.forme;
    }
}
