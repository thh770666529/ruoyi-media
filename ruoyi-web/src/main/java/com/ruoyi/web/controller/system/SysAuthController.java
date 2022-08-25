package com.ruoyi.web.controller.system;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ruoyi.common.config.AuthClientConfig;
import com.ruoyi.common.config.JustAuthConfig;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.*;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.SysAuthUser;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import me.zhyd.oauth.cache.AuthDefaultStateCache;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;

/**
 * 第三方认证授权处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/auth")
public class SysAuthController extends BaseController {
    private AuthStateCache authStateCache;

    @Autowired
    private SysUserMapper userMapper;

    {
        authStateCache = AuthDefaultStateCache.INSTANCE;
    }

    @Autowired
    JustAuthConfig justAuthConfig;

    /**
     * 认证授权
     *
     * @param source
     * @throws IOException
     */
    @GetMapping("/render/{source}")
    @ResponseBody
    public AjaxResult renderAuth(@PathVariable("source") String source) throws IOException {
        AuthClientConfig config = justAuthConfig.getConfig(source);
        if (config == null) {
            return error("没有" + source + "的授权client信息！");
        }
        AuthRequest authRequest = AuthUtils.getAuthRequest(source, config.getClientId(), config.getClientSecret(), justAuthConfig.getRedirectUri() + source, authStateCache);
        String authorizeUrl = authRequest.authorize(AuthStateUtils.createState());
        Map data = new HashMap();
        data.put("authorizeUrl", authorizeUrl);
        return AjaxResult.success(data);
    }


    @Autowired
    SysLoginService sysLoginService;

    @Autowired
    TokenService tokenService;

    @Autowired
    ISysUserService userService;

    /**
     * 回调结果
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/callback/{source}")
    @ResponseBody
    public void callbackAuth(@PathVariable("source") String source, AuthCallback callback, HttpServletRequest request) throws IOException {
        AuthClientConfig config = justAuthConfig.getConfig(source);
        if (config == null) {
            ServletUtils.getResponse().sendRedirect(justAuthConfig.getWebsiteUrl() + 500);
        }
        AuthRequest authRequest = AuthUtils.getAuthRequest(source, config.getClientId(), config.getClientSecret(), justAuthConfig.getRedirectUri() + source, authStateCache);
        AuthResponse<AuthUser> response = authRequest.login(callback);
        if (response.ok()) {
            SysUser user = userMapper.selectAuthUserByUuid(source + response.getData().getUuid());
            if (StringUtils.isNotNull(user)) {
                String token = sysLoginService.login(user);
                ServletUtils.getResponse().sendRedirect(justAuthConfig.getWebsiteUrl() + "?thirdToken=" + token);
            } else {
                SysUser sysUser = new SysUser();
                sysUser.setUserName(response.getData().getUsername());
                sysUser.setNickName(response.getData().getNickname());
                sysUser.setPassword(SecurityUtils.encryptPassword(justAuthConfig.getDefaultPwd()));
                String avatar = FileUploadUtils.uploadByUrl(RuoYiConfig.getAvatarPath(), response.getData().getAvatar());
                sysUser.setAvatar(avatar);
                boolean regFlag = userService.registerUser(sysUser);
                if (!regFlag) {
                    //return error("请求远程授权失败！");
                }
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(sysUser.getUserName(), Constants.REGISTER,
                        MessageUtils.message("user.register.success")));
                String token = sysLoginService.login(sysUser);
                // 若已经登录则直接绑定系统账号
                SysAuthUser authUser = new SysAuthUser();
                authUser.setAvatar(avatar);
                authUser.setUuid(source + response.getData().getUuid());
                authUser.setUserId(sysUser.getUserId());
                authUser.setUserName(response.getData().getNickname());
                authUser.setLoginName(response.getData().getUsername());
                authUser.setEmail(response.getData().getEmail());
                authUser.setSource(source);
                userMapper.insertAuthUser(authUser);
                ServletUtils.getResponse().sendRedirect(justAuthConfig.getWebsiteUrl() + "?thirdToken=" + token);
            }
        } else {
            ServletUtils.getResponse().sendRedirect(justAuthConfig.getWebsiteUrl() + 500);
        }
    }

    /**
     * 检查是否授权
     */
    @PostMapping("/checkAuthUser")
    @ResponseBody
    public AjaxResult checkAuthUser(SysAuthUser authUser) {
        Long userId = SecurityUtils.getUserId();
        String source = authUser.getSource();
        if (userMapper.checkAuthUser(userId, source) > 0) {
            return error(source + "平台账号已经绑定");
        }
        return AjaxResult.success();
    }

    /**
     * 取消授权
     */
    @PostMapping("/unlock")
    @ResponseBody
    public AjaxResult unlockAuth(SysAuthUser authUser) {
        return toAjax(userMapper.deleteAuthUser(authUser.getAuthId()));
    }

}
