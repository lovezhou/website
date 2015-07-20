-- create 表空间
create tablespace website  datafile 'E:\app\Administrator\oradata\orcl\website.dbf' size 2000m;
create user  website identified by website default tablespace website;
grant connect,resource to website;
grant create view to website;


-- Create table
create table T_SYS_DICT
(
  fid          VARCHAR2(20) not null,
  fname        VARCHAR2(40),
  fcode        VARCHAR2(40),
  fremark      VARCHAR2(200),
  fcreate_time DATE
)
tablespace WEBSITE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_SYS_DICT
  is '数据字典';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_SYS_DICT
  add constraint PK_T_SYS_DICT_ID primary key (FID)
  using index 
  tablespace WEBSITE
  pctfree 10
  initrans 2
  maxtrans 255;

  
  -- Create table
create table T_SYS_DICT_DETAIL
(
  fid          VARCHAR2(20) not null,
  fdict_id     VARCHAR2(20),
  fcode        VARCHAR2(40),
  fname        VARCHAR2(40),
  forder       NUMBER(20),
  fremark      VARCHAR2(200),
  fcreate_time DATE
)
tablespace WEBSITE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_SYS_DICT_DETAIL
  is '数据字典详情';
-- Add comments to the columns 
comment on column T_SYS_DICT_DETAIL.fdict_id
  is '与t_sys_dict表id关联';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_SYS_DICT_DETAIL
  add constraint PK_T_SYS_DICT_DETAIL_ID primary key (FID)
  using index 
  tablespace WEBSITE
  pctfree 10
  initrans 2
  maxtrans 255;



