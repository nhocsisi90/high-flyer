package com.relic.highflyer.sprites.move;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.sprites.Player;

public class BuildingCollisionDetector implements CollisionDetector {

	@Override
	public boolean detect(GameEngine game, List<Cell> nextCells, Player player, float nextX, float nextY) {
		if (isBuildingCell(nextCells, nextX, nextY)) {
			// TODO: Handle damage and sounds
			player.setX(0);
			player.setY((game.getSettings().getWindowHeight() / 2) - (player.getPlayerHeight() / 2));

			Sound shutdownSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/Shutdown.wav"));
			shutdownSound.play();
			game.getState().penaltyDeath();
			return true;
		}

		return false;
	}

	private boolean isBuildingCell(List<Cell> nextCells, float x, float y) {
		for (Cell cell: nextCells) {
			if (cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("damage")) {
				return true;
			}
		}
		return false;
		
	}

}
