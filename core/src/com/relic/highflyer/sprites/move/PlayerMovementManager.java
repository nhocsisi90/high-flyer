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
		collisionDetectors.add(new LandingDetector());
		collisionDetectors.add(new PowerUpDetector());
	}

	public boolean tryMove(GameEngine game, List<Cell> nextCells, Player player, float nextX, float nextY) {
		boolean canMove = true;
		for (CollisionDetector collisionDetector : collisionDetectors) {
			boolean hasCollision = collisionDetector.detect(game, nextCells, player, nextX, nextY);
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
