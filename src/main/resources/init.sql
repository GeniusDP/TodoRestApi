drop table if exists todos;
create table todos
(
    todo_id bigserial,
    title text not null,
    body text not null,
    creation_date_time timestamp default now() not null,
    last_update_date_time timestamp default now() not null
);

create unique index table_name_title_uindex
    on todos (title);

create unique index table_name_todo_id_uindex
    on todos (todo_id);

alter table todos
    add constraint table_name_pk
        primary key (todo_id);

insert into todos (title, body)
values ('How did i start learning Spring', 'Some beautiful text');

insert into todos (title, body)
values ('Как я учился в школе', 'Да знаете, на отлична!');