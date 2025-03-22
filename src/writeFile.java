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
        }
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName[1]));
            int t;
            for(t = 0;t < generator.questions.size(); t++ ){
                out.write(t+1+". "+generator.questions.get(t).getAnswerString()+"\n");
            }
            out.close();
        } catch (IOException e) {
        }
    }
}
