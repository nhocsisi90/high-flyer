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

public class Player extends Sprite implements InputProcessor, Disposable {

	private static final float TILE_SIZE = 32;
	private MapLayers mapLayers;

	public Player(Texture texture, MapLayers mapLayers, int srcX, int srcY) {
		super(texture, srcX, srcY, 106, 80);

		this.mapLayers = mapLayers;

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void draw(Batch batch) {
		super.draw(batch);
	}

	@Override
	public boolean keyDown(int keycode) {
		float nextX = getX();
		float nextY = getY();

		switch (keycode) {
		case Input.Keys.LEFT:
			nextX = getX() - TILE_SIZE;
			break;
		case Input.Keys.RIGHT:
			nextX = getX() + TILE_SIZE;
			break;
		case Input.Keys.UP:
			nextY = getY() + TILE_SIZE;
			break;
		case Input.Keys.DOWN:
			nextY = getY() - TILE_SIZE;
			break;
		}

		if (!isTileBlocked(nextX, nextY)) {
			setX(nextX);
			setY(nextY);
		}

		return true;
	}

	private boolean isTileBlocked(float x, float y) {
		Cell cell = getCell(x, y);
		return cell != null && cell.getTile().getProperties().containsKey("blocking");
	}

	private Cell getCell(float x, float y) {
		TiledMapTileLayer backgroundLayer = getBackgroundLayer();
		int tileX = (int) (x / backgroundLayer.getTileWidth());
		int tileY = (int) (y / backgroundLayer.getTileHeight());

		return backgroundLayer.getCell(tileX, tileY);
	}

	private TiledMapTileLayer getBackgroundLayer() {
		return (TiledMapTileLayer) mapLayers.get(0);
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
