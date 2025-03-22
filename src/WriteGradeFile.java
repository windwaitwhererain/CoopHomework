import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteGradeFile {
    public static void writeGradeFile(List<Integer> correct, List<Integer> wrong) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Grade.txt"))) {
            writer.write("Correct: " + correct.size() + " (" + FormatGrade.format(correct) + ")\n");
            writer.write("Wrong: " + wrong.size() + " (" + FormatGrade.format(wrong) + ")\n");
        }
    }
}