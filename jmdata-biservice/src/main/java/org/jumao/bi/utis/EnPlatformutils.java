package org.jumao.bi.utis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen qian
 */
public class EnPlatformutils {

    public static Map<String, String> idEnNameMap = new HashMap<String, String>();

    static {
        idEnNameMap.put("100101", "JUMOREGlobal");
        idEnNameMap.put("100201", "JUMOREChemical");
        idEnNameMap.put("100301", "JUMORENon-ferrous");
        idEnNameMap.put("100401", "EtransMore");
        idEnNameMap.put("100701", "JUMORECoal");
        idEnNameMap.put("100801", "JUMORESteel");
        idEnNameMap.put("100901", "JUMOREMineral");
        idEnNameMap.put("101001", "JUMOREAgro");
        idEnNameMap.put("101101", "JUMOREIndustrial");
        idEnNameMap.put("101201", "JUMORECG");
        idEnNameMap.put("101301", "JUMOREMachinery");
        idEnNameMap.put("101401", "JUMOREFood");
        idEnNameMap.put("101501", "JUMOREFinance");
        idEnNameMap.put("101601", "JUMOREBigdata");
        idEnNameMap.put("101701", "JUMORECertification");
        idEnNameMap.put("101801", "JUMORETech");
        idEnNameMap.put("101901", "JUMOREConsultancy");
        idEnNameMap.put("102201", "JUMOREPetro");
    }

}
