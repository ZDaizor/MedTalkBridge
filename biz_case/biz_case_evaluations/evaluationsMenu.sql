-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生病例得分总', '3', '1', 'evaluations', 'system/evaluations/index', 1, 0, 'C', '0', '0', 'system:evaluations:list', '#', 'admin', sysdate(), '', null, '学生病例得分总菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生病例得分总查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:evaluations:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生病例得分总新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:evaluations:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生病例得分总修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:evaluations:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生病例得分总删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:evaluations:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生病例得分总导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:evaluations:export',       '#', 'admin', sysdate(), '', null, '');