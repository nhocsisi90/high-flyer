package com.relic.highflyer.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.Disposable;

public class Player extends Sprite implements InputProcessor, Disposable {

	private static float TILE_SIZE = 32;
	private MapLayers mapLayers;
	private int timmer;

	public Player(Texture texture, MapLayers mapLayers, int srcX, int srcY) {
		super(texture, srcX, srcY, 106, 80);

		this.mapLayers = mapLayers;

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
//		case Input.Keys.LEFT:
//
//			nextX = getX() - TILE_SIZE;
//			break;
//		case Input.Keys.RIGHT:
//
//			nextX = getX() + TILE_SIZE;
//			;
//			break;
		case Input.Keys.UP:
			nextY = getY() + TILE_SIZE;
			break;
		case Input.Keys.DOWN:
			nextY = getY() - TILE_SIZE;
			break;
		}

		if (!handleCollision(nextX+TILE_SIZE, nextY+ 2*TILE_SIZE)) {
			setX(nextX);
			setY(nextY);
		}

		return true;
	}
	
	private boolean handleCollision(float x, float y) {
		if (isTileBlocked(x, y)) {
			setX(0);
			setY(32*4);
			
			return true;
		}
		
		return false;
	}
	private boolean handleLanding (float x, float y) {
		if (isLanding(x,y)) {
			
		}
		return true;
	}

	private boolean isTileBlocked(float x, float y) {
		Cell cell = getCell(x + 32, y + 32);
		return cell != null && cell.getTile().getProperties().containsKey("damage");
	}
	
	private boolean isLanding(float x, float y) {
		Cell cell = getCell(x, y);
		return cell != null & cell.getTile().getProperties().containsKey("landing");
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
		//elapsedTime += Gdx.graphics.getDeltaTime();
		float nextX = getX();
	    // handle user input **always should come iffirst**
	    
	        nextX += deltaTime*100;
	    if (!handleCollision(nextX, this.getY())) {
	    	setX(nextX);
	    }
	    System.out.print(nextX);

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
