package com.mygdx.game.Utilites;

import java.util.ArrayList;
import java.util.Random;

public abstract class BaseChar implements Step, Comparable<BaseChar> {
    protected Random r = new Random();
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int strength;
    protected int aqility;
    protected int stamina;
    protected boolean alive;
    public Position position;
    protected int initiative;

    public BaseChar(String name, int health, int maxHealth, int strength, int aqility, int stamina, boolean alive, int x, int y, int initiative) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.strength = strength;
        this.aqility = aqility;
        this.stamina = stamina;
        this.alive = alive;
        this.position = new Position(x, y);
        this.initiative = initiative;
    }

    public BaseChar(String name, int x, int y, int initiative) {
        this.name = name;
        this.health = 50;
        this.maxHealth = 50;
        this.strength = 20;
        this.aqility = 20;
        this.stamina = 50;
        this.alive = true;
        this.position = new Position(x, y);
        this.initiative = initiative;
    }
    @Override
    public String toString() {
        return name + ", \u2665 " + health;
    }
    public String getInfo() {
        return "";
    }

    public BaseChar nearestTarget(ArrayList<BaseChar> enemys) {
        BaseChar target = null;
        double minDistance = 100;
        for (BaseChar enemy : enemys) {
            if (position.getDistance(enemy) < minDistance && enemy.getHealth() > 0) {
                minDistance = position.getDistance(enemy);
                target = enemy;
            }
        }
        return target;
    }

    public void getDamage(int damage) {
        health -= damage;
        if (health < 1) {
            health = 0;
        }
        if (health >= maxHealth) health = maxHealth;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public  int getHealth() {return health;}

    public int getMaxHealth() {return maxHealth;}

    public void setHealth(int health) {this.health = health;}

    public int getStamina() { return stamina; }

    public int getInitiative() {return initiative; }

        @Override
    public int compareTo(BaseChar o) {
        if (this.initiative > o.initiative)
            return -1;
        else if (this.initiative < o.initiative)
            return 1;
        else
            return 0;
    }

}
