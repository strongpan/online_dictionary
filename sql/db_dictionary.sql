DROP DATABASE IF EXISTS db_dictionary;
CREATE DATABASE db_dictionary;

-- table admin
CREATE TABLE db_dictionary.admin (
  id       INT UNSIGNED AUTO_INCREMENT PRIMARY KEY
  COMMENT 'PK',
  username VARCHAR(255) NOT NULL
  COMMENT '用户名',
  password VARCHAR(255) NOT NULL
  COMMENT '密码'
)
  COMMENT '管理员表';

INSERT INTO db_dictionary.admin VALUES (NULL, 'admin', '123');

-- table word
CREATE TABLE db_dictionary.word (
  id           INT UNSIGNED AUTO_INCREMENT PRIMARY KEY
  COMMENT 'PK',
  english      VARCHAR(255) NOT NULL
  COMMENT '英文',
  chinese      VARCHAR(255) NOT NULL
  COMMENT '中文',
  phonetic     VARCHAR(255) NOT NULL
  COMMENT '音标',
  partOfSpeech VARCHAR(255) NOT NULL
  COMMENT '词性',
  category     VARCHAR(255) NOT NULL
  COMMENT '类别'
)
  COMMENT '单词表';

SELECT *
FROM db_dictionary.word;