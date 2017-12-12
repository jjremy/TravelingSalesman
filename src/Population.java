import java.util.ArrayList;
import java.util.Random;

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

    public Population(int size) {
        City[] cities = test.getCities();
        cityLength = cities.length;
        distances = new double[cityLength][cityLength];
        for (int i = 0; i < cityLength; i++) {
            for (int j = 0; j < cityLength; j++) {
                double wam = cities[i].distanceTo(cities[j]);
                distances[i][j] = wam;
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

    public void mutate() {
        for (int i = chromosomes.size() - 1; i > chromosomes.size() / 2; i--) {
//            int doMutate = (int) (Math.random() * 10);
//            if (doMutate >= 3) {
                int[] c = chromosomes.get(i).mutate();
                Chromosome chrom = new Chromosome(c);
                chromosomes.set(chromosomes.size() - i, chrom);
                // }
        }
    }
    public void mutate2() {
        for (int i = chromosomes.size()-1; i > chromosomes.size()/2; i--) {
            //int doMutate = (int) (Math.random() * 10);
            //if (doMutate >= 3) {
            int[] c = chromosomes.get(i).mutate2();
            Chromosome chrom = new Chromosome(c);
            chromosomes.set(chromosomes.size()-i,chrom);
            // }
        }
    }


    public void sort() {
        chromosomes.sort((o1, o2) -> o2.calcCost() - o1.calcCost());
    }
    private int findCity(int targetCity, int[] path){
        int returnCityIndex;
        for (returnCityIndex = 0; returnCityIndex < path.length - 1; returnCityIndex++) {
            if(path[returnCityIndex] == targetCity){
                return returnCityIndex;
            }
        }
        return returnCityIndex;
    }


    public Chromosome crossoverDPX(Chromosome chromosome1, Chromosome chromosome2){ //TODO OPTIMISE DPX
        int[] p1 = chromosome1.getC();
        int[] p2 = chromosome2.getC();

        ArrayList<ArrayList<Integer>> subpaths = new ArrayList<>();

        ArrayList<Integer> currentSubpath = new ArrayList<>();

        int currentCityIndex = findCity(p1[0],p2);
        for (int i = 0; i < p1.length - 1; i++) {   //TODO Optimize and only check both sides if new city
            int nextCity = p1[i+1];
            boolean found = false;
            if(currentCityIndex != p2.length - 1){
                if(p2[currentCityIndex + 1] == nextCity){
                    found = true;
                    currentCityIndex ++;
                }
            }
            if(!found && currentCityIndex != 0){
                if(p2[currentCityIndex - 1] == nextCity){
                    found = true;
                    currentCityIndex --;
                }
            }

            currentSubpath.add(p1[i]);

            if(!found){
                subpaths.add(currentSubpath);
                currentSubpath = new ArrayList<>();
                if(i < p1.length - 1) {
                    currentCityIndex = findCity(p1[i + 1], p2);
                }
            }
        }
        currentSubpath.add(p1[p1.length - 1]);
        subpaths.add(currentSubpath);

        //Part 2
        int[] offspring = new int[p1.length];
        int offspringCurrentIndex = 0;

        Random random = new Random();
        boolean isReversed = random.nextBoolean();
        currentSubpath = subpaths.remove(random.nextInt(subpaths.size()));

        if(isReversed){
            for (int i = currentSubpath.size() - 1; i >= 0; i--) {
                offspring[offspringCurrentIndex] = currentSubpath.get(i);
                offspringCurrentIndex++;
            }
        }else{
            for (int i = 0; i < currentSubpath.size(); i++) {
                offspring[offspringCurrentIndex] = currentSubpath.get(i);
                offspringCurrentIndex++;
            }
        }

        while(subpaths.size() > 0){
            ArrayList<Integer> cityChoices = new ArrayList<>();
            for (ArrayList<Integer> subpath : subpaths) {
                cityChoices.add(subpath.get(0));
                if (subpath.size() != 1) {
                    cityChoices.add(subpath.get(subpath.size() - 1));
                }
            }

            int tempIndex = findCity(offspring[offspringCurrentIndex-1], p1);

            int upperIndex = -1;
            int lowerIndex = -1;

            if (tempIndex != p2.length - 1) {
                upperIndex = cityChoices.indexOf(p2[tempIndex + 1]);
            }
            if (tempIndex != 0) {
                lowerIndex = cityChoices.indexOf(p2[tempIndex - 1]);
            }

            if (cityChoices.size() == 2) {
                if (lowerIndex != -1 && upperIndex != -1) {
                    //DON'T REMOVE BOTH IF THERE IS ONLY TWO LEFT
                } else {
                    if (lowerIndex != -1) {
                        cityChoices.remove(lowerIndex);
                    }
                    if (upperIndex != -1) {
                        upperIndex = cityChoices.indexOf(p2[tempIndex + 1]);
                        cityChoices.remove(upperIndex);
                    }
                }
            } else if (cityChoices.size() == 1) {
                //DON'T REMOVE IF THERE IS ONLY ONE LEFT
            } else {
                if (lowerIndex != -1) {
                    cityChoices.remove(lowerIndex);
                }
                if (upperIndex != -1) {
                    upperIndex = cityChoices.indexOf(p2[tempIndex + 1]);
                    cityChoices.remove(upperIndex);
                }
            }

            int bestCity = cityChoices.get(0);
            for (int i = 1; i < cityChoices.size(); i++) {
                if (distances[cityChoices.get(i)][offspring[offspringCurrentIndex-1]] < distances[bestCity][offspring[offspringCurrentIndex-1]]) {
                    bestCity = cityChoices.get(i);
                }
            }


            for (int i = 0; i < subpaths.size(); i++) {
                ArrayList<Integer> tempSubpath = subpaths.get(i);
                if (tempSubpath.get(0) == bestCity) {
                    currentSubpath = subpaths.remove(i);
                    isReversed = false;
                    break;
                } else if (tempSubpath.get(tempSubpath.size() - 1) == bestCity) {
                    currentSubpath = subpaths.remove(i);
                    isReversed = true;
                    break;
                }
            }

            if (isReversed) {
                for (int i = currentSubpath.size() - 1; i >= 0; i--) {
                    offspring[offspringCurrentIndex] = currentSubpath.get(i);
                    offspringCurrentIndex++;
                }
            } else {
                for (int i = 0; i < currentSubpath.size(); i++) {
                    offspring[offspringCurrentIndex] = currentSubpath.get(i);
                    offspringCurrentIndex++;
                }
            }
        }

        return new Chromosome(offspring);
    }


    public void mate() {
        for (int i = 0; i < chromosomes.size()/2; i++) {

            chromosomes.set(i,crossoverDPX(chromosomes.get(chromosomes.size()-i-1),chromosomes.get(chromosomes.size()-i-2)));

        }
      //  chromosomes.set(0, new Chromosome(chromosomes.get(chromosomes.size()-1).mate(chromosomes.get(chromosomes.size()-2).getC())));
    //    chromosomes.set(1, new Chromosome(chromosomes.get(chromosomes.size()-2).mate(chromosomes.get(chromosomes.size()-1).getC())));


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


    public void runGen() {
        this.sort();
//        this.kill();
        this.mate();
//        this.fill();
//        this.mutate();
//        this.sort();
        this.mutate2();
    }
}






