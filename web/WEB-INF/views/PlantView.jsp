<%@page import="workconstants.ControllerConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${plant.getName()}</title>
        <style>
            .product-view {
                display: flex;
                align-items: center;
                padding: 20px;
            }

            .image-section {
                width: 40%;
                text-align: center;
            }

            .product-details {
                width: 60%;
                padding-left: 20px;
            }

            .product-name {
                font-size: 24px;
                font-weight: bold;
            }

            .product-description {
                font-size: 16px;
                margin-top: 10px;
            }

            .product-price {
                font-size: 18px;
                font-weight: bold;
                margin-top: 20px;
            }

            .add-to-cart {
                display: flex;
                align-items: center;
                justify-content: space-between;
                margin-top: 20px;
            }

            .number-selector {
                width: 60px;
                height: 30px;
                border: 1px solid black;
                text-align: center;
            }

            .cart-button {
                width: 120px;
                height: 40px;
                background-color: red;
                color: white;
                font-size: 16px;
                font-weight: bold;
                border: none;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <%@include file="jspf/header.jspf"%>
        <div class="product-view">
            <div class="image-section">
                <img src="resources/img/${plant.getImgPath()}"  alt="${plant.getDescription()}" width='500' height='500'>
            </div>
            <div class="product-details">
                <div class="product-name">${plant.getName()}</div>
                <div class="product-description">${plant.getDescription()}</div>
                <div class="product-price">${plant.getPrice()} $</div>
                <form action="MainController?action=<%=ControllerConstants.ADDTOCART%>&id=${plant.getId()}" method="post">
                    <div>
                        <label>Number of items:</label>
                        <input type="number" name="quantity" min="1" value="1">
                    </div>            
                        <input class="cart-button border-2 bg-slate-200" type="submit" value="Put to Cart">       
                </form>
            </div>
        </div>
    </body>
</html>
