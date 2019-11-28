DROP TABLE IF EXISTS auth_process_control;
create table  auth_process_control (
  pc_auth_trxn_code      varchar(7)   not null comment '授权交易代码 ',
  pc_step                int           not null comment '执行步骤',
  pc_component_bean      varchar(100) not null comment '授权组件bean',
  pc_error_process_type  varchar(2)   not null comment '授权组价名称',
  pc_goto_step           int                     comment '异常跳转步骤', -- 对应到当前配置的执行步骤阶段
  pc_create_time         timestamp(3)  not null default current_timestamp(3) comment '创建时间',
  pc_create_user         varchar(30)   not null default '' comment '创建用户',
  pc_update_time         timestamp(3)  not null default current_timestamp(3) on update current_timestamp(3) comment '更新时间',
  pc_update_user         varchar(30)             comment '更新用户',
  pc_version             int            not null default '1' comment '版本号',
  primary key (pc_auth_trxn_code, pc_step)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_bin comment='授权业务规则控制表' distributed by duplicate(g1);