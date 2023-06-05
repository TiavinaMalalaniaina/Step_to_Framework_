<%-- 
    Document   : index
    Created on : 23 mars 2023, 23:26:43
    Author     : tiavi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>INPUT</h1>
        <form action="save.action" method="post">
            <p>
                
            <input type="text" name="nom" value="RAMIANDRISOA">
            <input type="text" name="prenom" value="Tiavina">
            <input type="date" name="dtn" >
            </p>
            <p>
                
                <input type="checkbox" name="loisir" value="basket"> basket
            </p>
            <p>
                <input type="checkbox" name="loisir" value="foot"> foot
            </p>
            <p>
                <input type="checkbox" name="loisir" value="volley"> volley
            </p>
            <input type="submit" value="Envoyer">
            
        </form>
    </body>
</html>
