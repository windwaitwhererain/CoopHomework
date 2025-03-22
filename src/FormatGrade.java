import java.util.List;

public class FormatGrade {
    public static String format(List<Integer> numbers) {
        if (numbers.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.size(); i++) {
            sb.append(numbers.get(i));
            if (i < numbers.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}