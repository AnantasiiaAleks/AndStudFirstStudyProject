package com.mygdx.game.Heroes;

import com.mygdx.game.Utilites.BaseChar;

import java.util.ArrayList;
import java.util.Iterator;


public abstract class Magic extends BaseChar {
    private int mana;
    private int maxMana;
    private boolean flag;

    public Magic(String name, int x, int y) {
        super(name, 40, 40, 30,
                40, 60, true, x, y, 1);
        mana = 10;
        maxMana = 10;
        flag = false;

    }
    @Override
    public void step(ArrayList<BaseChar> enemy, ArrayList<BaseChar> friends) {
        if (health < 1) return;
        ArrayList<BaseChar> healingList = new ArrayList<>(friends);
        ArrayList<BaseChar> deathList = new ArrayList<>();
        healingList.sort((o1, o2) -> o1.getHealth() - o2.getHealth());
        Iterator<BaseChar> iterator = healingList.iterator();
        while (iterator.hasNext()) {
            BaseChar unit = iterator.next();
            if (unit.getHealth() == 0) {
                deathList.add(unit);
                iterator.remove();
            }
        }

        if (deathList.size() > 3) flag = true;
        if (flag && mana == maxMana) {
            deathList.sort((o1, o2) -> o2.getInitiative() - o1.getInitiative());
            deathList.get(0).setHealth(maxHealth);
            mana = 0;
            flag = false;
            return;
        }
        if (flag) { mana++; return; }
        if (mana < 2) { mana++; return; }
        healing(healingList.get(0));
        mana-=2;
    }

    public void healing(BaseChar target) {
        int healPoints = r.nextInt(2, 15);
        target.getDamage(-healPoints);
    }


    public int getMana() {return mana;}

    public void setMana(int mana) {this.mana = mana;}

    @Override
    public String toString() {
        return super.toString() + ", \u26a1 " + mana;
    }
}
