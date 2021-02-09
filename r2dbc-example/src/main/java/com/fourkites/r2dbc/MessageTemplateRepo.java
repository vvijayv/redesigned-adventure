package com.fourkites.r2dbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class MessageTemplateRepo {

    @Autowired
    R2dbcEntityTemplate template;

    public Flux<Message> findByMessageValue(RequestQuery requestQuery) {
        Criteria criteria = createQuery(requestQuery);
        Query query = Query.query(criteria);
        return template.select(Message.class).matching(query).all();
    }

    private Criteria createQuery(RequestQuery query) {
        List<Criteria> criterias = new ArrayList<>();
        if (query.getId() > 0)
            criterias.add(Criteria.where("id").is(query.getId()));
        if (query.getValue() != null) {
            criterias.add(Criteria.where("value").is(query.getValue()));
        }
        if (query.getFrom() != null && query.getTo() != null) {
            criterias.add(Criteria.where("ts").between(query.getFrom(), query.getTo()));
        }
        return Criteria.from(criterias);
    }

}
