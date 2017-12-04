/**
 * Created by student on 12/4/17.
 */
public class Main {
    public static void main(String[] args) {
        TestCities t = new TestCities();
        Population p = new Population(t.getCities().length);
        int score1 = 0;
        int score2 = 0;
        int dif = score1 -score2;
        int count = 0;
        while(dif>=5 || count<20){
            score1 = p.calcCost();
            p.runGen();
            score2 = p.calcCost();
            dif = score1 - score2;
            count++;
        }
        System.out.println(score2);
    }
}
