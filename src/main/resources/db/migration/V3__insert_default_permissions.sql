INSERT INTO t_permission(id, name)
VALUES
    -- Product permisson
    (gen_random_uuid(), 'PRODUCT_READ'),
    (gen_random_uuid(), 'PRODUCT_WRITE'),
    (gen_random_uuid(), 'PRODUCT_UPDATE'),
    (gen_random_uuid(), 'PRODUCT_DELETE'),
    -- Store permission
    (gen_random_uuid(), 'STORE_READ'),
    (gen_random_uuid(), 'STORE_WRITE'),
    (gen_random_uuid(), 'STORE_UPDATE'),
    (gen_random_uuid(), 'STORE_DELETE');