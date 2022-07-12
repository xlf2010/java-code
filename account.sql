create table if not exists account_info (
	id bigint not null ,
	user_id VARCHAR(32) not null,
 	user_name varchar(32) DEFAULT null,
	account_name VARCHAR(32) not null,
	account_type tinyint not null comment '',
	balance bigint not null default '0',
	frozen_balance bigint not null default '0',
	status int not null default '1',
	create_type int not null default '1' ,
	create_by varchar(64) not null default '',
	create_time datetime not null default current_timestamp ,
	update_by varchar(64) not null default '',
	update_time datetime not null default current_timestamp on update current_timestamp,
	primary key(id),
	UNIQUE key uk_user_id_type(user_id,account_type)
) ENGINE=InnoDB;

create table if not exists account_info_bak (
	id bigint not null ,
	user_id VARCHAR(32) not null,
 	user_name varchar(32) DEFAULT null,
	account_name VARCHAR(32) not null,
	account_type tinyint not null comment '',
	balance bigint not null default '0',
	frozen_balance bigint not null default '0',
	status int not null default '1',
	create_by varchar(64) not null default '',
	create_time datetime not null default current_timestamp ,
	update_by varchar(64) not null default '',
	update_time datetime not null default current_timestamp on update current_timestamp,
	primary key(id)
) ENGINE=InnoDB comment '';

