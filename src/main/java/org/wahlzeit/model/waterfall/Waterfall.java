package org.wahlzeit.model.waterfall;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import org.wahlzeit.services.DataObject;

import java.util.Objects;

@Entity
public class Waterfall extends DataObject {

    protected WaterfallId id = null;

    @Id
    Long idLong;

    protected WaterfallType waterfallType = null;

    /**
     * The Number of stages inside the waterfall stages >= 1
     */
    protected int stages;

    /**
     * @methodetype constructor
     */
    protected Waterfall(WaterfallType wt) {
        id = WaterfallId.getNextId();
        waterfallType = wt;
        stages = 1;
        incWriteCount();
    }

    /**
     * @methodetype get
     */
    public int getStages() {
        return stages;
    }

    /**
     * @methodetype set
     */
    public void setStages(int stages) {
        if(stages < 1)
            throw new IllegalArgumentException("A waterfall has at least one stage!");
        this.stages = stages;
        incWriteCount();
    }

    /**
     * @methodetype get
     */
    public WaterfallType getType() {
        return waterfallType;
    }

    /**
     * @methodetype get
     */
    public WaterfallId getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Waterfall)) return false;
        Waterfall waterfall = (Waterfall) o;
        return stages == waterfall.stages &&
                id.isEqual(waterfall.id) &&
                waterfallType.getTypeName().compareToIgnoreCase(waterfall.waterfallType.getTypeName()) == 0;           // each waterfallType is unique by typeName
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, waterfallType, stages);
    }
}
