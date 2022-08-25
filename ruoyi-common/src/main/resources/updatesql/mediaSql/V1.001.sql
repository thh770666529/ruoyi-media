alter table wm_movie
    add stills varchar(1000) comment '剧照',
add total_video_length bigint(20) comment  '视频总长度';
commit;
