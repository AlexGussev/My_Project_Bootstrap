fetch('http://localhost:8080/userInfo')
    .then(response => response.json())
    .then(user => {
        $('#loginInfo').append(`${user.username}`)
        $('#rolesInfo').append(`${user.roles.map(role => " " + role.name.substring(5))}`)
        let temp = `<tr>
                    <td>${user.name}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${user.username}</td>
                    <td>${user.roles.map(role => " " + role.name.substring(5))}</td>
                </tr>`
        $('#userInfo').append(temp)
    })