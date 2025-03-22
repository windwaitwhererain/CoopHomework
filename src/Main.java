import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int n = 0, r = 0;
        if (args.length > 8) {
            System.out.println("您输入的参数过多。");
            return;
        }
        for (int i = 0; i < args.length; i++) {
            if ("-n".equals(args[i])) {
                n = Integer.parseInt(args[i + 1]);
            }
            if ("-r".equals(args[i])) {
                r = Integer.parseInt(args[i + 1]);
            }
            if ("-e".equals(args[i])) {
                try {
                    new GradeExercises().gradeExercises(args[i + 1], args[i + 3]);
                } catch (IOException e) {
                    System.out.println("Error grading exercises: " + e.getMessage());
                }
                return; // 批改模式完成后退出
            }
        }
        if (n != 0 && r != 0) {
            QuestionGenerator generator = new QuestionGenerator(n, r);
            generator.questionGenerate();
            try {
                WriteFile.writeExerciseFile(generator); // 生成题目和答案文件
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("请提供有效的 -n 和 -r 参数。");
        }
    }
}
