

<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>

    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automcatically -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />

    <!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Wholesail</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/load">Load</a></li>
                <li class="active"><a href="#">Invoice</a></li>
                <li><a href="/balance">Balance</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <header>
        <h1>Load Raw Invoice Data</h1>
    </header>
    <div class="starter-template">
            <table
                    class="table table-striped table-hover table-condensed table-bordered">
                <tr>
                    <th>Seller</th>
                    <th>Customer</th>
                    <th>Invoice ID</th>
                    <th>Payment Term</th>
                    <th>Amount</th>
                    <th>Adjustment Amount</th>
                    <th>Paid Amount</th>
                    <th>Transaction Date</th>
                    <th>Paid In Full Date</th>
                    <th>Line Item</th>
                    <th>Memo</th>
                </tr>
                <c:forEach var="invoice" items="${invoices}">
                    <tr>
                        <td>${invoice.seller.name}</td>
                        <td>${invoice.customerName}</td>
                        <td>${invoice.invoiceId}</td>
                        <td>${invoice.paymentTerm}</td>
                        <td><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${invoice.amountInCents / 100}" /></td>
                        <td><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${invoice.adjustmentAmountInCents / 100}" /></td>
                        <td><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${invoice.paidAmountInCents / 100}" /></td>
                        <td>${invoice.dateOfTransaction}</td>
                        <td>${invoice.dateOfPaymentInFull}</td>
                        <td>${invoice.lineItems}</td>
                        <td>${invoice.memo}</td>
                    </tr>
                </c:forEach>
            </table>
    </div>
</div>

<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>