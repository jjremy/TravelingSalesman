/**
 * Created by student on 12/4/17.
 */
public class Chromosome {
    private int[] c;
    private City[] cities;

    public Chromosome(City[] cities) {
        this.cities = cities;
        c = new int[cities.length];
        for (int i = 0; i < cities.length; i++) {
            int a = (int)(Math.random()*20);
            while(cities[a] == null){
                a = (int)(Math.random()*20);
            }
            c[i] = a;
            cities[a] = null;
        }
    }


    public int calcCost() {
        int total = 0;
        for (int i = 0; i < c.length - 1; i++) {
            total += cities[c[0]].distanceTo(cities[c[1]]);
        }
        return total;
    }

    public int[] mate(City[] city) {
        return c;
    }

    public void mutate() {
        int pivot = (int) (Math.random() * c.length);
        int pivot2 = (int) (Math.random() * c.length);
        int temp = c[pivot];
        c[pivot] = c[pivot2];
        c[pivot2] = temp;
    }
}
