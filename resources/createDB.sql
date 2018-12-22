CREATE DATABASE IF NOT EXISTS instabook;

USE instabook;

DROP TABLE IF EXISTS friends;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    userId int(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    userName varchar(60) 
);

CREATE TABLE posts (
    postId int(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    userId int(11) NOT NULL REFERENCES users(userId),
    content text
);

CREATE TABLE comments (
    commentId int(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    postId int(11) NOT NULL REFERENCES posts(postId),
    userId int(11) NOT NULL REFERENCES users(userId),
    content text
);

CREATE TABLE friends (
    friendId int(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    userId1 int(11) NOT NULL REFERENCES users(userId),
    userId2 int(11) NOT NULL REFERENCES users(userId)
);