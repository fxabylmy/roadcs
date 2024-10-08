package com.jingjin.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingjin.common.result.PageResponse;
import com.jingjin.common.result.PageUtil;
import com.jingjin.model.userInteraction.dto.OpinionResponseDTO;
import com.jingjin.model.userInteraction.po.UserOpinion;
import com.jingjin.model.userInteraction.vo.BackUserOpinionVO;
import com.jingjin.model.userInteraction.vo.RespondedUserOpinionVO;
import com.jingjin.model.userInteraction.vo.UserOpinionVO;
import com.jingjin.serviceClient.service.user.UserFeignClient;
import com.jingjin.userservice.mapper.UserOpinionMapper;
import com.jingjin.userservice.service.UserOpinionService;
import com.jingjin.userservice.service.UserService;
import com.jingjin.userservice.util.converter.UserOpinionConverter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserOpinionServiceImpl extends ServiceImpl<UserOpinionMapper, UserOpinion> implements UserOpinionService {

    @Resource
    private UserOpinionMapper userOpinionMapper;

    @Resource
    private UserService userService;

    @Override
    public PageResponse<UserOpinionVO> getPageById(String userId, int pageIndex, int pageSize) {
        IPage<UserOpinion> pageParams = new Page<>(pageIndex, pageSize);
        LambdaQueryWrapper<UserOpinion> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserOpinion::getIsDelete,"0")
                .eq(UserOpinion::getUserId,userId);
        IPage<UserOpinion> resultPage =  page(pageParams,wrapper);
        if(pageParams.getTotal()==0)
            return PageUtil.isNull(new ArrayList<UserOpinionVO>());
        List<UserOpinion> userOpinionList = resultPage.getRecords();
        List<UserOpinionVO> userOpinionVOList = new ArrayList<>();
        userOpinionList.stream().forEach((UserOpinion userOpinion)->{
            UserOpinionVO userOpinionVO = UserOpinionConverter.INSTANCE.toUserOpinionVO(userOpinion);
            if (StringUtils.hasText(userOpinion.getResponseId())){
                String responseName = userService.getById(userOpinion.getResponseId()).getName();
                userOpinionVO.setResponseName(responseName);
            }
            userOpinionVOList.add(userOpinionVO);
        });
        PageResponse<UserOpinionVO> pageResponse = PageUtil.setPage(resultPage,userOpinionVOList);
        return pageResponse;
    }

    @Override
    public PageResponse<BackUserOpinionVO> getPageBack(int pageIndex, int pageSize) {
        IPage<UserOpinion> pageParams = new Page<>(pageIndex, pageSize);
        LambdaQueryWrapper<UserOpinion> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserOpinion::getIsDelete,"0");
        IPage<UserOpinion> resultPage =  page(pageParams,wrapper);
        if(pageParams.getTotal()==0)
            return PageUtil.isNull(new ArrayList<BackUserOpinionVO>());
        List<UserOpinion> userOpinionList = resultPage.getRecords();
        List<BackUserOpinionVO> backUserOpinionVOList = new ArrayList<>();
        userOpinionList.stream().forEach((UserOpinion userOpinion)->{
            BackUserOpinionVO backUserOpinionVO = UserOpinionConverter.INSTANCE.toBackUserOpinionVO(userOpinion);
            if (StringUtils.hasText(userOpinion.getResponseId())){
                String responseName = userService.getById(userOpinion.getResponseId()).getName();
                backUserOpinionVO.setResponseName(responseName);
            }
            String userName = userService.getById(userOpinion.getUserId()).getName();
            backUserOpinionVO.setUserName(userName);
            backUserOpinionVOList.add(backUserOpinionVO);
        });
        PageResponse<BackUserOpinionVO> pageResponse = PageUtil.setPage(resultPage,backUserOpinionVOList);
        return pageResponse;
    }

    @Override
    public PageResponse<RespondedUserOpinionVO> getPageResponded(int pageIndex, int pageSize) {
        IPage<UserOpinion> pageParams = new Page<>(pageIndex, pageSize);
        LambdaQueryWrapper<UserOpinion> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserOpinion::getIsDelete,"0")
                .eq(UserOpinion::getStatus,2);
        IPage<UserOpinion> resultPage =  page(pageParams,wrapper);
        if(pageParams.getTotal()==0)
            return PageUtil.isNull(new ArrayList<RespondedUserOpinionVO>());
        List<UserOpinion> userOpinionList = resultPage.getRecords();
        List<RespondedUserOpinionVO> respondedUserOpinionVOList = new ArrayList<>();
        userOpinionList.stream().forEach((UserOpinion userOpinion)->{
            RespondedUserOpinionVO respondedUserOpinionVO = UserOpinionConverter.INSTANCE.toRespondedUserOpinionVO(userOpinion);
            if (StringUtils.hasText(userOpinion.getResponseId())){
                String responseName = userService.getById(userOpinion.getResponseId()).getName();
                respondedUserOpinionVO.setResponseName(responseName);
            }
            String userName = userService.getById(userOpinion.getUserId()).getName();
            respondedUserOpinionVO.setUserName(userName);
            respondedUserOpinionVOList.add(respondedUserOpinionVO);
        });
        PageResponse<RespondedUserOpinionVO> pageResponse = PageUtil.setPage(resultPage,respondedUserOpinionVOList);
        return pageResponse;
    }


}
