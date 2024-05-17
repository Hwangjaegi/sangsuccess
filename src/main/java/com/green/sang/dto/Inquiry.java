package com.green.sang.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("Inquiry")
public class Inquiry {
	private int in_no;
	private String in_title;
	private String in_reply;
}
