package calculator;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class Buttons {
    private static final String BACK = "⬅";
    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String THREE = "3";
    private static final String FOUR = "4";
    private static final String FIVE = "5";
    private static final String SIX = "6";
    private static final String SEVEN = "7";
    private static final String EIGHT = "8";
    private static final String NINE = "9";
    private static final String ZERO = "0";
    public static final String DIVIDE = "÷";
    public static final String MULTIPLY = "x";
    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String EQUALS = "=";
    public static final String NEGATIVE = "+/-";
    public static final String PERCENT = "%";
    public static final String EXPONENT = "^";
    public static final String LEFTPARAN = "(";
    public static final String RIGHTPARAN = ")";
    public static final String CLEAR = "AC";
    public static final ArrayList<String> operators = new ArrayList<>();


    public static void assignOperators() {
        operators.add(ADD);
        operators.add(SUBTRACT);
        operators.add(DIVIDE);
        operators.add(MULTIPLY);
        operators.add(EXPONENT);
        operators.add(LEFTPARAN);
        operators.add(RIGHTPARAN);
    }

    public static void addButtons(GridPane grid) {
        grid.add(createButton(ONE), 0, 5);
        grid.add(createButton(TWO), 1, 5);
        grid.add(createButton(THREE), 2, 5);
        grid.add(createButton(ADD), 3, 5);
        grid.add(createButton(FOUR), 0, 4);
        grid.add(createButton(FIVE), 1, 4);
        grid.add(createButton(SIX), 2, 4);
        grid.add(createButton(SUBTRACT), 3, 4);
        grid.add(createButton(SEVEN), 0, 3);
        grid.add(createButton(EIGHT), 1, 3);
        grid.add(createButton(NINE), 2, 3);
        grid.add(createButton(MULTIPLY), 3, 3);
        grid.add(createButton(LEFTPARAN), 0, 2);
        grid.add(createButton(RIGHTPARAN), 1, 2);
        grid.add(createButton(EXPONENT), 2, 2);
        grid.add(createButton(DIVIDE), 3, 2);
        grid.add(createButton(CLEAR), 0, 1);
        grid.add(createButton(NEGATIVE), 3, 1);
        grid.add(createButton(PERCENT), 2, 1);
        grid.add(createButton(BACK), 1, 1);
        grid.add(Screen.mainScreen, 0, 0);
    }

    public static HBox createLastRow(){
        HBox lastRow = new HBox();
        lastRow.getChildren().addAll(createButton("0"),
                createButton("."),createButton(EQUALS));
        return lastRow;
    }

    private static Button createButton(String value) {
        Button calculatorButton = new Button(value);
        if(value.equals(ZERO)){
            calculatorButton.setMinWidth(137.5);
        }else {
            calculatorButton.setMinWidth(68.75);
        }
        calculatorButton.setMinHeight(50);
        if (value.equals(EQUALS)) {
            Body.setOnEqualsPressed(calculatorButton);
        }else if (operators.contains(value)) {
            Body.setOnArithmeticPressed(calculatorButton);
        }else if(value.equals(CLEAR)) {
            Body.setOnClearPressed(calculatorButton);
        }else if(value.equals(NEGATIVE)){
            Body.setOnNegativePressed(calculatorButton);
        }else if(value.equals(PERCENT)){
            Body.setOnPercentPressed(calculatorButton);
        }else if(value.equals(BACK)){
            Body.setOnBackPressed(calculatorButton);
        }else {
            Body.setOnNumberPressed(calculatorButton);
        }
        calculatorButton.setStyle("" +
                "    -fx-text-fill: rgb(255, 255, 255);\n" +
                "    -fx-border-color: rgb(255, 255, 255);\n" +
                "    -fx-border-width: 0.25;\n" +
                "    -fx-background-color: #5ac8fa;");
        return calculatorButton;
    }
}
