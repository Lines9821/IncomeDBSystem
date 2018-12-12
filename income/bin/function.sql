DELIMITER $$
CREATE  FUNCTION `lastfee`(sid int) RETURNS decimal(10,2)
begin
 DECLARE newp dec(10,2) default 0.0;
 DECLARE allfee dec(10,2) default 0.0;
 select newprice into newp from priceinfo where stuid=sid limit 1;
 select sum(feermb) into allfee from feeinfo where stuid=sid limit 1;
 if allfee is null then set allfee=0.0; end if;
 return newp-allfee;
end $$
DELIMITER ;