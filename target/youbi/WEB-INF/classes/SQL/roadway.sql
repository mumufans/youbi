drop TABLE if exists `roadway`;

create table `roadway`(
`road_id` INTEGER NOT NULL auto_increment,
`road_name` VARCHAR(255) NOT NULL,
`user_id` INTEGER NOT NULL,
`create_date` DATE ,
PRIMARY KEY (`road_id`)
)ENGINE=InnoDB DEFAULT charset=utf8;

insert into roadway (road_name, user_id) values ('玉泉路', 7);
insert into roadway ( road_name,user_id) values ('大望路', 7);
insert into roadway (road_name, user_id) values ('惠新街', 7);