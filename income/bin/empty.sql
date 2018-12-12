DROP TABLE IF EXISTS admin;
CREATE TABLE admin (
  id int(10) unsigned NOT NULL auto_increment COMMENT 'id',
  uname varchar(45) NOT NULL COMMENT '用户名',
  upwd varchar(45) NOT NULL COMMENT '密码',
  ustatus int(1) unsigned default '0' COMMENT '0正常1注销2销定',
  uposition varchar(45) default NULL COMMENT '职位',
  upur varchar(10) default '10000000' COMMENT '权限',
  PRIMARY KEY  (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=gbk COMMENT='管理员信息';


INSERT INTO admin (id,uname,upwd,ustatus,uposition,upur) VALUES 
 (6,'guest','21232f297a57a5a743894ae4a801fc3',0,'财务','0100000000'),
 (32,'admin','21232f297a57a5a743894ae4a801fc3',0,'老板','1000000000'),
 (33,'user','21232f297a57a5a743894ae4a801fc3',0,'其它','0010000000');

DROP TABLE IF EXISTS classinfo;
CREATE TABLE classinfo (
  id int(10) unsigned NOT NULL auto_increment COMMENT 'id',
  cno varchar(45) NOT NULL COMMENT '班级编号',
  cdesc varchar(45) NOT NULL COMMENT '描述',
  cteacher varchar(45) default NULL COMMENT '老师',
  ctutor varchar(45) default NULL COMMENT '班主任',
  PRIMARY KEY  (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=gbk COMMENT='班级信息';

DROP TABLE IF EXISTS stuinfo;
CREATE TABLE stuinfo (
  id int(10) unsigned NOT NULL auto_increment COMMENT 'id',
  classid int(10) unsigned default NULL COMMENT '班级',
  stuno varchar(45) NOT NULL COMMENT '编号',
  sname varchar(45) NOT NULL COMMENT '姓名',
  ssex enum('M','F') NOT NULL default 'M' COMMENT '性别',
  sbirthday date default NULL COMMENT '出生年月',
  school varchar(45) default NULL COMMENT '学校',
  sgraduate date default NULL COMMENT '毕业时间',
  sgrade varchar(45) default NULL COMMENT '学历',
  stel varchar(45) default NULL COMMENT '电话',
  smail varchar(45) default NULL COMMENT '邮箱',
  sqq varchar(45) default NULL COMMENT 'QQ',
  sstatus int(1) unsigned NOT NULL default '0' COMMENT '1:在校(默认)/2:已毕\r\r\n0在校1毕业2休学',
  smajor varchar(45) default NULL COMMENT '专业',
  PRIMARY KEY  (id),
  KEY FK_stuinfo_1 (classid),
  CONSTRAINT FK_stuinfo_1 FOREIGN KEY (classid) REFERENCES classinfo (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=gbk COMMENT='学生信息';

DROP TABLE IF EXISTS feeinfo;
CREATE TABLE feeinfo (
  id int(10) unsigned NOT NULL auto_increment COMMENT 'id',
  feeno varchar(45) default NULL COMMENT '收费编号',
  stuid int(10) unsigned default NULL COMMENT '学生id',
  feenote varchar(85) default NULL COMMENT '备注',
  feermb decimal(10,2) default NULL COMMENT '金额',
  feedate datetime NOT NULL COMMENT '日期',
  adminid int(10) unsigned default NULL COMMENT '记帐人',
  feetype int(1) unsigned default '0' COMMENT '0现金1刷卡2转帐',
  feeby varchar(45) default NULL COMMENT '收款人',
  PRIMARY KEY  (id),
  KEY FK_feeinfo_2 (adminid),
  KEY FK_feeinfo_1 (stuid),
  CONSTRAINT FK_feeinfo_1 FOREIGN KEY (stuid) REFERENCES stuinfo (id) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT FK_feeinfo_2 FOREIGN KEY (adminid) REFERENCES admin (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=gbk COMMENT='收费明细';

DROP TABLE IF EXISTS priceinfo;
CREATE TABLE priceinfo (
  id int(10) unsigned NOT NULL auto_increment COMMENT 'ID',
  stuid int(10) unsigned default NULL COMMENT '学生id',
  orgprice decimal(10,2) NOT NULL COMMENT '原价',
  newprice decimal(10,2) NOT NULL COMMENT '新价',
  PRIMARY KEY  (id),
  KEY FK_priceinfo_1 (stuid),
  CONSTRAINT FK_priceinfo_1 FOREIGN KEY (stuid) REFERENCES stuinfo (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=gbk COMMENT='学费价格';



