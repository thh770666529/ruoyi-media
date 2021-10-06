package com.ruoyi.website.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.website.domain.Collect;
import com.ruoyi.website.domain.vo.CheckCollectFlagVO;
import com.ruoyi.website.domain.vo.CollectVO;

/**
 * 收藏Service接口
 *
 * @author thh
 * @date 2021-10-05
 */
public interface ICollectService  extends IService<Collect>
{
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
     * 新增收藏
     *
     * @param collect 收藏
     * @return 结果
     */
    public int insertCollect(Collect collect);

    /**
     * 修改收藏
     *
     * @param collect 收藏
     * @return 结果
     */
    public int updateCollect(Collect collect);

    /**
     * 批量删除收藏
     *
     * @param collectIds 需要删除的收藏主键集合
     * @return 结果
     */
    public int deleteCollectByCollectIds(Long[] collectIds);

    /**
     * 删除收藏信息
     *
     * @param collectId 收藏主键
     * @return 结果
     */
    public int deleteCollectByCollectId(Long collectId);


    /**
     * 检查收藏标志
     */
    public Boolean checkCollectFlag(CheckCollectFlagVO checkCollectFlagVO);

    /**
     * 查询自己的收藏列表
     * @param collect
     * @return
     */
    List<CollectVO> selectMyCollectList(Collect collect);
}
