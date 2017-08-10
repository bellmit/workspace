package com.jumore.jmbi.common.util;

import java.util.List;

import com.jumore.jmbi.common.exceptions.RemoteGenericException;
import com.jumore.ucenter.dubbo.service.api.base.dto.PageResponseDTO;
import com.jumore.ucenter.dubbo.service.api.base.dto.ResponseDTO;

/**
 * 
 *
 * @author: Lao Yang
 * @since: 2016年6月28日 下午4:21:13
 * @history:
 */
public class RemoteObjectConvertor {

    /**
     * convert
     * 
     * @author Administrator
     * @date 2017年6月5日 上午9:26:59
     * @param remoteResponseDTO
     * @return
     * @throws RemoteGenericException
     */
    public static <T> T convert(ResponseDTO<T> remoteResponseDTO) throws RemoteGenericException {
        if (remoteResponseDTO.isSuccess()) {
            return remoteResponseDTO.getResult();
        } else if (remoteResponseDTO.isNoData()) {
            return null;
        } else {
            throw new RemoteGenericException(remoteResponseDTO.getCode().name(), remoteResponseDTO.getDesc());
        }
    }

    /**
     * handleIfSuccess:处理异常.
     * 
     * @author Administrator
     * @date 2017年6月5日 上午9:28:15
     * @param remoteResponseDTO
     * @throws RemoteGenericException
     */
    public static void handleIfSuccess(ResponseDTO<?> remoteResponseDTO) throws RemoteGenericException {
        if (!remoteResponseDTO.isSuccess()) {
            throw new RemoteGenericException(remoteResponseDTO.getCode().name(), remoteResponseDTO.getDesc());
        }
    }

    /**
     * convertList:得到page里的list数据.
     * 
     * @author Administrator
     * @date 2017年6月5日 上午9:29:05
     * @param remoteResponseDTOs
     * @return
     * @throws RemoteGenericException
     */
    public static <T> List<T> convertList(PageResponseDTO<List<T>> remoteResponseDTOs) throws RemoteGenericException {
        if (remoteResponseDTOs.isSuccess()) {
            return remoteResponseDTOs.getResult();
        } else {
            throw new RemoteGenericException(remoteResponseDTOs.getCode().name(), remoteResponseDTOs.getDesc());
        }
    }
}
