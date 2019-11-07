package org.wahlzeit.model;


import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public class WaterfallPhotoFactory extends PhotoFactory {

    private static final Logger log = Logger.getLogger(WaterfallPhotoFactory.class.getName());
    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static WaterfallPhotoFactory instance = null;

    /**
     *
     */
    protected WaterfallPhotoFactory() {
        // do nothing
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance(); // drops result due to getInstance() side-effects
    }

    /**
     * Public singleton access method.
     */
    public static synchronized WaterfallPhotoFactory getInstance() {
        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting generic WaterfallPhotoFactory").toString());
            setInstance(new PhotoFactory());
        }

        return instance;
    }

    /**
     * Method to set the singleton instance of PhotoFactory.
     */
    protected static synchronized void setInstance(WaterfallPhotoFactory photoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initalize WaterfallPhotoFactory twice");
        }

        instance = photoFactory;
    }

    // TODO instance init -> no idea how exactly

    @Override
    public WaterfallPhoto createPhoto() {
        return new WaterfallPhoto();
    }

    @Override
    public WaterfallPhoto createPhoto(PhotoId id) {
        return new WaterfallPhoto(id);
    }

    @Override
    public WaterfallPhoto loadPhoto(PhotoId id) {
        return null;
    }

    @Override
    public PhotoFilter createPhotoFilter() {
        return super.createPhotoFilter();
    }

    @Override
    public PhotoTagCollector createPhotoTagCollector() {
        return super.createPhotoTagCollector();
    }
}
