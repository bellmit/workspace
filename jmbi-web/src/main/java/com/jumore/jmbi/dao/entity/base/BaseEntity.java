/**
 * 
 */
package com.jumore.jmbi.dao.entity.base;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/** 
*
* @author: luochao
* @since: 2016年8月3日  下午3:23:24
* @history:
*/
public class BaseEntity {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
