package com.relic.highflyer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.relic.highflyer.GameEngine;
import com.relic.highflyer.GameSettings;

public class DesktopLauncher {
	public static void main(String[] arg) {
		GameSettings settings = new GameSettings(AspectRatio.STANDARD.calculateWidth(600), 600);

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "High Flyer - Soars to the sky!";
		config.useGL30 = false;
		config.height = settings.getWindowHeight();
		config.width = settings.getWindowWidth();
		new LwjglApplication(new GameEngine(settings), config);
	}

	public enum AspectRatio {
		STANDARD(16, 9);

		private final int widthRatio;
		private final int heightRatio;

		private AspectRatio(int widthRatio, int heightRatio) {
			this.widthRatio = widthRatio;
			this.heightRatio = heightRatio;
		}

		public int calculateWidth(int height) {
			return (height / this.heightRatio) * widthRatio;
		}
	}
}
