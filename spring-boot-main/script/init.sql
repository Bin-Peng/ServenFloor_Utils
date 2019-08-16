DROP TABLE IF EXISTS auth_process_control;
create table  auth_process_control (
  pc_auth_trxn_code varchar (7) not null comment '授权交易代码 ',
  pc_step           int          not null comment '执行步骤',
)