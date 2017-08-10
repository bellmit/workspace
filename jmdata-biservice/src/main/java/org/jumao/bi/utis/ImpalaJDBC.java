package org.jumao.bi.utis;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImpalaJDBC {


    private static final String JDBC_DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";
    // impalad host
    private static final String IMPALAD_HOST = "172.18.203.113";  //  impala  Impala Daemon角色的地址
    private static final String IMPALAD_JDBC_PORT = "21050";  // impalad JDBC port
    private static final String CONNECTION_URL = "jdbc:hive2://" + IMPALAD_HOST + ':' + IMPALAD_JDBC_PORT + "/;auth=noSasl";
    private static final String SQL_STATEMENT = "select * from hive_student/* where key = \"1002\"*/;";  // sql语句样例


    /**
     * 初始化impala连接
     */
    private static Connection con;

    public static Statement getInstence() {
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER_NAME);
            con = DriverManager.getConnection(CONNECTION_URL);
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return stmt;
    }


    /**
     * 查询行数据
     */
    public List getImpalaData(Object obj, String Sql) {
        ArrayList<Object> resultList = new ArrayList<Object>();  //  返回的多条数据集合
        try {
            Statement stmt = getInstence();
            ResultSet rs = stmt.executeQuery(Sql);
            while (rs.next()) {
                Class c = obj.getClass();  //  通过类获取
                Object o = c.newInstance();  //  创建对象

                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnValue = rs.getString(columnName);
                    String columnTypeName = metaData.getColumnTypeName(i);

                    //  拼接方法首字母大小写
                    char[] cs = columnName.toCharArray();
                    cs[0] -= 32;
                    columnName = String.valueOf(cs);

                    try {
                        Class object = getColumnTypeClazz(columnTypeName);
                        Method method = c.getMethod("set" + columnName, object); //  第一个参数是调用的方法名，第二个参数是这个方法传的值的类型
                        //  反射调用方法
                        Object val = getColumnValue(columnTypeName, columnValue);
                        method.invoke(o, val);  //  第一个参数是对象名；第二个是你给这个方法传的值
                    } catch (Exception e) {
//                        logHelper.getBuilder().error("没有定义相应实体方法 = ", e);
                        e.printStackTrace();
                    }
                }
                resultList.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    /**
     * 统计值查询
     */
    public String
    getTotal(String Sql) {
        try {
            Statement stmt = getInstence();
            ResultSet rs = stmt.executeQuery(Sql);
            String string = "";
            while (rs.next()) {
                string = rs.getString(1);
            }
            return string;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 根据根据查询返回的数据类型获得相应类型的class
     */
    Class getColumnTypeClazz(String columnTypeName) {
        if (columnTypeName.equals("string")) return String.class;
        if (columnTypeName.equals("int")) return Integer.class;
        if (columnTypeName.equals("timestamp")) return Timestamp.class;
        if (columnTypeName.equals("boolean")) return Boolean.class;
        if (columnTypeName.equals("decimal")) return BigDecimal.class;
        if (columnTypeName.equals("smallint")) return Integer.class;
        if (columnTypeName.equals("varchar")) return String.class;
        if (columnTypeName.equals("bigint")) return Long.class;
        return null;
    }

    /**
     * 根据返回类型的字符串得到相应类型
     */
    Object getColumnValue(String columnTypeName, String columnValue) {
        if (columnTypeName.equals("string")) return columnValue;
        if (columnTypeName.equals("int")) return Integer.valueOf(columnValue);
        if (columnTypeName.equals("timestamp")) return new Timestamp(System.currentTimeMillis()).valueOf(columnValue);
        if (columnTypeName.equals("boolean")) return Boolean.getBoolean(columnValue);
        if (columnTypeName.equals("decimal")) return new BigDecimal(columnValue);
        if (columnTypeName.equals("smallint")) return Integer.valueOf(columnValue);
        if (columnTypeName.equals("varchar")) return columnValue;
        if (columnTypeName.equals("bigint")) return Long.parseLong(columnValue);
        return null;
    }

    /**
     * 查询指定单列
     */
    Map<String, ArrayList<String>> getColumn(String Sql, String ags) {
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        List<String> list = new ArrayList<String>();
        Statement stmt = getInstence();
        try {
            ResultSet rs = stmt.executeQuery(Sql);
            ResultSetMetaData metaData = rs.getMetaData();
            while (rs.next()) {
                String coluStr = rs.getString(ags);
                list.add(coluStr);
                System.out.println(coluStr);
            }
            map.put(ags, (ArrayList<String>) list);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }


    public static void main(String[] args) {
//        List<Object> resultList = new ImpalaJDBC().getImpalaData(new HiveStudent(), "select * from hive_student");
//        Map<String, ArrayList<String>> column = new ImpalaJDBC().getColumn("select phone from hive_student", "phone");
    }


    //  通过反射获得实体对象方法
    Object setEntity(Object obj, String methodName, String parType, String methodParm) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//            Class c = Class.forName(classPath);  //  通过路径获取要创建对象的类
        Class c = obj.getClass();  //  通过类获取
        Object o = c.newInstance();  //  创建对象

        //  拼接方法大小写
        char[] cs = methodName.toCharArray();
        cs[0] -= 32;
        methodName = String.valueOf(cs);

        //反射调用方法
        Method method = c.getMethod("set" + methodName, String.class); //  第一个参数是调用的方法名，第二个参数是这个方法传的值的类型
        method.invoke(o, methodParm);  //  第一个参数是对象名；第二个是你给这个方法传的值
        return o;
    }
}
