package com.green.sang.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.green.sang.dto.Staff;

@Mapper
public interface StaffMapper {

	List<Staff> staffList(Map<String, Object> map);
	
	int geTotal();

	Staff selectStaff(int st_no);

}
