package calculator;

import javax.swing.*;
import java.awt.event.*;

public class Action implements ActionListener {
    private JTextField textField;   // 수식 입력용 필드
    private JTextField resultField; // 결과 표시용 필드
    private static boolean firstInput = true; // 첫 입력 상태
    private static double operand1 = 0;  // 첫 번째 피연산자
    private static double operand2 = 0;  // 두 번째 피연산자
    private static double previousResult = 0;  // 이전 계산값 (결과 기억)
    private static String operator = ""; // 연산자
    private int count =0;
    // 생성자
    public Action(JTextField textField, JTextField resultField) {
        this.textField = textField;
        this.resultField = resultField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        String buttonText = sourceButton.getText();

        if (firstInput) {
            // 첫 입력은 0이 아닌 숫자만 허용
            if (buttonText.matches("[1-9]")) {
                textField.setText(buttonText);
                resultField.setText(buttonText); // resultField 업데이트
                firstInput = false;
                operand1 = Double.parseDouble(buttonText); // 첫 번째 피연산자 설정
            }
        } else {
            String currentText = textField.getText();
           
            if (buttonText.matches("[0-9]")) {
                // 숫자 추가
                String updatedText = currentText + buttonText;
                textField.setText(updatedText);
                resultField.setText(updatedText); // resultField 업데이트
            } else if (buttonText.equals("+") || buttonText.equals("-") || 
                        buttonText.equals("×") ||buttonText.equals("÷")) {
                // 연산자 입력 시 처리
                operand1 = Double.parseDouble(currentText); // 이전 값 저장
                operator = buttonText; // 연산자 설정
                textField.setText(""); // 텍스트 필드 초기화
                resultField.setText(formatNumber(operand1) + " " + operator); // 수식 표시
                count++;
            } else if (buttonText.equals("=")) {
                // "=" 버튼 처리
                if (!currentText.isEmpty()) {
                    operand2 = Double.parseDouble(currentText); // 두 번째 피연산자
                    double result = calculate(operand1, operand2, operator);

                    if (Double.isNaN(result)) {
                        resultField.setText("Error: Division by zero");
                    } else {
                        resultField.setText(formatNumber(result)); // 결과 표시
                        previousResult = result; // 이전 계산값 기억
                        operand1 = result; // 결과를 다음 계산의 operand1으로 설정
                    }
                }
                textField.setText(""); // 텍스트 필드 초기화
                firstInput = true;
            } else if (buttonText.equals("C")) {
                // 전체 초기화
                textField.setText("");
                resultField.setText("");
                firstInput = true;
                operand1 = 0;
                operand2 = 0;
                previousResult = 0;
                operator = "";
            } else if (buttonText.equals("CE")) {
                // 현재 텍스트 필드 초기화
                if (!currentText.isEmpty()) {
                    String updatedText = currentText.substring(0, currentText.length() - 1);
                    textField.setText(updatedText);
                    resultField.setText(updatedText); // resultField 업데이트
                }
            }
        }
    }

    // 사칙연산 계산 메서드
    private double calculate(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+": return operand1 + operand2;
            case "-": return operand1 - operand2;
            case "x": return operand1 * operand2; // 곱셈 처리
            case "×": return operand1 * operand2; // 곱셈 처리 (×로 입력된 경우)
            case "÷": return operand2 != 0 ? operand1 / operand2 : Double.NaN; // 나눗셈 처리
            default: return 0;
        }
    }

    // 숫자 형식화 메서드
    private String formatNumber(double number) {
        // 결과값이 정수면 소수점 제거
        if (number == (int) number) {
            return String.valueOf((int) number);
        } else {
            return String.valueOf(number);
        }
    }
}
