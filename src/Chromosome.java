/**
 * Created by student on 12/4/17.
 */
public class Chromosome {
    private City[] c;

    public Chromosome(City[] cities) {
        c = cities;
    }
    public Chromosome(int size){
        City[] city = new City[size];
        for (int i = 0; i < city.length; i++) {
            city[i] = new City();
        }
    }

    public void calcCost() {
        int total = 0;
        for (int i = 0; i < c.length - 1; i++) {
            total += c[0].distanceTo(c[1]);
        }
    }

    public City[] mate(City[] city) {
        return c;
    }

    public void mutate() {
        int pivot = (int) (Math.random() * c.length);
        int pivot2 = (int) (Math.random() * c.length);
        City temp = c[pivot];
        c[pivot] = c[pivot2];
        c[pivot2] = temp;
    }
}
