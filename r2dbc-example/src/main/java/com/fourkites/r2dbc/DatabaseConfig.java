package com.fourkites.r2dbc;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.transaction.ReactiveTransactionManager;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Row;

@Configuration
public class DatabaseConfig extends AbstractR2dbcConfiguration {

    @Autowired
    ConnectionFactory connectionFactory;

    @Override
    public ConnectionFactory connectionFactory() {
        PostgresqlConnectionConfiguration configuration = PostgresqlConnectionConfiguration.builder()
                .database("messages").host("127.0.0.1").username("vijayveeraraghavanv").password("").build();
        return new PostgresqlConnectionFactory(configuration);
    }

    @Bean
    ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        initializer.setDatabasePopulator(populator);
        return initializer;
    }

    @Override
    // this is required if there has to be explicit mapping for Java fields with the
    // db columns
    protected List<Object> getCustomConverters() {
        return List.of(new MessageReadingConverter(), new DateFormatter());
    }

}

class MessageReadingConverter implements Converter<Row, Message> {
    @Override
    public Message convert(Row row) {
        return Message.builder().id(row.get("id", Long.class)).value(row.get("value", String.class))
                .ts(row.get("ts", LocalDateTime.class)).build();
    }
}

class DateFormatter implements Converter<Row, LocalDateTime> {
    @Override
    public LocalDateTime convert(Row row) {
        return row.get("ts", Date.class).toLocalDate().atStartOfDay();
    }
}