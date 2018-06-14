CREATE DATABASE IF NOT EXISTS speedjob;

/* Drop & Create user */
CREATE USER 'speedjob_user'@'localhost' IDENTIFIED BY 'LeJobParfaitPourTimmy!';

/* Grant his privileges */
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,INDEX,ALTER,DROP,REFERENCES ON speedjob.* TO 'speedjob_user'@'localhost';

FLUSH PRIVILEGES;