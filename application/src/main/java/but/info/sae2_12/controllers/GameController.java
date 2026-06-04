package but.info.sae2_12.controllers;

import but.info.sae2_12.MainController;
import but.info.sae2_12.model.factory.FactoryDoubled;
import but.info.sae2_12.model.factory.IFactory;
import but.info.sae2_12.model.state.IState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class GameController {

	@FXML private Button partieAleatoire;
	@FXML private Button etatTest;
	@FXML private Button ligneBlanche;
	@FXML private Button ligneNoire;
	@FXML private CheckBox modeEdition;
	@FXML private HBox choixEdition;
	@FXML private ColorPicker pickerCouleur1;
	@FXML private ColorPicker pickerCouleur2; 
	@FXML private ColorPicker pickerCouleur3;
	@FXML private MainController mainController;
	@FXML private CheckBox activeCoord;

	public void setController(MainController c){
		this.mainController=c;
		if (this.mainController != null) {
			pickerCouleur1.valueProperty().bindBidirectional(this.mainController.couleur1Property());
			pickerCouleur2.valueProperty().bindBidirectional(this.mainController.couleur2Property());
			pickerCouleur3.valueProperty().bindBidirectional(this.mainController.couleur3Property());
		}
	}
	
	private IFactory factory = new FactoryDoubled();

	@FXML
	public void onToggleCoord(){
		boolean coche = activeCoord.isSelected();
		mainController.selectionnerAffichageCoordonnees(coche);
	}

	@FXML
	public void onPartieAleatoire() {
		mainController.PartieAleatoire(TypePartie.ALEA);
	}

	@FXML
	public void onEtatTest() {
		mainController.PartieAleatoire(TypePartie.TEST);
	}
	
	@FXML
	public void onLigneBlanche() {
		mainController.PartieAleatoire(TypePartie.LIGNEBLANC);
	}

	@FXML
	public void onLigneNoire() {
		mainController.PartieAleatoire(TypePartie.LIGNENOIR);
	}
	
	@FXML
	public void onToggleModeEdition() {
	    if (modeEdition.isSelected()) {
	        choixEdition.setDisable(false);
	    } else {
	        choixEdition.setDisable(true);
	    }
	    // TODO: mainController.getModel().isEditModeProperty().set(modeEdition.isSelected());
	}
	
	@FXML
	public void onActionCouleur1() {
		if (mainController != null) {
			Color nouvelleCouleur = pickerCouleur1.getValue();
			mainController.couleur1Property().setValue(nouvelleCouleur);
			mainController.hexGridController.desinerGrille();
		}
	}

	@FXML
	public void onActionCouleur2() {
		if (mainController != null) {
			Color nouvelleCouleur = pickerCouleur2.getValue();
			mainController.couleur2Property().setValue(nouvelleCouleur);
			mainController.hexGridController.desinerGrille();
		}
	}

	@FXML
	public void onActionCouleur3() {
		if (mainController != null) {
			Color nouvelleCouleur = pickerCouleur3.getValue();
			mainController.couleur3Property().setValue(nouvelleCouleur);
			mainController.hexGridController.desinerGrille();
		}
	}
}