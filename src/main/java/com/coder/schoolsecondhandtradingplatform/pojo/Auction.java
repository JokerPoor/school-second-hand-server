package com.coder.schoolsecondhandtradingplatform.pojo;

import com.coder.schoolsecondhandtradingplatform.contants.AuctionStatus;
import com.coder.schoolsecondhandtradingplatform.contants.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auction {
    private Long auctionId; // 拍卖表ID

    private Long sellerId; // 卖家ID

    private String title; // 教材标题

    private String description; // 教材描述

    private Double startPrice; // 起拍价格

    private Double currentPrice;  // 当前价格，竞拍过程中当前的最高价格

    private String startTime;  // 拍卖开始时间，记录拍卖何时开始

    private String endTime;  // 拍卖结束时间，记录拍卖何时结束

    private AuctionStatus status;  // 拍卖状态：待开始、进行中、已售、已取消

    private PaymentStatus paymentStatus; // 支付状态：待支付、已支付、未支付

    private String auction_image; // 展示图片链接url

}
