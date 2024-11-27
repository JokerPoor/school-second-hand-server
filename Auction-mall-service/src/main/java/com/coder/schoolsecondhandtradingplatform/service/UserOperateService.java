package com.coder.schoolsecondhandtradingplatform.service;

import com.coder.schoolsecondhandtradingplatform.vo.collection.CollectionAuctionVO;

public interface UserOperateService {
    /**
     * 获取用户收藏的商品列表
     *
     * @param queryDTO 查询条件
     * @return 分页数据
     */
    PageVO<CollectionAuctionVO> getUserCollections(AuctionQueryDTO auctionQueryDTO);
    /**
     * 获取卖家发布的商品列表
     *
     * @param queryDTO 查询条件
     * @return 分页数据
     */
    PageVO<SellerAuctionVO> getSellerAuctions(AuctionQueryDTO auctionQueryDTO);

    /**
     * 获取买家竞拍成功的商品列表
     *
     * @param queryDTO 查询条件
     * @return 分页数据
     */
    PageVO<WinnerAuctionVO> getWinnerAuctions(AuctionQueryDTO auctionQueryDTO);
}
