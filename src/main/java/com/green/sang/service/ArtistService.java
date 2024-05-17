package com.green.sang.service;

import java.util.List;

import com.green.sang.dto.Artist;

public interface ArtistService {
	
	int getTotal();
	int getTotal_interview();
	int getTotal_news();

	List<Artist> artist_list(int startRow, int endRow);
	List<Artist> artist_interview(int startRow, int endRow);
	List<Artist> artist_news(int startRow, int endRow);
	List<Artist> art_list();
	Artist select_d(int ar_no);
}
