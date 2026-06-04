package but.info.sae2_12.controllers;

import but.info.sae2_12.MainController;
import but.info.sae2_12.controllers.Interaction.EditMode;
import but.info.sae2_12.controllers.Interaction.GameMode;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class GameController {

	@FXML private Button partieAleatoire;
	@FXML private Button etatTest;
	@FXML private Button ligneBlanche;
	@FXML private Button ligneNoire;
	@FXML private CheckBox modeEdition;
	@FXML private HBox choixEdition;
	@FXML private ToggleGroup Team;
	@FXML private ToggleGroup Token;
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
		if (Token.getSelectedToggle() == null) {
			Token.getToggles().getFirst().setSelected(true);
		}
		if (Team.getSelectedToggle() == null) {
			Team.getToggles().getFirst().setSelected(true);
		}
	}
	
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
		if (modeEdition.isSelected()) {
			choixEdition.setDisable(false);
			mainController.setInteractionMode(new EditMode(mainController));
		} else {
			choixEdition.setDisable(true);
			mainController.setInteractionMode(new GameMode(mainController));
		}
		choixEdition.setDisable(!modeEdition.isSelected());
		mainController.updateInteractionMode();
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

	public but.info.sae2_12.model.Team getSelectedTeam() {
		RadioButton selected = (RadioButton) Team.getSelectedToggle();
		if (selected == null) return but.info.sae2_12.model.Team.BLACK;
		return selected.getText().equals("Noir") ? but.info.sae2_12.model.Team.BLACK : but.info.sae2_12.model.Team.WHITE;
	}

	public boolean isAnneauSelected() {
		RadioButton selected = (RadioButton) Token.getSelectedToggle();
		if (selected == null) return true;
		return selected.getText().equals("Anneau");
	}

	public boolean isModeEdition() {
		return modeEdition.isSelected();
	}

}