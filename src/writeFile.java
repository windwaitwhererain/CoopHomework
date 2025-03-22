import java.io.*;
public class writeFile {
    static String[] fileName = {"Exercises.txt","Answers.txt"};
    public static void writeExerciseFile(QuestionGenerator generator){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName[0]));
            int t;
            for(t = 0;t < generator.questions.size(); t++ ){
                out.write(t+1+". "+generator.questions.get(t).getdataString());
            }
            out.close();
        } catch (IOException e) {
            System.out.println("写入数据失败，请检查硬盘剩余是否充足及程序是否有足够权限");
        }
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName[1]));
            int t;
            for(t = 0;t < generator.questions.size(); t++ ){
                out.write(t+1+". "+generator.questions.get(t).getAnswerString()+"\n");
            }
            out.close();
        } catch (IOException e) {
            System.out.println("写入数据失败，请检查硬盘剩余是否充足及程序是否有足够权限");
        }
    }
}
