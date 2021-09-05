package com.ruoyi.framework.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

import static com.ruoyi.common.utils.SecurityUtils.getAuthentication;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        LoginUser loginUser = this.getLoginUser();
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createTime", () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class);
        if(loginUser != null){
            this.strictInsertFill(metaObject, "createBy", () -> loginUser.getUsername(), String.class);
            this.strictInsertFill(metaObject, "updateBy", () -> loginUser.getUsername(), String.class);
        }
        log.info("end insert fill ....");
    }

    private LoginUser getLoginUser() {
        LoginUser loginUser = null;
        Authentication authentication = getAuthentication();
        if (authentication != null){
            try
            {
                loginUser = (LoginUser) authentication.getPrincipal();
            }
            catch (Exception e)
            {

            }
        }
        return loginUser;
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        LoginUser loginUser = this.getLoginUser();
        this.strictUpdateFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class);
        if(loginUser != null){
            this.strictInsertFill(metaObject, "updateBy", () -> loginUser.getUsername(), String.class);
        }
        log.info("end update fill ....");
    }
}
