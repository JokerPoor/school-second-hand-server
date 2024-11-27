package com.coder.schoolsecondhandtradingplatform.service;

public interface WinnerSuccessService {
    /**
     * 买家提交竞拍价格
     *
     * @param bidDTO 竞拍信息
     * @return 是否竞拍成功
     */
    Boolean submitBid(BidDTO bidDTO);
}
