package com.techelevator.parks.dao;

import java.util.List;

import com.techelevator.parks.model.FavoritePark;

public interface FavoriteParkDao {

	List<FavoritePark> list(String username);
	
	void add(FavoritePark park, String username);
	
}
