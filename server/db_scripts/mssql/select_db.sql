--ПРОИЗВОДИТСЯ ЗАМЕНА НА НАЗВАНИЕ БД
--ПРОИЗВОДИТСЯ ЗАМЕНА НА СХЕМУ БД
select
	t0.[text]
from
(
	select
		t1.definition as [text]
	from :DATABASE.:SCHEMA.sysobjects t0
	inner join
	( 
		select object_id, definition from :DATABASE.sys.sql_modules where PATINDEX('%<description%', definition) > 0
	) t1 on
		t0.id = t1.object_id
	inner join  :DATABASE.sys.all_objects t2 on 
		t0.id=t2.object_id
	where 
		t0.xtype in ('V' ,'FN', 'p', 'IF')
) t0	