package com.relic.highflyer.screens;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;
import com.relic.highflyer.MyGdxGame;

public class ScreenManager implements Disposable {

	private static final String WORLD_MAP = "world_map";
	private static final ScreenManager INSTANCE = new ScreenManager();

	private MyGdxGame game;
	private Map<String, Screen> screens = new HashMap<>();

	private ScreenManager() {
	}

	public static void init(MyGdxGame game) {
		INSTANCE.game = game;
	}

	public static ScreenManager instance() {
		return INSTANCE;
	}

	public Screen getInitial() {
		Screen screen = new WorldScreen(game);
		screens.put(WORLD_MAP, screen);
		return screen;
	}

	public Screen setCurrent(String location) {
		Screen screen = null;
		if (WORLD_MAP.equals(location)) {
			screen = screens.get(location);
			if (screen == null) {
				screen = new WorldScreen(game);
				screens.put(location, screen);
			}
		} else if ("lake_haven".equals(location)) {
			screen = screens.get(location);
			if (screen == null) {
				screen = new CityScreen(game);
				screens.put(location, screen);
			}
		}

		if (screen == null) {
			throw new IllegalArgumentException(String.format("Screen %s not found", location));
		}

		Gdx.app.log("Entering location", location.toString());

		game.setScreen(screen);
		return screen;

	}

	@Override
	public void dispose() {
	}
}
