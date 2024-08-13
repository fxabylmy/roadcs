package com.jingjin.thirdpartywebsiteservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingjin.common.exception.BusinessException;
import com.jingjin.common.exception.ThrowUtils;
import com.jingjin.common.result.ErrorCode;
import com.jingjin.model.thirdPartyWebsite.dto.AddThirdPartyWebsiteDTO;
import com.jingjin.model.thirdPartyWebsite.dto.UpdateThirdPartyWebsiteDTO;
import com.jingjin.model.thirdPartyWebsite.vo.ThirdPartyWebsiteDetailVO;
import com.jingjin.model.thirdPartyWebsite.vo.ThirdPartyWebsiteSimpleVO;
import com.jingjin.thirdpartywebsiteservice.util.UploadUtil;
import com.jingjin.thirdpartywebsiteservice.util.converter.ThirdPartyWebsiteConverter;
import com.jingjin.thirdpartywebsiteservice.mapper.ThirdPartyWebsiteMapper;
import com.jingjin.thirdpartywebsiteservice.service.ThirdPartyWebsiteService;
import com.jingjin.model.thirdPartyWebsite.po.ThirdPartyWebsite;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static com.jingjin.thirdpartywebsiteservice.util.urlUtil.UrlUtil.urlToBase64;

/**
 * 第三方网站服务实施
 *
 * @author fxab
 * @date 2024/08/13
 */
@Service
@Slf4j
public class ThirdPartyWebsiteServiceImpl extends ServiceImpl<ThirdPartyWebsiteMapper, ThirdPartyWebsite> implements ThirdPartyWebsiteService {

    /**
     * 第三方网站映射器
     */
    @Resource
    private ThirdPartyWebsiteMapper thirdPartyWebsiteMapper;

    /**
     * 上传util
     */
    @Resource
    private UploadUtil uploadUtil;


    /**
     * 获取全部
     *
     * @return {@link List}<{@link ThirdPartyWebsiteSimpleVO}>
     */
    @Override
    public List<ThirdPartyWebsiteSimpleVO> getAll() {
        LambdaQueryWrapper<ThirdPartyWebsite> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ThirdPartyWebsite::getStatus,"1")
               .eq(ThirdPartyWebsite::getIsDelete,"0");
        List<ThirdPartyWebsite> list =list(wrapper);
        List<ThirdPartyWebsiteSimpleVO> websiteSimpleVOList = new ArrayList<>();
        list.stream().forEach((ThirdPartyWebsite thirdPartyWebsite)->{
            String base64Image = urlToBase64(thirdPartyWebsite.getLogoUrl());
            ThirdPartyWebsiteSimpleVO websiteSimpleVO =
                    ThirdPartyWebsiteConverter.INSTANCE.toWebsiteSimpleVO(thirdPartyWebsite);
            websiteSimpleVO.setLogoBase64(base64Image);
            websiteSimpleVOList.add(websiteSimpleVO);
        });
        return websiteSimpleVOList;
    }

    /**
     * 按id获取详细信息
     *
     * @param ThirdPartyWebsiteId 管理员网站id
     * @return {@link ThirdPartyWebsiteDetailVO}
     */
    @Override
    public ThirdPartyWebsiteDetailVO getDetailById(Integer ThirdPartyWebsiteId) {
        ThirdPartyWebsite thirdPartyWebsite = getById(ThirdPartyWebsiteId);
        String base64Image = urlToBase64(thirdPartyWebsite.getLogoUrl());
        ThirdPartyWebsiteDetailVO websiteDetailVO =
                ThirdPartyWebsiteConverter.INSTANCE.toWebsiteDetailVO(thirdPartyWebsite);
        websiteDetailVO.setLogoBase64(base64Image);
        return websiteDetailVO;
    }

    /**
     * 添加管理员网站
     *
     * @param addThirdPartyWebsiteDTO 添加第三方网站dto
     * @return {@link Boolean}
     */
    @Override
    public Boolean addThirdPartyWebsite(AddThirdPartyWebsiteDTO addThirdPartyWebsiteDTO) {
        String logoUrl = uploadUtil.uploadImg(addThirdPartyWebsiteDTO.getLogo());
        ThirdPartyWebsite thirdPartyWebsite = ThirdPartyWebsite.builder()
                .name(addThirdPartyWebsiteDTO.getName())
                .logoUrl(logoUrl)
                .websiteUrl(addThirdPartyWebsiteDTO.getWebsiteUrl())
                .type(addThirdPartyWebsiteDTO.getType())
                .category(addThirdPartyWebsiteDTO.getCategory())
                .importanceLevel(addThirdPartyWebsiteDTO.getImportanceLevel())
                .subtitle(addThirdPartyWebsiteDTO.getSubtitle())
                .recommendation(addThirdPartyWebsiteDTO.getRecommendation())
                .build();
        Boolean isSuccess = save(thirdPartyWebsite);
        return isSuccess;
    }

    /**
     * 更新管理员网站
     *
     * @param updateThirdPartyWebsiteDTO 更新第三方网站dto
     * @return {@link Boolean}
     */
    @Override
    public Boolean updateThirdPartyWebsite(UpdateThirdPartyWebsiteDTO updateThirdPartyWebsiteDTO) {
        String logoUrl = uploadUtil.uploadImg(updateThirdPartyWebsiteDTO.getLogo());
        ThirdPartyWebsite thirdPartyWebsite = ThirdPartyWebsite.builder()
                .id(updateThirdPartyWebsiteDTO.getId())
                .name(updateThirdPartyWebsiteDTO.getName())
                .logoUrl(logoUrl)
                .websiteUrl(updateThirdPartyWebsiteDTO.getWebsiteUrl())
                .type(updateThirdPartyWebsiteDTO.getType())
                .category(updateThirdPartyWebsiteDTO.getCategory())
                .importanceLevel(updateThirdPartyWebsiteDTO.getImportanceLevel())
                .subtitle(updateThirdPartyWebsiteDTO.getSubtitle())
                .recommendation(updateThirdPartyWebsiteDTO.getRecommendation())
                .status(updateThirdPartyWebsiteDTO.getStatus())
                .build();
        Boolean isSuccess = updateById(thirdPartyWebsite);
        return isSuccess;
    }

    /**
     * 通过ids获取
     *
     * @param ids 身份证
     * @return {@link List}<{@link ThirdPartyWebsiteSimpleVO}>
     */
    @Override
    public List<ThirdPartyWebsiteSimpleVO> getByIds(List<Integer> ids) {
        List<ThirdPartyWebsite> list = listByIds(ids);
        List<ThirdPartyWebsiteSimpleVO> websiteSimpleVOList = new ArrayList<>();
        list.stream().forEach((ThirdPartyWebsite thirdPartyWebsite)->{
            String base64Image = urlToBase64(thirdPartyWebsite.getLogoUrl());
            ThirdPartyWebsiteSimpleVO websiteSimpleVO =
                    ThirdPartyWebsiteConverter.INSTANCE.toWebsiteSimpleVO(thirdPartyWebsite);
            websiteSimpleVO.setLogoBase64(base64Image);
            websiteSimpleVOList.add(websiteSimpleVO);
        });
        return websiteSimpleVOList;
    }
}
