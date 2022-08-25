
package com.ruoyi.common.utils.ffmpeg;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * A package-private utility extending java.io.BufferedReader. If a line read
 * with {@link RBufferedReader#readLine()} is not useful for the calling code,
 * it can be re-inserted in the stream. The same line will be returned again at
 * the next readLine() call.
 *
 * @author thh
 */
class RBufferedReader extends BufferedReader {

    /**
     * Re-inserted lines buffer.
     */
    private ArrayList lines = new ArrayList();

    /**
     * It builds the reader.
     *
     * @param in The underlying reader.
     */
    public RBufferedReader(Reader in) {
        super(in);
    }

    /**
     * It returns the next line in the stream.
     */
    @Override
    public String readLine() throws IOException {
        if (lines.size() > 0) {
            return (String) lines.remove(0);
        } else {
            return super.readLine();
        }
    }

    /**
     * Reinserts a line in the stream. The line will be returned at the next
     * {@link RBufferedReader#readLine()} call.
     *
     * @param line The line.
     */
    public void reinsertLine(String line) {
        lines.add(0, line);
    }

}
