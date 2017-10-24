-- 查询指定条件的用户信息
select a.* 
from users a
where a.state = 0
and a.sex = 0
and a.age between 28 and 38
and a.salary between 5000 and 9000
order by a.create_time desc
limit 0, 10;


-- 查询用户的魅力值信息
select u.id, a.charm from 
users u, user_account a
where u.id = a.user_id


-- 综合查询语句(TMD, 出现重复数据)
select a.*, s.charm, (select file_name from attachment where id = a.portrait_id) file_name
from users a, 
(select a.charm from users u, user_account a where u.id = a.user_id) s
where a.state = 0
and a.sex = 0
and a.age between 28 and 38
and a.salary between 5000 and 9000
order by s.charm desc
limit 0, 10;


-- 查询指定人员的头像图片名字
select u.*, a.* 
from users u, attachment a
where u.portrait_id = a.id;

-- 最终答案
select a.*, s.charm, (select file_name from attachment where id = a.portrait_id) file_name
from users a left join (select u.id idd,q.charm from users u, user_account q where u.id = q.user_id) s
on a.id = s.idd
where a.state = 0
and a.sex = 0
and a.age between 28 and 38
and a.salary between 5000 and 9000
order by s.charm desc
limit 0, 10;




select u.*, a.*
from users u left join user_account a
on u.id = a.user_id



select g.*,a.* 
from goods g, attachment a
where g.img = a.id

