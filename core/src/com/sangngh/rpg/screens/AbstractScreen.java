package com.sangngh.rpg.screens;

import com.badlogic.gdx.ScreenAdapter;

public abstract class AbstractScreen extends ScreenAdapter {

	private String map;

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}
	
	public static class Entrance {
		private String position;
		private String whenFrom;
	}
}
