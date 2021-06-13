package com.ruoyi.ufo.util;


import com.ruoyi.common.utils.file.qiwen.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author wangshuaijun
 * @description
 * 读取文件工具类：支持以下文件内容读取
 * 1. word(.doc),word(.docx)
 * 2. excel(.xls),excel(xlsx)
 * 3. pdf
 * 4. txt
 * 5. ppt(.ppt),pptx(,pptx)
 * @date 2019年4月19日10:52:45
 *
 */
public class ReadFileUtils {
    private static final Logger log = LoggerFactory.getLogger(ReadFileUtils.class);

    /**
     * 根据文件类型返回文件内容
     *
     * @param fileurl 文件路径
     * @return
     * @throws IOException
     */
    public static String getContentByPath(String fileurl) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileurl);
        String fileType = FileUtil.getFileExtendName(fileurl);
        switch (fileType){
            case "doc":
                return readWord(fileType, fileInputStream);
            case "docx":
                return readWord(fileType, fileInputStream);
            case "xlsx":
                return readExcel(fileType, fileInputStream);
            case "xls":
                return readExcel(fileType, fileInputStream);
            case "txt":
                return readTxt(fileurl);
            case "pdf":
                return readPdf(fileInputStream);
            case "ppt":
                return readPPT(fileType, fileInputStream);
            case "pptx":
                return readPPT(fileType, fileInputStream);
            default:
                log.error("不支持的文件类型");
                return "";

        }

    }

    /**
     * 根据文件类型返回文件内容
     *
//     * @param filepath
     * @return
     * @throws IOException
     */
    public static String getContentByInputStream(String fileType, InputStream inputStream) throws IOException {
        if ("doc".equals(fileType) || "docx".equals(fileType)) {
            return readWord(fileType, inputStream);
        } else if ("xlsx".equals(fileType) || "xls".equals(fileType)) {
            return readExcel(fileType, inputStream);
        } else if ("txt".equals(fileType)) {
            return readTxt(inputStream);
        } else if ("pdf".equals(fileType)) {
            return readPdf(inputStream);
        } else if ("ppt".equals(fileType) || "pptx".equals(fileType)) {
            return readPPT(fileType, inputStream);
        } else {
            log.error("不支持的文件类型！");
        }
        return "";
    }

    /**
     * 读取PDF中的内容
     *
//     * @param filePath
     * @return
     */
    public static String readPdf(InputStream inputStream) {
//        FileInputStream fileInputStream = null;
        PDDocument pdDocument = null;
        String content = "";
        try {
            //创建输入流对象
//            fileInputStream = new FileInputStream(filePath);
            //创建解析器对象
            PDFParser pdfParser = new PDFParser(new RandomAccessBuffer(inputStream));
            pdfParser.parse();
            //pdf文档
            pdDocument = pdfParser.getPDDocument();
            //pdf文本操作对象,使用该对象可以获取所读取pdf的一些信息
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            content = pdfTextStripper.getText(pdDocument);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //PDDocument对象时使用完后必须要关闭
                if (null != pdDocument) {
                    pdDocument.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 读取text中的内容
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    private static String readTxt(String filePath) throws IOException {
        File f = new File(filePath);
        return FileUtils.readFileToString(f, "GBK");
    }

    /**
     * 读取text中的内容
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static String readTxt(InputStream inputStream) throws IOException {
        return IOUtils.toString(inputStream, Charset.defaultCharset());
    }

    /**
     * 读取Excel中的内容
     *
     * @param fileType
     * @param inputStream
     * @return
     */
    private static String readExcel(String fileType, InputStream inputStream) {

        try {
                Workbook wb;
                //根据文件后缀（xls/xlsx）进行判断
                if ("xls".equalsIgnoreCase(fileType)) {
                    wb = new HSSFWorkbook(inputStream);
                } else if ("xlsx".equalsIgnoreCase(fileType)) {
                    wb = new XSSFWorkbook(inputStream);
                } else {
                    System.out.println("文件类型错误!");
                    return "";
                }
                //开始解析,获取页签数
                StringBuffer sb = new StringBuffer("");
                for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                    Sheet sheet = wb.getSheetAt(i);     //读取sheet
                    sb.append(sheet.getSheetName() + "_");
                    int firstRowIndex = sheet.getFirstRowNum() + 1;   //第一行是列名，所以不读
                    int lastRowIndex = sheet.getLastRowNum();
                    for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                        Row row = sheet.getRow(rIndex);
                        if (row != null) {
                            int firstCellIndex = row.getFirstCellNum();
                            int lastCellIndex = row.getLastCellNum();
                            for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                                Cell cell = row.getCell(cIndex);
                                if (cell != null) {
                                    sb.append(cell.toString());
                                }
                            }
                        }
                    }
                }
                return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 读取word中的内容
     *
     * @param fileType
     * @return
     */
    public static String readWord(String fileType, InputStream inputStream) {
        String buffer = "";
        try {
            if ("doc".equalsIgnoreCase(fileType)) {
               //   InputStream is = new FileInputStream(new File(path));
                WordExtractor ex = new WordExtractor(inputStream);
                buffer = ex.getText();
                ex.close();
            } else if ("docx".equalsIgnoreCase(fileType)) {
                //  OPCPackage opcPackage = POIXMLDocument.openPackage(path);
                XWPFWordExtractor extractor = new XWPFWordExtractor(OPCPackage.open(inputStream));
                // POIXMLTextExtractor extractor = new XWPFWordExtractor(OPCPackage.open(inputStream));
                buffer = extractor.getText();
                extractor.close();

            } else {
                System.out.println("此文件不是word文件！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffer;
    }

    private static String readPPT(String fileType, InputStream inputStream) {
        try {
            if ("ppt".equalsIgnoreCase(fileType)) {
//                QuickButCruddyTextExtractor extractor = new QuickButCruddyTextExtractor(inputStream);
                PowerPointExtractor extractor = new PowerPointExtractor(inputStream);
                return extractor.getText();
            } else if ("pptx".equalsIgnoreCase(fileType)) {
//                return new XSLFExtractor(new XMLSlideShow(OPCPackage.open(inputStream))).getText();
                return new XSLFPowerPointExtractor(OPCPackage.open(inputStream)).getText();
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        } catch (OpenXML4JException e) {
            e.getMessage();
        } catch (XmlException e) {
            e.printStackTrace();
        }

        return "";
    }
}
