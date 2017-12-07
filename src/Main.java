/**
 * Created by student on 12/4/17.
 */
public class Main {
    public static void main(String[] args) {
//        System.out.println("o");
        Population p = new Population(10);
        int score1 = 0;
        int score2 = 0;
        int dif = score1 -score2;
        int count = 0;
//        while(dif>=5 || count<20){
        for (int i = 0; i < 1000; i++) {
            System.out.println("j");


            score1 = p.getChromosomes().get(p.getChromosomes().size()-1).calcCost();
            System.out.println(score1);
            p.runGen();
            score2 = p.getChromosomes().get(p.getChromosomes().size()-1).calcCost();
            dif = score1 - score2;
            System.out.println(dif);
            count++;
        }
//        }
        System.out.println(score2);

    }
}
