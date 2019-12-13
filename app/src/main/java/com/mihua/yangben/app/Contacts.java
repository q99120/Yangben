package com.mihua.yangben.app;

public class Contacts {
    public static final int SEND = 100;
    /**
     * 固定呋喃四项数据源
     */
    public static String guding_funan_json =
            "[{\"solven_name\":\"盐酸用量(ml)\",\"solven_num\":0},{\"solven_name\":\"孵育温度(摄氏度)\",\"solven_num\":2},{\"solven_name\":\"搅拌转速(RPM)\",\"solven_num\":4},{\"solven_name\":\"搅拌时间(分钟)\",\"solven_num\":5}, {\"solven_name\":\"氢氧化钠用量(ml)\",\"solven_num\":6},{\"solven_name\":\"PBS缓冲液用量(ml)\",\"solven_num\":7},{\"solven_name\":\"再次搅拌转速(RPM)\",\"solven_num\":8},{\"solven_name\":\"再次搅拌时间(分钟)\",\"solven_num\":9},{\"solven_name\":\"甲醇用量(ml)\",\"solven_num\":10},{\"solven_name\":\"活化压力1(MPA)\",\"solven_num\":11},{\"solven_name\":\"活化时间1(分钟)\",\"solven_num\":12},{\"solven_name\":\"水用量(ml)\",\"solven_num\":13},{\"solven_name\":\"活化压力2(MPA)\",\"solven_num\":14},{\"solven_name\":\"活化时间2(分钟)\",\"solven_num\":15},{\"solven_name\":\"上样用量(ml)\",\"solven_num\":16},{\"solven_name\":\"上样压力(MPA)\",\"solven_num\":17},{\"solven_name\":\"上样时间(分钟)\",\"solven_num\":18},{\"solven_name\":\"水用量(ml)\",\"solven_num\":19},{\"solven_name\":\"净化压力(MPA)\",\"solven_num\":20},{\"solven_name\":\"净化时间(分钟)\",\"solven_num\":21},{\"solven_name\":\"乙酸乙酯用量(ml)\",\"solven_num\":22},{\"solven_name\":\"洗脱压力(MPA)\",\"solven_num\":23},{\"solven_name\":\"洗脱时间(分钟)\",\"solven_num\":24},{\"solven_name\":\"浓缩温度(摄氏度)\",\"solven_num\":25},{\"solven_name\":\"吹气温度(摄氏度)\",\"solven_num\":26},{\"solven_name\":\"浓缩时间(分钟)\",\"solven_num\":27}]\n";


    public static String guding_kq_json =
            "[{\"solven_name\":\"样本乙腈\",\"solven_num\":0},{\"solven_name\":\"搅拌转速\",\"solven_num\":1},{\"solven_name\":\"搅拌时间\",\"solven_num\":2},{\"solven_name\":\"过柱乙腈\",\"solven_num\":2},{\"solven_name\":\"活化正压\",\"solven_num\":3},{\"solven_name\":\"活化时间\",\"solven_num\":4},{\"solven_name\":\"上样样本液\",\"solven_num\":5},{\"solven_name\":\"上样正压\",\"solven_num\":6},{\"solven_name\":\"上样时间\",\"solven_num\":7},{\"solven_name\":\"上样次数\",\"solven_num\":8},{\"solven_name\":\"浓缩孵育温度\",\"solven_num\":9},{\"solven_name\":\"浓缩吹气温度\",\"solven_num\":10},{\"solven_name\":\"浓缩时间\",\"solven_num\":11},{\"solven_name\":\"上样样本液吸取值\",\"solven_num\":11}]\n";


