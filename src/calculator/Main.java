package calculator;

import javafx.application.Application;
import javafx.geometry.Pos;
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
    private static final String EXPONENT = "^";
    private static final String LEFTPARAN = "(";
    private static final String RIGHTPARAN = ")";
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
    private static String RESULT = "";
    private static boolean equalsClicked = false;
    private static boolean firstNumber = true;
    private static final ArrayList<String> calculationArray = new ArrayList<>();
    static final ArrayList<String> operators = new ArrayList<>();
    static final TextField mainScreen = new TextField();
    static final TextField calculationScreen = new TextField();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");
        primaryStage.setResizable(false);
        assignOperators();
        initalizeCalculationScreen();
        initalizeMainScreen();
        VBox calculator = createCalculator();
        Scene scene = new Scene(calculator, 275, 375);
        calculator.setStyle("-fx-background-color:  rgb(65,64,65);");
       // scene.getStylesheets().add("css/stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void assignOperators() {
        operators.add(ADD);
        operators.add(SUBTRACT);
        operators.add(DIVIDE);
        operators.add(MULTIPLY);
        operators.add(EXPONENT);
        operators.add(LEFTPARAN);
        operators.add(RIGHTPARAN);
    }

    private void initalizeCalculationScreen(){
        calculationScreen.setAlignment(Pos.CENTER_RIGHT);
        calculationScreen.setMinHeight(25);
        calculationScreen.setDisable(true);
        calculationScreen.setStyle(
                "-fx-opacity: 1;\n" + "-fx-background-color:  rgb(65,64,65);\n" +
                        "-fx-text-fill: rgba(255, 255, 225, 0.3);\n" +
                        "    -fx-border-width: 0;\n" +"-fx-font-size: 12px;");
        calculationScreen.setText("");
    }

    private void initalizeMainScreen(){
        mainScreen.setAlignment(Pos.CENTER_RIGHT);
        mainScreen.setMinHeight(50);
        mainScreen.setDisable(true);
        mainScreen.setStyle(
                "-fx-opacity: 1;\n" + "-fx-background-color:  rgb(65,64,65);\n" +
                        "-fx-border-width: 0;\n" +
                "-fx-text-fill: rgb(255, 255, 225);\n" + "-fx-font-size: 24px;");
    }

    private static VBox createCalculator() {
        GridPane grid = new GridPane();
        addButtons(grid);
        VBox calculator = new VBox();
        calculator.getChildren().addAll(calculationScreen,mainScreen, grid, createLastRow());
        return calculator;
    }

    private static void addButtons(GridPane grid) {
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
        grid.add(mainScreen, 0, 0);
    }

    private static void setOnNumberPressed(Button number) {
        number.setOnAction(e -> {
            if (calculationArray.isEmpty()) {
                calculationArray.add(number.getText());
                mainScreen.setText(number.getText());
                calculationScreen.setText(number.getText());
            } else if (operators.contains(calculationArray.get(calculationArray.size() - 1))) {
                System.out.println("PUSHING");
                calculationArray.add(number.getText());
                mainScreen.setText(number.getText());
                String calculationString = calculationScreen.getText();
                calculationString += (" " + number.getText());
                calculationScreen.setText(calculationString);
            } else if(equalsClicked == true) {
                calculationArray.clear();
                calculationArray.add(number.getText());
                mainScreen.setText(number.getText());
                calculationScreen.setText(number.getText());
                equalsClicked = false;
            }else{
                System.out.println("Peek: " + calculationArray.get(calculationArray.size() - 1));
                String newValue = calculationArray.get(calculationArray.size() - 1);
                System.out.println("Adding this on: " + number.getText());
                newValue += number.getText();
                calculationArray.set((calculationArray.size() - 1), newValue);
                mainScreen.setText(newValue);
                String calculationString = calculationScreen.getText();
                calculationString += number.getText();
                calculationScreen.setText(calculationString);
                System.out.println("New Value: " + newValue);
            }
        });
    }

    private static void setOnArithmeticPressed(Button arithmetic) {
        arithmetic.setOnAction(e -> {
            equalsClicked = false;
            if (!calculationArray.isEmpty()) {
                System.out.println(arithmetic.getText());
                calculationArray.add(arithmetic.getText());
                String calculationString = calculationScreen.getText();
                calculationString += (" " + arithmetic.getText());
                calculationScreen.setText(calculationString);
            } else {
                System.out.println("ERR NULL!");
            }
        });
    }

