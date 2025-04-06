/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : lin-cms

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 24/04/2024 23:20:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for news
-- ----------------------------
CREATE TABLE `news` (
                        `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                        `title` VARCHAR(255) NOT NULL COMMENT '新闻标题',
                        `source` VARCHAR(255) DEFAULT NULL COMMENT '来源',
                        `category` VARCHAR(255) DEFAULT NULL COMMENT '分类',
                        `image` VARCHAR(1024) DEFAULT NULL COMMENT '图片链接',
                        `content` MEDIUMTEXT NOT NULL COMMENT '内容',
                        `top` TINYINT(1) DEFAULT 0 COMMENT '是否置顶, 0: 不置顶, 1: 置顶',
                        `recommend` TINYINT(1) DEFAULT 0 COMMENT '是否推荐, 0: 不推荐, 1: 推荐',
                        `is_deleted` TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标识, 0: 未删除, 1: 已删除',
                        `creator` VARCHAR(64) DEFAULT NULL COMMENT '创建人',
                        `updater` VARCHAR(64) DEFAULT NULL COMMENT '修改人',
                        `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        `delete_time` DATETIME DEFAULT NULL COMMENT '删除时间',
                        `like_count` INT UNSIGNED DEFAULT 0 COMMENT '点赞量',
                        `comments` INT UNSIGNED DEFAULT 0 COMMENT '评论量',
                        `views` INT UNSIGNED DEFAULT 0 COMMENT '阅读量',
                        PRIMARY KEY (`id`),
                        KEY `idx_title` (`title`),
                        KEY `idx_creator` (`creator`),
                        KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='新闻表';
-- ----------------------------
-- Table structure for lin_file
-- ----------------------------
DROP TABLE IF EXISTS `lin_file`;
CREATE TABLE `lin_file`  (
  `id` int unsigned NOT NULL,
  `path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'LOCAL' COMMENT 'LOCAL 本地，REMOTE 远程',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `extension` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `size` int(0) DEFAULT NULL,
  `md5` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'md5值，防止上传重复文件',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `md5_del`(`md5`, `delete_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lin_file
-- ----------------------------
INSERT INTO `lin_file` VALUES (1, '2024\\04\\06\\350ee7aa15a346ceb005cf43bdfc5f6e.jpg', 'LOCAL', '350ee7aa15a346ceb005cf43bdfc5f6e.jpg', '.jpg', 72260, '3f2fc514fe137f5c889065ed7672911d', '2024-04-06 21:39:31.757', '2024-04-06 21:39:31.757', NULL, 0);
INSERT INTO `lin_file` VALUES (2, 'http://sc1c8677g.hn-bkt.clouddn.com/e6d030a594a94fe59a818d653327b07b', 'REMOTE', 'e6d030a594a94fe59a818d653327b07b', '', 231287, '4610ca23f0d88d3d59b81390acf9cd02', '2024-04-17 10:41:40.306', '2024-04-17 18:32:58.609', NULL, 0);
INSERT INTO `lin_file` VALUES (3, 'http://sc1c8677g.hn-bkt.clouddn.com/f0dc94ae7fa24d8087a862e467fe1fb5', 'REMOTE', 'f0dc94ae7fa24d8087a862e467fe1fb5', '', 33064, '1686089b09fcddb6371c834ff98326fa', '2024-04-17 21:50:28.971', '2024-04-17 21:50:28.971', NULL, 0);

-- ----------------------------
-- Table structure for lin_group
-- ----------------------------
DROP TABLE IF EXISTS `lin_group`;
CREATE TABLE `lin_group`  (
  `id` int unsigned NOT NULL,
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组名称，例如：搬砖者',
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分组信息：例如：搬砖的人',
  `level` tinyint(0) NOT NULL DEFAULT 3 COMMENT '分组级别 1：root 2：guest 3：user  root（root、guest分组只能存在一个)',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_del`(`name`, `delete_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lin_group
-- ----------------------------
INSERT INTO `lin_group` VALUES (1, 'root', '超级用户组', 1, '2024-03-12 16:44:28.185', '2024-03-12 16:44:28.185', NULL, 0);
INSERT INTO `lin_group` VALUES (2, 'guest', '游客组', 2, '2024-03-12 16:44:28.186', '2024-03-12 16:44:28.186', NULL, 0);
INSERT INTO `lin_group` VALUES (3, 'BookManager', '图书管理员', 3, '2024-04-22 19:33:45.191', '2024-04-22 19:33:45.191', NULL, 0);
INSERT INTO `lin_group` VALUES (4, '1111111111', '111', 3, '2024-04-22 19:42:50.965', '2024-04-22 19:43:54.461', NULL, 1);
INSERT INTO `lin_group` VALUES (5, '22', '2', 3, '2024-04-22 20:53:10.214', '2024-04-22 20:53:10.214', NULL, 0);

-- ----------------------------
-- Table structure for lin_group_permission
-- ----------------------------
DROP TABLE IF EXISTS `lin_group_permission`;
CREATE TABLE `lin_group_permission`  (
  `id` int unsigned NOT NULL,
  `group_id` int unsigned NOT NULL COMMENT '分组id',
  `permission_id` int unsigned NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `group_id_permission_id`(`group_id`, `permission_id`) USING BTREE COMMENT '联合索引'
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lin_group_permission
-- ----------------------------
INSERT INTO `lin_group_permission` VALUES (1, 1, 1);
INSERT INTO `lin_group_permission` VALUES (2, 1, 2);
INSERT INTO `lin_group_permission` VALUES (3, 1, 3);
INSERT INTO `lin_group_permission` VALUES (4, 1, 4);
INSERT INTO `lin_group_permission` VALUES (5, 3, 4);
INSERT INTO `lin_group_permission` VALUES (8, 5, 1);
INSERT INTO `lin_group_permission` VALUES (7, 5, 4);

-- ----------------------------
-- Table structure for lin_log
-- ----------------------------
DROP TABLE IF EXISTS `lin_log`;
CREATE TABLE `lin_log`  (
  `id` int unsigned NOT NULL,
  `message` varchar(450) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `user_id` int unsigned NOT NULL,
  `username` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status_code` int(0) DEFAULT NULL,
  `method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `permission` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lin_log
-- ----------------------------
INSERT INTO `lin_log` VALUES (1, '查询所有用户', 1, 'root', 200, 'GET', '/cms/admin/users', '查询所有用户', '2024-04-05 16:44:45.195', '2024-04-05 16:44:45.195', NULL, 0);

-- ----------------------------
-- Table structure for lin_permission
-- ----------------------------
DROP TABLE IF EXISTS `lin_permission`;
CREATE TABLE `lin_permission`  (
  `id` int unsigned NOT NULL,
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称，例如：访问首页',
  `module` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限所属模块，例如：人员管理',
  `mount` tinyint(1) NOT NULL DEFAULT 1 COMMENT '0：关闭 1：开启',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lin_permission
-- ----------------------------
INSERT INTO `lin_permission` VALUES (1, '查询所有日志', '日志', 1, '2024-03-17 16:00:29.551', '2024-03-17 16:00:29.551', NULL, 0);
INSERT INTO `lin_permission` VALUES (2, '搜索日志', '日志', 1, '2024-03-17 16:00:29.568', '2024-03-17 16:00:29.568', NULL, 0);
INSERT INTO `lin_permission` VALUES (3, '查询日志记录的用户', '日志', 1, '2024-03-17 16:00:29.573', '2024-03-17 16:00:29.573', NULL, 0);
INSERT INTO `lin_permission` VALUES (4, '删除图书', '图书', 1, '2024-03-17 16:00:29.579', '2024-03-17 16:00:29.579', NULL, 0);

-- ----------------------------
-- Table structure for lin_user
-- ----------------------------
DROP TABLE IF EXISTS `lin_user`;
CREATE TABLE `lin_user`  (
  `id` int unsigned NOT NULL,
  `username` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名，唯一',
  `nickname` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像url',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_del`(`username`, `delete_time`) USING BTREE,
  UNIQUE INDEX `email_del`(`email`, `delete_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lin_user
-- ----------------------------
INSERT INTO `lin_user` VALUES (1, 'root', 'root', NULL, NULL, '2024-03-12 16:44:28.183', '2024-03-12 16:44:28.183', NULL, 0);
INSERT INTO `lin_user` VALUES (2, 'guest1', NULL, NULL, NULL, '2024-03-26 16:17:07.923', '2024-03-26 16:17:07.923', NULL, 0);
INSERT INTO `lin_user` VALUES (3, 'normal', NULL, NULL, NULL, '2024-03-26 16:17:31.444', '2024-03-26 16:17:31.444', NULL, 0);

-- ----------------------------
-- Table structure for lin_user_group
-- ----------------------------
DROP TABLE IF EXISTS `lin_user_group`;
CREATE TABLE `lin_user_group`  (
  `id` int unsigned NOT NULL,
  `user_id` int unsigned NOT NULL COMMENT '用户id',
  `group_id` int unsigned NOT NULL COMMENT '分组id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id_group_id`(`user_id`, `group_id`) USING BTREE COMMENT '联合索引'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lin_user_group
-- ----------------------------
INSERT INTO `lin_user_group` VALUES (1, 1, 1);
INSERT INTO `lin_user_group` VALUES (2, 2, 2);
INSERT INTO `lin_user_group` VALUES (3, 3, 2);

-- ----------------------------
-- Table structure for lin_user_identity
-- ----------------------------
DROP TABLE IF EXISTS `lin_user_identity`;
CREATE TABLE `lin_user_identity`  (
  `id` int unsigned NOT NULL,
  `user_id` int unsigned NOT NULL COMMENT '用户id',
  `identity_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `identifier` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `credential` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lin_user_identity
-- ----------------------------
INSERT INTO `lin_user_identity` VALUES (1, 1, 'USERNAME_PASSWORD', 'root', 'pbkdf2sha256:64000:18:24:n:yUnDokcNRbwILZllmUOItIyo9MnI00QW:6ZcPf+sfzyoygOU8h/GSoirF', '2024-03-12 16:44:28.184', '2024-03-12 16:44:28.184', NULL, 0);
INSERT INTO `lin_user_identity` VALUES (2, 2, 'USERNAME_PASSWORD', 'guest1', 'pbkdf2sha256:64000:18:24:n:btJs86l0cGsci+f4TrvtYddTzeNBTiV/:wVoYOOhFL33v/2Apv8fz1IeL', '2024-03-26 16:17:08.094', '2024-03-27 20:36:40.225', NULL, 0);
INSERT INTO `lin_user_identity` VALUES (3, 3, 'USERNAME_PASSWORD', 'normal', 'pbkdf2sha256:64000:18:24:n:6PhHbfnxSw8Zf1fe6Ud3azI6FtWMSzGM:/+81HIvWgc3cg4nK8W4Z2ICq', '2024-03-26 16:17:31.615', '2024-03-26 16:17:31.615', NULL, 0);

-- ----------------------------
-- Table structure for comments
-- ----------------------------
CREATE TABLE comments (
                          id INT PRIMARY KEY AUTO_INCREMENT COMMENT '评论id',
                          content VARCHAR(255) NOT NULL COMMENT '评论内容',
                          user_id INT NOT NULL COMMENT '评论人id',
                          nickname VARCHAR(50) COMMENT '评论人昵称',
                          news_id INT NOT NULL COMMENT '被评论新闻id',
                          create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          delete_time DATETIME COMMENT '删除时间',
                          is_deleted TINYINT(1) DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
                          INDEX idx_user_id (user_id),
                          INDEX idx_news_id (news_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';


-- ----------------------------
-- Table structure for settings
-- ----------------------------
CREATE TABLE setting (
                         id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
                         type INT NOT NULL COMMENT '设置类型',
                         name VARCHAR(50) NOT NULL COMMENT '设置名称',
                         value VARCHAR(255) NOT NULL COMMENT '设置值',
                         parent_id INT COMMENT '父设置id',
                         create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         delete_time DATETIME COMMENT '删除时间',
                         is_deleted TINYINT(1) DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
                         is_enable TINYINT(1) NOT NULL COMMENT '是否启用 (0: 禁用, 1: 启用)'
                         INDEX idx_parent_id (parent_id),
                         INDEX idx_type (type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统设置表';


-- ----------------------------
-- Records of setting
-- ----------------------------
INSERT INTO `setting` VALUES (1, 1, '寰球新闻网', '1', NULL, '2025-04-06 14:42:26', '2025-04-06 14:43:44', NULL, 0, 1);
INSERT INTO `setting` VALUES (2, 1, '中国新闻网', '2', NULL, '2025-04-06 14:43:29', '2025-04-06 14:43:29', NULL, 0, 1);

SET FOREIGN_KEY_CHECKS = 1;

