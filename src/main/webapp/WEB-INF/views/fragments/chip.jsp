<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="chip-wrapper">
    <c:if test="${empty chip || (chip eq true)}">
        <div class="chip white-text teal">
            Здравствуйте, ${user.employee.initials}
            <span class="hide-on-small-and-down">,
                Вы вошли под правами ${user.role.name eq 'ROLE_EDITOR' ? 'редактора' : 'респондента'}.
            </span>
            <i class="material-icons" id="hide-chip-btn">close</i>
        </div>
    </c:if>
</div>