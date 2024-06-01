-- 创建名为 foxtube 的数据库
DROP  DATABASE IF EXISTS foxtube;
CREATE DATABASE foxtube;
USE foxtube;

-- 创建 Video 表
CREATE TABLE IF NOT EXISTS videos
(
    id           BIGINT  AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    cover        VARCHAR(255),
    url          VARCHAR(255) NOT NULL,
    description  TEXT,
    published_at BIGINT ,
    likes        BIGINT  DEFAULT 0,
    author_id    BIGINT           NOT NULL
);

-- 创建 User 表
CREATE TABLE IF NOT EXISTS users
(
    id          BIGINT  AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(255) NOT NULL UNIQUE,
    email       VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    avatar      VARCHAR(255),
    profile     TEXT,
    video_count BIGINT  DEFAULT 0
);

-- 创建 Like 表
CREATE TABLE IF NOT EXISTS likes
(
    id       BIGINT  AUTO_INCREMENT PRIMARY KEY,
    video_id BIGINT  NOT NULL,
    user_id  BIGINT  NOT NULL
);

-- 创建 Comment 表
CREATE TABLE IF NOT EXISTS comments
(
    id         BIGINT  AUTO_INCREMENT PRIMARY KEY,
    content    TEXT NOT NULL,
    created_at BIGINT ,
    video_id   BIGINT   NOT NULL,
    author_id  BIGINT   NOT NULL
);

-- 插入 Video 表的数据
INSERT INTO videos (title, cover, url, description, published_at, likes, author_id)
VALUES ('Video Title 1', 'cover_url_1.jpg', 'video_url_1.mp4', 'Description for Video 1', 1633070400, 100, 1),
       ('Video Title 2', 'cover_url_2.jpg', 'video_url_2.mp4', 'Description for Video 2', 1633052800, 150, 2),
       ('Video Title 3', 'cover_url_3.jpg', 'video_url_3.mp4', 'Description for Video 3', 1632977200, 200, 3);

-- 插入 User 表的数据
INSERT INTO users (username, email, password, avatar, profile, video_count)
VALUES ('user1', 'user1@example.com', 'password123', 'avatar1.jpg', 'Profile for User 1', 5),
       ('user2', 'user2@example.com', 'password456', 'avatar2.jpg', 'Profile for User 2', 3),
       ('user3', 'user3@example.com', 'password789', 'avatar3.jpg', 'Profile for User 3', 7);

-- 插入 Like 表的数据
INSERT INTO likes (video_id, user_id)
VALUES (1, 2),
       (1, 3),
       (2, 1),
       (2, 3),
       (3, 1),
       (3, 2);

-- 插入 Comment 表的数据
INSERT INTO comments (content, created_at, video_id, author_id)
VALUES ('Great video!', 1633087200, 1, 2),
       ('I learned a lot from this.', 1633091000, 1, 3),
       ('Thanks for sharing!', 1633078800, 2, 1),
       ('Looking forward to more content.', 1633093600, 2, 3),
       ('Very informative.', 1633084400, 3, 1),
       ('Subscribed!', 1633097200, 3, 2);
