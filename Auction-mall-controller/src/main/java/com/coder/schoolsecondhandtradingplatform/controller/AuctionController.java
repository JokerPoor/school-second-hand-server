package com.coder.schoolsecondhandtradingplatform.controller;

import com.coder.schoolsecondhandtradingplatform.contants.AuctionStatus;
import com.coder.schoolsecondhandtradingplatform.contants.TipMessage;
import com.coder.schoolsecondhandtradingplatform.context.BaseContext;
import com.coder.schoolsecondhandtradingplatform.dto.auction.AddAuctionDTO;
import com.coder.schoolsecondhandtradingplatform.dto.auction.EditAuctionDTO;
import com.coder.schoolsecondhandtradingplatform.entity.Auction;
import com.coder.schoolsecondhandtradingplatform.utils.PageBean;
import com.coder.schoolsecondhandtradingplatform.utils.Result;
import com.coder.schoolsecondhandtradingplatform.utils.ValidationUtils;
import com.coder.schoolsecondhandtradingplatform.service.AuctionService;
import com.coder.schoolsecondhandtradingplatform.vo.auction.AddAuctionVO;
import com.coder.schoolsecondhandtradingplatform.vo.auction.EditAuctionVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Slf4j
@RestController
@Tag(name = "AuctionController", description = "教材拍卖相关操作")    // 标签描述
@RequestMapping("/api/v1")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @PostMapping("/auction")
    @Operation(summary = "新增拍卖教材", description = "新增拍卖教材")
    public Result addAuction(@RequestBody @Valid AddAuctionDTO  addAuctionDTO, BindingResult bindingResult){
        String validationError = ValidationUtils.handleValidationErrors(bindingResult);
        if (validationError != null) {
            // 校验失败
            return Result.error(validationError);
        }

        AddAuctionVO addAuctionVO = new AddAuctionVO();
        BeanUtils.copyProperties(addAuctionDTO,addAuctionVO);
        addAuctionVO.setCurrentPrice(addAuctionVO.getStartPrice());
        /*
        *  从token中取出user_id
        * */
        log.info("当前登录id {}",BaseContext.getCurrentId());
        addAuctionVO.setSellerId(BaseContext.getCurrentId());
        auctionService.addAuction(addAuctionVO);
        /*
        * 判断是否新增成功
        * */
        if(addAuctionVO.getAuctionId() != null){
            return Result.success();
        }else {
            return Result.error(TipMessage.Insert_Failed.getDescription());
        }
    }

    @GetMapping("/auction")
    @Operation(summary = "参与拍卖过的教材分页接口", description = "不管拍卖的教材状态所有数据做分页接口，保留最全的查询条件")
    public Result page(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize){

        PageBean pageBean = auctionService.page(pageNo, pageSize);

        return Result.success(pageBean);
    }

    @GetMapping("/auction/{auctionId}")
    @Operation(summary = "通过id获取拍卖的详细信息", description = "获取拍卖教程的详细信息")
    public Result getAuctionById(@PathVariable("auctionId") @NotNull(message = "auctionId不能为空") Long auctionId){
        Auction auction = auctionService.getAuctionById(auctionId);
        return Result.success(auction);
    }

    @PutMapping("/auction")
    @Operation(summary = "编辑拍卖教材", description = "编辑拍卖教材")
    public Result editAuction(@RequestBody @Valid EditAuctionDTO editAuctionDTO, BindingResult bindingResult){
        String validationError = ValidationUtils.handleValidationErrors(bindingResult);
        if (validationError != null) {
            // 校验失败
            return Result.error(validationError);
        }

        Auction oldAuction =  auctionService.getAuctionById(editAuctionDTO.getAuctionId());

        /*
        * 状态已开拍或者开个拍，则不允许在修改正在拍卖的教程信息
        * */
        if(oldAuction.getStatus() != AuctionStatus.PENDING){
            return Result.error(TipMessage.Auction_Not_PENDING.getDescription());
        }

        EditAuctionVO editAuctionVO = new EditAuctionVO();
        BeanUtils.copyProperties(editAuctionDTO,editAuctionVO);
        editAuctionVO.setCurrentPrice(editAuctionVO.getStartPrice());
        auctionService.editAuction(editAuctionVO);
        return Result.success();
    }
}
