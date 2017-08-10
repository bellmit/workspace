package org.jumao.bi.dao.ga.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;
import org.jumao.bi.dao.CommonDaoImpl;
import org.jumao.bi.dao.ga.GaDao;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.HbaseUtils;
import org.jumao.bi.utis.RegMapUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.enums.GaFields;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Repository(GaDaoImpl.Ga_Dao)
public class GaDaoImpl implements GaDao {

    public static final String Ga_Dao = "gaDao";
    private static final String HTABLE_PREFIX = "jmbi:ga_";

    private Logger logger = Logger.getLogger(this.getClass());

    @Value(value = "${zk.quorum}")
    private String zkQuorum;

    private Gson gson = new Gson();
    private JsonParser jsonParser = new JsonParser();
    private byte[] cf = Bytes.toBytes("info");
    private byte[] platform_id = Bytes.toBytes("platform_id");
    private byte[] create_time = Bytes.toBytes("create_time");


    @Override
    public void parseJsonToHbase(String hbaseCrtTime, String jobjStr) throws Exception {
        JsonObject jobj = jsonParser.parse(jobjStr).getAsJsonObject();
        Map<String, List<Put>> tablePutsMap = new HashMap<String, List<Put>>();

        for (Map.Entry<String, JsonElement> entry1 : jobj.entrySet()) {
            String platformId = entry1.getKey();
            JsonObject dataJobj = entry1.getValue().getAsJsonObject();

            for (Map.Entry<String, JsonElement> entry2 : dataJobj.entrySet()) {
                try {
                    String tableName = getTableName(entry2.getKey());
                    JsonArray jarr = entry2.getValue().getAsJsonArray();
                    String rowKeyPrefix = StringUtils.joinStr(platformId, hbaseCrtTime);

                    for (int i = 0; i < jarr.size(); i++) {
                        JsonObject thisJobj = jarr.get(i).getAsJsonObject();
                        Put put = new Put(Bytes.toBytes(rowKeyPrefix + i));
                        put.addColumn(cf, platform_id, Bytes.toBytes(platformId));
                        put.addColumn(cf, create_time, Bytes.toBytes(hbaseCrtTime));

                        for (Map.Entry<String, JsonElement> entry3 : thisJobj.entrySet()) {
                            String hField = entry3.getKey();
                            String hVal = entry3.getValue().getAsString();

                            put.addColumn(cf, Bytes.toBytes(hField), Bytes.toBytes(hVal));
                            RegMapUtils.fill_key_listMap(tablePutsMap, tableName, put);
                        }
                    }
                } catch (Exception e) {
                    logger.error("", e);
                }
            }
        }

        for (Map.Entry<String, List<Put>> ele : tablePutsMap.entrySet()) {
            List<Put> puts = ele.getValue();

            if (!puts.isEmpty()) {
                Connection conn = HbaseUtils.getConn(zkQuorum);
                BufferedMutator bm = conn.getBufferedMutator(TableName.valueOf(ele.getKey()));
                bm.mutate(puts);
                bm.close();
                logger.info(StringUtils.joinStr(GeneralUtils.getCurrMethod(),
                        " mutate puts size:", puts.size(), " data Success!"));
            }
        }
    }

    private String getTableName(String tableDesc) {
        return StringUtils.joinStr(HTABLE_PREFIX, tableDesc);
    }


}
