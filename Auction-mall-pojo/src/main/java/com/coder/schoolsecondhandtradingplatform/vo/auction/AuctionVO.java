package com.coder.schoolsecondhandtradingplatform.vo.auction;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuctionVO {
    private Long auctionId;
    private String title;
    private LocalDateTime endTime;
    private LocalDateTime collectionTime;
    private String paymentStatus;
}