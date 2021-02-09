create table IF NOT EXISTS message (
    id serial primary key, 
    value varchar(50), 
    ts timestamp
);