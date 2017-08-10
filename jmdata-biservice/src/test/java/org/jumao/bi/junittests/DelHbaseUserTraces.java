package org.jumao.bi.junittests;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Delete;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.Key;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kartty on 2017/5/26.
 */
public class DelHbaseUserTraces {

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Connection hconn = HBaseUtil.getConn();
        Table table = hconn.getTable(AddHbaseUserTraces.tableName);
        BufferedMutator bm = hconn.getBufferedMutator(TableName.valueOf("jmbi:userTraces"));

        Scan scan = new Scan();

        ResultScanner rs = table.getScanner(scan);
        Iterator<Result> it = rs.iterator();
        List<Delete> dels = new ArrayList<Delete>(Key.Num512);

        while (it.hasNext()) {
            Result res = it.next();
            byte[] rowkey = res.getRow();
            String rowkeyStr = StringUtils.getUtf8Str(rowkey);

            if (NumberUtils.isDigits(rowkeyStr)) {
                Delete del = new Delete(rowkey);
                dels.add(del);
            }
        }

        bm.mutate(dels);
        bm.flush();
        bm.close();
        rs.close();
        hconn.close();
    }
    
}
