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
  CONSTRAINT `fk_account_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='계좌 정보';

-- 테이블 데이터 bank.account:~4 rows (대략적) 내보내기
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`account_number`, `type`, `sum`, `alias`, `id`, `pw`, `status`, `register_date`, `end_date`) VALUES
	('010-1111-1111-121', '기본', 75310, '미지정', 'day_0821', '0092', 'y', '2019-09-05 08:31:36', NULL),
	('010-1111-112-1212', '저축예금', 100000000, '월급', 'elliottjo', '1234', '활성', '2019-09-18 01:00:46', NULL),
	('010-1111-112-1213', '기본', 1000000, '학자금', 'day_0821', '1111', '활성', '2010-10-10 10:10:10', '2222-02-22 22:22:22'),
	('101-1112-112-1232', '펀드', 500000, '오일펀드', 'elliottjo', '1234', '활성', '2019-09-18 01:01:33', NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- 테이블 bank.account_pw_log 구조 내보내기
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

-- 테이블 bank.deposits 구조 내보내기
CREATE TABLE IF NOT EXISTS `deposits` (
  `account_number` varchar(50) NOT NULL COMMENT '계좌번호',
  `id` varchar(50) NOT NULL COMMENT '사용자 id',
  `product` varchar(50) NOT NULL COMMENT '상품명',
  `preferential` varchar(50) NOT NULL COMMENT '적용된 우대조건',
  `interest` float(4,2) NOT NULL COMMENT '적용이자',
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
CREATE TABLE IF NOT EXISTS `deposits_info` (
  `product` varchar(50) NOT NULL COMMENT '상품명',
  `min_interest` float(2,2) NOT NULL COMMENT '최저 연이자',
  `max_interest` float(2,2) NOT NULL COMMENT '최고 연이자',
  `month` int(11) NOT NULL COMMENT '만기 개월',
  `type` varchar(10) NOT NULL COMMENT '가입타입 ex 인터넷, 모바일',
  `regular` varchar(10) NOT NULL COMMENT '정기/비정기',
  `jnterest_type` varchar(10) NOT NULL COMMENT '이자지급방식',
  `tax` varchar(50) NOT NULL COMMENT '세금종류',
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
  PRIMARY KEY (`product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='예금 상품 정보';

