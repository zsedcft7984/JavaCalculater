package calculator;

import javax.swing.*;
import java.awt.*;

public class Button {
    private String text;

    public Button(String text) {
        this.text = text;
    }

    public JButton createButton() {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setFocusPainted(false); // 버튼 선택 표시 제거
        button.setBackground(Color.WHITE); // 기본 버튼 배경색

        // "=" 버튼은 파란색
        if (text.equals("=")) {
            button.setBackground(new Color(0, 120, 215)); // "=" 버튼은 파란색
            button.setForeground(Color.WHITE); // 텍스트는 흰색
        }
        // 사칙연산 버튼은 옅은 회색으로 설정
        else if (text.equals("+") || text.equals("-") || text.equals("×") || text.equals("÷")) {
            button.setBackground(new Color(211, 211, 211)); // 옅은 회색
        }

        return button;
    }
}
