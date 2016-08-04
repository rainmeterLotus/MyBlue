package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class BlueDaoGenerator {
    /**
     * 数据库版本
     */
    private static int version = 1;
    /**
     * 生成java文件所在包名
     */
    private static String packageName = "com.myblue.dao";
    /**
     * 指定路径
     */
    private static String tagFile = "./app/src/main/java";
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(version, packageName);

        addData(schema);
     //   new DaoGenerator().generateAll(schema,tagFile);
    }

    private static void addData(Schema schema) {
        //一个实体（类）就关联到数据库中的一张表，此处表名为「BlueFey」（既类名）
        Entity theme = schema.addEntity("BlueFey");
        theme.addIdProperty();
        theme.addStringProperty("blueUrl");
        theme.addStringProperty("blueName");
    }
}
