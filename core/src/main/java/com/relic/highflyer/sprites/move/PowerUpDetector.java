package com.relic.highflyer.sprites.move;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.sprites.Player;

public class PowerUpDetector implements CollisionDetector  {
	
	@Override
	public boolean detect(GameEngine game, List<Cell> nextCells, Player player, float nextX, float nextY) {
		if (isPowerCell(nextCells, nextX, nextY)) {
			
			
			
			Sound shutdownSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/PowerUp.wav"));
			shutdownSound.play();
			game.getState().addPowerUp();
			
			return true;
		}
		
		return false;
	}

	private boolean isPowerCell(List<Cell> nextCells, float x, float y) {
		for (Cell cell: nextCells) {
			if (cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("power")) {
				cell.setTile(null);
				return true;
			}
		}
		return false;
		
	}


}
