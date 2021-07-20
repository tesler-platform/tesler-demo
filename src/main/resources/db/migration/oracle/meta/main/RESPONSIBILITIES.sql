insert into responsibilities (id, resp_type, screens, dept_id, internal_role_cd)
VALUES (nextval('RESPONSIBILITY_ID'), 'SCREEN',
        '[
          {
    "id": "id12",
    "name": "preferences",
    "text": "Настройки",
    "url": "/screen/preferences"
  },
  {
    "id": "id1",
    "name": "client",
    "text": "Client",
    "url": "/screen/client"
  }
        ]', 0, 'ADMIN');
insert into responsibilities (id, resp_type, dept_id, internal_role_cd, responsibilities)
VALUES (nextval('RESPONSIBILITY_ID'), 'VIEW', 0, 'ADMIN', 'clientlist');
insert into responsibilities (id, resp_type, dept_id, internal_role_cd, responsibilities)
VALUES (nextval('RESPONSIBILITY_ID'), 'VIEW', 0, 'ADMIN', 'clientedit');
insert into responsibilities (id, resp_type, dept_id, internal_role_cd, responsibilities)
VALUES (nextval('RESPONSIBILITY_ID'), 'VIEW', 0, 'ROLE_ADMIN', 'clientview');


insert into responsibilities (id, resp_type, screens, dept_id, internal_role_cd)
VALUES (nextval('RESPONSIBILITY_ID'), 'SCREEN',
        '[
          {
    "id": "id12",
    "name": "preferences",
    "text": "Настройки",
    "url": "/screen/preferences"
  },
  {
    "id": "id1",
    "name": "client",
    "text": "Client",
    "url": "/screen/client"
  }
        ]', 0, 'ROLE_ADMIN');
insert into responsibilities (id, resp_type, dept_id, internal_role_cd, responsibilities)
VALUES (nextval('RESPONSIBILITY_ID'), 'VIEW', 0, 'ROLE_ADMIN', 'clientlist');
insert into responsibilities (id, resp_type, dept_id, internal_role_cd, responsibilities)
VALUES (nextval('RESPONSIBILITY_ID'), 'VIEW', 0, 'ROLE_ADMIN', 'clientedit');
insert into responsibilities (id, resp_type, dept_id, internal_role_cd, responsibilities)
VALUES (nextval('RESPONSIBILITY_ID'), 'VIEW', 0, 'ROLE_ADMIN', 'clientview');

insert into responsibilities (id, resp_type, screens, dept_id, internal_role_cd)
VALUES (nextval('RESPONSIBILITY_ID'), 'SCREEN',
        '[
          {
    "id": "id12",
    "name": "preferences",
    "text": "Настройки",
    "url": "/screen/preferences"
  },
  {
    "id": "id1",
    "name": "client",
    "text": "Client",
    "url": "/screen/client"
  }
        ]', 0, 'ROLE_ROLE_ADMIN');
insert into responsibilities (id, resp_type, dept_id, internal_role_cd, responsibilities)
VALUES (nextval('RESPONSIBILITY_ID'), 'VIEW', 0, 'ROLE_ROLE_ADMIN', 'clientlist');
insert into responsibilities (id, resp_type, dept_id, internal_role_cd, responsibilities)
VALUES (nextval('RESPONSIBILITY_ID'), 'VIEW', 0, 'ROLE_ROLE_ADMIN', 'clientedit');
insert into responsibilities (id, resp_type, dept_id, internal_role_cd, responsibilities)
VALUES (nextval('RESPONSIBILITY_ID'), 'VIEW', 0, 'ROLE_ADMIN', 'clientview');
