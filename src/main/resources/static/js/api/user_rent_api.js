async function deleteByUserIdAndComputerStuffId(data,token) {
    return await fetch("/admin/deleteByUserIdAndComputerStuffId",{
        method :'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function setUserRentFormById(data,token) {
    return await fetch("/admin/setUserRentFormById",{
        method :'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function getAllUserRentByUserId(id, token) {
    return await fetch(`/user/getAllByUserId/${id}`,{
        method :'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },

    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}
async function getAllUserRentByRent(data, token) {
    return await fetch(`/doctor/getAllByRent/${data}`,{
        method :'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },

    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}

async function isUserRentExistByScooterId(data, token) {
    return await fetch("/admin/isUserRentExistByScooterId",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function isUserRentExistByUserId(data, token) {
    return await fetch("/user/isUserRentExistByUserId",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    })
}
async function createUserRent(data, token) {
    return await fetch("/user/createUserRent",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function getAllUserRentByScooterExpirationDateLessThan(data, token) {
    return await fetch("/admin/getAllByScooterExpirationDateLessThan",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}