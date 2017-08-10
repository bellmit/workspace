package org.jumao.bi.junittests;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HBaseUtil {

    static Logger LOG = LoggerFactory.getLogger(HBaseUtil.class);
    public static Connection connection = null;

    /**
     *
     * @return
     */
    public static Connection getConn() {
        if (connection == null) {
            Configuration cfg = HBaseConfiguration.create();
            cfg.set("hbase.zookeeper.quorum", "dn1,dn2,dn3,dn4");
            cfg.set("hbase.zookeeper.property.clientPort", "2181");
            try {
                connection = ConnectionFactory.createConnection(cfg);
            } catch (IOException e) {
                LOG.error(e.getMessage());
            }
        }
        return connection;
    }

}
