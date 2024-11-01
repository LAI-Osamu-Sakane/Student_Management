INSERT INTO students  (name, kana_name , nickname, email, area, age, sex, remark, is_deleted)
VALUES ('坂根 治', 'さかね おさむ', 'おさむ', 'aaa.bb@zzz.com', '関西', 52, '男', 'なし', false),
('渡真利 大吾郎', 'とまり だいごろう', 'だいごろう', 'ccc.dd@zzz.com', '九州', 52, '男', 'なし', false),
('北川 尚', 'きたがわ ひさし', 'ひさし', 'eee.ff@zzz.com', '関東', 42, '男', 'なし', false),
('高瀬 雅之', 'たかせ まさゆき', 'まさゆき', 'ggg.hh@zzz.com', '関西', 42, '男', 'なし', false),
('星 加奈子', 'ほし かなこ', 'かなこ', 'iii.jj@zzz.com', '関東', 32, '女', 'なし', false)
;

INSERT INTO students_courses (student_id, course_name, course_start_at, course_end_at)
VALUES (1,'Java Basic','2024-01-23 00:00:00', '2024-04-22 00:00:00'),
(2,'Python Basic','2024-01-23 00:00:00', '2024-02-22 00:00:00'),
(3,'Java Basic','2024-01-23 00:00:00', '2024-04-22 00:00:00'),
(4,'PHP Basic','2024-01-23 00:00:00', '2024-03-22 00:00:00'),
(5,'Python Basic','2024-01-23 00:00:00', '2024-02-22 00:00:00'),
(1,'Python Basic','2024-02-23 00:00:00', '2024-03-22 00:00:00'),
(3,'PHP Basic','2024-03-23 00:00:00', '2024-05-22 00:00:00'),
(5,'Java Basic','2024-03-23 00:00:00', '2024-06-22 00:00:00')
;