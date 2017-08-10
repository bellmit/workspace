package org.jumao.bi.utis.comparator;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by kartty on 2017/5/8.
 */
public class TypeTotalComp implements Comparator<Map.Entry<String, Long>> {

    /**
     * 按 total 从大到小排
     * @param o1
     * @param o2
     * @return
     */
    public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
        try {
            long t1 = o1.getValue();
            long t2 = o2.getValue();
            if (t2 > t1) {
                return 1;
            } else if (t2 == t1) {
                return 0;
            } else {
                return -1;
            }
        } catch (Exception e) {
            return 0;
        }
    }
}
