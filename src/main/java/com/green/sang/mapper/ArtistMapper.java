package com.green.sang.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.green.sang.dto.Artist;

@Mapper
public interface ArtistMapper {

	int getTotal();
	int getTotal_interview();
	int getTotal_news();

	List<Artist> artist_list(Map<String, Object> map);
	List<Artist> artist_interview(Map<String, Object> map);
	List<Artist> artist_news(Map<String, Object> map);
	List<Artist> art_list();
	Artist select_d(int ar_no);

}