/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model.waterfall;

import java.io.Serializable;
import java.util.Random;

/**
 * Copy of PhotoId
 */
public class WaterfallId implements Serializable {

    protected static int currentId = 0;

    public static final int BUFFER_SIZE_INCREMENT = 64;

    public static final WaterfallId NULL_ID = new WaterfallId(0);

    protected static WaterfallId[] ids = new WaterfallId[BUFFER_SIZE_INCREMENT];

    public static final int ID_START = getFromString("x1abz") + 1;

    protected static Random randomNumber = new Random(System.currentTimeMillis());

    private WaterfallId() {}


    public static int getCurrentIdAsInt() {
        return currentId;
    }

    public static synchronized void setCurrentIdFromInt(int id) {
        currentId = id;
        ids = new WaterfallId[currentId + BUFFER_SIZE_INCREMENT];
        ids[0] = NULL_ID;
    }

    public static synchronized int getNextIdAsInt() {
        currentId += 1;
        if (currentId >= ids.length) {
            WaterfallId[] nids = new WaterfallId[currentId + BUFFER_SIZE_INCREMENT];
            System.arraycopy(ids, 0, nids, 0, currentId);
            ids = nids;
        }
        return currentId;
    }

    public static WaterfallId getIdFromInt(int id) {
        if ((id < 0) || (id > currentId)) {
            return NULL_ID;
        }

        // @FIXME http://en.wikipedia.org/wiki/Double-checked_locking
        WaterfallId result = ids[id];
        if (result == null) {
            synchronized (ids) {
                result = ids[id];
                if (result == null) {
                    result = new WaterfallId(id);
                    ids[id] = result;
                }
            }
        }

        return result;
    }

    public static WaterfallId getIdFromString(String id) {
        return getIdFromInt(getFromString(id));
    }

    public static WaterfallId getNextId() {
        return getIdFromInt(getNextIdAsInt());
    }

    public static WaterfallId getRandomId() {
        int max = getCurrentIdAsInt() - 1;
        int id = randomNumber.nextInt();
        id = (id == Integer.MIN_VALUE) ? id++ : id;
        id = (Math.abs(id) % max) + 1;
        return getIdFromInt(id);
    }

    protected int value = 0;
    protected String stringValue = null;

    protected WaterfallId(int myValue) {
        value = myValue;
        stringValue = getFromInt(myValue);
    }

    public boolean equals(Object o) {
        // @FIXME

        if (!(o instanceof WaterfallId)) {
            return false;
        }

        WaterfallId pid = (WaterfallId) o;
        return isEqual(pid);
    }

    public boolean isEqual(WaterfallId other) {
        return other.value == value;
    }

    /**
     * @methodtype get
     */
    public int hashCode() {
        return value;
    }

    public boolean isNullId() {
        return this == NULL_ID;
    }

    public int asInt() {
        return value;
    }

    public String asString() {
        return stringValue;
    }

    public static String getFromInt(int id) {
        StringBuffer result = new StringBuffer(10);

        id += ID_START;
        for (; id > 0; id = id / 36) {
            char letterOrDigit;
            int modulus = id % 36;
            if (modulus < 10) {
                letterOrDigit = (char) ((int) '0' + modulus);
            } else {
                letterOrDigit = (char) ((int) 'a' - 10 + modulus);
            }
            result.insert(0, letterOrDigit);

        }

        return "x" + result.toString();
    }

    public static int getFromString(String value) {
        int result = 0;
        for (int i = 1; i < value.length(); i++) {
            int temp = 0;
            char letterOrDigit = value.charAt(i);
            if (letterOrDigit < 'a') {
                temp = (int) letterOrDigit - '0';
            } else {
                temp = 10 + (int) letterOrDigit - 'a';
            }
            result = result * 36 + temp;
        }

        result -= ID_START;
        if (result < 0) {
            result = 0;
        }

        return result;
    }

}

