drop TABLE if exists `roadway`;

create table `roadway`(
`road_id` INTEGER NOT NULL auto_increment,
`road_name` VARCHAR(255) NOT NULL,
`county` INTEGER NOT NULL ,
FOREIGN KEY (`county`) REFERENCES china(`id`),
PRIMARY KEY (`road_id`)
)ENGINE=InnoDB DEFAULT charset=utf8;