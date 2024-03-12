package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Heroes.MagicUnits.Mage;
import com.mygdx.game.Heroes.MagicUnits.Monk;
import com.mygdx.game.Heroes.MeleeUnits.Rogue;
import com.mygdx.game.Heroes.MeleeUnits.Spearman;
import com.mygdx.game.Heroes.Peasant;
import com.mygdx.game.Heroes.RangeUnits.Crossbowman;
import com.mygdx.game.Heroes.RangeUnits.Sniper;
import com.mygdx.game.Utilites.BaseChar;
import com.mygdx.game.Utilites.Names;

import java.util.ArrayList;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture fon, crossBowMan, mage, monk, peasant, rogue, sniper, spearMan;
	Music music;
	public ArrayList<BaseChar> holyTeam;
	public ArrayList<BaseChar> darkTeam;
	public ArrayList<BaseChar> allTeam;
	
	@Override
	public void create () {
		holyTeam = new ArrayList<>();
		darkTeam = new ArrayList<>();
		allTeam = new ArrayList<>();

		init();
		holyTeam.sort((o1, o2) -> o2.position.getX() - o1.position.getX());
		darkTeam.sort((o1, o2) -> o2.position.getX() - o1.position.getX());
		allTeam.addAll(holyTeam);
		allTeam.addAll(darkTeam);
		allTeam.sort((o1, o2) -> o2.getInitiative() - o1.getInitiative());

		batch = new SpriteBatch();

		fon = new Texture("fon/CmBk"+ MathUtils.random(0, 4)+".png");

		music = Gdx.audio.newMusic(Gdx.files.internal("music/paul-romero-rob-king-combat-theme-0"+MathUtils.random(1, 4)+".mp3"));
		music.setVolume(.03f);
		music.play();

		this.crossBowMan = new Texture("units/CrossBowMan.png");
		this.mage = new Texture("units/Mage.png");
		this.monk = new Texture("units/Monk.png");
		this.peasant = new Texture("units/Peasant.png");
		this.rogue = new Texture("units/Rogue.png");
		this.sniper = new Texture("units/Sniper.png");
		this.spearMan = new Texture("units/SpearMan.png");

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(fon, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		for (BaseChar unit : allTeam) {
			if (unit.getHealth() == 0) continue;
			int y = unit.position.getX() * Gdx.graphics.getWidth() / 15 - 30;
			int x = unit.position.getY() * Gdx.graphics.getHeight() / 15 + 100;
			int k = 1;
			if (darkTeam.contains(unit)) k = -1;
			switch (unit.getInfo()) {
				case "А":
					batch.draw(crossBowMan, x, y, crossBowMan.getWidth() * k, crossBowMan.getHeight());
					break;
				case "В":
					batch.draw(mage, x, y, mage.getWidth() * k, mage.getHeight());
					break;
				case "М":
					batch.draw(monk, x, y, monk.getWidth() * k, monk.getHeight());
					break;
				case "Ф":
					batch.draw(peasant, x, y, peasant.getWidth() * k, peasant.getHeight());
					break;
				case "Р":
					batch.draw(rogue, x, y, rogue.getWidth() * k, rogue.getHeight());
					break;
				case "С":
					batch.draw(sniper, x, y, sniper.getWidth() * k, sniper.getHeight());
					break;
				case "К":
					batch.draw(spearMan, x, y, spearMan.getWidth() * k, spearMan.getHeight());
					break;
			}
		}

		batch.end();

		boolean flag = true;
		for (BaseChar unit : darkTeam) {
            if (unit.getHealth() > 0) {
                flag = false;
            }
		}
		if (flag) {
			Gdx.graphics.setTitle("Команда тёмных победила");
			return;
		}

		flag = true;
		for (BaseChar unit : holyTeam) {
            if (unit.getHealth() > 0) {
                flag = false;
            }
		}
		if (flag) {
			Gdx.graphics.setTitle("Команда светлых победила");
			return;
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.justTouched()) {
				for (BaseChar unit : allTeam) {
				if (holyTeam.contains(unit)) unit.step(darkTeam, holyTeam);
				else unit.step(holyTeam, darkTeam);
			}
		}
	}
	@Override
	public void dispose() {
			batch.dispose();
			fon.dispose();
			music.dispose();
			crossBowMan.dispose();
			mage.dispose();
			monk.dispose();
			peasant.dispose();
			rogue.dispose();
			sniper.dispose();
			spearMan.dispose();
	}

	private String getName(){
		return String.valueOf(Names.values()[new Random().nextInt(Names.values().length-1)]);
	}
	public void init() {
		int teamCount = 10;
		for (int i = 1; i < teamCount + 1; i++) {
			int variant = MathUtils.random(3);
			switch (variant) {
				case 0:
					holyTeam.add(new Crossbowman(getName(), i, 1));
					break;
				case 1:
					holyTeam.add(new Monk(getName(), i, 1));
					break;
				case 2:
					holyTeam.add(new Spearman(getName(), i, 1));
					break;
				case 3:
					holyTeam.add(new Peasant(getName(), i, 1));
					break;
			}
		}

		for (int i = 1; i < teamCount + 1; i++) {
			int variant = MathUtils.random(3);
			switch (variant) {
				case 0:
					darkTeam.add(new Sniper(getName(), i, 10));
					break;
				case 1:
					darkTeam.add(new Mage(getName(), i, 10));
					break;
				case 2:
					darkTeam.add(new Rogue(getName(), i, 10));
					break;
				case 3:
					darkTeam.add(new Peasant(getName(), i, 10));
					break;
			}
		}
	}
}
