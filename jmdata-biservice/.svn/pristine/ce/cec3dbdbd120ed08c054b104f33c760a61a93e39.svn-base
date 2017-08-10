package org.jumao.bi.utis;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * for new version api
 */
public class HbaseUtils {

    private static Logger logger = LoggerFactory.getLogger(HbaseUtils.class);
    private static Connection connection = null;


    public synchronized static Connection getConn(String zkQuorum) {
        if (connection == null) {
            Configuration cfg = HBaseConfiguration.create();
            cfg.set("hbase.zookeeper.quorum", zkQuorum);
            cfg.set("hbase.zookeeper.property.clientPort", "2181");
            try {
                connection = ConnectionFactory.createConnection(cfg);
            } catch (IOException e) {
                logger.error("", e);
            }
        }
        return connection;
    }

    public static void main(String[] args) throws Exception {
        Connection conn = HbaseUtils.getConn("172.18.203.113,172.18.203.114,172.18.203.115,172.18.203.116");

        Table table = conn.getTable(TableName.valueOf("jmbi:ga_country"));
        Result res = table.get(new Get(Bytes.toBytes("10220120170726110")));
        System.out.println(new String(res.value()));

//        BufferedMutator bm = conn.getBufferedMutator(TableName.valueOf("jmbi:ga_country"));
//
//        List<Put> puts = new ArrayList<>();
//        Put put = new Put(Bytes.toBytes("2"));
//        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("b"), Bytes.toBytes("b2"));
//        puts.add(put);
//
//        bm.mutate(puts);
//        bm.close();
    }

}
