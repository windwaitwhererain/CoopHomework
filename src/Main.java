public class Main {
    public static void main(String[] args) {
        int n=0,r=0;
        if(args.length>8){
            System.out.println("您输入的参数过多。");
            return;//程序不应该接受超过8个参数
        }
        for (int i=0;i< args.length;i++){
            if("-n".equals(args[i])){
                n=Integer.parseInt(args[i+1]);
            }
            if("-r".equals(args[i])){
                r=Integer.parseInt(args[i+1]);
            }
            if("-e".equals(args[i])){
                break;
                //检测函数

            }
        }
        if(n!=0){
            QuestionGenerator generator=new QuestionGenerator(n,r);
            generator.questionGenerate();
        }
    }
}