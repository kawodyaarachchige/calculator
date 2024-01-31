package org.example;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

    public class Calculator {

        private TextField display;
        private GridPane grid;

        public Calculator() {
            grid = createGrid();
        }

        public GridPane getGrid() {
            return grid;
        }

        private GridPane createGrid() {
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setStyle("-fx-background-color: black;");

            display = createDisplay();
            grid.add(display, 0, 0, 4, 1);

            String[] buttonLabels = {
                    "+",
                    "7", "8", "9", "/",
                    "4", "5", "6", "*",
                    "1", "2", "3", "-",
                    "=", ".", "0", "C",//clear button

            };

            int row = 1;
            int col = 0;

            for (String label : buttonLabels) {
                Button button = createButton(label);
                grid.add(button, col, row);

                col++;
                if (col > 3) {
                    col = 0;
                    row++;
                }
            }

            return grid;
        }

        private TextField createDisplay() {
            TextField display = new TextField();
            display.setEditable(false);
            display.setMinHeight(50);
            display.setStyle("-fx-background-color: #B4F0DA;");
            display.setFont(Font.font("System", 25));
            display.setAlignment(Pos.CENTER_RIGHT);
            return display;
        }

        private Button createButton(String label) {
            Button button = new Button(label);
            button.setMinSize(70, 70);
            button.setStyle("-fx-background-color: #52B593;");
            button.setFont(Font.font("System", 25));
            button.setOnAction(event -> handleButtonClick(label));
            return button;
        }


        private void handleButtonClick(String label) {
            switch (label) {
                case "=":
                    calculateResult();
                    break;
                case "C":
                    clearDisplay();
                    break;
                default:
                    display.appendText(label);
            }
        }

        private void calculateResult() {
            try {
                String expression = display.getText();
                double result = evaluateExpression(expression);
                display.setText(String.valueOf(result));
            } catch (Exception e) {
                display.setText("Error");
            }
        }

        private void clearDisplay() {
            display.clear();
        }

        private double evaluateExpression(String expression) {
            try {
                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("JavaScript");
                return Double.parseDouble(engine.eval(expression).toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


