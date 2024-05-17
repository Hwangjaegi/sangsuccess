package com.green.sang.dto;

import java.sql.Date;
import org.apache.ibatis.type.Alias;
import lombok.Data;

@Data
@Alias("union")
public class Union {
	private int no;
	private String kind;
	private String title;
	private String content;
	private String image; 
	private Date date;
}