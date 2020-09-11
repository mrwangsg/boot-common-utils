package test.csv.strategy;

import com.opencsv.bean.BeanField;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

/**
 * @创建人 sgwang
 * @name CustomMappingStrategy
 * @user shiguang.wang
 * @创建时间 2020/8/29
 * @描述 添加scv导出策略类，重写opencsv的类导出策略，用于解决导出时列顺序错乱的问题
 */
public class CustomMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {

    @Override
    public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {
        super.setColumnMapping(new String[FieldUtils.getAllFields(bean.getClass()).length]);

        // 统计输入bean，属性个数。注意：从零开始统计
        final int numColumns = findMaxFieldIndex();
        if (!isAnnotationDriven() || numColumns == -1) {
            return super.generateHeader(bean);
        }

        // 排序结果存储区域
        String[] header = new String[numColumns + 1];

        BeanField beanField;
        for (int i = 0; i <= numColumns; i++) {
            // 获取CsvBindByPosition位置
            beanField = findField(i);
            String columnHeaderName = extractHeaderName(beanField);
            header[i] = columnHeaderName;
        }
        return header;
    }

    /**
     * 如何字段没有设置CsvBindByName属性，赋值=StringUtils.EMPTY
     *
     * @param beanField
     * @return String
     */
    private String extractHeaderName(final BeanField beanField) {
        if (beanField == null || beanField.getField() == null || beanField.getField().getDeclaredAnnotationsByType(CsvBindByName.class).length == 0) {
            return StringUtils.EMPTY;
        }

        final CsvBindByName bindByNameAnnotation = beanField.getField().getDeclaredAnnotationsByType(CsvBindByName.class)[0];
        return bindByNameAnnotation.column();
    }

}