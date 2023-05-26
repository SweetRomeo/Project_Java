package csd;

import java.util.Random;
import java.util.Scanner;

class App {
    public static void main(String [] args)
    {
        Scanner kb = new Scanner(System.in);
        String text = kb.nextLine();
        String [] pop_array = text.split(" ");
        CreateColonyTest.run(pop_array.length,pop_array);
    }
}

