package org.jumao.bi.dao.trade.operate;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/28.
 */
public interface IOperationOverviewDao {

    /**
     * 普通多行查询
     * @param
     * @return
     */
    public List<Map<String, Object>> getTopTotal(String platform, String nowDate);

    /**
     * 返回以第一列为key，第二列为value的map
     * @param SQL
     * @return
     */
    Map<String, String> getForMap(String SQL);

    /**
     * 定制pv、uv、ip、ave、avg、exit返回map类型list
     * @param SQL
     * @return
     */
    List<Map<String, String>> getForListMap(String SQL);
}
