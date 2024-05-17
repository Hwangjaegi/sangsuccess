package com.green.sang.service;

import java.util.List;

import com.green.sang.dto.Staff;

public interface StaffService {

	List<Staff> staffList(int startRow, int endRow);

	int getTotal();

	Staff selectStaff(int st_no);

}
