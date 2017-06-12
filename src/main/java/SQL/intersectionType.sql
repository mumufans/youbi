DROP TABLE if EXISTS `intersection_type`;

CREATE TABLE `intersection_type`(
`intersection_type_id` TINYINT NOT NULL auto_increment,
`intersection_type_name` VARCHAR(255) NOT NULL ,
PRIMARY KEY (`intersection_type_id`)
)ENGINE=InnoDB DEFAULT charset=utf8;

insert into intersection_type (intersection_type_name) values ('十字路口');
insert into intersection_type (intersection_type_name) values ('丁字路口');
insert into intersection_type (intersection_type_name) values ('多岔路口');