drop TABLE if exists `intersection`;

CREATE TABLE `intersection`(
`intersection_id` INTEGER NOT NULL auto_increment,
`intersection_name` VARCHAR (255) NOT NULL ,
`intersection_long` FLOAT NOT NULL ,
`intersection_lat` FLOAT NOT NULL ,
`user_id` INTEGER NOT NULL ,
`unit_id` INTEGER NOT NULL ,
`intersection_type_id` INTEGER NOT NULL ,
`is_damaged` tinyint not null,
`create_date` DATE ,
PRIMARY key (`intersection_id`),
FOREIGN key (`intersection_type_id`) REFERENCES intersection_type(`intersection_type_id`),
FOREIGN KEY (`unit_id`) REFERENCES unit(`unit_id`)
)ENGINE=InnoDB DEFAULT charset=utf8;

insert into intersection (intersection_name, intersection_long, intersection_lat,is_damaged, user_id, unit_id,intersection_type_id)
 values ('六道口十字路口', 132.11, 122.11, 0, 7, 77,1);