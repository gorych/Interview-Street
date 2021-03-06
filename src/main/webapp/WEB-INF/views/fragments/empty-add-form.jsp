<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form id="add-interview-form" class="hide col s12" method="POST" action="<c:url value="/interview/form"/>">
    <input type="hidden" name="type" value=""/>
    <div class="row valign-wrapper left-align">
        <div class="col l9 m9 s9">
            <h4 id="title"></h4>
        </div>
        <div class="col l3 m3 s9">
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
                <option value="0" selected disabled>Все подразделения предприятия</option>
                <c:forEach var="sub" items="${subdivisions}">
                    <option value="${sub.id}">${sub.name}</option>
                </c:forEach>
            </select>
            <label for="subdivisions">Подразделение</label>
        </div>

        <div class="hide input-field col s12 m6 l6">
            <select id="posts" class="validate" multiple>
                <option value="0" selected disabled>Сотрудники всех должностей</option>
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

        <div class="col s12 m12 l12 second-passage-col left-align">
            <input type="checkbox" class="filled-in" id="second-passage"/>
            <label for="second-passage">Разрешить повторное прохождение</label>
        </div>
    </div>

    <div class="row valign-wrapper">
        <div class="col m1 l1 left-align hide-on-small-only">
            <a href="<c:url value="/gateway"/>"><i
                    class="medium material-icons brown-text text-lighten-2" title="Назад">reply</i></a>
        </div>
        <div class="col m11 l11 right-align hide-on-small-only">
            <a href="<c:url value="/editor/interview-list"/>" class="waves-effect waves-light btn-large red accent-2">Отмена</a>
            <a class="submit waves-effect waves-light btn-large accent-2"><i
                    class="material-icons right">send</i>Продолжить</a>
        </div>

        <a href="<c:url value="/editor/interview-list"/>"
           class="col s12 hide-on-med-and-up waves-effect waves-light btn-large red accent-2">Отмена</a>
        &nbsp;
        <a class="submit col s12 hide-on-med-and-up waves-effect waves-light btn-large accent-2"><i
                class="material-icons right">send</i>Далее</a>
    </div>
</form>
