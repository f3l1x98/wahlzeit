package org.wahlzeit.model;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class WaterfallPhotoManager extends PhotoManager {

    /**
     *
     */
    protected static final WaterfallPhotoManager instance = new WaterfallPhotoManager();

    private static final Logger log = Logger.getLogger(WaterfallPhotoManager.class.getName());

    /**
     * In-memory cache for photos
     */
    protected Map<PhotoId, WaterfallPhoto> photoCache = new HashMap<>();

    /**
     *
     */
    public WaterfallPhotoManager() {
        photoTagCollector = WaterfallPhotoFactory.getInstance().createPhotoTagCollector();
    }

    @Override
    public WaterfallPhoto getPhotoFromId(PhotoId id) {
        //return super.getPhotoFromId(id); //TODO not sure if that can be used
        if (id == null) {
            return null;
        }

        WaterfallPhoto result = doGetPhotoFromId(id);

        if (result == null) {
            result = WaterfallPhotoFactory.getInstance().loadPhoto(id);
            if (result != null) {
                doAddPhoto(result);
            }
        }

        return result;
    }

    @Override
    protected WaterfallPhoto doGetPhotoFromId(PhotoId id) {
        return photoCache.get(id);
    }

    @Override
    public WaterfallPhoto getVisiblePhoto(PhotoFilter filter) {
        filter.generateDisplayablePhotoIds();
        return getPhotoFromId(filter.getRandomDisplayablePhotoId());
    }
}
