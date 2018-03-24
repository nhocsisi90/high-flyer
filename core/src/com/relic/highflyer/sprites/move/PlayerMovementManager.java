package com.relic.highflyer.sprites.move;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.sprites.Player;

public class PlayerMovementManager {

	private List<CollisionDetector> collisionDetectors = new ArrayList<>();

	public PlayerMovementManager() {
		collisionDetectors.add(new BuildingCollisionDetector());
		collisionDetectors.add(new WindowCollisionDetector());
	}

	public boolean tryMove(GameEngine game, Cell nextCell, Player player, float nextX, float nextY) {
		boolean canMove = true;
		for (CollisionDetector collisionDetector : collisionDetectors) {
			boolean hasCollision = collisionDetector.detect(game, nextCell, player, nextX, nextY);
			if (hasCollision) {
				return false;
			}
		}
		
		if (canMove) {
			player.setX(nextX);
			player.setY(nextY);
		}

		return canMove;
	}
}
