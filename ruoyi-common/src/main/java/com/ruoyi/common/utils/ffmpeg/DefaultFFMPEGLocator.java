package com.ruoyi.common.utils.ffmpeg;

import cn.hutool.core.util.ZipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;

/**
 * 默认的 ffmpeg 可执行文件定位器，它在磁盘上导出与库分发捆绑的 ffmpeg 可执行文件。
 * 它应该适用于 windows 和许多 linux 发行版。如果没有，请尝试编译您自己的 ffmpeg 可执行文件并使用自定义
 *
 * @author thh
 */
public class DefaultFFMPEGLocator extends FFMPEGLocator {

    private static final Logger log = LoggerFactory.getLogger(DefaultFFMPEGLocator.class);

    /**
     * 版本
     */
    private static final int myExeVersion = 1;

    /**
     * ffmpeg执行地址
     * 例如：用户的临时目录是 ： C:\Users\用户名\AppData\Local\Temp  ， 那么文件将导入到 C:\Users\tanhh\AppData\Local\Temp\ruoyi-media1\ffmpeg下
     */
    private String path;


    public static void main(String[] args) {
        DefaultFFMPEGLocator defaultFFMPEGLocator = new DefaultFFMPEGLocator();
        String ffmpegExecutablePath = defaultFFMPEGLocator.getFFMPEGExecutablePath();
        System.out.println("ffmpegExecutablePath = " + ffmpegExecutablePath);
    }

    // 判断是否是windows
    public boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.toLowerCase().contains("windows");
    }

    /**
     * ffmpeg 工具导入用户目录下
     */
    public DefaultFFMPEGLocator() {
        boolean isWindows = isWindows();
        // 获取临时目录（windows）/用户目录（linux）
        File temp = new File(System.getProperty("user.dir"), "conf");
        if (isWindows) {
            temp = new File(System.getProperty("java.io.tmpdir"), "ruoyi-media" + myExeVersion);
        }
        // 获取并创建临时目录
        if (!temp.exists()) {
            temp.mkdirs();
            temp.deleteOnExit();
        }
        File zip = new File(temp, "ffmpeg.zip");
        String suffix = isWindows ? ".exe" : "";
        File exe = new File(temp.getAbsolutePath() + File.separator + "ffmpeg" + File.separator + "ffmpeg" + suffix);
        if (!zip.exists()) {
            log.info("ffmpeg.zip is not exists，copy file to {}", zip.getAbsolutePath());
            try {
                copyFile("ffmpeg/ffmpeg.zip", zip);
            } catch (Exception e) {
                log.error("ffmpeg.zip copy error", e);
                throw e;
            }
        }
        if (!exe.exists()) {
            try {
                log.info("ffmpeg执行文件不存在，unzip file to {}", exe.getAbsolutePath());
                ZipUtil.unzip(new File(temp.getAbsolutePath(), "ffmpeg.zip"));
            } catch (Exception e) {
                log.error("unzip ffmpeg.zip error", e);
                throw e;
            }

            if (!isWindows) {
                Runtime runtime = Runtime.getRuntime();
                try {
                    runtime.exec(new String[]{"/bin/chmod", "755",
                            exe.getAbsolutePath()});
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        this.path = exe.getAbsolutePath();
    }

    @Override
    protected String getFFMPEGExecutablePath() {
        return path;
    }


    /**
     * @param path
     * @param dest
     * @Author tanhuihuang
     * @Description 复制文件
     * @Date 9:04 2022/8/25
     */
    private void copyFile(String path, File dest) throws RuntimeException {
        try (InputStream input = DefaultFFMPEGLocator.class.getClassLoader().getResourceAsStream(path); OutputStream output = Files.newOutputStream(dest.toPath())) {
            byte[] buffer = new byte[1024];
            int l;
            while ((l = input.read(buffer)) != -1) {
                output.write(buffer, 0, l);
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot write file " + dest.getAbsolutePath());
        }
    }

}
