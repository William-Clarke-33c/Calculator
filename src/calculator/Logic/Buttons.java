package calculator.Logic;

import java.util.HashMap;
import java.util.Map;

public class Buttons {

    public static final Map<String, Integer> operators = new HashMap<String, Integer>() {
        {
            put(ButtonText.ADD.getValue(),0);
            put(ButtonText.SUBTRACT.getValue(),0);
            put(ButtonText.MULTIPLY.getValue(),1);
            put(ButtonText.DIVIDE.getValue(),1);
            put(ButtonText.EXPONENT.getValue(),2);
            put(ButtonText.LEFTPARAN.getValue(),3);
            put(ButtonText.RIGHTPARAN.getValue(),3);
        }
    };
}
