package com.green.sang.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.sang.dto.Artist;
import com.green.sang.mapper.ArtistMapper;

@Service
public class ArtistServiceImpl implements ArtistService {
	@Autowired
	private ArtistMapper arm;

//	아티스트 전체 리스트
	public List<Artist> art_list() {
		return arm.art_list();
	}
	public int getTotal() {
		return arm.getTotal();
	}
	public int getTotal_news() {
		return arm.getTotal_news();
	}
	public int getTotal_interview() {
		return arm.getTotal_interview();
	}
	public List<Artist> artist_list(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return arm.artist_list(map);
	}
	public List<Artist> artist_interview(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return arm.artist_interview(map);
	}
	public List<Artist> artist_news(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return arm.artist_news(map);
	}
	@Override
	public Artist select_d(int ar_no) {
		return arm.select_d(ar_no);
	}
}
