alter table wm_movie
    add summary varchar(1000) comment '简介',
add en varchar(100) comment  '拼音',
add letter varchar(100) comment  '首字母大写',
add lang varchar(30) comment  '语言';

INSERT INTO `sys_dict_type`(`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`,
                            `update_time`, `remark`)
VALUES (118, '语言', 'lang', '0', 'admin', '2021-12-04 11:32:24', '', NULL, '语言');
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`,
                            `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`,
                            `update_time`, `remark`)
VALUES (179, 4, '日语', '日语', 'lang', NULL, 'default', 'N', '0', 'admin', '2021-12-04 11:33:47', 'admin',
        '2021-12-04 11:49:45', NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`,
                            `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`,
                            `update_time`, `remark`)
VALUES (180, 0, '国语', '国语', 'lang', NULL, 'default', 'N', '0', 'admin', '2021-12-04 11:34:04', '', NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`,
                            `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`,
                            `update_time`, `remark`)
VALUES (182, 1, '粤语', '粤语', 'lang', NULL, 'default', 'N', '0', 'admin', '2021-12-04 11:34:23', 'admin',
        '2021-12-04 11:49:25', NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`,
                            `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`,
                            `update_time`, `remark`)
VALUES (183, 0, '英语', '英语', 'lang', NULL, 'default', 'N', '0', 'admin', '2021-12-04 11:34:30', '', NULL, NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`,
                            `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`,
                            `update_time`, `remark`)
VALUES (184, 3, '韩语', '韩语', 'lang', NULL, 'default', 'N', '0', 'admin', '2021-12-04 11:34:41', 'admin',
        '2021-12-04 11:49:35', NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`,
                            `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`,
                            `update_time`, `remark`)
VALUES (185, 9, '其他', '其他', 'lang', NULL, 'default', 'N', '0', 'admin', '2021-12-04 11:34:51', 'admin',
        '2021-12-04 11:49:53', NULL);
INSERT INTO `sys_dict_data`(`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`,
                            `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`,
                            `update_time`, `remark`)
VALUES (186, 0, '闽南语', '闽南语', 'lang', NULL, 'default', 'N', '0', 'admin', '2021-12-04 13:02:34', '', NULL, NULL);

INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`,
                       `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`,
                       `update_by`, `update_time`, `remark`)
VALUES (2106, '资源管理', 0, 11, 'resource', NULL, NULL, 1, 0, 'M', '0', '0', '', 'documentation', 'admin',
        '2021-12-04 13:34:50', 'admin', '2021-12-04 13:35:01', '');
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`,
                       `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`,
                       `update_by`, `update_time`, `remark`)
VALUES (2107, '网盘管理', 2106, 1, 'file', 'resource/file/File', NULL, 1, 0, 'C', '0', '0', 'resource:file:list', 'table',
        'admin', '2021-12-04 13:36:33', 'admin', '2021-12-04 13:42:56', '');
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`,
                       `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`,
                       `update_by`, `update_time`, `remark`)
VALUES (2108, '我的分享', 2106, 2, 'myshare', 'resource/MyShare/index', NULL, 1, 0, 'C', '0', '0', 'resource:myshare:list',
        'table', 'admin', '2021-12-04 13:39:48', 'admin', '2021-12-04 13:43:05', '');
commit;
