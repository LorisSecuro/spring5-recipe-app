create database sfg_dev;
create database sfg_prod;

create user 'sfg_dev_user'@'localhost' identified by 'password';
create user 'sfg_prod_user'@'localhost' identified by 'password';
create user 'sfg_dev_user'@'%' identified by 'password';
create user 'sfg_prod_user'@'%' identified by 'password';

grant delete,update,insert,select on sfg_dev.* to 'sfg_dev_user'@'localhost';
grant delete,update,insert,select on sfg_prod.* to 'sfg_prod_user'@'localhost';
grant delete,update,insert,select on sfg_dev.* to 'sfg_dev_user'@'%';
grant delete,update,insert,select on sfg_prod.* to 'sfg_prod_user'@'%';

FLUSH PRIVILEGES;