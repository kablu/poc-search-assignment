package com.poc.search;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.poc.search.dto.Example;
import com.poc.search.dto.PhtotJsonDto;
import com.poc.search.dto.SearchDTO;
import com.poc.search.dto.User;
import com.poc.search.service.SearchService;

@RestController
public class SearchController {

	@Autowired
	SearchService searchService;

	@Autowired
	PhotoRepository photoRepository;
	
	@GetMapping("/getAll")
	public List<Photos> index() {
		return photoRepository.findAll();
	}
	
	/*
	 * @GetMapping("/getAllUsers") public List<Photos> getAllUsers() { return
	 * photoRepository.findAll(); }
	 */

	@GetMapping("/listData")
	public String listData() {
		return searchService.prepareStaticData();
	}
	
	@GetMapping("/search/{title}")
	public ResponseEntity<String> searchByTitle(@PathVariable String title) {

		System.out.println("@@inside searchByTitle.." + title);
		String list = searchService.prepareStaticData();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/insert")
	public ResponseEntity<String> listPhoto() throws JsonSyntaxException, JsonIOException, IOException {

		System.out.println("@@inside listPhoto");
		List<PhtotJsonDto[]> list = searchService.convertJsonToDto();

		savePhoto(list);
		// photoRepository.save(entity)
		return ResponseEntity.ok("Record Inserted");
	}

	
	@GetMapping("/insertUsers")
	public ResponseEntity<List<Example[]>> insertUsers() throws JsonSyntaxException, JsonIOException, IOException {

		System.out.println("@@inside insertUsers");
		List<Example[]> list = searchService.convertUserJsonToDto();

		//savePhoto(list);
		// photoRepository.save(entity)
		return ResponseEntity.ok(list);
	}
	
	public int savePhoto(List<PhtotJsonDto[]> list) {

		System.out.println("@@inside savePhoto");
		int totalRec = list.size();

		System.out.println("@@inside for loop");
		PhtotJsonDto[] photojsonDtoArr = list.get(0);

		int arrlen = photojsonDtoArr.length;

		for (int i = 0; i < arrlen; i++) {
			PhtotJsonDto photoJsonBean = photojsonDtoArr[i];
			Photos photos = new Photos();

			photos.setId(i + "");
			photos.setAlbumId(photoJsonBean.getAlbumId() + "");
			photos.setTitle(photoJsonBean.getTitle());
			photos.setUrl(photoJsonBean.getUrl());

			photoRepository.save(photos);

		}

		System.out.println(photojsonDtoArr[0] + " " + photojsonDtoArr[1] + " " + photojsonDtoArr[2]);

		return totalRec;
	}

}
