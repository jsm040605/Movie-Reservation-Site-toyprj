DROP TABLE IF EXISTS `Seats`;
DROP TABLE IF EXISTS `Reservation`;
DROP TABLE IF EXISTS `Screening`;
DROP TABLE IF EXISTS `Users`;
DROP TABLE IF EXISTS `Theater`;
DROP TABLE IF EXISTS `Movie`;
DROP TABLE IF EXISTS `Reservation_Detail`;
DROP TABLE IF EXISTS `Room`;


CREATE TABLE `Users`
(
    `user_id`      BIGINT NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(100) NULL,
    `email`        VARCHAR(255) NULL,
    `phone_number` VARCHAR(20) NULL,
    `password`     VARCHAR(255) NULL,
    `user_status`  VARCHAR(50) NULL,
    `created_at`   TIMESTAMP NULL,
    `updated_at`   TIMESTAMP NULL,

    CONSTRAINT PK_USERS PRIMARY KEY (user_id)
);


CREATE TABLE `Theater`
(
    `theater_id` BIGINT       NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(255) NOT NULL,
    `address`    VARCHAR(255) NULL,
    `province`   VARCHAR(100) NULL,
    `created_at` DATETIME NULL,
    `updated_at` DATETIME NULL,

    CONSTRAINT PK_THEATER PRIMARY KEY (theater_id)
);


CREATE TABLE `Movie`
(
    `movie_id`    BIGINT       NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(255) NOT NULL,
    `runtime`     INT NULL,
    `genre`       VARCHAR(100) NULL,
    `description` TEXT NULL,
    `age_rating`  VARCHAR(20) NULL,

    CONSTRAINT PK_MOVIE PRIMARY KEY (movie_id)
);


CREATE TABLE `Room`
(
    `room_id`     BIGINT NOT NULL AUTO_INCREMENT,
    `room_number` INT NULL,
    `room_grade`  VARCHAR(50) NULL,
    `row_count`   INT NULL,
    `col_count`   INT NULL,
    `theater_id`  BIGINT NOT NULL,

    CONSTRAINT PK_ROOM PRIMARY KEY (room_id),

    CONSTRAINT FK_Theater_TO_Room_1 FOREIGN KEY (theater_id)
        REFERENCES Theater (theater_id)
);


CREATE TABLE `Screening`
(
    `screening_id`   BIGINT   NOT NULL AUTO_INCREMENT,
    `screening_time` DATETIME NOT NULL,
    `price`          INT NULL,
    `movie_id`       BIGINT   NOT NULL,
    `room_id`        BIGINT   NOT NULL,

    CONSTRAINT PK_SCREENING PRIMARY KEY (screening_id),

    CONSTRAINT FK_Movie_TO_Screening_1 FOREIGN KEY (movie_id)
        REFERENCES Movie (movie_id),

    CONSTRAINT FK_Room_TO_Screening_1 FOREIGN KEY (room_id)
        REFERENCES Room (room_id)
);


CREATE TABLE `Reservation`
(
    `reservation_id` BIGINT NOT NULL AUTO_INCREMENT,
    `booking_status` VARCHAR(20) NULL,
    `total_amount`   INT NULL,
    `payment_status` VARCHAR(20) NULL,
    `user_id`        BIGINT NOT NULL,
    `screening_id`   BIGINT NOT NULL,

    CONSTRAINT PK_RESERVATION PRIMARY KEY (reservation_id),

    CONSTRAINT FK_Users_TO_Reservation_1 FOREIGN KEY (user_id)
        REFERENCES Users (user_id),

    CONSTRAINT FK_Screening_TO_Reservation_1 FOREIGN KEY (screening_id)
        REFERENCES Screening (screening_id)
);


CREATE TABLE `Seats`
(
    `seats_id` BIGINT NOT NULL AUTO_INCREMENT,
    `seat_row` INT NULL,
    `seat_col` INT NULL,
    `room_id`  BIGINT NOT NULL,

    CONSTRAINT PK_SEATS PRIMARY KEY (seats_id),

    CONSTRAINT FK_Room_TO_Seats_1 FOREIGN KEY (room_id)
        REFERENCES Room (room_id)
);


CREATE TABLE `Reservation_Detail`
(
    `reservation_detail_id` BIGINT NOT NULL AUTO_INCREMENT,
    `reservation_id`        BIGINT NOT NULL,
    `seats_id`              BIGINT NOT NULL,

    CONSTRAINT PK_RESERVATION_DETAIL PRIMARY KEY (reservation_detail_id),

    CONSTRAINT FK_Reservation_TO_Reservation_Detail_1 FOREIGN KEY (reservation_id)
        REFERENCES Reservation (reservation_id),

    CONSTRAINT FK_Seats_TO_Reservation_Detail_1 FOREIGN KEY (seats_id)
        REFERENCES Seats (seats_id)
);
