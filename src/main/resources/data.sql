INSERT INTO `Movie` (`title`, `runtime`, `genre`, `description`, `age_rating`)
VALUES ('My First Movie', 120, 'action', 'ababta', 'ALL'),
       ('My Second Movie', 150, 'animation', 'demon hunters', 'ALL'),
       ('My Third Movie', 150, 'comic', 'green books', 'ALL');

INSERT INTO `Theater` (`name`, `address`, `province`, `created_at`, `updated_at`)
VALUES ('부천', '경기도 부천시 원미구', 'GYEONGI', NOW(), NOW()),
       ('부평', '인천광역시 부평구', 'INCHEON', NOW(), NOW()),
       ('소풍', '경기도 부천시 원미구', 'GYEONGI', NOW(), NOW());

INSERT INTO `Room` (`room_number`, `room_grade`, `row_count`, `col_count`, `theater_id`)
VALUES (1, 'IMAX', 10, 20, 1), -- r1: theater_id=1, col_count=20, row_count=10
       (2, 'DOLBY', 10, 15, 1);
-- r2: theater_id=1, col_count=15, row_count=10

-- 부평 (Theater ID 2) 소속 방
INSERT INTO `Room` (`room_number`, `room_grade`, `row_count`, `col_count`, `theater_id`)
VALUES (1, 'IMAX', 10, 20, 2);
-- r3: theater_id=2, col_count=20, row_count=10

INSERT INTO `Screening` (`start_time`, `end_time`, `movie_id`, `room_id`, `price`)
VALUES
    ('2025-10-09 09:00:00',  '2025-10-09 11:00:00' ,1, 1, NULL), -- s1: movie_id=1, room_id=1
    ('2025-10-09 09:10:00', '2025-10-09 11:10:00' ,1, 2, NULL), -- s2: movie_id=1, room_id=2
    ('2025-10-09 12:30:00', '2025-10-09 14:00:00' ,3, 1, NULL), -- s3: movie_id=3, room_id=1
    ('2025-10-10 09:00:00', '2025-10-09 11:30:00',2, 3, NULL); -- s4: movie_id=2, room_id=3

INSERT INTO users (name, email, phone_number, password, user_status, created_at, updated_at)
VALUES ('hello', 'asdf@asdf', '010-1234-5678', 'pass1234', 'ACTIVE',NOW(), NOW())