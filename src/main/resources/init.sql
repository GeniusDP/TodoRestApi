CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
drop table if exists todos;
create table todos
(
    todo_id uuid default uuid_generate_v4(),
    title text not null,
    body text not null,
    done bool not null default false,
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

insert into todos (title, body) values ('Innovative maximized synergy', 'Versatile exuding throughput');
insert into todos (title, body) values ('Monitored hybrid architecture', 'Multi-channelled user-facing concept');
insert into todos (title, body) values ('Right-sized disintermediate architecture', 'Horizontal intangible framework');
insert into todos (title, body) values ('User-centric heuristic algorithm', 'Self-enabling responsive task-force');
insert into todos (title, body) values ('Switchable object-oriented website', 'Function-based asymmetric internet solution');
insert into todos (title, body) values ('Monitored holistic firmware', 'Persistent executive benchmark');
insert into todos (title, body) values ('Adaptive maximized leverage', 'Public-key client-driven application');
insert into todos (title, body) values ('Synergistic global strategy', 'Business-focused optimizing challenge');
insert into todos (title, body) values ('Mandatory composite functionalities', 'Grass-roots maximized monitoring');
insert into todos (title, body) values ('Open-source full-range help-desk', 'Virtual systematic concept');