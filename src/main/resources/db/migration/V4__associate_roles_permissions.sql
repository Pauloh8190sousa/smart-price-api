INSERT INTO t_role_permission(role_id, permission_id)
SELECT r.id, p.id
FROM t_role r, t_permission p
WHERE r.name = 'ROLE_ADMIN';

INSERT INTO t_role_permission(role_id, permission_id)
SELECT r.id, p.id
FROM t_role r, t_permission p
WHERE r.name = 'ROLE_USER'
  AND p.name = 'PRODUCT_READ';