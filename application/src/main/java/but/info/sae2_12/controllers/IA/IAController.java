package but.info.sae2_12.controllers.IA;

import java.net.URL;
import java.util.ResourceBundle;

import but.info.sae2_12.AI.MiniMax;
import but.info.sae2_12.model.Team;
import but.info.sae2_12.model.state.IState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class IAController implements Initializable {

	@FXML private TextField NombreDAnneau; 
	@FXML private TextField ScoreVictoire;
	@FXML private TextField LigneDeQuatre;
	@FXML private TextField AnneauRetire;
	@FXML private TextField pion;
	@FXML private ProgressBar ProgressBar;
	@FXML private Button MeilleurCoup;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		NombreDAnneau.setText("5");
		ScoreVictoire.setText("100 000");
		LigneDeQuatre.setText("50");
		AnneauRetire.setText("1000");
		pion.setText("10");
		ProgressBar.setProgress(0.5);
		
	}
	
	private MainController mainController;

	public void setMainController(MainController mainController) {
	    this.mainController = mainController;
	}
	
	public void updateProgressBar(IState state , Team teamIA ) {
		double score = mainController.getMiniMax().evaluate(state, teamIA);
		double calcul = (score + 100000) / 200000;
		ProgressBar.setProgress(calcul);
	}
	
	@FXML
	public void AfficherMeilleurCoup() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Meilleur coup");
		alert.setContentText(mainController.getMiniMaxAI().chooseMove(mainController.getState()).toString());
		alert.showAndWait();
	}

}
