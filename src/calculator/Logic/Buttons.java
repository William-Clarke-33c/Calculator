package calculator.Logic;

import java.util.Arrays;
import java.util.List;

public class Buttons {

    public static final List<String> operators = Arrays.asList(
            ButtonText.ADD.getValue(),
            ButtonText.SUBTRACT.getValue(),
            ButtonText.DIVIDE.getValue(),
            ButtonText.MULTIPLY.getValue(),
            ButtonText.EXPONENT.getValue(),
            ButtonText.LEFTPARAN.getValue(),
            ButtonText.RIGHTPARAN.getValue()
    );
}
