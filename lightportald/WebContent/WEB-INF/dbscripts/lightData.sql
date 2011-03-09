/*Light Portal references data
mysql -p -h DBSERVER portal < lightData.sql
mysqldump -u DBUSER -p portal > lightData.sql
mysqldump -u -p portal > lightData.sql

SHOW TABLE STATUS;
show variables like "%character%";show variables like "%collation%";
/etc/rc.d/init.d/mysqld restart //restart mysql
vi /etc/my.cnf //change mysql configuration parameter
[client]
default-character-set=utf8
[mysql]
default-character-set=utf8
[mysqld]
default-character-set=utf8
*/

SET NAMES utf8;

SET SQL_MODE='';
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

/*Data for the table `light_calendar_holiday` */

insert into `light_calendar_holiday` (`id`,`holiday`,`name`,`country`,`description`,`link`) values (1,'01-01','New Year',NULL,'January 1 is the 1st day of the year in the Gregorian calendar. There are 364 days remaining (365 in leap years). The preceding day is December 31 of the previous year.','http://en.wikipedia.org/wiki/January_1');
insert into `light_calendar_holiday` (`id`,`holiday`,`name`,`country`,`description`,`link`) values (2,'02-14','Valentine\'s Day',NULL,'Valentine Day is a holiday on February 14. It is the traditional day on which lovers express their love for each other; sending Valentine cards or candy. It is very common to present flowers on Valentine Day. The holiday is named after two men, both Christian martyrs among the numerous Early Christian martyrs named Valentine. The day became associated with romantic love in the circle of Geoffrey Chaucer in High Middle Ages, when the tradition of courtly love flourished.','http://en.wikipedia.org/wiki/Valentine%27s_Day');
insert into `light_calendar_holiday` (`id`,`holiday`,`name`,`country`,`description`,`link`) values (3,'04-01','April Fools\' Day',NULL,'though not a holiday in its own right, is a notable day celebrated in many countries on April 1. The day is marked by the commission of hoaxes and other practical jokes of varying sophistication on friends, enemies and neighbours, or sending them on fools errands, the aim of which is to embarrass the gullible.','http://en.wikipedia.org/wiki/April_Fools%27_Day');
insert into `light_calendar_holiday` (`id`,`holiday`,`name`,`country`,`description`,`link`) values (4,'07-04','Independence Day (United States)','US','Independence Day (commonly known as the Fourth of July) is a federal holiday commemorating the adoption of the Declaration of Independence on July 4, 1776, declaring independence from the Kingdom of Great Britain.','http://en.wikipedia.org/wiki/Independence_Day_%28United_States%29');
insert into `light_calendar_holiday` (`id`,`holiday`,`name`,`country`,`description`,`link`) values (5,'10-31','Halloween',NULL,'Halloween is a holiday celebrated on the night of October 31. Traditional activities include trick-or-treating, Halloween festivals, bonfires, costume parties, visiting \"haunted houses\" and viewing horror films. Halloween originated from the Pagan festival Samhain, celebrated among the Celts of Ireland and Great Britain. Irish and Scottish immigrants carried versions of the tradition to North America in the nineteenth century. Other western countries embraced the holiday in the late twentieth century. Halloween is now celebrated in several parts of the western world, most commonly in Ireland, the United States, Canada, Puerto Rico, and the United Kingdom.','http://en.wikipedia.org/wiki/Halloween');
insert into `light_calendar_holiday` (`id`,`holiday`,`name`,`country`,`description`,`link`) values (6,'12-25','Christmas Day',NULL,'Christmas is an annual holiday that celebrates the birth of Jesus. Christmas festivities often combine the commemoration of Jesus\' birth with various customs, many of which have been influenced by earlier winter festivals. Traditions include the display of Nativity scenes, Holly and Christmas trees, the exchange of gifts and cards, and the arrival of Father Christmas (Santa Claus) on Christmas Eve or Christmas morning. Popular Christmas themes include the promotion of goodwill, compassion and peace.','http://en.wikipedia.org/wiki/Christmas');
insert into `light_calendar_holiday` (`id`,`holiday`,`name`,`country`,`description`,`link`) values (10,'2008-11-27','Thanksgiving Day','US','Thanksgiving, or Thanksgiving Day, is a traditional North American holiday to give thanks at the conclusion of the harvest season. Thanksgiving is celebrated on the fourth Thursday of November in the United States.\r\n\r\nIn the United States, Thanksgiving is a four day weekend which usually marks a pause in school and college calendars. Thanksgiving meals are traditionally family events where certain kinds of food are served. First and foremost, turkey is the featured item in most Thanksgiving feasts (so much so that Thanksgiving is sometimes called \"Turkey Day\"). Stuffing, mashed potatoes with gravy, sweet potatoes, cranberry sauce, corn, turnips, yams and pumpkin pie are commonly associated with Thanksgiving dinner.','http://en.wikipedia.org/wiki/Thanksgiving');
insert into `light_calendar_holiday` (`id`,`holiday`,`name`,`country`,`description`,`link`) values (14,'2008-10-06','Thanksgiving Day (Canada)','CA','Thanksgiving, or Thanksgiving Day, is a traditional North American holiday to give thanks at the conclusion of the harvest season. Thanksgiving is celebrated  on the second Monday of October in Canada.\r\nIn Canada, Thanksgiving is a three day weekend (although some provinces observe a four day weekend, Friday to Monday). Traditional Thanksgiving meals prominently feature turkey, stuffing, cranberry sauce and mashed potatoes, though Canada multicultural heritage has seen some families infuse this traditional meal with elements of their traditional ethnic foods. Many Canadians also consume pumpkin pie after their meal.','http://en.wikipedia.org/wiki/Thanksgiving');
insert into `light_calendar_holiday` (`id`,`holiday`,`name`,`country`,`description`,`link`) values (21,'2007-02-18','Chinese New Year','CN','Chinese New Year or Spring Festival or the Lunar New Year is the most important of the traditional Chinese holidays. It is an important holiday in East Asia. The festival proper begins on the first day of the first lunar month ','http://en.wikipedia.org/wiki/Chinese_New_Year');

/*Data for the table `light_horoscope` */

insert into `light_horoscope` (`ID`,`name`,`title`,`description`,`startDate`,`endDate`,`startMonth`,`startDay`,`endMonth`,`endDay`) values (1,'Aries','portlet.label.horoscope.Aries','portlet.label.horoscope.Aries.desc','March 21','April 20',3,21,4,20);
insert into `light_horoscope` (`ID`,`name`,`title`,`description`,`startDate`,`endDate`,`startMonth`,`startDay`,`endMonth`,`endDay`) values (2,'Taurus','portlet.label.horoscope.Taurus','portlet.label.horoscope.Taurus.desc','April 21','May 21',4,21,5,21);
insert into `light_horoscope` (`ID`,`name`,`title`,`description`,`startDate`,`endDate`,`startMonth`,`startDay`,`endMonth`,`endDay`) values (3,'Gemini','portlet.label.horoscope.Gemini','portlet.label.horoscope.Gemini.desc','May 22','June 21',5,22,6,21);
insert into `light_horoscope` (`ID`,`name`,`title`,`description`,`startDate`,`endDate`,`startMonth`,`startDay`,`endMonth`,`endDay`) values (4,'Cancer','portlet.label.horoscope.Cancer','portlet.label.horoscope.Cancer.desc','June 22','July 23',6,22,7,23);
insert into `light_horoscope` (`ID`,`name`,`title`,`description`,`startDate`,`endDate`,`startMonth`,`startDay`,`endMonth`,`endDay`) values (5,'Leo','portlet.label.horoscope.Leo','portlet.label.horoscope.Leo.desc','July 24','Auguest 23',7,24,8,23);
insert into `light_horoscope` (`ID`,`name`,`title`,`description`,`startDate`,`endDate`,`startMonth`,`startDay`,`endMonth`,`endDay`) values (6,'Virgo','portlet.label.horoscope.Virgo','portlet.label.horoscope.Virgo.desc','Auguest 24','September 23',8,24,9,23);
insert into `light_horoscope` (`ID`,`name`,`title`,`description`,`startDate`,`endDate`,`startMonth`,`startDay`,`endMonth`,`endDay`) values (7,'Libra','portlet.label.horoscope.Libra','portlet.label.horoscope.Libra.desc','September 24','October 23',9,24,10,23);
insert into `light_horoscope` (`ID`,`name`,`title`,`description`,`startDate`,`endDate`,`startMonth`,`startDay`,`endMonth`,`endDay`) values (8,'Scorpio','portlet.label.horoscope.Scorpio','portlet.label.horoscope.Scorpio.desc','October 24','November 22',10,24,11,22);
insert into `light_horoscope` (`ID`,`name`,`title`,`description`,`startDate`,`endDate`,`startMonth`,`startDay`,`endMonth`,`endDay`) values (9,'Sagittarius','portlet.label.horoscope.Sagittarius','portlet.label.horoscope.Sagittarius.desc','November 23','December 21',11,23,12,21);
insert into `light_horoscope` (`ID`,`name`,`title`,`description`,`startDate`,`endDate`,`startMonth`,`startDay`,`endMonth`,`endDay`) values (10,'Capricorn','portlet.label.horoscope.Capricorn','portlet.label.horoscope.Capricorn.desc','December 22','January 20',12,22,1,20);
insert into `light_horoscope` (`ID`,`name`,`title`,`description`,`startDate`,`endDate`,`startMonth`,`startDay`,`endMonth`,`endDay`) values (11,'Aquarius','portlet.label.horoscope.Aquarius','portlet.label.horoscope.Aquarius.desc','January 21','February 19',1,21,2,19);
insert into `light_horoscope` (`ID`,`name`,`title`,`description`,`startDate`,`endDate`,`startMonth`,`startDay`,`endMonth`,`endDay`) values (12,'Pisces','portlet.label.horoscope.Pisces','portlet.label.horoscope.Pisces.desc','February 20','March 20',2,20,3,20);

/*Data for the table `light_horoscope_weekly` */

insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (1,1,'en','Aries: A promise someone made to you will turn out to be a lot more complicated than it first seemed. Try to keep an open mind in case it doesn\'t work out.',28);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (2,2,'en','Taurus: You\'ve been trying to nail down future plans, but nothing\'s getting accomplished. Take the lead and set a date, or it will never happen.',28);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (3,3,'en','Gemini: You\'ve been nervous to broach a difficult subject with someone, because they get very emotional. Don\'t let that stop you - you\'ve got to talk about it.',28);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (4,4,'en','Cancer: Try not to be hypercritical of people\'s mistakes this week. You wouldn\'t want anyone else to be that judgmental towards you. Be forgiving.',28);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (5,5,'en','Leo: Your priorities have been changing lately, and something that used to seem like a huge deal to you just isn\'t as important. This is a good thing.',28);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (6,6,'en','Virgo: Someone will make a comment that\'ll hurt your pride, but try not to be too bothered by it. Consider the source - is their opinion the one that matters?',28);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (7,7,'en','Libra: A person who recently entered your life will surprise you when they aren\'t like you thought they were. Don\'t rely too heavily on your first impressions.',28);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (8,8,'en','Scorpio: You\'ll find yourself working surprisingly well with someone you didn\'t expect you would. Be open and don\'t dismiss their ideas right away.',28);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (9,9,'en','Sagittarius: You\'ve been feeling a lot of guilt and regret about something that happened in the past, but it\'s not too late to make it right, if you want to.',28);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (10,10,'en','Capricorn: Sometimes you find more joy in planning an event than the event itself. Don\'t forget to take time to enjoy something you\'ve been looking forward to.',28);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (11,11,'en','Aquarius: A new person in your life will provide a welcome change of direction. Be open to learning from them, and you\'ll greatly expand your horizons.',28);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (12,12,'en','Pisces: A friend has been hinting around at something lately but doesn\'t want to come right out and say it. It\'s time to be blunt and ask what\'s on their mind.',28);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (13,1,'en','Aries: A friend has been acting really strange around you lately, and you\'re getting suspicious. Don\'t be - they are planning an exciting surprise for you.',29);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (14,2,'en','Taurus: Tired of friends canceling plans or flaking out at the last minute? Stop making plans with them, and they\'ll get the hint.',29);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (15,3,'en','Gemini: You\'ll be flattered and excited when someone starts being extra nice and complimentary to you this week. Be careful - they want something.',29);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (16,4,'en','Cancer: A bad habit you thought you\'d broken will begin to tempt you again. Don\'t indulge, or you\'ll undo all that hard work.',29);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (17,5,'en','Leo: Someone you haven\'t spoken to in ages will apologize for a tiff you\'d long forgotten about. Accept their apology, and everyone will feel better.',29);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (18,6,'en','Virgo: Be wary of an offer that seems too good to be true. Check out the fine print, or discuss all the details before rushing into it.',29);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (19,7,'en','Libra: A friend will try to get you to change your mind or plans that you\'ve been excited about. Stick with your original idea or you might end up resentful.',29);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (20,8,'en','Scorpio: Someone special has recently come into your life. Though you may not know it yet, they will play a very important role in your future.',29);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (21,9,'en','Sagittarius: You will be forced to make a small sacrifice to benefit someone else. It will be much appreciated and definitely worth it in the end.',29);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (22,10,'en','Capricorn: Don\'t let your emotions show in an inappropriate setting this week. Fake a smile until you\'re in private and can let out your real feelings.',29);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (23,11,'en','Aquarius: Let someone else have the last word. You\'ll be tempted to talk back, but don\'t stoop down to their level. They\'ll be the one to apologize later.',29);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (24,12,'en','Pisces: An awkward subject will come up, and you\'ll be tempted to change the subject. Don\'t - a weight will be lifted off your shoulders once you talk about it.',29);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (25,1,'en','Aries: You\'re finally beginning to get that stability you need. Don\'t take it for granted. Remember what it was like before you had it.',30);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (26,2,'en','Taurus: How in the world are you going to explain this one? You\'ve gotten yourself in a sticky situation, and need to figure out how to talk your way out of it.',30);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (27,3,'en','Gemini: You\'ve been a bit overwhelmed lately with everything you\'ve had to get done, but an exciting adventure will soon help you leave the stress behind.',30);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (28,4,'en','Cancer: You\'re feeling a bit spacey and not sure if you can get everything done. If you can, save your most important tasks for next week, or even later.',30);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (29,5,'en','Leo: You love being the center of attention, and now is your time to shine. People will soon be lavishing you with attention, and you\'ll eat it up with a spoon!',30);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (30,6,'en','Virgo: Make sure to follow through on a promise you made, even if you don\'t want to. If not, it will come back to haunt you later.',30);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (31,7,'en','Libra: Someone will make a generous offer that you\'re not sure you should accept. Before you decide, find out if there are any ulterior motives.',30);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (32,8,'en','Scorpio: Someone\'s working your last nerve, and you\'re feeling like you\'re about to burst. Talk to the one person who\'s always good at calming you down.',30);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (33,9,'en','Sagittarius: You\'re feeling very anxious about an upcoming event, but you needn\'t worry so much. Everything will go fine, and much better than you\'re expecting.',30);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (34,10,'en','Capricorn: Running into an old friend will bring memories you\'d long forgotten about to the surface. Perfect timing - you\'ve been needing a good laugh!',30);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (35,11,'en','Aquarius: You\'ll have the urge to tell too many personal details to someone inappropriate. Think before you speak or you\'ll end up leaving a bad impression.',30);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (36,12,'en','Pisces: You\'ve got a big deadline looming, and it\'s making you physically ill. Do what you can now, because you\'ll feel worse if you put it off.',30);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (37,1,'en','Aries: It\'s time to let a friend deal with his or her own problems. They haven\'t been taking the advice you\'ve been giving, so it\'s time to stop doling it out.',32);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (38,2,'en','Taurus: Don\'t be too disappointed if something you\'ve been looking forward doesn\'t go as planned this week. You\'ve got to go with the flow.',32);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (39,3,'en','Gemini: One mistake does not equal failure. If you mess something up, don\'t get down on yourself. It has nothing to do with who you are as a person.',32);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (40,4,'en','Cancer: You\'ve been stressing about making a phone call for ages, and it\'s time to do it. You\'ll feel so much better when it\'s off your chest.',32);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (41,5,'en','Leo: It\'s time to finalize some upcoming plans. If you put things off for too long, you won\'t get to participate in something you\'ve been looking forward to.',32);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (42,6,'en','Virgo: You\'re feeling quite domestic lately. Try a new recipe, gardening or a redecorating project to channel your creativity.',32);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (43,7,'en','Libra: It\'s important to get your financials in order this week. Upcoming expenses could throw you for a loop if you aren\'t prepared.',32);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (44,8,'en','Scorpio: It\'s time to organize and clear out some paperwork. You might come across something you forgot about that you need to get done ASAP.',32);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (45,9,'en','Sagittarius: Just because you ask for advice does not mean you need to take it. Be careful that the person giving you advice isn\'t pushing their own agenda.',32);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (46,10,'en','Capricorn: Someone will ask you for a big favor this week. Don\'t feel bad if you need time to think it over. You have to consider your own needs.',32);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (47,11,'en','Aquarius: It might be hard for you to tell right from wrong this week. Think about your decisions and make sure they\'re fair for everyone, not just you.',32);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (48,12,'en','Pisces: An offhand comment you make to someone will affect them in a way you didn\'t expect. Don\'t overthink what you say, but do be kind.',32);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (49,1,'en','Aries: You\'ve got to face it - some people never change. You\'re the one who can change, by cutting them out of your life.',33);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (50,2,'en','Taurus: A friend will share a secret, and you\'ll feel guilty for not noticing sooner. All you can do now is provide support and be a friend.',33);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (51,3,'en','Gemini: You\'re caught in between two friends who really don\'t get along. Sometimes, you just have to take sides to salvage at least one friendship.',33);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (52,4,'en','Cancer: Unfortunately, things aren\'t going quite as planned. Try and make the best of the situation, because there\'s not much you can do about it.',33);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (53,5,'en','Leo: You feel like a friend wronged you in the past, but wonder if it\'s too late to say anything. Better to get it off your chest than continuing being upset.',33);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (54,6,'en','Virgo: You\'ve been interacting with some negative people lately, and it\'s bringing you down. Spend time with some people who lift up your spirits.',33);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (55,7,'en','Libra: It\'s time to speak up about what you need from someone. They haven\'t figured it out on their own, so you need to be crystal clear.',33);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (56,8,'en','Scorpio: Be careful before booking travel plans. Triple-check the dates and make sure you don\'t have any conflicts.',33);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (57,9,'en','Sagittarius: Being at the right place at the right time sometime this week will cause something very exciting to happen when you least expect it.',33);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (58,10,'en','Capricorn: No matter how much caffeine or fresh air you get today, you can\'t seem to clear your head. Try not to work on important tasks if you can avoid them.',33);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (59,11,'en','Aquarius: When someone pays you a compliment this week, resist the urge to say \"this old thing?\" Accept it gracefully and enjoy the praise.',33);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (60,12,'en','Pisces: Be careful not to ask someone for too many favors this week. You don\'t want to push it, so start small.',33);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (61,1,'en','Aries: A sweet deal coming your way may be too good to be true. Do your research and see if it\'s legit. If it is, go for it.',34);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (62,2,'en','Taurus: You recently lost something very important to you, and you thought you\'d never see it again. Well, lo and behold, you\'ll rediscover it this week.',34);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (63,3,'en','Gemini: You\'ve been putting off making travel plans for ages - waiting for a better deal, to hear back from someone, etc. It\'s time to make the final plans now.',34);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (64,4,'en','Cancer: All work and no play? Bad. All play and no work? Not good either. You need to work a little harder to strike a balance between these two.',34);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (65,5,'en','Leo: Big changes are about to happen in your life. As much as you think you\'re prepared, some will still take you by surprise, so expect the unexpected.',34);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (66,6,'en','Virgo: It feels like everything in your life is out of your control lately. But you can do something about that by being vocal and standing up for yourself.',34);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (67,7,'en','Libra: Someone\'s trying to pull you into their drama, and you\'re trying hard to fight it. Time to put your foot down and tell them you will NOT get involved.',34);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (68,8,'en','Scorpio: Keep your mouth shut when someone asks you what you think of their new romantic partner. Nothing good will come out of being honest in this situation.',34);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (69,9,'en','Sagittarius: You\'ve been really bored with the same old routine lately, and feel the need for a challenge. Not to worry. You\'re about to get a BIG one.',34);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (70,10,'en','Capricorn: If you didn\'t want to know what someone really thought, why did you ask? Don\'t get upset with a friend who gave an honest opinion.',34);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (71,11,'en','Aquarius: You\'ve been trying to take a relationship to a deeper level, but you feel like you\'re the only one making an effort. Time to have a heart-to-heart.',34);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (72,12,'en','Pisces: Someone new has come into your circle, and you\'re not sure if they fit in. Make an effort to get to know them and you\'ll be pleasantly surprised.',34);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (73,1,'en','Aries: An impression you had of someone is starting to change. Realize that first impressions can be wrong, and figure out how to deal with this new image.',35);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (74,2,'en','Taurus: You\'re feeling guilty about a comment you made to a friend that came out the wrong way. Perhaps putting things in writing will clear it all up.',35);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (75,3,'en','Gemini: You\'ve been frustrated with the lack of organization in your social circle lately, so nominate yourself to be in charge of making fun plans.',35);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (76,4,'en','Cancer: You\'ll have to make a quick decision that could change how people view you in the future. Make the choice that puts you first.',35);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (77,5,'en','Leo: You were recently given a piece of advice that you immediately blew off, but you\'re starting to realize that maybe you should take it after all.',35);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (78,6,'en','Virgo: A falling-out between two friends is forcing you to take sides. Speak up and tell your friends you will not be caught in the middle.',35);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (79,7,'en','Libra: Be careful not to be too gossipy or spread rumors this week. Being too chatty could come back to haunt you later.',35);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (80,8,'en','Scorpio: You\'re about to reach a turning point in a romantic relationship. It\'s time for both of you to decide what you want, and be clear with each other.',35);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (81,9,'en','Sagittarius: You\'re about to take a big risk, and are naturally nervous. But you\'ll never achieve your dreams if you don\'t take a leap of faith now and then.',35);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (82,10,'en','Capricorn: You\'ve been feeling a bit out-of-sorts lately. Do something that comforts you until you start feeling like yourself again.',35);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (83,11,'en','Aquarius: You\'re tired of hearing a friend talk about the same thing over and over again. But be patient, and you\'ll be rewarded for being such a good listener.',35);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (84,12,'en','Pisces: You\'re fiercely loyal, but feel you haven\'t been paid the same respect. You can\'t control others, so continue to treat \'em as you wish they\'d treat you.',35);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (85,1,'en','Aries: Trust is something you have to earn, and someone\'s trying to earn it from you. Stop being so suspicious - believe it or not, their intentions are good.',38);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (86,2,'en','Taurus: Someone who isn\'t your usual type keeps catching your eye. There may be a deeper reason you aren\'t being drawn to what you usually like.',38);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (87,3,'en','Gemini: Are you taking someone for granted? Think about it - you may not feel like you are, but they sure do. Make sure your loved ones feel appreciated.',38);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (88,4,'en','Cancer: You\'re feeling very social lately, but it seems like no one else is feeling it. If your friends are busy, venture out on your own to meet some new ones!',38);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (89,5,'en','Leo: One of your relationships has been really one-sided lately, and you\'re fed up. You need to share how you feel if you want things to change.',38);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (90,6,'en','Virgo: You\'ll find inspiration in the most unlikely places today. Try to take everything in, and if something unusual is motivating you, don\'t fight it.',38);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (91,7,'en','Libra: Things won\'t go perfectly today, and you\'re gonna have to be ok with that. Stop wasting time fixing up every little detail, and just do the best you can.',38);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (92,8,'en','Scorpio: An exciting money-making opportunity will come your way, but do your research and make sure it\'s legitimate before you jump on it.',38);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (93,9,'en','Sagittarius: You need to take charge to get things done. If you want your ideas to be the ones that stand out, take the lead before someone else does.',38);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (94,10,'en','Capricorn: A risky situation will have you debating which path to take. Weigh your options, but remember: you\'ll never know if you don\'t try.',38);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (95,11,'en','Aquarius: Be careful not to make a promise you can\'t keep. You always want to say yes to people, but don\'t do it if you know you might flake later.',38);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (96,12,'en','Pisces: When someone comes to you for advice today, think carefully about what you want to say before just spouting out the first thing that comes to mind.',38);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (97,1,'en','Aries: Go ahead, vent. Something is seriously bugging you, but you haven\'t shared it with anyone. You need to talk about it so you don\'t boil over.',41);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (98,2,'en','Taurus: You\'re waiting for an important phone call or email, and can\'t stop checking. Take a little break and do something fun to make the time go faster.',41);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (99,3,'en','Gemini: Don\'t take a friend\'s attitude personally. Yeah, she or he isn\'t being too nice, but they\'ll snap out of it soon. Everyone has their moments.',41);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (100,4,'en','Cancer: You\'ll finally find the solution to something that\'s been bugging you if you take a break from it for a little while. The answer will come to you.',41);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (101,5,'en','Leo: Even though you wish it weren\'t this way, you can\'t help who you like. You\'re being drawn to someone inappropriate for you. But is it worth it?',41);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (102,6,'en','Virgo: Someone who hurt you in the past is begging for forgiveness. You know they\'re no good, so tell them you can forgive, but you\'ll never forget.',41);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (103,7,'en','Libra: You\'re feeling resentful towards a family member who hurt you in the past. Being angry won\'t get you anywhere, so work on forgiving and moving forward.',41);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (104,8,'en','Scorpio: You will be presented with a situation that tests your moral standards. Do what you feel is right, no matter what anyone else says.',41);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (105,9,'en','Sagittarius: Some of the best things in life actually do come easily. Don\'t question something that seems too good to be true. It actually isn\'t this time.',41);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (106,10,'en','Capricorn: Good for you! You\'ve recently given up a bad habit that was hard to shake. No backtracking now. One day at a time.',41);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (107,11,'en','Aquarius: You\'re not convinced someone a friend just started dating is good for them. Bite your tongue for now. They might turn out not to be so bad after all.',41);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (108,12,'en','Pisces: You\'ve been making some really smart decisions for your future lately. Don\'t stop now. Stick with your new habits.',41);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (109,1,'en','Aries: You\'ve been on a bit of a roller coaster for the past year, but it\'s finally settling down. Do what you can do avoid getting back on it.',42);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (110,2,'en','Taurus: No more wasting time. You\'ve got to get something done, now. Think about how good you\'ll feel when it\'s over and done with!',42);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (111,3,'en','Gemini: You feel like you\'ll never get over a situation you\'re going through now, but you need to realize that in a few years the pain will have faded greatly.',42);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (112,4,'en','Cancer: Don\'t count on getting an extension on a project or suddenly coming into some money. Just do your best and take care of what you need to get done.',42);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (113,5,'en','Leo: You\'re trying to be vague so you don\'t hurt someone\'s feelings, but you\'ve got to speak up or things won\'t go the way you want.',42);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (114,6,'en','Virgo: A heart to heart with a family member will help you realize that you\'ve gotten off-track lately, and exactly how you can get back on the right path.',42);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (115,7,'en','Libra: You\'re having trouble striking a balance between what you need and want to do. If you don\'t procrastinate on work, it\'ll be easier to make time for fun.',42);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (116,8,'en','Scorpio: You\'re having trouble communicating with someone. You don\'t get them and they don\'t get you. Try to speak their \"language\" to get your point across.',42);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (117,9,'en','Sagittarius: You feel like you made a bad first impression on someone, but it turns out they didn\'t think so. Guess you didn\'t need to overanalyze it after all!',42);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (118,10,'en','Capricorn: Just when you finally thought you cut someone out of your life, they\'ll come crawling back. Don\'t let them, unless you\'re up for some major drama.',42);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (119,11,'en','Aquarius: Be careful not to make plans you don\'t intend on keeping. Someone might take something you say seriously, and then be crushed when you cancel.',42);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (120,12,'en','Pisces: It\'s hard to stop thinking about (and overanalyzing) a romantic situation you\'re dealing with, but do your best. Time for some fun distractions!',42);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (121,1,'en','Aries: You\'re a very generous person, but it can take a toll on you. Carve out some time to be generous to yourself.',43);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (122,2,'en','Taurus: You\'ve been taking on too much at once. Take a break from multitasking and focus on one thing at a time.',43);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (123,3,'en','Gemini: Sometimes tasks seem harder than they really are when you procrastinate. Do something you\'ve been putting off.',43);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (124,4,'en','Cancer: A hobby of yours could turn into a successful career if you do your research and take some initiative now.',43);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (125,5,'en','Leo: You\'re caught between telling the truth and being tactful. Think carefully and you\'ll strike the perfect balance.',43);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (126,6,'en','Virgo: Be careful not to overreact in a professional situation. Keep calm and wait to vent to someone you trust.',43);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (127,7,'en','Libra: A family member is feeling put out because you\'ve been so busy with life. Let them know how important they are.',43);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (128,8,'en','Scorpio: A friend\'s generosity will inspire you to do good deeds of your own. Be thankful for such positive influences.',43);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (129,9,'en','Sagittarius: You\'re not a failure if you ask for help - or hire someone - to finish a task that\'s taking way too long.',43);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (130,10,'en','Capricorn: A big relief from the stress you\'re facing is coming. You just have to get through a few more obstacles.',43);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (131,11,'en','Aquarius: Someone who is about to enter your life will help you make sense of those vivid dreams you\'ve been having.',43);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (132,12,'en','Pisces: Play by the rules this time, even if it\'d be easier to take shortcuts. You can\'t risk getting in trouble now.',43);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (133,1,'en','Aries: Everyone doubted a recent decision you made, but now they\'re beginning to see what a smart choice it was. Try not to gloat..',48);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (134,2,'en','Taurus: Yes, things have been crazy lately, but that doesn\'t mean you don\'t deserve a break. Take some downtime to recharge, no matter how short it is..',48);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (135,3,'en','Gemini: You\'ve been going back and forth on a decision, and finally realized you shouldn\'t do it. Stick with your choice and try to stop debating it! .',48);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (136,4,'en','Cancer: Time to face reality. Someone keeps saying they\'ll change, but they haven\'t done it yet. Are you really willing to wait around any longer?.',48);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (137,5,'en','Leo: Admit it when you don\'t know the answer this week. You\'ll gain a lot more respect than trying to fake your way through it. .',48);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (138,6,'en','Virgo: Time to use your connections! It\'s all about who you know, and who they know. Don\'t be afraid to ask..',48);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (139,7,'en','Libra: You\'ve been saving something for a special occasion, but you know what? Every day you\'re alive is a special occasion, so go for it!.',48);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (140,8,'en','Scorpio: A new hobby you\'ve recently discovered has the potential to dramatically change your life, in a good way. Are you ready to take it all the way?.',48);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (141,9,'en','Sagittarius: For reasons you can\'t explain, you\'ll be able to stay calm in a stressful situation and make things easier for everyone involved. .',48);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (142,10,'en','Capricorn: When you know someone\'s not telling you the truth this week, don\'t call them on it. They\'ll come clean eventually..',48);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (143,11,'en','Aquarius: Be careful to draw the line between personal and academic or professional today. Don\'t bring your drama in when you\'re trying to make an impression..',48);
insert into `light_horoscope_weekly` (`ID`,`horoscopeId`,`language`,`description`,`weekNumber`) values (144,12,'en','Pisces: Sometimes playing dumb actually can work to your advantage. It\'s ok if you don\'t want to step up and figure things out for everyone this time..',48);

