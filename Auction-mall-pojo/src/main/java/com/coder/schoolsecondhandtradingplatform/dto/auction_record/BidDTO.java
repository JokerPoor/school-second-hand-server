package com.coder.schoolsecondhandtradingplatform.dto.auction_record;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

// 竞拍DTO

@Data
public class BidDTO {
    @NotNull(message = "拍卖ID不能为空")
    private Long auctionId;

    @NotNull(message = "竞拍价格不能为空")
    @DecimalMin(value = "0.01", message = "竞拍价格必须大于0")
    private BigDecimal currentPrice;
}

