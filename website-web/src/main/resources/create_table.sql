-- create 表空间
create tablespace website  datafile 'E:\app\Administrator\oradata\orcl\website.dbf' size 2000m;
create user  website identified by website default tablespace website;
grant connect,resource to website;
grant create view to website;