/*Data for the table `light_not_keyword` */

insert into `light_not_keyword` (`word`) values ('3g');
insert into `light_not_keyword` (`word`) values ('a');
insert into `light_not_keyword` (`word`) values ('about');
insert into `light_not_keyword` (`word`) values ('add');
insert into `light_not_keyword` (`word`) values ('address');
insert into `light_not_keyword` (`word`) values ('advertise');
insert into `light_not_keyword` (`word`) values ('advertising');
insert into `light_not_keyword` (`word`) values ('against');
insert into `light_not_keyword` (`word`) values ('all');
insert into `light_not_keyword` (`word`) values ('although');
insert into `light_not_keyword` (`word`) values ('always');
insert into `light_not_keyword` (`word`) values ('an');
insert into `light_not_keyword` (`word`) values ('and');
insert into `light_not_keyword` (`word`) values ('another');
insert into `light_not_keyword` (`word`) values ('antitrust');
insert into `light_not_keyword` (`word`) values ('anyway');
insert into `light_not_keyword` (`word`) values ('api');
insert into `light_not_keyword` (`word`) values ('application');
insert into `light_not_keyword` (`word`) values ('are');
insert into `light_not_keyword` (`word`) values ('article');
insert into `light_not_keyword` (`word`) values ('at');
insert into `light_not_keyword` (`word`) values ('auction');
insert into `light_not_keyword` (`word`) values ('b');
insert into `light_not_keyword` (`word`) values ('back');
insert into `light_not_keyword` (`word`) values ('backwater');
insert into `light_not_keyword` (`word`) values ('bar');
insert into `light_not_keyword` (`word`) values ('battle');
insert into `light_not_keyword` (`word`) values ('been');
insert into `light_not_keyword` (`word`) values ('beyond');
insert into `light_not_keyword` (`word`) values ('beyonds');
insert into `light_not_keyword` (`word`) values ('big');
insert into `light_not_keyword` (`word`) values ('bigger');
insert into `light_not_keyword` (`word`) values ('biggest');
insert into `light_not_keyword` (`word`) values ('boat');
insert into `light_not_keyword` (`word`) values ('bought');
insert into `light_not_keyword` (`word`) values ('build');
insert into `light_not_keyword` (`word`) values ('business');
insert into `light_not_keyword` (`word`) values ('buy');
insert into `light_not_keyword` (`word`) values ('by');
insert into `light_not_keyword` (`word`) values ('bypass');
insert into `light_not_keyword` (`word`) values ('c');
insert into `light_not_keyword` (`word`) values ('can');
insert into `light_not_keyword` (`word`) values ('can\'t');
insert into `light_not_keyword` (`word`) values ('catch');
insert into `light_not_keyword` (`word`) values ('change');
insert into `light_not_keyword` (`word`) values ('channel');
insert into `light_not_keyword` (`word`) values ('check');
insert into `light_not_keyword` (`word`) values ('code');
insert into `light_not_keyword` (`word`) values ('come');
insert into `light_not_keyword` (`word`) values ('companies');
insert into `light_not_keyword` (`word`) values ('company');
insert into `light_not_keyword` (`word`) values ('complaint');
insert into `light_not_keyword` (`word`) values ('computer');
insert into `light_not_keyword` (`word`) values ('computing');
insert into `light_not_keyword` (`word`) values ('consider');
insert into `light_not_keyword` (`word`) values ('content');
insert into `light_not_keyword` (`word`) values ('contents');
insert into `light_not_keyword` (`word`) values ('continue');
insert into `light_not_keyword` (`word`) values ('continuous');
insert into `light_not_keyword` (`word`) values ('control');
insert into `light_not_keyword` (`word`) values ('could');
insert into `light_not_keyword` (`word`) values ('couldn\'t');
insert into `light_not_keyword` (`word`) values ('crash');
insert into `light_not_keyword` (`word`) values ('cripple');
insert into `light_not_keyword` (`word`) values ('cruise');
insert into `light_not_keyword` (`word`) values ('d');
insert into `light_not_keyword` (`word`) values ('day');
insert into `light_not_keyword` (`word`) values ('dead');
insert into `light_not_keyword` (`word`) values ('debate');
insert into `light_not_keyword` (`word`) values ('delete');
insert into `light_not_keyword` (`word`) values ('demand');
insert into `light_not_keyword` (`word`) values ('developer');
insert into `light_not_keyword` (`word`) values ('did');
insert into `light_not_keyword` (`word`) values ('digital');
insert into `light_not_keyword` (`word`) values ('do');
insert into `light_not_keyword` (`word`) values ('does');
insert into `light_not_keyword` (`word`) values ('doesn\'t');
insert into `light_not_keyword` (`word`) values ('domain');
insert into `light_not_keyword` (`word`) values ('don\'t');
insert into `light_not_keyword` (`word`) values ('down');
insert into `light_not_keyword` (`word`) values ('drive');
insert into `light_not_keyword` (`word`) values ('e');
insert into `light_not_keyword` (`word`) values ('eight');
insert into `light_not_keyword` (`word`) values ('eighteen');
insert into `light_not_keyword` (`word`) values ('eleven');
insert into `light_not_keyword` (`word`) values ('email');
insert into `light_not_keyword` (`word`) values ('embed');
insert into `light_not_keyword` (`word`) values ('embeded');
insert into `light_not_keyword` (`word`) values ('en');
insert into `light_not_keyword` (`word`) values ('end');
insert into `light_not_keyword` (`word`) values ('enter');
insert into `light_not_keyword` (`word`) values ('enters');
insert into `light_not_keyword` (`word`) values ('escalate');
insert into `light_not_keyword` (`word`) values ('explanation');
insert into `light_not_keyword` (`word`) values ('f');
insert into `light_not_keyword` (`word`) values ('fever');
insert into `light_not_keyword` (`word`) values ('fifteen');
insert into `light_not_keyword` (`word`) values ('fingerprint');
insert into `light_not_keyword` (`word`) values ('first');
insert into `light_not_keyword` (`word`) values ('five');
insert into `light_not_keyword` (`word`) values ('fool');
insert into `light_not_keyword` (`word`) values ('for');
insert into `light_not_keyword` (`word`) values ('former');
insert into `light_not_keyword` (`word`) values ('four');
insert into `light_not_keyword` (`word`) values ('fourteen');
insert into `light_not_keyword` (`word`) values ('free');
insert into `light_not_keyword` (`word`) values ('frenzy');
insert into `light_not_keyword` (`word`) values ('from');
insert into `light_not_keyword` (`word`) values ('frontier');
insert into `light_not_keyword` (`word`) values ('g');
insert into `light_not_keyword` (`word`) values ('gear');
insert into `light_not_keyword` (`word`) values ('get');
insert into `light_not_keyword` (`word`) values ('getting');
insert into `light_not_keyword` (`word`) values ('go');
insert into `light_not_keyword` (`word`) values ('going');
insert into `light_not_keyword` (`word`) values ('great');
insert into `light_not_keyword` (`word`) values ('grid');
insert into `light_not_keyword` (`word`) values ('grind');
insert into `light_not_keyword` (`word`) values ('group');
insert into `light_not_keyword` (`word`) values ('guide');
insert into `light_not_keyword` (`word`) values ('guides');
insert into `light_not_keyword` (`word`) values ('h');
insert into `light_not_keyword` (`word`) values ('has');
insert into `light_not_keyword` (`word`) values ('hasn\'t');
insert into `light_not_keyword` (`word`) values ('have');
insert into `light_not_keyword` (`word`) values ('he');
insert into `light_not_keyword` (`word`) values ('heard');
insert into `light_not_keyword` (`word`) values ('help');
insert into `light_not_keyword` (`word`) values ('her');
insert into `light_not_keyword` (`word`) values ('his');
insert into `light_not_keyword` (`word`) values ('hit');
insert into `light_not_keyword` (`word`) values ('hour');
insert into `light_not_keyword` (`word`) values ('house');
insert into `light_not_keyword` (`word`) values ('housing');
insert into `light_not_keyword` (`word`) values ('how');
insert into `light_not_keyword` (`word`) values ('hundred');
insert into `light_not_keyword` (`word`) values ('i');
insert into `light_not_keyword` (`word`) values ('I\'ve');
insert into `light_not_keyword` (`word`) values ('if');
insert into `light_not_keyword` (`word`) values ('in');
insert into `light_not_keyword` (`word`) values ('insert');
insert into `light_not_keyword` (`word`) values ('integrate');
insert into `light_not_keyword` (`word`) values ('integration');
insert into `light_not_keyword` (`word`) values ('internal');
insert into `light_not_keyword` (`word`) values ('into');
insert into `light_not_keyword` (`word`) values ('introduce');
insert into `light_not_keyword` (`word`) values ('introducing');
insert into `light_not_keyword` (`word`) values ('introduction');
insert into `light_not_keyword` (`word`) values ('it\'s');
insert into `light_not_keyword` (`word`) values ('j');
insert into `light_not_keyword` (`word`) values ('job');
insert into `light_not_keyword` (`word`) values ('join');
insert into `light_not_keyword` (`word`) values ('k');
insert into `light_not_keyword` (`word`) values ('keyword');
insert into `light_not_keyword` (`word`) values ('kill');
insert into `light_not_keyword` (`word`) values ('l');
insert into `light_not_keyword` (`word`) values ('last');
insert into `light_not_keyword` (`word`) values ('launch');
insert into `light_not_keyword` (`word`) values ('launching');
insert into `light_not_keyword` (`word`) values ('leader');
insert into `light_not_keyword` (`word`) values ('less');
insert into `light_not_keyword` (`word`) values ('lesson');
insert into `light_not_keyword` (`word`) values ('limit');
insert into `light_not_keyword` (`word`) values ('list');
insert into `light_not_keyword` (`word`) values ('loan');
insert into `light_not_keyword` (`word`) values ('look');
insert into `light_not_keyword` (`word`) values ('lower');
insert into `light_not_keyword` (`word`) values ('lucid');
insert into `light_not_keyword` (`word`) values ('m');
insert into `light_not_keyword` (`word`) values ('made');
insert into `light_not_keyword` (`word`) values ('magazine');
insert into `light_not_keyword` (`word`) values ('maintain');
insert into `light_not_keyword` (`word`) values ('major');
insert into `light_not_keyword` (`word`) values ('make');
insert into `light_not_keyword` (`word`) values ('manage');
insert into `light_not_keyword` (`word`) values ('management');
insert into `light_not_keyword` (`word`) values ('market');
insert into `light_not_keyword` (`word`) values ('measure');
insert into `light_not_keyword` (`word`) values ('merge');
insert into `light_not_keyword` (`word`) values ('mine');
insert into `light_not_keyword` (`word`) values ('minute');
insert into `light_not_keyword` (`word`) values ('mobile');
insert into `light_not_keyword` (`word`) values ('month');
insert into `light_not_keyword` (`word`) values ('monthly');
insert into `light_not_keyword` (`word`) values ('mortgage');
insert into `light_not_keyword` (`word`) values ('multimedia');
insert into `light_not_keyword` (`word`) values ('music');
insert into `light_not_keyword` (`word`) values ('myself');
insert into `light_not_keyword` (`word`) values ('n');
insert into `light_not_keyword` (`word`) values ('need');
insert into `light_not_keyword` (`word`) values ('never');
insert into `light_not_keyword` (`word`) values ('new');
insert into `light_not_keyword` (`word`) values ('news');
insert into `light_not_keyword` (`word`) values ('next');
insert into `light_not_keyword` (`word`) values ('nine');
insert into `light_not_keyword` (`word`) values ('nineteen');
insert into `light_not_keyword` (`word`) values ('no');
insert into `light_not_keyword` (`word`) values ('not');
insert into `light_not_keyword` (`word`) values ('now');
insert into `light_not_keyword` (`word`) values ('o');
insert into `light_not_keyword` (`word`) values ('of');
insert into `light_not_keyword` (`word`) values ('offer');
insert into `light_not_keyword` (`word`) values ('on');
insert into `light_not_keyword` (`word`) values ('one');
insert into `light_not_keyword` (`word`) values ('option');
insert into `light_not_keyword` (`word`) values ('or');
insert into `light_not_keyword` (`word`) values ('our');
insert into `light_not_keyword` (`word`) values ('ourself');
insert into `light_not_keyword` (`word`) values ('ourselves');
insert into `light_not_keyword` (`word`) values ('over');
insert into `light_not_keyword` (`word`) values ('p');
insert into `light_not_keyword` (`word`) values ('page');
insert into `light_not_keyword` (`word`) values ('pan');
insert into `light_not_keyword` (`word`) values ('parent');
insert into `light_not_keyword` (`word`) values ('part');
insert into `light_not_keyword` (`word`) values ('pitch');
insert into `light_not_keyword` (`word`) values ('plan');
insert into `light_not_keyword` (`word`) values ('policies');
insert into `light_not_keyword` (`word`) values ('policy');
insert into `light_not_keyword` (`word`) values ('prepare');
insert into `light_not_keyword` (`word`) values ('preparing');
insert into `light_not_keyword` (`word`) values ('price');
insert into `light_not_keyword` (`word`) values ('privacy');
insert into `light_not_keyword` (`word`) values ('push');
insert into `light_not_keyword` (`word`) values ('put');
insert into `light_not_keyword` (`word`) values ('puts');
insert into `light_not_keyword` (`word`) values ('q');
insert into `light_not_keyword` (`word`) values ('r');
insert into `light_not_keyword` (`word`) values ('rate');
insert into `light_not_keyword` (`word`) values ('re-enter');
insert into `light_not_keyword` (`word`) values ('read');
insert into `light_not_keyword` (`word`) values ('ready');
insert into `light_not_keyword` (`word`) values ('require');
insert into `light_not_keyword` (`word`) values ('return');
insert into `light_not_keyword` (`word`) values ('right');
insert into `light_not_keyword` (`word`) values ('roster');
insert into `light_not_keyword` (`word`) values ('rush');
insert into `light_not_keyword` (`word`) values ('s');
insert into `light_not_keyword` (`word`) values ('scramble');
insert into `light_not_keyword` (`word`) values ('script');
insert into `light_not_keyword` (`word`) values ('second');
insert into `light_not_keyword` (`word`) values ('seek');
insert into `light_not_keyword` (`word`) values ('seeks');
insert into `light_not_keyword` (`word`) values ('seiner');
insert into `light_not_keyword` (`word`) values ('select');
insert into `light_not_keyword` (`word`) values ('service');
insert into `light_not_keyword` (`word`) values ('services');
insert into `light_not_keyword` (`word`) values ('seven');
insert into `light_not_keyword` (`word`) values ('seventeen');
insert into `light_not_keyword` (`word`) values ('she');
insert into `light_not_keyword` (`word`) values ('ship');
insert into `light_not_keyword` (`word`) values ('shoe');
insert into `light_not_keyword` (`word`) values ('shopping');
insert into `light_not_keyword` (`word`) values ('shouldn\'t');
insert into `light_not_keyword` (`word`) values ('shove');
insert into `light_not_keyword` (`word`) values ('site');
insert into `light_not_keyword` (`word`) values ('six');
insert into `light_not_keyword` (`word`) values ('sixteen');
insert into `light_not_keyword` (`word`) values ('smart');
insert into `light_not_keyword` (`word`) values ('smash');
insert into `light_not_keyword` (`word`) values ('smashing');
insert into `light_not_keyword` (`word`) values ('social');
insert into `light_not_keyword` (`word`) values ('stake');
insert into `light_not_keyword` (`word`) values ('standard');
insert into `light_not_keyword` (`word`) values ('start');
insert into `light_not_keyword` (`word`) values ('state');
insert into `light_not_keyword` (`word`) values ('still');
insert into `light_not_keyword` (`word`) values ('succeed');
insert into `light_not_keyword` (`word`) values ('t');
insert into `light_not_keyword` (`word`) values ('tab');
insert into `light_not_keyword` (`word`) values ('take');
insert into `light_not_keyword` (`word`) values ('talk');
insert into `light_not_keyword` (`word`) values ('techie');
insert into `light_not_keyword` (`word`) values ('ten');
insert into `light_not_keyword` (`word`) values ('term');
insert into `light_not_keyword` (`word`) values ('test');
insert into `light_not_keyword` (`word`) values ('the');
insert into `light_not_keyword` (`word`) values ('their');
insert into `light_not_keyword` (`word`) values ('there');
insert into `light_not_keyword` (`word`) values ('they');
insert into `light_not_keyword` (`word`) values ('thirteen');
insert into `light_not_keyword` (`word`) values ('this');
insert into `light_not_keyword` (`word`) values ('thousand');
insert into `light_not_keyword` (`word`) values ('three');
insert into `light_not_keyword` (`word`) values ('through');
insert into `light_not_keyword` (`word`) values ('time');
insert into `light_not_keyword` (`word`) values ('to');
insert into `light_not_keyword` (`word`) values ('toilet');
insert into `light_not_keyword` (`word`) values ('too');
insert into `light_not_keyword` (`word`) values ('tool');
insert into `light_not_keyword` (`word`) values ('tooltip');
insert into `light_not_keyword` (`word`) values ('top');
insert into `light_not_keyword` (`word`) values ('trade');
insert into `light_not_keyword` (`word`) values ('trying');
insert into `light_not_keyword` (`word`) values ('TV');
insert into `light_not_keyword` (`word`) values ('TVs');
insert into `light_not_keyword` (`word`) values ('twelve');
insert into `light_not_keyword` (`word`) values ('twenty');
insert into `light_not_keyword` (`word`) values ('two');
insert into `light_not_keyword` (`word`) values ('u');
insert into `light_not_keyword` (`word`) values ('unaffordable');
insert into `light_not_keyword` (`word`) values ('unveil');
insert into `light_not_keyword` (`word`) values ('up');
insert into `light_not_keyword` (`word`) values ('update');
insert into `light_not_keyword` (`word`) values ('us');
insert into `light_not_keyword` (`word`) values ('user');
insert into `light_not_keyword` (`word`) values ('using');
insert into `light_not_keyword` (`word`) values ('v');
insert into `light_not_keyword` (`word`) values ('valley');
insert into `light_not_keyword` (`word`) values ('vedio');
insert into `light_not_keyword` (`word`) values ('w');
insert into `light_not_keyword` (`word`) values ('wall');
insert into `light_not_keyword` (`word`) values ('was');
insert into `light_not_keyword` (`word`) values ('watchdog');
insert into `light_not_keyword` (`word`) values ('way');
insert into `light_not_keyword` (`word`) values ('web');
insert into `light_not_keyword` (`word`) values ('went');
insert into `light_not_keyword` (`word`) values ('were');
insert into `light_not_keyword` (`word`) values ('what');
insert into `light_not_keyword` (`word`) values ('what\'s');
insert into `light_not_keyword` (`word`) values ('when');
insert into `light_not_keyword` (`word`) values ('where');
insert into `light_not_keyword` (`word`) values ('why');
insert into `light_not_keyword` (`word`) values ('will');
insert into `light_not_keyword` (`word`) values ('with');
insert into `light_not_keyword` (`word`) values ('without');
insert into `light_not_keyword` (`word`) values ('word');
insert into `light_not_keyword` (`word`) values ('work');
insert into `light_not_keyword` (`word`) values ('works');
insert into `light_not_keyword` (`word`) values ('would');
insert into `light_not_keyword` (`word`) values ('wouldn\'t');
insert into `light_not_keyword` (`word`) values ('write');
insert into `light_not_keyword` (`word`) values ('wrong');
insert into `light_not_keyword` (`word`) values ('x');
insert into `light_not_keyword` (`word`) values ('y');
insert into `light_not_keyword` (`word`) values ('year');
insert into `light_not_keyword` (`word`) values ('yes');
insert into `light_not_keyword` (`word`) values ('you');
insert into `light_not_keyword` (`word`) values ('you\'ve');
insert into `light_not_keyword` (`word`) values ('your');
insert into `light_not_keyword` (`word`) values ('yourself');
insert into `light_not_keyword` (`word`) values ('yourselves');
insert into `light_not_keyword` (`word`) values ('z');
insert into `light_not_keyword` (`word`) values ('zero');
insert into `light_not_keyword` (`word`) values ('zoo');

