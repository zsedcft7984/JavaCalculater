package Calculator3;

public class Calculator {
    private double currentValue;       // 현재 계산 값
    private double storedValue;        // 이전 값 저장
    private String currentOperator;    // 현재 연산자
    private boolean isNewOperand;      // 새로운 피연산자 입력 상태 플래그
    private StringBuilder equationText; // 수식 표시용

    public Calculator() {
        currentValue = 0;
        storedValue = 0;
        currentOperator = "";
        isNewOperand = true;
        equationText = new StringBuilder(); // 수식 초기화
    }

    public void handleInput(String input) {
        switch (input) {
            case "+":
            case "-":
            case "*":
            case "/":
                handleOperator(input);
                break;
            case "=":
                calculate();
                break;
            case "C":
                clear();
                break;
            default:
                handleNumber(input);
                break;
        }
    }

    private void handleNumber(String input) {
        if (isNewOperand) {
            currentValue = 0; // 새로운 피연산자일 경우 초기화
            isNewOperand = false;
        }

        if (input.equals("0") && currentValue == 0) {
            // 0일 때 추가 동작 없음
        } else {
            // 기존 값에 새로운 숫자를 붙임
            currentValue = currentValue * 10 + Integer.parseInt(input);
        }

        // 수식 업데이트
        equationText.append(input);
    }

    private void handleOperator(String operator) {
        if (!isNewOperand) {
            calculate(); // 이전 연산 수행
        }
        storedValue = currentValue; // 현재 값을 저장
        currentOperator = operator;
        isNewOperand = true;

        // 연산자 수식 업데이트
        if (equationText.length() > 0) {
            char lastChar = equationText.charAt(equationText.length() - 1);
            if ("+-*/".indexOf(lastChar) >= 0) {
                // 마지막 문자가 연산자인 경우 대체
                equationText.setCharAt(equationText.length() - 1, operator.charAt(0));
            } else {
                // 새 연산자 추가
                equationText.append(" ").append(operator).append(" ");
            }
        } else {
            // 수식이 비어 있을 경우 연산자만 추가
            equationText.append(operator).append(" ");
        }
    }

    private void calculate() {
        if (currentOperator.isEmpty()) {
            return; // 연산자가 없을 경우 동작 없음
        }

        switch (currentOperator) {
            case "+":
                currentValue = storedValue + currentValue;
                break;
            case "-":
                currentValue = storedValue - currentValue;
                break;
            case "*":
                currentValue = storedValue * currentValue;
                break;
            case "/":
                if (currentValue != 0) {
                    currentValue = storedValue / currentValue;
                } else {
                    currentValue = 0; // 0으로 나누는 경우
                }
                break;
        }

        // "=" 이후 수식 초기화
        currentOperator = "";
        isNewOperand = true;
        equationText = new StringBuilder(); // 수식 창 초기화
        equationText.append(currentValue); // 결과만 수식으로 표시
    }

    private void clear() {
        currentValue = 0;
        storedValue = 0;
        currentOperator = "";
        isNewOperand = true;
        equationText = new StringBuilder(); // 수식 초기화
    }

   
    public String getEquationText() {
        return equationText.toString();
    }
    public String getDisplayText() {
        // 결과가 정수인지 실수인지 확인하여 표시
        if (currentValue == (long) currentValue) {
            return String.valueOf((long) currentValue); // 정수로 변환 후 표시
        } else {
            return String.valueOf(currentValue); // 실수로 표시
        }
    }
}
