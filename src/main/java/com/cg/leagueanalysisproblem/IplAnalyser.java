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

public class IplAnalyser<E> {
	public enum MostRunSortType {
		AVERAGE,
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

	public String sortRunData(String mostrunsFilePath, Class classType, MostRunSortType type)
			throws IplAnalyserException {
		List<MostRun> runList = (List<MostRun>) getCSVFileList(mostrunsFilePath, MostRun.class);
		if (runList.size() == 0 || runList == null) {
			throw new IplAnalyserException("Empty List", ExceptionType.EMPTY_LIST);
		}
		List<Double> sortedList = runList.stream().filter(obj -> !obj.getAvg().contains("-"))
				.map(obj -> obj.getAvg()).map(avg -> Double.parseDouble(avg)).sorted(Collections.reverseOrder())
				.collect(Collectors.toList());
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

}
