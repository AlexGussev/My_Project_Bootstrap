showAllUsers()

function showAllUsers() {
    $('#tableUsers').empty()
    fetch('http://localhost:8080/allUsers')
        .then(response => response.json())
        .then(result => {
            for (let user of result) {
                let temp = `<tr id="row-${user.id}">
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${user.username}</td>
                    <td>${user.roles.map(role => " " + role.name.substring(5))}</td>
                    <td>
                        <button class="btn btn-info" onclick=edit(${user.id}) type="button" data-toggle="modal" data-target=#userEdit>Edit</button>
                    </td>
                    <td>
                        <button class="btn btn-danger" onclick="del(${user.id})" type="button" data-toggle="modal" data-target=#userDelete>Delete</button>
                    </td>
                </tr>`
                $('#tableUsers').append(temp)
            }
        })
}