drop TABLE if EXISTS `intersect_record`;

CREATE TABLE `intersect_record`(
`intersection_id` INTEGER NOT NULL ,
`road_id` INTEGER NOT NULL ,
PRIMARY KEY (`intersection_id`, `road_id`),
FOREIGN KEY (`intersection_id`) REFERENCES intersection(`intersection_id`),
FOREIGN KEY (`road_id`) REFERENCES roadway(`road_id`)
)ENGINE=InnoDB DEFAULT charset=utf8;

insert into intersect_record (intersection_id, road_id) values (1,1);