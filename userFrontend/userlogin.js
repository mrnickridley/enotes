const submituser = document.getElementById("submitLogin");

function getuser(){
    let User_Name = document.getElementById("username").value;

    fetch('http://localhost:8097/signup/finduser/' + User_Name)
    .then(response => {
        console.log("This is the response object:", response);
        return response.json(); // or response.text() if the response is not JSON
    })
    .then(data => {
        console.log("This is the response data:", data);

         if (data != null) {  // Assuming `getuser` is a property in the response JSON
            localStorage.setItem('User_Name', User_Name);

            fetch('http://localhost:8097/signup/' + User_Name)
            .then(response => {
                console.log("This is the response object:", response);
                return response.json(); // or response.text() if the response is not JSON
            })
            .then(idData => {
                console.log("This is the user ID data:", idData);

                localStorage.setItem('User_NameId', idData);

                window.location.href = './expensesHome.html'
            })
            } else {
                console.log('User not found or other condition');
            }

    })
    .catch(error => {
        console.error("There was an error with the fetch operation:", error);
    });

}





submituser.addEventListener('click', getuser);