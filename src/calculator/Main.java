package calculator;

import calculator.Display.CalculationScreenDisplay;
import calculator.Display.MainScreenDisplay;
import calculator.Logic.Body;
import calculator.Logic.ButtonLogic;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/* Implement an Array as a Queue */
/* Implement Shunting Yard  */
/* Implement Reverse Polish Notation */
// Clean Functions, every statement should be at the same level abstraction
//

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");
        primaryStage.setResizable(false);
        MainScreenDisplay mainScreen = new MainScreenDisplay();
        CalculationScreenDisplay calculationScreen = new CalculationScreenDisplay();
        ButtonLogic calculatorBody = new Body(mainScreen,calculationScreen);
        Calculator calculator = new Calculator(mainScreen, calculationScreen, calculatorBody);
        Scene scene = new Scene(calculator, 275, 375);
        calculator.setStyle("-fx-background-color:  rgb(65,64,65);");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}