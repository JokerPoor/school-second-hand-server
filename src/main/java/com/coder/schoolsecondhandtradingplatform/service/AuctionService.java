package com.coder.schoolsecondhandtradingplatform.service;

import com.coder.schoolsecondhandtradingplatform.pojo.Auction;
import com.coder.schoolsecondhandtradingplatform.utils.PageBean;
import com.coder.schoolsecondhandtradingplatform.vo.auction.AddAuctionVO;
import com.coder.schoolsecondhandtradingplatform.vo.auction.EditAuctionVO;
import org.springframework.stereotype.Service;

@Service
public interface AuctionService {
    void addAuction(AddAuctionVO addAuctionVO);

    PageBean page(Integer pageNo, Integer pageSize);

    Auction getAuctionById(Long auctionId);

    void editAuction(EditAuctionVO editAuctionVO);
}
