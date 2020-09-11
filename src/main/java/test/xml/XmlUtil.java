package test.xml;

import org.springframework.util.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @创建人 sgwang
 * @name XmlUtil
 * @user shiguang.wang
 * @创建时间 2020/9/7
 * @描述
 */
public class XmlUtil {

    /**
     * xml编码格式，默认为：UTF-8
     */
    public static final String defaultXmlEncode = StandardCharsets.UTF_8.toString();

    /**
     * 创建标准的document文档
     *
     * @return Document
     * @throws ParserConfigurationException
     */
    public static Document createRootDocument() throws ParserConfigurationException {
        // 创建document构造器
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        // 创建document
        Document document = docBuilder.newDocument();
        document.setXmlStandalone(true);

        return document;
    }

    /**
     * XML文档对象 转为 XML文件
     *
     * @param outputPath
     * @param fileName
     * @param document
     * @throws TransformerException
     */
    public static void generateXmlFile(String outputPath, String fileName, Document document) throws TransformerException {
        // 判断是否模板文件路径是否为空
        Assert.hasLength(outputPath, "XML文件输出路径，没有设置！");
        // 文件最终输出路径
        String fullPath = outputPath + File.separator + fileName;
        // 判断全路径文件是否存在
        File xmlFile = new File(fullPath);

        // 创建Transformer对象
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        // 解决首行不能换行问题
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "");
        // 输出内容是否使用换行
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        // 设置编码
        transformer.setOutputProperty(OutputKeys.ENCODING, defaultXmlEncode);

        // 对document封装，使其适配字节流
        DOMSource domSource = new DOMSource(document);

        // XML格式化，转字符串
        transformer.transform(domSource, new StreamResult(xmlFile));
    }

    /**
     * @param templatePath
     * @param fileName
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static Document parseTemplateFile(String templatePath, String fileName) throws ParserConfigurationException, IOException, SAXException {
        String fullPath = templatePath + File.separator + fileName;

        // 判断是否模板文件路径是否为空
        Assert.hasLength(templatePath, "XML模板文件路径，必须设置！");

        // 判断全路径文件是否存在
        File xmlFile = new File(fullPath);
        if (!xmlFile.exists()) {
            throw new RuntimeException("指定XML模板文件：" + fullPath);
        }

        // 得到创建 DOM 解析器的工厂
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        return docBuilder.parse(xmlFile);
    }

    public static void main(String[] args) throws Exception {
        Document docu = XmlUtil.parseTemplateFile("C:\\project\\spdb-provide-data\\src\\main\\resources\\template", "Dimple_FXSPOT_IMA_template.xml");

        Document document = XmlUtil.createRootDocument();
        Element summitElement = FxspotCommon.createSummitElement(document);
        FxspotCommon.createCommonElement(document, summitElement, new Date());
        FxspotCommon.createTrandeElement(document, summitElement);

        XmlUtil.generateXmlFile("C:\\project\\spdb-provide-data\\src\\main\\resources\\template", "test.xml", document);

    }

}
