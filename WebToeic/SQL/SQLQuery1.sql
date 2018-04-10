delete from member

DBCC CHECKIDENT (member, RESEED, 0)

insert into member(memberName,memberPass,categoryMemberId,name)
values ('Admin','admin123','2',N'Nguyễn Huyền Trang')