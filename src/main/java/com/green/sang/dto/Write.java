package com.green.sang.dto;

import java.sql.Date;
import org.apache.ibatis.type.Alias;
import lombok.Data;

@Data
@Alias("Write")
public class Write {
	private int w_no;
	private String w_title;
	private String w_content;
	private Date w_date;
	private int w_good_count;
	private int w_repl_count;
	private String id;
	
	// join용
	private String image;
	private String name;
}
