package calculator.Logic;

import java.util.LinkedList;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Queue;

public class ShuntingYard {
    private final Stack<String> shuntingYardStack = new Stack<>();
    private ArrayList<String> calculationArray = new ArrayList<>();
    private final Queue<String> calculationQueue = new LinkedList<>();

    public ShuntingYard(ArrayList<String> calculationArray){
        this.calculationArray = calculationArray;
    }

    public String doShuntingYard(){
        String indexValue;
        for (String s : calculationArray) {
            indexValue = s;
            if (!Buttons.operators.containsKey(indexValue)) {
                calculationQueue.add(indexValue);
            } else if (indexValue.equals(ButtonText.ADD.getValue()) || indexValue.equals(ButtonText.SUBTRACT.getValue())
                    || indexValue.equals(ButtonText.MULTIPLY.getValue()) || indexValue.equals(ButtonText.DIVIDE.getValue())
                    || indexValue.equals(ButtonText.EXPONENT.getValue())) {
                while (!shuntingYardStack.isEmpty() &&
                        Buttons.operators.get(shuntingYardStack.peek()) >=
                                Buttons.operators.get(indexValue) &&
                        (!shuntingYardStack.peek().equals(ButtonText.LEFTPARAN.getValue()))) {
                    calculationQueue.add(shuntingYardStack.pop());
                }
                shuntingYardStack.push(indexValue);
            } else if (indexValue.equals(ButtonText.LEFTPARAN.getValue())) {
                shuntingYardStack.push(indexValue);
            } else if (indexValue.equals(ButtonText.RIGHTPARAN.getValue())) {
                while (!shuntingYardStack.isEmpty() &&
                        !shuntingYardStack.peek().equals(ButtonText.LEFTPARAN.getValue())) {
                    calculationQueue.add(shuntingYardStack.pop());
                }
                if (shuntingYardStack.peek().equals(ButtonText.LEFTPARAN.getValue())) {
                    shuntingYardStack.pop();
                }
            }
        }
        while(!shuntingYardStack.isEmpty()){
            calculationQueue.add(shuntingYardStack.pop());
        }
        return new ReversePolishNotation(calculationQueue).doRPN();
    }
}
