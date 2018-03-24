package com.relic.highflyer.screens;

import com.relic.highflyer.GameEngine;

public class Level2 extends AbstractLevel {

	public Level2(GameEngine game) {
		super(game);
	}

	@Override
	protected String getLevelMap() {
		return "lvl2.tmx";
	}
}

