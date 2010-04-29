@echo off
REM compress and sign a JAR file, remove the original
REM args are <JAR> <keystore> <password> <alias>
@echo on
pack200 --repack --strip-debug %1
jarsigner -keystore %2 -storepass storepass -keypass keypass %1 %4
jarsigner -verify %1
pack200 %1.pack.gz %1
echo del %1
