package com.relic.highflyer.sprites.move;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.sounds.SoundPlayer;
import com.relic.highflyer.sprites.Player;

public class PowerUpDetector implements CollisionDetector  {
	
	private SoundPlayer soundPlayer;
	private String typePower="";
	
	public PowerUpDetector(SoundPlayer soundPlayer) {
		this.soundPlayer = soundPlayer;
	}
	
	@Override
	public boolean detect(GameEngine game, List<Cell> nextCells, Player player, float nextX, float nextY) {
		if (isPowerCell(nextCells, nextX, nextY)) {
			
			
			soundPlayer.playSound("data/sounds/PowerUp.wav");
			game.getState().addPowerUp(typePower);
			
			return true;
		}
		
		return false;
	}

	private boolean isPowerCell(List<Cell> nextCells, float x, float y) {
		for (Cell cell: nextCells) {
			if (cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("power")) {
				typePower= (String) cell.getTile().getProperties().get("power");
				cell.setTile(null);
				return true;
			}
		}
		return false;
		
	}


}
