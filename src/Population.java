import java.util.ArrayList;

/**
 * Created by student on 12/4/17.
 */
public class Population {
    private double[][] distances;
    TestCities test = new TestCities();
    private ArrayList<Chromosome> chromosomes;
    private int size;


    public Population(int size){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                City[] cities = test.getCities();
                double wam = cities[i].distanceTo(cities[j]);
                distances[i][j]= wam;
            }
        }
        chromosomes = new ArrayList<>();
        this.size = size;
        this.fill();

    }
    public void fill() {
        while (this.chromosomes.size() < this.size) {
            if (this.chromosomes.size() < this.size / 3) {
                this.chromosomes.add(new Chromosome(null));
            } else {
                this.mate();
            }
        }
    }

    public void mutate(){
        for (int i = 1; i < chromosomes.size(); i++) {
            int doMutate = (int)(Math.random() * 10);
            if (doMutate >= 6) {
                chromosomes.get(i).mutate();
            }
        }
    }
    public void sort(){
        chromosomes.sort((o1, o2) -> o2.calcCost() - o1.calcCost());
    }

    public void mate(){
        chromosomes.set(chromosomes.size(),chromosomes.get(1));
        chromosomes.set(chromosomes.size()-1,chromosomes.get(0));

    }
}





}
