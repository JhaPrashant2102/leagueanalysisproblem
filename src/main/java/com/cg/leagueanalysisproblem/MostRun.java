package com.cg.leagueanalysisproblem;

import com.opencsv.bean.CsvBindByName;

public class MostRun {
	@CsvBindByName(column = "POS")
	private String pos;
	@CsvBindByName(column = "PLAYER")
	private String player;
	@CsvBindByName(column = "Mat")
	private String matches;
	@CsvBindByName(column = "Inns")
	private String innings;
	@CsvBindByName(column = "NO")
	private String notOuts;
	@CsvBindByName(column = "Runs")
	private String runs;
	@CsvBindByName(column = "HS")
	private String highestScore;
	@CsvBindByName(column = "Avg")
	private Double avg;
	@CsvBindByName(column = "BF")
	private String ballsFaced;
	@CsvBindByName(column = "SR")
	private double strikeRate;
	@CsvBindByName(column = "100")
	private String hundreds;
	@CsvBindByName(column = "50")
	private String fifties;
	@CsvBindByName(column = "4s")
	private int fours;
	@CsvBindByName(column = "6s")
	private int sixes;

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getMatches() {
		return matches;
	}

	public void setMatches(String matches) {
		this.matches = matches;
	}

	public String getInnings() {
		return innings;
	}

	public void setInnings(String innings) {
		this.innings = innings;
	}

	public String getNotOuts() {
		return notOuts;
	}

	public void setNotOuts(String notOuts) {
		this.notOuts = notOuts;
	}

	public String getRuns() {
		return runs;
	}

	public void setRuns(String runs) {
		this.runs = runs;
	}

	public String getHighestScore() {
		return highestScore;
	}

	public void setHighestScore(String highestScore) {
		this.highestScore = highestScore;
	}

	public double getAvg() {
		return this.avg;
	}

	public void setAvg(Double avg) {
		this.avg = avg;
	}

	public String getBallsFaced() {
		return ballsFaced;
	}

	public void setBallsFaced(String ballsFaced) {
		this.ballsFaced = ballsFaced;
	}

	public double getStrikeRate() {
		return strikeRate;
	}

	public void setStrikeRate(double strikeRate) {
		this.strikeRate = strikeRate;
	}

	public String getHundreds() {
		return hundreds;
	}

	public void setHundreds(String hundreds) {
		this.hundreds = hundreds;
	}

	public String getFifties() {
		return fifties;
	}

	public void setFifties(String fifties) {
		this.fifties = fifties;
	}

	public int getFours() {
		return fours;
	}

	public void setFours(int fours) {
		this.fours = fours;
	}

	public int getSixes() {
		return sixes;
	}

	public void setSixes(int sixes) {
		this.sixes = sixes;
	}
	
	public int getBoundaries() {
		return this.fours+this.sixes;
	}

	@Override
	public String toString() {
		return "Batting [pos=" + pos + ", player=" + player + ", matches=" + matches + ", innings=" + innings
				+ ", notOuts=" + notOuts + ", runs=" + runs + ", highestScore=" + highestScore + ", avg=" + avg
				+ ", ballsFaced=" + ballsFaced + ", strikeRate=" + strikeRate + ", hundreds=" + hundreds + ", fifties="
				+ fifties + ", fours=" + fours + ", sixes=" + sixes + "]";
	}
}
