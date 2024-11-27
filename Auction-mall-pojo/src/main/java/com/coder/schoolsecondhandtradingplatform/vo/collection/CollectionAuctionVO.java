package com.coder.schoolsecondhandtradingplatform.vo.collection;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CollectionAuctionVO {
    private Long auctionId;// 收藏的商品ID
    private Long userId;   // 用户ID
    private LocalDateTime createTime;   // 收藏时间
}