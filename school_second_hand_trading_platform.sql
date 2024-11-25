/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37)
 Source Host           : localhost:3306
 Source Schema         : school_second_hand_trading_platform

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37)
 File Encoding         : 65001

 Date: 25/11/2024 10:51:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auctions
-- ----------------------------
DROP TABLE IF EXISTS `auctions`;
CREATE TABLE `auctions`  (
  `auction_id` int NOT NULL AUTO_INCREMENT COMMENT '拍卖ID，唯一标识每个拍卖商品',
  `seller_id` int UNSIGNED NOT NULL COMMENT '卖家ID，关联用户表的用户ID（外键）',
  `title` varchar(255) CHARACTER SET utf32 COLLATE utf32_general_ci NOT NULL COMMENT '拍卖商品标题，简短的商品名称或描述',
  `description` text CHARACTER SET utf32 COLLATE utf32_general_ci NULL COMMENT '商品描述，提供更多关于拍卖商品的信息',
  `start_price` decimal(10, 2) NOT NULL COMMENT '起拍价，拍卖开始时的最低竞价价格',
  `current_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '当前价格，竞拍过程中当前的最高价格',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '拍卖开始时间，记录拍卖何时开始',
  `end_time` timestamp NOT NULL COMMENT '拍卖结束时间，记录拍卖何时结束',
  `status` enum('PENDING','ONGOING','SOLD','CANCELLED') CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT 'PENDING' COMMENT '拍卖状态：待开始、进行中、已售、已取消',
  `winner_id` int UNSIGNED NULL DEFAULT NULL COMMENT '中标者ID，关联用户表的用户ID（外键，只有拍卖成功时才会有值）',
  `payment_status` enum('PENDING','PAID','UNPAID') CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT 'PENDING' COMMENT '支付状态：待支付、已支付、未支付',
  `auction_image` varchar(255) CHARACTER SET utf32 COLLATE utf32_general_ci NULL DEFAULT NULL COMMENT '商品展示图链接url',
  PRIMARY KEY (`auction_id`) USING BTREE,
  INDEX `seller_id`(`seller_id` ASC) USING BTREE,
  INDEX `winner_id`(`winner_id` ASC) USING BTREE,
  CONSTRAINT `auctions_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `auctions_ibfk_2` FOREIGN KEY (`winner_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf32 COLLATE = utf32_general_ci COMMENT = '拍卖商品表，存储每个教材的拍卖信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auctions
-- ----------------------------
INSERT INTO `auctions` VALUES (1, 1, '2', '122', 1.00, 1.00, '2024-11-22 16:50:02', '2024-11-22 16:50:02', 'ONGOING', NULL, 'PENDING', NULL);
INSERT INTO `auctions` VALUES (2, 1, '2', '122', 1.00, 1.00, '2024-11-22 16:53:41', '2024-11-22 16:50:02', 'PENDING', NULL, 'PENDING', NULL);
INSERT INTO `auctions` VALUES (3, 1, '1', '1', 1.00, 1.00, '2024-11-22 16:54:56', '2024-12-12 12:12:12', 'PENDING', NULL, 'PENDING', NULL);
INSERT INTO `auctions` VALUES (4, 1, '1', '1', 1.00, 1.00, '2024-11-22 16:55:19', '2024-12-12 12:12:12', 'PENDING', NULL, 'PENDING', NULL);
INSERT INTO `auctions` VALUES (5, 1, '1', '1', 1.00, 1.00, '2024-11-22 17:05:33', '2024-12-12 12:12:12', 'PENDING', NULL, 'PENDING', NULL);
INSERT INTO `auctions` VALUES (6, 1, '1', '1', 1.00, 1.00, '2024-11-22 17:05:37', '2024-12-12 12:12:12', 'PENDING', NULL, 'PENDING', NULL);
INSERT INTO `auctions` VALUES (7, 1, '1', '1', 1.00, 1.00, '2024-11-22 17:26:07', '2024-12-12 12:12:12', 'PENDING', NULL, 'PENDING', NULL);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户邮箱，唯一',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码（加密存储）',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户手机号（可选）',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像链接（可选）',
  `role` enum('user','admin') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'user' COMMENT '用户角色（普通用户或管理员）',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `status` enum('active','banned') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'active' COMMENT '用户状态（正常或禁用）',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `email`(`email` ASC) USING BTREE,
  INDEX `idx_email`(`email`(191) ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表，包括用户注册信息、个人资料和角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '131231@qq.com', 'dc483e80a7a0bd9ef71d8cf973673924', 'zhangsan', NULL, NULL, 'user', '2024-11-19 00:00:12', 'active');
INSERT INTO `users` VALUES (8, '132131', 'dc483e80a7a0bd9ef71d8cf973673924', 'test1', NULL, NULL, 'user', '2024-11-21 22:53:30', 'active');
INSERT INTO `users` VALUES (11, '1322131', 'dc483e80a7a0bd9ef71d8cf973673924', 'test1', NULL, NULL, 'user', '2024-11-21 22:54:46', 'active');
INSERT INTO `users` VALUES (13, '123', '202cb962ac59075b964b07152d234b70', '2b948e3f43054d8d95a28158c2e271b9', NULL, NULL, 'user', '2024-11-21 23:15:59', 'active');
INSERT INTO `users` VALUES (15, '122@qq.com', '202cb962ac59075b964b07152d234b70', 'ab3ff95b4c3a438f94422c4a77d48648', NULL, NULL, 'user', '2024-11-22 01:10:30', 'active');

SET FOREIGN_KEY_CHECKS = 1;
