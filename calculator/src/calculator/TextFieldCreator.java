package calculator;

import javax.swing.*;
import java.awt.*;

public class TextFieldCreator {

    // 수식 입력용 텍스트 필드를 생성하는 메서드
    public static JTextField createFormulaTextField() {
        JTextField textField = new JTextField("0");
        textField.setFont(new Font("Arial", Font.PLAIN, 30));  // 폰트 크기 줄이기
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setBackground(Color.lightGray);
        textField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        return textField;
    }

    // 연산자 표시용 텍스트 필드를 생성하는 메서드
    public static JTextField createOperatorTextField() {
        JTextField operatorField = new JTextField();
        operatorField.setFont(new Font("Arial", Font.BOLD, 26));  // 연산자 폰트 크기 설정
        operatorField.setHorizontalAlignment(SwingConstants.LEFT);  // 왼쪽 정렬로 변경
        operatorField.setBackground(Color.WHITE);
        operatorField.setEditable(false);  // 편집 불가
        operatorField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        return operatorField;
    }

    // 결과 표시용 텍스트 필드를 생성하는 메서드
    public static JTextField createResultTextField() {
        JTextField resultField = new JTextField();
        resultField.setFont(new Font("Arial", Font.BOLD, 40));  // 폰트 크기 설정
        resultField.setHorizontalAlignment(SwingConstants.RIGHT);
        resultField.setBackground(Color.WHITE);
        resultField.setEditable(false);  // 편집 불가
        resultField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 세로 크기 줄이기
        resultField.setPreferredSize(new Dimension(0, 60));  // 세로 크기 60으로 설정

        return resultField;
    }
}
