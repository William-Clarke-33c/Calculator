package calculator;

import calculator.Display.CalculationScreenDisplay;
import calculator.Display.CalculatorButton;
import calculator.Display.MainScreenDisplay;
import calculator.Logic.ButtonLogic;
import calculator.Logic.ButtonText;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Calculator extends VBox {
    ButtonLogic calculatorBody;
    public Calculator(MainScreenDisplay mainScreen, CalculationScreenDisplay calculationScreen, ButtonLogic calculatorBody) {
        this.calculatorBody = calculatorBody;
        GridPane grid = new GridPane();
        addButtons(grid);
        this.getChildren().addAll(calculationScreen, mainScreen, grid, createLastRow());
    }

    public void addButtons(GridPane grid) {
        grid.add(assignButtonLogic(ButtonText.ONE), 0, 5);
        grid.add(assignButtonLogic(ButtonText.TWO), 1, 5);
        grid.add(assignButtonLogic(ButtonText.THREE), 2, 5);
        grid.add(assignButtonLogic(ButtonText.ADD), 3, 5);
        grid.add(assignButtonLogic(ButtonText.FOUR), 0, 4);
        grid.add(assignButtonLogic(ButtonText.FIVE), 1, 4);
        grid.add(assignButtonLogic(ButtonText.SIX), 2, 4);
        grid.add(assignButtonLogic(ButtonText.SUBTRACT), 3, 4);
        grid.add(assignButtonLogic(ButtonText.SEVEN), 0, 3);
        grid.add(assignButtonLogic(ButtonText.EIGHT), 1, 3);
        grid.add(assignButtonLogic(ButtonText.NINE), 2, 3);
        grid.add(assignButtonLogic(ButtonText.MULTIPLY), 3, 3);
        grid.add(assignButtonLogic(ButtonText.LEFTPARAN), 0, 2);
        grid.add(assignButtonLogic(ButtonText.RIGHTPARAN), 1, 2);
        grid.add(assignButtonLogic(ButtonText.EXPONENT), 2, 2);
        grid.add(assignButtonLogic(ButtonText.DIVIDE), 3, 2);
        grid.add(assignButtonLogic(ButtonText.CLEAR), 0, 1);
        grid.add(assignButtonLogic(ButtonText.NEGATIVE), 3, 1);
        grid.add(assignButtonLogic(ButtonText.PERCENT), 2, 1);
        grid.add(assignButtonLogic(ButtonText.BACK), 1, 1);
    }

    public CalculatorButton assignButtonLogic(ButtonText text){
        CalculatorButton button = new CalculatorButton(text.getValue());
        button.setOnAction(e -> calculatorBody.doButtonLogic(text));
        return button;
    }

    public HBox createLastRow(){
        HBox lastRow = new HBox();
        lastRow.getChildren().addAll(assignButtonLogic(ButtonText.ZERO),
                assignButtonLogic(ButtonText.PERIOD),
                assignButtonLogic(ButtonText.EQUALS)
        );
        return lastRow;
    }
}
