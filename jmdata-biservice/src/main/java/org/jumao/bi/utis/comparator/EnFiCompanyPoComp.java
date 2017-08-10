package org.jumao.bi.utis.comparator;

import org.apache.log4j.Logger;
import org.jumao.bi.dao.trade.impl.GeneralBasicDao;
import org.jumao.bi.entites.jrt.financing.EnFiCompanyPo;
import org.jumao.bi.utis.LogUtils;
import org.jumao.bi.utis.RegCalUtils;

import java.util.Comparator;

/**
 */
public class EnFiCompanyPoComp implements Comparator<EnFiCompanyPo> {

    private Logger logger = Logger.getLogger(EnFiCompanyPoComp.class);

    @Override
    public int compare(EnFiCompanyPo o1, EnFiCompanyPo o2) {
        try {
            long l1 = RegCalUtils.getCalendarBy(o1.getCreate_time()).getTimeInMillis();
            long l2 = RegCalUtils.getCalendarBy(o2.getCreate_time()).getTimeInMillis();
            long diff = l2 - l1;

            if (diff > 0) {
                return -1;
            } else if (diff == 0) {
                return 0;
            } else {
                return 1;
            }
        } catch (Exception e) {
            LogUtils.writeLogs(logger, e.getMessage());
            return 0;
        }
    }

}
