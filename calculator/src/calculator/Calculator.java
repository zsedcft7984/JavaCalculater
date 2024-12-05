package calculator;

import javax.swing.*;
import java.awt.*;

public class Calculator {
    private JFrame frame;
    private JTextField textField;      // 수식 입력용 필드
    private JTextField resultField;   // 결과 표시용 필드
    private JTextField operatorField; // 연산자 표시용 필드
    private JPanel buttonPanel;       // 버튼 패널

    // 생성자
    public Calculator() {
        // 1. **프레임 및 기본 설정**
        frame = new JFrame("계산기");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 550);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setMinimumSize(new Dimension(350, 550));

        // 2. **좌측 패널 설정**
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout(10, 10));
        leftPanel.setBackground(Color.WHITE);

        // 3. **수식 입력 필드 생성**
        textField = TextFieldCreator.createFormulaTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 30));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        leftPanel.add(textField, BorderLayout.NORTH);

        // 4. **결과 패널 생성 및 설정**
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(1, 2));
        resultPanel.setBackground(Color.WHITE);

        // 4-1. 연산자 표시 필드
        operatorField = TextFieldCreator.createOperatorTextField();
        operatorField.setFont(new Font("Arial", Font.BOLD, 30));
        operatorField.setHorizontalAlignment(SwingConstants.LEFT);
        operatorField.setEditable(false);
        operatorField.setBackground(Color.WHITE);
        operatorField.setForeground(Color.BLACK);
        resultPanel.add(operatorField);

        // 4-2. 결과 표시 필드
        resultField = TextFieldCreator.createResultTextField();
        resultField.setFont(new Font("Arial", Font.BOLD, 40));
        resultField.setHorizontalAlignment(SwingConstants.RIGHT);
        resultField.setEditable(false);
        resultField.setBackground(Color.WHITE);
        resultField.setForeground(Color.BLACK);
        resultPanel.add(resultField);

        leftPanel.add(resultPanel, BorderLayout.CENTER);

        // 5. **버튼 패널 생성 및 버튼 추가**
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttons = {
            "Exit", "CE", "C", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "+/-", "0", ".", "="
        };

        for (String text : buttons) {
            Button button = new Button(text); // 버튼 객체 생성
            JButton jButton = button.createButton(); // JButton 생성
            jButton.setPreferredSize(new Dimension(100, 65));
            jButton.addActionListener(new Action2(textField, resultField, operatorField)); // Action2 연결
            buttonPanel.add(jButton);
        }

        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        // 6. **좌측 패널 프레임에 추가**
        frame.add(leftPanel, BorderLayout.CENTER);
    }

    // 화면 표시 메서드
    public void show() {
        frame.setVisible(true);
    }
}
