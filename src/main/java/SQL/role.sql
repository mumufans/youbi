DROP TABLE if EXISTS `role`;

CREATE TABLE `role`(
`role_id` int(10) NOT null auto_increment,
`role_name` VARCHAR(255) NOT NULL ,
`role_zhname` VARCHAR(255) NOT NULL ,
PRIMARY KEY (`role_id`)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

insert into role (role_id, role_name, role_zhname) values (1, 'ADMIN','管理员');
insert into role (role_id, role_name, role_zhname) values (2, 'CAPTAIN', '部门管理员');
insert into role (role_id, role_name, role_zhname) values (3, 'COMMON', '普通用户');