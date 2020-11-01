package com.poc.search.service;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.poc.search.dto.Example;
import com.poc.search.dto.PhtotJsonDto;
import com.poc.search.dto.SearchDTO;

@Service
public class SearchService {

	List<SearchDTO> list = null;

	
	
	@PostConstruct
	public void init() {
		list = new ArrayList<SearchDTO>();
	}

	public String prepareStaticData() {

		SearchDTO searchDTO = new SearchDTO(1, "Mobile", "Nokia", "1987", "2930");
		SearchDTO searchDTO1 = new SearchDTO(2, "TV", "Samsung", "1980", "222930");
		list.add(searchDTO);

		Gson gson = new GsonBuilder().create();
		List<SearchDTO> list = new ArrayList<SearchDTO>();
		list.add(searchDTO);
		list.add(searchDTO1);
		String json = gson.toJson(list);

		System.out.println("json:" + json);
		return json;
	}

	public List<PhtotJsonDto[]> convertJsonToDto() throws JsonSyntaxException, JsonIOException, IOException {
		   Gson gson = new GsonBuilder().create();
	        
	        String fileName = "src/main/resources/data.json";
	        Path path = new File(fileName).toPath();
	        PhtotJsonDto[] photo;
	        List<PhtotJsonDto[]> photoList = new ArrayList<PhtotJsonDto[]>();
	        try (Reader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
	            
	        	photo = gson.fromJson(reader, PhtotJsonDto[].class);
	        	photoList.add(photo);
				/*
				 * Arrays.stream(photo).forEach( e -> { System.out.println(e); });
				 */
	        }
			return photoList;
	}
	
	
	public List<Example[]> convertUserJsonToDto() throws JsonSyntaxException, JsonIOException, IOException {
		   Gson gson = new GsonBuilder().create();
	        
	        String fileName = "src/main/resources/Users.json";
	        Path path = new File(fileName).toPath();
	        Example[] users;
	        List<Example[]> photoList = new ArrayList<Example[]>();
	        try (Reader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
	            
	        	users = gson.fromJson(reader, Example[].class);
	        	photoList.add(users);
				
				  Arrays.stream(users).forEach( e -> { System.out.println(e.getName()); });
				 
	        }
			return photoList;
	}
	
	

}
