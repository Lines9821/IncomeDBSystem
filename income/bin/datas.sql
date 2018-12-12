DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(10) unsigned NOT NULL auto_increment COMMENT 'id',
  `uname` varchar(45) NOT NULL COMMENT '用户名',
  `upwd` varchar(45) NOT NULL COMMENT '密码',
  `ustatus` int(1) unsigned default '0' COMMENT '0正常1注销2销定',
  `uposition` varchar(45) default NULL COMMENT '职位',
  `upur` varchar(10) default '10000000' COMMENT '权限',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=gbk COMMENT='管理员信息';

INSERT INTO `admin` (`id`,`uname`,`upwd`,`ustatus`,`uposition`,`upur`) VALUES 
 (6,'guest','21232f297a57a5a743894ae4a801fc3',0,'财务','0100000000'),
 (32,'admin','21232f297a57a5a743894ae4a801fc3',0,'老板','1000000000'),
 (33,'user','21232f297a57a5a743894ae4a801fc3',0,'其它','0010000000');


DROP TABLE IF EXISTS `classinfo`;
CREATE TABLE `classinfo` (
  `id` int(10) unsigned NOT NULL auto_increment COMMENT 'id',
  `cno` varchar(45) NOT NULL COMMENT '班级编号',
  `cdesc` varchar(45) NOT NULL COMMENT '描述',
  `cteacher` varchar(45) default NULL COMMENT '老师',
  `ctutor` varchar(45) default NULL COMMENT '班主任',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gbk COMMENT='班级信息';


INSERT INTO `classinfo` (`id`,`cno`,`cdesc`,`cteacher`,`ctutor`) VALUES 
 (3,'200501','陕理工','张老师','美女'),
 (4,'200512','西安科大','刘老师','lisa'),
 (5,'201501','科大周末','刘老师','小李'),
 (6,'201502','西电全日','刘老师','小明'),
 (7,'201503','石油大学周末班','李老师','贾老师'),
 (8,'201504','临潼周末班','刘老师','王老师'),
 (9,'201505','西电','王老师','lili'),
 (10,'201506','脱产班','小花','小花'),
 (11,'201507','北大','赛老师','mimi');
DROP TABLE IF EXISTS `stuinfo`;
CREATE TABLE `stuinfo` (
  `id` int(10) unsigned NOT NULL auto_increment COMMENT 'id',
  `classid` int(10) unsigned default NULL COMMENT '班级',
  `stuno` varchar(45) NOT NULL COMMENT '编号',
  `sname` varchar(45) NOT NULL COMMENT '姓名',
  `ssex` enum('M','F') NOT NULL default 'M' COMMENT '性别',
  `sbirthday` date default NULL COMMENT '出生年月',
  `school` varchar(45) default NULL COMMENT '学校',
  `sgraduate` date default NULL COMMENT '毕业时间',
  `sgrade` varchar(45) default NULL COMMENT '学历',
  `stel` varchar(45) default NULL COMMENT '电话',
  `smail` varchar(45) default NULL COMMENT '邮箱',
  `sqq` varchar(45) default NULL COMMENT 'QQ',
  `sstatus` int(1) unsigned NOT NULL default '0' COMMENT '1:在校(默认)/2:已毕\r\r\n0在校1毕业2休学',
  `smajor` varchar(45) default NULL COMMENT '专业',
  PRIMARY KEY  (`id`),
  KEY `FK_stuinfo_1` (`classid`),
  CONSTRAINT `FK_stuinfo_1` FOREIGN KEY (`classid`) REFERENCES `classinfo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=gbk COMMENT='学生信息';

INSERT INTO `stuinfo` (`id`,`classid`,`stuno`,`sname`,`ssex`,`sbirthday`,`school`,`sgraduate`,`sgrade`,`stel`,`smail`,`sqq`,`sstatus`,`smajor`) VALUES 
 (5,6,'20150201','张三','M','1983-03-06','科技大学','2017-07-01','本科','13991313435','slfsdsdfsd@163.com','9703239',0,'计算机'),
 (8,4,'20050102','GGJU','M','1984-05-05','SS12','2018-07-01','硕士','SF12J','ES12','COCO12',1,'SA12'),
 (13,6,'20051201','sdf123111','M','1982-05-01','1231qweq','2016-02-01','硕士','12314346647','1231235235346','234254364',2,'2342534646'),
 (15,4,'20051202','小名','M','1982-05-06','陕科大','2017-01-01','硕士','12936548995','45256@fg.聪明','154354684',1,'计算机'),
 (16,4,'20051203','周星驰','M','1988-05-04','西科大','2015-02-01','本科','1234456y789','456778','45878526',2,'计算机'),
 (17,6,'20150202','小强','M','1986-07-04','西科大','2015-01-01','本科','13392683068','1091347412@qq.com','1091347412',1,'Java'),
 (20,3,'20050101','中华小当家','M','1980-01-01','蓝翔','2015-01-01','硕士','13568573640','121254@sina.com','120356540',0,'厨师'),
 (22,8,'20150401','小米','M','1981-03-03','北大','2016-03-01','本科','13991313435','971351','9703239',0,'油头粉面'),
 (25,3,'20050102','傻瓜斯蒂芬','F','1984-08-03','啊圣诞','2016-02-01','硕士','121241245346','5687078567','536486970',1,'阿斯达'),
 (26,4,'20051203','FG','M','1986-04-04','gghtyhy','2016-03-01','本科','15562258','145566@125','44589666',0,'ghhjkj'),
 (27,3,'20050102','高铁符','M','1987-01-20','kg恢复','2020-12-01','高中','6541368534','764787980','23423娃儿4645',2,'威尔4'),
 (28,4,'20051203','小明','F','2008-01-02','复旦','2016-07-01','本科','448966225','1254555555','5587895',0,'计算机'),
 (29,7,'20150301','Harden','M','1998-11-30','火箭','2019-02-01','高中','3549642','6546','65+6',2,'fg'),
 (30,7,'20150302','KG','M','1986-03-31','qweasd','2018-01-01','高中','sdfwe','hfujtyutt','yutioui',1,'kj;;/op'),
 (31,5,'20150101','Keba','M','2004-02-29','asd','2017-02-01','高中','fdsgye','yi76',';;;;;;;i',0,'\'\\\\p[='),
 (32,5,'20150102','Lebron','M','2008-12-25','jiasd','2020-02-01','高中','rt5y65427y m','                yutyu','o789\'\\]',1,'asdAAa'),
 (33,5,'20150102','张三','M','1981-05-04','科大','2017-01-01','硕士','15982368421','129856@qq.com','52659826',1,'计算'),
 (34,5,'20150102','65465','F','1983-10-01','werw','2016-04-01','硕士','gn','gh,','kj',0,''),
 (36,7,'20150302','JOJO','M','1980-01-01','h66y','2017-02-01','硕士','7864','345363','534534535',0,'345ertgfhfgh'),
 (37,10,'20150601','卢锡安','F','1980-01-01','战争学院','2020-01-01','硕士','657489498','85648548@qq.com','84654165',1,'adc'),
 (38,8,'20150402','UZI','M','1987-02-03',']][]','2015-01-01','硕士','[][][][][]','[][][][][][][][][][][][][][][]','[][][][][][][][][][]',0,'[][][][][]'),
 (39,8,'20150402','llkd','F','1980-01-01','ffd','2015-01-01','硕士','ffd','ffd','ffd',0,'ffd'),
 (40,9,'20150501','125','F','1980-01-01','568','2016-02-01','本科','659','+6+','656',2,'0..03'),
 (41,9,'20150502','````','F','1982-03-03','```','2016-11-01','专科','```','````','```',0,'```'),
 (43,10,'20150602','王羲之','M','1980-01-01','狂书学院','2015-01-01','硕士','654564','4@444.com','1',0,'书法'),
 (44,3,'20050102','羊','M','1980-01-01','么么','2015-01-01','硕士','25652','s1421414@163.com','25425',0,'信息');


DROP TABLE IF EXISTS `feeinfo`;
CREATE TABLE `feeinfo` (
  `id` int(10) unsigned NOT NULL auto_increment COMMENT 'id',
  `feeno` varchar(45) default NULL COMMENT '收费编号',
  `stuid` int(10) unsigned default NULL COMMENT '学生id',
  `feenote` varchar(85) default NULL COMMENT '备注',
  `feermb` decimal(10,2) default NULL COMMENT '金额',
  `feedate` datetime NOT NULL COMMENT '日期',
  `adminid` int(10) unsigned default NULL COMMENT '记帐人',
  `feetype` int(1) unsigned default '0' COMMENT '0现金1刷卡2转帐',
  `feeby` varchar(45) default NULL COMMENT '收款人',
  PRIMARY KEY  (`id`),
  KEY `FK_feeinfo_2` (`adminid`),
  KEY `FK_feeinfo_1` (`stuid`),
  CONSTRAINT `FK_feeinfo_1` FOREIGN KEY (`stuid`) REFERENCES `stuinfo` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `FK_feeinfo_2` FOREIGN KEY (`adminid`) REFERENCES `admin` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gbk COMMENT='收费明细';


INSERT INTO `feeinfo` (`id`,`feeno`,`stuid`,`feenote`,`feermb`,`feedate`,`adminid`,`feetype`,`feeby`) VALUES 
 (3,'012545421424',16,'sdfdsf','2000.00','2015-03-09 14:37:37',32,0,'admin'),
 (4,'4564sdfsdf',16,'ssssdfd','1000.00','2015-03-09 14:38:52',32,0,'admin'),
 (5,'123115561',16,'sss','500.00','2015-03-09 14:40:20',32,0,'admin'),
 (6,'456123',31,'qwe','300.00','2015-03-09 14:40:41',32,0,'admin'),
 (7,'456268165891',26,' 2014125','5000.00','2015-03-09 15:19:17',32,1,'admin'),
 (8,'123456789',43,'12545','500.00','2015-03-09 15:15:24',32,2,'admin'),
 (9,'456123',43,'45454','1235.00','2015-03-09 15:16:26',32,0,'admin');


DROP TABLE IF EXISTS `priceinfo`;
CREATE TABLE `priceinfo` (
  `id` int(10) unsigned NOT NULL auto_increment COMMENT 'ID',
  `stuid` int(10) unsigned default NULL COMMENT '学生id',
  `orgprice` decimal(10,2) NOT NULL COMMENT '原价',
  `newprice` decimal(10,2) NOT NULL COMMENT '新价',
  PRIMARY KEY  (`id`),
  KEY `FK_priceinfo_1` (`stuid`),
  CONSTRAINT `FK_priceinfo_1` FOREIGN KEY (`stuid`) REFERENCES `stuinfo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=gbk COMMENT='学费价格';


INSERT INTO `priceinfo` (`id`,`stuid`,`orgprice`,`newprice`) VALUES 
 (3,5,'11000.00','8800.00'),
 (6,8,'10000.00','8000.00'),
 (11,13,'10000.00','9800.00'),
 (13,15,'13800.00','8800.00'),
 (14,16,'13800.00','8800.00'),
 (15,17,'15800.00','1200.00'),
 (18,20,'13800.00','8800.00'),
 (20,22,'13800.00','8800.00'),
 (23,25,'13800.00','8800.00'),
 (24,26,'13800.00','8800.00'),
 (25,27,'30.00','29.99'),
 (26,28,'13800.00','8800.00'),
 (27,29,'450.00','430.00'),
 (28,30,'10800.00','5600.00'),
 (29,31,'6000.00','5633.00'),
 (30,32,'10000.00','9999.99'),
 (31,33,'12800.00','8900.00'),
 (32,34,'4000.00','3000.00'),
 (33,36,'14800.00','14800.00'),
 (34,37,'13800.00','8800.00'),
 (35,38,'13800.00','8800.00'),
 (36,39,'13800.00','8800.00'),
 (37,40,'3000.00','2000.00'),
 (38,41,'6565.00','3232.00'),
 (40,43,'13800.00','8800.00'),
 (41,44,'13800.00','8800.00');


