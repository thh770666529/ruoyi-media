
package com.ruoyi.common.utils.ffmpeg;

/**
 * A package-private utility to add a shutdown hook to kill ongoing encoding
 * processes at the jvm shutdown.
 *
 * @author thh
 */
class ProcessKiller extends Thread {

    /**
     * The process to kill.
     */
    private Process process;

    /**
     * Builds the killer.
     *
     * @param process The process to kill.
     */
    public ProcessKiller(Process process) {
        this.process = process;
    }

    /**
     * It kills the supplied process.
     */
    @Override
    public void run() {
        process.destroy();
    }

}
