package calculator.Display;

import calculator.Logic.TextDisplay;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class MainScreenDisplay extends TextField implements TextDisplay {

    public MainScreenDisplay(){
        super();
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setMinHeight(50);
        this.setDisable(true);
        this.setStyle(
                "-fx-opacity: 1;\n" + "-fx-background-color:  rgb(65,64,65);\n" +
                        "-fx-border-width: 0;\n" +
                        "-fx-text-fill: rgb(255, 255, 225);\n" + "-fx-font-size: 24px;");
    }

}
