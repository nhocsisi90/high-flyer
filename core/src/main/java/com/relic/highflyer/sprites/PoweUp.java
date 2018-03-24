package com.relic.highflyer.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayers;
import com.relic.highflyer.GameEngine;

public class PoweUp extends Sprite {
	private static final float TILE_SIZE = 32;
	private static final int POWER_WIDTH = 30;
	private static final int POWER_HEIGHT = 32;
	private final MapLayers mapLayers;
	private final GameEngine game;
	public PoweUp(GameEngine game, Texture texture, MapLayers mapLayers, int srcX, int srcY) {
		super(texture, srcX, srcY, POWER_WIDTH, POWER_HEIGHT);

		this.mapLayers = mapLayers;
		this.game = game;

	}
	public int getPowerWidth() {
		return POWER_WIDTH;
	}

	public int getPowerHeight() {
		return POWER_HEIGHT;
	}

	@Override
	public void draw(Batch batch) {
		super.draw(batch);
	}

}
