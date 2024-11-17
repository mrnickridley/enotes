const homeuser = localStorage.getItem('User_Name');
const userid = localStorage.getItem('User_NameId');
const submitB = document.getElementById("submitExpense");

document.getElementById("homeusername").textContent = homeuser;
document.getElementById("user").textContent = userid;

function getUserExpenses(){

fetch('http://localhost:8097/expenses/' + userid)
.then(response => {
    console.log("This is the response object:", response);
    return response.json(); // or response.text() if the response is not JSON
})
.then(expenses => {

    // Sort expenses by date in ascending order
    //".sort() method lists numerical data, or numbers, in numerical order, increasing or decreasing"
    /** "new Date() method can read integer date data and transform it into a date format." */
        //"new Date(b.expenseDate).getMonth()" returns month index (0-11)
   // expenses.sort((a, b) => new Date(a.expenseDate) - new Date(b.expenseDate));

    // Get the most recent expense date (last item in the sorted list)

    let mostRecentDate = null;
    if (expenses.length > 0) {
        mostRecentDate = expenses[expenses.length - 1].expenseDate;
        document.getElementById("today").textContent = mostRecentDate;
        document.getElementById("expenseDate").value = mostRecentDate;
    }

    // Get the tbody element where data will be inserted
    const tableBody = document.getElementById("tableData");

     // Clear any existing content inside the tbody (for example, if this function is called multiple times)
    tableBody.innerHTML="";


     // Loop through the expenses and create table rows dynamically
    expenses.forEach((expense) => {

    function deleteExpense(){
            const idCell = expense.id;
    
            fetch('http://localhost:8097/expenses/deleteexpense/' + idCell, {
                method: 'DELETE',
                headers: {
                'Content-Type': 'application/json'
                },
            })
            .then(response => response.json())
            .then(data => {
                console.log(data)
                getUserExpenses();//refresh table after deletion
            }) // Manipulate the data retrieved back, if we want to do something with it
            .catch(err => console.log(err)) // Do something with the error
        }

    // Create a new table row
    const row = document.createElement("tr");

    // Create cells for date, description, price, and delete button
    const iDCell = document.createElement("td");
    const dateCell = document.createElement("td");
    const descriptionCell = document.createElement("td");
    const priceCell = document.createElement("td");
    const deleteCell = document.createElement("td");




    // Set the text content for each cell
    iDCell.textContent =  expense.id;
    dateCell.textContent = expense.expenseDate;
    descriptionCell.textContent = expense.description;
    priceCell.textContent = expense.price.toFixed(2); //Format price to two decimal places



    const deleteButton = document.createElement("button");
    deleteButton.textContent = "Delete";
    // Add a delete button to the delete cell

    deleteCell.appendChild(deleteButton);

    deleteButton.onclick = function(){
        deleteExpense();
        row.remove(expense.id);
    };


    row.appendChild(iDCell);
    row.appendChild(dateCell);
    row.appendChild(descriptionCell);
    row.appendChild(priceCell);
    row.appendChild(deleteCell);
    
   // row.appendChild(deleteCell);

    tableBody.appendChild(row);
    });

    // Update total in localStorage and display it
    //The .reduce() method takes a function as an argument, and this function is applied to each element in the array to accumulate a single result.

    // Calculate total for expenses matching today's date
    const totalPerNavDate = expenses.reduce((sum, expense) => {
        if(expense.expenseDate === mostRecentDate){
            return sum + parseFloat(expense.price);
        }
        return sum;
    },0);

    localStorage.setItem('ePrice', totalPerNavDate.toFixed(2));
    document.getElementById("totalExpense").textContent = totalPerNavDate.toFixed(2);
})
.catch(error => {
    console.error("There was an error with the fetch operation:", error);
});
}

function saveExpenses(){
    let dateOfExpense = document.getElementById("expenseDate").value;
    let descript = document.getElementById("description").value;
    let expenseprice = document.getElementById("price").value;

    let expenseResponse = fetch('http://localhost:8097/expenses/newexpense', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            expenseDate: dateOfExpense,
            description: descript,
            price: expenseprice,
            user: {
                id: userid}
        })
    })
    .then(response => response.json())
    .then(data => {
        console.log("Expense saved:", data);
        reloadPage(); // Refresh the page after saving the expense
    })

}

function reloadPage(){
    location.reload();
}

submitB.onclick = function() {
    saveExpenses(); // Save expenses and then reload page
};

getUserExpenses();
//submitB.addEventListener('click', saveExpenses);



