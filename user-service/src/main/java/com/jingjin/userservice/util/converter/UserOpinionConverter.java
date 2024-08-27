package com.jingjin.userservice.util.converter;

import com.jingjin.model.userInteraction.po.UserOpinion;
import com.jingjin.model.userInteraction.vo.BackUserOpinionVO;
import com.jingjin.model.userInteraction.vo.RespondedUserOpinionVO;
import com.jingjin.model.userInteraction.vo.UserOpinionVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserOpinionConverter {

    UserOpinionConverter INSTANCE = Mappers.getMapper(UserOpinionConverter.class);

    List<UserOpinionVO> toUserOpinionVOList(List<UserOpinion> list);

    UserOpinionVO toUserOpinionVO(UserOpinion userOpinion);

    BackUserOpinionVO toBackUserOpinionVO(UserOpinion userOpinion);

    RespondedUserOpinionVO toRespondedUserOpinionVO(UserOpinion userOpinion);
}
