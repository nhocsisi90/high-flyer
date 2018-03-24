package com.relic.highflyer.screens;

import com.relic.highflyer.GameEngine;

public class Level1 extends AbstractLevel {

	public Level1(GameEngine game) {
		super(game);
	}

	@Override
	protected String getLevelMap() {
		return "lvl1_version2.tmx";
	}
}
