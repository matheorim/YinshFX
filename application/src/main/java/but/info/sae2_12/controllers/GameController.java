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
	}
	
	private IFactory factory = new FactoryDoubled();

	@FXML
	public void onToggleCoord(){
		boolean coche = activeCoord.isSelected();
		mainController.selectionnerAffichageCoordonnees(coche);
	}

	@FXML
	public void onPartieAleatoire() {
		IState etat = factory.randomGame();
		// TODO: mainController.getModel().setState(etat);
	}

	@FXML
	public void onEtatTest() {
		IState etat = factory.testState();
		// TODO: mainController.getModel().setState(etat);
	}
	
	@FXML
	public void onLigneBlanche() {
		IState etat = factory.stateForWhiteLineTest();
		// TODO: mainController.getModel().setState(etat);
	}

	@FXML
	public void onLigneNoire() {
		IState etat = factory.stateForBlackLineTest();
		// TODO: mainController.getModel().setState(etat);
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
	    Color nouvelleCouleur = pickerCouleur1.getValue();
	    // TODO: mainController.getModel().setCouleur1(nouvelleCouleur);
	}

	@FXML
	public void onActionCouleur2() {
	    Color nouvelleCouleur = pickerCouleur2.getValue();
	    // TODO: mainController.getModel().setCouleur2(nouvelleCouleur);
	}

	@FXML
	public void onActionCouleur3() {
	    Color nouvelleCouleur = pickerCouleur3.getValue();
	    // TODO: mainController.getModel().setCouleur3(nouvelleCouleur);
	}
}