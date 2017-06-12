drop table if exists `signal`;

create TABLE `signal`(
`signal_id` INTEGER NOT NULL auto_increment,
`type_id` integer NOT NULL,
`unit_id` INTEGER NOT NULL ,
`user_id` INTEGER NOT NULL ,
`signal_long` FLOAT NOT NULL ,
`signal_lat` FLOAT NOT NULL ,
`province_id` INTEGER NOT NULL ,
`city_id` integer NOT NULL ,
`county_id` INTEGER NOT NULL ,
`road_id` INTEGER NOT NULL ,
PRIMARY KEY (`signal_id`),
FOREIGN KEY (`type_id`) REFERENCES signal_type(`signal_type_id`),
FOREIGN KEY (`unit_id`) REFERENCES unit(`unit_id`),
FOREIGN KEY (`province_id`) REFERENCES province(`province_id`),
FOREIGN KEY (`city_id`) REFERENCES city(`city_id`),
FOREIGN KEY (`county_id`) REFERENCES county(`county_id`),
FOREIGN KEY (`road_id`) REFERENCES roadway(`road_id`)
)ENGINE=InnoDB DEFAULT charset=utf8;

