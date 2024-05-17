package com.green.sang.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("heartsave")
public class Heartsave {
	private int hs_no; 
	private int at_no;
	private int w_no;
	private String id;
}
