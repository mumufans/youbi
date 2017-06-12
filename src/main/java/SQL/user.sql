DROP TABLE if EXISTS `user`;

CREATE TABLE `user`(
`user_id` int(10) NOT NULL auto_increment,
`user_name` VARCHAR (255) NOT NULL ,
`zhname` VARCHAR (255) NOT NULL ,
`hash` VARCHAR (255) NOT NULL,
`salt` VARCHAR (255) NOT NULL ,
`bade_number` VARCHAR (255) DEFAULT NULL ,
`duty` VARCHAR (255)DEFAULT NULL ,
`mobile` VARCHAR (255) DEFAULT null,
`unit` int(10) DEFAULT null,
`role` int(10) not null,
`admin_unit` int(10) default null,
`last_login_time` datetime DEFAULT null,
PRIMARY KEY (`user_id`),
FOREIGN KEY (`role`) REFERENCES role(`role_id`),
FOREIGN KEY (`unit`) REFERENCES unit(`unit_id`)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

insert into user (user_name, zhname, hash, salt, role) values ('admin', '管理员',
'63709CF8212B6207B2F79F829E583174','1m8STP',1);