function addNewUser() {
    let name = $('#userRoleNew').val();
    fetch('http://localhost:8080/admin', {
        method: 'POST',
        body: JSON.stringify({
            name: $('#userNameNew').val(),
            lastName: $('#userLastNameNew').val(),
            age: $('#userAgeNew').val(),
            email: $('#userEmailNew').val(),
            username: $('#userLoginNew').val(),
            password: $('#userPasswordNew').val(),
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