package but.info.sae2_12.form;

import coordinates.Coordinate;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;

import static javafx.scene.text.Font.font;

public class HexSquare extends Polygon {

    public Coordinate coordonnee;

    private static final double ZOOM = 32.0;
    private Color couleur;
    private Shape forme;

    private static final double LARGEUR = ZOOM * 2.0;
    private static final double HAUTEUR = (2.0 / Math.sqrt(3.0)) * LARGEUR;

    public HexSquare(Coordinate coordonnee,Color couleur) {
        this.coordonnee = coordonnee;
        this.couleur=couleur;

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
    }
}
