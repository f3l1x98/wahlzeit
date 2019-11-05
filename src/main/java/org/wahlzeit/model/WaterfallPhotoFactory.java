package org.wahlzeit.model;


public class WaterfallPhotoFactory extends PhotoFactory {

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
}
