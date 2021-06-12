package com.ruoyi.ufo.util;//package com.ruoyi.ufo.util.office;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.Scanner;
//import java.util.zip.GZIPOutputStream;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
////import net.arnx.wmf2svg.gdi.svg.SvgGdi;
////import net.arnx.wmf2svg.gdi.wmf.WmfParser;
//
//import net.arnx.wmf2svg.gdi.svg.SvgGdi;
//import net.arnx.wmf2svg.gdi.wmf.WmfParser;
//import org.apache.batik.transcoder.TranscoderInput;
//import org.apache.batik.transcoder.TranscoderOutput;
//import org.apache.batik.transcoder.TranscodingHints;
//import org.apache.batik.transcoder.image.PNGTranscoder;
//import org.apache.batik.transcoder.wmf.tosvg.WMFTranscoder;
////import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//
//public class Wmf2Png {
//	public static void main(String[] args) throws Exception {
//		// convert("F:\\SVN\\BobUtil\\web\\25177.wmf");
//		// System.out.println((20 / (21 * 1.0)));
//		// svgToPng("F:\\SVN\\BobUtil\\web\\25177.svg", "F:\\SVN\\BobUtil\\web\\25177.png");
//	}
//
//	/**
//	 * @Description: 进行转换
//	 * @param filePath
//	 *            文件路径
//	 * @return 设定文件
//	 */
//	public static String convert(String filePath) {
//		String pngFile = "";
//		File wmfFile = new File(filePath);
//		try {
//			if (!wmfFile.getName().contains(".wmf")) {
//				throw new Exception("请确认输入的文件类型是wmf");
//			}
//			// wmf -> svg
//			String svgFile = filePath.replace("wmf", "svg");
//			wmfToSvg(filePath, svgFile);
//			// 对svg做预出理
//			PreprocessSvgFile(svgFile);
//			// svg -> png
//			pngFile = filePath.replace("wmf", "png");
//			svgToPng(svgFile, pngFile);
//			// 删除 svg
//			File file = new File(svgFile);
//			if (file.exists()) {
//				file.delete();
//			}
//			// 删除 wmf
//			if (wmfFile.exists()) {
//				wmfFile.delete();
//			}
//
//		} catch (Exception e) {
//			try {
//				e.printStackTrace();
//				wmfToJpg(filePath);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//		}
//		return wmfFile.getName().replace("wmf", "png");
//	}
//
//	/**
//	 * 将wmf转换为svg
//	 *
//	 * @param src
//	 * @param dest
//	 */
//	public static void wmfToSvg(String src, String dest) throws Exception {
//		boolean compatible = false;
//		try {
//			InputStream in = new FileInputStream(src);
//			WmfParser parser = new WmfParser();
//			final SvgGdi gdi = new SvgGdi(compatible);
//			parser.parse(in, gdi);
//
//			Document doc = gdi.getDocument();
//			OutputStream out = new FileOutputStream(dest);
//			if (dest.endsWith(".svgz")) {
//				out = new GZIPOutputStream(out);
//			}
//
//			output(doc, out);
//		} catch (Exception e) {
//			throw e;
//		}
//	}
//
//	/**
//	 * @Description: 输出svg文件
//	 * @param doc
//	 * @param out
//	 * @throws Exception
//	 *             设定文件
//	 */
//	private static void output(Document doc, OutputStream out) throws Exception {
//		TransformerFactory factory = TransformerFactory.newInstance();
//		Transformer transformer = factory.newTransformer();
//		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
//		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//		transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "-//W3C//DTD SVG 1.0//EN");
//		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
//				"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd");
//		transformer.transform(new DOMSource(doc), new StreamResult(out));
//		out.flush();
//		out.close();
//		out = null;
//	}
//
//	/**
//	 * @Description:对svg文件做预处理(这里主要是调整大小，先缩小10倍，如果还大于默认值，则按比例缩小)
//	 * @param svgFile
//	 * @throws Exception
//	 *             设定文件
//	 */
//	private static void PreprocessSvgFile(String svgFile) throws Exception {
//		int defaultWeight = 500;// 默认宽度
//		FileInputStream inputs = new FileInputStream(svgFile);
//		Scanner sc = new Scanner(inputs, "UTF-8");
//		ByteArrayOutputStream os = new ByteArrayOutputStream();
//		while (sc.hasNextLine()) {
//			String ln = sc.nextLine();
//			if (!ln.startsWith("<!DOCTYPE")) {
//				os.write((ln + "\r\n").getBytes());
//			}
//		}
//		os.flush();
//
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder builder;
//		builder = factory.newDocumentBuilder();
//		Document doc = null;
//		try {
//			doc = builder.parse(new ByteArrayInputStream(os.toByteArray()));
//		} catch (Exception e) {
//			inputs = new FileInputStream(svgFile);
//			os = new ByteArrayOutputStream();
//			int noOfByteRead = 0;
//			while ((noOfByteRead = inputs.read()) != -1) {
//				os.write(noOfByteRead);
//			}
//			os.flush();
//			doc = builder.parse(new ByteArrayInputStream(os.toByteArray()));
//		} finally {
//			os.close();
//			inputs.close();
//		}
//
//		int height = Integer.parseInt(((Element) doc.getElementsByTagName("svg").item(0)).getAttribute("height"));
//		int width = Integer.parseInt(((Element) doc.getElementsByTagName("svg").item(0)).getAttribute("width"));
//		int newHeight = 0;// 新高
//		int newWidth = 0;// 新宽
//		newHeight = height / 10;// 高缩小10倍
//		newWidth = width / 10; // 宽缩小10倍
//		// 如果缩小10倍后宽度还比defaultHeight大，则进行调整
//		if (newWidth > defaultWeight) {
//			newWidth = defaultWeight;
//			newHeight = defaultWeight * height / width;
//		}
//
//		((Element) doc.getElementsByTagName("svg").item(0)).setAttribute("width", String.valueOf(newWidth));
//		((Element) doc.getElementsByTagName("svg").item(0)).setAttribute("height", String.valueOf(newHeight));
//		OutputStream out = new FileOutputStream(svgFile);
//		output(doc, out);
//	}
//
//	/**
//	 * 将svg图片转成png图片
//	 *
//	 * @param filePath
//	 * @throws Exception
//	 */
//	public static void svgToPng(String svgPath, String pngFile) throws Exception {
//		File svg = new File(svgPath);
//		FileInputStream wmfStream = new FileInputStream(svg);
//		ByteArrayOutputStream imageOut = new ByteArrayOutputStream();
//		int noOfByteRead = 0;
//		while ((noOfByteRead = wmfStream.read()) != -1) {
//			imageOut.write(noOfByteRead);
//		}
//		imageOut.flush();
//		imageOut.close();
//		wmfStream.close();
//
//		ByteArrayOutputStream jpg = new ByteArrayOutputStream();
//		FileOutputStream jpgOut = new FileOutputStream(pngFile);
//
//		byte[] bytes = imageOut.toByteArray();
//		PNGTranscoder t = new PNGTranscoder();
//		TranscoderInput in = new TranscoderInput(new ByteArrayInputStream(bytes));
//		TranscoderOutput out = new TranscoderOutput(jpg);
//		t.transcode(in, out);
//		jpgOut.write(jpg.toByteArray());
//		jpgOut.flush();
//		jpgOut.close();
//		imageOut = null;
//		jpgOut = null;
//	}
//
//	/**
//	 * 将wmf图片转成png图片(备用方法，即当上面的转换失败时用这个)
//	 *
//	 * @param filePath
//	 * @throws Exception
//	 */
//	public static String wmfToJpg(String wmfPath) throws Exception {
//		//先wmf-->svg
//		File wmf = new File(wmfPath);
//		FileInputStream wmfStream = new FileInputStream(wmf);
//		ByteArrayOutputStream imageOut = new ByteArrayOutputStream();
//		int noOfByteRead = 0;
//		while ((noOfByteRead = wmfStream.read()) != -1) {
//			imageOut.write(noOfByteRead);
//		}
//		imageOut.flush();
//		imageOut.close();
//		wmfStream.close();
//
//		// WMFHeaderProperties prop = new WMFHeaderProperties(wmf);
//		WMFTranscoder transcoder = new WMFTranscoder();
//		TranscodingHints hints = new TranscodingHints();
//		transcoder.setTranscodingHints(hints);
//		TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(imageOut.toByteArray()));
//		ByteArrayOutputStream svg = new ByteArrayOutputStream();
//		TranscoderOutput output = new TranscoderOutput(svg);
//		transcoder.transcode(input, output);
//
//		//再svg-->png
//		ByteArrayOutputStream jpg = new ByteArrayOutputStream();
//		String jpgFile = StringUtils.replace(wmfPath, "wmf", "png");
//		FileOutputStream jpgOut = new FileOutputStream(jpgFile);
//
//		byte[] bytes = svg.toByteArray();
//		PNGTranscoder t = new PNGTranscoder();
//		TranscoderInput in = new TranscoderInput(new ByteArrayInputStream(bytes));
//		TranscoderOutput out = new TranscoderOutput(jpg);
//		t.transcode(in, out);
//		jpgOut.write(jpg.toByteArray());
//		jpgOut.flush();
//		jpgOut.close();
//		return jpgFile;
//	}
//}
