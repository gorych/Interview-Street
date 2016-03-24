;(function () {

    var operationErrMsg = "Данную операцию выполнить невозможно";

    var $typeSelect = $("#type");
    var $postSelect = $("#posts");
    var $subSelect = $("#subdivisions");

    /*Constructor for interview*/
    function Interview() {
        this.id = null;
        this.name = $("#name").val();
        this.description = $("#description").val();
        this.goal = $("#goal").val();
        this.audience = $("#audience").val();
        this.isHide = true;
        this.endDate = $("#end-date").val();
        this.type = {
            id: $typeSelect.val()
        };
    }

    //region Events

    $('.modal-trigger').leanModal({
        dismissible: false,
        ready: function () {
            $(".click-to-toggle").removeClass("active");
        },
        complete: function () {
            clearForm("#add-interview-form");
        }
    });

    $postSelect.change(function () {
        toggleSelectValidateClass(this);
    });

    $('.lock-btn').each(function () {
        addHideInterviewListener(this, $(this).attr("data-interview-id"));
    });

    $('.delete-btn').each(function () {
        addDeleteInterviewListener(this);
    });

    $('.edit-interview-btn').each(function () {
        addEditInterviewListener(this, $(this).attr("data-interview-id"));
    });

    $("#hide-chip-btn").click(function () {
        $.ajax({
            url: "/hide-chip",
            method: 'GET'
        }).fail(function () {
            Materialize.toast(operationErrMsg);
        });
    });

    $typeSelect.change(function () {
        var interviewType = $(this).val();

        /*If type is open*/
        if (interviewType == 1) {
            resetAddFormSelects();
        } else {
            rebuildAddForm();
        }
    });

    $("#add-interview-form").find("input.validate").blur(function () {
        toggleInputValidateClass(this);
    });

    //endregion

    addListenerToDeleteBtn();
    addListenerToSaveInterviewBtn();
    addListenerToSubdivisionSelect();

    //region Listeners

    function addListenerToSaveInterviewBtn() {
        $("#save-interview").click(function () {
            if (!isValidationForm()) {
                return;
            }

            var interview = new Interview();
            var postIds = $postSelect.val();

            var data = JSON.stringify([interview, postIds]);
            $.ajax({
                url: "/create-interview",
                data: data,
                type: "POST"
            }).done(function (interviewId) {
                interview.id = interviewId;
                buildNewCard(interview);

                $("#add-edit-interview-modal").closeModal();
            }).fail(function () {
                Materialize.toast(operationErrMsg);
            });
        });
    }

    function addHideInterviewListener(btn, interviewId) {
        $(btn).click(function () {
            $.ajax({
                url: "/hide-interview/" + interviewId,
                method: 'GET'
            }).done(function (response) {
                if (response == "success") {
                    var $icon = $(this).find("i");
                    var lock = ($icon.text() == "lock");

                    var msg;
                    if (lock) {
                        $icon.html("lock_open");
                        msg = "Анкета открыта для прохождения";
                    } else {
                        $icon.html("lock");
                        msg = "Анкета закрыта для прохождения";
                    }

                    $icon.attr("title", msg);
                    Materialize.toast(msg);
                }
            }).fail(function () {
                Materialize.toast(operationErrMsg);
            });
        });
    }

    function addDeleteInterviewListener(button) {
        $(button).click(function () {
            var interviewId = this.getAttribute("data-interview-id");
            var submitBtn = $("#submit-delete-btn");
            var card = $(this).parent(".card");

            $(submitBtn).attr("id", interviewId);
            submitBtn.parentNode = card;

            $("#delete-interview-modal").openModal();
        });
    }

    function addEditInterviewListener(btn, interviewId) {
        $(btn).click(function () {
            $.ajax({
                url: '/edit-interview',
                method: 'GET',
                data: {"interviewId": interviewId}
            }).done(function (response) {
                var data = JSON.parse(response);

                var postValues = data.posts;
                var subdivisionValues = data.subdivisions;
                var interview = data.interview;

                $.ajax({
                    url: '/load-posts',
                    method: 'GET',
                    data: {"data": JSON.stringify(subdivisionValues)}
                }).done(function (response) {
                    var posts = JSON.parse(response);
                    if (response.length > 0) {
                        fillPostSelect($postSelect, posts);

                        $postSelect.material_select();
                        $postSelect.val(postValues).material_select();
                    }
                });

                $('#name').addClass("active").val(interview.name);
                $('#description').addClass("active").val(interview.description);

                $typeSelect.val(interview.type.id).material_select();
                $subSelect.val(subdivisionValues).material_select();

                $("#add-edit-interview-modal").openModal();
            });
        });
    }

    function addListenerToDeleteBtn() {
        $("#submit-delete-btn").click(function () {
            $.ajax({
                url: "/delete-interview",
                method: 'GET',
                data: {data: JSON.stringify({id: $(this).attr("id")})}
            }).done(function (response) {
                if (response === "success") {

                    $(this).parent().remove();
                    $("#delete-interview-modal").closeModal();

                    Materialize.toast("Анкета успешно удалена");
                }
            }).fail(function () {
                Materialize.toast(operationErrMsg);
            });
        });
    }

    function addListenerToSubdivisionSelect() {
        $subSelect.change(function () {
            var values = $(this).val();
            $.ajax({
                url: '/load-posts',
                method: 'POST',
                data: JSON.stringify(values)
            }).done(function (response) {
                var data = JSON.parse(response);
                if (response.length > 0) {
                    fillPostSelect($postSelect, data);

                    toggleSelectValidateClass($subSelect);
                    toggleSelectValidateClass($postSelect);
                }
            }).fail(function () {
                Materialize.toast(operationErrMsg);
            });
        });
    }

    //endregion

    //region Helper functions
    function getFormattedDate(date) {
        var month = date.getMonth() + 1;
        return date.getFullYear() + "-" + (month < 10 ? "0" : "") + month + "-" + date.getDate();
    }

    function buildNewCard(interview) {
        var $card = $("#card-template").clone();

        var $goal = $card.find(".goal");
        var $audience = $card.find(".audience");
        var $visibilityIcon = $card.find(".visibility-icon");

        $card.find(".card-title-wrapper").text(interview.name);
        $card.find(".placement-date").text(getFormattedDate(new Date()));
        $card.find(".end-date").text(interview.endDate);
        $card.find(".description").text(interview.description);

        /*if interview type is closed*/
        if (interview.type.id > 1) {
            $goal.text(interview.goal);
            $audience.text(interview.audience);
            $visibilityIcon.html("visibility_off");
        } else {
            $goal.addClass("hide");
            $audience.addClass("hide");
            $visibilityIcon.html("visibility");
        }

        $card.removeClass("hide").removeAttr("id");

        $card.find("[data-interview-id]").each(function () {
            $(this).attr("data-interview-id", interview.id);
        });

        $(".card-container").append($card);
    }

    function clearForm() {
        $(".invalid").removeClass("invalid");
        $("label[for='end-date']").removeClass("active");

        resetAddFormSelects();

        $("#add-interview-form")[0].reset();
    }

    function resetAddFormSelects() {
        $("#goal, #audience").remove();

        $("label[for='goal']").replaceWith("<label for='subdivisions'>Подразделения</label>");
        $("label[for='audience']").replaceWith("<label for='posts'>Должности</label>");
        $("label[for='type']").removeClass("info-badge");

        $("#posts, #subdivisions")
            .find("option:selected").removeAttr("selected");

        $subSelect.material_select();
        $postSelect.material_select();
    }

    function rebuildAddForm() {

        $subSelect.material_select('destroy');
        $postSelect.material_select('destroy');

        $subSelect.next("label").replaceWith("<label for='goal'>Цель опроса</label>");
        $postSelect.next("label").replaceWith("<label for='audience'>Целевая аудитория</label>");

        $subSelect.before("<input id='goal' class='validate' type='text' length='65'/>");
        $postSelect.before("<input id='audience' class='validate' type='text' length='25'/>");

        $('input#goal, input#audience').characterCounter();

        $("label[for='type']").addClass("info-badge");
    }

    function fillPostSelect(postSelect, data) {
        $(postSelect).find('option').remove().end();

        $.each(data, function (index, entry) {
            $.each(entry, function (j, post) {
                var option = document.createElement("option");
                if (index == 0) {
                    $(postSelect).append("<option value='-1' disabled selected>Выберите должности</option>");
                }
                option.setAttribute("value", post.id);
                option.textContent = post.name;

                $(postSelect).append(option);
            });
        });

        $(postSelect).material_select();
    }

    //endregion

    //region Validation

    function isValidationForm() {
        var $form = $("#add-interview-form");
        $form.find(".validate").each(function (i, el) {
            if ($(el).is('select')) {
                toggleSelectValidateClass(el);
            } else {
                toggleInputValidateClass(el);
            }
        });

        return ($form.find("input.invalid").length < 1);
    }

    function toggleInputValidateClass(input) {
        if (!input.value || input.validity.patternMismatch) {
            $(input).addClass("invalid");
        } else {
            $(input)
                .removeClass("invalid").addClass("valid");
        }
    }

    function toggleSelectValidateClass(selector) {
        var hiddenInput = $(selector).prevAll("input.select-dropdown");
        var value = $(selector).val();

        if (!value || value < 1) {
            $(hiddenInput).addClass("invalid invalid-select");
        } else {
            $(hiddenInput)
                .removeClass("invalid invalid-select")
                .addClass("valid valid-select");
        }
    }

    //endregion

}());