package com.cg.leagueanalysisproblem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cg.leagueanalysisproblem.IplAnalyser.MostRunSortType;
import com.cg.leagueanalysisproblem.IplAnalyser.MostWicketSortType;
import com.google.gson.Gson;

public class IPLAnalysisTest {

	private static final String MOSTRUNS_FILE_PATH = "./src/resource/IPL2019FactsheetMostRuns.csv";
	private static final String MOSTWICKETS_FILE_PATH = "./src/resource/IPL2019FactsheetMostWkts.csv";
	private IplAnalyser iplAnalyser;

	@Before
	public void init() {
		iplAnalyser = new IplAnalyser();
	}

	@Test
	public void givenMostRunCsvFileShouldReturnCorrectNumOfRecords() throws IplAnalyserException {
		int numOfEntries = iplAnalyser.loadData(MOSTRUNS_FILE_PATH, MostRun.class);
		Assert.assertEquals(101, numOfEntries);
	}

	@Test
	public void givenMostWicketCsvFileShouldReturnCorrectNumOfRecords() throws IplAnalyserException {
		int numOfEntries = iplAnalyser.loadData(MOSTWICKETS_FILE_PATH, MostWicket.class);
		Assert.assertEquals(99, numOfEntries);
	}

	@Test
	public void givenMostRunCsvFile_WhenSortedByAvg_ShouldReturnHighestAverageFirst() throws IplAnalyserException {
		String sortedData = iplAnalyser.sortRunData(MOSTRUNS_FILE_PATH, MostRunSortType.AVERAGE);
		MostRun[] highestAvgData = new Gson().fromJson(sortedData, MostRun[].class);
		Assert.assertEquals(83.2, highestAvgData[0].getAvg(), 0.0);
	}

	@Test
	public void givenMostRunCsvFile_WhenSortedBySR_ShouldReturnHighestSRFirst() throws IplAnalyserException {
		String sortedData = iplAnalyser.sortRunData(MOSTRUNS_FILE_PATH, MostRunSortType.SR);
		MostRun[] highestData = new Gson().fromJson(sortedData, MostRun[].class);
		Assert.assertEquals(333.33, highestData[0].getStrikeRate(), 0.0);
		Assert.assertEquals("Ishant Sharma", highestData[0].getPlayer());
	}

	@Test
	public void givenMostRunCsvFile_WhenSortedByBounndaries_ShouldReturnPlayerWithHighestBoundaries()
			throws IplAnalyserException {
		String sortedData = iplAnalyser.sortRunData(MOSTRUNS_FILE_PATH, MostRunSortType.BOUNDARIES);
		MostRun[] highestData = new Gson().fromJson(sortedData, MostRun[].class);
		Assert.assertEquals(83, highestData[0].getBoundaries());
		Assert.assertEquals("Andre Russell", highestData[0].getPlayer());
	}

	@Test
	public void givenMostRunCsvFile_WhenSortedBySRAndBoundaries_ShouldReturnHighestSRFirst()
			throws IplAnalyserException {
		String sortedData = iplAnalyser.sortRunData(MOSTRUNS_FILE_PATH, MostRunSortType.SR_AND_BOUNDARIES);
		MostRun[] highestData = new Gson().fromJson(sortedData, MostRun[].class);
		Assert.assertEquals(333.33, highestData[0].getStrikeRate(), 0.0);
		Assert.assertEquals("Ishant Sharma", highestData[0].getPlayer());
	}

	@Test
	public void givenMostRunCsvFile_WhenSorted_ShouldReturnHighestAverageWithBestSR() throws IplAnalyserException {
		String sortedData = iplAnalyser.sortRunData(MOSTRUNS_FILE_PATH, MostRunSortType.AVERAGE_AND_SR);
		MostRun[] highestData = new Gson().fromJson(sortedData, MostRun[].class);
		Assert.assertEquals(83.2, highestData[0].getAvg(), 0.0);
		Assert.assertEquals("MS Dhoni", highestData[0].getPlayer());
	}
	
	@Test
	public void givenMostRunCsvFile_WhenSorted_ShouldReturnPlayerWith_MaxRunWithBestAvg() throws IplAnalyserException {
		String sortedData = iplAnalyser.sortRunData(MOSTRUNS_FILE_PATH, MostRunSortType.RUN_AND_AVERAGE);
		MostRun[] highestData = new Gson().fromJson(sortedData, MostRun[].class);
		Assert.assertEquals("David Warner", highestData[0].getPlayer());
	}
	
