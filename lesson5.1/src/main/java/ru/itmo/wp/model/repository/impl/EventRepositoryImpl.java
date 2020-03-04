package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.repository.EventRepository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class EventRepositoryImpl extends AbstractRepository<Event> implements EventRepository {


    @Override
    protected Event toThis(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        //not necessary
       return null;
    }

    @Override
    protected String getEntityName() {
        return "Event";
    }

    @Override
    public void save(Event event) {
        save(event, List.of(Map.entry("userId", event.getUserId()),
                Map.entry("type", event.getType())));
    }
}
