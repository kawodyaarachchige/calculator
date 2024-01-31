package org.example;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

    public class Launcher extends Application {

        @Override
        public void start(Stage primaryStage) {
            Calculator calculatorUI = new Calculator();
            Scene scene = new Scene(calculatorUI.getGrid(), 500, 500);
            primaryStage.setTitle("Calculator");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }


