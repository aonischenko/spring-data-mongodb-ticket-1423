package com.dt.dao;

import com.dt.model.Bar;
import com.dt.model.Foo;
import org.bson.types.ObjectId;

public interface FooDaoCustom {

    Foo updateBar(ObjectId id, Bar bar);

    Foo updateBarMap(ObjectId id, Bar bar);

    Foo updateBarMapTricky(ObjectId id, Bar bar);

}
