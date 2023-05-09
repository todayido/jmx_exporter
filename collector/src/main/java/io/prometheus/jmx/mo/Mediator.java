//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.prometheus.jmx.mo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class Mediator {
    public static List<Map> mediatorList = new ArrayList();

    public Mediator() {
    }

    public void Register(String gaugeName, Object classInstance, String fieldName, String labelName) {
        Map map = new HashMap();
        map.put("gaugeName", gaugeName);
        map.put("classInstance", classInstance);
        map.put("fieldName", fieldName);
        map.put("labelName", Arrays.asList(labelName));
        mediatorList.add(map);
    }

    public static List<Map> matrix() {
        ArrayList mediatorListResult = new ArrayList();

        try {
            Map mediatorMap;
            for(Iterator var1 = mediatorList.iterator(); var1.hasNext(); mediatorListResult.add(mediatorMap)) {
                mediatorMap = (Map)var1.next();
                Object classInstance = mediatorMap.get("classInstance");
                String fieldName = (String)mediatorMap.get("fieldName");
                Field field = mediatorMap.get("classInstance").getClass().getField(fieldName);
                String typeName = field.getGenericType().getTypeName();
                if (typeName.contains("double")) {
                    mediatorMap.put("value", field.get(classInstance));
                } else if (typeName.contains("int")) {
                    mediatorMap.put("value", (double)field.getInt(classInstance));
                } else if (typeName.contains("List")) {
                    Collection collection = (Collection)field.get(classInstance);
                    mediatorMap.put("value", collection == null ? 0.0D : Double.valueOf((double)collection.size()));
                } else if (typeName.contains("Map")) {
                    Map map = (Map)field.get(classInstance);
                    mediatorMap.put("value", map == null ? 0.0D : Double.valueOf((double)map.size()));
                } else {
                    mediatorMap.put("value", 0.0D);
                }
            }

            return mediatorListResult;
        } catch (IllegalAccessException | NoSuchFieldException var8) {
            var8.printStackTrace();
            return mediatorListResult;
        }
    }
}
