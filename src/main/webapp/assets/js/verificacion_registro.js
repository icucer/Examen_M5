// assets/js/register.js

function handleRegistrationResponse() {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "register-servlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 409) {
                // Mostrar el pop-up con el mensaje de error
                alert(xhr.responseText);
                // Redirigir a la página de registro después de hacer clic en OK
                window.location.href = "registro.jsp";
            } else if (xhr.status === 200) {
                // Redirigir a la página de login si el registro fue exitoso
                window.location.href = "login.jsp";
            }
        }
    };

    // Recoger los datos del formulario
    const email = document.getElementById("email").value;
    const nickname = document.getElementById("nickname").value;
    const name = document.getElementById("name").value;
    const password = document.getElementById("password").value;
    const weight = document.getElementById("weight").value;
    const address = document.getElementById("address").value;
    const houseNumber = document.getElementById("house_number").value;

    // Enviar los datos al servidor
    const params = `email=${encodeURIComponent(email)}&nickname=${encodeURIComponent(nickname)}&name=${encodeURIComponent(name)}&password=${encodeURIComponent(password)}&weight=${encodeURIComponent(weight)}&address=${encodeURIComponent(address)}&house_number=${encodeURIComponent(houseNumber)}`;
    xhr.send(params);
}
