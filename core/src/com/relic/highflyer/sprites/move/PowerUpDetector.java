package com.relic.highflyer.sprites.move;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.sprites.Player;

public class PowerUpDetector implements CollisionDetector  {
	
	@Override
	public boolean detect(GameEngine game, Cell nextCell, Player player, float nextX, float nextY) {
		if (isPowerCell(nextCell, nextX, nextY)) {
			
			nextCell.setTile(null);
			
			Sound shutdownSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/PowerUp.wav"));
			shutdownSound.play();
			return true;
		}
		
		return false;
	}

	private boolean isPowerCell(Cell cell, float x, float y) {
		return cell != null && cell.getTile().getProperties().containsKey("power");
	}


}
