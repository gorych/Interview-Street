<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="fragments/meta.html" %>
    <%@include file="fragments/general-css.jsp" %>
    <link href="<c:url value="/resources/css/respondents.css"/>" rel="stylesheet" type="text/css">

    <title>Interview Street - Список респондентов</title>
</head>
<body>
<%@include file="fragments/header.jsp" %>

<main>
    <div class="row teal">
        <div class="container">
            <div class="col l12 m12 s12">
                <h3 class="white-text">Список респондентов</h3>
                <!-- <ul class="collapsible" data-collapsible="accordion">
                     <li>
                         <div class="collapsible-header"><i class="material-icons">filter_list</i>Сортировка</div>
                         <div class="collapsible-body white">
                             <div class="row">
                                 <form style="padding: 0 1rem">
                                     <div class="col l4 m6 s12">
                                         <fieldset>
                                             <legend>По фамилии</legend>
                                             <input name="surname" type="radio" id="surname-up"/>
                                             <label for="surname-up">От А до Я</label><br/>
                                             <input name="surname" type="radio" id="surname-down"/>
                                             <label for="surname-down">От Я до А</label>
                                         </fieldset>
                                     </div>
                                     <div class="col l4 m6 s12">
                                         <fieldset>
                                             <legend>По дате</legend>
                                             <input name="date" type="radio" id="date-up"/>
                                             <label for="date-up">По убыванию</label><br/>
                                             <input name="date" type="radio" id="date-down"/>
                                             <label for="date-down">По возрастанию</label>
                                         </fieldset>
                                     </div>
                                     <div class="col l4 m6 s12">
                                         <fieldset>
                                             <legend>По статусу</legend>
                                             <input name="status" type="radio" id="passed"/>
                                             <label for="passed">Сначала пройденные</label>
                                             <input name="status" type="radio" id="not-passed"/>
                                             <label for="not-passed">Сначала не пройденные</label>
                                         </fieldset>
                                     </div>
                                 </form>
                             </div>
                         </div>
                     </li>
                 </ul>-->
            </div>
        </div>
    </div>

    <c:if test="${not empty user_interviews}">
        <div class="row container">
            <div class="col l12 m12 s12">

                <h5 class="teal-text">${user_interviews[0].interview.name}</h5>
                <h6>Всего респондентов, чел: ${user_interviews.size()}</h6>
                <h6 class="answers-count">Ответило, чел: 0</h6>

                <table id="sorted-table" class="highlight bordered responsive-table tablesorter">
                    <thead>
                    <tr>
                        <th data-field="fullname">Ф.И.О</th>
                        <th data-field="subdivision">Подразделение</th>
                        <th data-field="status">Статус</th>
                        <th data-field="passing-date">Дата прохождения</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="uInterview" items="${user_interviews}">
                        <tr>
                            <td>${uInterview.user.employee.fullName}</td>
                            <td>${uInterview.user.employee.subdivision.name}</td>
                            <c:choose>
                                <c:when test="${uInterview.passed}">
                                    <td class="passed">Пройдена</td>
                                    <td>${uInterview.formatPassingDate}</td>
                                </c:when>
                                <c:otherwise>
                                    <td class="red-text">Не пройдена</td>
                                    <td>Не определена</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>
</main>

<%@include file="fragments/footer.jsp" %>
<%@include file="fragments/general-js.jsp" %>
<script src="<c:url value="/resources/vendors/jquery/jquery.tablesorter.min.js"/>"></script>
<script src="<c:url value="/resources/js/respondents.js"/>"></script>

</body>
</html>