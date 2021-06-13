package com.ruoyi.ufo.util.office;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.ImageIO;
import com.ruoyi.common.utils.file.qiwen.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.poi.hslf.usermodel.HSLFShape;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFSlideShowImpl;
import org.apache.poi.hslf.usermodel.HSLFTextParagraph;
import org.apache.poi.hslf.usermodel.HSLFTextRun;
import org.apache.poi.hslf.usermodel.HSLFTextShape;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * ppt转换为html
 *
 * @authorchen
 */
public class PPTUtil {

    private static Logger logger = LoggerFactory.getLogger(PPTUtil.class);


    public static void main(String[] args) {
        System.out.println(pptToHtml("E:\\poi\\Java02(团队协作工具之SVN) .ppt", "E:\\poi", "33333"));
    }
    /**
     * @param sourceFilePath
     * @param targetFolder
     * @param targetFileName
     * @return
     */
    public static String pptToHtml(String sourceFilePath, String targetFolder, String targetFileName) {
        new File(targetFolder).mkdirs();
        File pptFile = new File(sourceFilePath);
        if (pptFile.exists()) {
            try {
                String type = FileUtil.getFileExtendName(sourceFilePath);
                String targetFilePath = targetFolder + "/" + targetFileName;
                if ("ppt".equals(type)) {
                    String htmlStr = toImage2003(sourceFilePath, targetFolder);
                    FileUtils.writeStringToFile(new File("targetFilePath"), htmlStr, "UTF-8");
                    ; //.writeToFile(htmlStr, targetFilePath, "UTF-8");
                    return htmlStr;
                } else if ("pptx".equals(type)) {
                    String htmlStr = toImage2007(sourceFilePath, targetFolder);
                    FileUtils.writeStringToFile(new File("targetFilePath"), htmlStr, "UTF-8");
//                    return FileUtils.writeToFile(htmlStr, targetFilePath, "UTF-8");
                    return htmlStr;
                } else {
                    logger.error("ppt转换为html,源文件={}不是ppt文件", sourceFilePath);
                    return null;
                }

            } catch (Exception e) {
                logger.error("ppt文档转换为html,发生异常,源文件={},", sourceFilePath, e);
                return null;
            }
        } else {
            logger.error("ppt文档转换为html,源文件={}不存在", sourceFilePath);
            return null;
        }
    }

    public static String toImage2007(String sourcePath, String targetDir) throws Exception {
        String htmlStr = "";
        FileInputStream is = new FileInputStream(sourcePath);
        XMLSlideShow ppt = new XMLSlideShow(is);
        is.close();
        new File(targetDir).mkdirs();
        Dimension pgsize = ppt.getPageSize();
        String imageFileName = "ppt" + RandomUtils.nextInt(100, 999); //ComUtil.genUUID(3);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ppt.getSlides().size(); i++) {
            try {
                for (XSLFShape shape : ppt.getSlides().get(i).getShapes()) {
                    if (shape instanceof XSLFTextShape) {
                        XSLFTextShape tsh = (XSLFTextShape) shape;
                        for (XSLFTextParagraph p : tsh) {
                            for (XSLFTextRun r : p) {
                                r.setFontFamily("宋体");
                            }
                        }
                    }
                }
                BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                // clear the drawing area
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
                // render
                ppt.getSlides().get(i).draw(graphics);
                // save the output
                String imageDir = targetDir + "/" + imageFileName + "/";
                 //   FileUtils.createDir(imageDir);// create image dir
                new File(imageDir).mkdirs();
                // 相对路径
                String relativeImagePath = imageFileName + "/" + imageFileName + "-" + (i + 1) + ".png";
                 // 绝对路径
                String imagePath = imageDir + imageFileName + "-" + (i + 1) + ".png";
                sb.append("<br>");
                sb.append("<img src=" + "\"" + relativeImagePath + "\"" + "/>");
                FileOutputStream out = new FileOutputStream(imagePath);
                ImageIO.write(img, "png", out);
                out.close();
            } catch (Exception e) {
                logger.error("ppt转换为html,发生异常,源文件={}", sourcePath, e);
                logger.error("第" + i + "张ppt转换出错");
                return null;
            }
        }
        htmlStr = sb.toString();
        return htmlStr;
    }

    public static String toImage2003(String sourcePath, String targetDir) {
        String htmlStr = "";
        try {
            HSLFSlideShow ppt = new HSLFSlideShow(new HSLFSlideShowImpl(sourcePath));
            // FileUtils.createDir(targetDir);
            new File(targetDir).mkdirs();
            Dimension pgsize = ppt.getPageSize();
            StringBuffer sb = new StringBuffer();
            String imageFileName = UUID.randomUUID().toString();
            for (int i = 0; i < ppt.getSlides().size(); i++) {
                for (HSLFShape shape : ppt.getSlides().get(i).getShapes()) {
                    if (shape instanceof HSLFTextShape) {
                        HSLFTextShape tsh = (HSLFTextShape) shape;
                        for (HSLFTextParagraph p : tsh) {
                            for (HSLFTextRun r : p) {
                                r.setFontFamily("宋体");
                            }
                        }
                    }
                }
                BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                 // clear the drawing area
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
                   // render
                ppt.getSlides().get(i).draw(graphics);
                String imageDir = targetDir + "/" + imageFileName + "/";
                // create image dir
                // FileUtils.createDir(imageDir);
                new File(imageDir).mkdirs();
                // 相对路径
                String relativeImagePath = imageFileName + "/" + imageFileName + "-" + (i + 1) + ".png";
                // 绝对路径
                String imagePath = imageDir + imageFileName + "-" + (i + 1) + ".png";
                sb.append("<br>");
                sb.append("<img src=" + "\"" + relativeImagePath + "\"" + "/>");
                FileOutputStream out = new FileOutputStream(imagePath);
                ImageIO.write(img, "png", out);
                out.close();
            }
            htmlStr = sb.toString();
        } catch (Exception e) {
            logger.error("ppt转换为html,发生异常,源文件={}", sourcePath, e);
            return null;
        }

        return htmlStr;
    }

    /**
     * @param srcImgPath
     * @param distImgPath
     * @param width
     * @param height
     * @throws IOException
     */
    public static void resizeImage(String srcImgPath, String distImgPath, int width, int height) throws IOException {
        File srcFile = new File(srcImgPath);
        Image srcImg = ImageIO.read(srcFile);
        BufferedImage buffImg = null;
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        buffImg.getGraphics().drawImage(srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        ImageIO.write(buffImg, "JPEG", new File(distImgPath));
    }

/*public static void main(String[] args) {
//POIPptToHtmlUtils.pptToHtml("D:/diagnosis/file/temp//ppt2007.pptx", "D:/diagnosis/file/temp/", "test5.html");
POIPptToHtmlUtils.pptToHtml("D:/diagnosis/file/temp//ppt2003.ppt", "D:/diagnosis/file/temp/", "test6.html");
}*/

}
