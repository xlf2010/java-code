create database if not exists trade;
use trade;
create table if not exists pay_order(
  order_id bigint NOT NULL,
  user_id varchar(32) NOT NULL,
  to_user_id varchar(32) NOT NULL default '',
  trans_id varchar(64) NOT NULL COMMENT 'transation id',
  pay_channel tinyint not null comment '1-wechat,2-alipay,3-paypal,4-amazon',
  operate_type tinyint NOT NULL COMMENT '1-create trade,2-recharge,3-withdraw,4-transaction,5-frozen,6-unfrozen',
  amount bigint NOT NULL DEFAULT '0',
  currency_type varchar(32) NOT NULL,
  status int not null default '1' comment '1-init, 10-calling pay channel, 11-call pay channel fail, 20-calling account , 21-call account channel fail,200-success',
  request_url text,
  request_param text,
  response text ,
  notify text ,
  remark varchar(512) not null DEFAULT '',
  create_by varchar(64) NOT NULL DEFAULT '',
  create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  order_time datetime not null comment 'order time for bill check',
  PRIMARY KEY (order_id),
  key idx_userId_payChannel(user_id,pay_channel),
  key idx_order_time(order_time)
) ENGINE=InnoDB ;