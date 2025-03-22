import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void Test01(){
        String[] args={"-n","10","-r","10"};
        Main.main(args);
    }
    @Test
    void Test02(){
        String[] args={"-n","10"};
        Main.main(args);
    }
    @Test
    void Test03(){
        String[] args={"-r","10"};
        Main.main(args);
    }
    @Test
    void Test04(){
        String[] args={"-e","10"};
        Main.main(args);
    }
    @Test
    void Test05(){
        String[] args={"-a","10"};
        Main.main(args);
    }
    @Test
    void Test06(){
        String[] args={"-e","Exercises.txt","-a","Answers.txt"};
        Main.main(args);
    }
    @Test
    void Test07(){
        String[] args={"-e","Areyousure?","-a","I'm_not_a.txt"};
        Main.main(args);
    }
    @Test
    void Test08(){
        String[] args={"-n","10000","-r","10"};

        assertTimeout(Duration.ofSeconds(100),() -> {
            Main.main(args);
        });
    }

}