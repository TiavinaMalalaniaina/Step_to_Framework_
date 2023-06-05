set fw_root=D:\Etude\L2\S4\INF209-Web_dynamique\project\Step_to_Framework\Framework
set project_lib=.\Test-Framework\build\web\WEB-INF\lib\

mkdir src_jar
mkdir class_jar

for /r %fw_root%\ %%f in (*.java) do copy %%f .\src_jar
javac -cp servlet-api.jar -parameters -d .\class_jar .\src_jar\*.java

jar -cf fw.jar -C .\Framework\build\web\WEB-INF\classes\ etu2025
copy fw.jar %project_lib%

rmdir /S /Q src_jar
rmdir /S /Q class_jar