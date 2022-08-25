package com.ruoyi.framework.web.service;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.service.ISysConfigService;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName SqlVersionService
 * @Description sql版本逻辑类
 * @Author thh
 * @Date 21:49 2021/11/13
 **/
@Component
public class SqlVersionService {

    @Autowired
    private ISysConfigService configService;

    private static String SQL_PATH = "updatesql/mediaSql/";

    private static String MEDIA_SQL_VERSION = "sql.version";

    private static String[] sqlArray = {"V1.001.sql", "V1.002.sql"};

    @Autowired
    private DataSource dataSource;

    private static final Logger logger = LoggerFactory.getLogger(SqlVersionService.class);

    // 系统启动只会执行一次
    @PostConstruct
    void init() {
        try {
            sqlFileSort(sqlArray);
            setVersion(MEDIA_SQL_VERSION, sqlArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * sql文件名称排序比较
     *
     * @param sqlArray
     */
    public void sqlFileSort(String[] sqlArray) {
        for (int i = 0; i < sqlArray.length; i++) {
            for (int j = i; j < sqlArray.length; j++) {
                if (sqlArray[i].compareTo(sqlArray[j]) > j) {
                    String temp = sqlArray[i];
                    sqlArray[i] = sqlArray[j];
                    sqlArray[j] = temp;
                }
            }
        }
    }

    /**
     * 更新sql脚本
     *
     * @param versionKey
     * @param sqlArray
     */
    public void setVersion(String versionKey, String[] sqlArray) {
        if (StringUtils.isEmpty(versionKey)) {
            return;
        }
        SysConfig dbSysConfig = configService.selectConfigParamByKey(versionKey);
        String currentVersion = "";
        if (dbSysConfig == null) {
            currentVersion = "V1.000";
            dbSysConfig = new SysConfig();
            dbSysConfig.setConfigKey(MEDIA_SQL_VERSION);
            dbSysConfig.setConfigName("sql版本号");
            dbSysConfig.setConfigType("Y");
            dbSysConfig.setRemark("sql版本号");
            dbSysConfig.setConfigValue(currentVersion);
            configService.insertConfig(dbSysConfig);
        } else {
            currentVersion = dbSysConfig.getConfigValue();
        }
        for (int i = 0; i < sqlArray.length; i++) {
            String fileVersion = sqlArray[i].substring(0, sqlArray[i].lastIndexOf("."));
            if (currentVersion.compareTo(fileVersion) < 0) {
                try {
                    logger.info("执行" + sqlArray[i]);
                    boolean flag = excuteSqlFile(sqlArray[i]);
                    if (dbSysConfig.getConfigId() == null) {
                        dbSysConfig = configService.selectConfigParamByKey(versionKey);
                    }
                    if (flag) {
                        dbSysConfig.setConfigValue(fileVersion);
                        configService.updateConfig(dbSysConfig);
                    } else {
                        logger.error("执行" + sqlArray[i] + "出错");
                        break;
                    }
                } catch (Exception e) {
                    logger.error("执行" + sqlArray[i] + "出错");
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    /**
     * 执行sql文件 (全文件读取)
     *
     * @param reader
     * @return
     * @throws SQLException
     */
    public void excuteSqlFile(Reader reader) {
        //获取一个连接
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ScriptRunner runner = new ScriptRunner(conn);
        runner.setThrowWarning(true);
        runner.setStopOnError(true);
        runner.runScript(reader);
    }

    public boolean excuteSqlFile(String fileName) {
        return excuteSqlFile(fileName, "UTF-8");
    }

    public boolean excuteSqlFile(String fileName, String charset) {
        boolean isSuccess = true;
        try (
                InputStream resourceAsStream = SqlVersionService.class.getClassLoader().getResourceAsStream(SQL_PATH + fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream, charset));
        ) {
            excuteSqlFile(reader);
        } catch (Exception e) {
            isSuccess = false;
            e.printStackTrace();
        }
        return isSuccess;
    }
}
