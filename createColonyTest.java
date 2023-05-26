package csd;

class CreateColonyTest{
    public static void run(int number_of_colonies,String[] pop_array)
    {
        String symbols = "%@*?^#$&";
        Colony[] colonies = new Colony[number_of_colonies];
        for(int i = 0;i < number_of_colonies;++i){
            colonies[i] = new Colony();
            colonies[i].setSymbol(symbols.charAt(i));
            colonies[i].setPopulation(Integer.parseInt(pop_array[i]));
            colonies[i].setFoodStock(Integer.parseInt(pop_array[i])*Integer.parseInt(pop_array[i]));
            colonies[i].setLoses(0);
            colonies[i].setLoses(0);
            colonies[i].setIsDeath(false);
        }
        Game.displayColonies(colonies,number_of_colonies);
    }
}
