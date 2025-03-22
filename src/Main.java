import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int n = 0, r = 0;
        int E = 0, A = 0;
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
                E = i+1;
            }
            if ("-a".equals(args[i])){
                A = i+1;
            }
        }
        if (E != 0){
            try {
                new GradeExercises().gradeExercises(args[E], args[A]);
            } catch (IOException e) {
                System.out.println("Error grading exercises: " + e.getMessage());
            }
        }
        if (n != 0) {
            if(r != 0){
                QuestionGenerator generator = new QuestionGenerator(n, r);
                generator.questionGenerate();
                writeFile.writeExerciseFile(generator); // 生成题目和答案文件
            }
            else {
                System.out.println("请提供有效的 -n 和 -r 参数。");
            }
        }
    }
}
