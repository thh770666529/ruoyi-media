package com.ruoyi.framework.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

import static com.ruoyi.common.utils.SecurityUtils.getAuthentication;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        LoginUser loginUser = this.getLoginUser();
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createTime", () -> DateUtils.getNowDate(), Date.class);
        this.strictInsertFill(metaObject, "updateTime", () -> DateUtils.getNowDate(), Date.class);
        if (loginUser != null) {
            this.strictInsertFill(metaObject, "createBy", () -> loginUser.getUserId() + "", String.class);
            this.strictInsertFill(metaObject, "updateBy", () -> loginUser.getUserId() + "", String.class);
        }
        log.info("end insert fill ....");
    }

    private LoginUser getLoginUser() {
        LoginUser loginUser = null;
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            try {
                loginUser = (LoginUser) authentication.getPrincipal();
            } catch (Exception e) {

            }
        }
        return loginUser;
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        LoginUser loginUser = this.getLoginUser();
        this.strictUpdateFill(metaObject, "updateTime", () -> DateUtils.getNowDate(), Date.class);
        if (loginUser != null) {
            this.strictUpdateFill(metaObject, "updateBy", () -> loginUser.getUserId() + "", String.class);
        }
        log.info("end update fill ....");
    }
}
