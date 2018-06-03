create table miaosha_user (
  id bigint(20) not null comment '用户id, 手机号码',
  nickname varchar(255) not null,
  password varchar(32) default null comment 'md5(md5(pass明文+固定salt) + salt)',
  salt varchar(10) default null,
  head varchar(128) default null comment '头像，云存储id',
  register_date datetime default null comment '注册时间',
  last_login_date datetime default null comment '上次登录时间',
  login_count int default 0 comment '登录次数',
  primary key (id)
) engine = innodb default charset = utf8;

create table goods (
  id bigint(20) not null auto_increment comment '商品id',
  goods_name varvhar(16) default null comment '商品名称',
  goods_title varchar(64) default null comment '商品标题',
  goods_img varchar(64) default null comment '商品图片',
  goods_detail longtext comment '商品的详情介绍',
  goods_price decimal(10, 2) default 0.00 comment '商品单价',
  goods_stock int(11) default 0 comment '商品库存，-1表示没有限制',
  primary key (id)
) engine = innodb default charset = utf8;

insert into goods values(1, 'iphoneX', 'Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机', '/img/iphonex.png', 'Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机', 8765.00, 10000), (2, '华为Mate9', '华为 Mate 9 4GB + 32G版 月光银 移动联通电信4G手机 双卡双待', '/img/mate10.png', '华为 Mate 9 4GB + 32G版 月光银 移动联通电信4G手机 双卡双待', 3212.00, -1);

create table miaosha_goods (
  id bigint(20) not null auto_increment comment '秒杀商品id',
  goods_id bigint(20) default null comment '商品id',
  miaosha_price decimal(10, 2) default 0.00 comment '秒杀价',
  stock_count int(11) default null comment '库存数量',
  start_date datetime default null comment '秒杀开始时间',
  end_date datetime default null comment '秒杀结束时间',
  primary key (id)
) engine = innodb default charset = utf8;

insert into miaosha_goods values (1, 1, 0.01, 4, now(), now()), (2, 2, 0.01, 9, now(), now());

create table order_info (
  id bigint(20) not null auto_increment,
  user_id bigint(20) default null comment '用户id',
  goods_id bigint(20) default null comment '商品id',
  delivery_addr_id bigint(20) default null comment '收货地址id',
  goods_name varchar(16) default null comment '商品名称',
  goods_count int(11) default 0 comment '商品数量',
  goods_price decimal(10, 2) default 0.00 comment '商品单价',
  order_channel tinyint(4) default 0 comment '1 pc, 2 android, 3 ios',
  status tinyint(4) default 0 comment '订单状态 0 新建未支付, 1 已支付, 2 已发货, 3 已收货, 4 已退款, 5 已完成',
  create_date datetime default null comment '订单创建时间',
  pay_date datetime default null comment '支付时间',
  primary key (id)
) engine = innodb default charset = utf8;

create table miaosha_order (
  id bigint(20) not null auto_increment,
  user_id bigint(20) default null comment '用户id',
  order_id bigint(20) default null comment '订单id',
  goods_id bigint(20) default null comment '商品id',
  primary key (id)
) engine = innodb default charset = utf8;
create unique index index_uid_gid on miaosha_order (user_id, goods_id);
alter table miaosha_order add index index_uid_gid(user_id, goods_id);