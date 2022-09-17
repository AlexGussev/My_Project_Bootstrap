function edit(userId){
    fetch(`http://localhost:8080/admin/${userId}`)
        .then(response => response.json())
        .then(user => {
            $('#userIdEdit').attr('value', `${user.id}`)
            $('#userNameEdit').attr('value', `${user.name}`)
            $('#userLastNameEdit').attr('value', `${user.lastName}`)
            $('#userAgeEdit').attr('value', `${user.age}`)
            $('#userEmailEdit').attr('value', `${user.email}`)
            $('#userLoginEdit').attr('value', `${user.username}`)
            $('#userPasswordEdit').attr('value', `${user.password}`)
            $('#btnEdit').attr('onclick', 'editUser()')
        })
}

function editUser(){
    $('#editUserRole').attr('value', [])
    let name = $('#editUserRole').val();
    fetch('http://localhost:8080/admin',{
        method: 'PUT',
        body: JSON.stringify({
            id: $('#userIdEdit').val(),
            name: $('#userNameEdit').val(),
            lastName: $('#userLastNameEdit').val(),
            age: $('#userAgeEdit').val(),
            email: $('#userEmailEdit').val(),
            username: $('#userLoginEdit').val(),
            password: $('#userPasswordEdit').val(),
            roles:
                [
                    {
                        id: null,
                        name: name[0]
                    },
                    {
                        id: null,
                        name: name[1]
                    }
                ]
        }),
        headers: {'Content-type': 'application/json; charset=UTF-8'},
    }).then(showAllUsers)
}