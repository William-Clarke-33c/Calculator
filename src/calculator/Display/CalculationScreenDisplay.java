package calculator.Display;

import calculator.Logic.TextDisplay;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class CalculationScreenDisplay extends TextField implements TextDisplay {

   public CalculationScreenDisplay(){
        super();
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setMinHeight(25);
        this.setDisable(true);
        this.setStyle(
                "-fx-opacity: 1;\n" + "-fx-background-color:  rgb(65,64,65);\n" +
                        "-fx-text-fill: rgba(255, 255, 225, 0.3);\n" +
                        "    -fx-border-width: 0;\n" +"-fx-font-size: 12px;");
        this.setText("");
    }
}
