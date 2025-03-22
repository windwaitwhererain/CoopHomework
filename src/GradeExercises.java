import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GradeExercises {
    public void gradeExercises(String exerciseFile, String answerFile) throws IOException {
        List<String> exercises = ReadFile.readFile(exerciseFile);
        List<String> answers = ReadFile.readFile(answerFile);
        List<Integer> correct = new ArrayList<>();
        List<Integer> wrong = new ArrayList<>();

        for (int i = 0; i < exercises.size(); i++) {
            String exercise = exercises.get(i).replaceAll(" ", "");
            String answer = answers.get(i).replaceAll(" ", "");
            String[] parts = exercise.split("\\.");
            int questionNumber = Integer.parseInt(parts[0]);
            String expr = parts[1].substring(0, parts[1].indexOf("="));
            String userAnswer = answer.substring(answer.indexOf(".") + 1);

            Question question = new ExpressionParser().parse(expr);
            try {
                MixedNumber correctAnswer = new EvaluateExpression().evaluateExpression(question);
                String correctAnswerStr = normalizeAnswer(correctAnswer.getNumber());
                userAnswer = normalizeAnswer(userAnswer);

                if (correctAnswerStr.equals(userAnswer)) {
                    correct.add(questionNumber);
                } else {
                    wrong.add(questionNumber);
                }
            } catch (ArithmeticException e) {
                wrong.add(questionNumber);
            }
        }

        WriteGradeFile.writeGradeFile(correct, wrong);
    }

    private String normalizeAnswer(String answer) {
        if (answer.contains("'")) {
            String[] parts = answer.split("'");
            int whole = Integer.parseInt(parts[0]);
            String[] fracParts = parts[1].split("/");
            int num = Integer.parseInt(fracParts[0]);
            int den = Integer.parseInt(fracParts[1]);
            int totalNum = whole * den + num;
            return totalNum + "/" + den;
        } else if (answer.contains("/")) {
            String[] parts = answer.split("/");
            int num = Integer.parseInt(parts[0]);
            int den = Integer.parseInt(parts[1]);
            if (den == 1) return String.valueOf(num);
            return num + "/" + den;
        }
        return answer;
    }
}