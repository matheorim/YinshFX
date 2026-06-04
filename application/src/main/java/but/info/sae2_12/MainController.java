package but.info.sae2_12;

import but.info.sae2_12.controllers.BottomController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private BottomController bottomController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("coucou");
        if (bottomController != null) bottomController.setMainController(this);
    }
}
