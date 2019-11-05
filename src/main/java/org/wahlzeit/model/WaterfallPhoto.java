package org.wahlzeit.model;

import java.util.Objects;

public class WaterfallPhoto extends Photo {

    /**
     * The Number of stages inside the waterfall stages >= 1
     */
    protected int stages;

    public WaterfallPhoto() {
        super();
    }

    public WaterfallPhoto(PhotoId myId) {
        super(myId);
    }

    public int getStages() {
        return stages;
    }

    public void setStages(int stages) {
        if(stages < 1)
            throw new IllegalArgumentException("A Waterfall has at least one stage!");
        this.stages = stages;
    }

    public boolean isEqual(WaterfallPhoto that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        return stages == that.stages;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        return this.isEqual((WaterfallPhoto) o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stages);
    }
}
