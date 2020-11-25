CREATE TABLE customer_login(
  customer_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  login_name VARCHAR(20) NOT NULL COMMENT '用户登录名', 
  password CHAR(32) NOT NULL COMMENT 'md5加密的密码', 
  user_stats TINYINT NOT NULL DEFAULT 1 COMMENT '用户状态', 
  create_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  update_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据更新时间', 
  PRIMARY KEY pk_customerid(customer_id) 
) ENGINE = innodb COMMENT '用户登录表'

CREATE TABLE customer_inf(
  customer_inf_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  customer_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'customer_login表的自增ID', 
  customer_name VARCHAR(20) NOT NULL COMMENT '用户真实姓名', 
  identity_card_type TINYINT NOT NULL DEFAULT 1 COMMENT '证件类型：1 身份证，2 军官证，3 护照', 
  identity_card_no VARCHAR(20) COMMENT '证件号码', 
  mobile_phone bigint(20) COMMENT '手机号', 
  customer_email VARCHAR(50) COMMENT '邮箱', 
  gender CHAR(1) COMMENT '性别', 
  user_point INT NOT NULL DEFAULT 0 COMMENT '用户积分', 
  register_time TIMESTAMP NOT NULL COMMENT '注册时间', 
  birthday DATETIME COMMENT '会员生日', 
  customer_level TINYINT NOT NULL DEFAULT 1 COMMENT '会员级别：1 普通会员，2 青铜，3白银，4黄金，5钻石', 
  user_money DECIMAL(8,2) NOT NULL DEFAULT 0.00 COMMENT '用户余额', 
  create_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  update_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据更新时间', 
  PRIMARY KEY pk_customerinfid(customer_inf_id) 
) ENGINE = innodb COMMENT '用户信息表';

CREATE TABLE customer_balance_log(
  balance_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '余额日志ID',
  customer_id bigint(20) NOT NULL COMMENT '用户ID', 
  source tinyint(1)  NOT NULL DEFAULT 1 COMMENT '记录来源：1订单，2退货单', 
  source_sn bigint(20) NOT NULL COMMENT '相关单据ID', 
  amount DECIMAL(8,2) NOT NULL DEFAULT 0.00 COMMENT '变动金额', 
  create_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  update_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据更新时间', 
  PRIMARY KEY pk_balanceid(balance_id) 
 ) ENGINE = innodb COMMENT '用户余额变动表';

