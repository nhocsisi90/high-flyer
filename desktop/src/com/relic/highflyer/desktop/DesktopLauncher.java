package com.relic.highflyer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.relic.highflyer.GameEngine;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "High Flyer - Soars to the sky!";
		config.useGL30 = true;
		config.height = 600;
		config.width = AspectRatio.STANDARD.calculateWidth(config.height);
		new LwjglApplication(new GameEngine(), config);
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
