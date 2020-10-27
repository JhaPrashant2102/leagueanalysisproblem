package com.cg.leagueanalysisproblem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cg.leagueanalysisproblem.IplAnalyser.MostRunSortType;
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
		String sortedData = iplAnalyser.sortRunData(MOSTRUNS_FILE_PATH,MostRunSortType.AVERAGE);
		Double[] highestAvgData = new Gson().fromJson(sortedData, Double[].class);
		Assert.assertEquals(83.2, highestAvgData[0].doubleValue(), 0.0);
	}
	@Test
	public void givenMostRunCsvFile_WhenSortedBySR_ShouldReturnHighestSRFirst() throws IplAnalyserException {
		String sortedData = iplAnalyser.sortRunData(MOSTRUNS_FILE_PATH,MostRunSortType.SR);
		MostRun[] highestAvgData = new Gson().fromJson(sortedData, MostRun[].class);
		Assert.assertEquals(333.33, highestAvgData[0].getStrikeRate(), 0.0);
		Assert.assertEquals("Ishant Sharma", highestAvgData[0].getPlayer());
	}
}
