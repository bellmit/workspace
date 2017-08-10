package org.jumao.bi.entites.trade.operate;

import org.jumao.bi.entites.CommonResponse;

import java.io.Serializable;

public class TrafficName extends CommonResponse implements Serializable {

    private static final long serialVersionUID = -5244773794123014069L;

    private String Name;
//    private String Value;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}