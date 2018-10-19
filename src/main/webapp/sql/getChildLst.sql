CREATE FUNCTION `getChildLst`(areaId INT)
RETURNS varchar(1000)
BEGIN
DECLARE sTemp VARCHAR(4000);

DECLARE sTempChd VARCHAR(4000);
SET sTemp = '$';
SET sTempChd = cast(areaId as char);
WHILE sTempChd is not NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT group_concat(menuid) INTO sTempChd FROM menu_resource where FIND_IN_SET(parentmenuid,sTempChd)>0 and removed='0' ;
END WHILE;
return sTemp;
END