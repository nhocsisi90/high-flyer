package com.relic.highflyer.sprites.move;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.sprites.Player;

public class BuildingCollisionDetector implements CollisionDetector {

	@Override
	public boolean detect(GameEngine game, Cell nextCell, Player player, float nextX, float nextY) {
		if (isBuildingCell(nextCell, nextX, nextY)) {
			// TODO: Handle damage and sounds
			player.setX(0);
			player.setY((game.getSettings().getWindowHeight()/2) - (player.getPlayerHeight()/2));
			return true;
		}
		
		return false;
	}

	private boolean isBuildingCell(Cell cell, float x, float y) {
		return cell != null && cell.getTile().getProperties().containsKey("damage");
	}

}
