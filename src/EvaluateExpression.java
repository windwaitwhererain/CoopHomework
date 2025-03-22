import java.util.Stack;

public class EvaluateExpression {
    private final ExpressionParser parser = new ExpressionParser();

    public MixedNumber evaluateExpression(Question question) {
        String expr = parser.parseExpression(question);
        String[] tokens = expr.split("\\s+");

        Stack<MixedNumber> numbers = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.matches("-?\\d+('?\\d*/\\d*)?")) {
                numbers.push(parseNumber(token));
            } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                while (!operators.isEmpty() && hasPrecedence(token, operators.peek())) {
                    applyOperator(numbers, operators.pop());
                }
                operators.push(token);
            }
        }

        while (!operators.isEmpty()) {
            applyOperator(numbers, operators.pop());
        }

        return numbers.pop();
    }

    private boolean hasPrecedence(String op1, String op2) {
        if ((op1.equals("+") || op1.equals("-")) && (op2.equals("*") || op2.equals("/"))) {
            return false;
        }
        return true;
    }

    private void applyOperator(Stack<MixedNumber> numbers, String op) {
        MixedNumber b = numbers.pop();
        MixedNumber a = numbers.pop();
        MixedNumber result;
        switch (op) {
            case "+":
                result = a.mixNumberAdd(a, b);
                break;
            case "-":
                result = a.mixNumberAdd(a, b.negation());
                if (result.isNegative() == 1) throw new ArithmeticException("Negative result");
                break;
            case "*":
                result = a.mixNumberMultiply(a, b);
                break;
            case "/":
                if (b.numerator == 0 && b.interger == 0)
                    throw new ArithmeticException("Division by zero");
                result = a.mixNumberDivide(a, b);
                break;
            default:
                throw new IllegalArgumentException("Invalid operator: " + op);
        }
        numbers.push(result);
    }

    public MixedNumber parseNumber(String token) {
        if (token.contains("'")) {
            String[] parts = token.split("'");
            int whole = Integer.parseInt(parts[0]);
            String[] fracParts = parts[1].split("/");
            int num = Integer.parseInt(fracParts[0]);
            int den = Integer.parseInt(fracParts[1]);
            MixedNumber mn = new MixedNumber();
            MixedNumber temp = new MixedNumber();
            temp.numerator = num;
            temp.denominator = den;
            temp.interger = whole;
            mn.setValue(temp);
            return mn;
        } else if (token.contains("/")) {
            String[] parts = token.split("/");
            int num = Integer.parseInt(parts[0]);
            int den = Integer.parseInt(parts[1]);
            MixedNumber mn = new MixedNumber();
            MixedNumber temp = new MixedNumber();
            temp.numerator = num;
            temp.denominator = den;
            mn.setValue(temp);
            return mn;
        } else {
            MixedNumber mn = new MixedNumber();
            mn.interger = Integer.parseInt(token);
            return mn;
        }
    }
}