//search for deepest left paran and stop when you see first right, do evaluation between those indexes
    private static void checkForParanthesis(ArrayList<String> expression){
        int currentIndex = 0;
        for(int i = 0; i < expression.size(); i++){
            if(expression.get(i).equals("(")){
                currentIndex = i;
            }
        }

    }
    private static void setOnEqualsPressed(Button equals) {
        equals.setOnAction((e -> {
            if (isCalculationArrayFilled()) {
                while(calculationArray.size() > 1) {
                        while (calculationArray.contains(EXPONENT)){
                            int index = calculationArray.indexOf(EXPONENT);
                            RESULT = Double.toString(
                                    Math.pow(Double.parseDouble(calculationArray.get(index - 1)) ,
                                            Double.parseDouble(calculationArray.get(index + 1))));
                            updateCalculationWithResult(index);
                        }
                        while (calculationArray.contains(MULTIPLY)){
                            int index = calculationArray.indexOf(MULTIPLY);
                            RESULT = Double.toString(Double.parseDouble(calculationArray.get(index - 1)) *
                                    Double.parseDouble(calculationArray.get(index + 1)));
                            updateCalculationWithResult(index);
                        }
                        while (calculationArray.contains(DIVIDE)){
                            int index = calculationArray.indexOf(DIVIDE);
                            if(calculationArray.get(index + 1).equals("0")){
                                RESULT = "undefined";
                                calculationScreen.setText("ERR!");
                                calculationArray.clear();
                                break;
                            }
                            RESULT = Double.toString(Double.parseDouble(calculationArray.get(index - 1)) /
                                    Double.parseDouble(calculationArray.get(index + 1)));
                            updateCalculationWithResult(index);
                        }
                        while (calculationArray.contains(ADD)) {
                            int index = calculationArray.indexOf(ADD);
                            RESULT = Double.toString(Double.parseDouble(calculationArray.get(index - 1)) +
                                    Double.parseDouble(calculationArray.get(index + 1)));
                            updateCalculationWithResult(index);
                        }
                        while (calculationArray.contains(SUBTRACT)){
                            int index = calculationArray.indexOf(SUBTRACT);
                            RESULT = Double.toString(Double.parseDouble(calculationArray.get(index - 1)) -
                                    Double.parseDouble(calculationArray.get(index + 1)));
                            updateCalculationWithResult(index);
                        }
                }
            } else {
                mainScreen.setText("ERR NULL!");
            }
            mainScreen.setText(RESULT);
            RESULT = "";
            equalsClicked = true;
        }));
    }

    private static boolean isCalculationArrayFilled() {
        return !calculationArray.isEmpty() && calculationArray.size() >= 3;
    }

    private static void setOnClearPressed(Button clear){
        clear.setOnAction((e -> {
            if(!calculationArray.isEmpty()){
                calculationArray.clear();
            }
            mainScreen.setText("0");
            calculationScreen.setText("0");
        }));
    }

    private static void setOnNegativePressed(Button negative){
        negative.setOnAction((e -> {
            if(!calculationArray.isEmpty()) {
                double value = Double.parseDouble(calculationArray.get(calculationArray.size() - 1));
                value *= -1;
                calculationArray.add(Double.toString(value));
                mainScreen.setText(Double.toString(value));
            }else{
                mainScreen.setText("ERR NULL!");
            }
        }));
    }

    private static void setOnPercentPressed(Button negative){
        negative.setOnAction((e -> {
            if(!calculationArray.isEmpty()) {
                double value = Double.parseDouble(calculationArray.get(calculationArray.size() - 1));
                value /= 100;
                calculationArray.set((calculationArray.size() - 1), Double.toString(value));
                mainScreen.setText(Double.toString(value));
            }else{
                mainScreen.setText("ERR NULL!");
            }
        }));
    }

    private static void setOnBackPressed(Button back){
        back.setOnAction((e -> {
            if(!calculationArray.isEmpty()) {
                int lastIndex = calculationArray.size() - 1;
                String calculationString = "";
                calculationArray.remove(lastIndex);
                calculationScreen.setText(String.join(" ", calculationArray));
                if(calculationArray.size() == 0){
                    mainScreen.setText("0");
                }
            }else{
                mainScreen.setText("ERR NULL!");
            }
        }));
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
            setOnEqualsPressed(calculatorButton);
        }else if (operators.contains(value)) {
            setOnArithmeticPressed(calculatorButton);
        }else if(value.equals(CLEAR)) {
            setOnClearPressed(calculatorButton);
        }else if(value.equals(NEGATIVE)){
            setOnNegativePressed(calculatorButton);
        }else if(value.equals(PERCENT)){
            setOnPercentPressed(calculatorButton);
        }else if(value.equals(BACK)){
            setOnBackPressed(calculatorButton);
        }else {
            setOnNumberPressed(calculatorButton);
        }
        calculatorButton.setStyle("" +
                "    -fx-text-fill: rgb(255, 255, 255);\n" +
                "    -fx-border-color: rgb(255, 255, 255);\n" +
                "    -fx-border-width: 0.25;\n" +
                "    -fx-background-color: #5ac8fa;");
        return calculatorButton;
    }

    private static HBox createLastRow(){
        HBox lastRow = new HBox();
        lastRow.getChildren().addAll(createButton("0"),
                createButton("."),createButton(EQUALS));
        return lastRow;
    }

    private static void updateCalculationWithResult(int operatorIndex){
        System.out.println("BEFORE");
        for(int i = 0; i < calculationArray.size(); i++){
            System.out.println("INDEX " + i + ": " + calculationArray.get(i));
        }
        calculationArray.remove(operatorIndex);
        calculationArray.remove(operatorIndex);
        if(operatorIndex >= 1){
            calculationArray.set((operatorIndex - 1), RESULT);
            System.out.println("RESULT: " + RESULT);
        }
        System.out.println("AFTER");
        for(int i = 0; i < calculationArray.size(); i++){
            System.out.println("INDEX " + i + ": " + calculationArray.get(i));
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
