import java.util.List;
import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.Random;

public class QuestionGenerator {
    int n;//生成题目数量
    int r;//生成范围
    List<Question> questions = new ArrayList<>();
    QuestionGenerator(int n,int r){
        this.n=n;
        this.r=r;
    }
    public void questionGenerate(){
        int t1,t2,t3=0;
        Random random=new Random();
        Question tmpQuestion = new Question();
        for(t1=0;t1<this.n;t1++){
            Question question=new Question();
            question.elements[0].randomNumber(r);
            question.operator[0] = random.nextInt(4)+1;
            question.elements[1].randomNumber(r);
            question.operator[1] = random.nextInt(5);
            if(question.operator[1] != 0){
                question.elements[2].randomNumber(r);
                question.operator[2] = 0;
            }
            question.testPrintQuestion();
            tmpQuestion.copyQuestion(question);
            try{
                question.result.setValue(tmpQuestion.getAnswerCalc());
            } catch (NullPointerException e) {
                t1--;
                continue;
            }
            question.testPrintQuestion();
            for(t2=0;t2<questions.size();t2++){
                if(question.result.isEqual(questions.get(t2).result)==0){
                    continue;
                }
                for(t3=0;t3 < 3; t3++){
                    if(questions.get(t2).elements[t3].isEqual(question.elements[t3])==0){
                        t3 = 4;
                        break;
                    }
                }
            }
            if(t3 == 4){
                t1--;
                continue;
            }
            questions.add(question);
        }
        for(t1=0;t1<this.n;t1++){
            questions.get(t1).testPrintQuestion();
        }
    }
}