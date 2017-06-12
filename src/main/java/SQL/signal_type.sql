drop TABLE if EXists `signal_type`;

CREATE TABLE `signal_type`(
`signal_type_id` INT NOT NULL,
`signal_type_name` VARCHAR (255) NOT NULL,
PRIMARY KEY (`signal_type_id`)
)ENGINE=InnoDB DEFAULT charset=utf8;

insert into signal_type (signal_type_id, signal_type_name) values (1, '一般信号灯');
insert into signal_type (signal_type_id, signal_type_name) values (2, '智能信号灯');
insert into signal_type (signal_type_id, signal_type_name) values (3, '移动信号灯');
insert into signal_type (signal_type_id, signal_type_name) values (4, '人行横道灯');