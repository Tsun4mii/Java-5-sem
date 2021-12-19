async function genCard() {
    let token = localStorage.getItem('token');
    let info = document.querySelector(".personalInfo");
    info.innerHTML='';


    let name = input('text', 'name', 'Name', '');

    let surname = input('text', 'surname', 'Surname', 'surnameValue');


    info.appendChild(name);
    info.appendChild(surname);

}

function validateCard() {
    let nameL = document.getElementById('name').value.length;
    let surnameL = document.getElementById('surname').value.length;


    if (!(nameL >= 2 && nameL <= 16)) {
        return false;
    }
    if (!(surnameL >= 4 && surnameL <= 16)) {
        return false;
    }

    return true;

}

async function genUserCreateButton(user , Scooter) {
    let token = localStorage.getItem('token');
    let userData = await getUserByToken(token);
    let text = await userData.text();
    let userTextData = JSON.parse(text);
    let isNotExist = await isUserRentExistByUserId({id: userTextData['id']}, token);
    let errMes = document.getElementById('errMes');

    if (validateCard() && await isAuth() && isNotExist.ok) {
        let name = document.getElementById('name').value;
        let surname = document.getElementById('surname').value;

        let data = {
            user:user,
            name: name,
            surname: surname,
            scooter:Scooter
        };
        await createUserRent(data, token);

        errMes.innerHTML = 'Created';
        await genUserInfo();
    } else {
        errMes.innerHTML = 'Not all fields are correct or user already got some rent';
    }

}

async function genCardCreate(user, comp) {
    let create = document.querySelector('.create');
    create.innerHTML='';
    let createButton = buttonWithParams('Create');
    createButton.onclick = async () => {
        await genUserCreateButton(user,comp);
    };
    createButton.id = 'patientCreateButton';
    create.appendChild(createButton);
}
async function getCertainCompUser(listProjectElement) {
    let token = localStorage.getItem('token');

    let userData = await getUserByToken(token);
    let text = await userData.text();
    let user = JSON.parse(text);

    let comp = await userGetScooterByName(listProjectElement['name'], token);

    await genCard();
    await genCardCreate(user,comp);
}

async function genListOfCompStuffForUser() {
    let token = localStorage.getItem('token');
    let someList = document.querySelector('.someList');
    someList.innerHTML = '';
    let listProject = await getAllCompsForUser(token);

    for (let i = 0; i < listProject.length; i++) {
        let genDiv = div();
        let genP1 = p(listProject[i]['name'] + ' ' + listProject[i]['description'] + ' ' + listProject[i]['cost']);
        let genBut = buttonWithParams('get');

        genBut.onclick = async () => {
            await getCertainCompUser(listProject[i]);
        };
        genDiv.appendChild(genP1);
        genDiv.appendChild(genBut);
        someList.appendChild(genDiv);
    }
}
async function genUserInfo() {
    let token = localStorage.getItem('token');
    let info = document.querySelector('.neededInfo');
    info.innerHTML='';
    let table = document.createElement('table');
    table.setAttribute('class', 'table');

    let userData = await getUserByToken(token);
    let text = await userData.text();
    let textUserData = JSON.parse(text);
    console.log(textUserData);

    let userRents = await getAllUserRentByUserId(textUserData['id'], token);

    for (let i = userRents.length - 1; i >= 0; i--) {
        if (i === userRents.length - 1) {
            let tr = document.createElement('tr');
            let th1 = document.createElement('th');
            th1.innerHTML = 'Name';
            let th2 = document.createElement('th');
            th2.innerHTML = 'Surname';
            let th3 = document.createElement('th');
            th3.innerHTML = 'Product name';
            let th4 = document.createElement('th');
            th4.innerHTML = 'Expiration date';
            let th5 = document.createElement('th');
            th5.innerHTML = 'Expired?';
            let th6 = document.createElement('th');
            th6.innerHTML = 'Delete';
            tr.appendChild(th1);
            tr.appendChild(th2);
            tr.appendChild(th3);
            tr.appendChild(th4);
            tr.appendChild(th5);
            tr.appendChild(th6);
            table.appendChild(tr);
        }
        let tr = document.createElement('tr');
        console.log(userRents)
        for (let y = 0; y < 6; y++) {
            let th = document.createElement('th');
            switch (y) {
                case 0: {
                    th.innerHTML = userRents[i]['userName'];
                    break;
                }
                case 1: {
                    th.innerHTML = userRents[i]['userSurname'];
                    break;
                }
                case 2: {
                    th.innerHTML = userRents[i]['scooter']['name'];
                    break;
                }
                case 3: {
                    th.innerHTML = userRents[i]['scooter']['expirationDate'];
                    break;
                }
                case 4: {
                    th.innerHTML = userRents[i]['rent'];
                    break;
                }
                case 5: {
                    let genBut = buttonWithParams('Delete');

                    genBut.onclick = async () => {
                        async function deleteComp(userRentElement) {
                            let token = localStorage.getItem('token');
                            console.log(userRentElement['userName']);
                            await deleteScooterByNameU({name:userRentElement['userName']},token);
                            await genUserInfo();

                        }
                        await deleteComp(userRents[i]);
                    };
                    th.appendChild(genBut);

                    break;
                }
            }
            tr.appendChild(th);
        }

        table.appendChild(tr);
    }
    info.appendChild(table);

}