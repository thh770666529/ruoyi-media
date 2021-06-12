package com.ruoyi.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.file.domain.Share;
import com.ruoyi.file.vo.ShareListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShareMapper  extends BaseMapper<Share> {

    List<ShareListVO> selectShareList(@Param("shareFilePath") String shareFilePath,@Param("shareBatchNum") String shareBatchNum,@Param("beginCount") Long beginCount,@Param("pageCount") Long pageCount,@Param("userId") Long userId);
    int selectShareListTotalCount(@Param("shareFilePath") String shareFilePath,@Param("shareBatchNum") String shareBatchNum,@Param("userId") Long userId);
}
