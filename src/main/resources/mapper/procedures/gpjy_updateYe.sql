delimiter $$
create procedure gpjy_updateYe(in p_ztbh int,in p_ywrq varchar(20))
begin
	-- 交易费用-股票
	UPDATE accountingsystem.t_ccyeb ye 
		SET ye.zqcb=ye.zqcb
        +IFNULL((select sum(t.lumpsum) from accountingsystem.t_qsb t WHERE (t.ywlb = '1101'  or t.ywlb = '1102')  and t.ztbh=p_ztbh and t.rq=p_ywrq),0)
	WHERE ye.kjkmdm=6201 and ye.ztbh=p_ztbh;

	-- 应付交易费用-股票
	UPDATE accountingsystem.t_ccyeb ye 
		SET ye.zqcb=ye.zqcb
        + IFNULL((select sum(t.yj) from accountingsystem.t_qsb t WHERE (t.ywlb = '1101'  or t.ywlb = '1102')  and t.ztbh=p_ztbh and t.rq=p_ywrq),0)
	WHERE ye.kjkmdm=2001 and ye.ztbh=p_ztbh;

	-- 证券清算款-股票
	UPDATE accountingsystem.t_ccyeb ye 
		SET ye.zqcb=ye.zqcb
		- IFNULL((SELECT sum(t.zqqsk) FROM accountingsystem.t_qsb t WHERE t.ywlb = '1101' and t.ztbh=p_ztbh and t.rq=p_ywrq),0)  -- 股票买入
	    +IFNULL((SELECT sum(t.zqqsk) FROM accountingsystem.t_qsb t WHERE t.ywlb = '1102' and t.ztbh=p_ztbh and t.rq=p_ywrq),0)  -- 股票卖出
	WHERE  ye.kjkmdm=1131 and ye.ztbh=p_ztbh;
    
    -- 投资收益-股票
	UPDATE accountingsystem.t_ccyeb ye 
		SET ye.zqcb=ye.zqcb
        +IFNULL((select sum(t.cjsr) from accountingsystem.t_qsb t WHERE t.ywlb = '1102'    and t.ztbh=p_ztbh and t.rq=p_ywrq),0)
	WHERE ye.kjkmdm=6001 and ye.ztbh=p_ztbh;
    
end$$
delimiter ;
