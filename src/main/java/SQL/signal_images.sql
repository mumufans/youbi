drop table if exists `signal_images`;

create table `signal_images`(
`id` int not null auto_increment,
`signal_id` int not null,
`subject` varchar(255) not null,
`url` varchar(255) not null ,
`file_name` varchar(255) not null,
`content_type` varchar(255) not null,
`file_size` varchar(255) not null,
PRIMARY KEY (`id`),
FOREIGN KEY (`signal_id`) REFERENCES signals(`signal_id`)
)ENGINE=InnoDB DEFAULT charset=utf8;