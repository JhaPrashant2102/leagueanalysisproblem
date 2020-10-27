package com.cg.leagueanalysisproblem;

import com.opencsv.bean.CsvBindByName;

public class MostWicket {
	@CsvBindByName(column = "POS")
	private String pos;
	@CsvBindByName(column = "PLAYER")
	private String player;
	@CsvBindByName(column = "Mat")
	private String matches;
	@CsvBindByName(column = "Inns")
	private String innings;
	@CsvBindByName(column = "Ov")
	private String overs;
	@CsvBindByName(column = "Runs")
	private String runs;
	@CsvBindByName(column = "Wkts")
	private int wickets;
	@CsvBindByName(column = "BBI")
	private String bbi;
	@CsvBindByName(column = "Avg")
	private double avg;
	@CsvBindByName(column = "Econ")
	private double economy;
	@CsvBindByName(column = "SR")
	private double strikeRate;
	@CsvBindByName(column = "4w")
	private int fourWickets;
	@CsvBindByName(column = "5w")
	private int fiveWickets;

	@Override
	public String toString() {
		return "Bowling [pos=" + pos + ", player=" + player + ", matches=" + matches + ", innings=" + innings
				+ ", overs=" + overs + ", runs=" + runs + ", wickets=" + wickets + ", bbi=" + bbi + ", avg=" + avg
				+ ", economy=" + economy + ", strikeRate=" + strikeRate + ", fourWickets=" + fourWickets
				+ ", fiveWickets=" + fiveWickets + "]";
	}

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

	public String getOvers() {
		return overs;
	}

	public void setOvers(String overs) {
		this.overs = overs;
	}

	public String getRuns() {
		return runs;
	}

	public void setRuns(String runs) {
		this.runs = runs;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public String getBbi() {
		return bbi;
	}

	public void setBbi(String bbi) {
		this.bbi = bbi;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public double getEconomy() {
		return economy;
	}

	public void setEconomy(double economy) {
		this.economy = economy;
	}

	public double getStrikeRate() {
		return strikeRate;
	}

	public void setStrikeRate(double strikeRate) {
		this.strikeRate = strikeRate;
	}

	public int getFourWickets() {
		return fourWickets;
	}

	public void setFourWickets(int fourWickets) {
		this.fourWickets = fourWickets;
	}

	public int getFiveWickets() {
		return fiveWickets;
	}

	public void setFiveWickets(int fiveWickets) {
		this.fiveWickets = fiveWickets;
	}
	public int get4W5W() {
		return this.fiveWickets+this.fourWickets;
	}
}
