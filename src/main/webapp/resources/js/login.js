function login(){
    let credentinals = {
      username:  document.getElementById("username").value,
      password:  document.getElementById("password").value,
      _csrf: document.getElementById("token").value
    };
    $.ajax({
        type: 'POST',
        data: credentinals,
        success: function (data, textStatus, jqXHR) {
            location.href = './';
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.status == 401)
                alert('Нет такого пользователя, с таким паролем, ' 
                    + 'Вы можете зарегистрироваться');
            else
                alert('Неизвестная ошибка!');
        }
    });
}
