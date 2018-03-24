package com.relic.highflyer.sprites.move;

import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.sounds.SoundPlayer;
import com.relic.highflyer.sprites.Player;

public class BuildingCollisionDetector implements CollisionDetector {
	
	private SoundPlayer soundPlayer;
	
	public BuildingCollisionDetector(SoundPlayer soundPlayer) {
		this.soundPlayer = soundPlayer;
	}

	@Override
	public boolean detect(GameEngine game, List<Cell> nextCells, Player player, float nextX, float nextY) {
		if (isBuildingCell(nextCells)) {
			// TODO: Handle damage and sounds
			player.setX(0);
			player.setY((game.getSettings().getWindowHeight() / 2) - (player.getPlayerHeight() / 2));
			soundPlayer.playSound("data/sounds/Shutdown.wav");
			
			game.getState().penaltyDeath();
			return true;
		}

		return false;
	}

	private boolean isBuildingCell(List<Cell> nextCells) {
		for (Cell cell: nextCells) {
			if (cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("damage")) {
				return true;
			}
		}
		return false;
		
	}

}
