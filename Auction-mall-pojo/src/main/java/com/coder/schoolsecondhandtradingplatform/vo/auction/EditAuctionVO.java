package com.coder.schoolsecondhandtradingplatform.vo.auction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditAuctionVO {
    private Long auctionId; // 拍卖ID

    private String title; // 教材标题

    private String description; // 教材描述

    private Double startPrice; // 起拍价格

    private Double currentPrice;  // 当前价格，竞拍过程中当前的最高价格

    private String endTime;  // 拍卖结束时间，记录拍卖何时结束

    private String auction_image; // 展示图片链接url
}
