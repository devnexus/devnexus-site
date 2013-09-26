alter table events add created_date timestamp without time zone;
alter table events add updated_date timestamp without time zone;
alter table events add version integer;
 
alter table users add created_date timestamp without time zone;
alter table users add updated_date timestamp without time zone;
alter table users add version integer;

