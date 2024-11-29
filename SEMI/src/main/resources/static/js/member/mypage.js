const editBtn = document.querySelector("#edit-form input[type=button]");

editBtn.addEventListener("click" , function(evt){
    
    const nickTag = document.querySelector("#edit-form input[name=nick]");
    const pwdTag = document.querySelector("#edit-form input[name=pwd]");

    nickTag.removeAttribute("disabled");
    pwdTag.removeAttribute("disabled");

    const newBtn = document.createElement("input");
    newBtn.setAttribute("type", "submit");
    newBtn.setAttribute("value","수정");

    const formTag = document.querySelector("#edit-form");
    formTag.appendChild(newBtn);

    editBtn.remove();

});