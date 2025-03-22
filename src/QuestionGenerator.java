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
            question.operator[0] = random.nextInt(4)+1;//第一个运算符号不能为等于号
            question.elements[1].randomNumber(r);
            question.operator[1] = random.nextInt(5);
            if(question.operator[1] != 0){//如果第二个运算符号为等于号，则不必继续生成
                question.elements[2].randomNumber(r);
                question.operator[2] = 0;
                if(random.nextInt(10)-5>0){
                    question.operator[1] += 10;
                }
                //十分之一的概率生成括号
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
            for(t2=0;t2<questions.size();t2++){//查重逻辑如下：首先判断结果是否相等，其次检查各元素是否相等
                if(question.result.isEqual(questions.get(t2).result)==0){
                    continue;
                }
                for(t3=0;t3 < question.elements.length-1; t3++){
                    if(questions.get(t2).elements[t3].isEqual(question.elements[t3]) == 0){
                        break;
                    }
                }
                if(questions.get(t2).elements[t3].isEqual(question.elements[t3]) == 0){
                    t3 = question.elements.length+1;
                    break;
                }
            }
            if(t3 == question.elements.length+1){//如果发现重复，则丢弃此次生成式子重新生成
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