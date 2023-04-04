jar -cf fw.jar -C .\Framework\build\web\WEB-INF\classes\ etu2025
copy fw.jar .\Test-Framework\build\web\WEB-INF\lib\
jar -cvf test_fw.war -C .\Test-Framework\build\web\ .
copy test_fw.war D:\Logiciel\Apache_Software_Foundation\Tomcat_10.0\webapps\
