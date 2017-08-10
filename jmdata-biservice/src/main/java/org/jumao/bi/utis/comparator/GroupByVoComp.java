package org.jumao.bi.utis.comparator;

import org.jumao.bi.entites.trade.register.vo.GroupByVo;

import java.util.Comparator;

/**
 * Created by kartty on 2017/5/8.
 */
public class GroupByVoComp implements Comparator<GroupByVo> {

    /**
     * @param o1
     * @param o2
     * @return
     */
    public int compare(GroupByVo o1, GroupByVo o2) {
        if (o2.getTotal() > o1.getTotal()) {
            return 1;
        } else if (o2.getTotal() > o1.getTotal()) {
            return 0;
        } else {
            return -1;
        }
    }
}
