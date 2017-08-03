with tableDescription  as
(
	select 
		t0.match
	from 
	(
		select
			 substring( t0.[text], startDesc, endDesc - startDesc) as match
			,startDesc
			,endDesc
		from(
			select
				 startDesc
				,case when endDesc != 0 then endDesc + 14 /*длина оканчивающегося тега*/ else endDesc end as endDesc
				,t0.[text]
			from
			(		
				select 
					 PATINDEX('%<description%', t0.[text]) as startDesc
					,PATINDEX('%</description>%', t0.[text])as endDesc
					,t0.[text]
				from
				(
					--СЮДА ГЕНЕРИРУЕТСЯ ЗАПРОС ПО УКАЗАННЫМ БАЗАМ ДАННЫХ
					:UNION_SCRIPT_SELECT					
				)
					t0
			) t0
		) t0
		where
			t0.endDesc != 0
	) t0
)

select match from tableDescription