var start = 0;
var showCount = 5;
var end = 5;


function isTokenExist() {
    return localStorage.getItem('token') != null;
}

function logOut() {
    localStorage.removeItem('token');
    window.location.replace(window.location.origin);
}

async function isAuth() {
    if (isTokenExist()) {
        let token = localStorage.getItem('token');
        await authorizedUser(token);
        return true;
    }
    return false;
}

async function getUser() {
    let token = localStorage.getItem('token');
    let res = await getUserByToken(token);
    let body = await res.text();
    return JSON.parse(body);
}

function validateLoginPass(login, password , email) {
    if (!(login.length >= 4 && login.length <= 16)) {
        return "not correct login";
    }
    if (!(password.length >= 4 && password.length <= 16)) {
        return "not correct password";
    }
    if (!email.length >= 4) {
        return "not correct email";
    }

    return true;
}

async function reg() {
    let login = document.getElementById("login").value;
    let password = document.getElementById("password").value;
    let email = document.getElementById("email").value;
    let mes = document.getElementById("message");
    let result = validateLoginPass(login, password , email);
    if (result === true) {
        let data = {login: login, password: password,email:email};
        let res = await regUser(data);
        if (res.ok) {
            mes.innerHTML = "Check your email address to continue your registration"
            //window.location.replace(window.location.origin);
        } else {
            mes.innerHTML = "this user already exist";
        }

    } else {
        mes.innerHTML = result;
    }
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
        let body = await result.text();
        let info = JSON.parse(body);
        mes.innerHTML = info['message'];
    }
}

async function generateListOfUsers(result) {
    result.innerText = '';
    let data = await getAllUsers();

    let showed = data.length;
    if (showed > end) {
        showed = end
    }
    for (let i = start; i < showed; i++) {
        let divP = div();
        let text = p('login:' + data[i].login + ' role:' + data[i].userRole.name);
        divP.appendChild(text);
        result.appendChild(divP);
    }
}

function genLogReg() {

    let divLogReg = document.querySelector('.logReg');
    let aDivLog = div();
    let aDivReg = div();
    let aLogin = a('/loginin', 'Login');
    let aReg = a('/reg', 'Registration');
    aDivLog.appendChild(aLogin);
    aDivReg.appendChild(aReg);
    divLogReg.appendChild(aDivLog);
    divLogReg.appendChild(aDivReg);

}

function genLogout() {
    let divLogOut = document.querySelector('.logOut');
    let logoutBtn = button(logOut, 'Logout');
    divLogOut.appendChild(logoutBtn)
}

async function genNext() {
    let div = document.querySelector('.nextPrev');
    let logoutBtn = button(await next, 'Next');
    div.appendChild(logoutBtn)
}

function genPrev() {
    let div = document.querySelector('.nextPrev');
    let logoutBtn = button(prev, 'Prev');
    div.appendChild(logoutBtn)
}

async function next() {
    let result = document.querySelector('.results');
    await fetch("/users", {
        method: "POST",
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        if (end < data.length) {
            start += showCount;
            end += showCount;
        }
        generateListOfUsers(result);
    });
}

async function prev() {
    let result = document.querySelector('.results');
    if (start - showCount >= 0) {
        start -= showCount;
        end -= showCount;
    }
    await generateListOfUsers(result);
}