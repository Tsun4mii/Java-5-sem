async function deleteComputerStuffByNameA(data, token) {
    return await fetch("/admin/deleteScooterByNameA", {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)

    });
}
async function deleteComputerStuffByNameU(data, token) {
    return await fetch("/user/deleteComputerStuffByNameU", {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)

    });
}

async function updateComputerStuff(data, token) {
    return await fetch("/admin/updateComputerStuff", {
        method: 'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)

    });
}

async function userGetComputerStuffByName(name, token) {
    return await fetch(`/user/userGetComputerStuffByName/${name}`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        }

    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}

async function adminGetComputerStuffByName(name, token) {
    return await fetch(`/admin/adminGetComputerStuffByName/${name}`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        }

    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}
async function isComputerStuffExistByName(data, token) {
    return await fetch("/admin/isComputerStuffExistByName",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function createComputerStuff(data, token) {
    return await fetch("/admin/createScooter",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function getAllCompsForAdmin(token) {
    return await fetch(`/admin/getAllCompsForAdmin`, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        }
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}
async function getAllCompsForUser(token) {
    return await fetch(`/user/getAllCompsForUser`, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        }
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}