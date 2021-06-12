package com.ruoyi.file.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.file.domain.FileBean;

import java.util.List;

public interface FileMapper extends BaseMapper<FileBean> {


    void batchInsertFile(List<FileBean> fileBeanList);
//    void updateFile(FileBean fileBean);






}
