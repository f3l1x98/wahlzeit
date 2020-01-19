package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.annotations.PatternInstance;
import org.wahlzeit.model.waterfall.Waterfall;

import java.util.Objects;

@PatternInstance(
        patternName = "Abstract Factory",
        participants = {
                "WaterfallPhoto", "WaterfallPhotoFactory"
        }
)
@Subclass
public class WaterfallPhoto extends Photo {

    /**
     * The corresponding waterfall object, which has all waterfall specific attributes
     */
    protected Waterfall waterfall;

    public WaterfallPhoto() {
        super();
    }

    public WaterfallPhoto(PhotoId myId) {
        super(myId);
    }

    public Waterfall getWaterfall() {
        return waterfall;
    }

    public void setWaterfall(Waterfall waterfall) {
        if(waterfall == null)
            throw new IllegalArgumentException("Unable to set to null");
        this.waterfall = waterfall;
        incWriteCount();
    }

    public boolean isEqual(WaterfallPhoto that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        return waterfall == that.waterfall;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        return this.isEqual((WaterfallPhoto) o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(waterfall);
    }
}
