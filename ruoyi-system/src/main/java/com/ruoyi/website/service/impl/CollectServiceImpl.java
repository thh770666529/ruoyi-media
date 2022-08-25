package com.ruoyi.website.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.website.domain.vo.CheckCollectFlagVO;
import com.ruoyi.website.domain.vo.CollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.website.mapper.CollectMapper;
import com.ruoyi.website.domain.Collect;
import com.ruoyi.website.service.ICollectService;

/**
 * 收藏Service业务层处理
 *
 * @author thh
 * @date 2021-10-05
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements ICollectService {
    @Autowired
    private CollectMapper collectMapper;

    /**
     * 查询收藏
     *
     * @param collectId 收藏主键
     * @return 收藏
     */
    @Override
    public Collect selectCollectByCollectId(Long collectId) {
        return collectMapper.selectCollectByCollectId(collectId);
    }

    /**
     * 查询收藏列表
     *
     * @param collect 收藏
     * @return 收藏
     */
    @Override
    public List<Collect> selectCollectList(Collect collect) {
        return collectMapper.selectCollectList(collect);
    }

    /**
     * 新增收藏
     *
     * @param collect 收藏
     * @return 结果
     */
    @Override
    public int insertCollect(Collect collect) {
        return collectMapper.insert(collect);
    }

    /**
     * 修改收藏
     *
     * @param collect 收藏
     * @return 结果
     */
    @Override
    public int updateCollect(Collect collect) {
        return collectMapper.updateById(collect);
    }

    /**
     * 批量删除收藏
     *
     * @param collectIds 需要删除的收藏主键
     * @return 结果
     */
    @Override
    public int deleteCollectByCollectIds(Long[] collectIds) {
        return collectMapper.deleteCollectByCollectIds(collectIds);
    }

    /**
     * 删除收藏信息
     *
     * @param collectId 收藏主键
     * @return 结果
     */
    @Override
    public int deleteCollectByCollectId(Long collectId) {
        return collectMapper.deleteById(collectId);
    }

    @Override
    public Boolean checkCollectFlag(CheckCollectFlagVO checkCollectFlagVO) {
        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<Collect> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Collect::getTargetId, checkCollectFlagVO.getTargetId())
                .eq(Collect::getUserId, userId)
                .eq(Collect::getTableName, checkCollectFlagVO.getTableName());
        List<Collect> list = this.list(lambdaQueryWrapper);
        if (list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<CollectVO> selectMyCollectList(Collect collect) {
        return collectMapper.selectMyCollectList(collect);
    }
}
