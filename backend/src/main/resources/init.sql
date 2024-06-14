-- 创建名为 foxtube 的数据库
DROP DATABASE IF EXISTS foxtube;
CREATE DATABASE foxtube;
USE foxtube;

-- 创建 Video 表
CREATE TABLE IF NOT EXISTS videos
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    cover        VARCHAR(255),
    url          VARCHAR(255) NOT NULL,
    description  TEXT,
    published_at BIGINT,
    likes        BIGINT DEFAULT 0,
    author_id    BIGINT       NOT NULL
);

-- 创建 User 表
CREATE TABLE IF NOT EXISTS users
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(255) NOT NULL UNIQUE,
    email       VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    avatar      VARCHAR(255),
    profile     TEXT,
    video_count BIGINT DEFAULT 0
);

-- 创建 Like 表
CREATE TABLE IF NOT EXISTS likes
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    video_id BIGINT NOT NULL,
    user_id  BIGINT NOT NULL
);

-- 创建 Comment 表
CREATE TABLE IF NOT EXISTS comments
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    content    TEXT   NOT NULL,
    created_at BIGINT,
    video_id   BIGINT NOT NULL,
    author_id  BIGINT NOT NULL
);

-- 插入 Video 表的数据
INSERT INTO videos (title, cover, url, description, published_at, likes, author_id)
VALUES ('Shameless S01E01-0', 'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-0.jpg',
        'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-0.mp4', 'Shameless Season 1 Episode 1 Part 1',
        1717491664908, 765, 2),
       ('Shameless S01E01-1', 'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-1.jpg',
        'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-1.mp4', 'Shameless Season 1 Episode 1 Part 2',
        1717491764908, 432, 3),
       ('Shameless S01E01-2', 'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-2.jpg',
        'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-2.mp4', 'Shameless Season 1 Episode 1 Part 3',
        1717491864908, 999, 1),
       ('Shameless S01E01-3', 'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-3.jpg',
        'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-3.mp4', 'Shameless Season 1 Episode 1 Part 4',
        1717491964908, 123, 4),
       ('Shameless S01E01-4', 'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-4.jpg',
        'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-4.mp4', 'Shameless Season 1 Episode 1 Part 5',
        1717492064908, 876, 2),
       ('Shameless S01E01-5', 'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-5.jpg',
        'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-5.mp4', 'Shameless Season 1 Episode 1 Part 6',
        1717492164908, 555, 3),
       ('Shameless S01E01-6', 'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-6.jpg',
        'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-6.mp4', 'Shameless Season 1 Episode 1 Part 7',
        1717492264908, 321, 1),
       ('Shameless S01E01-7', 'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-7.jpg',
        'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-7.mp4', 'Shameless Season 1 Episode 1 Part 8',
        1717492364908, 987, 4),
       ('Shameless S01E01-8', 'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-8.jpg',
        'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-8.mp4', 'Shameless Season 1 Episode 1 Part 9',
        1717492464908, 456, 2),
       ('Shameless S01E01-9', 'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-9.jpg',
        'https://foxtube.blob.core.windows.net/storage/Shameless S01E01-9.mp4', 'Shameless Season 1 Episode 1 Part 10',
        1717492564908, 789, 3);


-- 插入 User 表的数据
INSERT INTO users (username, email, password, avatar, profile, video_count)
VALUES ('user1', 'user1@example.com', '$2a$10$orG/qGzTFsuzQavTCCTVyedLjzjA2okiR8eBFVJe.mfkUnsIaAoMG',
        'https://tvax4.sinaimg.cn/large/9bd9b167ly1g1p9pfhnf6j20b40b4glv.jpg',
        'Hello, my name is Emily, a passionate filmmaker from New York. I love exploring the world through my lens and sharing inspiring stories. Follow my channel for captivating travel documentaries and creative shorts!',
        2),
       ('user2', 'user2@example.com', '$2a$10$yLmpayV.ZMiWi25x4Vp1auIavHl1EZlPIdq.lBrsyfRQELP/iaiAK',
        'https://tvax3.sinaimg.cn/large/9bd9b167ly1g1p9w0x81dj20b40b4752.jpg',
        'Hey there, I’m Daniel, a vlogger based in Los Angeles. I’m all about tech reviews, gaming tips, and the latest gadgets. Join me on my journey through the exciting world of technology!',
        3),
       ('user3', 'user3@example.com', '$2a$10$mgS8URIlt8qjS6CGIugqgOMxxRWps2LuaBqF9qwg4XT879dXonSL.',
        'https://tvax1.sinaimg.cn/large/9bd9b167ly1g1p9aso3fhj20b40b4mxs.jpg',
        'Hi, I’m Olivia, a food enthusiast and chef-in-training. On my channel, you’ll find delicious recipes, cooking tutorials, and culinary adventures from around the globe. Let’s embark on a flavorful journey together!',
        3),
       ('user4', 'user4@example.com', '$2a$10$UNU1Zz.Xv.RufHFDb8y8Ke9d5GL8PhihMYREcjtiQNIVifqI5/9J6',
        'https://tvax1.sinaimg.cn/large/9bd9b167ly1fzjxymncosj20b40b4dgb.jpg',
        'What’s up, I’m Ethan, a fitness addict and personal trainer. My channel is dedicated to helping you achieve your health and wellness goals. Expect workout tips, healthy lifestyle hacks, and motivational content to keep you inspired!',
        2);

