public class Question {
    MixedNumber[] elements;
    int[] operator;
    MixedNumber result;
    Question(){
        int t;
        elements = new MixedNumber[3];
        for(t=0;t<3;t++){
            elements[t]=new MixedNumber();
        }
        operator = new int[3];//规定0为=，1为+，2为-，3为*，4为/
        result = new MixedNumber();
    }
    void testPrintQuestion(){//测试函数
        int t=0;
        while(true){
            elements[t].testPrintNumber();
            System.out.print(" ");
            switch (operator[t]%10){
                case 0:
                    System.out.print("=");
                    break;
                case 1:
                    System.out.print("+");
                    break;
                case 2:
                    System.out.print("-");
                    break;
                case 3:
                    System.out.print("*");
                    break;
                case 4:
                    System.out.print("/");
                    break;
            }
            System.out.print(" ");
            if(operator[t]==0)break;
            t++;
        }
        this.result.testPrintNumber();
        System.out.print("\n");
    }
    void copyQuestion(Question Q){
        int i=0;
        for(;i<elements.length;i++){
            elements[i].setValue(Q.elements[i]);
            operator[i]=Q.operator[i];
        }
    }
    MixedNumber getAnswerCalc(){//计算答案
        int t;
        int maxOperator=0;
        for(t=0;t<operator.length;t++){//找到最先进行的运算
            if(operator[t]>operator[maxOperator]){
                maxOperator=t;
            }
        }
        do{
            try{
                elements[maxOperator].setValue(getAnswer(maxOperator));
            }catch (ArithmeticException e){//判断除数中是否出现0
                return null;
            }
            if(elements[maxOperator].isNegative()==1){//判断过程中是否出现负数
                return null;
            }
            if(operator[maxOperator]==4 && elements[1].interger>1){//判断除法时是否出现假分数
                return null;
            }
            operator[maxOperator]=0;
            elements[maxOperator+1].setValue(elements[maxOperator]);
            operator[maxOperator]=0;
            for(t=0;t<operator.length;t++){
                if(operator[t]>operator[maxOperator]){
                    maxOperator=t;
                }
            }
        }while(operator[maxOperator]!=0);
        return elements[1];
    }
    MixedNumber getAnswer(int t){
        result=new MixedNumber();
        switch (operator[t]%10) {
            case 1:
                result = result.mixNumberAdd(elements[t], elements[t + 1]);
                break;
            case 2:
                result = result.mixNumberAdd(elements[t], elements[t + 1].negation());
                break;
            case 3:
                result = result.mixNumberMultiply(elements[t], elements[t + 1]);
                break;
            case 4:
                result = result.mixNumberDivide(elements[t], elements[t + 1]);
                break;
        }
        return result;
    }
    String getdataString(){
        StringBuilder outString = new StringBuilder();
        int t=0;
        while(true){
            outString.append(elements[t].getNumber());
            if(t > 0 && operator[t-1]>10){//判断右括号
                outString.append(")");
            }
            outString.append(" ");
            switch (operator[t]%10){
                case 0:
                    outString.append("=");
                    break;
                case 1:
                    outString.append("+");
                    break;
                case 2:
                    outString.append("-");
                    break;
                case 3:
                    outString.append("*");
                    break;
                case 4:
                    outString.append("/");
                    break;
            }
            outString.append(" ");
            if(operator[t] == 0){
                outString.append("\n");
                break;
            }
            else if(operator[t+1] > 10){
                outString.append("(");
            }
            t++;
        }
        return outString.toString();
    }
    String getAnswerString(){
        return result.getNumber();
    }
}
