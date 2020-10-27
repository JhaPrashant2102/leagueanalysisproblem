package com.cg.leagueanalysisproblem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.cg.builder.CSVBuilderFactory;
import com.cg.builder.ICSVBuilder;
import com.cg.leagueanalysisproblem.IplAnalyserException.ExceptionType;
import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

@SuppressWarnings("unused")
public class IplAnalyser<E> {
	public enum MostRunSortType {
		AVERAGE, SR, BOUNDARIES, SR_AND_BOUNDARIES, AVERAGE_AND_SR, RUN_AND_AVERAGE,
	}
	public enum MostWicketSortType{
		AVERAGE, SR, ECONOMY,
	}

	List<E> operationalList;

	public int loadData(String mostrunsFilePath, Class classType) throws IplAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(mostrunsFilePath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			try {
				operationalList = csvBuilder.getCSVFileList(reader, classType);
			} catch (CsvException e) {
				throw new IplAnalyserException("Invalid class",
						IplAnalyserException.ExceptionType.INCORRECT_CLASS_TYPE);
			}
			return operationalList.size();
		} catch (IOException e) {
			throw new IplAnalyserException("Invalid file location",
					IplAnalyserException.ExceptionType.INCORRECT_CLASS_TYPE);
		}

	}

	@SuppressWarnings("unchecked")
	public String sortRunData(String mostrunsFilePath, MostRunSortType type) throws IplAnalyserException {
		List<MostRun> runList = (List<MostRun>) getCSVFileList(mostrunsFilePath, MostRun.class);
		if (runList.size() == 0 || runList == null) {
			throw new IplAnalyserException("Empty List", ExceptionType.EMPTY_LIST);
		}
		List<E> sortedList = null;
		switch (type) {
		case AVERAGE:
			sortedList = (List<E>) runList.stream().sorted(Comparator.comparing(MostRun::getAvg).reversed())
					.collect(Collectors.toList());
			break;
		case SR:
			sortedList = (List<E>) runList.stream().sorted(Comparator.comparing(MostRun::getStrikeRate).reversed())
					.collect(Collectors.toList());
			break;
		case BOUNDARIES:
			sortedList = (List<E>) runList.stream().sorted(Comparator.comparing(MostRun::getBoundaries).reversed())
					.collect(Collectors.toList());
			break;
		case SR_AND_BOUNDARIES:
			sortedList = (List<E>) runList.stream().sorted(
					Comparator.comparing(MostRun::getStrikeRate).thenComparing(MostRun::getBoundaries).reversed())
					.collect(Collectors.toList());
			break;
		case AVERAGE_AND_SR:
			sortedList = (List<E>) runList.stream()
					.sorted(Comparator.comparing(MostRun::getAvg).thenComparing(MostRun::getStrikeRate).reversed())
					.collect(Collectors.toList());
			break;
		case RUN_AND_AVERAGE:
			sortedList = (List<E>) runList.stream()
					.sorted(Comparator.comparing(MostRun::getRuns).thenComparing(MostRun::getAvg).reversed())
					.collect(Collectors.toList());
			break;
		default:
			break;
		}
		String sortedJsonData = new Gson().toJson(sortedList);
		return sortedJsonData;

	}

	private List<E> getCSVFileList(String mostrunsFilePath, Class classType) throws IplAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(mostrunsFilePath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<E> csvList = null;
			try {
				csvList = csvBuilder.getCSVFileList(reader, classType);
			} catch (CsvException e) {
				throw new IplAnalyserException("Incorrect Class Type", ExceptionType.INCORRECT_CLASS_TYPE);
			}
			return csvList;
		} catch (IOException e) {
			throw new IplAnalyserException("Invalid File Path For Code Data", ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	@SuppressWarnings("unchecked")
	public String sortWicketData(String mostwicketsFilePath, MostWicketSortType type) throws IplAnalyserException {
		List<MostWicket> wicketList = (List<MostWicket>) getCSVFileList(mostwicketsFilePath, MostWicket.class);
		if (wicketList.size() == 0 || wicketList == null) {
			throw new IplAnalyserException("Empty List", ExceptionType.EMPTY_LIST);
		}
		List<E> sortedList = null;
		switch (type) {
		case AVERAGE:
			sortedList = (List<E>) wicketList.stream().sorted(Comparator.comparing(MostWicket::getAvg).reversed())
					.collect(Collectors.toList());
			break;
		case SR:
			sortedList = (List<E>) wicketList.stream().sorted(Comparator.comparing(MostWicket::getStrikeRate).reversed())
					.collect(Collectors.toList());
			break;
		case ECONOMY:
			sortedList = (List<E>) wicketList.stream().sorted(Comparator.comparing(MostWicket::getEconomy))
					.collect(Collectors.toList());
			break;
		default:
			break;
		}
		String sortedJsonData = new Gson().toJson(sortedList);
		return sortedJsonData;
	}

}
