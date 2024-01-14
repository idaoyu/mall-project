create table if not exists mall_category
(
    id        varchar(32)          not null comment '类目id'
        primary key,
    parent_id varchar(32)          null comment '父级主键',
    name      varchar(64)          null comment '类目名字',
    del_flag  tinyint(1) default 0 null comment '逻辑删除标记字段',
    sort_num  int                  null comment '排序字段'
)
    comment '商城类目表';

create table if not exists mall_property
(
    id       varchar(32)          not null
        primary key,
    name     varchar(32)          null comment '属性名字',
    del_flag tinyint(1) default 0 null comment '逻辑删除标记'
)
    comment '商城属性表';

create table if not exists mall_property_value
(
    id          varchar(32)          not null
        primary key,
    property_id varchar(32)          null comment '属性id',
    value       varchar(32)          null comment '属性值名称',
    del_flag    tinyint(1) default 0 null comment '逻辑删除标记'
)
    comment '商城属性值表';

create index mall_property_value_property_id_index
    on mall_property_value (property_id);

