package calculator.Logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ReversePolishNotation {
    Queue<String> calculationQueue = new LinkedList<>();
    ArrayList<String> calculationArray = new ArrayList<>();

    public ReversePolishNotation(Queue calculationQueue){
        this.calculationQueue = calculationQueue;
    }

    public String doRPN(){
        String result = "", leftValue, rightValue, operator;
        while(!calculationQueue.isEmpty()){
            calculationArray.add(calculationQueue.remove());
        }
        for(int i = 0; i < calculationArray.size(); i++){
            if(Buttons.operators.containsKey(calculationArray.get(i))){
                operator = calculationArray.get(i);
                rightValue = calculationArray.get(i - 1);
                leftValue = calculationArray.get(i - 2);
                System.out.println(calculationArray);
                calculationArray.remove(i - 2);
                calculationArray.remove(i - 2);
                System.out.println(calculationArray);
                result = doCalculations(operator,rightValue,leftValue);
                calculationArray.set(i - 2, result);
                i--;
                i--;
            }
        }
        return result;
    }

    private String doCalculations(String operator, String rightValue, String leftValue) {
        String result = "";
        System.out.println("Operator: " + operator + " Right: " + rightValue + " Left: " + leftValue);
        if(operator.equals(ButtonText.ADD.getValue())){
            result = Double.toString(Double.parseDouble(leftValue) + Double.parseDouble(rightValue));
        }
        if(operator.equals(ButtonText.SUBTRACT.getValue())){
            result = Double.toString(Double.parseDouble(leftValue) - Double.parseDouble(rightValue));
        }
        if(operator.equals(ButtonText.MULTIPLY.getValue())){
            result = Double.toString(Double.parseDouble(leftValue) * Double.parseDouble(rightValue));
        }
        if(operator.equals(ButtonText.DIVIDE.getValue())){
            result = Double.toString(Double.parseDouble(leftValue) / Double.parseDouble(rightValue));
        }
        if(operator.equals(ButtonText.EXPONENT.getValue())){
            result = Double.toString(Math.pow(Double.parseDouble(leftValue),Double.parseDouble(rightValue)));
        }
        return result;
    }
}
