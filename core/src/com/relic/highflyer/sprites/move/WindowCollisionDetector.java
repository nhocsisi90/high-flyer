package com.relic.highflyer.sprites.move;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.sprites.Player;

public class WindowCollisionDetector implements CollisionDetector {

	@Override
	public boolean detect(GameEngine game, Cell nextCell, Player player, float nextX, float nextY) {
		if (nextX < 0 || nextY < 0 || nextY > game.getSettings().getWindowHeight() - player.getPlayerHeight()) {
			return true;
		}
		return false;
	}

}
