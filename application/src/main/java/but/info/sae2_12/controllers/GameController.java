package but.info.sae2_12.controllers;

import but.info.sae2_12.model.factory.FactoryDoubled;
import but.info.sae2_12.model.factory.IFactory;
import but.info.sae2_12.model.state.IState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameController {

	@FXML private Button partieAleatoire;
	@FXML private Button etatTest;
	@FXML private Button ligneBlanche;
	@FXML private Button ligneNoire;
	
	private IFactory factory = new FactoryDoubled();		

	@FXML
	private void onPartieAleatoire() {
		IState etat = factory.randomGame();
		// TODO: mainController.getModel().setState(etat);
	}

	@FXML
	private void onEtatTest() {
		IState etat = factory.testState();
		// TODO: mainController.getModel().setState(etat);
	}
	
	@FXML
	private void onLigneBlanche() {
		IState etat = factory.stateForWhiteLineTest();
		// TODO: mainController.getModel().setState(etat);
	}

	@FXML
	private void onLigneNoire() {
		IState etat = factory.stateForBlackLineTest();
		// TODO: mainController.getModel().setState(etat);
	}
	
}