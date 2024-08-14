package com.jingjin.userwebsiteservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingjin.common.result.PageResponse;
import com.jingjin.common.result.PageUtil;
import com.jingjin.model.userInteraction.po.UserMemo;
import com.jingjin.model.userInteraction.vo.UserMemoVO;
import com.jingjin.userwebsiteservice.mapper.UserMemoMapper;
import com.jingjin.userwebsiteservice.service.UserMemoService;
import com.jingjin.userwebsiteservice.util.UserMemoConverter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserMemoServiceImpl extends ServiceImpl<UserMemoMapper, UserMemo> implements UserMemoService {

    @Resource
    private UserMemoMapper userMemoMapper;

    @Override
    public PageResponse<UserMemoVO> getPageById(int pageIndex, int pageSize, String userId) {
        IPage<UserMemo> pageParams = new Page<>(pageIndex, pageSize);
        LambdaQueryWrapper<UserMemo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserMemo::getIsDelete,"0")
               .eq(UserMemo::getUserId,userId);
        IPage<UserMemo> resultPage =  page(pageParams,wrapper);
        if(pageParams.getTotal()==0)
            return PageUtil.isNull(new ArrayList<UserMemoVO>());
        List<UserMemo> userMemoList = resultPage.getRecords();
        List<UserMemoVO> userMemoVOList = UserMemoConverter.INSTANCE.toUserMemoVOList(userMemoList);
        PageResponse<UserMemoVO> pageResponse = PageUtil.setPage(resultPage,userMemoVOList);
        return pageResponse;
    }
}
