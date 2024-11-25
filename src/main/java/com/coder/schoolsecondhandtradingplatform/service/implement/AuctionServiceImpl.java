package com.coder.schoolsecondhandtradingplatform.service.implement;

import com.coder.schoolsecondhandtradingplatform.mapper.AuctionMapper;
import com.coder.schoolsecondhandtradingplatform.pojo.Auction;
import com.coder.schoolsecondhandtradingplatform.service.AuctionService;
import com.coder.schoolsecondhandtradingplatform.utils.PageBean;
import com.coder.schoolsecondhandtradingplatform.vo.auction.AddAuctionVO;
import com.coder.schoolsecondhandtradingplatform.vo.auction.EditAuctionVO;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import java.util.List;


@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionMapper auctionMapper;

    @Override
    public void addAuction(AddAuctionVO addAuctionVO) {
        auctionMapper.addAuction(addAuctionVO);
    }

    @Override
    public PageBean page(Integer pageNo, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNo, pageSize);
        // 执行分页查询
        List<Auction> auctionList = auctionMapper.list();
        // 获取分页结果
        Page<Auction> p = (Page<Auction>) auctionList;
        //封装PageBean
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public Auction getAuctionById(Long auctionId) {
        return auctionMapper.getAuctionById(auctionId);
    }

    @Override
    public void editAuction(EditAuctionVO editAuctionVO) {
        auctionMapper.editAuction(editAuctionVO);
    }
}
