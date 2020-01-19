package org.wahlzeit.model.waterfall;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import org.wahlzeit.services.DataObject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity
public class WaterfallType extends DataObject {

    @Id
    Long idLong;

    private String typeName;

    @Ignore
    protected WaterfallType superType = null;
    @Ignore
    protected Set<WaterfallType> subTypes = new HashSet<WaterfallType>();

    /**
     * @methodetype get
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @methodetype set
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
        incWriteCount();
    }

    /**
     * @methodetype get
     */
    public WaterfallType getSuperType() {
        return superType;
    }

    /**
     * @methodetype set
     */
    public void setSuperType(WaterfallType superType) {
        this.superType = superType;
    }

    /**
     * @methodetype get
     */
    public Iterator<WaterfallType> getSubTypeIterator() {
        return subTypes.iterator();
    }


    /**
     * @methodetype constructor
     */
    protected WaterfallType(String typeName) {
        this.typeName = typeName;
        incWriteCount();
    }


    public Waterfall createInstance() {
        return new Waterfall(this);
    }

    public void addSubType(WaterfallType wt) {
        assert (wt != null);
        wt.setSuperType(this);
        subTypes.add(wt);
    }

    public boolean hasInstance(Waterfall waterfall) {
        assert (waterfall != null);
        if (waterfall.getType() == this) {
            return true;
        }
        for (WaterfallType type : subTypes) {
            if (type.hasInstance(waterfall)) {
                return true;
            }
        }
        return false;
    }

    /// Can be done this easy, because each waterfallType is uniquely identified by its typeName
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WaterfallType)) return false;
        WaterfallType that = (WaterfallType) o;
        return typeName.compareToIgnoreCase(that.typeName) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName);
    }
}
