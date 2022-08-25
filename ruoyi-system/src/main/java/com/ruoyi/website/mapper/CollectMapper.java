package com.ruoyi.website.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.website.domain.Collect;
import com.ruoyi.website.domain.vo.CollectVO;

/**
 * 收藏Mapper接口
 *
 * @author thh
 * @date 2021-10-05
 */
public interface CollectMapper extends BaseMapper<Collect> {
    /**
     * 查询收藏
     *
     * @param collectId 收藏主键
     * @return 收藏
     */
    public Collect selectCollectByCollectId(Long collectId);

    /**
     * 查询收藏列表
     *
     * @param collect 收藏
     * @return 收藏集合
     */
    public List<Collect> selectCollectList(Collect collect);


    /**
     * 批量删除收藏
     *
     * @param collectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCollectByCollectIds(Long[] collectIds);

    /**
     * 查询自己的收藏列表
     *
     * @param collect
     * @return
     */
    List<CollectVO> selectMyCollectList(Collect collect);
}
