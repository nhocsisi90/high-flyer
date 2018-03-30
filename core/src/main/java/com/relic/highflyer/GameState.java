package com.relic.highflyer;

public class GameState {
	private int score=0;
	private int lvl=1;
	
	// ADD LEVEL, X/Y COORDINATE
	// ADD POWERUPS, ADD OTHER SPRITES LOCATION
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	
	public void addLvlUp() {
		this.score+= 50 + this.lvl*10;
	}
	
	public void addKill() {
		this.score+= 50;
	}
	
	public void addPowerUp(String typePower) {
      switch  (typePower)
		{
		case "star":
			this.score+=10;
			break;
		}
			
	}
	
	public void penaltyDeath() {
		if (this.score <100)
			this.score = 0;
		else
			this.score-= 100;
	}


}
