<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%@include file="fragments/css_imports.html" %>
    <title>Мои анкеты</title>
</head>
<body>
<%@include file="fragments/header.jsp" %>
<main class="container">
    <div class="row">
        <div class="box">
            <c:if test="${empty chip || (chip eq true)}">
                <div class="chip">
                    Здравствуйте, ${user_initials}, Вы вошли под правами респондента.
                    <i class="material-icons" onclick="hideChip()">close</i>
                </div>
            </c:if>
            <h4 class="teal-text">Ваши анкеты</h4>

            <c:choose>
                <c:when test="${userInterviews.size() < 1}">
                    <h6>У вас пока нет анкет для прохождения.</h6>
                </c:when>
                <c:otherwise>
                    <h6>Количество: ${userInterviews.size()}</h6>
                </c:otherwise>
            </c:choose>
        </div>
        <c:forEach var="item" items="${userInterviews}">
            <div class="col s6 m6 l4">
                <div class="card teal darken-1 z-depth-2">
                    <div class="card-content white-text">
                        <span class="card-title card-title-fix">${item.interview.name}</span>
                        <h6>Дата размещения:<br/> ${item.interview.placementDate}</h6>

                        <p>${item.interview.description}. А может и не для всех. Это как пойдет</p>
                    </div>
                    <div class="card-action">
                        <a href="/user-interviews/${item.interview.id}"
                           class="waves-effect waves-light grey lighten-2 black-text btn">Перейти к анкете</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div id="chart_div"></div>
    <div id="chart_div1" style="width: 900px; height: 500px;"></div>
</main>
<%@include file="fragments/footer.jsp" %>
<%@include file="fragments/js_imports.html" %>
</body>
</html>
