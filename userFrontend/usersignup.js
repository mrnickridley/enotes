const sub = document.getElementById("submit");

function saveLead(){
    let First_Name = document.getElementById("firstname").value;
    let Last_Name = document.getElementById("lastname").value;
    let User_Name = document.getElementById("username").value;
    let Email = document.getElementById("email").value;

    let response = fetch('http://localhost:8097/signup/saveuser', {
        method: 'POST', 
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            firstname: First_Name,
            lastname: Last_Name,
            username: User_Name,
            email: Email
        })
    })
    .then(response => response.json())
    .then(data => {
        console.log("Expense saved:", data);
        restartPage(); // Refresh the page after saving the expense
    })
    
}

function restartPage(){
    location.reload();
}

sub.addEventListener('click', saveLead);