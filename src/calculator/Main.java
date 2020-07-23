package calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/* Implement an Array as a Queue */
/* Implement Shunting Yard  */
/* Implement Reverse Polish Notation */
// Clean Functions, every statement should be at the same level abstraction
//

public class Main extends Application {

    /* Declaring String Literals */




    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");
        primaryStage.setResizable(false);
        Buttons.assignOperators();
        Screen.initalizeCalculationScreen();
        Screen.initalizeMainScreen();
        VBox calculator = Calculator.createCalculator();
        Scene scene = new Scene(calculator, 275, 375);
        calculator.setStyle("-fx-background-color:  rgb(65,64,65);");
       // scene.getStylesheets().add("css/stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


















    public static void main(String[] args) {
        launch(args);
    }
}