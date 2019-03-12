function register(){
    let credentinals = {
        login: document.getElementById('username').value,
        password: document.getElementById('password').value
    };
    let csrfToken = $("meta[name='_csrf']").attr("content");
    
    $.ajax({
        type: 'PUT',
        url: "user/register",
        data: JSON.stringify(credentinals),
        contentType: 'application/json;charset=UTF-8',
        headers: {"X-CSRF-Token":csrfToken},
        success: function (data, textStatus, jqXHR) {
            location.href = './login';
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.status == 409)
                alert('Такой пользователь уже зарегистрирован');
            else
                alert('Неизвестная ошибка');
        }
    });
}
