package com.relic.highflyer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.relic.highflyer.screens.ScreenManager;

public class GameEngine extends Game {

	private final GameSettings settings;
	private final GameState state;
	private SpriteBatch batch;

	public GameEngine(GameSettings settings, GameState state) {
		this.settings = settings;
		this.state = state;
	}

	@Override
	public void create() {
		ScreenManager.init(this);

		batch = new SpriteBatch();
		this.setScreen(ScreenManager.instance().getInitial());
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
}
