CREATE TABLE t_permission (
                              id UUID PRIMARY KEY,
                              name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE t_role (
                        id UUID PRIMARY KEY,
                        name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE t_role_permission (
                                   role_id UUID NOT NULL,
                                   permission_id UUID NOT NULL,

                                   CONSTRAINT fk_role_permission_role
                                       FOREIGN KEY (role_id) REFERENCES t_role(id),

                                   CONSTRAINT fk_role_permission_permission
                                       FOREIGN KEY (permission_id) REFERENCES t_permission(id)
);