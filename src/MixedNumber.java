import java.util.Random;

class MixedNumber {
    int interger;//带分数的整数部分
    int numerator;//带分数的分子部分
    int denominator;//带分数的分母部分
    MixedNumber(){
        interger=0;
        numerator=0;
        denominator=1;
    }
    MixedNumber(int numerator,int denominator){
        this.numerator=numerator;
        this.denominator=denominator;
        this.simplify();
    }
    void setValue(MixedNumber M){
        this.interger=M.interger;
        this.numerator=M.numerator;
        this.denominator=M.denominator;
        this.simplify();
    }
    private int gcd(int a,int b){//找到最大公约数(递归算法很好看）
        if(b == 0)return a;
        else return gcd(b , a % b);
    }
    private void simplify(){
        if(numerator == 0){
            denominator = 1;
        }
        else {
            int n = gcd(numerator, denominator);
            numerator = numerator / n;
            denominator = denominator / n;
            if(numerator > denominator){
                interger = numerator / denominator;
                numerator = numerator % denominator;
            }
            if (denominator < 0) {
                numerator = -numerator;
                interger = -interger;
                denominator = -denominator;
            }
        }
    }
    MixedNumber negation(){
        MixedNumber data=new MixedNumber();
        data.interger=-interger;
        data.numerator=-numerator;
        return data;
    }
    void randomNumber(int r){
        Random random=new Random();
        interger = random.nextInt(r)+1;
    }
    MixedNumber mixNumberAdd(MixedNumber numA,MixedNumber numB){
        MixedNumber numC = new MixedNumber();
        int numeratorA,numeratorB;
        numeratorA = numA.numerator + numA.interger * numA.denominator;
        numeratorB = numB.numerator + numB.interger * numB.denominator;
        numeratorA = numeratorA * (numB.denominator / gcd(numA.denominator,numB.denominator));
        numeratorB = numeratorB * (numA.denominator / gcd(numA.denominator,numB.denominator));
        numC.denominator = numB.denominator * gcd(numA.denominator,numB.denominator);
        numC.numerator = numeratorA + numeratorB;
        numC.simplify();
        return numC;
    }
    MixedNumber mixNumberMultiply(MixedNumber numA,MixedNumber numB){
        MixedNumber numC = new MixedNumber();
        int numeratorA,numeratorB;
        numeratorA = numA.numerator + numA.interger * numA.denominator;
        numeratorB = numB.numerator + numB.interger * numB.denominator;
        numC.denominator = numA.denominator * numB.denominator;
        numC.numerator = numeratorA * numeratorB;
        numC.simplify();
        return numC;
    }
    MixedNumber mixNumberDivide(MixedNumber numA,MixedNumber numB){
        MixedNumber numC = new MixedNumber();
        int numeratorA,numeratorB;
        numeratorA = numA.numerator + numA.interger * numA.denominator;
        numeratorB = numB.numerator + numB.interger * numB.denominator;
        numC.numerator = numeratorA * numB.denominator;
        numC.denominator = numA.denominator * numeratorB;
        numC.simplify();
        return numC;
    }
    void testPrintNumber(){
        System.out.print(interger);
        if(numerator!=0){
            System.out.print("'"+numerator+"/"+denominator);
        }
    }
    String getNumber(){
        StringBuilder outString = new StringBuilder();
        if(interger == 0){
            if(numerator==0){
                outString.append("0");

            }
            else{
                outString.append(numerator+"/"+denominator);
            }
        }
        else{
            outString.append(interger);
            if(numerator != 0){
                outString.append("'"+numerator+"/"+denominator);
            }
        }
        return outString.toString();
    }
    int isNegative(){
        if(interger < 0 || (interger == 0&& numerator < 0)){
            return 1;
        }
        return 0;
    }
}
