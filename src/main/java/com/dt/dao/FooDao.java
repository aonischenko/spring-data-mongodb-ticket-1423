package com.dt.dao;

import com.dt.model.Foo;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

public interface FooDao extends CrudRepository<Foo, ObjectId>, FooDaoCustom {

}
