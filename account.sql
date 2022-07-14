create database if not exists account;
use account;
CREATE table if not exists account_info (
    id bigint NOT NULL,
    user_id varchar(32) NOT NULL,
    user_name varchar(32) DEFAULT NULL,
    account_name varchar(32) NOT NULL,
    account_type tinyint NOT NULL,
    balance bigint NOT NULL DEFAULT '0',
    frozen_balance bigint NOT NULL DEFAULT '0',
    currency_type varchar(32) NOT NULL,
    status int NOT NULL DEFAULT '1',
    create_type tinyint NOT NULL DEFAULT '1',
    create_by varchar(64) NOT NULL DEFAULT '',
    create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_user_id_type_currency (user_id,account_type,currency_type)
) ENGINE=InnoDB ;


CREATE table if not exists account_info_bak (
    id bigint NOT NULL,
    user_id varchar(32) NOT NULL,
    user_name varchar(32) DEFAULT NULL,
    account_name varchar(32) NOT NULL,
    account_type tinyint NOT NULL,
    balance bigint NOT NULL DEFAULT '0',
    frozen_balance bigint NOT NULL DEFAULT '0',
    currency_type varchar(32) NOT NULL,
    status int NOT NULL DEFAULT '1',
    create_type tinyint NOT NULL DEFAULT '1',
    create_by varchar(64) NOT NULL DEFAULT '',
    create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB comment 'backup account info while accout was deleted';


CREATE TABLE if not exists account_flow (
  flow_id bigint NOT NULL,
  account_id bigint NOT NULL,
  to_account_id bigint NOT NULL default '0',
  user_id varchar(32) NOT NULL,
  to_user_id varchar(32) NOT NULL default '',
  account_type tinyint NOT NULL,
  to_account_type tinyint NOT NULL default '0',
  trans_id varchar(64) NOT NULL COMMENT 'transation id',
  operate_type tinyint NOT NULL COMMENT '1-create account,2-recharge,3-withdraw,4-transaction,5-frozen,6-unfrozen',
  amount bigint NOT NULL DEFAULT '0',
  currency_type varchar(32) NOT NULL,
  balance bigint NOT NULL DEFAULT '0' COMMENT 'balance after operation',
  frozen_balance bigint NOT NULL DEFAULT '0' COMMENT 'frozen_balance after operation',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (flow_id),
  KEY idx_account_id (account_id),
  KEY idx_to_account_id (to_account_id),
  KEY idx_user_id_account_type (user_id,account_type),
  KEY idx_to_user_id_account_type (to_user_id,to_account_type),
  KEY idx_transId_operateType (trans_id,operate_type)
) ENGINE=InnoDB;

CREATE TABLE if not exists account_flow_bak (
  flow_id bigint NOT NULL,
  account_id bigint NOT NULL,
  to_account_id bigint NOT NULL default '0',
  user_id varchar(32) NOT NULL,
  to_user_id varchar(32) NOT NULL default '',
  account_type tinyint NOT NULL,
  to_account_type tinyint NOT NULL default '0',
  trans_id varchar(64) NOT NULL COMMENT 'transation id',
  operate_type tinyint NOT NULL COMMENT '1-create account,2-recharge,3-withdraw,4-transaction,5-frozen,6-unfrozen',
  amount bigint NOT NULL DEFAULT '0',
  currency_type varchar(32) NOT NULL,
  balance bigint NOT NULL DEFAULT '0' COMMENT 'balance after operation',
  frozen_balance bigint NOT NULL DEFAULT '0' COMMENT 'frozen_balance after operation',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (flow_id),
  KEY idx_account_id (account_id),
  KEY idx_to_account_id (to_account_id),
  KEY idx_transId_operateType (trans_id,operate_type)
) ENGINE=InnoDB comment 'backup account flow while accout was deleted';