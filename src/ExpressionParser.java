public class ExpressionParser {
    public String parseExpression(Question question) {
        return question.getdataString().replace("=", "").trim();
    }

    public Question parse(String expr) {
        Question question = new Question();
        String[] tokens = expr.split("\\s+");
        int elementIndex = 0;
        int operatorIndex = 0;

        for (String token : tokens) {
            if (token.matches("\\d+('?\\d*/\\d*)?")) {
                question.elements[elementIndex].setValue(parseNumber(token));
                elementIndex++;
            } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                question.operator[operatorIndex] = operatorToInt(token);
                operatorIndex++;
            }
        }
        question.operator[operatorIndex] = 0;
        return question;
    }

    private int operatorToInt(String op) {
        switch (op) {
            case "+": return 1;
            case "-": return 2;
            case "*": return 3;
            case "/": return 4;
            default: throw new IllegalArgumentException("Invalid operator: " + op);
        }
    }

    private MixedNumber parseNumber(String token) {
        if (token.contains("'")) {
            String[] parts = token.split("'");
            int whole = Integer.parseInt(parts[0]);
            String[] fracParts = parts[1].split("/");
            int num = Integer.parseInt(fracParts[0]);
            int den = Integer.parseInt(fracParts[1]);
            MixedNumber mn = new MixedNumber();
            mn.interger = whole;
            mn.numerator = num;
            mn.denominator = den;
            return mn;
        } else if (token.contains("/")) {
            String[] parts = token.split("/");
            int num = Integer.parseInt(parts[0]);
            int den = Integer.parseInt(parts[1]);
            MixedNumber mn = new MixedNumber();
            mn.numerator = num;
            mn.denominator = den;
            return mn;
        } else {
            MixedNumber mn = new MixedNumber();
            mn.interger = Integer.parseInt(token);
            return mn;
        }
    }
}