drop table if exists `signals`;

create TABLE `signals`(
`signal_id` INTEGER NOT NULL auto_increment,
`type_id` integer DEFAULT 1,
`signal_type` INTEGER NOT NULL ,
`unit_id` INTEGER NOT NULL ,
`user_id` INTEGER NOT NULL ,
`signal_long` FLOAT NOT NULL ,
`signal_lat` FLOAT NOT NULL ,
`county_id` INTEGER NOT NULL ,
`road_id` INTEGER NOT NULL ,
`is_damaged` tinyint DEFAULT 0,
`is_used` tinyint DEFAULT 1,
`is_confirmed` tinyint DEFAULT 0,
`description` VARCHAR(255),
`create_time` DATE NOT NULL,

PRIMARY KEY (`signal_id`),
FOREIGN KEY (`signal_type`) REFERENCES signal_type(`signal_type_id`),
FOREIGN KEY (`unit_id`) REFERENCES unit(`unit_id`),
FOREIGN KEY (`user_id`) REFERENCES user(`user_id`),
FOREIGN KEY (`county_id`) REFERENCES china(`id`),
FOREIGN KEY (`road_id`) REFERENCES roadway(`road_id`)
)ENGINE=InnoDB DEFAULT charset=utf8;

