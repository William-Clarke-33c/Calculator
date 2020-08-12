package calculator.Logic;

import java.util.ArrayList;

public class Body implements ButtonLogic {
    
    private String RESULT = "";
    private final ArrayList<String> calculationArray = new ArrayList<>();
    private String undefined = "undefined";
    private String error = "ERR NULL!";
    public boolean equalsClicked = false;
    TextDisplay mainScreen;
    TextDisplay calculationScreen;
    
    public Body(TextDisplay mainScreen,
                TextDisplay calculationScreen) {
        this.mainScreen = mainScreen;
        this.calculationScreen = calculationScreen;
    }
    
    public void doButtonLogic(ButtonText buttonText){
        switch(buttonText){
            case ZERO:
            case ONE:
            case TWO:
            case THREE:
            case FOUR:
            case FIVE:
            case SIX:
            case SEVEN:
            case EIGHT:
            case NINE:
            case PERIOD:
                setOnNumberPressed(buttonText);
                break;

            case ADD:
            case SUBTRACT:
            case DIVIDE:
            case MULTIPLY:
            case EXPONENT:
            case LEFTPARAN:
            case RIGHTPARAN:
                setOnOperatorPressed(buttonText);
                break;

            case EQUALS:
                setOnEqualsPressed();
                break;

            case BACK:
                setOnBackPressed();
                break;

            case CLEAR:
                setOnClearPressed();
                break;

            case PERCENT:
                setOnPercentPressed();
                break;

            case NEGATIVE:
                setOnNegativePressed();
                break;
        }
    }

    public void setOnNumberPressed(ButtonText buttontext) {
            if (calculationArray.isEmpty()) {
                calculationArray.add(buttontext.getValue());
                mainScreen.setText(buttontext.getValue());
                calculationScreen.setText(buttontext.getValue());
            } else if (Buttons.operators.contains(calculationArray.get(calculationArray.size() - 1))) {
                calculationArray.add(buttontext.getValue());
                mainScreen.setText(buttontext.getValue());
                String calculationString = calculationScreen.getText();
                calculationString += (" " + buttontext.getValue());
                calculationScreen.setText(calculationString);
            } else if(equalsClicked == true) {
                calculationArray.clear();
                calculationArray.add(buttontext.getValue());
                mainScreen.setText(buttontext.getValue());
                calculationScreen.setText(buttontext.getValue());
                equalsClicked = false;
            }else{
                String newValue = calculationArray.get(calculationArray.size() - 1);
                newValue += buttontext.getValue();
                calculationArray.set((calculationArray.size() - 1), newValue);
                mainScreen.setText(newValue);
                String calculationString = calculationScreen.getText();
                calculationString += buttontext.getValue();
                calculationScreen.setText(calculationString);
            }
    }

    public void setOnOperatorPressed(ButtonText buttonText) {
            equalsClicked = false;
            if (!calculationArray.isEmpty()) {
                calculationArray.add(buttonText.getValue());
                String calculationString = calculationScreen.getText();
                calculationString += (" " + buttonText.getValue());
                calculationScreen.setText(calculationString);
            } else if(calculationArray.isEmpty()) {
                if(buttonText.getValue().equals("(") || buttonText.getValue().equals(")"))  {
                    calculationArray.add(buttonText.getValue());
                    calculationScreen.setText(buttonText.getValue());
                }
            } else {
                mainScreen.setText(error);
            }
    }

    public void setOnEqualsPressed() {
            if (isCalculationArrayFilled()) {
                checkForParanthesis();
            } else {
                System.out.println("lmao wtf");
                mainScreen.setText(error);
            }
            mainScreen.setText(RESULT);
            RESULT = "";
            equalsClicked = true;
    }


    public void setOnClearPressed(){
            if(!calculationArray.isEmpty()){
                calculationArray.clear();
            }
            mainScreen.setText("0");
            calculationScreen.setText("0");
    }

