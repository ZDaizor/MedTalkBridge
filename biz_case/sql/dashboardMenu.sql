-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('仪表板统计', '3', '1', 'dashboard', 'system/dashboard/index', 1, 0, 'C', '0', '0', 'system:dashboard:list', 'dashboard', 'admin', sysdate(), '', null, '仪表板统计菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('仪表板统计查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:dashboard:statistics',        '#', 'admin', sysdate(), '', null, '');