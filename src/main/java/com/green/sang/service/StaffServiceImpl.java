package com.green.sang.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.sang.dto.Staff;
import com.green.sang.mapper.StaffMapper;

@Service
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffMapper sm;

	@Override
	public List<Staff> staffList(int startRow, int endRow) {
		System.out.println("왔니? : " + startRow + ", " + endRow );
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sm.staffList(map);
	}

	@Override
	public int getTotal() {
		
		return sm.geTotal();
	}

	@Override
	public Staff selectStaff(int st_no) {
		
		return sm.selectStaff(st_no);
	}
	
	
	
}
