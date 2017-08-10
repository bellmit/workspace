package org.jumao.bi.junittests;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.jumao.bi.utis.constants.Key;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author chen qian
 */
public class AddHbaseUserTraces {

    static final int Pf_Ele100700 = 100700;
    static final int Pf_Ele100300 = 100300;
    static final int Pf_Ele100900 = 100900;
    static final int Pf_Ele101000 = 101000;
    static final int Pf_Ele100800 = 100800;
    static final int Pf_Ele101200 = 101200;
    static final int Pf_Ele101400 = 101400;
    static final int Pf_Ele101100 = 101100;
    static final int Pf_Ele101300 = 101300;
    static final int Pf_Ele100200 = 100200;
    static final int Pf_Ele102200 = 102200;
    static final int Pf_Ele100100 = 100100;

    static int[] platformArr = {Pf_Ele100700, Pf_Ele100300, Pf_Ele100900, Pf_Ele101000, Pf_Ele100800, Pf_Ele101200, Pf_Ele101400,
            Pf_Ele101100, Pf_Ele101300, Pf_Ele100200, Pf_Ele102200, Pf_Ele100100};

    static int platformArrSize = platformArr.length;

    static Random random = new Random();

    public static TableName tableName = TableName.valueOf("jmbi:userTraces");


    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        org.apache.hadoop.hbase.client.Connection hconn = HBaseUtil.getConn();
        BufferedMutator bm = hconn.getBufferedMutator(tableName);

        Class.forName("com.mysql.jdbc.Driver");
        Connection mysqlConn = DriverManager.getConnection("jdbc:mysql://172.18.1.13:3306/trade_dev",
                "user_jsj_read1", "jsj@123");


        Statement st = mysqlConn.createStatement();
        ResultSet rs = st.executeQuery("select u.user_id, u.company_id from trade_order o ,trade_company c ,trade_center_user u" +
                " where o.member_id=u.user_id and u.company_id=c.comp_id group by u.user_id, u.company_id;");

        List<Put> puts = new ArrayList<Put>(Key.Num512);
        byte[] family = Bytes.toBytes("info");
        byte[] bizCode = Bytes.toBytes("bizCode");
        byte[] loginTime = Bytes.toBytes("loginTime");
        byte[] logoutTime = Bytes.toBytes("logoutTime");
        byte[] userId = Bytes.toBytes("userId");
        byte[] companyId = Bytes.toBytes("companyId");
        byte[] isOverseas = Bytes.toBytes("isOverseas");
        byte[] deviceType = Bytes.toBytes("deviceType");
        byte[] browseType = Bytes.toBytes("browseType");

        while (rs.next()) {
            long mysqluId = rs.getLong(1);
            long compId = rs.getLong(2);

            Put put = new Put(Bytes.toBytes(String.valueOf(System.currentTimeMillis() + random.nextInt(Key.Num5000))));
            put.addColumn(family, bizCode, Bytes.toBytes(platformArr[random.nextInt(platformArrSize)] + ""));
            put.addColumn(family, loginTime, Bytes.toBytes("20170526100708"));
            put.addColumn(family, logoutTime, Bytes.toBytes("20170526130708"));
            put.addColumn(family, userId, Bytes.toBytes(mysqluId + ""));
            put.addColumn(family, companyId, Bytes.toBytes(compId + ""));
            put.addColumn(family, isOverseas, Bytes.toBytes("false"));
            put.addColumn(family, deviceType, Bytes.toBytes("Browser"));
            put.addColumn(family, browseType, Bytes.toBytes("browseType"));

            puts.add(put);
        }

        bm.mutate(puts);
        bm.flush();
        bm.close();
        hconn.close();
        rs.close();
        st.close();
        mysqlConn.close();
    }

}
