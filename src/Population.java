import java.util.ArrayList;

/**
 * Created by student on 12/4/17.
 */
public class Population {
    public static double[][] distances;
    TestCities test = new TestCities();
    private ArrayList<Chromosome> chromosomes;
    private int size;
    private int cityLength;

    public int getSize() {
        return size;
    }

    public ArrayList<Chromosome> getChromosomes() {
        return chromosomes;
    }

    public Population(int size){
        City[] cities = test.getCities();
        cityLength = cities.length;
        distances = new double[cityLength][cityLength];
        for (int i = 0; i < cityLength; i++) {
            for (int j = 0; j < cityLength; j++) {
                double wam = cities[i].distanceTo(cities[j]);
                distances[i][j]= wam;
            }
        }
        chromosomes = new ArrayList<>();
        this.size = size;
//        System.out.println("before fill");
        this.fill();
//        System.out.println("after");

    }
    public void fill() {
        while (this.chromosomes.size() < this.size) {
            if (this.chromosomes.size() < this.size / 3) {
                this.chromosomes.add(new Chromosome(cityLength));
               // this.mate();
            } else {
                this.chromosomes.add(new Chromosome(cityLength));
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
        chromosomes.set(chromosomes.size()-1,chromosomes.get(1));
        chromosomes.set(chromosomes.size()-2,chromosomes.get(0));

    }
    public void kill(){
        int killRate = chromosomes.size()/2;
        for (int i = 0; i < killRate; i++) {
            chromosomes.remove(i);
        }
    }

    public void calcCost() {
        for (int i = 0; i < chromosomes.size(); i++) {
             chromosomes.get(i).calcCost();
        }
    }
    public void runGen(){
        this.sort();
        //this.mate();
        this.fill();
        this.mutate();    }
}






