package com.coder.schoolsecondhandtradingplatform.dto.auction;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EditAuctionDTO {
    @NotNull(message = "教材ID不能为空")
    private Long auctionId;
    
    @NotBlank(message = "教材标题不能为空")
    private String title; // 教材标题

    @NotBlank(message = "教材描述不能为空")
    private String description; // 教材描述

    @NotNull(message = "起拍价格不能为空")
    @DecimalMin(value = "0.01", message = "起拍价格必须大于0")
    private Double startPrice; // 起拍价格

    @NotNull(message = "拍卖结束时间不能为空")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$", message = "拍卖结束时间日期格式不正确，应为 yyyy-MM-dd HH:mm:ss")
    private String endTime;  // 拍卖结束时间，记录拍卖何时结束

    private String auction_image; // 展示图片链接url
}
