DROP TABLE if EXISTS `unit`;

CREATE TABLE `unit`(
`unit_id` int(10) NOT NULL auto_increment,
`unit_name` VARCHAR (255) NOT NULL ,
`unit_number` VARCHAR (255) DEFAULT null,
`unit_level` int(4) DEFAULT null,
`parent` int(10) DEFAULT null,
`county` int(10) DEFAULT null,
`created` datetime DEFAULT null,
PRIMARY KEY (`unit_id`),
FOREIGN KEY (`parent`) REFERENCES unit(`unit_id`),
FOREIGN key (`county`) REFERENCES china(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