    public static String funan_json = " [{\"solven_name\":\"盐酸用量(ml)\",\"solven_num\":35},{\"solven_name\":\"孵育温度(摄氏度)\",\"solven_num\":2}," +
            "{\"solven_name\":\"搅拌转速(RPM)\",\"solven_num\":4},{\"solven_name\":\"搅拌时间(分钟)\",\"solven_num\":5}, " +
            "{\"solven_name\":\"氢氧化钠用量(ml)\",\"solven_num\":6},{\"solven_name\":\"PBS缓冲液用量(ml)\",\"solven_num\":7}," +
            "{\"solven_name\":\"再次搅拌转速(RPM)\",\"solven_num\":8},{\"solven_name\":\"再次搅拌时间(分钟)\",\"solven_num\":9}," +
            "{\"solven_name\":\"甲醇用量(ml)\",\"solven_num\":10},{\"solven_name\":\"排液量1(ml)\",\"solven_num\":11}," +
            "{\"solven_name\":\"水用量1(ml)\",\"solven_num\":12},{\"solven_name\":\"排液量2(ml)\",\"solven_num\":13}," +
            "{\"solven_name\":\"上样用量(ml)\",\"solven_num\":14},{\"solven_name\":\"水用量2(ml)\",\"solven_num\":15}," +
            "{\"solven_name\":\"排液量4(ml)\",\"solven_num\":16},{\"solven_name\":\"乙酸乙酯用量(ml)\",\"solven_num\":17}," +
            "{\"solven_name\":\"排液量5(ml)\",\"solven_num\":18},{\"solven_name\":\"浓缩温度(摄氏度)\",\"solven_num\":25}," +
            "{\"solven_name\":\"吹气温度(摄氏度)\",\"solven_num\":26},{\"solven_name\":\"浓缩时间(分钟)\",\"solven_num\":27}]\n";


    public static String kq_json =
            "[{\"solven_name\":\"乙腈用量(ml)\",\"solven_num\":0},{\"solven_name\":\"搅拌转速(RPM)\",\"solven_num\":1}," +
                    "{\"solven_name\":\"搅拌时间(分钟)\",\"solven_num\":2},{\"solven_name\":\"氢氧化钠用量(ml)\",\"solven_num\":2}," +
                    "{\"solven_name\":\"排液量1(ml)\",\"solven_num\":3},{\"solven_name\":\"上样用量(ml)\",\"solven_num\":4}," +
                    "{\"solven_name\":\"浓缩温度(摄氏度)\",\"solven_num\":9},{\"solven_name\":\"吹气温度(摄氏度)\",\"solven_num\":10}," +
                    "{\"solven_name\":\"浓缩时间(分钟)\",\"solven_num\":11}]\n";


    public static String sr_json =
            " [{\"solven_name\":\"乙酸盐缓冲液用量(ml)\",\"solven_num\":35},{\"solven_name\":\"孵育温度(摄氏度)\",\"solven_num\":2}," +
                    "{\"solven_name\":\"搅拌转速(RPM)\",\"solven_num\":4},{\"solven_name\":\"搅拌时间(分钟)\",\"solven_num\":5}, " +
                    "{\"solven_name\":\"甲醇用量1(ml)\",\"solven_num\":6},{\"solven_name\":\"排液量1(ml)\",\"solven_num\":7}," +
                    "{\"solven_name\":\"水用量(ml)\",\"solven_num\":8},{\"solven_name\":\"排液量2(ml)\",\"solven_num\":9}," +
                    "{\"solven_name\":\"高氯酸用量(ml)\",\"solven_num\":10},{\"solven_name\":\"排液量3(ml)\",\"solven_num\":11}," +
                    "{\"solven_name\":\"上样用量(ml)\",\"solven_num\":12},{\"solven_name\":\"甲醇用量2(ml)\",\"solven_num\":13}," +
                    "{\"solven_name\":\"排液量4(ml)\",\"solven_num\":14},{\"solven_name\":\"甲醇水溶液用量(ml)\",\"solven_num\":15}," +
                    "{\"solven_name\":\"排液量5(ml)\",\"solven_num\":16},{\"solven_name\":\"氨化甲醇用量(ml)\",\"solven_num\":17}," +
                    "{\"solven_name\":\"排液量6(ml)\",\"solven_num\":18}]\n";
}
