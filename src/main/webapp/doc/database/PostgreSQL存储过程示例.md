# PostgreSQL存储过程示例

```sql
CREATE OR REPLACE FUNCTION totalRecords ()
RETURNS integer AS $total$
declare
    total integer;
BEGIN
   SELECT count(*) into total FROM EMPLOYEES;
   RETURN total;
END;
$total$ LANGUAGE PLPGSQL;
```

```bash
year: 2017
sfbh: 33
jgbh: 1
lylx: 1
gaah: gaah
jcyah: jcyah
fyah: fyah
```

```sql
CREATE OR REPLACE FUNCTION db_upid.generate_ajupid(
    IN year VARCHAR, IN sfbh VARCHAR, IN jgbh NUMERIC, IN lylx NUMERIC, 
    IN gaah VARCHAR, IN jcyah VARCHAR, IN fyah VARCHAR, OUT ajupid VARCHAR)
AS $$
DECLARE
    abc varchar;
BEGIN
   ajupid := year || sfbh || jgbh || gaah;
END;
$$ LANGUAGE PLPGSQL;
```

```sql
SELECT generate_ajupid('2017', '33', 2, 2, 'gaah', null, NULL);

CASE lylx
         WHEN 1 THEN '男'
         WHEN 2 THEN '女'
	ELSE '其他' END;

ELSE
        sql1 := 'SELECT c_bh FROM db_upid.t_upid_ajupid WHERE c_jcyah = ' || jcyah || ' ORDER BY dt_zhgxsj DESC LIMIT 1 OFFSET 0'    

SELECT c_bh FROM db_upid.t_upid_ajupid WHERE c_jcyah = jcyah2 ORDER BY dt_zhgxsj DESC LIMIT 1 OFFSET 0
```

```sql
CREATE OR REPLACE FUNCTION db_upid.generate_ajupid(
    IN year VARCHAR, IN sfbh VARCHAR, IN jgbh NUMERIC, IN lylx NUMERIC, 
    IN gaah VARCHAR, IN jcyah VARCHAR, IN fyah VARCHAR)
RETURNS TABLE(col1 VARCHAR)
AS $$
DECLARE
    sql1 varchar;
BEGIN
    IF lylx = 1 THEN
        sql1 := 'SELECT c_bh FROM db_upid.t_upid_ajupid WHERE c_gaah = ' || gaah || ' ORDER BY dt_zhgxsj DESC LIMIT 1 OFFSET 0';
    ELSE
        sql1 := 'SELECT c_bh FROM db_upid.t_upid_ajupid WHERE c_jcyah = '' || jcyah || '' ORDER BY dt_zhgxsj DESC LIMIT 1 OFFSET 0';
    END IF;
    
    RETURN QUERY EXECUTE sql1;
END;
$$ LANGUAGE PLPGSQL;


SELECT generate_ajupid('2017', '33', 2, 2, 'gaah', 'jcyah2', NULL);
```