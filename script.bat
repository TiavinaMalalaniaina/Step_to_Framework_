:: Création du dossier temporaire temp
mkdir temp
cd temp

:: Création de la structure du fichier
mkdir WEB-INF
mkdir src
cd WEB-INF
mkdir classes
mkdir lib
cd ../../

:: Compilation du modele
copy fw.jar .\temp\WEB-INF\lib
for /r .\Test-Framework\ %%f in (*.java) do copy %%f .\temp\src
javac -cp .\temp\WEB-INF\lib\fw.jar -d .\temp\WEB-INF\classes .\temp\src\*.java
rmdir /S /Q .\temp\src

:: Copie des Autre fichiers
copy .\Test-Framework\web\input.jsp .\temp\
copy .\Test-Framework\web\index.jsp .\temp\
copy .\Test-Framework\web\WEB-INF\web.xml .\temp\WEB-INF\

:: Déploiement vers tomcat
jar -cvf test_fw.war -C .\temp\ .
copy test_fw.war D:\Logiciel\Apache_Software_Foundation\Tomcat_10.0\webapps\

:: Supprimer le dossier temp
rmdir /S /Q .\temp



