package calculator.Logic;

public enum ButtonText {

    BACK("⬅"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    ZERO("0"),
    DIVIDE("÷"),
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    EQUALS("="),
    NEGATIVE("+/-"),
    PERCENT("%"),
    EXPONENT("^"),
    LEFTPARAN("("),
    RIGHTPARAN(")"),
    PERIOD("."),
    CLEAR("AC");

    private String value;

    public String getValue()
    {
        return this.value;
    }

    ButtonText(String value)
    {
        this.value = value;
    }
}
