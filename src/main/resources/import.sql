insert into in_zone(id, created, name, priority) values ('12cbb7a9-d907-4ad4-8470-3b06b7656b0b',TO_DATE('2018/12/28', 'YYYY/MM/DD'),'Zona 1', 1)
insert into in_zone(id, created, name, priority) values ('01c5256d-b315-4961-bd53-453a7da9bcad',TO_DATE('2018/12/29', 'YYYY/MM/DD'),'Zona 2', 2)
insert into in_zone(id, created, name, priority) values ('0e3ccb7d-09d5-488c-bd81-f1d8e68318c3',TO_DATE('2018/12/30', 'YYYY/MM/DD'),'Zona 2', 3)

insert into in_zone_polygon(idzp, idz, x1, y1, x2, y2, x3, y3, x4, y4) values ('5b6db652-30ab-4fd1-a1dc-0c707160de31', '12cbb7a9-d907-4ad4-8470-3b06b7656b0b', 1, 1, 2, 2, 3, 3, 4, 4)