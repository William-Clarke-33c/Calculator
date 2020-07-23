package calculator;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class Screen {

    static final TextField mainScreen = new TextField();
    static final TextField calculationScreen = new TextField();

    public static void initalizeCalculationScreen(){
        calculationScreen.setAlignment(Pos.CENTER_RIGHT);
        calculationScreen.setMinHeight(25);
        calculationScreen.setDisable(true);
        calculationScreen.setStyle(
                "-fx-opacity: 1;\n" + "-fx-background-color:  rgb(65,64,65);\n" +
                        "-fx-text-fill: rgba(255, 255, 225, 0.3);\n" +
                        "    -fx-border-width: 0;\n" +"-fx-font-size: 12px;");
        calculationScreen.setText("");
    }

    public static void initalizeMainScreen(){
        mainScreen.setAlignment(Pos.CENTER_RIGHT);
        mainScreen.setMinHeight(50);
        mainScreen.setDisable(true);
        mainScreen.setStyle(
                "-fx-opacity: 1;\n" + "-fx-background-color:  rgb(65,64,65);\n" +
                        "-fx-border-width: 0;\n" +
                        "-fx-text-fill: rgb(255, 255, 225);\n" + "-fx-font-size: 24px;");
    }
}
