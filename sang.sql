-- Ctrl + a [전체선택] 이후 alt + x 통해 전체테이블 drop 및 create 가능합니다
-- 테이블 삭제
drop table heartsave cascade constraints;
drop table ta_replynotice cascade constraints;
drop table ten_audit cascade constraints;
drop table t_replynotice cascade constraints;
drop table w_replynotice cascade constraints;
drop table at_replynotice cascade constraints;
drop table inquiry cascade constraints;
drop table qna cascade constraints;
drop table cancle_buy cascade constraints;
drop table tentext cascade constraints;
drop table write cascade constraints;
drop table attend cascade constraints;
drop table staff cascade constraints;
drop table replynotice cascade constraints;
drop table notice cascade constraints;
drop table artist cascade constraints;
drop table replyreview cascade constraints;
drop table review cascade constraints;
drop table love cascade constraints;
drop table buy cascade constraints;
drop table cart cascade constraints;
drop table detail cascade constraints;
drop table academy cascade constraints;
drop table category cascade constraints;
drop table member cascade constraints;

-- 회원
create table member (
	id varchar2(20) primary key, 	-- 아이디
	password varchar2(100),			-- 암호
	name varchar2(20),				-- 이름
	tel varchar2(20),				-- 연락처
	email varchar2(50),				-- 이메일
	image varchar2(200),			-- 회원 이미지
	reg_date date,					-- 가입일자
	del char(1) default 'N',		-- 회원삭제
	snt char(1) default 'N',		-- 수강생/강사 구분
	admin char(1) default 'N'		-- 관리자 구분
);

--카테고리
create table category (
	ct_no number(20) primary key,	--카테고리 번호
	ct_tag varchar(100)
);

-- 강의
create table academy (
	a_no number(20) primary key,			-- 강의번호
	title varchar2(200),					-- 제목
	price number(10),						-- 가격
	intro varchar2(200),					-- 소개
	tag varchar2(100),						-- 키워드
	schedule varchar2(100),					-- 일정
	a_name varchar2(100),					-- 강사
	place varchar2(100),					-- 장소
	inwon varchar2(100),					-- 정원
	turn varchar2(100),						-- 회차
	request varchar2(100),					-- 접수
	onoff char(1) default 'N',				-- 온y-오프라인n 수업 구분
	image varchar2(200),					-- 섬네일
	count number(10),						-- 수량 - 모집중/모집마감 구분
	a_date date,							-- 등록일자
	id varchar2(20),						-- 아이디(fk)
	view_count number(10),					-- 조회수
	good_count number(10),					-- 좋아요 수 [1.추가된건가요? 질문하기]
	status char(1) default 'N',				-- 대기접수중N 구분
	ct_no number(20),						-- 카테고리 번호(fk)
	content CLOB					-- CLOB : 제한이없는 타입 , 주로text가많은곳에사용
);
-- fk는 아카데미 삭제구현위해 사용하지 않겠습니다
select * from academy;

-- 강의 상세
create table detail(
	a_no number(20) primary key,
	txt_title1 varchar2(100),
	text1 varchar2(2000),
	text1_img varchar2(200),
	txt_title2 varchar2(100),
	text2 varchar2(2000),
	text2_img varchar2(200),
	txt_title3 varchar2(100),
	text3 varchar2(2000),
	text3_img varchar2(200),
	txt_title4 varchar2(100),
	text4 varchar2(2000),
	text4_img varchar2(200),
	txt_title5 varchar2(100),
	text5 varchar2(2000),
	text5_img varchar2(200),
	txt_title6 varchar2(100),
	text6 varchar2(2000),
	text6_img varchar2(200),
	foreign key(a_no) references academy(a_no)
);

-- 장바구니
create table cart (
	c_no number(20) primary key,				-- 장바구니 번호
	c_count number(10),							-- 장바구니 수량
	id varchar2(20),							-- 아이디 (fk)
	a_no number(20),							-- 강의 번호 (fk)
	foreign key(id) references member(id),
	foreign key(a_no) references academy(a_no)
);
select * from cart;

