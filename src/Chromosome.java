import java.util.ArrayList;

/**
 * Created by student on 12/4/17.
 */
public class Chromosome {
    private int[] c;
    TestCities test = new TestCities();
    private int cost = -1;

    public Chromosome(int[] path) {
        c = path;
    }

    public Chromosome(int length) {
        c = new int[length];
        for (int i = 0; i < length; i++) {
            int a = (int) (Math.random() * 20);
            while (test.getCities()[a] == null) {
                a = (int) (Math.random() * 20);
            }
            c[i] = a;
            test.getCities()[a] = null;
        }
    }


    public int calcCost() {
//        if (cost != -1) {
//            return cost;
//        }
        int total = 0;
        for (int i = 0; i < c.length - 1; i++) {
            total += Population.distances[c[i]][c[i + 1]];
        }
        cost = total;
        return total;
    }

    public int[] mate(int[] city) {
        ArrayList<Integer> path1 = new ArrayList<>();
        ArrayList<Integer> path2 = new ArrayList<>();
        ArrayList<Integer> unique1 = new ArrayList<>();
        ArrayList<Integer> unique2 = new ArrayList<>();
        int[] ans = new int[c.length];
        int point = (int) (Math.random() * (city.length - 2) + 1);
        for (int i = 0; i < point + 1; i++) {
            path1.add(c[i]);
            path2.add(city[i]);
        }
//        System.out.println(path1.size());
//        System.out.println(path2.size());
        for (int i = 0; i < point + 1; i++) {
            if (!path2.contains(c[i])) {
                unique1.add(c[i]);
            }
            if (!path1.contains(city[i])) {
                unique2.add(city[i]);
            }
        }
        for (int i = 0; i < point + 1; i++) {
            ans[i] = path1.get(i);
            //System.out.println(ans[i]);
        }
//        System.out.println(unique1.size());
//        System.out.println(unique2.size());
        for (int i = point + 1; i < ans.length; i++) {
            if (unique1.contains(city[i])) {
                ans[i] = unique2.get(unique1.indexOf(city[i]));
            }

        }
        return ans;

    }

    public int[] mutate() {
        int pivot = (int) (Math.random() * c.length);
        int pivot2 = (int) (Math.random() * c.length);
        int temp = c[pivot];
        c[pivot] = c[pivot2];
        c[pivot2] = temp;
        return c;

    }

    public int[] mutate2() {
        int point1 = (int) (Math.random() * c.length);
        int point2 = (int) (Math.random() * c.length);
        int dur = Math.abs(point1 - point2);
        for (int i = 0; i < (dur + 1) / 2; i++) {
            if (point1 > point2) {
                int temp = c[point2 + i];
                c[point2 + i] = c[point1 - i];
                c[point1 - i] = temp;
            }
            if (point2 > point1) {
                int temp = c[point1 + i];
                c[point1 + i] = c[point2 - i];
                c[point2 - i] = temp;
            }


        }
        return c;
    }

    public int[] getC() {
        return c;
    }
}
