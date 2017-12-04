/**
 * Created by michael_hopps on 12/1/17.
 */
public class TestCities {

     private City[] cities;

    public TestCities(){
        cities = new City[20];
        cities[0] = new City(273, 225);
        cities[1] = new City(643, 437);
        cities[2] = new City(235, 630);
        cities[3] = new City(541, 737);
        cities[4] = new City(248, 587);
        cities[5] = new City(129, 185);
        cities[6] = new City(369, 503);
        cities[7] = new City(300, 797);
        cities[8] = new City(555, 406);
        cities[9] = new City(404, 214);
        cities[10] = new City(302, 657);
        cities[11] = new City(775, 438);
        cities[12] = new City(315, 359);
        cities[13] = new City(773, 75);
        cities[14] = new City(129, 69);
        cities[15] = new City(238, 13);
        cities[16] = new City(520, 23);
        cities[17] = new City(295, 299);
        cities[18] = new City(794, 346);
        cities[19] = new City(621, 27);
    }
    public City[] getCities(){
        return cities;
    }


}
