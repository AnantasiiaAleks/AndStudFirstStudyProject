package com.mygdx.game.Heroes;

import com.mygdx.game.Utilites.BaseChar;

import java.util.ArrayList;

public abstract class Range extends BaseChar {
    private int arrows = 20;
    public Range(String name, int x, int y) {
        super(name, 50, 50, 30,
                40, 60, true, x, y, 3);
    }
    @Override
    public void step(ArrayList<BaseChar> enemy, ArrayList<BaseChar> friends) {
        if (health < 1 || getArrows() <= 0) return;
        BaseChar target = super.nearestTarget(enemy);
        if(target == null) return;
        attack(target);

        for (BaseChar unit : friends) {
            if (unit.getInfo().equals("Фермер") && !((Peasant)unit).busy) {
                ((Peasant) unit).busy = true;
                return;
            }
        }
        arrows--;
    }

    private void attack(BaseChar target) {
        int damage =  r.nextInt(2, 15);
        target.getDamage(damage);
    }
    public int getArrows() {return arrows;}

    public void setArrows(int arrows) {this.arrows = arrows;}

    @Override
    public String toString() {
        return super.toString() + ", \u27b6 " + arrows;
    }
}
