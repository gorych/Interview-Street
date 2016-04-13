<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form id="add-interview-form" class="hide col s12" method="POST" action="<c:url value="/interview/form"/>">
    <input type="hidden" name="type" value=""/>
    <div class="row valign-wrapper left-align">
        <div class="col l9 m9">
            <h4 id="title"></h4>
        </div>
        <div class="col l3 m3">
            <i class="interview-icon medium material-icons teal-text right">perm_identity</i>
        </div>
    </div>

    <div class="row">
        <div class="input-field col s12">
            <input required name="name" class="validate" type="text"
                   placeholder="Минимум 5 символов"
                   pattern=".{5,}" length="60"/>
            <label class="active">Наименование</label>
        </div>

        <div class="hide input-field col s12 m6 l6">
            <select id="subdivisions" name="subdivisions" class="validate" multiple>
                <option value="-1" disabled selected>Выберите подразделения</option>
                <c:forEach var="sub" items="${subdivisions}">
                    <option value="${sub.id}">${sub.name}</option>
                </c:forEach>
            </select>
            <label for="subdivisions">Подразделение</label>
        </div>

        <div class="hide input-field col s12 m6 l6">
            <select id="posts" class="validate" multiple>
                <option value="-1" disabled selected>Сначала выберите подразделения</option>
            </select>
            <label for="posts">Должности</label>
        </div>

        <div class="hide input-field col s12 m6 l6">
            <input id='goal' class='validate' type='text' length='65' placeholder='Максимум 65 символов'/>
            <label class="active">Цель опроса</label>
        </div>
        <div class="hide input-field col s12 m6 l6">
            <input id='audience' class='validate' type='text' length='25' placeholder='Максимум 25 символов'/>
            <label class="active">Целевая аудитория</label>
        </div>

        <div class="input-field col s12 m12 l4">
            <input required id="end-date" name="end-date" class="datepicker" type="date"
                   pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"/>
            <label for="end-date">Дата окончания опроса</label>
        </div>

        <div class="input-field col s12 m12 l8">
            <input required name="description" class="validate" type="text"
                   placeholder="Минимум 3 символа" pattern=".{3,}"
                   length="70"/>
            <label class="active">Описание</label>
        </div>
    </div>

    <div class="row valign-wrapper">
        <div class="col s1 left-align hide-on-small-only">
            <a id="go-back" href="<c:url value="/interview/form"/>"><i
                    class="medium material-icons deep-orange-text text-lighten-2" title="Назад">reply</i></a>
        </div>
        <div class="col s11 right-align">
            <a href="<c:url value="/editor/interview-list"/>" class="waves-effect waves-light btn-large red accent-2">Отмена</a>
            <a id="submit" class="waves-effect waves-light btn-large accent-2"><i
                    class="material-icons right">send</i>Продолжить</a>
        </div>
    </div>
</form>