-- 테이블 데이터 bank.deposits_info:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `deposits_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `deposits_info` ENABLE KEYS */;

-- 테이블 bank.deposits_log 구조 내보내기
CREATE TABLE IF NOT EXISTS `deposits_log` (
  `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '로그번호',
  `account_number` varchar(50) NOT NULL COMMENT '계좌',
  `interest` float(4,2) NOT NULL COMMENT '이자',
  `sum` int(11) NOT NULL COMMENT '이자금액',
  `status` varchar(50) NOT NULL COMMENT '성공/실패(사유)',
  `register_date` datetime NOT NULL COMMENT '날짜',
  PRIMARY KEY (`seq`),
  KEY `account_number` (`account_number`),
  KEY `status` (`status`),
  CONSTRAINT `fk_deposits_log_account` FOREIGN KEY (`account_number`) REFERENCES `deposits` (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='월별 예금 이자';

-- 테이블 데이터 bank.deposits_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `deposits_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `deposits_log` ENABLE KEYS */;

-- 테이블 bank.fund 구조 내보내기
CREATE TABLE IF NOT EXISTS `fund` (
  `account_number` varchar(50) NOT NULL COMMENT '계좌번호',
  `id` varchar(50) NOT NULL COMMENT 'id',
  `product` varchar(50) NOT NULL COMMENT '상품명',
  `fluctuation` float(4,2) NOT NULL COMMENT '등락률',
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
CREATE TABLE IF NOT EXISTS `fund_info` (
  `product` varchar(50) NOT NULL COMMENT '상품명',
  `price` float(2,2) NOT NULL COMMENT '초기기준가',
  `price_modify` float(5,2) NOT NULL COMMENT '수정기준가',
  `type` varchar(50) NOT NULL COMMENT '유형 주식, 채권 등등',
  `area` varchar(50) NOT NULL COMMENT '국내/해외',
  `property` varchar(50) NOT NULL COMMENT '상품속성ex 인터넷..',
  `first_fee` float(2,2) NOT NULL COMMENT '선취수수료',
  `fee` float(2,2) NOT NULL COMMENT '년보수',
  `management` varchar(50) NOT NULL COMMENT '운용사',
  `sector` varchar(50) NOT NULL COMMENT '섹터',
  `status` varchar(50) NOT NULL COMMENT '상태',
  `register_date` datetime NOT NULL COMMENT '펀드등록일',
  `end_date` datetime NOT NULL COMMENT '펀드폐지일',
  PRIMARY KEY (`product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='펀드 상품 정보';

-- 테이블 데이터 bank.fund_info:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `fund_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `fund_info` ENABLE KEYS */;

-- 테이블 bank.fund_log 구조 내보내기
CREATE TABLE IF NOT EXISTS `fund_log` (
  `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '로그번호',
  `account_number` varchar(50) NOT NULL COMMENT '계좌',
  `fluctuation` float(4,2) NOT NULL COMMENT '등락률',
  `sum` int(11) NOT NULL COMMENT '등락금액',
  `status` varchar(50) NOT NULL COMMENT '성공/실패(사유)',
  `register_date` datetime NOT NULL COMMENT '날짜',
  PRIMARY KEY (`seq`),
  KEY `account_number` (`account_number`),
  KEY `status` (`status`),
  CONSTRAINT `fk__fund` FOREIGN KEY (`account_number`) REFERENCES `fund` (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='일별 펀드 수익';

-- 테이블 데이터 bank.fund_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `fund_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `fund_log` ENABLE KEYS */;

-- 테이블 bank.membersheep 구조 내보내기
CREATE TABLE IF NOT EXISTS `membersheep` (
  `id` varchar(50) NOT NULL COMMENT '사용자 id',
  `membersheep` varchar(50) NOT NULL COMMENT '멤버쉽등급',
  `point` int(11) NOT NULL COMMENT '누적포인트',
  `status` varchar(10) NOT NULL COMMENT '멤버쉽상태 활성/비활성',
  `register_date` date NOT NULL COMMENT '멤버쉽등록일',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_membersheep_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='멤버쉽 사용자';

-- 테이블 데이터 bank.membersheep:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `membersheep` DISABLE KEYS */;
/*!40000 ALTER TABLE `membersheep` ENABLE KEYS */;

-- 테이블 bank.membersheep_log 구조 내보내기
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='멤버쉽 포인트에 관한 로그인데 흐릿해짐';

-- 테이블 데이터 bank.membersheep_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `membersheep_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `membersheep_log` ENABLE KEYS */;

-- 테이블 bank.menu 구조 내보내기
CREATE TABLE IF NOT EXISTS `menu` (
  `type` varchar(50) NOT NULL COMMENT '메뉴 분류',
  `name` varchar(50) NOT NULL COMMENT '메뉴명',
  `kor_name` varchar(100) NOT NULL COMMENT '메뉴한국어명',
  `prnts_name` varchar(50) NOT NULL COMMENT '상위메뉴명',
  `status` varchar(50) NOT NULL,
  `depth` int(11) NOT NULL COMMENT '메뉴 깊이',
  `sort` int(11) NOT NULL COMMENT '정렬 순서'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='메뉴';

-- 테이블 데이터 bank.menu:~129 rows (대략적) 내보내기
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`type`, `name`, `kor_name`, `prnts_name`, `status`, `depth`, `sort`) VALUES
	('transfer', 'check', '자동이체 조회/변경/취소', 'auto', '', 2, 1),
	('transfer', 'check', '자동이체결과 조회', 'auto', '', 2, 2),
	('transfer', 'register', '자동이체 등록', 'auto', '', 2, 0),
	('utlity', 'bauto', '지로자동이체신청/조회/해지', 'bill', '', 2, 3),
	('utlity', 'bill', '지로납부', 'utlity', '', 1, 1),
	('utlity', 'bpay', '지로요금납부', 'bill', '', 2, 0),
	('product', 'checkrn', '조회/입출금', 'deposit', '', 2, 0),
	('check', 'check', '조회', '', '', 0, 0),
	('product', 'check', '조회/입금/해지', 'fund', '', 2, 0),
	('product', 'check', '대출계좌조회', 'loan', '', 2, 0),
	('transfer', 'check', '예약이체 처리결과 조회', 'reservation', '', 2, 2),
	('check', 'acc', '계좌조회', 'check', '', 1, 0),
	('check', 'etc', '기타조회', 'check', '', 1, 1),
	('check', 'all', '전체계좌', 'acc', '', 2, 6),
	('check', 'deposit', '예금/신탁', 'acc', '', 2, 0),
	('check', 'fund', '펀드', 'acc', '', 2, 3),
	('check', 'insurance', '보험/노란우산', 'acc', '', 2, 5),
	('check', 'isa', 'isa', 'acc', '', 2, 1),
	('check', 'loan', '대출', 'acc', '', 2, 2),
	('check', 'pension', '퇴직연금', 'acc', '', 2, 4),
	('check', 'bill', '보관어음조회', 'etc', '', 2, 4),
	('check', 'cheque', '발행수표조회', 'etc', '', 2, 3),
	('check', 'close', '기일도래계좌조회', 'etc', '', 2, 1),
	('check', 'mycheque', '자기앞수표조회', 'etc', '', 2, 2),
	('check', 'sleep', '휴면예금조회', 'etc', '', 2, 0),
	('product', 'close', '해지', 'deposit', '', 2, 2),
	('product', 'deposit', '예금/신탁', 'product', '', 1, 0),
	('transfer', 'check', '지연이체등록 조회/취소', 'delay', '', 2, 1),
	('transfer', 'result', '지연이체 처리결과 조회', 'delay', '', 2, 2),
	('transfer', 'register', '당행/타행 지연이체 등록', 'delay', '', 2, 0),
	('utlity', 'eei', '전기요금', 'bill', '', 2, 2),
	('utlity', 'etc', '기타', 'utlity', '', 1, 4),
	('transfer', 'family', '경조금이체', 'etc', '', 2, 0),
	('transfer', 'kid', '키즈플러스이체', 'etc', '', 2, 2),
	('transfer', 'stock', '증권사이체', 'etc', '', 2, 1),
	('product', 'fund', '펀드', 'product', '', 1, 2),
	('product', 'guide', '상품안내', 'deposit', '', 2, 3),
	('utlity', 'imp', '환경개선부담금', 'lotax', '', 2, 1),
	('utlity', 'imtax', '세외수입', 'lotax', '', 2, 2),
	('product', 'loan', '대출', 'product', '', 1, 1),
	('utlity', 'lotax', '지방세/세외수입', 'utlity', '', 1, 2),
	('security', 'security', '뱅킹보안관리', '', '', 0, 4),
	('security', 'apply', '서비스신청', 'accaign', '', 2, 1),
	('security', 'assign', '이용기기등록', 'assign', '', 2, 0),
	('security', 'deny', '특정계좌조회금지서비스', 'account', '', 2, 2),
	('security', 'hidden', '계좌감추기 서비스', 'accounty', '', 2, 1),
	('security', 'info', '서비스안내', 'accaign', '', 2, 0),
	('security', 'release', '이용기기등록 해제', 'assign', '', 2, 2),
	('security', 'search', '등록기기조회/삭제', 'assign', '', 2, 1),
	('security', 'secure', '보안계좌 서비스', 'account', '', 2, 0),
	('security', 'excepip', '예외ip로그인알람서비스', 'blockalarm', '', 2, 1),
	('security', 'outalarm', '해외ip로그인알람서비스', 'blockalarm', '', 2, 2),
	('security', 'outip', '해외 ip차단 신청', 'blockalarm', '', 2, 0),
	('security', 'info', '서비스안내', 'delaytrs', '', 2, 0),
	('security', 'modify', '서비스신청/변경', 'delaytrs', '', 2, 1),
	('transfer', 'management', '급여이체 등록관리', 'salary', '', 2, 0),
	('security', 'accaign', '신입금계좌지정서비스', 'security', '', 1, 1),
	('security', 'account', '계좌보안관리', 'security', '', 1, 3),
	('security', 'assign', '이용기기등록서비스', 'security', '', 1, 0),
	('security', 'blockalarm', '보안차단/알림서비스', 'security', '', 1, 4),
	('security', 'delaytrs', '지연이체서비스', 'security', '', 1, 2),
	('transfer', 'many', '다계좌이체', 'right', '', 2, 1),
	('product', 'mortgage', '주택/전세자금', 'loan', '', 2, 3),
	('transfer', 'check', '자동납부결과 조회/취소', 'move', '', 2, 1),
	('transfer', 'check', '자동납부 조회/변경', 'move', '', 2, 0),
	('transfer', 'check', '자동송금결과 조회', 'move', '', 2, 3),
	('transfer', 'check', '자동송금 조회/변경', 'move', '', 2, 2),
	('utlity', 'myut', '나의공과금조회/납부', 'utlaw', '', 2, 0),
	('utlity', 'nadu', '국세/관세', 'utlity', '', 1, 3),
	('utlity', 'nat', '국세', 'nadu', '', 2, 0),
	('product', 'new', '신규', 'deposit', '', 2, 1),
	('product', 'new', '신규', 'fund', '', 2, 1),
	('utlity', 'patcom', '특허수수료', 'nadu', '', 2, 1),
	('product', 'payment', '이자납부원금상환', 'loan', '', 2, 1),
	('utlity', 'pen', '범칙금', 'etc', '', 2, 0),
	('product', 'pledge', '신용', 'loan', '', 2, 2),
	('product', 'product', '금융상품', '', '', 0, 2),
	('utlity', 'reserve', '공과금예약납부조회/취소', 'utlaw', '', 2, 1),
	('transfer', 'basics', '당행/타행이체', 'right', '', 2, 0),
	('transfer', 'check', '이체결과조회', 'right', '', 2, 2),
	('transfer', 'check', '예약이체등록 조회/취소', 'reservation', '', 2, 1),
	('transfer', 'register', '예약이체등록', 'reservation', '', 2, 0),
	('product', 'search', '펀드검색', 'fund', '', 2, 2),
	('transfer', 'check', '급여이체결과 조회', 'salary', '', 2, 2),
	('transfer', 'register', '급여이체 등록', 'salary', '', 2, 1),
	('utlity', 'staco', '국고입력납부', 'nadu', '', 2, 3),
	('utlity', 'tariff', '관세', 'nadu', '', 2, 2),
	('transfer', 'transfer', '이체', '', '', 0, 1),
	('transfer', 'auto', '자동이체', 'transfer', '', 1, 3),
	('transfer', 'delay', '지연이체', 'transfer', '', 1, 2),
	('transfer', 'etc', '기타이체서비스', 'transfer', '', 1, 6),
	('transfer', 'move', '계좌이동', 'transfer', '', 1, 5),
	('transfer', 'right', '즉시이체', 'transfer', '', 1, 0),
	('transfer', 'reservation', '예약이체', 'transfer', '', 1, 1),
	('transfer', 'salary', '급여이체', 'transfer', '', 1, 4),
	('utlity', 'tut', '등록금', 'etc', '', 2, 1),
	('usermanagement', 'acccs', '평생계좌전환서비스', 'accmanag', '', 2, 4),
	('usermanagement', 'bbcc', '통장표시내용변경', 'accmanag', '', 2, 6),
	('usermanagement', 'deposit', '자주쓰는입금계좌관리', 'accmanag', '', 2, 3),
	('usermanagement', 'anickname', '계좌별명관리', 'accmanag', '', 2, 5),
	('usermanagement', 'nomalaccc', '정상계좌 전환신청', 'accmanag', '', 2, 7),
	('usermanagement', 'apw', '계좌비밀번호관리', 'accmanag', '', 2, 1),
	('usermanagement', 'asequencecha', '계좌순서변경', 'accmanag', '', 2, 2),
	('usermanagement', 'withdacc', '출금계좌관리', 'accmanag', '', 2, 0),
	('usermanagement', 'edit', '마이메뉴편집', 'intbank', '', 2, 0),
	('usermanagement', 'holdcheck', '이체보류 신청/조회', 'intbank', '', 2, 5),
	('usermanagement', 'ibrelease', '인터넷뱅킹해지', 'intbank', '', 2, 6),
	('usermanagement', 'limitred', '이체한도감액', 'intbank', '', 2, 4),
	('usermanagement', 'otpregi', 'otp이용등록', 'intbank', '', 2, 1),
	('usermanagement', 'security', '스마트보안카드 온라인재등록', 'intbank', '', 2, 2),
	('usermanagement', 'unuserel', '장기미사용정지 해제', 'intbank', '', 2, 3),
	('usermanagement', 'bagreement', '(은행)개인(신용)정보수집-이용제공동의', 'info', '', 2, 3),
	('usermanagement', 'changenum', '입금불능연락처 변경', 'info', '', 2, 1),
	('usermanagement', 'gagreement', '(그룹사)개인(신용)정보수집-이용제공동의', 'info', '', 2, 4),
	('usermanagement', 'infochange', '고객정보 조회/변경', 'info', '', 2, 0),
	('usermanagement', 'phonecheck', '전화수신거부 신청/조회', 'info', '', 2, 2),
	('usermanagement', 'usermanagement', '사용자관리', ' ', '', 0, 5),
	('usermanagement', 'bbseal', '통장/인감', 'report', '', 2, 0),
	('usermanagement', 'cashier', '자기앞수표', 'report', '', 2, 3),
	('usermanagement', 'famcheck', '가계수표', 'report', '', 2, 4),
	('usermanagement', 'mic', '현금/ic카드', 'report', '', 2, 2),
	('usermanagement', 'scotp', '보안카드/otp', 'report', '', 2, 1),
	('usermanagement', 'accmanag', '계좌관리', 'usermanagement', '', 1, 2),
	('usermanagement', 'info', '고객정보관리', 'usermanagement', '', 1, 0),
	('usermanagement', 'intbank', '인터넷뱅킹관리', 'usermanagement', '', 1, 1),
	('usermanagement', 'report', '사고신고', 'usermanagement', '', 1, 3),
	('utlity', 'utlaw', '공과금/법원센터', 'utlity', '', 1, 0),
	('utlity', 'utlity', '공과금/법원', '', '', 0, 3),
	('utlity', 'wase', '상하수도요금', 'lotax', '', 2, 3);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;

-- 테이블 bank.security 구조 내보내기
CREATE TABLE IF NOT EXISTS `security` (
  `id` varchar(50) NOT NULL COMMENT '사용자 id',
  `serial` varchar(50) NOT NULL COMMENT '시리얼 번호',
  `type` varchar(50) NOT NULL COMMENT '장치타입',
  `status` varchar(50) NOT NULL COMMENT '사용여부',
  `register_date` datetime NOT NULL COMMENT '등록일',
  `end_date` datetime NOT NULL COMMENT '제거일',
  PRIMARY KEY (`id`),
  KEY `serial` (`serial`),
  KEY `status` (`status`),
  CONSTRAINT `fk_security_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자 보안매체';

-- 테이블 데이터 bank.security:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `security` DISABLE KEYS */;
/*!40000 ALTER TABLE `security` ENABLE KEYS */;

-- 테이블 bank.transfer_auto 구조 내보내기
CREATE TABLE IF NOT EXISTS `transfer_auto` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `account_number` varchar(50) NOT NULL,
  `to_account_number` varchar(50) NOT NULL,
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
CREATE TABLE IF NOT EXISTS `transfer_log` (
  `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '로그 번호',
  `account_number` varchar(50) NOT NULL COMMENT '보내는 계좌',
  `self` varchar(50) NOT NULL COMMENT '본인계좌여부',
  `target` varchar(50) NOT NULL COMMENT '은행/증권사명',
  `to_account_number` varchar(50) NOT NULL COMMENT '받는 계좌',
  `received` varchar(50) NOT NULL COMMENT '받는이',
  `sum` bigint(11) NOT NULL COMMENT '금액',
  `fee` int(11) NOT NULL COMMENT '수수료',
  `cms` varchar(50) NOT NULL COMMENT 'cms코드',
  `memo` varchar(50) NOT NULL COMMENT '내 통장메모',
  `to_memo` varchar(50) NOT NULL COMMENT '받는 통장메모',
  `status` varchar(50) NOT NULL COMMENT '성공/실패(사유)',
  `register_date` datetime NOT NULL COMMENT '거래일시',
  PRIMARY KEY (`seq`),
  KEY `accountnumber` (`account_number`),
  KEY `id` (`self`),
  KEY `status` (`status`),
  CONSTRAINT `fk_accountlog_account` FOREIGN KEY (`account_number`) REFERENCES `account` (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='계좌 이체 이력';

-- 테이블 데이터 bank.transfer_log:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `transfer_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `transfer_log` ENABLE KEYS */;

-- 테이블 bank.transfer_reserve 구조 내보내기
CREATE TABLE IF NOT EXISTS `transfer_reserve` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `account_number` varchar(50) NOT NULL,
  `to_account_number` varchar(50) NOT NULL,
  `sum` varchar(50) NOT NULL,
  `time` varchar(50) NOT NULL COMMENT '이체시간',
  `memo` varchar(50) NOT NULL,
  `to_memo` varchar(50) NOT NULL,
  `cms` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL COMMENT '사용/사용안함',
  `scheduled_date` varchar(50) NOT NULL,
  PRIMARY KEY (`seq`),
  KEY `account_number` (`account_number`),
  KEY `status` (`status`),
  CONSTRAINT `fk_reserve_transfer_account` FOREIGN KEY (`account_number`) REFERENCES `account` (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='예약이체 등록 정보';

-- 테이블 데이터 bank.transfer_reserve:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `transfer_reserve` DISABLE KEYS */;
/*!40000 ALTER TABLE `transfer_reserve` ENABLE KEYS */;

-- 테이블 bank.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(50) NOT NULL COMMENT '사용자 id',
  `pw` varchar(50) NOT NULL COMMENT '사용자 pw',
  `simple_pw` int(11) NOT NULL COMMENT '간편비밀번호',
  `name` varchar(50) NOT NULL COMMENT '사용자 이름',
  `gen` varchar(10) NOT NULL COMMENT '성별',
  `family` varchar(10) NOT NULL COMMENT '가계형태',
  `job_group` varchar(50) NOT NULL COMMENT '직업군',
  `income` varchar(50) NOT NULL COMMENT '연소득',
  `residence` varchar(50) NOT NULL COMMENT '거주지',
  `county` varchar(50) NOT NULL COMMENT '거주구',
  `status` varchar(10) NOT NULL COMMENT '계정 상태',
  `register_date` datetime NOT NULL COMMENT '등록일',
  `end_date` datetime DEFAULT NULL COMMENT '탈퇴일',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자 정보';

-- 테이블 데이터 bank.user:~3 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `pw`, `simple_pw`, `name`, `gen`, `family`, `job_group`, `income`, `residence`, `county`, `status`, `register_date`, `end_date`) VALUES
	('day_0821', '1111', 0, '박이찬', '', '', '', '', '', '', 'y', '2019-09-05 08:31:36', NULL),
	('elliottjo', '1234', 7777, '조은환', '남자', '3인', '프로그래머', '100000000', '인천시', '부평구', '활성', '2019-09-18 00:56:13', NULL),
	('exception', '123456', 921028, '황동규', '남자', '4인', '백수', '0', '안양시', '동안구', '활성', '2019-09-10 11:11:00', NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- 테이블 bank.user_pw_log 구조 내보내기
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

-- 테이블 bank.user_transfer_limit 구조 내보내기
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
