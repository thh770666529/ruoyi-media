package com.ruoyi.common.utils.ffmpeg.m3u8;


import com.ruoyi.common.utils.ffmpeg.VideoUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: thh
 * @description:
 * @date: Created in 2021/07/04 11:19
 */
public class M3u8VideoUtils {

    private static final Logger log = LoggerFactory.getLogger(M3u8VideoUtils.class);

    public static void main(String[] args) throws FileNotFoundException {
        //System.out.println(VideoUtil.downloadAndMergeM3U8Video("http://cache.m.iqiyi.com/tmts/2058056753323600/c0758ed26ae4dd1eafaf206a8bac33df/?type=m3u8&qdv=1&t=1603216710047&src=02029022240000000000&vf=98fef242fff2d3f487ada5dae8ef6d0c", "C:/Users/King/Desktop/ruoyi/test.mp4"));
        String m3u8path = "C:\\Users\\King\\Desktop\\ruoyi\\hls";
        clearM3u8(m3u8path);
        //M3U8 m3U8ByFile = getM3U8ByFile(m3u8path);
        //System.out.println("m3U8ByFile = " + m3U8ByFile);

    }


    public static void clearM3u8(String m3u8FolderPath){
        //删除原来已经生成的m3u8及ts文件
        File m3u8dir = new File(m3u8FolderPath);
        if (m3u8dir.isFile()){
            return;
        }
        if(!m3u8dir.exists()){
            m3u8dir.mkdirs();
        }
        if(m3u8dir.exists()&& m3u8dir.getPath().indexOf(File.separator+"hls")>=0) {//在hls目录方可删除，以免错误删除
            String[] children = m3u8dir.list();
            //删除目录中的文件
            for (int i = 0; i < children.length; i++) {
                File file = new File(m3u8FolderPath, children[i]);
                file.delete();
            }
        }
    }


    public static boolean singleThread(String urlStr, String pathname) {
        try {
            if (null == urlStr || "".equals(urlStr)) {
                return false;
            }
            M3U8 m3u8ByURL = getM3U8ByURL(urlStr);
            String basePath = m3u8ByURL.getBasepath();
            List<M3U8.Ts> list = m3u8ByURL.getTsList();
            List<String> files = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                try {
                    M3U8.Ts m3U8Ts = list.get(i);
                    String pathname_temp = pathname.replace(".mp4", "_" + i + ".mp4");
                    System.out.println("down==" + basePath + m3U8Ts.getFile());
                    long len = singleThread3(basePath + m3U8Ts.getFile(), pathname_temp);
                    if (len > 0) {
                        files.add(pathname_temp);
                    } else {
                        System.out.println("下载失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            boolean b = VideoUtils.mergeVideo(pathname, files);
            for (String s : files) {
                deleteFile(s);
            }
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取m3u8path
     * @param m3u8path
     * @return
     * @throws FileNotFoundException
     */
    public static M3U8 getM3U8ByFile(String m3u8path) throws FileNotFoundException {
        try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(m3u8path)));
                M3U8 m3U8 = new M3U8();
                String line;
                float seconds = 0;
                int mIndex;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("#")) {
                        if (line.startsWith("#EXTINF:")) {
                            line = line.substring(8);
                            if ((mIndex = line.indexOf(",")) != -1) {
                                line = line.substring(0, mIndex + 1);
                            }
                            try {
                                seconds = Float.parseFloat(line);
                            } catch (Exception e) {
                                seconds = 0;
                            }
                        }
                        continue;
                    }
                    m3U8.addTs(new M3U8.Ts(line, seconds));
                    seconds = 0;
                }
               reader.close();
               return m3U8;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }


    public static M3U8 getM3U8ByURL(String m3u8URL) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(m3u8URL).openConnection();
            if (conn.getResponseCode() == 200) {
                String realUrl = conn.getURL().toString();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String basepath = realUrl.substring(0, realUrl.lastIndexOf("/") + 1);
                System.out.println("basepath==" + basepath);
                M3U8 ret = new M3U8();
                ret.setBasepath(basepath);

                String line;
                float seconds = 0;
                int mIndex;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("#")) {
                        if (line.startsWith("#EXTINF:")) {
                            line = line.substring(8);
                            if ((mIndex = line.indexOf(",")) != -1) {
                                line = line.substring(0, mIndex + 1);
                            }
                            try {
                                seconds = Float.parseFloat(line);
                            } catch (Exception e) {
                                seconds = 0;
                            }
                        }
                        continue;
                    }
                    if (line.endsWith("m3u8")) {
                        return getM3U8ByURL(basepath + line);
                    }
                    ret.addTs(new M3U8.Ts(line, seconds));
                    seconds = 0;
                }
                reader.close();

                return ret;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        try {
            if (null == fileName) {
                log.info("删除的单个文件不存在== " + fileName);
                return false;
            }
            File file = new File(fileName);
            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    log.info("删除单个文件" + fileName + "成功！");
                    return true;
                } else {
                    log.info("删除单个文件" + fileName + "失败！");
                    return false;
                }
            } else {
                log.info("删除单个文件失败：" + fileName + "不存在！");
                return false;
            }
        } catch (Exception e) {
            log.error("删除单个文件失败== " + fileName, e);
        }
        return false;
    }

    private static long singleThread3(String urlStr, String pathname) throws Exception {
        if (null == urlStr || !urlStr.startsWith("http") || null == pathname) {
            log.info("下载视频的参数有问题 urlStr=" + urlStr + " , pathname=" + pathname);
            return 0;
        }

        File file = new File(pathname);
        try(InputStream inputStream = HttpUtils.doGet(urlStr))
        {

                FileOutputStream fos = new FileOutputStream(file);
                byte[] temp = new byte[2048];
                int len;
                while ((len = inputStream.read(temp)) != -1) {
                    fos.write(temp, 0, len);
                }
                fos.flush();
                fos.close();
                inputStream.close();
                log.info("文件" + pathname + "下載完成！");
                if (file.exists()) {
                    return file.length();
                }
        }catch (Exception e){
            log.error("文件" + pathname + "下載失敗！error=>{}",e.getMessage());
        }
        return 0;
    }

}
