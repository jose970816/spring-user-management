-- CREACIÓN DE MODULOS
INSERT INTO module (name, base_path) VALUES ('USER', '/users');
INSERT INTO module (name, base_path) VALUES ('POST', '/posts');
INSERT INTO module (name, base_path) VALUES ('CUSTOMER', '/customers');
INSERT INTO module (name, base_path) VALUES ('AUTH', '/auth');

-- CREACIÓN DE OPERACIONES
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_USERS','/listAll', 'GET', false, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_USER','', 'GET', true, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_USER','', 'POST', true, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_USER','/[0-9]*', 'PUT', false, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_USER','/[0-9]*/disabled', 'PUT', false, 1);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_POSTS','', 'GET', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_POST','', 'POST', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_POST','/[0-9]*', 'PUT', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_POST','/[0-9]*/disabled', 'PUT', false, 2);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('AUTHENTICATE','/authenticate', 'POST', true, 4);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('VALIDATE-TOKEN','/validate-token', 'GET', true, 4);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_MY_PROFILE','/profile','GET', false, 4);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('LOGOUT','/logout','POST', true, 4);

-- CREACIÓN DE ROLES
INSERT INTO role (name) VALUES ('CUSTOMER');
INSERT INTO role (name) VALUES ('ASSISTANT_ADMINISTRATOR');
INSERT INTO role (name) VALUES ('ADMINISTRATOR');

-- CREACIÓN DE PERMISOS
--PERMISOS DEL ROL CUSTOMER
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 12);

--PERMISOS DEL ROL ASSISTANT_ADMINISTRATOR
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 1);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 2);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 3);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 12);

--PERMISOS DEL ROL ADMINISTRATOR
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 1);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 2);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 3);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 4);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 5);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 6);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 7);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 8);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 9);
INSERT INTO granted_permission (role_id, operation_id) VALUES (3, 12);

-- CREACIÓN DE USUARIOS
INSERT INTO "user" (username, name, last_name, password, cellphone, enabled, creation_date, role_id) VALUES ('lmarquez', 'Luis', 'Márquez', '$2a$10$ywh1O2EwghHmFIMGeHgsx.9lMw5IXpg4jafeFS.Oi6nFv0181gHli', 936045556, true, CURRENT_TIMESTAMP, 1);
INSERT INTO "user" (username, name, last_name, password, cellphone, enabled, creation_date, role_id) VALUES ('fperez', 'Fernando', 'Pérez', '$2a$10$V29z7/qC9wpHfzRMxGOHye5RMAxCid2/MzJalk0dsiA3zZ9CJfub.', 952801659, true, CURRENT_TIMESTAMP, 2);
INSERT INTO "user" (username, name, last_name, password, cellphone, enabled, creation_date, role_id) VALUES ('mhernandez', 'Martin', 'Hernández', '$2a$10$TMbMuEZ8utU5iq8MOoxpmOc6QWQuYuwgx1xJF8lSMNkKP3hIrwYFG', 911365765, true, CURRENT_TIMESTAMP, 3);

-- CREACIÓN DE POSTS
INSERT INTO post (text, status, creation_date, user_id) VALUES ('Hoy tenemos un evento en el parque a las 3 pm. ¡No te lo pierdas!', 'ENABLED', CURRENT_TIMESTAMP, 1);
INSERT INTO post (text, status, creation_date, user_id) VALUES ('Aquí tienes un enlace interesante sobre programación funcional: https://spring.io/projects/spring-security', 'ENABLED', CURRENT_TIMESTAMP, 1);
INSERT INTO post (text, status, creation_date, user_id) VALUES ('La mayor gloria no es nunca caer, sino levantarse siempre.' ,'ENABLED', CURRENT_TIMESTAMP, 2);
INSERT INTO post (text, status, creation_date, user_id) VALUES ('¡Acabo de terminar la primera versión de nuestro proyecto! Estoy emocionado por compartirlo con todos ustedes.' ,'ENABLED', CURRENT_TIMESTAMP, 2);
INSERT INTO post (text, status, creation_date, user_id) VALUES ('¡Miren esta hermosa puesta de sol que capturé hoy!' ,'ENABLED', CURRENT_TIMESTAMP, 3);
INSERT INTO post (text, status, creation_date, user_id) VALUES ('¡Gran noticia! Nuestra empresa acaba de anunciar una asociación estratégica con una compañía líder en la industria.' ,'ENABLED', CURRENT_TIMESTAMP, 3);