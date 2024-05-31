-- 创建名为 foxtube 的数据库
CREATE DATABASE IF NOT EXISTS foxtube;
USE foxtube;

-- 创建 Video 表
CREATE TABLE IF NOT EXISTS videos
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    cover        VARCHAR(255),
    url          VARCHAR(255) NOT NULL,
    description  TEXT,
    published_at INT,
    likes        INT DEFAULT 0,
    author_id    INT          NOT NULL
);

-- 创建 User 表
CREATE TABLE IF NOT EXISTS users
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(255) NOT NULL UNIQUE,
    email       VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    avatar      VARCHAR(255),
    profile     TEXT,
    video_count INT DEFAULT 0
);

-- 创建 Like 表
CREATE TABLE IF NOT EXISTS likes
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    video_id INT NOT NULL,
    user_id  INT NOT NULL
);

-- 创建 Comment 表
CREATE TABLE IF NOT EXISTS comments
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    content    TEXT NOT NULL,
    created_at INT,
    video_id   INT  NOT NULL,
    author_id  INT  NOT NULL
);
