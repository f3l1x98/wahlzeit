package org.wahlzeit.model;


import com.google.appengine.api.images.Image;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import org.wahlzeit.services.LogBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class WaterfallPhotoManager extends PhotoManager {

    private static final Logger log = Logger.getLogger(WaterfallPhotoManager.class.getName());

    /**
     *
     */
    public WaterfallPhotoManager() {
        super();
    }

    @Override
    public WaterfallPhoto getPhotoFromId(PhotoId id) {
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
    public void loadPhotos() {
        Collection<WaterfallPhoto> existingPhotos = ObjectifyService.run(new Work<Collection<WaterfallPhoto>>() {
            @Override
            public Collection<WaterfallPhoto> run() {
                Collection<WaterfallPhoto> existingPhotos = new ArrayList<>();
                readObjects(existingPhotos, WaterfallPhoto.class);
                return existingPhotos;
            }
        });

        for (WaterfallPhoto photo : existingPhotos) {
            if (!doHasPhoto(photo.getId())) {
                log.config(LogBuilder.createSystemMessage().
                        addParameter("Load WaterfallPhoto with ID", photo.getIdAsString()).toString());
                try {
                    loadScaledImages(photo);
                } catch (IOException e) {
                    log.warning(LogBuilder.createSystemMessage().addMessage("Failed to load Image for Photo with ID: " + photo.getId().stringValue).toString());
                }
                doAddPhoto(photo);
            } else {
                log.config(LogBuilder.createSystemMessage().
                        addParameter("Already loaded WaterfallPhoto", photo.getIdAsString()).toString());
            }
        }

        log.info(LogBuilder.createSystemMessage().addMessage("All photos loaded.").toString());
    }

    @Override
    protected WaterfallPhoto doGetPhotoFromId(PhotoId id) {
        return (WaterfallPhoto) super.doGetPhotoFromId(id);
    }

    @Override
    public WaterfallPhoto getVisiblePhoto(PhotoFilter filter) {
        return (WaterfallPhoto) super.getVisiblePhoto(filter);
    }

    @Override
    public WaterfallPhoto createPhoto(String filename, Image uploadedImage) throws Exception {
        PhotoId id = PhotoId.getNextId();
        WaterfallPhoto result = (WaterfallPhoto) PhotoUtil.createPhoto(filename, id, uploadedImage);
        addPhoto(result);
        return result;
    }
}