	@Test
	public void givenMostWktsCsvFile_WhenSorted_ShouldReturnPlayerWithBestAvg() throws IplAnalyserException {
		String sortedData = iplAnalyser.sortWicketData(MOSTWICKETS_FILE_PATH, MostWicketSortType.AVERAGE);
		MostWicket[] highestData = new Gson().fromJson(sortedData, MostWicket[].class);
		Assert.assertEquals("Krishnappa Gowtham", highestData[0].getPlayer());
		Assert.assertEquals(166, highestData[0].getAvg(),0.0);
	}
	
	@Test
	public void givenMostWktsCsvFile_WhenSorted_ShouldReturnPlayerWithBestSR() throws IplAnalyserException {
		String sortedData = iplAnalyser.sortWicketData(MOSTWICKETS_FILE_PATH, MostWicketSortType.SR);
		MostWicket[] highestData = new Gson().fromJson(sortedData, MostWicket[].class);
		Assert.assertEquals("Krishnappa Gowtham", highestData[0].getPlayer());
		Assert.assertEquals(120, highestData[0].getStrikeRate(),0.0);
	}
	
	@Test
	public void givenMostWktsCsvFile_WhenSorted_ShouldReturnPlayerWithBestEconomy() throws IplAnalyserException {
		String sortedData = iplAnalyser.sortWicketData(MOSTWICKETS_FILE_PATH, MostWicketSortType.ECONOMY);
		MostWicket[] highestData = new Gson().fromJson(sortedData, MostWicket[].class);
		Assert.assertEquals("Shivam Dube", highestData[0].getPlayer());
		Assert.assertEquals(4.8, highestData[0].getEconomy(),0.0);
	}
	
	@Test
	public void givenMostWktsCsvFile_WhenSorted_ShouldReturnPlayerWithBestSRAnd5W4W() throws IplAnalyserException {
		String sortedData = iplAnalyser.sortWicketData(MOSTWICKETS_FILE_PATH, MostWicketSortType.SR_AND_5W_4W);
		MostWicket[] highestData = new Gson().fromJson(sortedData, MostWicket[].class);
		Assert.assertEquals("Krishnappa Gowtham", highestData[0].getPlayer());
		Assert.assertEquals(120, highestData[0].getStrikeRate(),0.0);
	}
	
	@Test
	public void givenMostWktsCsvFile_WhenSorted_ShouldReturnPlayerWithBestAvgAndSR() throws IplAnalyserException {
		String sortedData = iplAnalyser.sortWicketData(MOSTWICKETS_FILE_PATH, MostWicketSortType.AVERAGE_AND_SR);
		MostWicket[] highestData = new Gson().fromJson(sortedData, MostWicket[].class);
		Assert.assertEquals("Krishnappa Gowtham", highestData[0].getPlayer());
		Assert.assertEquals(166, highestData[0].getAvg(),0.0);
	}
	
	@Test
	public void givenMostWktsCsvFile_WhenSorted_ShouldReturnPlayerWithMaxWicketsAndAvg() throws IplAnalyserException {
		String sortedData = iplAnalyser.sortWicketData(MOSTWICKETS_FILE_PATH, MostWicketSortType.WICKETS_AND_AVG);
		MostWicket[] highestData = new Gson().fromJson(sortedData, MostWicket[].class);
		Assert.assertEquals("Imran Tahir", highestData[0].getPlayer());
		Assert.assertEquals(26, highestData[0].getWickets());
	}
	@Test
	public void givenMostWktsAndMostRunCsvFile_WhenSorted_ShouldReturnPlayerWithMaxAvg() throws IplAnalyserException {
		String sortedWicketData = iplAnalyser.sortWicketData(MOSTWICKETS_FILE_PATH, MostWicketSortType.AVERAGE);
		String sortedRunData = iplAnalyser.sortRunData(MOSTRUNS_FILE_PATH, MostRunSortType.AVERAGE);
		MostWicket[] highestWicketData = new Gson().fromJson(sortedWicketData, MostWicket[].class);
		MostRun[] highestRunData = new Gson().fromJson(sortedRunData, MostRun[].class);
		Assert.assertEquals("Krishnappa Gowtham", highestWicketData[0].getPlayer());
		Assert.assertEquals("MS Dhoni", highestRunData[0].getPlayer());
	}
}
