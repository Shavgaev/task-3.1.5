DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

create table roles
(
    id   int auto_increment
        primary key,
    name varchar(255) null
);

create table users
(
    id        int auto_increment
        primary key,
    age       int          null,
    last_name varchar(255) null,
    name      varchar(255) null,
    password  varchar(255) null,
    username  varchar(255) null,
    constraint UK_r43af9ap4edm43mmtq01oddj6
--      Это имя для ограничения. Оно используется для идентификации ограничения и может быть удобным для отслеживания его в будущем.
        unique (username)
-- Это ограничение типа UNIQUE, которое применяется к столбцу. Ограничение UNIQUE гарантирует, что все значения в столбце username будут уникальными, то есть не могут повторяться. Это полезно, например, для обеспечения уникальности идентификационных имен пользователей в таблице, чтобы избежать конфликтов и обеспечить целостность данных.
);

create table users_roles
(
    user_id  int not null,
    roles_id int not null,
    primary key (user_id, roles_id),
    constraint FK2o0jvgh89lemvvo17cbqvdxaa
        foreign key (user_id) references users (id),
    constraint FKa62j07k5mhgifpp955h37ponj
        foreign key (roles_id) references roles (id)
);