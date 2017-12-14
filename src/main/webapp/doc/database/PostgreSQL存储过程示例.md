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

```sql
CREATE OR REPLACE FUNCTION db_upid.update_gjf_ajupid(schema_table VARCHAR, ajupid VARCHAR, old_ajupid VARCHAR)
RETURNS integer AS $$
BEGIN
    EXECUTE 'UPDATE ' || schema_table || ' set c_ptbh = ' || quote_literal(ajupid) 
        || ' WHERE c_ptbh = ' || quote_literal(old_ajupid);
    RETURN 1;
END;
$$ LANGUAGE PLPGSQL;

SELECT update_gjf_ajupid('db_sacw.t_test_aaa', 'my_test_bh1', 'typtbh01');
```

```sql
CREATE OR REPLACE FUNCTION db_upid.test_loop(schema_tables VARCHAR[])
RETURNS VARCHAR AS $$
DECLARE
    str VARCHAR := '';
BEGIN
    FOR i IN 1..ARRAY_LENGTH(schema_tables, 1) LOOP
        str := str || schema_tables[i];
    END LOOP;
    RETURN str;
END;
$$ LANGUAGE PLPGSQL;

SELECT db_upid.test_loop(ARRAY['a', 'b', 'c']);
```

```sql
CREATE OR REPLACE FUNCTION db_upid.update_gjf_cwupid(update_schema VARCHAR, main_table VARCHAR,
    sub_tables VARCHAR[], cwupid VARCHAR, old_cwupid VARCHAR)
RETURNS void AS $$
BEGIN
    --  执行主表的财物平台编号的更新.
    EXECUTE 'UPDATE ' || update_schema || '.' || main_table || ' set c_ptbh = ' || quote_literal(cwupid)
            || ' WHERE c_ptbh = ' || quote_literal(old_cwupid);

    -- 循环执行其下各个子表的财物平台编号的更新.
    FOR i IN 1..ARRAY_LENGTH(sub_tables, 1) LOOP
        EXECUTE 'UPDATE ' || update_schema || '.' || sub_tables[i] || ' set c_cwptbh = ' || quote_literal(cwupid)
            || ' WHERE c_cwptbh = ' || quote_literal(old_cwupid);
    END LOOP;
END;
$$ LANGUAGE PLPGSQL;

SELECT db_upid.update_gjf_cwupid('db_sacw_fy', 't_cw_jbxx',
    ARRAY['t_cw_kxzcxx', 't_cw_wpjdxx', 't_cw_ckrkxx'], '2017331000001520002', '2017331000001520001');
```

```sql
-- 在DB_STATIS数据库模式下创建统计某个数据库模式的记录数的函数,sn参数表是数据库模式表名，默认值为'db_sacw'.
CREATE OR REPLACE FUNCTION db_statis.record_statis (sn VARCHAR DEFAULT 'db_sacw')
RETURNS VARCHAR AS $$
DECLARE
    -- 定义变量，分别是:当前时间(now_time), 含模式的数据库表名(full_table_name), 查询所有表的sql变量(table_sql), 查询某张表所有列的sql变量(column_sql), 查询该列没有值的SQL变量(column_num_sql)
    -- 查询的所有表每行的记录(table_record的简称tr), 查询该列所有列名的记录(column_record的简称cr), 该列有值的数据量, 该列有值的数据量, 该列有值的占比.
    now_time TIMESTAMP := current_timestamp;
    full_table_name VARCHAR;
    table_sql VARCHAR;
    column_sql VARCHAR;
    column_num_sql VARCHAR;
    tr RECORD;
    cr RECORD;
    wzsj_num INTEGER;
    yzsj_num INTEGER;
    yzzb_num NUMERIC;
BEGIN
    -- 先清空所需要统计插入的数据库模式的表数据.
    EXECUTE 'DELETE FROM db_statis.t_record_table WHERE c_name LIKE ' || quote_literal(sn || '.%');
    EXECUTE 'DELETE FROM db_statis.t_record_column WHERE c_table_name LIKE ' || quote_literal(sn || '.%');

    -- 再查询db_sacw表中所有表的表名和记录总数，然后循环插入到db_statis.t_record_table和db_statis.t_record_column两张表中.
    table_sql := 'SELECT relname AS table_name, reltuples AS row_count FROM pg_class WHERE relkind = '
        || quote_literal('r') || ' AND relnamespace = (SELECT oid FROM pg_namespace WHERE nspname = ' || quote_literal(sn) || ') ORDER BY relname ASC';
    FOR tr IN EXECUTE table_sql LOOP
        full_table_name := sn || '.' || tr.table_name;
        -- 对每张表执行表分析，重新整理数据，使统计数据为最新数据且更准确.
        EXECUTE 'ANALYZE ' || full_table_name;

        -- 向'db_statis.t_record_table'表中,插入表名和该表所对应的总记录数.
        EXECUTE 'INSERT INTO db_statis.t_record_table (c_name, n_count, dt_zhgxsj) VALUES (' || quote_literal(full_table_name) || ', '
            || quote_literal(tr.row_count) || ', ' || quote_literal(now_time) || ')';

        -- 再查询出该表中的所有列的数据,并循环查询出该列在该表中的有值和没有值的数据量和有值数据量的占比.
        column_sql := 'SELECT column_name FROM information_schema.columns WHERE table_schema = ' || quote_literal(sn)
            || ' AND table_name = ' || quote_literal(tr.table_name) || ' ORDER BY ordinal_position';
        FOR cr IN EXECUTE column_sql LOOP
            -- 如果某张表中的数据总数为0，则不需要再统计其每一列有值和没有值的情况了,均为0。否则,就统计该列没有值(即为null)的总数，就可以算出有值的数量和占比了.
            IF tr.row_count = 0 THEN
                wzsj_num := 0;
                yzsj_num := 0;
                yzzb_num := 0;
            ELSE
                -- 查询出该列没有值(即为null)的总数，并计算出有值的数量和占比.
                column_num_sql := 'SELECT COUNT(*) FROM ' || full_table_name || ' where ' || cr.column_name || ' is null';
                EXECUTE column_num_sql INTO wzsj_num;
                yzsj_num := tr.row_count - wzsj_num;
                yzzb_num := ROUND(yzsj_num/tr.row_count::numeric, 2);
            END IF;

            -- 向'db_statis.t_record_column'表中,插入表名、列名、该列有值的数量，无值的数量和有值的占比.
            EXECUTE 'INSERT INTO db_statis.t_record_column (c_table_name, c_name, n_yzsjl, n_wzsjl, n_yzsjzb, dt_zhgxsj) VALUES ('
                || quote_literal(full_table_name) || ', ' || quote_literal(cr.column_name) || ', ' || quote_literal(yzsj_num) || ', '
                || quote_literal(wzsj_num) || ', ' || quote_literal(yzzb_num) || ', ' || quote_literal(now_time) || ')';
        END LOOP;
    END LOOP;
    RETURN '统计成功！执行耗时:' || EXTRACT(EPOCH FROM (current_timestamp - now_time)) || ' s';
END; $$ LANGUAGE plpgsql;
```