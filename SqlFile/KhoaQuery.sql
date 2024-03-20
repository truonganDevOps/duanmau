use Nerdyers
go

create or alter procedure thongKeSach @isView bit
as
begin
	if @isView = 1
	begin
		select * from Sach
		order by viewCount asc
	end
	else if @isView = 0
	begin
		select * from Sach
		order by likeCount asc		
	end
	else if @isView is null
	begin
		select * from Sach
		order by danhGiaTB asc
	end
end

go

exec thongKeSach 1
go


