package com.ruoyi.common.utils.file;

import java.util.HashMap;
import java.util.Map;

/**
 * 媒体类型工具类
 *
 * @author ruoyi
 */
public class MimeTypeUtils
{
    public static final String IMAGE_PNG = "image/png";

    public static final String IMAGE_JPG = "image/jpg";

    public static final String IMAGE_JPEG = "image/jpeg";

    public static final String IMAGE_BMP = "image/bmp";

    public static final String IMAGE_GIF = "image/gif";


    public static final String VIDEO_MP4 = "video/mp4";

    public static final String VIDEO_AVI = "video/avi";

    public static final String VIDEO_RMVB = "video/rmvb";

    public static final String[] IMAGE_EXTENSION = { "bmp", "gif", "jpg", "jpeg", "png" };

    public static final String[] FLASH_EXTENSION = { "swf", "flv" };

    public static final String[] MEDIA_EXTENSION = { "swf", "flv", "mp3", "wav", "wma", "wmv", "mid", "avi", "mpg",
            "asf", "rm", "rmvb","mp4" };

    public static final String[] DEFAULT_ALLOWED_EXTENSION = {
            // 图片
            "bmp", "gif", "jpg", "jpeg", "png",
            // word excel powerpoint
            "doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt",
            // 压缩文件
            "rar", "zip", "gz", "bz2",
            // pdf
            "pdf" };

    public static String getExtension(String prefix)
    {
        switch (prefix)
        {
            case IMAGE_PNG:
                return "png";
            case IMAGE_JPG:
                return "jpg";
            case IMAGE_JPEG:
                return "jpeg";
            case IMAGE_BMP:
                return "bmp";
            case IMAGE_GIF:
                return "gif";
            case VIDEO_MP4:
                return "mp4";
            case VIDEO_RMVB:
                return "rmvb";
            case VIDEO_AVI:
                return "avi";
            default:
                return "";
        }
    }

    private static Map<String, String> mimeMap = new HashMap();

    public static String getMime(String suffix) {
        return mimeMap.get(suffix);
    }

