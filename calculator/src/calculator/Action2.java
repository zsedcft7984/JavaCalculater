package calculator;

import javax.swing.*;
import java.awt.event.*;

public class Action2 implements ActionListener {
    // **필드 변수 선언**
    private JTextField textField;
    private JTextField resultField;
    private JTextField operatorField;

    private static boolean firstInput = true;     // 첫 입력 상태
    private static double operand1 = 0;          // 첫 번째 피연산자
    private static double operand2 = 0;          // 두 번째 피연산자
    private static String currentOperator = "";  // 현재 연산자

    // **생성자**
    public Action2(JTextField textField, JTextField resultField, JTextField operatorField) {
        this.textField = textField;
        this.resultField = resultField;
        this.operatorField = operatorField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // **각 버튼에 대한 동작**
        if ("0123456789.".contains(command)) {        // 숫자 입력
            handleNumberInput(command);
        } else if ("+-×÷".contains(command)) {       // 연산자 입력
            handleOperatorInput(command);
        } else if (command.equals("+/-")) {          // 부호 변경
            handleSignToggle();
        } else if (command.equals("=")) {            // 계산
            handleEqualsInput();
        } else if (command.equals("C")) {            // 전체 초기화
            resetCalculator();
        } else if (command.equals("CE")) {           // 현재 입력 초기화
            textField.setText("");
        } else if (command.equals("Exit")) {         // 계산기 종료
            System.exit(0);
        }
    }

    // **숫자 및 소수점 입력 처리**
    private void handleNumberInput(String input) {
        if (firstInput) {
            textField.setText(input.equals(".") ? "0." : input);
            firstInput = false;
        } else {
            if (input.equals(".") && textField.getText().contains(".")) return;
            textField.setText(textField.getText() + input);
        }
    }

    // **연산자 입력 처리**
    private void handleOperatorInput(String operator) {
        if (!textField.getText().isEmpty()) {
            if (!currentOperator.isEmpty()) { // 이전 연산자 처리
                operand2 = Double.parseDouble(textField.getText());
                calculateResult();
            } else {
                operand1 = Double.parseDouble(textField.getText());
            }
            currentOperator = operator;
            operatorField.setText(currentOperator);
            textField.setText("");
        }
    }

    // **부호 변경 처리**
    private void handleSignToggle() {
        if (!textField.getText().isEmpty()) {
            String currentText = textField.getText();
            textField.setText(currentText.charAt(0) == '-' ? currentText.substring(1) : "-" + currentText);
        }
    }

    // **계산 실행**
    private void handleEqualsInput() {
        if (!currentOperator.isEmpty() && !textField.getText().isEmpty()) {
            operand2 = Double.parseDouble(textField.getText());
            calculateResult();
            currentOperator = "";
            firstInput = true;
        } else if (!textField.getText().isEmpty()) {
            resultField.setText(textField.getText());
        }
    }

    // **결과 계산**
    private void calculateResult() {
        double result = 0;
        try {
            switch (currentOperator) {
                case "+" -> result = operand1 + operand2;
                case "-" -> result = operand1 - operand2;
                case "×" -> result = operand1 * operand2;
                case "÷" -> {
                    if (operand2 == 0) throw new ArithmeticException("Division by Zero");
                    result = operand1 / operand2;
                }
            }
            operand1 = result; // 결과를 operand1에 저장
            updateResultField(result);
        } catch (ArithmeticException e) {
            resultField.setText("Error");
            resetCalculator();
        }
    }

    // **결과 필드 업데이트**
    private void updateResultField(double result) {
        resultField.setText(result == (int) result ? String.format("%d", (int) result) : String.valueOf(result));
    }

    // **전체 초기화**
    private void resetCalculator() {
        textField.setText("");
        resultField.setText("");
        operand1 = operand2 = 0;
        currentOperator = "";
        operatorField.setText("");
        firstInput = true;
    }
}
