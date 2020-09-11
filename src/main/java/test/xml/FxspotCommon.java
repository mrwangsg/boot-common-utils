package test.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import test.util.DateUtil;

import java.util.Date;

/**
 * @创建人 sgwang
 * @name FxspotCommon
 * @user shiguang.wang
 * @创建时间 2020/9/7
 * @描述
 */
public class FxspotCommon {

    /**
     * 数据导出最外层包裹标签
     */
    public static final String SUMMIT_TAG = "SUMMIT";


    /**
     * 交易数据公共部分
     */
    public static final String COMMON_TAG = "COMMON";
    /**
     * 消息ID
     */
    public static final String MsgId = "MsgId";
    /**
     * 交易ID
     */
    public static final String ExternalId = "ExternalId";
    /**
     * 交易类型
     */
    public static final String Type = "Type";
    /**
     * 版本
     */
    public static final String Version = "Version";
    /**
     * 导入动作
     */
    public static final String Action = "Action";
    /**
     * 时间戳
     */
    public static final String Timestamp = "Timestamp";
    /**
     * 源系统
     */
    public static final String TargetSystem = "TargetSystem";
    /**
     * 目标系统
     */
    public static final String SourceSystem = "SourceSystem";


    /**
     * 交易数据明细标签
     */
    public static final String TRADEDETAIL_TAG = "TRADEDETAIL";
    /**
     * 交易数据列表标签
     */
    public static final String TRADEDLIST_TAG = "TRADELIST";
    /**
     * FXSPOT数据行标签
     */
    public static final String FXSPOT_TAG = "FXSPOT";


    /**
     * 在文档根目录，创建Summit标签
     *
     * @param document
     * @return Element
     */
    public static Element createSummitElement(Document document) {
        // 创建summit节点
        Element summitNode = document.createElement(SUMMIT_TAG);
        document.appendChild(summitNode);

        return summitNode;
    }

    /**
     * 创建公共头部分
     *
     * @param document
     * @param summitElement
     * @param nowDate
     * @return Element
     */
    public static Element createCommonElement(Document document, Element summitElement, Date nowDate) {
        // 创建common节点
        Element commonNode = document.createElement(COMMON_TAG);
        summitElement.appendChild(commonNode);

        // 1位方向标识(固定2表示导入)+ 2位系统编号(06)+ 外源系统编号(32位)[0...0]
        String systemNo = "20600000000000000000000000000000000";

        Element temp;
        // common节点添加，消息Id
        temp = document.createElement(MsgId);
        temp.setTextContent(DateUtil.formatDate(nowDate, DateUtil.DATE_NO_LINE_TIME_NO_COLON_FORMAT) + systemNo);
        commonNode.appendChild(temp);

        // common节点添加，交易ID
        temp = document.createElement(ExternalId);
        temp.setTextContent(DateUtil.formatDate(nowDate, DateUtil.ONLY_DATE_NO_LINE_TIME_FORMAT));
        commonNode.appendChild(temp);

        // common节点添加，交易类型
        temp = document.createElement(Type);
        temp.setTextContent("FXSPOT");
        commonNode.appendChild(temp);

        // common节点添加，版本
        temp = document.createElement(Version);
        temp.setTextContent("0");
        commonNode.appendChild(temp);

        // common节点添加，导入动作
        temp = document.createElement(Action);
        temp.setTextContent("cCREATE");
        commonNode.appendChild(temp);

        // common节点添加，时间戳
        temp = document.createElement(Timestamp);
        temp.setTextContent(DateUtil.formatDate(nowDate, DateUtil.DATE_NO_LINE_FORMAT));
        commonNode.appendChild(temp);

        // common节点添加，源系统
        temp = document.createElement(SourceSystem);
        temp.setTextContent("DIMPLE");
        commonNode.appendChild(temp);

        // common节点添加，目标系统
        temp = document.createElement(TargetSystem);
        temp.setTextContent("IMA");
        commonNode.appendChild(temp);

        return commonNode;
    }

    /**
     * 创建交易明细节点；并在交易明细节点下，创建交易数据集合节点；
     * 返回：装集合数据的节点
     *
     * @param document
     * @param summitElement
     * @return Element
     */
    public static Element createTrandeElement(Document document, Element summitElement) {
        // 创建tradeDetail节点，加在summit节点下
        Element detailNode = document.createElement(TRADEDETAIL_TAG);
        summitElement.appendChild(detailNode);

        // 创建tradeList节点，加在tradeDetail节点下
        Element listNode = document.createElement(TRADEDLIST_TAG);
        detailNode.appendChild(listNode);

        // 返回装集合数据的节点
        return listNode;
    }

    /**
     * TODO 将行数据转化为标签
     *
     * @param document
     * @param trandeListElement
     * @return Element
     */
    public static Element addFxspotRowDataElement(Document document, Element trandeListElement) {
        // TODO
        return null;
    }

}
