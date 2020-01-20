package org.wahlzeit.model.waterfall;

import org.wahlzeit.services.ObjectManager;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class WaterfallManager extends ObjectManager {

    private static final Logger log = Logger.getLogger(WaterfallManager.class.getName());

    protected static WaterfallManager instance = null;

    // root waterfallType which has no parent
    protected WaterfallType rootType = new WaterfallType("Waterfall");
    protected Map<String, WaterfallType> waterfallTypes = new HashMap<String, WaterfallType>();
    protected Map<WaterfallId, Waterfall> waterfalls = new HashMap<WaterfallId, Waterfall>();

    private WaterfallManager() {
        waterfallTypes.put(rootType.getTypeName(), rootType);
    }

    public static WaterfallManager getInstance() {
        if (instance == null) {
            instance = new WaterfallManager();
        }
        return instance;
    }


    public WaterfallType createWaterfallType(String typeName) {
        WaterfallType newType;
        if(waterfallTypes.containsKey(typeName)) {
            newType = getWaterfallType(typeName);
        } else {
            newType = new WaterfallType(typeName);
            waterfallTypes.put(newType.getTypeName(), newType);
        }
        return newType;
    }

    public Waterfall createWaterfall(String typeName) {
        assertIsValidWaterfallTypeName(typeName);
        WaterfallType wt = getWaterfallType(typeName);
        Waterfall result = wt.createInstance();
        waterfalls.put(result.getId(), result);
        return result;
    }

    public Waterfall getWaterfall(WaterfallId id) {
        return waterfalls.get(id);
    }

    private WaterfallType getWaterfallType(String typeName) {
        return waterfallTypes.get(typeName);
    }

    private void assertIsValidWaterfallTypeName(String typeName) {
        if(!waterfallTypes.containsKey(typeName))
            throw new AssertionError("No WaterfallType with given typeName found!");
    }
}
