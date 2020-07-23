package calculator;

import javafx.scene.control.Button;

import java.util.ArrayList;

public class Body {

    private static String RESULT = "";
    private static final ArrayList<String> calculationArray = new ArrayList<>();
    public static boolean equalsClicked = false;

    public static void setOnNumberPressed(Button number) {
        number.setOnAction(e -> {
            if (calculationArray.isEmpty()) {
                calculationArray.add(number.getText());
                Screen.mainScreen.setText(number.getText());
                Screen.calculationScreen.setText(number.getText());
            } else if (Buttons.operators.contains(calculationArray.get(calculationArray.size() - 1))) {
                calculationArray.add(number.getText());
                Screen.mainScreen.setText(number.getText());
                String calculationString = Screen.calculationScreen.getText();
                calculationString += (" " + number.getText());
                Screen.calculationScreen.setText(calculationString);
            } else if(equalsClicked == true) {
                calculationArray.clear();
                calculationArray.add(number.getText());
                Screen.mainScreen.setText(number.getText());
                Screen.calculationScreen.setText(number.getText());
                equalsClicked = false;
            }else{
                String newValue = calculationArray.get(calculationArray.size() - 1);
                newValue += number.getText();
                calculationArray.set((calculationArray.size() - 1), newValue);
                Screen.mainScreen.setText(newValue);
                String calculationString = Screen.calculationScreen.getText();
                calculationString += number.getText();
                Screen.calculationScreen.setText(calculationString);
            }
        });
    }

    public static void setOnArithmeticPressed(Button arithmetic) {
        arithmetic.setOnAction(e -> {
            equalsClicked = false;
            if (!calculationArray.isEmpty() || arithmetic.getText().equals("(") ||
                    arithmetic.getText().equals(")")) {
                calculationArray.add(arithmetic.getText());
                String calculationString = Screen.calculationScreen.getText();
                calculationString += (" " + arithmetic.getText());
                Screen.calculationScreen.setText(calculationString);
            } else {
                Screen.mainScreen.setText("ERR NULL!");
            }
        });
    }

    public static void setOnEqualsPressed(Button equals) {
        equals.setOnAction((e -> {
            if (isCalculationArrayFilled()) {
                checkForParanthesis();
            } else {
                Screen.mainScreen.setText("ERR NULL!");
            }
            Screen.mainScreen.setText(RESULT);
            RESULT = "";
            equalsClicked = true;
        }));
    }


    public static void setOnClearPressed(Button clear){
        clear.setOnAction((e -> {
            if(!calculationArray.isEmpty()){
                calculationArray.clear();
            }
            Screen.mainScreen.setText("0");
            Screen.calculationScreen.setText("0");
        }));
    }

    public static void setOnNegativePressed(Button negative){
        negative.setOnAction((e -> {
            if(!calculationArray.isEmpty()) {
                double value = Double.parseDouble(calculationArray.get(calculationArray.size() - 1));
                value *= -1;
                calculationArray.add(Double.toString(value));
                Screen.mainScreen.setText(Double.toString(value));
            }else{
                Screen.mainScreen.setText("ERR NULL!");
            }
        }));
    }

    public static void setOnPercentPressed(Button negative){
        negative.setOnAction((e -> {
            if(!calculationArray.isEmpty()) {
                double value = Double.parseDouble(calculationArray.get(calculationArray.size() - 1));
                value /= 100;
                calculationArray.set((calculationArray.size() - 1), Double.toString(value));
                Screen.mainScreen.setText(Double.toString(value));
            }else{
                Screen.mainScreen.setText("ERR NULL!");
            }
        }));
    }

    public static void setOnBackPressed(Button back){
        back.setOnAction((e -> {
            if(!calculationArray.isEmpty()) {
                int lastIndex = calculationArray.size() - 1;
                String calculationString = "";
                calculationArray.remove(lastIndex);
                Screen.calculationScreen.setText(String.join(" ", calculationArray));
                if(calculationArray.size() == 0){
                    Screen.mainScreen.setText("0");
                }
            }else{
                Screen.mainScreen.setText("ERR NULL!");
            }
        }));
    }

    private static boolean isCalculationArrayFilled() {
        return !calculationArray.isEmpty() && calculationArray.size() >= 3;
    }

    //search for deepest left paran and stop when you see first right, do evaluation between those indexes
    private static void checkForParanthesis(){
        while(calculationArray.contains("(") && calculationArray.contains(")")){
            int lastLeftParanIndex = 0;
            int firstRightParanIndex = 0;
            for(int i = 0; i < calculationArray.size(); i++){
                if(calculationArray.get(i).equals("(")){
                    lastLeftParanIndex = i;
                }
                if(calculationArray.get(i).equals(")")){
                    firstRightParanIndex = i;
                    break;
                }
            }
            ArrayList<String> expression = new ArrayList<>();
            for(int i = lastLeftParanIndex + 1; i < firstRightParanIndex; i++){
                expression.add(calculationArray.get(i));
            }
            doCalculations(expression);
            int removeCount = (firstRightParanIndex - lastLeftParanIndex);
            int removeIndex = lastLeftParanIndex + 1;
            while(removeCount > 0){
                calculationArray.remove(removeIndex);
                removeCount--;
            }
            calculationArray.set((lastLeftParanIndex), RESULT);
        }
        doCalculations(calculationArray);

    }

    private static void doCalculations(ArrayList<String> expression){
        while(expression.size() > 1) {
            while (expression.contains(Buttons.EXPONENT)){
                int index = expression.indexOf(Buttons.EXPONENT);
                RESULT = Double.toString(
                        Math.pow(Double.parseDouble(expression.get(index - 1)) ,
                                Double.parseDouble(expression.get(index + 1))));
                updateCalculationWithResult(index, expression);
            }
            while (expression.contains(Buttons.MULTIPLY)){
                int index = expression.indexOf(Buttons.MULTIPLY);
                RESULT = Double.toString(Double.parseDouble(expression.get(index - 1)) *
                        Double.parseDouble(expression.get(index + 1)));
                updateCalculationWithResult(index, expression);
            }
            while (expression.contains(Buttons.DIVIDE)){
                int index = expression.indexOf(Buttons.DIVIDE);
                if(expression.get(index + 1).equals("0")){
                    RESULT = "undefined";
                    Screen.calculationScreen.setText("ERR!");
                    expression.clear();
                    break;
                }
                RESULT = Double.toString(Double.parseDouble(expression.get(index - 1)) /
                        Double.parseDouble(expression.get(index + 1)));
                updateCalculationWithResult(index, expression);
            }
            while (expression.contains(Buttons.ADD)) {
                int index = expression.indexOf(Buttons.ADD);
                RESULT = Double.toString(Double.parseDouble(expression.get(index - 1)) +
                        Double.parseDouble(expression.get(index + 1)));
                updateCalculationWithResult(index, expression);
            }
            while (expression.contains(Buttons.SUBTRACT)){
                int index = expression.indexOf(Buttons.SUBTRACT);
                RESULT = Double.toString(Double.parseDouble(expression.get(index - 1)) -
                        Double.parseDouble(expression.get(index + 1)));
                updateCalculationWithResult(index, expression);
            }
        }
    }

    private static void updateCalculationWithResult(int operatorIndex, ArrayList<String> expression){
        expression.remove(operatorIndex);
        expression.remove(operatorIndex);
        if(operatorIndex >= 1){
            expression.set((operatorIndex - 1), RESULT);
        }
    }

}
