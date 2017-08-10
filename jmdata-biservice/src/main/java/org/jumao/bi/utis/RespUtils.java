package org.jumao.bi.utis;

import clojure.lang.Obj;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.jumao.bi.constant.ServiceConst;
import org.jumao.bi.entites.ResponseResult;
import org.jumao.bi.entites.trade.register.ChartResp;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.enums.AUOType;
import org.jumao.bi.utis.enums.VUOType;
import org.jumao.bi.utis.exceptions.FailedOperationException;
import org.jumao.bi.utis.exceptions.ParamErrorException;
import org.jumao.bi.utis.exceptions.WrapEx;

import javax.ws.rs.core.Response;
import java.lang.reflect.Method;

/**
 * Created by kartty on 2017/5/2.
 */
public class RespUtils {

    private static Logger logger = Logger.getLogger(RespUtils.class);

    private static int SUCCESS_CODE_INT = Integer.parseInt(ServiceConst.SUCCESS_CODE);
    private static int SERVER_ERROR_CODE_INT = Integer.parseInt(ServiceConst.SERVER_ERROR_CODE);
    private static int PARAM_ERR_INT = Key.Num601;
    private static String PARAM_ERR = "601";

    /**
     */
    public static Response checkExAndResp(Exception e) {
        if (e instanceof FailedOperationException) {
            FailedOperationException foe = (FailedOperationException) e;
            String errMsg = foe.getFailedResponse();
            LogUtils.writeLogs(logger, errMsg);
            ResponseResult rr = new ResponseResult(foe.getCode(), errMsg);

            return Response.status(SUCCESS_CODE_INT).entity(rr).build();

        } else if (e instanceof ParamErrorException) {
            ParamErrorException pee = (ParamErrorException) e;
            String errMsg = pee.toString();
            ResponseResult rr = new ResponseResult(PARAM_ERR, errMsg);
            LogUtils.writeLogs(logger, errMsg);

            return Response.status(SUCCESS_CODE_INT).entity(rr).build();

        } else {
            String errMsg = e.getMessage();
            if (errMsg != null && errMsg.length() > Key.Num256) {
                errMsg = errMsg.substring(0, Key.Num256);
            }
            LogUtils.writeLogs(logger, errMsg);
            ResponseResult rr = new ResponseResult(ServiceConst.SERVER_ERROR_CODE, errMsg);

            return Response.status(SUCCESS_CODE_INT).entity(rr).build();
        }
    }

    /**
     */
    public static ChartResp getSuccessChartResp() {
        ChartResp cp = new ChartResp();
        cp.setStatus(new ResponseResult(ServiceConst.SUCCESS_CODE, ServiceConst.SUCCESS_MSG));
        return cp;
    }

    /**
     */
    public static void checkDateRange(String startDate, String endDate) throws Exception {
        if (!ValidUtils.isCorrectDateRange(startDate, endDate)) {
            throw new WrapEx("非法的日期格式或日期范围!");
        }
    }
    /**
     */
    public static void checkPlatform(String platform) throws Exception {
        if (!Verifier.isEffectiveStr(platform)) {
            throw new WrapEx("platform 不是有效字符串！");
        }
    }
    /**
     */
    public static int checkEnumType(String typeStr, Class enumClass) throws Exception {
        if (!NumberUtils.isDigits(typeStr)) {
            throw new WrapEx("type 不是数字！");
        }
        int type = Integer.parseInt(typeStr);
//        String name = AUOType.getNameByType(type);
        Method method = enumClass.getMethod("getNameByType", Integer.class);
        Object name = method.invoke(null, type);
        if (name == null) {
            throw new WrapEx("type 不存在！");
        } else {
            return type;
        }
    }
    /**
     */
//    public static int checkVUOType(String typeStr) throws Exception {
//        if (!NumberUtils.isDigits(typeStr)) {
//            throw new Exception("type 不是数字！");
//        }
//        int type = Integer.parseInt(typeStr);
//        String name = VUOType.getNameByType(type);
//        if (name == null) {
//            throw new Exception("type 不存在！");
//        } else {
//            return type;
//        }
//    }
    /**
     */
    public static void checkIndustryId(String industryId) throws Exception {
        if (!Verifier.isEffectiveStr(industryId)) {
            throw new WrapEx("industryId 不是有效字符串！");
        }
    }


}
