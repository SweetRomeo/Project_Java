package csd;

import java.util.Random;
import java.util.Scanner;

class Colony{
    private char symbol;
    private int population;
    private int foodStock;
    private int wins;
    private int loses;
    private boolean isDeath;
    public Colony()
    {

    }
    public Colony(char symbol,int population,int food_stock,int wins,int loses,boolean isDeath)
    {
        this.symbol = symbol;
        this.population = population;
        this.foodStock = food_stock;
        this.wins = wins;
        this.loses = loses;
        this.isDeath = isDeath;
    }
    public void createColony(Colony colony)
    {
        symbol = colony.symbol;
        population = colony.population;
        foodStock = colony.foodStock;
        wins = colony.wins;
        loses = colony.loses;
        isDeath = colony.isDeath;
    }
    public char getSymbol()
    {
        return this.symbol;
    }
    public void setSymbol(char symbol)
    {
        this.symbol = symbol;
    }
    public int getPopulation()
    {
        return this.population;
    }
    public void setPopulation(int population)
    {
        this.population = population;
    }
    public int getFoodStock()
    {
        return this.foodStock;
    }
    public void setFoodStock(int foodStock)
    {
        this.foodStock = foodStock;
    }
    public int getWins()
    {
        return this.wins;
    }
    public void setWins(int wins)
    {
        this.wins = wins;
    }
    public int getLoses()
    {
        return this.loses;
    }
    public void setLoses(int loses)
    {
        this.loses = loses;
    }
    public boolean getIsDeath()
    {
        return this.isDeath;
    }
    public void setIsDeath(boolean isDeath)
    {
        this.isDeath = isDeath;
    }

}

class Game{
    public static Random r = new Random();
    public static Scanner kb = new Scanner(System.in);
    public static int turnCount = 0;
    public static int deathCount = 0;
    public static void displayColonies(Colony[] colonies,int number_of_colonies)
    {
        while(true)
        {
            System.out.println("------------------------------------------");
            System.out.printf("Tur sayısı: %d%n",turnCount++);
            System.out.println("Koloni   Populasyon  Yemek Stoğu     Kazanma     Kaybetme");
            for(int i = 0;i < number_of_colonies;++i){
                if(!colonies[i].getIsDeath() && colonies[i].getPopulation() <= 0 || colonies[i].getFoodStock() <= 0){
                    deathCount++;
                    colonies[i].setIsDeath(true);
                    System.out.printf("%d öldü.%n",i);
                }
                if(!colonies[i].getIsDeath())
                    System.out.printf("%2c %9d %13d %13d %13d%n",colonies[i].getSymbol(),colonies[i].getPopulation(),
                            colonies[i].getFoodStock(),colonies[i].getWins(),colonies[i].getLoses());
                else
                    System.out.printf("%4c      --         --         --        --\n", colonies[i].getSymbol());
            }
            System.out.println("------------------------------------------");
            for(int i = 0;i < number_of_colonies;++i){
                for(int j = i + 1;j < number_of_colonies;++j) {
                    int tempSavas1= Taktik.Savas(new Random());
                    int tempSavas2 = Taktik.Savas(new Random());
                    int kazanan;
                    int foodtransfertemp;

                    if(tempSavas1 == tempSavas2){
                        if(colonies[i].getPopulation() == colonies[j].getPopulation())
                            kazanan = r.nextInt(2) + 1;
                        else if(colonies[i].getPopulation() > colonies[j].getPopulation())
                            kazanan = 1;
                        else
                            kazanan = 2;
                    }
                    else if(tempSavas1 > tempSavas2){
                        kazanan = 1;
                    }
                    else {
                        kazanan = 2;
                    }

                    if(kazanan == 1){
                        colonies[i].setWins(colonies[i].getWins() + 1);
                        colonies[j].setLoses(colonies[i].getLoses()+ 1);
                        colonies[j].setPopulation(colonies[j].getPopulation() - colonies[i].getPopulation() *((int)((double)(tempSavas1 - tempSavas2)/ 1000)));
                        foodtransfertemp = (int)(((double)colonies[j].getFoodStock() / 100)* 10);
                        colonies[i].setFoodStock(colonies[j].getFoodStock() + foodtransfertemp);
                        colonies[j].setFoodStock(colonies[j].getFoodStock() - foodtransfertemp);

                    }
                    else{
                        colonies[i].setLoses(colonies[i].getLoses() + 1);
                        colonies[j].setWins(colonies[i].getWins()+ 1);
                        colonies[i].setPopulation(colonies[i].getPopulation() - colonies[i].getPopulation() *((int)((double)(tempSavas2 - tempSavas1)/ 1000)));
                        foodtransfertemp = (int)(((double)colonies[i].getFoodStock() / 100)* 10);
                        colonies[j].setFoodStock(colonies[j].getFoodStock() + foodtransfertemp);
                        colonies[i].setFoodStock(colonies[j].getFoodStock() - foodtransfertemp);
                    }

                }
            }
            for(int i = 0;i < number_of_colonies;++i){
                colonies[i].setPopulation(colonies[i].getPopulation() + (int)(((double)colonies[i].getPopulation() / 100) * 20));
                colonies[i].setFoodStock(colonies[i].getFoodStock() - colonies[i].getPopulation() * 2);
            }
            if(deathCount >= number_of_colonies - 1)
                break;
            System.out.println("Savaşı devam ettirmek için entera basınız...");
            String str = kb.nextLine();
        }
    }
}
