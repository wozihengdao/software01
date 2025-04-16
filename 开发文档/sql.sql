#用户单
drop table tb_user;
create table tb_user (

    username varchar(20),
    password varchar(20),
    type varchar(10)
);
#报修单
create table tb_repair_order (

    id int primary key auto_increment,
    stop varchar(20),
    faulty_equipment_id varchar(20),
    faulty_type varchar(20),
    faulty_description varchar(20),
    faulty_grade varchar(20),
    picture varchar(2000),
    type varchar(4),
    data varchar(20)
)auto_increment=10000000;
#维修单
create table tb_maintenance_order (

    id int primary key auto_increment,
    repair_id varchar(20),
    engineer_id varchar(20),
    picture varchar(2000),
    type varchar(4),

    data varchar(20)

)auto_increment=20000000;
#巡检单
create table tb_patrol_order (

   id int primary key auto_increment,
   stop varchar(20),
   equipment_id varchar(20),
   equipment_type varchar(20),
   engineer_id varchar(20),
   picture varchar(2000),
   type varchar(4),
   data varchar(20)
)auto_increment=30000000;
#保养单
create table tb_upkeep_order (

     id int primary key auto_increment,
     stop varchar(20),
     equipment_id varchar(20),
     equipment_type varchar(20),
     engineer_id varchar(20),
     old_picture varchar(2000),
     new_picture varchar(2000),
     type varchar(4),
     data varchar(20)
)auto_increment=40000000;

#检修单
create table tb_check_order (

 id int primary key auto_increment,
 stop varchar(20),
 equipment_id varchar(20),
 equipment_type varchar(20),
 engineer_id varchar(20),
 old_picture varchar(2000),
 new_picture varchar(2000),
 type varchar(4),
 data varchar(20)
)auto_increment=50000000;

#备件单
create table tb_spare_order (

 id int primary key auto_increment,
 stop varchar(20),
 equipment_id varchar(20),
 equipment_type varchar(20),
 engineer_id varchar(20),
 old_picture varchar(2000),
 new_picture varchar(2000),
 type varchar(4),
 data varchar(20)
)auto_increment=60000000;


# engineer 工程师
create table tb_engineer (

  id int primary key auto_increment,
  order_num  int,
  state varchar(4),
  type varchar(4)
)auto_increment=70000000;

#设备表：ID，设备ID，设备名称，设备状态,车站
create table tb_equipment (

   id int primary key auto_increment,
   stop varchar(20),
   name varchar(20),
   type varchar(4)
)auto_increment=80000000;

# 备件表：ID，备件ID，备件名称，备件状态

create table tb_spare (

  id int primary key auto_increment,
  name varchar(20),
  type varchar(4),
  data varchar(20)
)auto_increment=90000000;
#车站表：ID，车站ID，车站名称
create table tb_stop (
 id int primary key auto_increment,
 name varchar(20)
)auto_increment=11000000;