/*Data for the table `light_not_word` */

insert into `light_not_word` (`word`) values ('!');
insert into `light_not_word` (`word`) values ('\"');
insert into `light_not_word` (`word`) values ('#');
insert into `light_not_word` (`word`) values ('$');
insert into `light_not_word` (`word`) values ('%');
insert into `light_not_word` (`word`) values ('&');
insert into `light_not_word` (`word`) values ('\'');
insert into `light_not_word` (`word`) values ('(');
insert into `light_not_word` (`word`) values (')');
insert into `light_not_word` (`word`) values ('*');
insert into `light_not_word` (`word`) values ('+');
insert into `light_not_word` (`word`) values (',');
insert into `light_not_word` (`word`) values ('-');
insert into `light_not_word` (`word`) values ('.');
insert into `light_not_word` (`word`) values ('/');
insert into `light_not_word` (`word`) values (':');
insert into `light_not_word` (`word`) values (';');
insert into `light_not_word` (`word`) values ('<');
insert into `light_not_word` (`word`) values ('>');
insert into `light_not_word` (`word`) values ('?');
insert into `light_not_word` (`word`) values ('\\');
insert into `light_not_word` (`word`) values ('_');
insert into `light_not_word` (`word`) values ('|');


INSERT INTO `light_portlet_ref` VALUES 
('yahooRecommended',0,null,'portlet.title.recommended.yahoo','/light/images/feed.png',null,'http://www.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.featured','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.news.yahoo.com/rss/highestrated','role_guest',NULL),
('clipmarks',0,null,'Clipmarks','/light/images/feed.png',null,'http://www.clipmarks.com/','/rssPortlet.lp',NULL,'portlet.tag.title.popular','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.clipmarks.com/popular/','role_guest',NULL),
('blogmarks',0,null,'Public marks','/light/images/feed.png',null,'http://blogmarks.net/marks','/rssPortlet.lp',NULL,'portlet.tag.title.popular','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.blogmarks.net/api/marks?format=rss','role_guest',NULL),
('wordOfDay',0,null,'Word of the Day','/light/images/dictionary.gif',null,'http://dictionary.reference.com/wordoftheday/','/rssPortlet.lp',NULL,'portlet.tag.title.popular','en',1,1,0,1,1,1,NULL,0,0,6,1,0,0,0,0,0,'feed=http://dictionary.reference.com/wordoftheday/wotd.rss','role_guest',NULL),
('howtoOfDay',0,null,'How to of the Day','/light/images/idea.png',null,'http://www.wikihow.com/Main-Page','/rssPortlet.lp',NULL,'portlet.tag.title.popular','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.wikihow.com/feed.rss','role_guest',NULL),
('quotesOfDay',0,null,'Quotes of the Day','/light/images/quote.png',null,'http://www.quotationspage.com/qotd.html','/rssPortlet.lp',NULL,'portlet.tag.title.popular','en',1,1,0,1,1,1,NULL,0,0,1,2,0,0,0,0,0,'feed=http://feeds.feedburner.com/quotationspage/qotd','role_guest',NULL),
('quoteOfDay',0,null,'Quote of the Day','/light/images/quote.png',null,'http://www.brainyquote.com/','/rssPortlet.lp',NULL,'portlet.tag.title.popular','en',1,1,0,1,1,1,NULL,0,0,1,2,0,0,0,0,0,'feed=http://feeds.feedburner.com/brainyquote/QUOTEBR','role_guest',NULL),
('funnyQuoteOfDay',0,null,'Funny Quote of the Day','/light/images/quote.png',null,'http://www.brainyquote.com/','/rssPortlet.lp',NULL,'portlet.tag.title.popular','en',1,1,0,1,1,1,NULL,0,0,1,2,0,0,0,0,0,'feed=http://feeds.feedburner.com/brainyquote/QUOTEFU','role_guest',NULL),
('historyDay',0,null,'This Day in History','http://img.tfd.com/modules/thm_history.png',null,'http://www.thefreedictionary.com','/rssPortlet.lp',NULL,'portlet.tag.title.popular','en',1,1,0,1,1,1,NULL,0,0,6,2,0,0,0,0,0,'feed=http://www.thefreedictionary.com/_/WoD/rss.aspx?type=history','role_guest',NULL),
('jokeOfDay',0,null,'Joke of the Day','/light/images/joke.png',null,'','/jokeOfDay.lp',NULL,'portlet.tag.title.fun','en',1,0,0,1,1,1,NULL,0,0,0,0,0,0,0,1,0,'','role_guest',NULL),
('howStuffWorks',0,null,'HowStuffWorks','http://static.howstuffworks.com/gif/hsw-rss-thumb3.jpg',null,'','/rssPortlet.lp',NULL,'portlet.tag.title.fun','en',1,0,0,1,1,1,NULL,0,0,6,0,0,0,0,1,0,'feed=http://feeds.howstuffworks.com/DailyStuff','role_guest',NULL),
('newestSlang',0,null,'newest slang','/light/images/slang.png',null,'http://www.brainyquote.com/','/rssPortlet.lp',NULL,'portlet.tag.title.popular','en',1,1,0,1,1,1,NULL,0,0,6,2,0,0,0,0,0,'feed=http://www.noslang.com/newslang.xml','role_guest',NULL),
('recipeOfDay',0,null,'Betty Crocker Recipe','http://www.bettycrocker.com/images/global/spoon.gif',null,'http://www.bettycrocker.com/','/rssPortlet.lp',NULL,'portlet.tag.title.recipe','en',1,1,0,1,1,1,NULL,0,0,6,2,0,0,0,0,0,'feed=http://www.bettycrocker.com/recipes/RecipeOfTheDayRSS.aspx','role_guest',NULL),
('recipeOfDay1',0,null,'Allrecipes Dinner Recipes','http://image.allrecipes.com/global/nav/ar_88x31.gif',null,'http://www.allrecipes.com/','/rssPortlet.lp',NULL,'portlet.tag.title.recipe','en',1,1,0,1,1,1,NULL,0,0,6,2,0,0,0,0,0,'feed=http://rss.allrecipes.com/2/3.xml','role_guest',NULL),
('recipeOfDay2',0,null,'Allrecipes Healthy Recipes','http://image.allrecipes.com/global/nav/ar_88x31.gif',null,'http://www.allrecipes.com/','/rssPortlet.lp',NULL,'portlet.tag.title.recipe','en',1,1,0,1,1,1,NULL,0,0,6,2,0,0,0,0,0,'feed=http://rss.allrecipes.com/2/7.xml','role_guest',NULL),
('recipeOfDay3',0,null,'Allrecipes ET Recipes','http://image.allrecipes.com/global/nav/ar_88x31.gif',null,'http://www.allrecipes.com/','/rssPortlet.lp',NULL,'portlet.tag.title.recipe','en',1,1,0,1,1,1,NULL,0,0,6,2,0,0,0,0,0,'feed=http://rss.allrecipes.com/2/6.xml','role_guest',NULL),
('recipeOfDay4',0,null,'Allrecipes Baking Recipes','http://image.allrecipes.com/global/nav/ar_88x31.gif',null,'http://www.allrecipes.com/','/rssPortlet.lp',NULL,'portlet.tag.title.recipe','en',1,1,0,1,1,1,NULL,0,0,6,2,0,0,0,0,0,'feed=http://rss.allrecipes.com/2/1.xml','role_guest',NULL),
('cnnTopStories',0,null,'CNN - Top Stories','http://i.cnn.net/cnn/.element/img/1.0/logo/cnn.logo.rss.gif',null,'http://www.cnn.com/?section=cnn_topstories','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.cnn.com/rss/cnn_topstories.rss','role_guest',NULL),
('cnnWorld',0,null,'CNN - World','http://i.cnn.net/cnn/.element/img/1.0/logo/cnn.logo.rss.gif',null,'http://www.cnn.com/WORLD/?eref=rss_world','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.cnn.com/rss/cnn_world.rss','role_guest',NULL),
('abcTopStories',0,null,'ABC - Top Stories','/light/images/feed.png',null,'http://abcnews.go.com/','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://my.abcnews.go.com/rsspublic/fp_rss20.xml','role_guest',NULL),
('usaToday',0,null,'USA Today','/light/images/feed.png',null,'http://www.usatoday.com/news/digest.htm','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rssfeeds.usatoday.com/usatoday-NewsTopStories','role_guest',NULL),
('bbcNews',0,null,'BBC News','/light/images/feed.png',null,'http://news.bbc.co.uk/','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://newsrss.bbc.co.uk/rss/newsonline_world_edition/front_page/rss.xml','role_guest',NULL),
('cbcTopStories',0,null,'CBC - Top Stories','/light/images/feed.png',null,'http://www.cbc.ca/news/','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.cbc.ca/topstoriesnews.xml','role_guest',NULL),
('msnbcHeadlines',0,null,'MSNBC: HEADLINES','/light/images/feed.png',null,'http://www.msnbc.msn.com','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.msnbc.msn.com/id/3032091/device/rss/rss.xml','role_guest',NULL),
('cbbNews',0,null,'CBS News','/light/images/feed.png',null,'http://www.cbsnews.com/','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.cbsnews.com/feeds/rss/main.rss','role_guest',NULL),
('yahooTopStories',0,null,'portlet.title.top.yahoo','/light/images/feed.png',null,'http://news.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.news.yahoo.com/rss/topstories','role_guest',NULL),
('cnnUS',0,null,'CNN - US','/light/images/feed.png',null,'http://www.cnn.com/rssclick/US/?section=cnn_us','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.cnn.com/rss/cnn_us.rss','role_guest',NULL),
('abcUS',0,null,'ABC - US','/light/images/feed.png',null,'http://abcnews.go.com/US/index','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://my.abcnews.go.com/rsspublic/us_rss20.xml','role_guest',NULL),
('onJava',0,null,'On Java','/light/images/feed.png',null,'http://www.onjava.com/','/rssPortlet.lp','Java','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.oreillynet.com/pub/feed/7?format=rss2','role_guest',NULL),
('javaWorld',0,null,'Java World','/light/images/feed.png',null,'http://www.javaworld.com/','/rssPortlet.lp','Java','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.javaworld.com/index.xml','role_guest',NULL),
('theServerSide',0,null,'The Server Side','/light/images/feed.png',null,'http://www.theserverside.com/','/rssPortlet.lp','Java','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.feedburner.com/techtarget/tsscom/home','role_guest',NULL),
('javaLobby',0,null,'Java Lobby','/light/images/feed.png',null,'http://www.javalobby.org/','/rssPortlet.lp','Java','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.javalobby.org/forumRSS/61.xml','role_guest',NULL),
('developerWorks',0,null,'Developer Works','/light/images/feed.png',null,'http://www.javalobby.org/','/rssPortlet.lp','Java','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www-128.ibm.com/developerworks/views/java/rss/libraryview.jsp','role_guest',NULL),
('focusOnJava',0,null,'Focus On Java','/light/images/feed.png',null,'http://java.about.com/','/rssPortlet.lp','Java','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://z.about.com/6/g/java/b/index.xml','role_guest',NULL),
('jRoller',0,null,'JRoller','/light/images/feed.png',null,'http://www.jroller.com/','/rssPortlet.lp','Java','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.jroller.com/rss','role_guest',NULL),
('javaSunCom',0,null,'java.sun.com','/light/images/feed.png',null,'http://developers.sun.com','/rssPortlet.lp','Java','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://developers.sun.com/rss/java.xml','role_guest',NULL),
('devxJava',0,null,'DevX-Java','/light/images/feed.png',null,'http://www.devx.com','/rssPortlet.lp','Java','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://services.devx.com/outgoing/javafeed.xml','role_guest',NULL),
('lxer',0,null,'LXer-Linux News','/light/images/feed.png',null,'http://lxer.com/','/rssPortlet.lp','Linux','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://lxer.com/module/newswire/headlines.rdf','role_guest',NULL),
('yahooTech',0,null,'portlet.title.tech.yahoo','/light/images/feed.png',null,'http://www.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,1,0,0,0,0,0,'feed=http://rss.news.yahoo.com/rss/tech','role_guest',NULL),
('yahooSports',0,null,'portlet.title.sports.yahoo','/light/images/feed.png',null,'http://www.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.sports','en',1,1,0,1,1,1,NULL,0,0,6,1,0,0,0,0,0,'feed=http://rss.news.yahoo.com/rss/sports','role_guest',NULL),
('yahooET',0,null,'portlet.title.et.yahoo','/light/images/feed.png',null,'http://www.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.entertainment','en',1,1,0,1,1,1,NULL,0,0,6,1,0,0,0,0,0,'feed=http://rss.news.yahoo.com/rss/entertainment','role_guest',NULL),
('yahooHealth',0,null,'portlet.title.health.yahoo','/light/images/feed.png',null,'http://www.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.health','en',1,1,0,1,1,1,NULL,0,0,6,1,0,0,0,0,0,'feed=http://rss.news.yahoo.com/rss/health','role_guest',NULL),
('financialTimes',0,null,'Financial Times','/light/images/feed.png',null,'http://news.ft.com/home/us','/rssPortlet.lp',NULL,'portlet.tag.title.finance','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://news.ft.com/rss/home/us','role_guest',NULL),
('yahooScience',0,null,'portlet.title.science.yahoo','/light/images/feed.png',null,'http://www.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.science','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.news.yahoo.com/rss/science','role_guest',NULL),
('yahooBusiness',0,null,'portlet.title.business.yahoo','/light/images/feed.png',null,'http://www.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.business','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.news.yahoo.com/rss/business','role_guest',NULL),
('yahooPolitics',0,null,'portlet.title.politics.yahoo','/light/images/feed.png',null,'http://www.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.politics','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.news.yahoo.com/rss/politics','role_guest',NULL),
('yahooAuto',0,null,'portlet.title.auto.european','/light/images/feed.png',null,'http://www.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.auto','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.europeancarweb.com/rss/nonstandard_rss.xml','role_guest',NULL),
('yahooGame',0,null,'portlet.title.game.yahoo','/light/images/feed.png',null,'http://games.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.game','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://games.yahoo.com/rss/newgamesRSS.xml','role_guest',NULL),
('monsterCareer',0,null,'portlet.title.career.monster','/light/images/feed.png',null,'http://www.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.career','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://content.monster.com/rss/rss.xml','role_guest',NULL),
('yahooTopCa',0,null,'portlet.title.top.ca.yahoo','/light/images/feed.png',null,'http://ca.news.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.featured','en_CA',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://ca.rss.news.yahoo.com/rss/canada','role_guest',NULL),
('ctvTopStories',0,null,'CTV - Top Stories','/light/images/ctv.gif',null,'http://www.ctv.ca','/rssPortlet.lp',NULL,'portlet.tag.title.news','en_CA',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.ctv.ca/generic/generated/freeheadlines/rdf/TopStories.xml','role_guest',NULL),
('yahooBusinessCa',0,null,'portlet.title.business.ca.yahoo','/light/images/feed.png',null,'http://www.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.business','en_CA',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://ca.rss.news.yahoo.com/rss/business','role_guest',NULL),
('yahooETCa',0,null,'portlet.title.et.ca.yahoo','/light/images/feed.png',null,'http://www.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.entertainment','en_CA',1,1,0,1,1,1,NULL,0,0,6,1,0,0,0,0,0,'feed=http://ca.rss.news.yahoo.com/rss/entertainment','role_guest',NULL),
('yahooHealthCa',0,null,'portlet.title.health.ca.yahoo','/light/images/feed.png',null,'http://www.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.health','en_CA',1,1,0,1,1,1,NULL,0,0,6,1,0,0,0,0,0,'feed=http://ca.rss.news.yahoo.com/rss/health','role_guest',NULL),
('yahooSportsCa',0,null,'portlet.title.sports.ca.yahoo','/light/images/feed.png',null,'http://www.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.sports','en_CA',1,1,0,1,1,1,NULL,0,0,6,1,0,0,0,0,0,'feed=http://ca.rss.news.yahoo.com/rss/sports','role_guest',NULL),
('yahooTechCa',0,null,'portlet.title.tech.ca.yahoo','/light/images/feed.png',null,'http://www.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.tech','en_CA',1,1,0,1,1,1,NULL,0,0,6,1,0,0,0,0,0,'feed=http://ca.rss.news.yahoo.com/rss/tech','role_guest',NULL),
('sinaTop',0,null,'portlet.title.top.sina','/light/images/feed.png',null,'http://news.sina.com.cn/','/rssPortlet.lp',NULL,'portlet.tag.title.featured','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.sina.com.cn/news/marquee/ddt.xml','role_guest',NULL),
('sinaCivic',0,null,'portlet.title.news.sina','/light/images/feed.png',null,'http://news.sina.com.cn/','/rssPortlet.lp',NULL,'portlet.tag.title.news','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.sina.com.cn/news/china/focus15.xml','role_guest',NULL),
('baiduNews',0,null,'portlet.title.news.baidu','/light/images/feed.png',null,'http://news.baidu.com/','/rssPortlet.lp',NULL,'portlet.tag.title.news','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://news.baidu.com/n?cmd=4&class=civilnews&pn=1&tn=rss','role_guest',NULL),
('baiduWorld',0,null,'portlet.title.world.baidu','/light/images/feed.png',null,'http://news.baidu.com/','/rssPortlet.lp',NULL,'portlet.tag.title.news','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://news.baidu.com/n?cmd=4&class=internews&pn=1&tn=rss','role_guest',NULL),
('sinaBusiness',0,null,'portlet.title.business.sina','/light/images/feed.png',null,'http://www.sina.com.cn/','/rssPortlet.lp',NULL,'portlet.tag.title.business','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.sina.com.cn/finance/gncj.xml','role_guest',NULL),
('sinaET',0,null,'portlet.title.et.sina','/light/images/feed.png',null,'http://www.sina.com.cn/','/rssPortlet.lp','portlet.tag.sub.news','portlet.tag.title.entertainment','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.sina.com.cn/news/allnews/ent.xml','role_guest',NULL),
('xinhuanetET',0,null,'portlet.title.et.xinhuanet','/light/images/feed.png',null,'http://www.xinhuanet.com/','/rssPortlet.lp','portlet.tag.sub.news','portlet.tag.title.entertainment','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.xinhuanet.com/rss/ent.xml','role_guest',NULL),
('baiduET',0,null,'portlet.title.et.baidu','/light/images/feed.png',null,'http://news.baidu.com/','/rssPortlet.lp',NULL,'portlet.tag.title.entertainment','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://news.baidu.com/n?cmd=4&class=enternews&pn=1&tn=rss','role_guest',NULL),
('xinhuanetHealth',0,null,'portlet.title.health.xinhuanet','/light/images/feed.png',null,'http://www.xinhuanet.com/','/rssPortlet.lp',NULL,'portlet.tag.title.health','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.xinhuanet.com/rss/health.xml','role_guest',NULL),
('sinaCulture',0,null,'portlet.title.culture.sina','/light/images/feed.png',null,'http://www.yahoo.com/','/rssPortlet.lp',NULL,'portlet.tag.title.health','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.sina.com.cn/book/culture10.xml','role_guest',NULL),
('baiduSocial',0,null,'portlet.title.social.baidu','/light/images/feed.png',null,'http://news.baidu.com/','/rssPortlet.lp',NULL,'portlet.tag.title.health','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://news.baidu.com/n?cmd=4&class=socianews&pn=1&tn=rss','role_guest',NULL),
('xinhuanetSports',0,null,'portlet.title.sports.xinhuanet','/light/images/feed.png',null,'http://www.xinhuanet.com/','/rssPortlet.lp',NULL,'portlet.tag.title.sports','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.xinhuanet.com/rss/sports.xml','role_guest',NULL),
('sinaSports',0,null,'portlet.title.sports.sina','/light/images/feed.png',null,'http://www.sina.com.cn/','/rssPortlet.lp',NULL,'portlet.tag.title.sports','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.sina.com.cn/news/allnews/sports.xml','role_guest',NULL),
('baiduSports',0,null,'portlet.title.sports.baidu','/light/images/feed.png',null,'http://news.baidu.com/','/rssPortlet.lp',NULL,'portlet.tag.title.sports','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://news.baidu.com/n?cmd=4&class=sportnews&pn=1&tn=rss','role_guest',NULL),
('xinhuanetTech',0,null,'portlet.title.tech.xinhuanet','/light/images/feed.png',null,'http://www.xinhuanet.com/','/rssPortlet.lp',NULL,'portlet.tag.title.tech','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.xinhuanet.com/rss/science.xml','role_guest',NULL),
('xinhuanetIT',0,null,'portlet.title.it.xinhuanet','/light/images/feed.png',null,'http://www.xinhuanet.com/','/rssPortlet.lp',NULL,'portlet.tag.title.tech','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.xinhuanet.com/rss/it.xml','role_guest',NULL),
('sinaTech',0,null,'portlet.title.tech.sina','/light/images/feed.png',null,'http://www.sina.com.cn/','/rssPortlet.lp',NULL,'portlet.tag.title.tech','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.sina.com.cn/news/allnews/tech.xml','role_guest',NULL),
('baiduInternet',0,null,'portlet.title.it.baidu','/light/images/feed.png',null,'http://news.baidu.com/','/rssPortlet.lp',NULL,'portlet.tag.title.tech','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://news.baidu.com/n?cmd=4&class=internet&pn=1&tn=rss','role_guest',NULL),
('baiduTech',0,null,'portlet.title.tech.baidu','/light/images/feed.png',null,'http://news.baidu.com/','/rssPortlet.lp',NULL,'portlet.tag.title.tech','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://news.baidu.com/n?cmd=4&class=technnews&pn=1&tn=rss','role_guest',NULL),
('xinhuanetAuto',0,null,'portlet.title.auto.xinhuanet','/light/images/feed.png',null,'http://www.xinhuanet.com','/rssPortlet.lp',NULL,'portlet.tag.title.auto','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.xinhuanet.com/rss/autonews.xml','role_guest',NULL),
('xinhuanetCareer',0,null,'portlet.title.career.xinhuanet','/light/images/feed.png',null,'http://www.xinhuanet.com','/rssPortlet.lp',NULL,'portlet.tag.title.career','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.xinhuanet.com/rss/employment.xml','role_guest',NULL),
('ccwCareer',0,null,'portlet.title.career.ccwCareer','/light/images/feed.png',null,'http://www.ccw.com.cn/work2/','/rssPortlet.lp',NULL,'portlet.tag.title.career','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.ccw.com.cn/work2/39.xml','role_guest',NULL),
('itjobCareer',0,null,'portlet.title.career.itjobCareer','/light/images/feed.png',null,'http://www.5itjob.com/','/rssPortlet.lp',NULL,'portlet.tag.title.career','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.5itjob.com/rss.php','role_guest',NULL),
('xyzpCareer',0,null,'portlet.title.career.xyzpCareer','/light/images/feed.png',null,'http://www.xyzp.net/','/rssPortlet.lp',NULL,'portlet.tag.title.career','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.xyzp.net/rss/job.html','role_guest',NULL),
('baiduFinance',0,null,'portlet.title.finance.baidu','/light/images/feed.png',null,'http://news.baidu.com/','/rssPortlet.lp',NULL,'portlet.tag.title.finance','zh_CN',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://news.baidu.com/n?cmd=4&class=finannews&pn=1&tn=rss','role_guest',NULL),
('1173576666687',0,null,'The Seattle Times: Home','http://seattletimes.nwsource.com/art/ui/stlogo_231.gif',null,'http://seattletimes.nwsource.com/html/home','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','all',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://seattletimes.nwsource.com/rss/home.xml','liujianmin@gmail.com',NULL),
('1173576804484',0,null,'The Seattle Times: Business & Technology','http://seattletimes.nwsource.com/art/ui/stlogo_231.gif',null,'http://seattletimes.nwsource.com/html/businesstechnology','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://seattletimes.nwsource.com/rss/businesstechnology.xml','liujianmin@gmail.com',NULL),
('1173576827421',0,null,'The Seattle Times: Personal Technology','http://seattletimes.nwsource.com/art/ui/stlogo_231.gif',null,'http://seattletimes.nwsource.com/html/personaltechnology','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://seattletimes.nwsource.com/rss/personaltechnology.xml','liujianmin@gmail.com',NULL),
('1173576868109',0,null,'The Seattle Times: Consumer news','http://seattletimes.nwsource.com/art/ui/stlogo_231.gif',null,'http://seattletimes.nwsource.com/html/consumernews','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://seattletimes.nwsource.com/rss/consumernews.xml','liujianmin@gmail.com',NULL),
('1173576891812',0,null,'The Seattle Times: Living','http://seattletimes.nwsource.com/art/ui/stlogo_231.gif',null,'http://seattletimes.nwsource.com/html/living','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://seattletimes.nwsource.com/rss/living.xml','liujianmin@gmail.com',NULL),
('1173576918531',0,null,'The Seattle Times: Local News','http://seattletimes.nwsource.com/art/ui/stlogo_231.gif',null,'http://seattletimes.nwsource.com/html/localnews','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://seattletimes.nwsource.com/rss/localnews.xml','liujianmin@gmail.com',NULL),
('1173576986796',0,null,'The Seattle Times: Opinion','http://seattletimes.nwsource.com/art/ui/stlogo_231.gif',null,'http://seattletimes.nwsource.com/html/opinion','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://seattletimes.nwsource.com/rss/opinion.xml','liujianmin@gmail.com',NULL),
('1173577004031',0,null,'The Seattle Times: Real Estate','http://seattletimes.nwsource.com/art/ui/stlogo_231.gif',null,'http://seattletimes.nwsource.com/html/realestate','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://seattletimes.nwsource.com/rss/realestate.xml','liujianmin@gmail.com',NULL),
('1173577015203',0,null,'The Seattle Times: Home Forum','http://seattletimes.nwsource.com/art/ui/stlogo_231.gif',null,'http://seattletimes.nwsource.com/html/homeforum','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://seattletimes.nwsource.com/rss/homeforum.xml','liujianmin@gmail.com',NULL),
('1173577040921',0,null,'The Seattle Times: Most Read Articles','http://seattletimes.nwsource.com/art/ui/stlogo_231.gif',null,'http://seattletimes.nwsource.com/news/mostread/','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://seattletimes.nwsource.com/rss/mostreadarticles.xml','liujianmin@gmail.com',NULL),
('1173672973609',0,null,'Seattle Post-Intelligencer: Local News','http://seattlepi.nwsource.com/art2/logo/logo.gif',null,'http://seattlepi.nwsource.com/local/','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://seattlepi.nwsource.com/rss/local_2.rss','liujianmin@gmail.com',NULL),
('1173673051140',0,null,'Seattle Post-Intelligencer: Real Estate','http://seattlepi.nwsource.com/art2/logo/logo.gif',null,'http://seattlepi.nwsource.com/realestate/','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://seattlepi.nwsource.com/rss/realestate_2.rss','liujianmin@gmail.com',NULL),
('1173673096843',0,null,'Seattle Post-Intelligencer: Transportation','http://seattlepi.nwsource.com/art2/logo/logo.gif',null,'http://seattlepi.nwsource.com/transportation/','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://seattlepi.nwsource.com/rss/trans_2.rss','liujianmin@gmail.com',NULL),
('1173673140328',0,null,'Seattle Post-Intelligencer: Consumer','http://seattlepi.nwsource.com/art2/logo/logo.gif',null,'http://seattlepi.nwsource.com/consumer/','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://seattlepi.nwsource.com/rss/consumer_2.rss','liujianmin@gmail.com',NULL),
('1173674141984',0,null,'KING5.com Biz/Tech top stories','http://www.king5.com/images/kiosk-sitelogo.gif',null,'http://www.king5.com','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.king5.com/newskiosk/rss/kingbiztop.xml','liujianmin@gmail.com',NULL),
('1173674399296',0,null,'Seattle Post-Intelligencer: Most Read','',null,'http://seattlepi.nwsource.com/most/index.asp?mosttype=mostread','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://seattlepi.nwsource.com/rss/mostread.rss','liujianmin@gmail.com',NULL),
('1176754370656',0,null,'Fly Fishing Forum | Fly Fishing General discussion','http://www.bigfishtackle.com/images/rss_logo.gif',null,'http://www.bigfishtackle.com/rss/132.xml','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.bigfishtackle.com/rss/132.xml','liujianmin@gmail.com',NULL),
('1187159035689',0,null,'Guardian Unlimited','http://image.guardian.co.uk/sitecrumbs/Guardian.gif',null,'http://www.guardian.co.uk/?gusrc=rss&feed=networkfront','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.guardian.co.uk/rss','liujianmin@gmail.com',NULL),
('1187159108084',0,null,'Guardian Unlimited','http://image.guardian.co.uk/sitecrumbs/Guardian.gif',null,'http://www.guardian.co.uk/?gusrc=rss&feed=networkfront','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.guardian.co.uk/rss','role_public','liujianmin@gmail.com'),
('1187159174342',0,null,'Gawker','http://cache.gawker.com/assets/base/img/thumbs140x140/gawker.com.png',null,'http://gawker.com','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.gawker.com/gawker/excerpts.xml','role_public','liujianmin@gmail.com'),
('1187159511942',0,null,'NPR Topics: Nation','http://www.npr.org/images/npr_news_123x20.gif',null,'http://www.npr.org/templates/topics/topic.php?topicId=1003&ft=1&f=1003','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.npr.org/rss/rss.php?id=1003','role_public','liujianmin@gmail.com'),
('1187159549746',0,null,'NPR Topics: World','http://www.npr.org/images/npr_news_123x20.gif',null,'http://www.npr.org/templates/topics/topic.php?topicId=1004&ft=1&f=1004','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.npr.org/rss/rss.php?id=1004','role_public','liujianmin@gmail.com'),
('1187159591533',0,null,'NPR Topics: Business','http://www.npr.org/images/npr_news_123x20.gif',null,'http://www.npr.org/templates/topics/topic.php?topicId=1006&ft=1&f=1006','/rssPortlet.lp',NULL,'portlet.tag.title.business','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.npr.org/rss/rss.php?id=1006','role_public','liujianmin@gmail.com'),
('1187159628219',0,null,'NPR Topics: Health & Science','http://www.npr.org/images/npr_news_123x20.gif',null,'http://www.npr.org/templates/topics/topic.php?topicId=1007&ft=1&f=1007','/rssPortlet.lp',NULL,'portlet.tag.title.health','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.npr.org/rss/rss.php?id=1007','role_public','liujianmin@gmail.com'),
('1187159652877',0,null,'NPR Topics: Politics & Society','http://www.npr.org/images/npr_news_123x20.gif',null,'http://www.npr.org/templates/topics/topic.php?topicId=1012&ft=1&f=1012','/rssPortlet.lp',NULL,'portlet.tag.title.politics','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.npr.org/rss/rss.php?id=1012','role_public','liujianmin@gmail.com'),
('1187159758515',0,null,'NPR Topics: People & Places','http://www.npr.org/images/npr_news_123x20.gif',null,'http://www.npr.org/templates/topics/topic.php?topicId=1021&ft=1&f=1021','/rssPortlet.lp',NULL,'portlet.tag.title.fun','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.npr.org/rss/rss.php?id=1021','role_public','liujianmin@gmail.com'),
('1187159785845',0,null,'NPR Topics: Arts & Culture','http://www.npr.org/images/npr_news_123x20.gif',null,'http://www.npr.org/templates/topics/topic.php?topicId=1008&ft=1&f=1008','/rssPortlet.lp',NULL,'portlet.tag.title.fun','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.npr.org/rss/rss.php?id=1008','role_public','liujianmin@gmail.com'),
('1187159877889',0,null,'NPR Topics: Movies','http://www.npr.org/images/npr_news_123x20.gif',null,'http://www.npr.org/templates/topics/topic.php?topicId=1045&ft=1&f=1045','/rssPortlet.lp','portlet.tag.sub.movie','portlet.tag.title.entertainment','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.npr.org/rss/rss.php?id=1045','role_public','liujianmin@gmail.com'),
('1187159893192',0,null,'NPR Topics: Music','http://www.npr.org/images/npr_news_123x20.gif',null,'http://www.npr.org/templates/topics/topic.php?topicId=1039&ft=1&f=1039','/rssPortlet.lp','portlet.tag.sub.music','portlet.tag.title.entertainment','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.npr.org/rss/rss.php?id=1039','role_public','liujianmin@gmail.com'),
('1187159920214',0,null,'NPR Topics: Sports','http://www.npr.org/images/npr_news_123x20.gif',null,'http://www.npr.org/templates/topics/topic.php?topicId=1055&ft=1&f=1055','/rssPortlet.lp',NULL,'portlet.tag.title.sports','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.npr.org/rss/rss.php?id=1055','role_public','liujianmin@gmail.com'),
('1187159938418',0,null,'NPR Topics: Technology','http://www.npr.org/images/npr_news_123x20.gif',null,'http://www.npr.org/templates/topics/topic.php?topicId=1019&ft=1&f=1019','/rssPortlet.lp',NULL,'portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.npr.org/rss/rss.php?id=1019','role_public','liujianmin@gmail.com'),
('1187159951089',0,null,'NPR Topics: Technology','http://www.npr.org/images/npr_news_123x20.gif',null,'http://www.npr.org/templates/topics/topic.php?topicId=1019&ft=1&f=1019','/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.npr.org/rss/rss.php?id=1019','role_public','liujianmin@gmail.com'),
('1187160000390',0,null,'NPR Topics: Your Money','http://www.npr.org/images/npr_news_123x20.gif',null,'http://www.npr.org/templates/topics/topic.php?topicId=1018&ft=1&f=1018','/rssPortlet.lp',NULL,'portlet.tag.title.finance','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.npr.org/rss/rss.php?id=1018','role_public','liujianmin@gmail.com'),
('1187160065514',0,null,'NPR Topics: Health Care','http://www.npr.org/images/npr_news_123x20.gif',null,'http://www.npr.org/templates/topics/topic.php?topicId=1027&ft=1&f=1027','/rssPortlet.lp',NULL,'portlet.tag.title.health','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.npr.org/rss/rss.php?id=1027','role_public','liujianmin@gmail.com'),
('1187160233358',0,null,'The Onion','http://www.theonion.com/content/themes/onion/assets/logos/onion_super_tiny.png',null,'http://www.theonion.com/content','/rssPortlet.lp',NULL,'portlet.tag.title.fun','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.theonion.com/theonion/daily','role_public','liujianmin@gmail.com'),
('1187160268427',0,null,'The Onion - Photos','',null,'http://www.theonion.com/content','/rssPortlet.lp',NULL,'portlet.tag.title.fun','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.theonion.com/theonion/photos','role_public','liujianmin@gmail.com'),
('1187160406072',0,null,'Slate Magazine','',null,'http://www.slate.com/','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.slate.com/rss/','role_public','liujianmin@gmail.com'),
('1187160443069',0,null,'Slate Magazine - Technology','',null,'http://www.slate.com/id/2127411/fr/rss','/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.slate.com/rss/feed.aspx?id=2126999','role_public','liujianmin@gmail.com'),
('1187160490730',0,null,'Slate Magazine - Politics','',null,'http://www.slate.com/id/2076924/fr/rss','/rssPortlet.lp',NULL,'portlet.tag.title.politics','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.slate.com/rss/feed.aspx?id=101526','role_public','liujianmin@gmail.com'),
('1187160514527',0,null,'Slate Magazine - Movies','',null,'http://www.slate.com/id/2076824/fr/rss','/rssPortlet.lp','portlet.tag.sub.movie','portlet.tag.title.entertainment','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.slate.com/rss/feed.aspx?id=3184','role_public','liujianmin@gmail.com'),
('1187160570394',0,null,'Slate Magazine - Sports Nut','',null,'http://www.slate.com/id/2076907/fr/rss','/rssPortlet.lp',NULL,'portlet.tag.title.sports','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.slate.com/rss/feed.aspx?id=83221','role_public','liujianmin@gmail.com'),
('1187160704822',0,null,'The Smoking Gun','',null,'http://www.thesmokinggun.com/','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.thesmokinggun.com/rss.asp','role_public','liujianmin@gmail.com'),
('1187160758288',0,null,'Akihabaranews.com','',null,'http://www.akihabaranews.com','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.feedburner.com/Akihabaranews_en','role_public','liujianmin@gmail.com'),
('1187160888416',0,null,'Business IT Section - Ars Technica','',null,'http://arstechnica.com/business.ars','/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://arstechnica.com/business.rssx','role_public','liujianmin@gmail.com'),
('1187160948130',0,null,'Ars Technica','',null,'http://arstechnica.com/index.ars','/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.arstechnica.com/arstechnica/BAaf','role_public','liujianmin@gmail.com'),
('1187464783273',0,null,'Yahoo! News: Odd News','http://us.i1.yimg.com/us.yimg.com/i/us/nws/th/main_142b.gif',null,'http://news.yahoo.com/i/757','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.news.yahoo.com/rss/oddlyenough','liujianmin@gmail.com',NULL),
('1187464855712',0,null,'Yahoo! News: Odd News','http://us.i1.yimg.com/us.yimg.com/i/us/nws/th/main_142b.gif',null,'http://news.yahoo.com/i/757','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.news.yahoo.com/rss/oddlyenough','role_public','liujianmin@gmail.com'),
('1190099787287',0,null,'Amazon.com - Jianmin Liu\'s Wish List','http://images.amazon.com/images/G/01/rcm/logo2.gif',null,'http://www.amazon.com/gp/registry/wishlist/3ALACW9W4C56U/ref=cm_wl_rss-go/','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://xml-us.amznxslt.com/onca/xml?SubscriptionId=1FJCCJ9W5JQJB6WDRM02&ListId=3ALACW9W4C56U&ResponseGroup=ListFull,Large,Reviews&Style=http://images.amazon.com/media/i3d/01/amzn-wishlist-xsl-1-0.css?v=1.0-0&ListType=WishList&Operation=ListLookup&Ser','liujianmin@gmail.com',NULL),
('1191728759533',0,null,'Yahoo! - Arts','http://us.i1.yimg.com/us.yimg.com/i/us/nt/ma/ma_search-dir_1.gif',null,'http://dir.yahoo.com/new/20071005/Arts','/rssPortlet.lp',NULL,'portlet.tag.title.art','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://dir.yahoo.com/rss/dir/getrss.php?arts','role_public','liujianmin@gmail.com'),
('1191728936597',0,null,'Yahoo! - Society and Culture','http://us.i1.yimg.com/us.yimg.com/i/us/nt/ma/ma_search-dir_1.gif',null,'http://dir.yahoo.com/new/20071005/Society_and_Culture','/rssPortlet.lp',NULL,'portlet.tag.title.culture','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://dir.yahoo.com/rss/dir/getrss.php?soc','role_public','liujianmin@gmail.com'),
('1192572806653',0,null,'E! Online - What\'s Hot','http://images.eonline.com/images/feeds_logo.gif',null,'http://www.eonline.com/index.jsp','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.eonline.com/syndication/feeds/rssfeeds/whatshot.xml','afibarra_07@yahoo.com',NULL),
('1193262993850',0,null,'Amazon.com Gold Box Deals','http://images.amazon.com/images/G/01/rcm/logo2.gif',null,'http://www.amazon.com/gp/goldbox','/rssPortlet.lp',NULL,'portlet.tag.title.shopping','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rssfeeds.s3.amazonaws.com/goldbox','role_public',NULL),
('1193263824424',0,null,'Amazon.com Gold Box Deals','http://images.amazon.com/images/G/01/rcm/logo2.gif',null,'http://www.amazon.com/gp/goldbox','/rssPortlet.lp',NULL,'portlet.tag.title.popular','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rssfeeds.s3.amazonaws.com/goldbox','role_public','liujianmin@gmail.com'),
('1193264414779',0,null,'Amazon Daily','',null,'http://www.amazon.com/gp/daily','/rssPortlet.lp',NULL,'portlet.tag.title.popular','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.amazon.com/gp/daily/rss.xml?prid=PLGRSS2BJP7X14STGI','role_public','liujianmin@gmail.com'),
('1193346109266',0,null,'El Universal: Minuto x Minuto','http://www.eluniversal.com.mx/rss/rss.jpg',null,'http://www.eluniversal.com.mx','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.eluniversal.com.mx/rss/universalmxm.xml','afibarra_07@yahoo.com',NULL),
('1193691603192',0,null,'BusinessWeek Online --','http://images.businessweek.com/common_images/bw_logo.125.gif',null,'http://www.businessweek.com//index.html','/rssPortlet.lp',NULL,'portlet.tag.title.business','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.businessweek.com/rss/bwdaily.rss','role_public','liujianmin@gmail.com'),
('1193691674642',0,null,'BusinessWeek Online -- Most Popular Stories','http://images.businessweek.com/common_images/bw_logo.125.gif',null,'http://www.businessweek.com/','/rssPortlet.lp',NULL,'portlet.tag.title.business','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.businessweek.com/bw_rss/topstories','role_public','liujianmin@gmail.com'),
('1193691844157',0,null,'BusinessWeek Online -- Autos','http://images.businessweek.com/common_images/bw_logo.125.gif',null,'http://www.businessweek.com/autos/index.html','/rssPortlet.lp',NULL,'portlet.tag.title.auto','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.businessweek.com/bw_rss/autos','role_public','liujianmin@gmail.com'),
('1193691904984',0,null,'BusinessWeek Online -- Auto Reviews','http://images.businessweek.com/common_images/bw_logo.125.gif',null,'http://www.businessweek.com/autos/reviews/index.html','/rssPortlet.lp',NULL,'portlet.tag.title.auto','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.businessweek.com/bw_rss/autoreviews','role_public','liujianmin@gmail.com'),
('1193691947538',0,null,'BusinessWeek Online -- Auto Design','http://images.businessweek.com/common_images/bw_logo.125.gif',null,'http://www.businessweek.com/innovate/carbuff//index.html','/rssPortlet.lp',NULL,'portlet.tag.title.auto','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.businessweek.com/bw_rss/carbuff','role_public','liujianmin@gmail.com'),
('1193691994221',0,null,'BusinessWeek Online -- Technology','http://images.businessweek.com/common_images/bw_logo.125.gif',null,'http://www.businessweek.com/technology//index.html','/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.businessweek.com/bw_rss/technology','role_public','liujianmin@gmail.com'),
('1193692036169',0,null,'BusinessWeek Online -- CEO Guide to Technology','http://images.businessweek.com/common_images/bw_logo.125.gif',null,'http://www.businessweek.com/technology/ceo_guide//index.html','/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.businessweek.com/bw_rss/ceo_guide_tech','role_public','liujianmin@gmail.com'),
('1193692118744',0,null,'BusinessWeek Online --','http://images.businessweek.com/common_images/bw_logo.125.gif',null,'http://www.businessweek.com/innovate/gameroom//index.html','/rssPortlet.lp',NULL,'portlet.tag.title.game','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.businessweek.com/bw_rss/gameroom','role_public','liujianmin@gmail.com'),
('1193692170491',0,null,'BusinessWeek Online -- Investing','http://images.businessweek.com/common_images/bw_logo.125.gif',null,'http://www.businessweek.com/investor//index.html','/rssPortlet.lp',NULL,'portlet.tag.title.business','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.businessweek.com/bw_rss/investor','role_public','liujianmin@gmail.com'),
('1193692251004',0,null,'BusinessWeek Online -- Managing','http://images.businessweek.com/common_images/bw_logo.125.gif',null,'http://www.businessweek.com/managing/index.html','/rssPortlet.lp',NULL,'portlet.tag.title.career','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.businessweek.com/bw_rss/careers','role_public','liujianmin@gmail.com'),
('1194057741964',0,null,'OpenSocial API Blog','',null,NULL,'/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.feedburner.com/OpensocialApiBlog?format=xml','role_public','liujianmin@gmail.com'),
('1194550600366',0,null,'Silverback Rants','',null,'http://www.silverbackcoder.com/blog','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.silverbackcoder.com/blog/?feed=rss2','1194549825000',NULL),
('1195237512848',0,null,'The Seattle Times: Health','http://seattletimes.nwsource.com/art/ui/stlogo_231.gif',null,'http://seattletimes.nwsource.com/html/health','/rssPortlet.lp',NULL,'portlet.tag.title.myfeed','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://seattletimes.nwsource.com/rss/health.xml','liujianmin@gmail.com',NULL),
('1221461619277',0,null,'Ajaxian » Front Page','',null,'http://ajaxian.com','/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.feedburner.com/ajaxian','default_home','liujianmin@gmail.com'),
('1219126797821',0,null,'SI.com','http://i2.cdn.turner.com/si/.element/img/1.0/misc/logo.rss.gif',null,'http://sportsillustrated.cnn.com/?eref=si_topstories','/rssPortlet.lp',NULL,'portlet.tag.title.sports','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.cnn.com/rss/si_topstories.rss','default_home','liujianmin@gmail.com'),
('1219126848855',0,null,'SI.com - Most Popular Stories','',null,'http://sportsillustrated.cnn.com/mostpopular/?eref=si_mostpopular','/rssPortlet.lp',NULL,'portlet.tag.title.sports','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.cnn.com/rss/si_mostpopular.rss','default_home','liujianmin@gmail.com'),
('1219126879432',0,null,'Golf.com: Tours & News','http://img.timeinc.net/golf/static/img/golf_rss_logo.gif',null,'http://www.golf.com/golf/tours_news/0,28350,,00.html','/rssPortlet.lp',NULL,'portlet.tag.title.sports','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.feedburner.com/GolfToursNews','default_home','liujianmin@gmail.com'),
('1219126911328',0,null,'SI.com - NBA','http://i2.cdn.turner.com/si/.element/img/1.0/misc/logo.rss.gif',null,'http://sportsillustrated.cnn.com/basketball/nba/?eref=si_nba','/rssPortlet.lp',NULL,'portlet.tag.title.sports','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.cnn.com/rss/si_nba.rss','default_home','liujianmin@gmail.com'),
('1221461875021',0,null,'Ajax Impact','',null,NULL,'/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.feedburner.com/AjaxImpact','default_home','liujianmin@gmail.com'),
('1221461927625',0,null,'Ajax Lessons','',null,'http://www.ajaxlessons.com','/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.ajaxlessons.com/feed/','default_home','liujianmin@gmail.com'),
('1221461973742',0,null,'Latest AjaxMatters.com Articles','',null,'http://204.9.76.234/index.aspx','/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.feedburner.com/AjaxMatters','default_home','liujianmin@gmail.com'),
('1227033817878',0,null,'VentureBeat','',null,'http://venturebeat.com','/rssPortlet.lp',NULL,'portlet.tag.title.business','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feedproxy.google.com/venturebeat','default_home','liujianmin@gmail.com'),
('1227033865016',0,null,'VentureBeat','',null,'http://venturebeat.com','/rssPortlet.lp',NULL,'portlet.tag.title.business','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feedproxy.google.com/Venturebeat?format=xml','default_home','liujianmin@gmail.com'),
('1227123117195',0,null,'The most recent articles from vnunet.com','http://www.vnunet.com/images/rss/vnu_logo.gif',null,'http://www.vnunet.com/','/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.vnunet.com/feed/vnunet/Vnunet','role_guest','liujianmin@gmail.com'),
('1227123241932',0,null,'VentureBeat','',null,'http://venturebeat.com','/rssPortlet.lp',NULL,'portlet.tag.title.business','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feedproxy.google.com/Venturebeat?format=xml','role_guest','liujianmin@gmail.com'),
('1227128272749',0,null,'The most recent News from VNU Business Publications','http://www.vnunet.com/images/rss/vnu_logo.gif',null,'http://www.vnunet.com/','/rssPortlet.lp',NULL,'portlet.tag.title.business','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.vnunet.com/feed/vnunet/News','role_guest','liujianmin@gmail.com'),
('1227128347218',0,null,'Computing.co.uk Latest updates','http://www.computing.co.uk/images/rss/ctg_logo.gif',null,'http://www.computing.co.uk/','/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.computing.co.uk/feed/vnunet/Computing','role_guest','liujianmin@gmail.com'),
('1227128365871',0,null,'The most recent articles from IT Week','http://www.itweek.co.uk/images/rss/itw_logo.gif',null,'http://www.itweek.co.uk/','/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.itweek.co.uk/feed/vnunet/IT_week','role_guest','liujianmin@gmail.com'),
('1227128498008',0,null,'digg.com: Stories / Popular','',null,'http://digg.com/','/rssPortlet.lp',NULL,'portlet.tag.title.news','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.digg.com/digg/popular.rss','role_guest','liujianmin@gmail.com'),
('1227128550159',0,null,'digg.com: Stories / Technology / Popular','',null,'http://digg.com/','/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.digg.com/digg/container/technology/popular.rss','role_guest','liujianmin@gmail.com'),
('1227128595605',0,null,'digg.com: Stories / World & Business / Popular','',null,'http://digg.com/','/rssPortlet.lp',NULL,'portlet.tag.title.business','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.digg.com/digg/container/world_business/popular.rss','role_guest','liujianmin@gmail.com'),
('1227128634398',0,null,'digg.com: Stories / Entertainment / Popular','',null,'http://digg.com/','/rssPortlet.lp',NULL,'portlet.tag.title.entertainment','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.digg.com/digg/container/entertainment/popular.rss','role_guest','liujianmin@gmail.com'),
('1227128662753',0,null,'digg.com: Stories / Sports / Popular','',null,'http://digg.com/','/rssPortlet.lp',NULL,'portlet.tag.title.sports','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.digg.com/digg/container/sports/popular.rss','role_guest','liujianmin@gmail.com'),
('1227128695279',0,null,'digg.com: Stories / Lifestyle / Popular','',null,'http://digg.com/','/rssPortlet.lp',NULL,'portlet.tag.title.health','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.digg.com/digg/container/lifestyle/popular.rss','role_guest','liujianmin@gmail.com'),
('1227128725761',0,null,'digg.com: Stories / Gaming / Popular','',null,'http://digg.com/','/rssPortlet.lp',NULL,'portlet.tag.title.game','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.digg.com/digg/container/gaming/popular.rss','role_guest','liujianmin@gmail.com'),
('1227128751526',0,null,'digg.com: Stories / Science / Popular','',null,'http://digg.com/','/rssPortlet.lp',NULL,'portlet.tag.title.science','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.digg.com/digg/container/science/popular.rss','role_guest','liujianmin@gmail.com'),
('1227129112025',0,null,'digg.com: Stories / Offbeat / Popular','',null,'http://digg.com/','/rssPortlet.lp',NULL,'portlet.tag.title.popular','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://feeds.digg.com/digg/container/offbeat/popular.rss','role_guest','admin'),
('1227227448428',0,null,'TechRadar: All Latest Feeds','http://www.techradar.com/default/img/techradarsmall.gif',null,'http://www.techradar.com//rss','/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.feedsportal.com/c/669/f/9809/index.rss','role_guest','liujianmin@gmail.com'),
('1227227586699',0,null,'TechRadar: All Blogs Feeds','http://www.techradar.com/default/img/techradarsmall.gif',null,'http://www.techradar.com//rss/blogs','/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://www.techradar.com/rss/blogs','role_guest','liujianmin@gmail.com'),
('1227227637078',0,null,'TechRadar: All Review Feeds','http://www.techradar.com/default/img/techradarsmall.gif',null,'http://www.techradar.com//rss/reviews','/rssPortlet.lp','portlet.tag.title.popular','portlet.tag.title.tech','en',1,1,0,1,1,1,NULL,0,0,6,0,0,0,0,0,0,'feed=http://rss.feedsportal.com/c/669/f/415090/index.rss','role_guest','liujianmin@gmail.com');

SET SQL_MODE=@OLD_SQL_MODE;