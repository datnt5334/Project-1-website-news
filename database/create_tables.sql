use
news_website;

create table role
(
    id           bigint       not null primary key auto_increment,
    name         varchar(150) not null,
    code         varchar(150) not null,
    createddate  timestamp null,
    modifieddate timestamp null,
    createdby    varchar(255) null,
    modifiedby   varchar(255) null
);

create table user
(
    id           bigint       not null primary key auto_increment,
    username     varchar(150) not null,
    password     varchar(255) not null,
    fullname     varchar(255) null,
    status       tinyint      not null,
    roleid       bigint       not null,
    createddate  timestamp null,
    modifieddate timestamp null,
    createdby    varchar(255) null,
    modifiedby   varchar(255) null
);

alter table user
    add constraint fk_user_role foreign key (roleid) references role (id);

create table news
(
    id               bigint not null primary key auto_increment,
    title            varchar(255) null,
    thumbnail        mediumblob null,
    shortdescription text null,
    content          text null,
    categoryid       bigint not null,
    createddate      timestamp null,
    modifieddate     timestamp null,
    createdby        varchar(255) null,
    modifiedby       varchar(255) null
);

create table categories
(
    id           bigint       not null primary key auto_increment,
    name         varchar(255) not null,
    code         varchar(255) not null,
    createddate  timestamp null,
    modifieddate timestamp null,
    createdby    varchar(255) null,
    modifiedby   varchar(255) null
);

alter table news
    add constraint fk_news_categories foreign key (categoryid) references categories (id);

create table comment
(
    id           bigint not null primary key auto_increment,
    content      text   not null,
    user_id      bigint not null,
    news_id      bigint not null,
    createddate  timestamp null,
    modifieddate timestamp null,
    createdby    varchar(255) null,
    modifiedby   varchar(255) null
);

alter table comment
    add constraint fk_comment_user foreign key (user_id) references user (id);
alter table comment
    add constraint fk_comment_news foreign key (news_id) references news (id);