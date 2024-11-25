package com.coder.schoolsecondhandtradingplatform.mapper;

import com.coder.schoolsecondhandtradingplatform.pojo.Auction;
import com.coder.schoolsecondhandtradingplatform.vo.auction.AddAuctionVO;
import com.coder.schoolsecondhandtradingplatform.vo.auction.EditAuctionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuctionMapper {
    void addAuction(AddAuctionVO addAuctionVO);

    List<Auction> list();

    Auction getAuctionById(Long auctionId);

    void editAuction(EditAuctionVO editAuctionVO);
}
