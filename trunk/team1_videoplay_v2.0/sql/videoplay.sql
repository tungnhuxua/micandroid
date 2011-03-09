
/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2010/7/30 23:48:51    create database video; drop database video;                     */
/*==============================================================*/
select * from t_video;
select * from t_video where video_keywords like '%我%' or video_keywords like '%a%' or video_keywords like '%e%' or video_keywords like '%c%' or video_keywords like '%工%' or video_keywords like '%人%'  limit 1,2;
desc t_video;
select * from t_video;
delete from t_playRecord;
drop table if exists t_favourite;

drop table if exists t_payUser;

drop table if exists t_playRecord;

drop table if exists t_review;

drop table if exists t_user;

drop table if exists t_userPay;

drop table if exists t_video;

drop table if exists t_videoType;

create table t_user
(
   user_id              int not null auto_increment,
   user_name            varchar(20) not null,
   user_password        varchar(20) not null,
   user_registedate     date not null,
   user_lastlogindate   date,
   user_point           bigint,
   user_account         varchar(20),
   user_email           varchar(30) not null,
   user_telephone       varchar(11),
   user_money           float(8,2),
   user_state           int,
   user_question		varchar(100),
   user_answer			varchar(100),
   primary key (user_id)
);


create table t_videoType
(
   type_id              int not null auto_increment,
   type_name            varchar(40) not null,
   type_createtime      date,
   type_updatetime      date,
   type_desc            varchar(100),
   type_creater         int,
   type_updater         int,
   primary key (type_id)
);

create table t_video
(
   video_id             int not null auto_increment,
   type_id              int,
   user_id              int,
   video_title          varchar(50) not null,
   video_desc           varchar(200),
   video_point          bigint,
   video_playcount      bigint,
   video_uploadtime     date,
   video_lastplaytime   date,
   video_url            varchar(100) not null,
   video_size           int,
   video_keywords       varchar(50),
   video_checkstate     int not null,
   video_money          float(8,2),
   video_picture        varchar(100),
   video_requirdMoney   int,
   primary key (video_id)
);

create table t_favourite
(
   favourite_id         int not null auto_increment,
   user_id              int,
   video_id             int,
   primary key (favourite_id)
);

create table t_payUser
(
   payuser_id           int not null auto_increment,
   user_id              int,
   payuser_applytime    date,
   payuser_supplytime   date,
   payuser_applymoney   float(8,2),
   payuser_remark       varchar(100),
   payuser_supplymoney  float(8,2),
   payuser_state        int,
   primary key (payuser_id)
);

create table t_playRecord
(
   play_id              int not null auto_increment,
   video_id             int,
   user_id              int,
   play_time            date,
   primary key (play_id)
);

create table t_review
(
   review_id            int not null auto_increment,
   user_id              int,
   video_id             int,
   review_time          date,
   review_content       varchar(200),
   review_score         int,
   primary key (review_id)
);


create table t_userPay
(
   userpay_id           int not null auto_increment,
   user_id              int,
   userpay_date         date,
   userpay_type         int,
   userpay_money        float(8,2),
   primary key (userpay_id)
);


alter table t_favourite add constraint FK_收藏 foreign key (user_id)
      references t_user (user_id) on delete restrict on update restrict;

alter table t_favourite add constraint FK_被用户收藏视频 foreign key (video_id)
      references t_video (video_id) on delete restrict on update restrict;

alter table t_payUser add constraint FK_付给谁 foreign key (user_id)
      references t_user (user_id) on delete restrict on update restrict;

alter table t_playRecord add constraint FK_播放的哪个视频 foreign key (video_id)
      references t_video (video_id) on delete restrict on update restrict;

alter table t_playRecord add constraint FK_谁播放 foreign key (user_id)
      references t_user (user_id) on delete restrict on update restrict;

alter table t_review add constraint FK_评论的哪个视频 foreign key (video_id)
      references t_video (video_id) on delete restrict on update restrict;

alter table t_review add constraint FK_谁评论 foreign key (user_id)
      references t_user (user_id) on delete restrict on update restrict;

alter table t_userPay add constraint FK_谁付的 foreign key (user_id)
      references t_user (user_id) on delete restrict on update restrict;

alter table t_video add constraint FK_属于 foreign key (type_id)
      references t_videoType (type_id) on delete restrict on update restrict;

alter table t_video add constraint FK_谁上传 foreign key (user_id)
      references t_user (user_id) on delete restrict on update restrict;




insert into t_user values(null,'wuduo','111',now(),null,0,'2323232323232','hua@gmail.com','15879175065',0,0,'我的','没有');
insert into t_user values(null,'hua','111',now(),null,0,'2323232323232','hua@gmail.com','15879175065',0,0,'我的','没有');
insert into t_user values(null,'hua','111',now(),null,0,'2323232323232','hua@gmail.com','15879175065',0,0,'我的','没有');
insert into t_user values(null,'hua','111',now(),null,0,'2323232323232','hua@gmail.com','15879175065',0,0,'我的','没有');
insert into t_user values(null,'hua','111',now(),null,0,'2323232323232','hua@gmail.com','15879175065',0,0,'我的','没有');

insert into t_videoType values(null,'javaee',now(),null,'Java方向开发',null,null);
insert into t_videoType values(null,'.net',now(),null,'.net方向开发',null,null);
insert into t_videoType values(null,'andrio',now(),null,'andrio方向开发',null,null);



insert into t_video values(null,1,1,'Java视频','Java很好学',0,0,now(),null,'/java/mm',10,'java',0,0,'d:/haokan',0);
insert into t_video values(null,2,2,'.net视频','.net很好学',0,0,now(),null,'/java/mm',10,'java',0,0,'d:/haokan',0);
insert into t_video values(null,3,3,'andrio视频','andrio很好学',0,0,now(),null,'/java/mm',10,'java',0,0,'d:/haokan',10);
desc t_video;
select * from t_video;
update t_video set video_url='/team1_videoplay_v2.0/upload/2010725/tol24.com01.swf';
insert into t_favourite values(null,1,1);
insert into t_favourite values(null,1,2);
insert into t_favourite values(null,1,3);
insert into t_favourite values(null,2,1);

insert into t_payUser values(null,1,null,now(),100.0,'我要钱',90.0,1);
insert into t_payUser values(null,2,null,now(),100.0,'我要钱1',90.0,1);
insert into t_payUser values(null,3,null,now(),100.0,'我要钱2',90.0,1);
insert into t_payUser values(null,1,null,now(),100.0,'我要钱3',90.0,1);

insert into t_playRecord values(null,1,1,now());
insert into t_playRecord values(null,1,2,now());
insert into t_playRecord values(null,1,3,now());
insert into t_playRecord values(null,2,1,now());

insert into t_review values(null,1,1,now(),'这个视频太好了1',5);
insert into t_review values(null,1,2,now(),'这个视频太好了2',5);
insert into t_review values(null,2,2,now(),'这个视频太好了3',5);
insert into t_review values(null,2,1,now(),'这个视频太好了4',5);
insert into t_review values(null,3,3,now(),'这个视频太好了5',5);

insert into t_userPay values(null,1,now(),1,100.0);
insert into t_userPay values(null,1,now(),2,100.0);
insert into t_userPay values(null,2,now(),1,100.0);
insert into t_userPay values(null,2,now(),1,100.0);

select * from t_user;

