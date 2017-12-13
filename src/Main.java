/**
 * Created by student on 12/4/17.
 */
public class Main {
    public static void main(String[] args) {
        Population p = new Population(10);
        int score = 0;
        for (int i = 0; i < 1000000; i++) {
            p.runGen();
            score = p.getChromosomes().get(p.getChromosomes().size() - 1).calcCost();
            //System.out.println(score);

        }
        System.out.println(score);
        System.out.println();
        for (int i = 0; i < 20; i++) {
            System.out.print(p.getChromosomes().get(p.getChromosomes().size() - 1).getC()[i] + ", ");

        }

    }



}
