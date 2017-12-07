/**
 * Created by student on 12/4/17.
 */
public class Chromosome {
    private int[] c;
    private City[] city;
    TestCities test = new TestCities();

    public Chromosome(City[] cities) {
            city = new City[test.getCities().length];
            city = test.getCities();
            c = new int[cities.length];
            for (int i = 0; i < cities.length; i++) {
                int a = (int) (Math.random() * 20);
                while (cities[a] == null) {
                    a = (int) (Math.random() * 20);
                }
                c[i] = a;
                cities[a] = null;
            }

        }
        public Chromosome(int length){
            city = new City[test.getCities().length];
            city = test.getCities();
            c = new int[length];
            for (int i = 0; i < city.length; i++) {
                int a = (int) (Math.random() * 20);
                while (city[a] == null) {
                    a = (int) (Math.random() * 20);
                }
                c[i] = a;
                city[a] = null;
            }
        }


    public int calcCost() {
        int total = 0;
        for (int i = 0; i < c.length - 1; i++) {
            total += Population.distances[c[i]][c[i+1]];
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
