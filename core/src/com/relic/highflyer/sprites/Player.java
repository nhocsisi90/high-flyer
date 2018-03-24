package com.relic.highflyer.sprites;

import java.util.ArrayList;
import java.util.List;

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

	private static final float TILE_SIZE = 32;
	private static final int PLAYER_TEXTURE_WIDTH = 106;
	private static final int PLAYER_TEXTURE_HEIGHT = 80;

	private final MapLayers mapLayers;
	private final GameEngine game;

	private PlayerMovementManager mover = new PlayerMovementManager();

	public Player(GameEngine game, Texture texture, MapLayers mapLayers, int srcX, int srcY) {
		super(texture, srcX, srcY, PLAYER_TEXTURE_WIDTH, PLAYER_TEXTURE_HEIGHT);

		this.mapLayers = mapLayers;
		this.game = game;

		Gdx.input.setInputProcessor(this);
	}

	public int getPlayerWidth() {
		// Actual width is a little less than the texture
		return PLAYER_TEXTURE_WIDTH - 20;
	}

	public int getPlayerHeight() {
		return PLAYER_TEXTURE_HEIGHT;
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

		float nextCellX = getX();
		float nextCellY = getY();

		List<Cell> nextCells = new ArrayList<>();
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
			nextCellY = nextY + getPlayerHeight();
			nextCellX = nextX + getPlayerWidth();
			
			int count = (int) Math.ceil(this.getPlayerWidth() / TILE_SIZE);
			for (int i = 0; i < count; i++) {
				Cell cell = getCell(nextCellX - (i * TILE_SIZE), nextCellY);
				nextCells.add(cell);
			}
			break;
		case Input.Keys.DOWN:
			nextY = getY() - TILE_SIZE;
			nextCellY = nextY;
			nextCellX = nextX + getPlayerWidth();
			
			count = (int) Math.ceil(this.getPlayerWidth() / TILE_SIZE);
			for (int i = 0; i < count; i++) {
				Cell cell = getCell(nextCellX - (i * TILE_SIZE), nextCellY);
				nextCells.add(cell);
			}
			break;
		}
		
		mover.tryMove(game, nextCells, this, nextX, nextY);

		return true;
	}

	private Cell getCell(float x, float y) {
		System.out.println(String.format("Getting cell X=%s, Y=%s", x,y));
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

		float nextCellY = getY() + getPlayerHeight();
		float nextCellX = nextX + getPlayerWidth();
		
		List<Cell> nextCells = new ArrayList<>();
		int nextCellsCount = (int) Math.ceil(this.getPlayerHeight() / TILE_SIZE);
		for (int i = 0; i < nextCellsCount; i++) {
			Cell cell = getCell(nextCellX, nextCellY - (i * TILE_SIZE));
			nextCells.add(cell);
		}

		mover.tryMove(game, nextCells, this, nextX, getY());
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
