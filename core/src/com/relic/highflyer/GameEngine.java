package com.relic.highflyer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.relic.highflyer.screens.ScreenManager;

public class GameEngine extends Game {

	private final GameSettings settings;
	private final GameState state;
	private final ScreenManager screenManager;
	private SpriteBatch batch;

	public GameEngine(GameSettings settings, GameState state, ScreenManager screenManager) {
		this.settings = settings;
		this.state = state;
		this.screenManager = screenManager;
		this.screenManager.setGame(this);
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		this.setScreen(screenManager.getInitial());
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public GameSettings getSettings() {
		return settings;
	}

	public GameState getState() {
		return state;
	}

	public ScreenManager getScreenManager() {
		return screenManager;
	}
}
