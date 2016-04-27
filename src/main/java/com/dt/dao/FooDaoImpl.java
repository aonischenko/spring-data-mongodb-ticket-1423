package com.dt.dao;

import com.dt.model.Bar;
import com.dt.model.Foo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class FooDaoImpl implements FooDaoCustom {

    @Autowired
    private MongoTemplate mongoOps;

    @Override
    public Foo updateBar(ObjectId id, Bar bar) {
        Query query = Query.query(Criteria.where("_id").is(id));
        Update update = new Update().set("bar", bar);
        mongoOps.updateFirst(query, update, Foo.class);

        return mongoOps.findOne(query, Foo.class);
    }

    @Override
    public Foo updateBarMap(ObjectId id, Bar bar) {
        Query query = Query.query(Criteria.where("_id").is(id));
        // check for bar == null is omitted
        Update update = new Update()
                .set("bar.baz", bar.getBaz())
                .set("bar.bazMap", bar.getBazMap());
        mongoOps.updateFirst(query, update, Foo.class);

        return mongoOps.findOne(query, Foo.class);
    }

    @Override
    public Foo updateBarMapTricky(ObjectId id, Bar bar) {
        Query query = Query.query(Criteria.where("_id").is(id));
        // check for bar == null is omitted

        Update update = new Update().set("bar.baz", bar.getBaz());

        DBObject dbObject = new BasicDBObject();
        mongoOps.getConverter().write(bar.getBazMap(), dbObject);
        dbObject.removeField("_class");
        update.set("bar.bazMap", dbObject);

        mongoOps.updateFirst(query, update, Foo.class);

        return mongoOps.findOne(query, Foo.class);
    }
}