-- 插入 Like 表的数据
INSERT INTO likes (video_id, user_id)
VALUES (3, 1),
       (2, 1),
       (6, 1),
       (1, 2),
       (5, 2),
       (8, 2),
       (3, 3),
       (5, 3),
       (1, 3),
       (8, 4),
       (3, 4),
       (6, 4);

-- 插入 Comment 表的数据
INSERT INTO comments (content, created_at, video_id, author_id)
VALUES ('This video was absolutely amazing! The content was so engaging and informative. I couldn\'t stop watching!',
        1717492664908, 1, 1),
       ('I loved every minute of this video. The editing was top-notch and the music was perfect. Great job!',
        1717492664910, 2, 2),
       ('This video blew me away! The ideas presented were so innovative and inspiring. I\'m looking forward to more!',
        1717492664912, 3, 3),
       ('I must say, this is one of the best videos I\'ve seen in a long time. The production quality is excellent and the story is captivating.',
        1717492664914, 4, 4),
       ('The way this video was presented was truly unique. I learned so much from it and I can\'t wait to share it with my friends.',
        1717492664916, 5, 1),
       ('The visuals in this video were stunning. The color palette and cinematography were on another level. Well done!',
        1717492664918, 6, 2),
       ('The presenter did an excellent job of explaining the complex concepts in this video. It was easy to understand and engaging from start to finish.',
        1717492664920, 7, 3),
       ('This video kept me on the edge of my seat! The plot twists and surprises kept me wanting more. Can\'t recommend it enough!',
        1717492664922, 8, 4),
       ('The message of this video is so important. It really resonated with me and I think it will with many others too. Well done on such a great piece!',
        1717492664924, 9, 1),
       ('The acting in this video was superb. The characters were so believable and the emotions were portrayed perfectly. A must-watch!',
        1717492664926, 10, 2),
       ('The humor in this video was spot on! It made me laugh out loud several times. Definitely worth checking out!',
        1717492664928, 1, 3),
       ('This video gave me a whole new perspective on a topic I\'d never considered before. It was eye-opening and thought-provoking.',
        1717492664930, 2, 4),
       ('The animation in this video was breathtaking. The detail and creativity were amazing. I couldn\'t believe my eyes!',
        1717492664932, 3, 1),
       ('I was blown away by the storytelling in this video. The narrative flowed seamlessly and kept me captivated.',
        1717492664934, 4, 2),
       ('The sound design in this video was outstanding. The audio effects and music added so much to the overall experience.',
        1717492664936, 5, 3),
       ('This video is a true masterpiece. It combines creativity, artistry, and storytelling in a way that is truly unique.',
        1717492664938, 6, 4),
       ('The director did an amazing job of capturing the essence of this story in a visual format. I was completely immersed!',
        1717492664940, 7, 1),
       ('The cinematography in this video is absolutely stunning. The shots are so beautifully composed and the framing is perfect.',
        1717492664942, 8, 2),
       ('The acting in this video is so natural and realistic. The actors really brought the characters to life and made them believable.',
        1717492664944, 9, 3),
       ('This video is a must-see for anyone interested in [topic]. It covers all the important aspects in a way that is easy to understand and engaging.',
        1717492664946, 10, 4),
       ('Fantastic content!', 1717492664909, 1, 1),
       ('Loved it!', 1717492664911, 2, 2),
       ('Great video!', 1717492664913, 3, 3),
       ('Must watch!', 1717492664915, 4, 4),
       ('Interesting!', 1717492664917, 5, 1),
       ('Learned a lot', 1717492664919, 6, 2),
       ('Enjoyed the editing', 1717492664921, 7, 3),
       ('Keep it up!', 1717492664923, 8, 4),
       ('Great job!', 1717492664925, 9, 1),
       ('Loved the ending', 1717492664927, 10, 2),
       ('So informative', 1717492664929, 1, 3),
       ('Watched it twice', 1717492664931, 2, 4),
       ('Loved the music', 1717492664933, 3, 1),
       ('Hope for more!', 1717492664935, 4, 2),
       ('Inspiring!', 1717492664937, 5, 3),
       ('Keep sharing!', 1717492664939, 6, 4),
       ('Great stuff!', 1717492664941, 7, 1),
       ('Enjoyed every bit', 1717492664943, 8, 2),
       ('Loved the concept', 1717492664945, 9, 3),
       ('Looking forward to more!', 1717492664947, 10, 4);
