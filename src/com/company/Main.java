package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static int bossHealth = 0;
    static int bossDamage = 50;
    static String bossDefenceType = "";

    static int[] heroesHealth = {260, 280, 270, 350};
    static  int recovery = 30;
    static int[] heroesDamage = {20, 25, 15};
    static String[] heroesSuperAbilities = {"Physical", "Magical", "Kinetic", "Medic"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter boss health: ");
        bossHealth = scanner.nextInt();
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }
    public static void medicHealing(){
        Random r = new Random();
        int ranNum =r.nextInt(heroesDamage.length);
        if (heroesHealth[ranNum] <100 &&  heroesHealth [ranNum] >0 && heroesHealth[3] > 0) {
            heroesHealth[ranNum]= heroesHealth[ranNum] + recovery;
            System.out.println("Medic healed " + heroesSuperAbilities[ranNum]);
        }

    }


    public static void changeBossDefence() {
        Random r = new Random();
        int randomNumber = r.nextInt(heroesSuperAbilities.length); // 0, 1, 2
        bossDefenceType = heroesSuperAbilities[randomNumber];
    }

    public static void round() {
        changeBossDefence();
        Random r = new Random();
        int chance = r.nextInt(2); //0,1
        if (chance == 1) {
            System.out.println("Boss hits first");
            bossHit();
            medicHealing();
            heroesHit();
        } else {
            System.out.println("Heroes hit first");
            heroesHit();
            bossHit();
            medicHealing();
        }
        printStatistics();
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesSuperAbilities[i] == bossDefenceType) {
                    Random r = new Random();
                    int coeff = r.nextInt(7) + 2; //0,1,2,3,4
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println(heroesSuperAbilities[i] + " hit with critical damage " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] - bossDamage < 0) {
                heroesHealth[i] = 0;
            } else {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }
    }

    public static void printStatistics() {
        System.out.println("__________________");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("Medic health: " + heroesHealth[3]);
        System.out.println("__________________");
    }
}