    static {
        mimeMap.put("323", "text/h323");
        mimeMap.put("acx", "application/internet-property-stream");
        mimeMap.put("ai", "application/postscript");
        mimeMap.put("aif", "audio/x-aiff");
        mimeMap.put("aifc", "audio/x-aiff");
        mimeMap.put("aiff", "audio/x-aiff");
        mimeMap.put("asf", "video/x-ms-asf");
        mimeMap.put("asr", "video/x-ms-asf");
        mimeMap.put("asx", "video/x-ms-asf");
        mimeMap.put("au", "audio/basic");
        mimeMap.put("avi", "video/x-msvideo");
        mimeMap.put("axs", "application/olescript");
        mimeMap.put("bas", "text/plain");
        mimeMap.put("bcpio", "application/x-bcpio");
        mimeMap.put("bin", "application/octet-stream");
        mimeMap.put("bmp", "image/bmp");
        mimeMap.put("c", "text/plain");
        mimeMap.put("cat", "application/vnd.ms-pkiseccat");
        mimeMap.put("cdf", "application/x-cdf");
        mimeMap.put("cer", "application/x-x509-ca-cert");
        mimeMap.put("class", "application/octet-stream");
        mimeMap.put("clp", "application/x-msclip");
        mimeMap.put("cmx", "image/x-cmx");
        mimeMap.put("cod", "image/cis-cod");
        mimeMap.put("cpio", "application/x-cpio");
        mimeMap.put("crd", "application/x-mscardfile");
        mimeMap.put("crl", "application/pkix-crl");
        mimeMap.put("crt", "application/x-x509-ca-cert");
        mimeMap.put("csh", "application/x-csh");
        mimeMap.put("css", "text/css");
        mimeMap.put("dcr", "application/x-director");
        mimeMap.put("der", "application/x-x509-ca-cert");
        mimeMap.put("dir", "application/x-director");
        mimeMap.put("dll", "application/x-msdownload");
        mimeMap.put("dms", "application/octet-stream");
        mimeMap.put("doc", "application/msword");
        mimeMap.put("dot", "application/msword");
        mimeMap.put("dvi", "application/x-dvi");
        mimeMap.put("dxr", "application/x-director");
        mimeMap.put("eps", "application/postscript");
        mimeMap.put("etx", "text/x-setext");
        mimeMap.put("evy", "application/envoy");
        mimeMap.put("exe", "application/octet-stream");
        mimeMap.put("fif", "application/fractals");
        mimeMap.put("flr", "x-world/x-vrml");
        mimeMap.put("gif", "image/gif");
        mimeMap.put("gtar", "application/x-gtar");
        mimeMap.put("gz", "application/x-gzip");
        mimeMap.put("h", "text/plain");
        mimeMap.put("hdf", "application/x-hdf");
        mimeMap.put("hlp", "application/winhlp");
        mimeMap.put("hqx", "application/mac-binhex40");
        mimeMap.put("hta", "application/hta");
        mimeMap.put("htc", "text/x-component");
        mimeMap.put("htm", "text/plain");
        mimeMap.put("html", "text/plain");
        mimeMap.put("htt", "text/webviewhtml");
        mimeMap.put("ico", "image/x-icon");
        mimeMap.put("ief", "image/ief");
        mimeMap.put("iii", "application/x-iphone");
        mimeMap.put("ins", "application/x-internet-signup");
        mimeMap.put("isp", "application/x-internet-signup");
        mimeMap.put("jfif", "image/pipeg");
        mimeMap.put("jpe", "image/jpeg");
        mimeMap.put("jpeg", "image/jpeg");
        mimeMap.put("jpg", "image/jpeg");
        mimeMap.put("js", "application/x-javascript");
        mimeMap.put("latex", "application/x-latex");
        mimeMap.put("lha", "application/octet-stream");
        mimeMap.put("lsf", "video/x-la-asf");
        mimeMap.put("lsx", "video/x-la-asf");
        mimeMap.put("lzh", "application/octet-stream");
        mimeMap.put("m13", "application/x-msmediaview");
        mimeMap.put("m14", "application/x-msmediaview");
        mimeMap.put("m3u", "audio/x-mpegurl");
        mimeMap.put("man", "application/x-troff-man");
        mimeMap.put("mdb", "application/x-msaccess");
        mimeMap.put("me", "application/x-troff-me");
        mimeMap.put("mht", "message/rfc822");
        mimeMap.put("mhtml", "message/rfc822");
        mimeMap.put("mid", "audio/mid");
        mimeMap.put("mny", "application/x-msmoney");
        mimeMap.put("mov", "video/quicktime");
        mimeMap.put("movie", "video/x-sgi-movie");
        mimeMap.put("mp2", "video/mpeg");
        mimeMap.put("mp3", "audio/mpeg");
        mimeMap.put("mp4", "video/mp4");
        mimeMap.put("mpa", "video/mpeg");
        mimeMap.put("mpe", "video/mpeg");
        mimeMap.put("mpeg", "video/mpeg");
        mimeMap.put("mpg", "video/mpeg");
        mimeMap.put("mpp", "application/vnd.ms-project");
        mimeMap.put("mpv2", "video/mpeg");
        mimeMap.put("ms", "application/x-troff-ms");
        mimeMap.put("mvb", "application/x-msmediaview");
        mimeMap.put("nws", "message/rfc822");
        mimeMap.put("oda", "application/oda");
        mimeMap.put("p10", "application/pkcs10");
        mimeMap.put("p12", "application/x-pkcs12");
        mimeMap.put("p7b", "application/x-pkcs7-certificates");
        mimeMap.put("p7c", "application/x-pkcs7-mime");
        mimeMap.put("p7m", "application/x-pkcs7-mime");
        mimeMap.put("p7r", "application/x-pkcs7-certreqresp");
        mimeMap.put("p7s", "application/x-pkcs7-signature");
        mimeMap.put("pbm", "image/x-portable-bitmimeMap");
        mimeMap.put("pdf", "application/pdf");
        mimeMap.put("pfx", "application/x-pkcs12");
        mimeMap.put("pgm", "image/x-portable-graymimeMap");
        mimeMap.put("pko", "application/ynd.ms-pkipko");
        mimeMap.put("pma", "application/x-perfmon");
        mimeMap.put("pmc", "application/x-perfmon");
        mimeMap.put("pml", "application/x-perfmon");
        mimeMap.put("pmr", "application/x-perfmon");
        mimeMap.put("pmw", "application/x-perfmon");
        mimeMap.put("pnm", "image/x-portable-anymimeMap");
        mimeMap.put("pot,", "application/vnd.ms-powerpoint");
        mimeMap.put("ppm", "image/x-portable-pixmimeMap");
        mimeMap.put("pps", "application/vnd.ms-powerpoint");
        mimeMap.put("ppt", "application/vnd.ms-powerpoint");
        mimeMap.put("prf", "application/pics-rules");
        mimeMap.put("ps", "application/postscript");
        mimeMap.put("pub", "application/x-mspublisher");
        mimeMap.put("qt", "video/quicktime");
        mimeMap.put("ra", "audio/x-pn-realaudio");
        mimeMap.put("ram", "audio/x-pn-realaudio");
        mimeMap.put("ras", "image/x-cmu-raster");
        mimeMap.put("rgb", "image/x-rgb");
        mimeMap.put("rmi", "audio/mid");
        mimeMap.put("roff", "application/x-troff");
        mimeMap.put("rtf", "application/rtf");
        mimeMap.put("rtx", "text/richtext");
        mimeMap.put("scd", "application/x-msschedule");
        mimeMap.put("sct", "text/scriptlet");
        mimeMap.put("setpay", "application/set-payment-initiation");
        mimeMap.put("setreg", "application/set-registration-initiation");
        mimeMap.put("sh", "application/x-sh");
        mimeMap.put("shar", "application/x-shar");
        mimeMap.put("sit", "application/x-stuffit");
        mimeMap.put("snd", "audio/basic");
        mimeMap.put("spc", "application/x-pkcs7-certificates");
        mimeMap.put("spl", "application/futuresplash");
        mimeMap.put("src", "application/x-wais-source");
        mimeMap.put("sst", "application/vnd.ms-pkicertstore");
        mimeMap.put("stl", "application/vnd.ms-pkistl");
        mimeMap.put("stm", "text/html");
        mimeMap.put("svg", "image/svg+xml");
        mimeMap.put("sv4cpio", "application/x-sv4cpio");
        mimeMap.put("sv4crc", "application/x-sv4crc");
        mimeMap.put("swf", "application/x-shockwave-flash");
        mimeMap.put("t", "application/x-troff");
        mimeMap.put("tar", "application/x-tar");
        mimeMap.put("tcl", "application/x-tcl");
        mimeMap.put("tex", "application/x-tex");
        mimeMap.put("texi", "application/x-texinfo");
        mimeMap.put("texinfo", "application/x-texinfo");
        mimeMap.put("tgz", "application/x-compressed");
        mimeMap.put("tif", "image/tiff");
        mimeMap.put("tiff", "image/tiff");
        mimeMap.put("tr", "application/x-troff");
        mimeMap.put("trm", "application/x-msterminal");
        mimeMap.put("tsv", "text/tab-separated-values");
        mimeMap.put("txt", "text/plain");
        mimeMap.put("uls", "text/iuls");
        mimeMap.put("ustar", "application/x-ustar");
        mimeMap.put("vcf", "text/x-vcard");
        mimeMap.put("vrml", "x-world/x-vrml");
        mimeMap.put("wav", "audio/x-wav");
        mimeMap.put("wcm", "application/vnd.ms-works");
        mimeMap.put("wdb", "application/vnd.ms-works");
        mimeMap.put("wks", "application/vnd.ms-works");
        mimeMap.put("wmf", "application/x-msmetafile");
        mimeMap.put("wps", "application/vnd.ms-works");
        mimeMap.put("wri", "application/x-mswrite");
        mimeMap.put("wrl", "x-world/x-vrml");
        mimeMap.put("wrz", "x-world/x-vrml");
        mimeMap.put("xaf", "x-world/x-vrml");
        mimeMap.put("xbm", "image/x-xbitmimeMap");
        mimeMap.put("xla", "application/vnd.ms-excel");
        mimeMap.put("xlc", "application/vnd.ms-excel");
        mimeMap.put("xlm", "application/vnd.ms-excel");
        mimeMap.put("xls", "application/vnd.ms-excel");
        mimeMap.put("xlt", "application/vnd.ms-excel");
        mimeMap.put("xlw", "application/vnd.ms-excel");
        mimeMap.put("xof", "x-world/x-vrml");
        mimeMap.put("xpm", "image/x-xpixmimeMap");
        mimeMap.put("xwd", "image/x-xwindowdump");
        mimeMap.put("z", "application/x-compress");
        mimeMap.put("zip", "application/zip");
    }
}
