package org.wahlzeit.model;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;
import org.junit.*;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class WaterfallPhotoManagerTest {

    private static final String PICTURES_PATH = "pictures";

    @ClassRule
    public static RuleChain ruleChain = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());

    @Test
    public void testHasNotPhoto() {
        WaterfallPhoto photo = new WaterfallPhoto();

        boolean ret = WaterfallPhotoManager.getInstance().hasPhoto(photo.getId());

        assertFalse(ret);
    }

    @Test
    public void testGetPhotoFromID() {
        Photo photo;

        try {
            Image image = getFirstImage();
            WaterfallPhotoManager wm = WaterfallPhotoManager.getInstance();
            photo = wm.createPhoto("BLABLABLUB", image);

            Photo ret = wm.getPhotoFromId(photo.getId());

            assertNotNull(ret);
            assertEquals(photo, ret);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCreatePhoto() {

        Image image = getFirstImage();

        assertNotNull(image);

        try {
            Photo photo = WaterfallPhotoManager.getInstance().createPhoto("BLABLABLUB", image);

            assertNotNull(photo);
            assertEquals(photo.getClass(), WaterfallPhoto.class);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);      // if this fails, it means an exception was thrown (which shouldn't happen)
        }
    }


    private Image getFirstImage() {
        try {
            URL url = getClass().getClassLoader().getResource(PICTURES_PATH);
            File folder = new File(url.getPath());

            File photoDirFile = new File(folder.getAbsolutePath());
            FileFilter photoFileFilter = file -> file.getName().endsWith(".jpg");
            File[] photoFiles = photoDirFile.listFiles(photoFileFilter);

            if(photoFiles == null || photoFiles.length == 0)
                return null;

            return getImageFromFile(photoFiles[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Image getImageFromFile(File file) throws IOException {
        String photoPath = file.getAbsolutePath();
        return ImagesServiceFactory.makeImage(Files.readAllBytes(Paths.get(photoPath)));
    }
}