    public void setOnNegativePressed(){
            if(!calculationArray.isEmpty()) {
                int index = calculationArray.size() - 1;
                double value = Double.parseDouble(calculationArray.get(calculationArray.size() - 1));
                value *= -1;
                calculationArray.set(index, Double.toString(value));
                mainScreen.setText(Double.toString(value));
                calculationScreen.setText(String.join(" ", calculationArray));
            }else{
                mainScreen.setText(error);
            }
    }

    public void setOnPercentPressed(){
            if(!calculationArray.isEmpty()) {
                double value = Double.parseDouble(calculationArray.get(calculationArray.size() - 1));
                value /= 100;
                calculationArray.set((calculationArray.size() - 1), Double.toString(value));
                mainScreen.setText(Double.toString(value));
            }else{
                mainScreen.setText(error);
            }
    }

    public void setOnBackPressed(){
            if(!calculationArray.isEmpty()) {
                int lastIndex = calculationArray.size() - 1;
                calculationArray.remove(lastIndex);
                calculationScreen.setText(String.join(" ", calculationArray));
                if(calculationArray.size() == 0){
                    mainScreen.setText("0");
                }
            }else{
                mainScreen.setText(error);
            }
    }

    private boolean isCalculationArrayFilled() {
        return !calculationArray.isEmpty() && calculationArray.size() >= 3;
    }

    //search for deepest left paran and stop when you see first right, do evaluation between those indexes
    private void checkForParanthesis(){
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

    private void doCalculations(ArrayList<String> expression){
        while(expression.size() > 1) {
            while (expression.contains(ButtonText.EXPONENT.getValue())){
                int index = expression.indexOf(ButtonText.EXPONENT.getValue());
                RESULT = Double.toString(
                        Math.pow(Double.parseDouble(expression.get(index - 1)) ,
                                Double.parseDouble(expression.get(index + 1))));
                updateCalculationWithResult(index, expression);
            }
            while (expression.contains(ButtonText.MULTIPLY.getValue())){
                int index = expression.indexOf(ButtonText.MULTIPLY.getValue());
                RESULT = Double.toString(Double.parseDouble(expression.get(index - 1)) *
                        Double.parseDouble(expression.get(index + 1)));
                updateCalculationWithResult(index, expression);
            }
            while (expression.contains(ButtonText.DIVIDE.getValue())){
                int index = expression.indexOf(ButtonText.DIVIDE.getValue());
                if(expression.get(index + 1).equals("0")){
                    RESULT = undefined;
                    calculationScreen.setText(error);
                    expression.clear();
                    break;
                }
                RESULT = Double.toString(Double.parseDouble(expression.get(index - 1)) /
                        Double.parseDouble(expression.get(index + 1)));
                updateCalculationWithResult(index, expression);
            }
            while (expression.contains(ButtonText.ADD.getValue())) {
                int index = expression.indexOf(ButtonText.ADD.getValue());
                System.out.println("ADDING: " + expression.get(index - 1) + " AND " +
                        expression.get(index + 1));
                RESULT = Double.toString(Double.parseDouble(expression.get(index - 1)) +
                        Double.parseDouble(expression.get(index + 1)));
                System.out.println("RESULT: " + RESULT);
                updateCalculationWithResult(index, expression);
            }
            while (expression.contains(ButtonText.SUBTRACT.getValue())){
                int index = expression.indexOf(ButtonText.SUBTRACT.getValue());
                RESULT = Double.toString(Double.parseDouble(expression.get(index - 1)) -
                        Double.parseDouble(expression.get(index + 1)));
                updateCalculationWithResult(index, expression);
            }
        }
    }

    private void updateCalculationWithResult(int operatorIndex, ArrayList<String> expression){
        expression.remove(operatorIndex);
        expression.remove(operatorIndex);
        if(operatorIndex >= 1){
            expression.set((operatorIndex - 1), RESULT);
        }
    }

}
