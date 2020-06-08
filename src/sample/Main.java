package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

/* Implement an Array as a Queue */

public class Main extends Application {

    /* Declaring String Literals */
    private static final String DIVIDE = "÷";
    private static final String MULTIPLY = "x";
    private static final String ADD = "+";
    private static final String SUBTRACT = "-";
    private static final String EQUALS = "=";
    private static final String NEGATIVE = "+/-";
    private static final String PERCENT = "%";
    private static final String CLEAR = "AC";
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
    private static final ArrayList<String> calculationArray = new ArrayList<>();
    static final ArrayList<String> operators = new ArrayList<>();
    static final TextField screen = new TextField();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");
        assignOperators();
        initalizeScreen();
        VBox calculator = createCalculator();
        primaryStage.setScene(new Scene(calculator, 230, 320));
        primaryStage.show();
    }

    private void assignOperators() {
        operators.add(ADD);
        operators.add(SUBTRACT);
        operators.add(DIVIDE);
        operators.add(MULTIPLY);
    }

    private void initalizeScreen(){
        screen.setMinHeight(50);
        screen.setDisable(true);
        screen.setStyle("-fx-opacity: 1;");
    }

    private static VBox createCalculator() {
        GridPane grid = new GridPane();
        addButtons(grid);
        VBox calculator = new VBox();
        calculator.getChildren().addAll(screen, grid, createLastRow());
        return calculator;
    }

    private static void addButtons(GridPane grid) {
        grid.add(createButton(ONE), 0, 4);
        grid.add(createButton(TWO), 1, 4);
        grid.add(createButton(THREE), 2, 4);
        grid.add(createButton(ADD), 3, 4);
        grid.add(createButton(FOUR), 0, 3);
        grid.add(createButton(FIVE), 1, 3);
        grid.add(createButton(SIX), 2, 3);
        grid.add(createButton(SUBTRACT), 3, 3);
        grid.add(createButton(SEVEN), 0, 2);
        grid.add(createButton(EIGHT), 1, 2);
        grid.add(createButton(NINE), 2, 2);
        grid.add(createButton(MULTIPLY), 3, 2);
        grid.add(createButton(CLEAR), 0, 1);
        grid.add(createButton(NEGATIVE), 1, 1);
        grid.add(createButton(PERCENT), 2, 1);
        grid.add(createButton(DIVIDE), 3, 1);
        grid.add(screen, 0, 0);
    }

    private static void setOnNumberPressed(Button number) {
        number.setOnAction(e -> {
            if (calculationArray.isEmpty()) {
                calculationArray.add(number.getText());
                screen.setText(number.getText());
            /* Make your calculationStack a Stack<String>, as opposed to a Stack, so you
             * don't have to call .toString() on the elements */
            } else if (operators.contains(calculationArray.get(calculationArray.size() - 1))) {
                System.out.println("PUSHING");
                calculationArray.add(number.getText());
                screen.setText(number.getText());
            } else {
                System.out.println("Peek: " + calculationArray.get(calculationArray.size() - 1));
                String newValue = calculationArray.get(calculationArray.size() - 1);
                System.out.println("Adding this on: " + number.getText());
                newValue += number.getText();
                calculationArray.set((calculationArray.size() - 1), newValue);
                screen.setText(newValue);
                System.out.println("New Value: " + newValue);
            }
        });
    }

    private static void setOnArithmeticPressed(Button arithmetic) {
        arithmetic.setOnAction(e -> {
            if (!calculationArray.isEmpty()) {
                System.out.println(arithmetic.getText());
                calculationArray.add(arithmetic.getText());
            } else {
                System.out.println("ERR NULL!");
            }
        });
    }

    private static void setOnEqualsPressed(Button equals) {
        equals.setOnAction((e -> {
            double valueOne = 0;
            double valueTwo = 0;
            String operation = "";
            if (!calculationArray.isEmpty() && calculationArray.size() >= 3) {
                while(calculationArray.size() > 1) {
                    valueOne = Double.parseDouble(calculationArray.get(0));
                    operation = calculationArray.get(1);
                    valueTwo = Double.parseDouble(calculationArray.get(2));
                    System.out.println("ValueOne: " + valueOne + " ValueTwo: " + valueTwo);
                    if(operation.equals(ADD)){
                        calculationArray.set(0, Double.toString(valueOne + valueTwo));
                        removeIndexes();
                        screen.setText(Double.toString(valueOne + valueTwo));
                    }
                    if(operation.equals((SUBTRACT))){
                        calculationArray.set(0, Double.toString(valueOne - valueTwo));
                        removeIndexes();
                        screen.setText(Double.toString(valueOne - valueTwo));
                    }
                    if(operation.equals((MULTIPLY))){
                        calculationArray.set(0, Double.toString(valueOne * valueTwo));
                        removeIndexes();
                        screen.setText(Double.toString(valueOne * valueTwo));
                    }
                    if(operation.equals((DIVIDE))){
                        calculationArray.set(0, Double.toString(valueOne / valueTwo));
                        removeIndexes();
                        screen.setText(Double.toString(valueOne / valueTwo));
                    }
                }
            } else {
                screen.setText("ERR NULL!");
            }
        }));
    }

    private static void setOnClearPressed(Button clear){
        clear.setOnAction((e -> {
            if(!calculationArray.isEmpty()){
                calculationArray.clear();
            }
            screen.setText("");
        }));
    }

    private static void setOnNegativePressed(Button negative){
        negative.setOnAction((e -> {
            if(!calculationArray.isEmpty()) {
                double value = Double.parseDouble(calculationArray.get(calculationArray.size() - 1));
                value *= -1;
                calculationArray.add(Double.toString(value));
                screen.setText(Double.toString(value));
            }else{
                screen.setText("ERR NULL!");
            }
        }));
    }

    private static void setOnPercentPressed(Button negative){
        negative.setOnAction((e -> {
            if(!calculationArray.isEmpty()) {
                double value = Double.parseDouble(calculationArray.get(calculationArray.size() - 1));
                value /= 100;
                calculationArray.set((calculationArray.size() - 1), Double.toString(value));
                screen.setText(Double.toString(value));
            }else{
                screen.setText("ERR NULL!");
            }
        }));
    }

    private static Button createButton(String value) {
        Button calculatorButton = new Button(value);
        if(value.equals(ZERO)){
            calculatorButton.setMinWidth(115);
        }else {
            calculatorButton.setMinWidth(57.5);
        }
        calculatorButton.setMinHeight(54);
        if (value.equals(EQUALS)) {
            setOnEqualsPressed(calculatorButton);
        }else if (operators.contains(value)) {
            setOnArithmeticPressed(calculatorButton);
        }else if(value.equals(CLEAR)) {
            setOnClearPressed(calculatorButton);
        }else if(value.equals(NEGATIVE)){
            setOnNegativePressed(calculatorButton);
        }else if(value.equals(PERCENT)){
            setOnPercentPressed(calculatorButton);
        }else {
            setOnNumberPressed(calculatorButton);
        }
        return calculatorButton;
    }

    // Why isn't this part of the grid? I think it would be simpler if it was.
    private static HBox createLastRow(){
        HBox lastRow = new HBox();
        lastRow.getChildren().addAll(createButton("0"),
                createButton("."),createButton(EQUALS));
        return lastRow;
    }

    private static void removeIndexes(){
        System.out.println("BEFORE");
        for(int i = 0; i < calculationArray.size(); i++){
            System.out.println("INDEX " + i + ": " + calculationArray.get(i));
        }
        calculationArray.remove(1);
        calculationArray.remove(1);
        System.out.println("AFTER");
        for(int i = 0; i < calculationArray.size(); i++){
            System.out.println("INDEX " + i + ": " + calculationArray.get(i));
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