-- 구매
create table buy (
	b_no number(20) primary key,				-- 구매 번호
	b_date date,								-- 주문 날짜
	cancle char(1) default 'N',					-- 취소
	c_count number(10),							-- 장바구니 수량
	a_no number(20),							-- 강의 번호 (fk)
	id varchar2(20),							-- 아이디 (fk)
	foreign key(id) references member(id),
	foreign key(a_no) references academy(a_no)
);

-- 찜
create table love (
	l_no number(20) primary key,				-- 찜 번호
	id varchar2(20),							-- 아이디 (fk)
	a_no number(20),							-- 강의 번호 (fk)
	foreign key(id) references member(id),
	foreign key(a_no) references academy(a_no)
);

-- 리뷰
create table review (
	r_no number(20) primary key,				-- 리뷰 번호
	r_title varchar2(100),						-- 리뷰 제목
	r_image varchar2(200),						-- 리뷰 사진
	r_content varchar2(2000),					-- 리뷰 내용
	r_date date,								-- 등록 일자
	id varchar2(20),							-- 아이디 (fk)
	ct_no number(20),							-- 카테고리 번호(fk)
	foreign key(id) references member(id),
	foreign key(ct_no) references category(ct_no)
);

-- 리뷰 댓글
create table replyreview (
	r_rno number(20) primary key,
	r_bno number(20),
	r_replytext varchar2(500),
	r_replier varchar2(50),
	r_regdate date,
	r_updatedate date,
	r_del char(1) default 'N'
);

-- 소식
create table artist (
	ar_no number(20) primary key,				-- 소식(아티스트) 번호
	ar_title varchar2(100),						-- 소식(아티스트) 제목
	ar_image varchar2(200),						-- 소식(아티스트) 사진
	ar_content varchar2(4000),					-- 소식(아티스트) 내용
	ar_date date,								-- 등록 일자
	id varchar2(20),							-- 아이디 (fk)
	ct_no number(20),							-- 카테고리 번호(fk)
	ar_detail varchar2(200),					-- 상세 보기 이미지
	ar_detail2 varchar2(200),					-- 상세 보기 이미지
	foreign key(id) references member(id),
	foreign key(ct_no) references category(ct_no)
);

-- 공지
create table notice (
	nt_no number(20) primary key,				-- 공지 번호
	nt_title varchar2(100),						-- 공지 제목
	nt_image varchar2(200),						-- 공지 사진
	nt_content varchar2(2000),					-- 공지 내용
	nt_date date,								-- 등록 일자
	id varchar2(20),							-- 아이디 (fk)
	ct_no number(20),							-- 카테고리 번호(fk)
	foreign key(id) references member(id),
	foreign key(ct_no) references category(ct_no)
);

-- 공지 댓글
create table replynotice (
	nt_rno number(20) primary key,
	nt_bno number(20),
	nt_replytext varchar2(500),
	nt_replier varchar2(50),
	nt_regdate date,
	nt_updatedate date,
	nt_del char(1) default 'N'
);

-- 강사 소개
create table staff (
	st_no number(20) primary key,				-- 강사소개 번호
	st_title varchar2(100),						-- 강사소개 제목
	st_image varchar2(200),						-- 강사소개 사진
	st_content varchar2(2000),					-- 강사소개 내용
	st_name varchar2(50),						-- 강사소개 이름
	st_type varchar2(100),						-- 강사소개 강의타입
	st_date date,								-- 등록 일자
	id varchar2(20),							-- 아이디 (fk)
	foreign key(id) references member(id)
);

-- 참여
create table attend (
	at_no number(20) primary key, -- 참여 번호
	at_title varchar2(100), -- 참여 제목
	at_image varchar2(200), -- 참여 사진
	at_editor varchar2(3000), --참여 편집자의 말
	at_content1 varchar2(4000), -- 참여 내용 2000-> 4000수정
	at_content2 varchar2(4000), -- 참여 내용2
	at_content3 varchar2(4000), -- 참여 내용3
	at_content4 varchar2(4000), -- 참여 내용4
	at_content5 varchar2(4000), -- 참여 내용5
	at_content6 varchar2(4000), -- 참여 내용6
	at_content7 varchar2(4000), -- 참여 내용7
	at_content8 varchar2(4000), -- 참여 내용8
	at_date date, -- 등록 일자
	at_good_count number(10), -- 좋아요 수
	at_view_count number(10), -- 조회수
	at_repl_count number(10), -- 댓글 수
	id varchar2(20), -- 아이디 (fk)
	foreign key(id) references member(id)
);

