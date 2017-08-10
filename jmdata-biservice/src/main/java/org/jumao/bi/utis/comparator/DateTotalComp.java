package org.jumao.bi.utis.comparator;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by kartty on 2017/5/8.
 */
public class DateTotalComp implements Comparator<Map.Entry<String, String>> {

    /**
     * 按日期从小到大排
     * @param o1
     * @param o2
     * @return
     */
    public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
        try {
            double t1 = Double.parseDouble(o1.getKey());
            double t2 = Double.parseDouble(o2.getKey());
            if (t2 > t1) {
                return -1;
            } else if (t2 == t1) {
                return 0;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 0;
        }
    }
}