CREATE TABLE  tb_category  (
   id  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类Id',
   name  varchar(32) NOT NULL COMMENT '分类名称',
   parent_id  bigint(20) NOT NULL COMMENT '父分类Id (顶级类目填0)',
   is_parent  tinyint(1) NOT NULL COMMENT '是否为父节点 (0-否，1-是)',
   sort  tinyint(2) NOT NULL COMMENT '排序指数，越小越靠前',
   create_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
   update_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据更新时间',
  PRIMARY KEY ( id ),
  KEY  key_parent_id  ( parent_id ) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1424 DEFAULT CHARSET=utf8 COMMENT='商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系'

CREATE TABLE  tb_brand  (
   id  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '品牌id',
   name  varchar(64) NOT NULL COMMENT '品牌名称',
   image  varchar(256) DEFAULT '' COMMENT '品牌图片地址',
   letter  char(1) DEFAULT '' COMMENT '品牌的首字母',
   create_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   update_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=325403 DEFAULT CHARSET=utf8 COMMENT='品牌表，一个品牌下有多个商品（spu），一对多关系'

CREATE TABLE  tb_category_brand  (
   category_id  bigint(20) NOT NULL COMMENT '商品类目id',
   brand_id  bigint(20) NOT NULL COMMENT '品牌id',
  PRIMARY KEY ( category_id , brand_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类和品牌的中间表，两者是多对多关系'

CREATE TABLE  tb_spec_group  (
   id  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '规格组Id',
   category_id  bigint(20) NOT NULL COMMENT '商品分类Id (一个分类下有多个规格组)',
   name  varchar(32) NOT NULL COMMENT '规格组名称',
   create_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   update_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY ( id ),
  KEY  key_category  ( cid )
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='规格参数的分组表，每个商品分类下有多个规格参数组';


CREATE TABLE  tb_spec_param  (
   id  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '参数Id',
   category_id  bigint(20) NOT NULL COMMENT '商品分类Id (参数所属的商品分类)',
   group_id  bigint(20) NOT NULL COMMENT '规则组Id (参数所属的规格组)',
   name  varchar(128) NOT NULL COMMENT '参数名',
   numeric  tinyint(1) NOT NULL COMMENT '是否是数字类型参数 (true或false)',
   unit  varchar(128) DEFAULT '' COMMENT '数字类型参数的单位 (非数字类型可以为空)',
   generic  tinyint(1) NOT NULL COMMENT '是否是SKU通用规格 (true或false)',
   searching  tinyint(1) NOT NULL COMMENT '是否用于搜索过滤 (true或false)',
   segments  varchar(1024) DEFAULT '' COMMENT '区间 (数值类型参数的预设区间值，如果需要搜索，则添加分段间隔值，如CPU频率间隔：0.5-1.0)',
   create_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   update_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY ( id ),
  KEY  key_group  ( group_id ),
  KEY  key_category  ( cid )
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='规格参数组下的参数名';



CREATE TABLE  tb_spu  (
   id  bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'SPU Id',
   name  varchar(256) NOT NULL DEFAULT '' COMMENT '商品名称',
   sub_title  varchar(256) DEFAULT '' COMMENT '副标题 (一般是促销信息)',
   cid1  bigint(20) NOT NULL COMMENT '1级分类Id',
   cid2  bigint(20) NOT NULL COMMENT '2级分类Id',
   cid3  bigint(20) NOT NULL COMMENT '3级分类Id',
   brand_id  bigint(20) NOT NULL COMMENT '品牌Id (商品所属的品牌)',
   saleable  tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否上架 (0-下架，1-上架)',
   create_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
   update_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY ( id )
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8 COMMENT='spu表描述的是一个抽象性的商品，比如 iphone8'


CREATE TABLE  tb_spu_detail  (
   spu_id  bigint(20) NOT NULL COMMENT 'SPU Id',
   description  text COMMENT '商品描述信息',
   generic_spec  varchar(2048) NOT NULL DEFAULT '' COMMENT '通用规格键值对 (json格式)',
   special_spec  varchar(1024) NOT NULL DEFAULT '' COMMENT '特有规格可选值 (json格式)',
   packing_list  varchar(1024) DEFAULT '' COMMENT '包装清单',
   after_service  varchar(1024) DEFAULT '' COMMENT '售后服务',
   create_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   update_time  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY ( spu_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8


CREATE TABLE  tb_sku  (
   id  bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'SKU Id',
   spu_id  bigint(20) NOT NULL COMMENT 'SPU Id',
   title  varchar(256) NOT NULL COMMENT '商品标题',
   images  varchar(1024) DEFAULT '' COMMENT '商品图片 (多个图片用,号分割)',
   stock  int(8) unsigned DEFAULT '9999' COMMENT '库存',
   price  bigint(16) NOT NULL DEFAULT '0' COMMENT '销售价格 (单位为分)',
   indexes  varchar(32) DEFAULT '' COMMENT '特有规格参数在SPU规格模板中对应的下标组合(如1_0_0)',
   own_spec  varchar(1024) DEFAULT '' COMMENT 'SKU的特有规格参数键值对 (json格式，反序列化时请使用linkedHashMap，保证有序)',
   enable  tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效 (0-无效，1-有效)',
   create_time  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
   update_time  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY ( id ),
  KEY  key_spu_id  ( spu_id ) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27359021564 DEFAULT CHARSET=utf8 COMMENT='sku表,该表表示具体的商品实体,如黑色的 64g的iphone 8'



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
  district_money DECIMAL(8,2) NOT NULL DEFAULT 0.00 COMMENT '优惠金额', 
  shipping_money DECIMAL(8,2) NOT NULL DEFAULT 0.00 COMMENT '运费金额', 
  payment_money DECIMAL(8,2) NOT NULL DEFAULT 0.00 COMMENT '支付金额', 
  shipping_comp_name VARCHAR(10) COMMENT '快递公司名称', 
  shipping_sn VARCHAR(50) COMMENT '快递单号', 
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间', 
  shipping_time DATETIME COMMENT '发货时间', 
  pay_time DATETIME COMMENT '支付时间', 
  receive_time DATETIME COMMENT '收货时间', 
  order_status TINYINT NOT NULL DEFAULT 0 COMMENT '订单状态', 
  order_point INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单积分', 
  invoice_time VARCHAR(100) COMMENT '发票抬头', 
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间', 
  PRIMARY KEY pk_orderid(order_id) 
)ENGINE = innodb COMMENT '订单主表';

CREATE TABLE order_detail(
  order_detail_id INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单详情表ID',
  order_id INT UNSIGNED NOT NULL COMMENT '订单表ID', 
  product_id INT UNSIGNED NOT NULL COMMENT '订单商品ID', 
  product_name VARCHAR(50) NOT NULL COMMENT '商品名称', 
  product_cnt INT NOT NULL DEFAULT 1 COMMENT '购买商品数量', 
  product_price DECIMAL(8,2) NOT NULL COMMENT '购买商品单价', 
  average_cost DECIMAL(8,2) NOT NULL COMMENT '平均成本价格', 
  weight FLOAT COMMENT '商品重量', 
  fee_money DECIMAL(8,2) NOT NULL DEFAULT 0.00 COMMENT '优惠分摊金额', 
  w_id INT UNSIGNED NOT NULL COMMENT '仓库ID', 
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间', 
  PRIMARY KEY pk_orderdetailid(order_detail_id) 
)ENGINE = innodb COMMENT '订单详情表';