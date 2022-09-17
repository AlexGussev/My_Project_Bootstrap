function del(userId) {
    fetch(`http://localhost:8080/admin/${userId}`)
        .then(response => response.json())
        .then(user => {
            $('#userIdDelete').attr('value', `${user.id}`)
            $('#userNameDelete').attr('value', `${user.name}`)
            $('#userLastNameDelete').attr('value', `${user.lastName}`)
            $('#userAgeDelete').attr('value', `${user.age}`)
            $('#userEmailDelete').attr('value', `${user.email}`)
            $('#userLoginDelete').attr('value', `${user.username}`)
            $('#userPasswordDelete').attr('value', `${user.password}`)
            $('#userRoleDelete').attr('value', `${user.roles.map(role => " " + role.name.substring(5))}`)
            $('#btnDelete').attr('onclick', `deleteUser(${user.id})`)
        })
}

function deleteUser(userId){
    console.log('deleteUser activated')
    fetch(`http://localhost:8080/admin/${userId}`,{
        method: 'DELETE'
    }).then(showAllUsers)
}