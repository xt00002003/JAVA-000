CREATE TABLE customer_login(
  customer_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  login_name VARCHAR(20) NOT NULL COMMENT '用户登录名', 
  password CHAR(32) NOT NULL COMMENT 'md5加密的密码', 
  user_stats TINYINT NOT NULL DEFAULT 1 COMMENT '用户状态', 
  create_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  update_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据更新时间', 
  PRIMARY KEY pk_customerid(customer_id) 
) ENGINE = innodb COMMENT '用户登录表';



CREATE TABLE  product  (
   id  bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'SPU Id',
   name  varchar(256) NOT NULL DEFAULT '' COMMENT '商品名称',
   price  bigint(16) NOT NULL DEFAULT '0' COMMENT '销售价格 (单位为分)',
   stock  int(8) unsigned DEFAULT '9999' COMMENT '库存',
   version int(8) unsigned DEFAULT '9999' COMMENT '版本号',
   create_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
   update_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8 COMMENT='商品表';


CREATE TABLE  product_snapshot  (
   id  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品快照 Id',
   name  varchar(256) NOT NULL DEFAULT '' COMMENT '商品名称',
   price  bigint(16) NOT NULL DEFAULT '0' COMMENT '销售价格 (单位为分)',
   stock  int(8) unsigned DEFAULT '9999' COMMENT '库存',
   version int(8) unsigned DEFAULT '9999' COMMENT '版本号',
   create_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8 COMMENT='商品快照表';



CREATE TABLE order_master(
  order_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  order_sn BIGINT UNSIGNED NOT NULL COMMENT '订单编号 yyyymmddnnnnnnnn', 
  customer_id INT UNSIGNED NOT NULL COMMENT '下单人ID', 
  shipping_user VARCHAR(10) NOT NULL COMMENT '收货人姓名', 
  province SMALLINT NOT NULL COMMENT '省', 
  city SMALLINT NOT NULL COMMENT '市', 
  district SMALLINT NOT NULL COMMENT '区', 
  address VARCHAR(100) NOT NULL COMMENT '地址', 
  payment_method TINYINT NOT NULL COMMENT '支付方式：1现金，2余额，3网银，4支付宝，5微信', 
  order_money DECIMAL(8,2) NOT NULL COMMENT '订单金额', 
  payment_money DECIMAL(8,2) NOT NULL DEFAULT 0.00 COMMENT '支付金额', 
  create_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  update_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY pk_orderid(order_id) 
)ENGINE = innodb COMMENT '订单主表';

CREATE TABLE order_detail(
  order_detail_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单详情表ID',
  order_id bigint(20) NOT NULL COMMENT '订单表ID', 
  product_snapshot_id bigint(20) NOT NULL COMMENT '订单商品ID', 
  product_name VARCHAR(50) NOT NULL COMMENT '商品名称', 
  product_cnt INT NOT NULL DEFAULT 1 COMMENT '购买商品数量', 
  create_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  update_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY pk_orderdetailid(order_detail_id) 
)ENGINE = innodb COMMENT '订单详情表';