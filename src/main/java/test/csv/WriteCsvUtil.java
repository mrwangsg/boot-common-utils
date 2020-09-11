package test.csv;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.csv.strategy.CustomMappingStrategy;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @创建人 sgwang
 * @name WriteCsvUtil
 * @user shiguang.wang
 * @创建时间 2020/8/29
 * @描述
 */
public final class WriteCsvUtil {

    private static final Logger logger = LoggerFactory.getLogger(WriteCsvUtil.class);

    /**
     * 将多行数据，保存到指定文件上
     *
     * @param dataList
     * @param clazz
     * @param finalPath
     * @param <T>
     */
    public static <T> void write2csv(List<T> dataList, Class<T> clazz, String finalPath) throws Exception {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(finalPath), StandardCharsets.UTF_8)) {
            // 手动加上BOM标识
            writer.write(new String(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}));

            // 使用自定义策略，目的是：解决导出时列顺序错乱的问题
            CustomMappingStrategy<T> mappingStrategy = new CustomMappingStrategy<>();
            mappingStrategy.setType(clazz);

            // 设置文件格式
            StatefulBeanToCsvBuilder<T> builder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<T> beanToCsv = builder.withMappingStrategy(mappingStrategy)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .withEscapechar(CSVWriter.NO_ESCAPE_CHARACTER)
                    .build();

            // 导出数据
            beanToCsv.write(dataList);
            writer.flush();
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException ex) {
            logger.error("导出csv文件异常：{}", ex.getMessage());
            throw ex;
        }
    }
//
//    public void readCSV2(String finalPath) {
//        try {
//            // 使用BOMInputStream自动去除UTF-8中的BOM
//            Reader reader = new InputStreamReader(new BOMInputStream(
//                    new FileInputStream(finalPath)), "utf-8");
//            CSVReader csvReader = new CSVReader(reader, CSVWriter.DEFAULT_SEPARATOR,
//                    CSVWriter.NO_QUOTE_CHARACTER);
//
//            // 列名的映射
//            HeaderColumnNameTranslateMappingStrategy<Person> strategy =
//                    new HeaderColumnNameTranslateMappingStrategy<Person>();
//            strategy.setType(Person.class);
//            Map<String, String> columnMapping = new HashMap<String, String>();
//            columnMapping.put("姓名", "name");
//            columnMapping.put("年龄", "age");
//            columnMapping.put("性别", "sex");
//            columnMapping.put("手机", "phone");
//            columnMapping.put("住址", "address");
//            strategy.setColumnMapping(columnMapping);
//
//            CsvToBean<Person> csvToBean = new CsvToBean<Person>();
//
//            List<Person> list = csvToBean.parse(strategy, csvReader);
//
//            for (Person p : list) {
//                System.out.println(p.toString());
//            }
//            csvReader.close();
//            reader.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
}


