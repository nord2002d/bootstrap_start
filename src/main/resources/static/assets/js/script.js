
$('#modalUpdate').on('show.bs.modal', function (event) {
    let button = $(event.relatedTarget)
    let userId = button.data('userid')
    if (userId) {
        $.get({
            url: 'admin/form/' + userId,
            success: (data) => {
                let modal = $(this)
                modal.find('#user-id-f1').val(data.id)
                modal.find('#user-name-f1').val(data.username)
                modal.find('#user-lastName-f1').val(data.surName)
                modal.find('#age-f1').val(data.age)
                modal.find('#email-f1').val(data.email)
                modal.find('#passNotShow-f1').val(data.password)
                modal.find('#r-f1').val(data.role)
            },
            error: function (xhr, status, error) {
                alert(xhr.responseText);
            }
        });
    }
});


$('#update-button').click(function () {
    let modal = $('#modalUpdate')
    let valuePass = modal.find('#pass-f1').val().length == 0 ? modal.find('#passNotShow-f1').val() : modal.find('#pass-f1').val();
    let user = {
        id: modal.find('#user-id-f1').val(),
        username: modal.find('#user-name-f1').val(),
        surName: modal.find('#user-lastName-f1').val(),
        age: modal.find('#age-f1').val(),
        email: modal.find('#email-f1').val(),
        password: valuePass,
        roles: [modal.find($("#role-f1 :selected")).text()],
    };

    $.ajaxSetup({contentType: "application/json; charset=utf-8"});
    $.ajax({
        url: '/admin/form',
        type: "PATCH",
        data: JSON.stringify(user),
        dataType: "json",
        success: () => {
            $("#tableUser").load(window.location + " #tableUser");
        },
        error: function (xhr, status, error) {
            alert(xhr.responseText);
        }
    });

});

// Модальное окно удаления_____________________________________________________________________________
$('#modalDelete').on('show.bs.modal', function (event) {
    let button = $(event.relatedTarget)
    let userId = button.data('userid')

    if (userId) {
        $.get({
            url: 'admin/form/' + userId,
            success: (data) => {
                let modal = $(this)
                modal.find('#user-id-f2').val(data.id)
                modal.find('#user-name-f2').val(data.username)
                modal.find('#user-lastName-f2').val(data.surName)
                modal.find('#age-f2').val(data.age)
                modal.find('#email-f2').val(data.email)
                modal.find('#pass-f2').val(data.email)
                modal.find('#role-f2').val(data.roles)
            },

        });
    }
})

// Модальное окно добавления_______________________________________________________________________________________


$('#delete-button').click(function () {
    let modal = $('#modalDelete')
    let user = {
        id: modal.find('#user-id-f2').val(),
        username: modal.find('#user-name-f2').val(),
        surName: modal.find('#user-lastName-f2').val(),
        age: modal.find('#age-f2').val(),
        email: modal.find('#email-f2').val(),
        password: modal.find('#pass-f2').val(),
        role: modal.find('#role-f2').val(),
    };

    $.ajaxSetup({contentType: "application/json; charset=utf-8"});
    $.ajax({
        url: '/admin/form',
        type: "DELETE",
        data: JSON.stringify(user),
        dataType: "json",
        success: () => {
            $("#tableUser").load(window.location + " #tableUser");
        },
    });

});


$('#userAdd-button').click(function () {
    let form = $('#nav-profile')

    let user = {
        username: form.find('#user-n-f1').val(),
        surName: form.find('#user-l-f1').val(),
        age: form.find('#a-f1').val(),
        email: form.find('#e-f1').val(),
        password: form.find('#p-f1').val(),
        roles: [$("#r-f1 :selected").text()],

    };

    $.ajaxSetup({contentType: "application/json; charset=utf-8"});
    $.ajax({
        url: '/admin/form',
        type: "POST",
        data: JSON.stringify(user),
        dataType: "json",
        success: () => {
            location.reload();
        },
        error: function (xhr, status, error) {
            alert(xhr.responseText);
        }
    });

});
