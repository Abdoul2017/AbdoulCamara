<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <link href="css/bootstrap.css" rel="stylesheet">
        <style type="text/css">
            #mainBlock{  
                font-family: calibri;

            }
            #moneyBlock{
                width: 180px;
                font-family: calibri;
            }               

        </style>

    </head>
    <body style="color: #8B4513; background-color: #F0FFFF;" >    


        <div id="mainBlock" class="container">
            <center><h1>Vending Machine</h1></center>       
            <hr/>
            <ul class="list-group" id="errorMessages"></ul>

            <div id = "itemsBlock" class="col-md-9">
                <c:forEach var="currentItem" items="${items}">
                    <a href="selectItem?itemId=${currentItem.itemId}">
                        <div class="col-md-3" style="border-style: groove; margin: 30px; padding: 15px;">
                            <button type="button" style="text-align: left;"> <c:out value="${currentItem.itemId}"/></button>
                            <br/>
                            <p style="text-align: center;"> <c:out value="${currentItem.name}"/></p>
                            <br/>
                            <p style="text-align: center;"> $<c:out value="${currentItem.price}"/></p>
                            <br/>
                            <p style="text-align: center;"> Quantity left: <c:out value="${currentItem.quantity}"/></p>
                        </div>
                    </c:forEach>
            </div>

            
            

            <div id="moneyBlock" class="col-md-3">
                <form class="form-horizontal" 
                      method="POST" 
                      role="form" 
                      action="insertMoney" 
                      style="margin-top: 20px; text-align: center;">
                    
                    <div class="form-group" style="text-align: center;">
                        <p class="col-md-offset-1" style="font-size: 30px;">Total $ in:</p>

                        <input type="text" class="form-control" 
                               value="${total}" 
                               style="text-align: center;" readonly/>
                        <br/>
                    </div>
                               
                    <div class="form-group" style="text-align: center;">
                        <button type="submit"  style="width: 65px; height: 50px;"  
                                value="1.00" name="addDollar">Add Dollar</button>
                        <button type="submit"  style="width: 65px; height: 50px" 
                                value="0.25" name="addQuarter">Add Quarter</button><br/>
                    </div>

                    <br/>
                    <div class="form-group" style="text-align: center;">
                        <button type="submit"  style="width: 65px; height: 50px"  
                                value="0.10" name="addDime">Add Dime</button>
                        <button type="submit"  style="width: 65px; height: 50px" 
                                value="0.05" name="addNickel">Add Nickel</button><br/>
                        <br/>
                    </div>
                    <hr/>
                </form>


                    
                    
                    

                <form class="form-horizontal" method="GET" role="form" action="makePurchase" style="text-align:center;">
                    <p class="col-md-offset-1" style="font-size: 30px;">Messages</p>
                    <input type="text" class="form-control" value="${message}" style="text-align: center;" readonly/><br/>
                    <label for="item" style="text-align: center;">
                        Item:
                    </label>                    
                    <input type="text" class="form-control" 
                           value="${id}"
                           
                           style="text-align: center;" 
                           id="item" readonly/><br/>
                    <button type="submit" style="text-align: center; width: 150px;">Make Purchase</button><br/>
                    <br/>
                    <hr/>
                    <p style="font-size: 30px; text-align: center;">Change</p>
                    <input type="text" class="form-control" style="text-align: center; height: 50px;" readonly/><br/>
                    <br/>
                </form>



                           

                <form class="form-horizontal" method="GET" role="form" action="getChange" style="text-align: center;">
                    <button type="submit" style="width: 150px;">Change Return</button>
                </form>    



            </div>
            <script src="js/jquery-2.2.4.min.js"></script>
            <script src="js/bootstrap.js"></script>        
    </body>
</html>

