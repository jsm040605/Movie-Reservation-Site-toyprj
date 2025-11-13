-- insert into users(id, join_date, name, password, ssn) values(99991, now(), 'user1', '1111', '11-11');
-- insert into users(id, join_date, name, password, ssn) values(99992, now(), 'user2', '2222', '22-22');
-- insert into users(id, join_date, name, password, ssn) values(99993, now(), 'user3', '3333', '33-33');
--
-- insert into post(description, user_id) values ('My first post', 99991);
-- insert into post(description, user_id) values ('My second post', 99991);

-- 영화 초기 데이터
insert into movie(id, title, description, genre)
values (90000, 'ababta', 'My First Movie', 'action');

insert into movie(id, title, description, genre)
values (90001, 'demon hunters', 'My Second Movie', 'animation');

insert into movie(id, title, description, genre)
values (90002, 'green books', 'My Third Movie', 'comic');

-- 영화관 초기 데이터
insert into theater(id, name, address, province)
values (90003, '소풍', '경기도 부천시 원미구', 'GYEONGI');

insert into theater(id, name, address, province)
values (90004, '부평', '인천광역시 부평구', 'INCHEON');

insert into theater(id, name, address, province)
values (90005, '부천', '경기도 부천시 원미구', 'GYEONGI');

-- 상영 관 초기 데이터
insert into room(id, room_number, room_grade, price, row_count, col_count, theater_id)
values (90005, 1, 'IMEX', 20000, 20, 20, 90003);

insert into room(id, room_number, room_grade, price, row_count, col_count, theater_id)
values (90006, 2, 'DOLBY', 16000, 30, 30, 90003);

insert into room(id, room_number, room_grade, price, row_count, col_count, theater_id)
values (90007, 1, 'IMEX', 20000, 20, 20, 90004);

-- 상영 초기 데이터
insert into screening(id, screening_time, movie_id, room_id)
values (90008, '2025-10-09T09:00:00', 90000, 90005);

insert into screening(id, screening_time, movie_id, room_id)
values (90009, '2025-10-09T20:00:00', 90000, 90005);

insert into screening(id, screening_time, movie_id, room_id)
values (90010, '2025-10-11T09:00:00', 90001, 90005);

insert into screening(id, screening_time, movie_id, room_id)
values (90011, '2025-10-08T09:00:00', 90000, 90006);