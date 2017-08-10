package org.jumao.bi.dao.trade.impl;

import org.jumao.bi.utis.Verifier;

/**
 */
public class GeneralDaoHelper {

    protected String checkFirstCondi(String firstCondi) {
        if (Verifier.isEffectiveStr(firstCondi)) {
            firstCondi += " and ";
        }
        return firstCondi;
    }


    protected String getNumTargetSql(String numTarget) {
        if (numTarget.startsWith("count") || numTarget.startsWith("sum") || numTarget.startsWith("avg")) {
            return numTarget;
        } else {
            return "count(" + numTarget + ")";
        }
    }



}
