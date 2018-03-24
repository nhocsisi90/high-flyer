package com.relic.highflyer.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.Disposable;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.sprites.move.PlayerMovementManager;

public class Player extends Sprite implements InputProcessor, Disposable {

	private static float TILE_SIZE = 32;
	private final MapLayers mapLayers;
	private final GameEngine game;

	private PlayerMovementManager mover = new PlayerMovementManager();

	public Player(GameEngine game, Texture texture, MapLayers mapLayers, int srcX, int srcY) {
		super(texture, srcX, srcY, 106, 80);

		this.mapLayers = mapLayers;
		this.game = game;

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void draw(Batch batch) {
		super.draw(batch);
	}

	public void move() {

	}

	@Override
	public boolean keyDown(int keycode) {
		float nextX = getX();
		float nextY = getY();

		switch (keycode) {
		// case Input.Keys.LEFT:
		//
		// nextX = getX() - TILE_SIZE;
		// break;
		// case Input.Keys.RIGHT:
		//
		// nextX = getX() + TILE_SIZE;
		// ;
		// break;
		case Input.Keys.UP:
			nextY = getY() + TILE_SIZE;
			break;
		case Input.Keys.DOWN:
			nextY = getY() - TILE_SIZE;
			break;
		}

		mover.tryMove(game, getCell(nextX, nextY), this, nextX, nextY);

		return true;
	}

	private Cell getCell(float x, float y) {
		TiledMapTileLayer backgroundLayer = getBuildingLayer();
		int tileX = (int) (x / backgroundLayer.getTileWidth());
		int tileY = (int) (y / backgroundLayer.getTileHeight());

		return backgroundLayer.getCell(tileX, tileY);
	}

	private TiledMapTileLayer getBuildingLayer() {
		return (TiledMapTileLayer) mapLayers.get(1);
	}

	public void update(float deltaTime) {
		// elapsedTime += Gdx.graphics.getDeltaTime();
		float nextX = getX();
		// handle user input **always should come iffirst**

		nextX += deltaTime * 100;

		mover.tryMove(game, getCell(nextX, getY()), this, nextX, getY());
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
