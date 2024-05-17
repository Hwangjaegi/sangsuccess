package com.green.sang.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.green.sang.dto.Academy;
import com.green.sang.dto.Cart;
@Mapper
public interface CartMapper {
	
	Cart select(Cart cart);
	int insert(Cart cart);
	int update(Cart cart2);

	List<Cart> cart_list(String id);
	Academy selectCartAcademy(int a_no);
	int deleteCart(Map<String, Object> map);
	int updateCountUp(int a_no);
	int updateCountDown(int a_no);
	List<Cart> selectCartID(String id);

}
