package com.qhh.network.custom;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author admin
 * @version $Rev$
 * @time 2018/9/12 17:29
 * @des ${TODO}
 * @packgename com.qhh.network.custom
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class ParameterizedTypeImpl implements ParameterizedType {

    private final Class raw;
    private final Type[] args;

    public ParameterizedTypeImpl(Class raw, Type[] args) {
        this.raw = raw;
        this.args = args != null ? args : new Type[0];
    }

    @Override
    public Type[] getActualTypeArguments() {

        return args;
    }

    @Override
    public Type getRawType() {
        return raw;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
