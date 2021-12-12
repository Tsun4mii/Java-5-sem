async function logUser(data) {
    return await fetch("/login", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    });
}

async function login() {
    let login = document.getElementById("login").value;
    let password = document.getElementById("password").value;
    let mes = document.getElementById("message");
    let data = {login: login, password: password};
    let result = await logUser(data);
    if (result.ok) {
        let body = await result.text();
        let info = JSON.parse(body);
        localStorage.setItem('token', info['token']);
        window.location.replace(window.location.origin);
    } else {
        mes.innerHTML = 'Error occured';
    }
}