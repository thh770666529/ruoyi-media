package com.ruoyi.common.utils.ffmpeg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ffmpeg执行类
 *
 * @author thh
 */
public class FFMPEGExecutor {

    private static final Logger log = LoggerFactory.getLogger(FFMPEGExecutor.class);

    /**
     * ffmpeg执行路径
     */
    private String ffmpegExecutablePath;

    /**
     * 执行参数.
     */
    private List args = new ArrayList();

    /**
     * ffmpeg进程
     */
    private Process ffmpeg = null;

    /**
     * ffmpeg杀手
     */
    private ProcessKiller ffmpegKiller = null;

    /**
     * 执行输入流
     */
    private InputStream inputStream = null;

    /**
     * 执行输出流
     */
    private OutputStream outputStream = null;

    /**
     * 错误流
     */
    private InputStream errorStream = null;

    /**
     * It build the executor.
     *
     * @param ffmpegExecutablePath The path of the ffmpeg executable.
     */
    public FFMPEGExecutor(String ffmpegExecutablePath) {
        this.ffmpegExecutablePath = ffmpegExecutablePath;
    }


    public void addArgument(String arg) {
        args.add(arg);
    }

    /**
     * 使用前面给定的参数执行 ffmpeg 进程
     */
    public void execute() throws IOException {
        int argsSize = args.size();
        String[] cmd = new String[argsSize + 1];
        cmd[0] = ffmpegExecutablePath;
        for (int i = 0; i < argsSize; i++) {
            cmd[i + 1] = (String) args.get(i);
        }
        Runtime runtime = Runtime.getRuntime();
        ffmpeg = runtime.exec(cmd);
        ffmpegKiller = new ProcessKiller(ffmpeg);
        runtime.addShutdownHook(ffmpegKiller);
        inputStream = ffmpeg.getInputStream();
        outputStream = ffmpeg.getOutputStream();
        errorStream = ffmpeg.getErrorStream();
    }


    public CmdResult execute2() throws IOException {
        CmdResult cmdResult = new CmdResult(false, "");
        List<String> cmds = new ArrayList<>();
        cmds.add(ffmpegExecutablePath);
        for (Object s : args) {
            cmds.add(s.toString());
        }
        ProcessBuilder builder = new ProcessBuilder(cmds);
        builder.redirectErrorStream(true);
        try {
            Process process = builder.start();
            final StringBuilder stringBuilder = new StringBuilder();
            final InputStream inputStream = process.getInputStream();
            new Thread(new Runnable() {//启动新线程为异步读取缓冲器，防止线程阻塞
                @Override
                public void run() {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    try {
                        while ((line = reader.readLine()) != null) {
                            log.debug(line);
                            stringBuilder.append(line);
                        }
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            process.waitFor();
            cmdResult.setSuccess(true);
            cmdResult.setMsg(stringBuilder.toString());
        } catch (Exception e) {
            log.error("ffmpeg执行异常", e);
            throw new RuntimeException("ffmpeg执行异常" + e.getMessage());
        }
        return cmdResult;
    }


    public InputStream getInputStream() {
        return inputStream;
    }


    public OutputStream getOutputStream() {
        return outputStream;
    }


    public InputStream getErrorStream() {
        return errorStream;
    }


    public void destroy() {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Throwable t) {
                ;
            }
            inputStream = null;
        }
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Throwable t) {
            }
            outputStream = null;
        }
        if (errorStream != null) {
            try {
                errorStream.close();
            } catch (Throwable t) {
            }
            errorStream = null;
        }
        if (ffmpeg != null) {
            ffmpeg.destroy();
            ffmpeg = null;
        }
        if (ffmpegKiller != null) {
            Runtime runtime = Runtime.getRuntime();
            runtime.removeShutdownHook(ffmpegKiller);
            ffmpegKiller = null;
        }
    }

}
