package com.coder.schoolsecondhandtradingplatform.dto.auction;

import lombok.Data;


@Data
public class AuctionQueryDTO {
    private Integer pageIndex = 1;  // 页码
    private Integer pageSize = 10;  // 每页数量
    private String title;         // 标题
    private String status;        // 状态
}
