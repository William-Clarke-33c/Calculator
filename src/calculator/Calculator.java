package calculator;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Calculator {

    public static VBox createCalculator() {
        GridPane grid = new GridPane();
        Buttons.addButtons(grid);
        VBox calculator = new VBox();
        calculator.getChildren().addAll(Screen.calculationScreen,
                Screen.mainScreen, grid, Buttons.createLastRow());
        return calculator;
    }
}
