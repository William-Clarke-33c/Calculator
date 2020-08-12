package calculator.Display;

import javafx.scene.control.Button;

public class CalculatorButton extends Button {

    public CalculatorButton(String value) {
        super(value);
        if (value.equals("0")) {
            this.setMinWidth(137.5);
        } else {
            this.setMinWidth(68.75);
        }
        this.setMinHeight(50);
        this.setStyle("" +
                "    -fx-text-fill: rgb(255, 255, 255);\n" +
                "    -fx-border-color: rgb(255, 255, 255);\n" +
                "    -fx-border-width: 0.25;\n" +
                "    -fx-background-color: #5ac8fa;");
    }
}

