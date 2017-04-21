chanjetedu-eps-dal
data access layer 数据访问层

=================

dal 主要使用 mybatis实现，使用 velocity 模板编写 sql。
简单sql可以写在代码中，复杂sql可以写在配置文件当中。（类似 mybatis-freemaker 的实现，需要自己重新实现）

连接池暂时使用 druid

已经加入了事务管理


暂存代码

`
ALTER TABLE eps.${table} ADD enabled BOOLEAN DEFAULT true NOT NULL;
ALTER TABLE eps.${table} ADD creater VARCHAR(16) DEFAULT 'DB' NOT NULL;
ALTER TABLE eps.${table} ADD create_time TIMESTAMP DEFAULT now() NOT NULL;
ALTER TABLE eps.${table} ADD updater VARCHAR(16) NULL;
ALTER TABLE eps.${table} ADD update_time TIMESTAMP NULL;
`
