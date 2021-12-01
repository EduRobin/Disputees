package Zalobci;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        File text = new File("C:\\Users\\Robin\\IdeaProjects\\ProcvicovaniPRG\\src\\Zalobci\\naklady_zastoupeni (2).csv");


        ArrayList<Zalobce> list = new ArrayList<>();
        Scanner sc = new Scanner(text);


        while (sc.hasNext()) {
            final String line = sc.nextLine();
            final String[] seperated = line.split(",");
            System.out.println(line);
            final Zalobce zalobce;

            if (seperated.length != 3) {
                final String namePlus = line.substring(line.indexOf("\""), line.lastIndexOf("\""));
                zalobce = new Zalobce(Integer.parseInt(seperated[0]), namePlus, Double.parseDouble(seperated[3]));
            } else
                zalobce = new Zalobce(Integer.parseInt(seperated[0]), seperated[1], Double.parseDouble(seperated[2]));

            list.add(zalobce);

        }


        Map<String, Double> hm = new HashMap<>();
        String nazev;
        double price;

        for (Zalobce i : list) {
            nazev = i.getNazev();
            price = hm.get(nazev) == null ? 0 : hm.get(nazev);

            hm.put(nazev, price + i.getCastka());
        }

        //hm.forEach((key, value) -> System.out.println(key + ", " + value));

        String richest = " ";
        double currentMaxValuevalue = Integer.MIN_VALUE;
        for (Map.Entry<String, Double> entry : hm.entrySet()) {
            if (entry.getValue() > currentMaxValuevalue) {
                richest = entry.getKey();
                currentMaxValuevalue = entry.getValue();
            }
        }

        System.out.println("---------------AVARAGE---------------");
        System.out.println("Prumer castek: " + Math.floor(getAvarage(list) / 100));
        System.out.println(" ");
        System.out.println("-----------THE RICHEST MAN---------------");
        System.out.println("Jmeno: " + richest + " Hodnota sporu: " + currentMaxValuevalue);
        System.out.println(" ");

    }


    public static Double getAvarage(ArrayList<Zalobce> zalobce) {
        long sum = 0;
        for (Zalobce z : zalobce) {
            sum += z.getCastka();
        }
        if(zalobce.size() == 0) return 0.0;
        return (double) sum / zalobce.size();
    }
}