-- 참여 - 글쓰기
create table write (
	w_no number(20) primary key, -- 번호
	w_title varchar2(100), -- 제목
	w_content varchar2(4000), -- 내용 2000-> 4000수정
	w_date date, -- 등록일자
	w_good_count number(10), -- 좋아요 수
	w_repl_count number(10), -- 댓글 수
	id varchar2(20), -- 아이디(fk)
	foreign key(id) references member(id)
);

-- 참여 열줄
create table tentext(
	t_no number(20) primary key,	-- 열줄번호
	t_title varchar2(200),			-- 열줄제목
	t_image varchar2(100),			-- 열줄썸네일
	t_content varchar2(2000),		-- 열줄내용
	t_date date,					-- 열줄등록일자
	id varchar2(20),
	foreign key(id) references member(id)
);

-- 참여 댓글
create table at_replynotice (
	at_rno number(20) primary key,
	at_bno number(20),
	at_replytext varchar2(2000), -- 500 -> 2000 수정
	at_replier varchar2(50),
	at_regdate date,
	at_updatedate date,
	id varchar2(20),
	foreign key(id) references member(id)
);

-- 글쓰기 댓글
create table w_replynotice (
	w_rno number(20) primary key,
	w_bno number(20),
	w_replytext varchar2(500),
	w_replier varchar2(50),
	w_regdate date,
	w_updatedate date,
	id varchar2(20),
	foreign key(id) references member(id)
);

-- 구매취소내역
create table cancle_buy(
	ca_no number(20) primary key,
	ca_option varchar2(100),
	ca_content varchar2(1000),
	b_no number(20),
	foreign key(b_no) references buy(b_no)
);


-- 마이페이지 - 문의
create table qna(
	q_no number(20) primary key,
	q_title varchar2(100),
	q_content varchar2(2000),
	q_date Date,
	q_del char(1) default 'n',
	id varchar2(20),
	foreign key(id) references member(id)
);


-- 문의게시판
create table inquiry(
	in_no number(20) primary key, 
	in_title varchar2(300),
	in_reply varchar2(3000)
);


-- 열줄-심사평
create table ten_audit(
	ta_no number(20) primary key,	-- 심사평 번호
	ta_title varchar2(100),			-- 심사평 제목
	ta_image varchar2(100),			-- 심사평 이미지
	ta_content1 varchar2(4000),		-- 본문내용
	ta_content2 varchar2(4000),		-- 본문내용
	ta_content3 varchar2(4000),		-- 본문내용
	ta_content4 varchar2(4000),		-- 본문내용
	ta_content5 varchar2(4000),		-- 본문내용
	ta_content6 varchar2(4000),		-- 본문내용
	ta_content7 varchar2(4000),		-- 본문내용
	ta_content8 varchar2(4000)		-- 본문내용
);

-- 열줄-심사평 댓글
create table ta_replynotice (
	ta_rno number(20) primary key,
	ta_bno number(20),
	ta_replytext varchar2(500),
	ta_replier varchar2(50),
	ta_regdate date,
	ta_updatedate date,
	id varchar2(20),
	foreign key(id) references member(id)
);

-- 열줄 댓글
create table t_replynotice (
	t_rno number(20) primary key,
	t_bno number(20),
	t_replytext varchar2(500),
	t_replier varchar2(50),
	t_regdate date,
	t_updatedate date,
	id varchar2(20),
	foreign key(id) references member(id)
);

-- 좋아요 저장
create table heartsave (
	hs_no number(20) primary key,
	at_no number(20),
	w_no number(20),
	id varchar2(20),
	foreign key(at_no) references attend(at_no),
	foreign key(w_no) references write(w_no),
	foreign key(id) references member(id)
);
