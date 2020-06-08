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
import java.util.Stack;

/* Implement an Array as a Queue */

public class Main extends Application {

    private static final Stack calculationStack = new Stack();
    //private static final ArrayList<String> calculationArray = new ArrayList<>();
    static final ArrayList<String> operators = new ArrayList<>();
    static final ArrayList<Integer> numbers = new ArrayList<>();
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
        operators.add("+");
        operators.add("-");
        operators.add("÷");
        operators.add("x");
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
        grid.add(createButton("1"), 0, 4);
        grid.add(createButton("2"), 1, 4);
        grid.add(createButton("3"), 2, 4);
        grid.add(createButton("+"), 3, 4);
        grid.add(createButton("4"), 0, 3);
        grid.add(createButton("5"), 1, 3);
        grid.add(createButton("6"), 2, 3);
        grid.add(createButton("-"), 3, 3);
        grid.add(createButton("7"), 0, 2);
        grid.add(createButton("8"), 1, 2);
        grid.add(createButton("9"), 2, 2);
        grid.add(createButton("x"), 3, 2);
        grid.add(createButton("AC"), 0, 1);
        grid.add(createButton("+/-"), 1, 1);
        grid.add(createButton("%"), 2, 1);
        grid.add(createButton("÷"), 3, 1);
        grid.add(screen, 0, 0);
    }

    private static void addNumberEvent(Button number) {
        number.setOnAction(e -> {
            if (calculationStack.isEmpty()) {
                calculationStack.push(number.getText());
                screen.setText(number.getText());
            /* Make your calculationStack a Stack<String>, as opposed to a Stack, so you
             * don't have to call .toString() on the elements */
            } else if (operators.contains(calculationStack.peek().toString())) {
                System.out.println("PUSHING");
                calculationStack.push(number.getText());
                screen.setText(number.getText());
            } else {
                System.out.println("Peek: " + calculationStack.peek().toString());
                String newValue = calculationStack.peek().toString();
                System.out.println("Adding this on: " + number.getText());
                newValue += number.getText();
                calculationStack.pop();
                calculationStack.push(newValue);
                screen.setText(newValue);
                System.out.println("New Value: " + newValue);
            }
        });
    }

    private static void addArithmeticEvent(Button arithmetic) {
        arithmetic.setOnAction(e -> {
            if (!calculationStack.isEmpty()) {
                System.out.println(arithmetic.getText());
                calculationStack.push(arithmetic.getText());
            } else {
                System.out.println("ERR NULL!");
            }
        });
    }

    private static void addEquals(Button equals) {
        equals.setOnAction((e -> {
            String operation = "";
            if (!calculationStack.isEmpty()) {
                while (!calculationStack.isEmpty()) {
                    if(operators.contains(calculationStack.peek().toString())){
                        operation = calculationStack.pop().toString();
                    }else{
                        numbers.add(Integer.parseInt(calculationStack.pop().toString()));
                    }
                }
            } else {
                screen.setText("ERR NULL!");
            }
            System.out.println("ValueOne: " + numbers.get(1) + " ValueTwo: " + numbers.get(0));
            int valueOne = numbers.get(1);
            int valueTwo = numbers.get(0);
            /* Usually it's good practice to make all string literals into static globals. E.g you would declare
            *  private static final String DIVIDE = "÷"; and replace any use of "÷" with DIVIDE.
            *  #1 it helps prevent typos, and #2 it makes it easier to change later, like if you wanted to replace
            *  the division symbol with "/". Yes, I think you should do it for the numbers too. */
            if(operation.equals("+")){
                calculationStack.push(valueOne + valueTwo);
                screen.setText(Integer.toString(valueOne + valueTwo));
                numbers.clear();
            }
            if(operation.equals(("-"))){
                calculationStack.push(valueOne - valueTwo);
                screen.setText(Integer.toString(valueOne - valueTwo));
                numbers.clear();
            }
            if(operation.equals(("x"))){
                calculationStack.push(valueOne * valueTwo);
                screen.setText(Integer.toString(valueOne * valueTwo));
                numbers.clear();
            }
            if(operation.equals(("÷"))){
                calculationStack.push(valueOne / valueTwo);
                screen.setText(Integer.toString( valueOne / valueTwo));
                numbers.clear();
            }
        }));
    }

    // This function isn't used anymore: delete it
    private static double makeDouble(int value){
        double d = value;
        return d;
    }

    private static void addClear(Button clear){
        clear.setOnAction((e -> {
            while(!calculationStack.isEmpty()){
                calculationStack.pop();
                screen.setText("");
                numbers.clear();
            }
            // nitpick: instead of .equals(""), use .isEmpty()
            if(!screen.getText().equals("")){
                screen.setText("");
                numbers.clear();
            }
        }));
    }

    private static void addNegative(Button negative){
        negative.setOnAction((e -> {
            if(!calculationStack.isEmpty()) {
                int value = Integer.parseInt(calculationStack.pop().toString());
                value *= -1;
                calculationStack.push(Integer.toString(value));
                screen.setText(Integer.toString(value));
            }else{
                screen.setText("ERR NULL!");
            }
        }));
    }

    private static void addPercentage(Button negative){
        negative.setOnAction((e -> {
            if(!calculationStack.isEmpty()) {
                double value = Double.parseDouble(calculationStack.pop().toString());
                value /= 100;
                calculationStack.push(Double.toString(value));
                screen.setText(Double.toString(value));
            }else{
                screen.setText("ERR NULL!");
            }
        }));
    }

    private static Button createButton(String value) {
        Button calculatorButton = new Button(value);
        if(value.equals("0")){
            calculatorButton.setMinWidth(115);
        }else {
            calculatorButton.setMinWidth(57.5);
        }
        calculatorButton.setMinHeight(54);
        /* nitpick: I think the naming of these functions is a little confusing, since "add" could also mean doing
         * math. I'd prefer something like setOnEqualsPressed, setOnOperatorPressed, setOnNumberPressed, etc. */
        if (value.equals("=")) {
            addEquals(calculatorButton);
        }else if (operators.contains(value)) {
            addArithmeticEvent(calculatorButton);
        }else if(value.equals("AC")) {
            addClear(calculatorButton);
        }else if(value.equals("+/-")){
            addNegative(calculatorButton);
        }else if(value.equals("%")){
            addPercentage(calculatorButton);
        }else {
            addNumberEvent(calculatorButton);
        }
        return calculatorButton;
    }

    // Why isn't this part of the grid? I think it would be simpler if it was.
    private static HBox createLastRow(){
        HBox lastRow = new HBox();
        lastRow.getChildren().addAll(createButton("0"),
                createButton("."),createButton("="));
        return lastRow;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
