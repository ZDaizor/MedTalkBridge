-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生问诊列', '3', '1', 'sessions', 'system/sessions/index', 1, 0, 'C', '0', '0', 'system:sessions:list', '#', 'admin', sysdate(), '', null, '学生问诊列菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生问诊列查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:sessions:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生问诊列新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:sessions:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生问诊列修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:sessions:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生问诊列删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:sessions:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生问诊列导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:sessions:export',       '#', 'admin', sysdate(), '', null, '');