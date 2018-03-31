package com.relic.highflyer.screens;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;
import com.relic.highflyer.GameEngine;

/**
 * This class manages the transition from one level to another.
 */
public class ScreenManager implements Disposable {

	public static final ScreenManager INSTANCE = new ScreenManager();
	

	private GameEngine game;
	private Map<Integer, Screen> screens = new HashMap<>();

	public void setGame(GameEngine game) {
		this.game = game;
	}

	public Screen getInitial() {
		Screen screen = new Level1(game);
		screens.put(1, screen);
		return screen;
	}
	
	public void setScreen(AbstractLevel level ) {
		game.setScreen(level);
	}
	
	public Screen getScreen(int level) {
		Screen screen = screens.get(level);
		if (screen == null) {
			switch (level) {
			case 1:
				screen = new Level1(game);
				screens.put(1, screen);
				break;
			case 2:
				screen = new Level2(game);
				screens.put(2, screen);
			default:
				break;
			}	
		}
		return screen;
	}

	@Override
	public void dispose() {
	}
}
