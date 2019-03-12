let csrfToken = $("meta[name='_csrf']").attr("content");

function loadNotes(){
    let str = document.getElementById('search').value;
    $.ajax({
        type: "GET",
        url: "notes/get?message="+str+"&rand="+Math.random(),
        contentType: 'application/json;charset=UTF-8',
        headers: {"X-CSRF-Token":csrfToken},
        success: function (data, textStatus, jqXHR) {
            insertNotes(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR.responseText);
            console.log(textStatus);
            console.log(errorThrown);
            alert("Невозможно загрузить информацию")
        }
    });
}

function insertNotes(notes){
    let content = "";
    if (notes.length == 0)
        content = "<tbody><tr><td>Записей не найдено</td></tr></tbody>";
    else{
        content+="<thead>";
        content+="<tr>";
        content+="<th></th>";
        content+="<th>";
        content+="Содержимое";
        content+="</th>";
        content+="<th>";
        content+="</th>";
        content+="</tr>";
        content+="</thead>";
        content+="<tbody>";
        for (var i = 0; i < notes.length; i++) {
            content+="<tr>";
            content+="<td>";
            content+="<img src='notes/img/" 
                    + notes[i].id
                    + ".svg'>";
            content+="</td>";
            content+="<td" 
                    + " style='cursor:pointer;'" 
                    + "onclick=\"location.href='notes/"
                    + notes[i].id
                    + "';\""
                    + ">";
            content+=notes[i].message.replace(/\n/g,"<br>");
            content+="</td>";
            content+="<td style='width:100px;'>";
            content+="<button " 
                    + "style='width:100%;' " 
                    + "class='btn btn-link' " 
                    + "onclick='removeNote(" 
                    + notes[i].id 
                    + ");'>Удалить</button>"
            content+="</td>";
            content+="</tr>";
        }
        content+="</tbody>";
    }
    document.getElementById("notesTable").innerHTML = content;
}

function removeNote(id){
    $.ajax({
        type: 'DELETE',
        url:  'notes/delete/'+id,
        async: true,
        headers: {"X-CSRF-Token":csrfToken},
        success: function (data, textStatus, jqXHR) {
            console.log(textStatus);
            loadNotes();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
            console.log(jqXHR.responseText);
            console.log(textStatus);
            console.log(errorThrown);
            alert('Не удалось удалить запись!');
        }
    });
    
}

function createNote(){
    let note = {
        message: document.getElementById('message').value
    };
    $.ajax({
        type: 'PUT',
        url: "notes/create",
        data: JSON.stringify(note),
        contentType: 'application/json;charset=UTF-8',
        headers: {"X-CSRF-Token":csrfToken},
        success: function (data, textStatus, jqXHR) {
            location.href = './';
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR.responseText);
            alert('Не удалось добавить заметку!');
        }
    });
}

