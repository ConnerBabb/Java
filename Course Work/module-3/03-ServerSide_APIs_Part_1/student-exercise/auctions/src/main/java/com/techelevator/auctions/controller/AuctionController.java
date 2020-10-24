package com.techelevator.auctions.controller;

import com.techelevator.auctions.DAO.AuctionDAO;
import com.techelevator.auctions.DAO.MemoryAuctionDAO;
import com.techelevator.auctions.model.Auction;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

	private AuctionDAO dao;

	public AuctionController() {
		this.dao = new MemoryAuctionDAO();
	}

//	@RequestMapping(method = RequestMethod.GET)
//	public List<Auction> list(@RequestParam(required = false) String title_like) {
//		List<Auction> filteredAuctions = new ArrayList<Auction>();
//		if (title_like.isEmpty()) {
//			List<Auction> auctions = dao.list();
//			return auctions;
//		} else {
//			filteredAuctions = dao.searchByTitle(title_like);
//			return filteredAuctions;
//		}
//	}

	// @RequestMapping(path="/filter",method=RequestMethod.GET)
	// public List<Auction> list(String title_like){
	// List<Auction> filteredAuctions = new ArrayList<Auction>();
	// List<Auction> auctions = dao.list();
	// if(title_like != null) {
	// filteredAuctions= dao.searchByTitle(title_like);
	// } else {
	// filteredAuctions = auctions;
	// }
	// return filteredAuctions;
	// }
    @RequestMapping( method=RequestMethod.GET)
    public List<Auction> listAuctions(@RequestParam(defaultValue="") String title_like, @RequestParam(defaultValue = "0.0") Double currentBid_lte) {
    if (!title_like.isEmpty() && currentBid_lte>0)	{
    return dao.searchByTitleAndPrice(title_like, currentBid_lte);
    } else if (!title_like.isEmpty()) {
    return dao.searchByTitle(title_like);
    } else if (currentBid_lte>0) {
    	return dao.searchByPrice(currentBid_lte);
    } else
    return dao.list();	
    }
//	 @RequestMapping(method=RequestMethod.GET)
//	 public List<Auction> list(){
//	 List<Auction> auctions = dao.list();
//	 return auctions;
//	 }
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Auction get(@PathVariable int id) {
		Auction auction = dao.get(id);
		return auction;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Auction create(@RequestBody Auction auction) {
		Auction auction1 = dao.create(auction);
		return auction1;
	}
}
