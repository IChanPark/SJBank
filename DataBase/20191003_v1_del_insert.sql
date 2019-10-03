-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.3.16-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- bank 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `bank` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bank`;

-- 테이블 bank.account 구조 내보내기
DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `account_number` varchar(50) NOT NULL COMMENT '계좌번호',
  `type` varchar(50) NOT NULL COMMENT '타입 ex 예금, 펀드 등',
  `sum` bigint(11) NOT NULL COMMENT '보유금',
  `alias` varchar(50) NOT NULL COMMENT '사용자 지정이름',
  `id` varchar(50) NOT NULL COMMENT '사용자 id',
  `pw` varchar(50) NOT NULL COMMENT '계좌비밀번호',
  `status` varchar(50) NOT NULL COMMENT '상태 활성/비활성/휴먼',
  `register_date` datetime NOT NULL COMMENT '개설일',
  `end_date` datetime DEFAULT NULL COMMENT '만료일/폐쇄일',
  PRIMARY KEY (`account_number`),
  KEY `id` (`id`),
  KEY `type` (`type`),
  CONSTRAINT `FK_account_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='계좌 정보';

-- 테이블 데이터 bank.account:~4 rows (대략적) 내보내기
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`account_number`, `type`, `sum`, `alias`, `id`, `pw`, `status`, `register_date`, `end_date`) VALUES
	('010-1111-1111-121', 'deposit', 75310, '미지정', 'day_0821', '0092', 'y', '2019-09-05 08:31:36', NULL),
	('010-1111-112-1212', 'deposit', 100000000, '월급', 'elliottjo', '1234', '활성', '2019-09-18 01:00:46', NULL),
	('010-1111-112-1213', 'deposit', 1000000, '학자금', 'day_0821', '1111', '활성', '2010-10-10 10:10:10', '2222-02-22 22:22:22'),
	('101-1112-112-1232', 'fund', 500000, '오일펀드', 'elliottjo', '1234', '활성', '2019-09-18 01:01:33', NULL),
	('110-346-555760', 'deposit', 100000000, '비상금', 'elliottjo', '1234', '활성', '2019-10-02 22:18:26', NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- 테이블 bank.account_modify_log 구조 내보내기
DROP TABLE IF EXISTS `account_modify_log`;
CREATE TABLE IF NOT EXISTS `account_modify_log` (
  `seq` int(11) NOT NULL,
  `account_number` varchar(50) NOT NULL COMMENT '계좌번호',
  `pw` varchar(50) NOT NULL COMMENT '변경전',
  `modify_pw` varchar(50) NOT NULL COMMENT '변경후',
  `register_date` datetime NOT NULL COMMENT '수정한날짜',
  PRIMARY KEY (`seq`),
  KEY `account_number` (`account_number`),
  CONSTRAINT `FK_account_modify_log_account` FOREIGN KEY (`account_number`) REFERENCES `account` (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='계좌정보 수정 이력';

-- 테이블 데이터 bank.account_modify_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `account_modify_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_modify_log` ENABLE KEYS */;

-- 테이블 bank.account_pw_log 구조 내보내기
DROP TABLE IF EXISTS `account_pw_log`;
CREATE TABLE IF NOT EXISTS `account_pw_log` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `account_number` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL COMMENT '유효한 로그인지',
  `register_date` datetime NOT NULL COMMENT '등록일시',
  PRIMARY KEY (`seq`),
  KEY `account_number` (`account_number`),
  KEY `status` (`status`),
  CONSTRAINT `fk_account_pw_log_account` FOREIGN KEY (`account_number`) REFERENCES `account` (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='계좌별 비밀번호 틀린 횟수 로그테이블\r\n해당 테이블에 동일한 계좌번호이며 status가 y인 로그가 3개 있을경우 해당\r\n계좌번호는 account테이블에 상태를 비활성화함';

-- 테이블 데이터 bank.account_pw_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `account_pw_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_pw_log` ENABLE KEYS */;

-- 테이블 bank.account_type 구조 내보내기
DROP TABLE IF EXISTS `account_type`;
CREATE TABLE IF NOT EXISTS `account_type` (
  `type` varchar(50) NOT NULL COMMENT '타입',
  `name` varchar(50) NOT NULL COMMENT '타입이름',
  PRIMARY KEY (`type`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='계좌 종류 데이터를 가진 테이블';

-- 테이블 데이터 bank.account_type:~5 rows (대략적) 내보내기
/*!40000 ALTER TABLE `account_type` DISABLE KEYS */;
INSERT INTO `account_type` (`type`, `name`) VALUES
	('isa', 'ISA'),
	('loan', '대출'),
	('deposit', '예금'),
	('saving', '적금'),
	('fund', '펀드');
/*!40000 ALTER TABLE `account_type` ENABLE KEYS */;

-- 테이블 bank.analysis 구조 내보내기
DROP TABLE IF EXISTS `analysis`;
CREATE TABLE IF NOT EXISTS `analysis` (
  `propensity` varchar(50) NOT NULL COMMENT '투자 성향',
  `name` varchar(50) NOT NULL COMMENT '투자 성향 이름',
  `scope` varchar(50) NOT NULL COMMENT '점수범위 0, 43(0이상 43이하)',
  `status` varchar(50) NOT NULL COMMENT '사용여부',
  `register_date` datetime NOT NULL COMMENT '등록일',
  PRIMARY KEY (`propensity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='투자 성향 분류';

-- 테이블 데이터 bank.analysis:~5 rows (대략적) 내보내기
/*!40000 ALTER TABLE `analysis` DISABLE KEYS */;
INSERT INTO `analysis` (`propensity`, `name`, `scope`, `status`, `register_date`) VALUES
	('active', '공격적투자형', '', '', '0000-00-00 00:00:00'),
	('neutral', '중립형', '', '', '0000-00-00 00:00:00'),
	('neutral_active', '적극적투자형', '', '', '0000-00-00 00:00:00'),
	('neutral_stability', '안정추구형', '', '', '0000-00-00 00:00:00'),
	('stability', '안정형', '', '', '0000-00-00 00:00:00');
/*!40000 ALTER TABLE `analysis` ENABLE KEYS */;

-- 테이블 bank.credit_rating 구조 내보내기
DROP TABLE IF EXISTS `credit_rating`;
CREATE TABLE IF NOT EXISTS `credit_rating` (
  `id` varchar(50) NOT NULL,
  `rating` int(11) NOT NULL DEFAULT 5 COMMENT '신용등급',
  `status` varchar(50) NOT NULL COMMENT '상태 탈퇴시 변경',
  `register_date` datetime NOT NULL COMMENT '등록일',
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_credit_rating_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='신용 등급 테이블';

-- 테이블 데이터 bank.credit_rating:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `credit_rating` DISABLE KEYS */;
/*!40000 ALTER TABLE `credit_rating` ENABLE KEYS */;

-- 테이블 bank.credit_rating_log 구조 내보내기
DROP TABLE IF EXISTS `credit_rating_log`;
CREATE TABLE IF NOT EXISTS `credit_rating_log` (
  `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '로그순서',
  `id` varchar(50) NOT NULL,
  `change` varchar(50) NOT NULL COMMENT '상승/하락',
  `reason` varchar(50) NOT NULL COMMENT '상승/하락 이유',
  `rating` int(11) NOT NULL COMMENT '변경된 등급',
  `register_date` datetime NOT NULL COMMENT '등록일',
  PRIMARY KEY (`seq`),
  KEY `id` (`id`),
  CONSTRAINT `FK_credit_rating_log_credit_rating` FOREIGN KEY (`id`) REFERENCES `credit_rating` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자 신용 등급 변동 이력';

-- 테이블 데이터 bank.credit_rating_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `credit_rating_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `credit_rating_log` ENABLE KEYS */;

-- 테이블 bank.deposits 구조 내보내기
DROP TABLE IF EXISTS `deposits`;
CREATE TABLE IF NOT EXISTS `deposits` (
  `account_number` varchar(50) NOT NULL COMMENT '계좌번호',
  `id` varchar(50) NOT NULL COMMENT '사용자 id',
  `product` varchar(50) NOT NULL COMMENT '상품명',
  `preferential` varchar(50) NOT NULL COMMENT '적용된 우대조건',
  `interest` float NOT NULL COMMENT '적용이자',
  `type` varchar(50) NOT NULL COMMENT '예금타입',
  `end_date` datetime DEFAULT NULL COMMENT '만기일',
  PRIMARY KEY (`account_number`),
  KEY `id` (`id`),
  KEY `fk_deposits_depositsinfo` (`product`),
  CONSTRAINT `fk_deposits_account` FOREIGN KEY (`account_number`) REFERENCES `account` (`account_number`),
  CONSTRAINT `fk_deposits_account_2` FOREIGN KEY (`id`) REFERENCES `account` (`id`),
  CONSTRAINT `fk_deposits_depositsinfo` FOREIGN KEY (`product`) REFERENCES `deposits_info` (`product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='예금 가입자';

-- 테이블 데이터 bank.deposits:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `deposits` DISABLE KEYS */;
/*!40000 ALTER TABLE `deposits` ENABLE KEYS */;

-- 테이블 bank.deposits_info 구조 내보내기
DROP TABLE IF EXISTS `deposits_info`;
CREATE TABLE IF NOT EXISTS `deposits_info` (
  `product` varchar(50) NOT NULL COMMENT '상품명',
  `deposits_info` varchar(200) NOT NULL,
  `min_interest` float NOT NULL COMMENT '최저 연이자',
  `max_interest` float NOT NULL COMMENT '최고 연이자',
  `month` int(11) NOT NULL COMMENT '만기 개월',
  `type` varchar(10) NOT NULL COMMENT '타입 보통, 정기',
  `interest_type` varchar(10) NOT NULL COMMENT '이자지급방식',
  `tax` varchar(50) NOT NULL COMMENT '과세여부 ',
  `preferential` varchar(200) NOT NULL COMMENT '우대구분 ',
  `prf_content` varchar(200) NOT NULL COMMENT '우대조건 내용',
  `prf_interest` varchar(200) NOT NULL COMMENT '우대이자율',
  `min_sum` int(11) NOT NULL COMMENT '최소금액',
  `max_sum` int(11) NOT NULL COMMENT '월납입 최대금액',
  `partialization` varchar(10) NOT NULL COMMENT '일부해지가능여부',
  `retention` varchar(10) NOT NULL COMMENT '재예치가능여부',
  `status` varchar(10) NOT NULL COMMENT '상태',
  `register_date` datetime NOT NULL COMMENT '상품등록일',
  `end_date` datetime DEFAULT NULL COMMENT '상품삭제일',
  PRIMARY KEY (`product`),
  KEY `type` (`type`),
  CONSTRAINT `FK_deposits_info_deposits_type` FOREIGN KEY (`type`) REFERENCES `deposits_type` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='예금 상품 정보';

-- 테이블 데이터 bank.deposits_info:~3 rows (대략적) 내보내기
/*!40000 ALTER TABLE `deposits_info` DISABLE KEYS */;
INSERT INTO `deposits_info` (`product`, `deposits_info`, `min_interest`, `max_interest`, `month`, `type`, `interest_type`, `tax`, `preferential`, `prf_content`, `prf_interest`, `min_sum`, `max_sum`, `partialization`, `retention`, `status`, `register_date`, `end_date`) VALUES
	('두번째', '', 0.8, 1.5, 12, '보통', '만기일시', '과세', '그냥#테스트', '고냥#테스트', '0.3#0.4', 100000, 10000000, '불가', '불가', '활성', '2019-10-01 11:00:08', NULL),
	('셋', '', 0.8, 0.9, 1, '보통', '만기일시', '과세', 'ㄱ', 'ㄱ', '0.1', 10000, 1000000, '불가', '불가', '활성', '2019-10-01 12:55:29', NULL),
	('첫번째', '', 0.7, 1.8, 12, '정기', '만기일시', '과세', '30세#프로그래머#기타', '30세라면누구나#프로그래머라면누구나#그냥줌', '0.3#0.7#0.1', 100000, 10000000, '불가', '불가', '활성', '2019-09-29 04:31:20', NULL);
/*!40000 ALTER TABLE `deposits_info` ENABLE KEYS */;

-- 테이블 bank.deposits_log 구조 내보내기
DROP TABLE IF EXISTS `deposits_log`;
CREATE TABLE IF NOT EXISTS `deposits_log` (
  `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '로그번호',
  `account_number` varchar(50) NOT NULL COMMENT '계좌',
  `interest` float NOT NULL COMMENT '이자',
  `sum` int(11) NOT NULL COMMENT '이자금액',
  `status` varchar(50) NOT NULL COMMENT '성공/실패(사유)',
  `register_date` datetime NOT NULL COMMENT '날짜',
  PRIMARY KEY (`seq`),
  KEY `account_number` (`account_number`),
  KEY `status` (`status`),
  CONSTRAINT `fk_deposits_log_account` FOREIGN KEY (`account_number`) REFERENCES `deposits` (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='월별 예금 계좌 이자 이력';

-- 테이블 데이터 bank.deposits_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `deposits_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `deposits_log` ENABLE KEYS */;

-- 테이블 bank.deposits_type 구조 내보내기
DROP TABLE IF EXISTS `deposits_type`;
CREATE TABLE IF NOT EXISTS `deposits_type` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 bank.deposits_type:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `deposits_type` DISABLE KEYS */;
INSERT INTO `deposits_type` (`name`) VALUES
	('보통'),
	('정기');
/*!40000 ALTER TABLE `deposits_type` ENABLE KEYS */;

-- 테이블 bank.email_sand 구조 내보내기
DROP TABLE IF EXISTS `email_sand`;
CREATE TABLE IF NOT EXISTS `email_sand` (
  `id` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='이메일 알림 테이블';

-- 테이블 데이터 bank.email_sand:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `email_sand` DISABLE KEYS */;
/*!40000 ALTER TABLE `email_sand` ENABLE KEYS */;

-- 테이블 bank.email_sand_log 구조 내보내기
DROP TABLE IF EXISTS `email_sand_log`;
CREATE TABLE IF NOT EXISTS `email_sand_log` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL COMMENT '보내는 이메일 계정',
  `to_email` varchar(50) NOT NULL COMMENT '사용자 이메일',
  `content` varchar(50) NOT NULL COMMENT '내용',
  `type` varchar(50) NOT NULL COMMENT '메일 발송종류 ? 알림, 문의 등',
  `status` varchar(50) NOT NULL COMMENT '전송 완료 여부',
  `register_date` datetime NOT NULL COMMENT '전송 일시',
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='이메일 전송 이력';

-- 테이블 데이터 bank.email_sand_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `email_sand_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `email_sand_log` ENABLE KEYS */;

-- 테이블 bank.faq 구조 내보내기
DROP TABLE IF EXISTS `faq`;
CREATE TABLE IF NOT EXISTS `faq` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(50) NOT NULL COMMENT '운영자',
  `title` varchar(50) NOT NULL COMMENT '제목',
  `content` varchar(50) NOT NULL COMMENT '내용',
  `type` varchar(50) NOT NULL COMMENT '자주하는질문 타입 타입별 분류 및 검색을 위해',
  `status` varchar(50) NOT NULL COMMENT '상태 비활성 시 안보임',
  `register_date` datetime NOT NULL COMMENT '등록일',
  PRIMARY KEY (`seq`),
  KEY `id` (`id`),
  CONSTRAINT `FK_faq_management` FOREIGN KEY (`id`) REFERENCES `management` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='자주하는 질문';

-- 테이블 데이터 bank.faq:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
INSERT INTO `faq` (`seq`, `id`, `title`, `content`, `type`, `status`, `register_date`) VALUES
	(1, 'kim', '망했어요', '몰 해도 안되~ 집에가자!! 때려쳐!!', '이체', '활성', '2019-09-30 00:00:00'),
	(3, 'god', '일해라 노예야', '참쉽죠 빨리 해주세요 5분 드립니다.', '명령', '활성', '2019-09-30 00:00:00');
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;

-- 테이블 bank.fund 구조 내보내기
DROP TABLE IF EXISTS `fund`;
CREATE TABLE IF NOT EXISTS `fund` (
  `account_number` varchar(50) NOT NULL COMMENT '계좌번호',
  `id` varchar(50) NOT NULL COMMENT 'id',
  `product` varchar(50) NOT NULL COMMENT '상품명',
  `fluctuation` float NOT NULL COMMENT '등락률',
  PRIMARY KEY (`account_number`),
  KEY `id` (`id`),
  KEY `product` (`product`),
  CONSTRAINT `fk_fund_account` FOREIGN KEY (`account_number`) REFERENCES `account` (`account_number`),
  CONSTRAINT `fk_fund_account_2` FOREIGN KEY (`id`) REFERENCES `account` (`id`),
  CONSTRAINT `fk_fund_fund_info` FOREIGN KEY (`product`) REFERENCES `fund_info` (`product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='펀드 가입자';

-- 테이블 데이터 bank.fund:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `fund` DISABLE KEYS */;
/*!40000 ALTER TABLE `fund` ENABLE KEYS */;

-- 테이블 bank.fund_info 구조 내보내기
DROP TABLE IF EXISTS `fund_info`;
CREATE TABLE IF NOT EXISTS `fund_info` (
  `product` varchar(50) NOT NULL COMMENT '상품명',
  `product_info` varchar(200) DEFAULT NULL COMMENT '상품설명',
  `price` float NOT NULL COMMENT '초기기준가',
  `price_modify` float NOT NULL COMMENT '수정기준가',
  `type` varchar(50) NOT NULL COMMENT '유형 주식, 채권 등등',
  `tax` varchar(50) NOT NULL COMMENT '과세여부',
  `area` varchar(50) NOT NULL COMMENT '국내/해외',
  `property` varchar(50) NOT NULL COMMENT '상품속성ex 인터넷..',
  `first_fee` float NOT NULL COMMENT '선취수수료',
  `fee` float NOT NULL COMMENT '년보수',
  `management` varchar(50) NOT NULL COMMENT '운용사',
  `sector` varchar(50) NOT NULL COMMENT '섹터',
  `status` varchar(50) NOT NULL COMMENT '상태',
  `register_date` datetime NOT NULL COMMENT '펀드등록일',
  `end_date` datetime NOT NULL COMMENT '펀드폐지일',
  PRIMARY KEY (`product`),
  KEY `type` (`type`),
  CONSTRAINT `FK_fund_info_fund_type` FOREIGN KEY (`type`) REFERENCES `fund_type` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='펀드 상품 정보';

-- 테이블 데이터 bank.fund_info:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `fund_info` DISABLE KEYS */;
INSERT INTO `fund_info` (`product`, `product_info`, `price`, `price_modify`, `type`, `tax`, `area`, `property`, `first_fee`, `fee`, `management`, `sector`, `status`, `register_date`, `end_date`) VALUES
	('테스트펀드', '테스트펀드입니다.\r\nㅎ\r\nㅎ\r\nㅎ\r\nㅎ\r\nㅎ\r\nㅎ\r\nㅎ\r\nㅎ\r\nㅎ\r\nㅎ', 1031, 1055, '혼합자산(재간접형)', '15.4%', '국내', '인터넷', 0.35, 0.56, '테스트운용사', '고위험', '유효', '2019-10-01 17:36:53', '2020-10-05 00:00:00'),
	('하나UBSPIMCO글로벌인컴혼합자산투자신탁(H)[재간접형]종류A-E', NULL, 1031, 1031, '혼합자산(재간접형)', '15.4%', '해외', '인터넷', 0.35, 0.56, '하나UBS자산운용', '보통위험', '활성', '2019-10-01 11:09:33', '2020-10-05 00:00:00');
/*!40000 ALTER TABLE `fund_info` ENABLE KEYS */;

-- 테이블 bank.fund_log 구조 내보내기
DROP TABLE IF EXISTS `fund_log`;
CREATE TABLE IF NOT EXISTS `fund_log` (
  `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '로그번호',
  `account_number` varchar(50) NOT NULL COMMENT '계좌',
  `fluctuation` float NOT NULL COMMENT '등락률',
  `sum` int(11) NOT NULL COMMENT '등락금액',
  `status` varchar(50) NOT NULL COMMENT '성공/실패(사유)',
  `register_date` datetime NOT NULL COMMENT '날짜',
  PRIMARY KEY (`seq`),
  KEY `account_number` (`account_number`),
  KEY `status` (`status`),
  CONSTRAINT `fk__fund` FOREIGN KEY (`account_number`) REFERENCES `fund` (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='일별 펀드 계좌 수익 적용 이력';

-- 테이블 데이터 bank.fund_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `fund_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `fund_log` ENABLE KEYS */;

-- 테이블 bank.fund_type 구조 내보내기
DROP TABLE IF EXISTS `fund_type`;
CREATE TABLE IF NOT EXISTS `fund_type` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='펀드 타입(유형) 검증용 테이블';

-- 테이블 데이터 bank.fund_type:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `fund_type` DISABLE KEYS */;
INSERT INTO `fund_type` (`name`) VALUES
	('혼합자산(재간접형)');
/*!40000 ALTER TABLE `fund_type` ENABLE KEYS */;

-- 테이블 bank.loan 구조 내보내기
DROP TABLE IF EXISTS `loan`;
CREATE TABLE IF NOT EXISTS `loan` (
  `account_number` varchar(50) NOT NULL COMMENT '계좌번호',
  `id` varchar(50) NOT NULL COMMENT '사용자 id',
  `product` varchar(50) NOT NULL COMMENT '상품명',
  `preferential` varchar(50) NOT NULL COMMENT '적용된 우대조건',
  `interest` float NOT NULL COMMENT '적용이자',
  `type` varchar(50) NOT NULL COMMENT '상환방식',
  `sum` int(11) NOT NULL COMMENT '대출금액',
  `period` varchar(50) NOT NULL COMMENT '대출기간ex 10,15,20...',
  `end_date` datetime NOT NULL COMMENT '만기일',
  PRIMARY KEY (`account_number`),
  KEY `id` (`id`),
  KEY `fk_loan_loaninfo` (`product`),
  CONSTRAINT `fk_loan_account` FOREIGN KEY (`account_number`) REFERENCES `account` (`account_number`),
  CONSTRAINT `fk_loan_account_2` FOREIGN KEY (`id`) REFERENCES `account` (`id`),
  CONSTRAINT `fk_loan_loaninfo` FOREIGN KEY (`product`) REFERENCES `loan_info` (`product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='대출 가입자';

-- 테이블 데이터 bank.loan:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `loan` DISABLE KEYS */;
/*!40000 ALTER TABLE `loan` ENABLE KEYS */;

-- 테이블 bank.loan_info 구조 내보내기
DROP TABLE IF EXISTS `loan_info`;
CREATE TABLE IF NOT EXISTS `loan_info` (
  `product` varchar(50) NOT NULL COMMENT '상품명',
  `product_info` varchar(50) DEFAULT NULL COMMENT '상품설명',
  `min_interest` float NOT NULL COMMENT '최저 연이자',
  `max_interest` float NOT NULL COMMENT '최고 연이자',
  `month` int(11) NOT NULL COMMENT '만기 개월',
  `type` varchar(10) NOT NULL COMMENT '대출 종류',
  `loanlimit` bigint(20) NOT NULL COMMENT '대출한도',
  `preferential` varchar(200) NOT NULL COMMENT '우대구분 ',
  `prf_content` varchar(200) NOT NULL COMMENT '우대조건 내용',
  `prf_interest` varchar(200) NOT NULL COMMENT '우대이자율',
  `status` varchar(10) NOT NULL COMMENT '상태',
  `register_date` datetime NOT NULL COMMENT '상품등록일',
  `end_date` datetime DEFAULT NULL COMMENT '상품삭제일',
  PRIMARY KEY (`product`),
  KEY `type` (`type`),
  CONSTRAINT `FK_loan_info_loan_type` FOREIGN KEY (`type`) REFERENCES `loan_type` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='대출 상품 정보';

-- 테이블 데이터 bank.loan_info:~1 rows (대략적) 내보내기
/*!40000 ALTER TABLE `loan_info` DISABLE KEYS */;
INSERT INTO `loan_info` (`product`, `product_info`, `min_interest`, `max_interest`, `month`, `type`, `loanlimit`, `preferential`, `prf_content`, `prf_interest`, `status`, `register_date`, `end_date`) VALUES
	('java대출', NULL, 2, 3, 12, '신용대출', 200000000, '프로그래머#java기술자', '프로그래머들#java가능', '0.3#0.7', '유효', '2019-10-01 15:30:05', NULL);
/*!40000 ALTER TABLE `loan_info` ENABLE KEYS */;

-- 테이블 bank.loan_log 구조 내보내기
DROP TABLE IF EXISTS `loan_log`;
CREATE TABLE IF NOT EXISTS `loan_log` (
  `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '로그번호',
  `account_number` varchar(50) NOT NULL COMMENT '계좌',
  `sum` int(11) NOT NULL COMMENT '납입금액',
  `remain` int(11) NOT NULL COMMENT '남은상환액',
  `status` varchar(50) NOT NULL COMMENT '성공/실패(사유)',
  `register_date` datetime NOT NULL COMMENT '날짜',
  PRIMARY KEY (`seq`),
  KEY `account_number` (`account_number`),
  KEY `status` (`status`),
  CONSTRAINT `fk_loan_log_account` FOREIGN KEY (`account_number`) REFERENCES `loan` (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='대출계좌 납부 이력';

-- 테이블 데이터 bank.loan_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `loan_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `loan_log` ENABLE KEYS */;

-- 테이블 bank.loan_type 구조 내보내기
DROP TABLE IF EXISTS `loan_type`;
CREATE TABLE IF NOT EXISTS `loan_type` (
  `name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 bank.loan_type:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `loan_type` DISABLE KEYS */;
INSERT INTO `loan_type` (`name`) VALUES
	('신용대출');
/*!40000 ALTER TABLE `loan_type` ENABLE KEYS */;

-- 테이블 bank.management 구조 내보내기
DROP TABLE IF EXISTS `management`;
CREATE TABLE IF NOT EXISTS `management` (
  `name` varchar(50) NOT NULL COMMENT '이름',
  `id` varchar(50) NOT NULL COMMENT '아이디',
  `pw` varchar(50) NOT NULL COMMENT '패스워드',
  `department` varchar(50) NOT NULL COMMENT '부서',
  `tel` varchar(50) NOT NULL COMMENT '연락처',
  `access_level` int(11) NOT NULL COMMENT '접근권한 1~',
  `status` varchar(50) NOT NULL COMMENT '계정상태',
  `register_date` datetime NOT NULL COMMENT '등록일',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='관리자 정보 테이블';

-- 테이블 데이터 bank.management:~3 rows (대략적) 내보내기
/*!40000 ALTER TABLE `management` DISABLE KEYS */;
INSERT INTO `management` (`name`, `id`, `pw`, `department`, `tel`, `access_level`, `status`, `register_date`) VALUES
	('갓이찬', 'god', '1111', '갓갓갓', '010-9999-9999', 9, 'y', '2019-09-28 21:36:37'),
	('김담당자', 'kim', '2', '상담부', '010-5555-5555', 5, 'y', '2019-09-28 21:43:53'),
	('김상담', 'sang1', '1', '상담부', '010-1111-1111', 1, 'y', '2019-09-28 21:37:25');
/*!40000 ALTER TABLE `management` ENABLE KEYS */;

-- 테이블 bank.membersheep 구조 내보내기
DROP TABLE IF EXISTS `membersheep`;
CREATE TABLE IF NOT EXISTS `membersheep` (
  `id` varchar(50) NOT NULL COMMENT '사용자 id',
  `membersheep` varchar(50) NOT NULL COMMENT '멤버쉽등급',
  `point` int(11) NOT NULL COMMENT '누적포인트',
  `status` varchar(10) NOT NULL COMMENT '멤버쉽상태 활성/비활성',
  `register_date` date NOT NULL COMMENT '멤버쉽등록일',
  PRIMARY KEY (`id`),
  KEY `membersheep` (`membersheep`),
  CONSTRAINT `FK_membersheep_membersheep_rating` FOREIGN KEY (`membersheep`) REFERENCES `membersheep_rating` (`name`),
  CONSTRAINT `fk_membersheep_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='멤버쉽 사용자';

-- 테이블 데이터 bank.membersheep:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `membersheep` DISABLE KEYS */;
/*!40000 ALTER TABLE `membersheep` ENABLE KEYS */;

-- 테이블 bank.membersheep_log 구조 내보내기
DROP TABLE IF EXISTS `membersheep_log`;
CREATE TABLE IF NOT EXISTS `membersheep_log` (
  `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '로그 순서',
  `id` varchar(50) NOT NULL COMMENT '사용자 id',
  `point` int(11) NOT NULL COMMENT '적립포인트',
  `way` varchar(50) NOT NULL COMMENT '적립타입',
  `status` varchar(50) NOT NULL COMMENT '로그 유효성',
  `register_date` datetime NOT NULL COMMENT '적립일',
  PRIMARY KEY (`seq`),
  KEY `fk_membersheeplog_membersheep` (`id`),
  KEY `status` (`status`),
  CONSTRAINT `fk_membersheeplog_membersheep` FOREIGN KEY (`id`) REFERENCES `membersheep` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='멤버쉽 포인트에 관한 로그';

-- 테이블 데이터 bank.membersheep_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `membersheep_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `membersheep_log` ENABLE KEYS */;

-- 테이블 bank.membersheep_rating 구조 내보내기
DROP TABLE IF EXISTS `membersheep_rating`;
CREATE TABLE IF NOT EXISTS `membersheep_rating` (
  `name` varchar(50) NOT NULL COMMENT '멤버쉽 등급이름',
  `condition` int(11) NOT NULL COMMENT '등급 조건 포인트',
  `status` varchar(50) NOT NULL COMMENT '사용여부',
  `register_date` datetime NOT NULL COMMENT '등록일',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='멤버쉽 등급 테이블';

-- 테이블 데이터 bank.membersheep_rating:~5 rows (대략적) 내보내기
/*!40000 ALTER TABLE `membersheep_rating` DISABLE KEYS */;
INSERT INTO `membersheep_rating` (`name`, `condition`, `status`, `register_date`) VALUES
	('Ace', 2000, 'y', '2019-09-26 11:02:42'),
	('Classic', 1000, 'y', '2019-09-26 11:00:25'),
	('Femily', 500, 'y', '2019-09-26 10:59:53'),
	('VIP', 10000, 'y', '2019-09-26 11:03:08'),
	('Welcome', 0, 'y', '2019-09-26 10:57:35');
/*!40000 ALTER TABLE `membersheep_rating` ENABLE KEYS */;

-- 테이블 bank.menu 구조 내보내기
DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu` (
  `type` varchar(50) NOT NULL COMMENT '메뉴 분류',
  `name` varchar(50) NOT NULL COMMENT '메뉴명',
  `kor_name` varchar(100) NOT NULL COMMENT '메뉴한국어명',
  `prnts_name` varchar(50) NOT NULL COMMENT '상위메뉴명',
  `status` varchar(50) NOT NULL,
  `depth` int(11) NOT NULL COMMENT '메뉴 깊이',
  `sort` int(11) NOT NULL COMMENT '정렬 순서',
  PRIMARY KEY (`name`,`prnts_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='메뉴';

-- 테이블 데이터 bank.menu:~50 rows (대략적) 내보내기
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`type`, `name`, `kor_name`, `prnts_name`, `status`, `depth`, `sort`) VALUES
	('security', 'accaign', '신입금계좌지정서비스', 'security', 'y', 1, 1),
	('management', 'acccs', '평생계좌전환서비스', 'accmanag', 'y', 2, 2),
	('management', 'accmanag', '계좌관리', 'management', 'y', 1, 2),
	('security', 'apply', '입금계좌관리', 'accaign', 'y', 2, 1),
	('banking', 'auto', '자동이체 조회', 'check', 'y', 2, 1),
	('banking', 'auto', '자동이체', 'transfer', 'y', 2, 1),
	('banking', 'banking', '조회 · 이체', '', 'y', 0, 0),
	('management', 'bbcc', '통장메모내용변경', 'accmanag', 'y', 2, 3),
	('security', 'blockalarm', 'IP차단 · 알림서비스', 'security', 'y', 1, 3),
	('banking', 'check', '조회', 'banking', 'y', 1, 0),
	('security', 'check', 'OTP관리', 'otp', 'y', 2, 1),
	('management', 'check', '고객정보조회', 'user', 'y', 2, 0),
	('service', 'client', '고객상담', 'service', 'y', 1, 0),
	('banking', 'delay', '지연이체 조회 · 취소', 'check', 'y', 2, 2),
	('banking', 'delay', '지연이체', 'transfer', 'y', 2, 2),
	('security', 'delaytrs', '지연이체서비스', 'security', 'y', 1, 2),
	('management', 'deposit', '자주쓰는입금계좌관리', 'accmanag', 'y', 2, 1),
	('product', 'deposit', '예금 · 적금', 'product', 'y', 1, 0),
	('management', 'edit', 'My메뉴관리', 'intbank', 'y', 2, 0),
	('service', 'faq', '자주하는 질문', 'client', 'y', 2, 1),
	('product', 'fund', '펀드', 'product', 'y', 1, 2),
	('product', 'guide', '상품안내', 'deposit', 'y', 2, 0),
	('product', 'guide', '상품안내', 'fund', 'y', 2, 0),
	('product', 'guide', '상품안내', 'isa', 'y', 2, 0),
	('product', 'guide', '상품안내', 'loan', 'y', 2, 0),
	('management', 'hidden', '계좌감추기 서비스', 'accounty', 'y', 2, 0),
	('security', 'info', '서비스안내', 'accaign', 'y', 2, 0),
	('security', 'info', '서비스안내', 'delaytrs', 'y', 2, 0),
	('management', 'info', '개인(신용)정보수집<br>이용제공동의', 'user', 'y', 2, 1),
	('management', 'intbank', '뱅킹관리', 'management', 'y', 1, 1),
	('security', 'ipblock', 'IP차단 서비스안내', 'blockalarm', 'y', 2, 0),
	('product', 'isa', 'ISA', 'product', 'y', 1, 3),
	('management', 'limitred', '이체한도관리', 'intbank', 'y', 2, 2),
	('product', 'loan', '대출', 'product', 'y', 1, 1),
	('management', 'management', '사용자관리', ' ', 'y', 0, 3),
	('security', 'management', 'IP차단 관리', 'blockalarm', 'y', 2, 1),
	('security', 'modify', '지연이체 계좌관리', 'delaytrs', 'y', 2, 1),
	('service', 'notice', '공지사항', 'client', 'y', 2, 0),
	('security', 'otp', 'OTP', 'security', 'y', 1, 0),
	('product', 'product', '금융상품', '', 'y', 0, 1),
	('service', 'qna', 'Q & A', 'client', 'y', 2, 1),
	('security', 'register', 'OTP안내', 'otp', 'y', 2, 0),
	('banking', 'reservation', '예약이체 조회', 'check', 'y', 2, 0),
	('banking', 'reservation', '예약이체', 'transfer', 'y', 2, 0),
	('security', 'security', '보안관리', '', 'y', 0, 2),
	('service', 'service', '고객센터', '', 'y', 0, 4),
	('banking', 'transfer', '이체', 'banking', 'y', 1, 1),
	('management', 'unuserel', '장기미사용정지 해제', 'intbank', 'y', 2, 1),
	('management', 'user', '고객정보', 'management', 'y', 1, 0),
	('management', 'withdacc', '출금계좌관리', 'accmanag', 'y', 2, 0);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;

-- 테이블 bank.nation 구조 내보내기
DROP TABLE IF EXISTS `nation`;
CREATE TABLE IF NOT EXISTS `nation` (
  `code` varchar(50) NOT NULL DEFAULT '',
  `name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='국가코드';

-- 테이블 데이터 bank.nation:~246 rows (대략적) 내보내기
/*!40000 ALTER TABLE `nation` DISABLE KEYS */;
INSERT INTO `nation` (`code`, `name`) VALUES
	('AD\r', '안도라'),
	('AE\r', '아랍에미리트'),
	('AF\r', '아프가니스탄'),
	('AG\r', '앤티가 바부다'),
	('AI\r', '앵귈라'),
	('AL\r', '알바니아'),
	('AM\r', '아르메니아'),
	('AN\r', '네덜란드령 안틸레스'),
	('AO\r', '앙골라'),
	('AQ\r', '남극'),
	('AR\r', '아르헨티나'),
	('AS\r', '아메리칸사모아'),
	('AT\r', '오스트리아'),
	('AU\r', '오스트레일리아'),
	('AW\r', '아루바'),
	('AX\r', '올란드 제도'),
	('AZ\r', '아제르바이잔'),
	('BA\r', '보스니아 헤르체고비나'),
	('BB\r', '바베이도스'),
	('BD\r', '방글라데시'),
	('BE\r', '벨기에'),
	('BF\r', '부르키나파소'),
	('BG\r', '불가리아'),
	('BH\r', '바레인'),
	('BI\r', '부룬디'),
	('BJ\r', '베냉'),
	('BM\r', '버뮤다'),
	('BN\r', '브루나이'),
	('BO\r', '볼리비아'),
	('BR\r', '브라질'),
	('BS\r', '바하마'),
	('BT\r', '부탄'),
	('BV\r', '부베섬'),
	('BW\r', '보츠와나'),
	('BY\r', '벨라루스'),
	('BZ\r', '벨리즈'),
	('CA\r', '캐나다'),
	('CC\r', '코코스 제도'),
	('CD\r', '콩고 민주 공화국'),
	('CF\r', '중앙아프리카 공화국'),
	('CG\r', '콩고 공화국'),
	('CH\r', '스위스'),
	('CI\r', '코트디부아르'),
	('CK\r', '쿡 제도'),
	('CL\r', '칠레'),
	('CM\r', '카메룬'),
	('CN\r', '중국'),
	('CO\r', '콜롬비아'),
	('CR\r', '코스타리카'),
	('CU\r', '쿠바'),
	('CV\r', '카보베르데'),
	('CX\r', '크리스마스 섬'),
	('CY\r', '키프로스'),
	('CZ\r', '체코'),
	('DE\r', '독일'),
	('DJ\r', '지부티'),
	('DK\r', '덴마크'),
	('DM\r', '도미니카 연방'),
	('DO\r', '도미니카 공화국'),
	('DZ\r', '알제리'),
	('EC\r', '에콰도르'),
	('EE\r', '에스토니아'),
	('EG\r', '이집트'),
	('EH\r', '서사하라'),
	('ER\r', '에리트레아'),
	('ES\r', '스페인'),
	('ET\r', '에티오피아'),
	('FI\r', '핀란드'),
	('FJ\r', '피지'),
	('FK\r', '포클랜드 제도'),
	('FM\r', '미크로네시아 연방'),
	('FO\r', '페로 제도'),
	('FR\r', '프랑스'),
	('GA\r', '가봉'),
	('GB\r', '영국'),
	('GD\r', '그레나다'),
	('GE\r', '조지아 (국가)'),
	('GF\r', '프랑스령 기아나'),
	('GG\r', '건지섬'),
	('GH\r', '﻿가나'),
	('GI\r', '지브롤터'),
	('GL\r', '그린란드'),
	('GM\r', '감비아'),
	('GN\r', '기니'),
	('GP\r', '과들루프'),
	('GQ\r', '적도 기니'),
	('GR\r', '그리스'),
	('GS\r', '사우스조지아 사우스샌드위치 제도'),
	('GT\r', '과테말라'),
	('GU\r', '괌'),
	('GW\r', '기니비사우'),
	('GY\r', '가이아나'),
	('HK\r', '홍콩'),
	('HM\r', '허드 맥도널드 제도'),
	('HN\r', '온두라스'),
	('HR\r', '크로아티아'),
	('HT\r', '아이티'),
	('HU\r', '헝가리'),
	('ID\r', '인도네시아'),
	('IE\r', '아일랜드'),
	('IL\r', '이스라엘'),
	('IM\r', '맨섬'),
	('IN\r', '인도'),
	('IO\r', '영국령 인도양 지역'),
	('IQ\r', '이라크'),
	('IR\r', '이란'),
	('IS\r', '아이슬란드'),
	('IT\r', '이탈리아'),
	('JE\r', '저지섬'),
	('JM\r', '자메이카'),
	('JO\r', '요르단'),
	('JP\r', '일본'),
	('KE\r', '케냐'),
	('KG\r', '키르기스스탄'),
	('KH\r', '캄보디아'),
	('KI\r', '키리바시'),
	('KM\r', '코모로'),
	('KN\r', '세인트키츠 네비스'),
	('KP\r', '조선민주주'),
	('KR\r', '대한민국'),
	('KW\r', '쿠웨이트'),
	('KY\r', '케이맨 제도'),
	('KZ\r', '카자흐스탄'),
	('LA\r', '라오스'),
	('LB\r', '레바논'),
	('LC\r', '세인트루시아'),
	('LI\r', '리히텐슈타인'),
	('LK\r', '스리랑카'),
	('LR\r', '라이베리아'),
	('LS\r', '레소토'),
	('LT\r', '리투아니아'),
	('LU\r', '룩셈부르크'),
	('LV\r', '라트비아'),
	('LY\r', '리비아'),
	('MA\r', '모로코'),
	('MC\r', '모나코'),
	('MD\r', '몰도바'),
	('ME\r', '몬테네그로'),
	('MG\r', '마다가스카르'),
	('MH\r', '마셜 제도'),
	('MK\r', '북마케도니아'),
	('ML\r', '말리'),
	('MM\r', '미얀마'),
	('MN\r', '몽골'),
	('MO\r', '마카오'),
	('MP\r', '북마리아나 제도'),
	('MQ\r', '마르티니크'),
	('MR\r', '모리타니'),
	('MS\r', '몬트세랫'),
	('MT\r', '몰타'),
	('MU\r', '모리셔스'),
	('MV\r', '몰디브'),
	('MW\r', '말라위'),
	('MX\r', '멕시코'),
	('MY\r', '말레이시아'),
	('MZ\r', '모잠비크'),
	('NA\r', '나미비아'),
	('NC\r', '누벨칼레도니'),
	('NE\r', '니제르'),
	('NF\r', '노퍽섬'),
	('NG\r', '나이지리아'),
	('NI\r', '니카라과'),
	('NL\r', '네덜란드'),
	('NO\r', '노르웨이'),
	('NP\r', '네팔'),
	('NR\r', '나우루'),
	('NU\r', '니우에'),
	('NZ\r', '뉴질랜드'),
	('OM\r', '오만'),
	('PA\r', '파나마'),
	('PE\r', '페루'),
	('PF\r', '프랑스령 폴리네시아'),
	('PG\r', '파푸아뉴기니'),
	('PH\r', '필리핀'),
	('PK\r', '파키스탄'),
	('PL\r', '폴란드'),
	('PM\r', '생피에르 미클롱'),
	('PN\r', '핏케언 제도'),
	('PR\r', '푸에르토리코'),
	('PS\r', '팔레스타인'),
	('PT\r', '포르투갈'),
	('PW\r', '팔라우'),
	('PY\r', '파라과이'),
	('QA\r', '카타르'),
	('RE\r', '레위니옹'),
	('RO\r', '루마니아'),
	('RS\r', '세르비아'),
	('RU\r', '러시아'),
	('RW\r', '르완다'),
	('SA\r', '사우디아라비아'),
	('SB\r', '솔로몬 제도'),
	('SC\r', '세이셸'),
	('SD\r', '수단'),
	('SE\r', '스웨덴'),
	('SG\r', '싱가포르'),
	('SH\r', '세인트헬레나'),
	('SI\r', '슬로베니아'),
	('SJ\r', '스발바르 얀마옌 제도'),
	('SK\r', '슬로바키아'),
	('SL\r', '시에라리온'),
	('SM\r', '산마리노'),
	('SN\r', '세네갈'),
	('SO\r', '소말리아'),
	('SR\r', '수리남'),
	('SS\r', '남수단'),
	('ST\r', '상투메 프린시페'),
	('SV\r', '엘살바도르'),
	('SX\r', '신트마르턴'),
	('SY\r', '시리아'),
	('SZ\r', '에스와티니'),
	('TC\r', '터크스 케이커스 제도'),
	('TD\r', '차드'),
	('TF\r', '프랑스령 남방 및 남극'),
	('TG\r', '토고'),
	('TH\r', '태국'),
	('TJ\r', '타지키스탄'),
	('TK\r', '토켈라우'),
	('TL\r', '동티모르'),
	('TM\r', '투르크메니스탄'),
	('TN\r', '튀니지'),
	('TO\r', '통가'),
	('TR\r', '터키'),
	('TT\r', '트리니다드 토바고'),
	('TV\r', '투발루'),
	('TW\r', '타이완'),
	('TZ\r', '탄자니아'),
	('UA\r', '우크라이나'),
	('UG\r', '우간다'),
	('UM\r', '미국령 군소 제도'),
	('US\r', '미국'),
	('UY\r', '우루과이'),
	('UZ\r', '우즈베키스탄'),
	('VA\r', '바티칸 시국'),
	('VC\r', '세인트빈센트 그레나딘'),
	('VE\r', '베네수엘라'),
	('VG\r', '영국령 버진아일랜드'),
	('VI\r', '미국령 버진아일랜드'),
	('VN\r', '베트남'),
	('VU\r', '바누아투'),
	('WF\r', '왈리스 퓌튀나'),
	('WS\r', '사모아'),
	('YE\r', '예멘'),
	('YT\r', '마요트'),
	('ZA\r', '남아프리카 공화국'),
	('ZM\r', '잠비아'),
	('ZW\r', '짐바브웨');
/*!40000 ALTER TABLE `nation` ENABLE KEYS */;

-- 테이블 bank.notice 구조 내보내기
DROP TABLE IF EXISTS `notice`;
CREATE TABLE IF NOT EXISTS `notice` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(50) NOT NULL,
  `title` varchar(50) NOT NULL COMMENT '공지사항 제목',
  `content` varchar(50) NOT NULL COMMENT '공지사항 내용',
  `status` varchar(50) NOT NULL COMMENT '상태 비활성 시 안보임',
  `register_date` datetime NOT NULL COMMENT '등록일',
  PRIMARY KEY (`seq`),
  KEY `id` (`id`),
  CONSTRAINT `FK_notice_management` FOREIGN KEY (`id`) REFERENCES `management` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='공지사항';

-- 테이블 데이터 bank.notice:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;

-- 테이블 bank.otp 구조 내보내기
DROP TABLE IF EXISTS `otp`;
CREATE TABLE IF NOT EXISTS `otp` (
  `serial` varchar(50) NOT NULL COMMENT '시리얼 번호',
  `id` varchar(50) NOT NULL COMMENT '사용자 id',
  `type` varchar(50) NOT NULL COMMENT '장치타입',
  `status` varchar(50) NOT NULL COMMENT '사용여부',
  `register_date` datetime NOT NULL COMMENT '등록일',
  `end_date` datetime NOT NULL COMMENT '제거일',
  PRIMARY KEY (`serial`),
  KEY `id` (`id`),
  KEY `status` (`status`),
  CONSTRAINT `fk_security_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자 보안매체 OTP';

-- 테이블 데이터 bank.otp:~14 rows (대략적) 내보내기
/*!40000 ALTER TABLE `otp` DISABLE KEYS */;
INSERT INTO `otp` (`serial`, `id`, `type`, `status`, `register_date`, `end_date`) VALUES
	('122-55-3282', 'elliottjo', '인터넷OTP', '비활성', '2019-10-01 01:30:59', '2020-10-01 01:30:59'),
	('123-1234-1234', 'elliottjo', '휴대폰OTP', '비활성', '2019-09-30 15:45:57', '2022-02-22 00:00:00'),
	('127-7235-2525', 'elliottjo', '인터넷OTP', '비활성', '2019-10-02 17:47:39', '2020-10-02 17:47:39'),
	('130-1473-692', 'elliottjo', '인터넷OTP', '비활성', '2019-10-01 01:27:38', '2020-10-01 01:27:38'),
	('175-2874-3261', 'elliottjo', '인터넷OTP', '비활성', '2019-10-01 14:56:00', '2020-10-01 14:56:00'),
	('193-8698-586', 'elliottjo', '인터넷OTP', '비활성', '2019-10-02 10:32:04', '2020-10-02 10:32:04'),
	('337-4031-9711', 'elliottjo', '인터넷OTP', '비활성', '2019-10-01 00:45:03', '2020-10-01 00:45:03'),
	('401-7197-8001', 'elliottjo', '인터넷OTP', '비활성', '2019-10-01 00:59:49', '2020-10-01 00:59:49'),
	('413-1677-1439', 'aaa', '인터넷OTP', '비활성', '2019-10-01 00:50:11', '2020-10-01 00:50:11'),
	('693-1209-8161', 'aaa', '인터넷OTP', '비활성', '2019-10-01 00:51:10', '2020-10-01 00:51:10'),
	('739-7069-9812', 'elliottjo', '인터넷OTP', '비활성', '2019-10-01 00:57:57', '2020-10-01 00:57:57'),
	('798-7007-410', 'elliottjo', '인터넷OTP', '비활성', '2019-10-01 01:02:19', '2020-10-01 01:02:19'),
	('865-4310-5088', 'elliottjo', '인터넷OTP', '비활성', '2019-10-01 14:56:00', '2020-10-01 14:56:00'),
	('914-9525-1191', 'elliottjo', '인터넷OTP', '비활성', '2019-10-02 10:32:04', '2020-10-02 10:32:04'),
	('970-3643-4552', 'elliottjo', '인터넷OTP', '비활성', '2019-10-01 14:56:00', '2020-10-01 14:56:00');
/*!40000 ALTER TABLE `otp` ENABLE KEYS */;

-- 테이블 bank.otp_log 구조 내보내기
DROP TABLE IF EXISTS `otp_log`;
CREATE TABLE IF NOT EXISTS `otp_log` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `serial` varchar(50) NOT NULL,
  `id` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `register_date` datetime NOT NULL,
  PRIMARY KEY (`seq`),
  KEY `serial` (`serial`),
  KEY `id` (`id`),
  CONSTRAINT `FK_otp_log_otp` FOREIGN KEY (`serial`) REFERENCES `otp` (`serial`),
  CONSTRAINT `FK_otp_log_otp_2` FOREIGN KEY (`id`) REFERENCES `otp` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='otp 로그 용도로 만들려했음 ';

-- 테이블 데이터 bank.otp_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `otp_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `otp_log` ENABLE KEYS */;

-- 테이블 bank.qna 구조 내보내기
DROP TABLE IF EXISTS `qna`;
CREATE TABLE IF NOT EXISTS `qna` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL COMMENT '문의 타입',
  `title` varchar(50) NOT NULL COMMENT '문의 제목',
  `content` varchar(50) NOT NULL COMMENT '문의 내용',
  `email` varchar(50) NOT NULL COMMENT '문의 받을 이메일',
  `status` varchar(50) NOT NULL COMMENT '답변여부',
  `reply` int(11) DEFAULT NULL COMMENT '답변 seq',
  `file` varchar(50) NOT NULL COMMENT '파일',
  `register_date` datetime NOT NULL COMMENT '등록일',
  PRIMARY KEY (`seq`),
  KEY `reply` (`reply`),
  CONSTRAINT `FK_qna_qna_reply` FOREIGN KEY (`reply`) REFERENCES `qna_reply` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='고객문의 테이블';

-- 테이블 데이터 bank.qna:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `qna` DISABLE KEYS */;
/*!40000 ALTER TABLE `qna` ENABLE KEYS */;

-- 테이블 bank.qna_reply 구조 내보내기
DROP TABLE IF EXISTS `qna_reply`;
CREATE TABLE IF NOT EXISTS `qna_reply` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(50) NOT NULL COMMENT '상담사 ID',
  `title` varchar(50) NOT NULL COMMENT '제목',
  `content` varchar(50) NOT NULL COMMENT '내용',
  `register_date` datetime NOT NULL COMMENT '등록일시',
  PRIMARY KEY (`seq`),
  KEY `id` (`id`),
  CONSTRAINT `FK_qna_reply_management` FOREIGN KEY (`id`) REFERENCES `management` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='qna 답변 이력';

-- 테이블 데이터 bank.qna_reply:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `qna_reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `qna_reply` ENABLE KEYS */;

-- 테이블 bank.saving 구조 내보내기
DROP TABLE IF EXISTS `saving`;
CREATE TABLE IF NOT EXISTS `saving` (
  `account_number` varchar(50) NOT NULL COMMENT '계좌번호',
  `id` varchar(50) NOT NULL COMMENT '사용자 id',
  `product` varchar(50) NOT NULL COMMENT '상품명',
  `preferential` varchar(50) NOT NULL COMMENT '적용된 우대조건',
  `interest` float NOT NULL COMMENT '적용이자',
  `type` varchar(50) NOT NULL COMMENT '적금 납입방식',
  `end_date` datetime NOT NULL COMMENT '만기일',
  PRIMARY KEY (`account_number`),
  KEY `id` (`id`),
  KEY `fk_savings_savingsinfo` (`product`),
  CONSTRAINT `fk_savings_account` FOREIGN KEY (`account_number`) REFERENCES `account` (`account_number`),
  CONSTRAINT `fk_savings_account_2` FOREIGN KEY (`id`) REFERENCES `account` (`id`),
  CONSTRAINT `fk_savings_savingsinfo` FOREIGN KEY (`product`) REFERENCES `saving_info` (`product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='적금 가입자';

-- 테이블 데이터 bank.saving:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `saving` DISABLE KEYS */;
/*!40000 ALTER TABLE `saving` ENABLE KEYS */;

-- 테이블 bank.saving_info 구조 내보내기
DROP TABLE IF EXISTS `saving_info`;
CREATE TABLE IF NOT EXISTS `saving_info` (
  `product` varchar(50) NOT NULL COMMENT '상품명',
  `product_info` varchar(50) NOT NULL COMMENT '상품설명',
  `min_interest` float NOT NULL COMMENT '최저 연이자',
  `max_interest` float NOT NULL COMMENT '최고 연이자',
  `month` int(11) NOT NULL COMMENT '만기 개월',
  `type` varchar(10) NOT NULL COMMENT '가입타입 ex 인터넷, 모바일',
  `interest_type` varchar(10) NOT NULL COMMENT '이자지급방식',
  `tax` varchar(50) NOT NULL COMMENT '과세여부',
  `preferential` varchar(200) NOT NULL COMMENT '우대구분 ',
  `prf_content` varchar(200) NOT NULL COMMENT '우대조건 내용',
  `prf_interest` varchar(200) NOT NULL COMMENT '우대이자율',
  `min_sum` int(11) NOT NULL COMMENT '최소금액',
  `max_sum` int(11) NOT NULL COMMENT '월납입 최대금액',
  `partialization` varchar(10) NOT NULL COMMENT '일부해지가능여부',
  `retention` varchar(10) NOT NULL COMMENT '재예치가능여부',
  `status` varchar(10) NOT NULL COMMENT '상태',
  `register_date` datetime NOT NULL COMMENT '상품등록일',
  `end_date` datetime DEFAULT NULL COMMENT '상품삭제일',
  PRIMARY KEY (`product`),
  KEY `type` (`type`),
  CONSTRAINT `FK_saving_info_saving_type` FOREIGN KEY (`type`) REFERENCES `saving_type` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='적금 상품 정보';

-- 테이블 데이터 bank.saving_info:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `saving_info` DISABLE KEYS */;
INSERT INTO `saving_info` (`product`, `product_info`, `min_interest`, `max_interest`, `month`, `type`, `interest_type`, `tax`, `preferential`, `prf_content`, `prf_interest`, `min_sum`, `max_sum`, `partialization`, `retention`, `status`, `register_date`, `end_date`) VALUES
	('SJ스마트적금', '', 2, 3, 12, '자유', '만기일시지급-단리식', '비과세종합저축가능', '프로그래머#java기술자', '프로그래머들#java가능', '0.3#0.7', 0, 1000000, '불가', '불가', '활성', '2019-10-01 12:46:38', NULL);
/*!40000 ALTER TABLE `saving_info` ENABLE KEYS */;

-- 테이블 bank.saving_log 구조 내보내기
DROP TABLE IF EXISTS `saving_log`;
CREATE TABLE IF NOT EXISTS `saving_log` (
  `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '로그번호',
  `account_number` varchar(50) NOT NULL COMMENT '계좌',
  `interest` float NOT NULL COMMENT '이자',
  `sum` int(11) NOT NULL COMMENT '이자금액',
  `status` varchar(50) NOT NULL COMMENT '성공/실패(사유)',
  `register_date` datetime NOT NULL COMMENT '날짜',
  PRIMARY KEY (`seq`),
  KEY `account_number` (`account_number`),
  KEY `status` (`status`),
  CONSTRAINT `fk_savings_log_account` FOREIGN KEY (`account_number`) REFERENCES `saving` (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='월별 적금계좌 이자 이력';

-- 테이블 데이터 bank.saving_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `saving_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `saving_log` ENABLE KEYS */;

-- 테이블 bank.saving_type 구조 내보내기
DROP TABLE IF EXISTS `saving_type`;
CREATE TABLE IF NOT EXISTS `saving_type` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='적금 타입 검증용';

-- 테이블 데이터 bank.saving_type:~3 rows (대략적) 내보내기
/*!40000 ALTER TABLE `saving_type` DISABLE KEYS */;
INSERT INTO `saving_type` (`name`) VALUES
	('자유'),
	('정기'),
	('정기/자유');
/*!40000 ALTER TABLE `saving_type` ENABLE KEYS */;

-- 테이블 bank.transfer_auto 구조 내보내기
DROP TABLE IF EXISTS `transfer_auto`;
CREATE TABLE IF NOT EXISTS `transfer_auto` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `account_number` varchar(50) NOT NULL,
  `to_account_number` varchar(50) NOT NULL COMMENT '상대계좌',
  `sum` int(11) NOT NULL,
  `period` varchar(50) NOT NULL COMMENT '이체주기',
  `start_date` date NOT NULL COMMENT '이체시작일',
  `finish_date` date NOT NULL COMMENT '이체종료일',
  `last_day` varchar(50) NOT NULL COMMENT '말일이체여부',
  `memo` varchar(50) NOT NULL,
  `to_memo` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL COMMENT '사용/사용안함',
  `register_date` datetime NOT NULL COMMENT '등록일',
  `end_date` datetime NOT NULL COMMENT '삭제일',
  PRIMARY KEY (`seq`),
  KEY `account_number` (`account_number`),
  KEY `status` (`status`),
  CONSTRAINT `fk_auto_transfer_account` FOREIGN KEY (`account_number`) REFERENCES `account` (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='자동이체 등록 정보';

-- 테이블 데이터 bank.transfer_auto:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `transfer_auto` DISABLE KEYS */;
/*!40000 ALTER TABLE `transfer_auto` ENABLE KEYS */;

-- 테이블 bank.transfer_log 구조 내보내기
DROP TABLE IF EXISTS `transfer_log`;
CREATE TABLE IF NOT EXISTS `transfer_log` (
  `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '로그 번호',
  `account_number` varchar(50) NOT NULL COMMENT '보내는 계좌',
  `self` varchar(50) NOT NULL DEFAULT 'y' COMMENT '본인계좌여부',
  `target` varchar(50) NOT NULL COMMENT '은행/증권사명',
  `to_account_number` varchar(50) NOT NULL COMMENT '받는 계좌',
  `received` varchar(50) NOT NULL COMMENT '받는이',
  `sum` bigint(20) NOT NULL COMMENT '금액',
  `fee` int(11) DEFAULT NULL COMMENT '수수료',
  `cms` varchar(50) DEFAULT NULL COMMENT 'cms코드',
  `memo` varchar(50) NOT NULL COMMENT '내 통장메모',
  `to_memo` varchar(50) DEFAULT NULL COMMENT '받는 통장메모',
  `status` varchar(50) DEFAULT NULL COMMENT '성공/실패(사유)',
  `register_date` datetime DEFAULT NULL COMMENT '거래일시',
  PRIMARY KEY (`seq`),
  KEY `accountnumber` (`account_number`),
  KEY `id` (`self`),
  KEY `status` (`status`),
  CONSTRAINT `fk_accountlog_account` FOREIGN KEY (`account_number`) REFERENCES `account` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='계좌 이체 이력';

-- 테이블 데이터 bank.transfer_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `transfer_log` DISABLE KEYS */;
INSERT INTO `transfer_log` (`seq`, `account_number`, `self`, `target`, `to_account_number`, `received`, `sum`, `fee`, `cms`, `memo`, `to_memo`, `status`, `register_date`) VALUES
	(1, '010-1111-112-1212', 'y', '하나은행', '111-1234-5678', '갓이찬', 1234560, NULL, NULL, '팀플금액청구', NULL, NULL, NULL);
/*!40000 ALTER TABLE `transfer_log` ENABLE KEYS */;

-- 테이블 bank.transfer_reserve 구조 내보내기
DROP TABLE IF EXISTS `transfer_reserve`;
CREATE TABLE IF NOT EXISTS `transfer_reserve` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `account_number` varchar(50) NOT NULL COMMENT '계좌',
  `to_account_number` varchar(50) NOT NULL COMMENT '받는 계좌',
  `sum` varchar(50) NOT NULL COMMENT '이체 금액',
  `time` varchar(50) NOT NULL COMMENT '이체시간',
  `memo` varchar(50) NOT NULL COMMENT '메모',
  `to_memo` varchar(50) NOT NULL COMMENT '받는 메모',
  `cms` varchar(50) NOT NULL COMMENT 'cms 코드 ',
  `status` varchar(50) NOT NULL COMMENT '사용/사용안함',
  `scheduled_date` varchar(50) NOT NULL COMMENT '이체 예정시간 n시~n+1시',
  `register_date` datetime NOT NULL COMMENT '예약이체 등록일',
  PRIMARY KEY (`seq`),
  KEY `account_number` (`account_number`),
  KEY `status` (`status`),
  CONSTRAINT `fk_reserve_transfer_account` FOREIGN KEY (`account_number`) REFERENCES `account` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='예약이체 등록 정보';

-- 테이블 데이터 bank.transfer_reserve:~1 rows (대략적) 내보내기
/*!40000 ALTER TABLE `transfer_reserve` DISABLE KEYS */;
INSERT INTO `transfer_reserve` (`seq`, `account_number`, `to_account_number`, `sum`, `time`, `memo`, `to_memo`, `cms`, `status`, `scheduled_date`, `register_date`) VALUES
	(1, '010-1111-112-1212', '101-1112-112-1232', '123456', '16:00', '아 이런', '내돈 내돈 돌려줘 ', '', '활성', '2019-11-15 16:00', '2019-10-03 00:02:24');
/*!40000 ALTER TABLE `transfer_reserve` ENABLE KEYS */;

-- 테이블 bank.user 구조 내보내기
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(50) NOT NULL COMMENT '사용자 id',
  `pw` varchar(50) NOT NULL COMMENT '사용자 pw',
  `simple_pw` int(11) NOT NULL COMMENT '간편비밀번호',
  `name` varchar(50) NOT NULL COMMENT '사용자 이름',
  `tel` varchar(15) NOT NULL COMMENT '전화번호',
  `gen` varchar(10) NOT NULL COMMENT '성별',
  `email` varchar(50) NOT NULL COMMENT '이메일',
  `job_group` varchar(50) NOT NULL COMMENT '직업군',
  `addr` varchar(50) NOT NULL COMMENT '거주지',
  `postal_code` int(11) NOT NULL COMMENT '우편번호',
  `status` varchar(10) NOT NULL COMMENT '계정 상태',
  `register_date` datetime NOT NULL COMMENT '등록일',
  `end_date` datetime DEFAULT NULL COMMENT '탈퇴일',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자 정보';

-- 테이블 데이터 bank.user:~8 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `pw`, `simple_pw`, `name`, `tel`, `gen`, `email`, `job_group`, `addr`, `postal_code`, `status`, `register_date`, `end_date`) VALUES
	('aaa', '1111', 1234, '귀태씨는왜아직안만들어져있지', '010-3830-6039', '남', 'asdfghjk@naver.com', '학생', '1535135', 13531513, '활성', '2019-09-30 15:03:47', NULL),
	('asas', '1111', 11111, '아아아', '010-2516-6038', '여', 'asdfghjk@naver.com', '주부', '1535135', 13531513, '활성', '2019-09-28 20:00:53', NULL),
	('day_0821', '1', 0, '박이찬', '010-####-#', '남', 'asasas@naver.com', '학생', '1212', 123, '활성', '2019-09-05 08:31:36', NULL),
	('elliottjo', '1234', 7777, '조은환', '010-####-#', '남자', 'TT@green.com', '프로그래머', '인천시', 0, '활성', '2019-09-18 00:56:13', NULL),
	('exception', '123456', 921028, '황동규', '010-####-#', '남', '111@naver.com', '학생', '경기도 안양시', 13531513, '활성', '2019-09-10 11:11:00', NULL),
	('fff', '123', 123, '박이찬', '123', '남', '123@naver.com', '학생', '123', 123, '활성', '2019-09-28 19:47:44', NULL),
	('ngt', '1111', 1111, '나귀태', '010-1111-2222', '남', 'ngfsd1@naver.com', '학생', '서울', 12356, '활성', '2019-09-30 15:27:05', NULL),
	('reck', '1111', 334256, 'Erica', '345-456-1222', '남', '123254@naver.com', '주부', '서울 강서구 발산로 24/', 7644, '활성', '2019-09-30 23:39:35', NULL),
	('yada0', '1234', 334256, '노현천', '345-456-1222', '여', 'asdas@naver.com', '학생', '서울 성북구 혜화로 80/레미안 2단지 1330호', 2880, '활성', '2019-09-30 23:40:54', NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- 테이블 bank.user_analysis_log 구조 내보내기
DROP TABLE IF EXISTS `user_analysis_log`;
CREATE TABLE IF NOT EXISTS `user_analysis_log` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL COMMENT '항목명 ,으로 구분 사용자한테는 보여주지 않는 컬럼',
  `score` varchar(50) NOT NULL COMMENT '항목당 점수 ,으로 구분',
  `total` float NOT NULL COMMENT '총점',
  `propensity` varchar(50) NOT NULL COMMENT '투자 성향',
  `register_date` datetime NOT NULL,
  PRIMARY KEY (`seq`),
  KEY `id` (`id`),
  KEY `propensity` (`propensity`),
  CONSTRAINT `FK_user_analysis_log_analysis` FOREIGN KEY (`propensity`) REFERENCES `analysis` (`propensity`),
  CONSTRAINT `FK_user_analysis_log_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자 투자성향 분석 이력';

-- 테이블 데이터 bank.user_analysis_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user_analysis_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_analysis_log` ENABLE KEYS */;

-- 테이블 bank.user_login_log 구조 내보내기
DROP TABLE IF EXISTS `user_login_log`;
CREATE TABLE IF NOT EXISTS `user_login_log` (
  `id` varchar(50) NOT NULL,
  `ip` varchar(50) NOT NULL COMMENT '접속IP',
  `register_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_user_login_history_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자 로그인 이력';

-- 테이블 데이터 bank.user_login_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user_login_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_login_log` ENABLE KEYS */;

-- 테이블 bank.user_pw_log 구조 내보내기
DROP TABLE IF EXISTS `user_pw_log`;
CREATE TABLE IF NOT EXISTS `user_pw_log` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(50) NOT NULL COMMENT '사용자id',
  `status` varchar(50) NOT NULL COMMENT '로그 유효성',
  `register_date` datetime NOT NULL COMMENT '등록일',
  PRIMARY KEY (`seq`),
  KEY `id` (`id`),
  KEY `status` (`status`),
  CONSTRAINT `fk__user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='유저 비밀번호 틀린횟수 테이블\r\n해당 테이블에 동일한 아이디이며 status가 y인 로그가 5개 있을경우 해당\r\nid는 user테이블에 상태를 비활성화함';

-- 테이블 데이터 bank.user_pw_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user_pw_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_pw_log` ENABLE KEYS */;

-- 테이블 bank.user_simple_pw_log 구조 내보내기
DROP TABLE IF EXISTS `user_simple_pw_log`;
CREATE TABLE IF NOT EXISTS `user_simple_pw_log` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL COMMENT '로그 유효성',
  `register_date` datetime NOT NULL,
  PRIMARY KEY (`seq`),
  KEY `id` (`id`),
  CONSTRAINT `fk_user_simple_pw_log_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='유저 간편 비밀번호 틀린횟수 테이블\r\n해당 테이블에 동일한 아이디이며 status가 y인 로그가 10개 있을경우 해당\r\nid는 user테이블에 상태를 비활성화함';

-- 테이블 데이터 bank.user_simple_pw_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user_simple_pw_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_simple_pw_log` ENABLE KEYS */;

-- 테이블 bank.user_status_log 구조 내보내기
DROP TABLE IF EXISTS `user_status_log`;
CREATE TABLE IF NOT EXISTS `user_status_log` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL COMMENT '변경된 상태',
  `comment` varchar(50) NOT NULL COMMENT '상태 변경사유',
  `register_date` datetime NOT NULL COMMENT '사용자 상태 금융거래제한 등록일시',
  PRIMARY KEY (`seq`),
  KEY `id` (`id`),
  CONSTRAINT `FK_restriction_log_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자 상태 변경 시 해당 테이블에 이력을 남긴다\r\n상태 변경 항목(금융거래제한/해지 시, 비활성화, 탈퇴 등)\r\nuser 테이블도 상태를 변경한다';

-- 테이블 데이터 bank.user_status_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user_status_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_status_log` ENABLE KEYS */;

-- 테이블 bank.user_transfer_limit 구조 내보내기
DROP TABLE IF EXISTS `user_transfer_limit`;
CREATE TABLE IF NOT EXISTS `user_transfer_limit` (
  `id` varchar(50) NOT NULL,
  `daily_limit` int(11) NOT NULL COMMENT '일일제한',
  `number_limit` int(11) NOT NULL COMMENT '횟수제한',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_transfer_limit_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자 이체 한도\r\n일일 제한, 일회 제한\r\n보통 일일제한 금액은 일회가능 금액에 5배가 기본';

-- 테이블 데이터 bank.user_transfer_limit:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user_transfer_limit` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_transfer_limit` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;