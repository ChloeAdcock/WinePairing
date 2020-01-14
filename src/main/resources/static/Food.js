const PORT = 8081;

let allFoods = [];
function getAllFoods() {
    axios.get("http://localhost:" + PORT + "/food/getFoods")
    .then ((response) => {
        allFoods = response.data;
        console.log("Get all foods request: " + allFoods);
        showAllFoods();
    }).catch ((error) => {
        console.error(error);
    })
}

const foodList = document.getElementById("foods");
const deleteFoodButton = document.createElement("button");

function showAllFoods() {

    for (let food of allFoods) {
    
        const foodCard = document.createElement("div");
        foodCard.className = "card w-30 m-3";

        deleteFoodButton.className = "btn btn-default btn-sm far fa-trash-alt";
        deleteFoodButton.id = food.id;
        foodCard.appendChild(deleteFoodButton);
        deleteFoodButton.onclick = deleteFood;
    
        const cardBody = document.createElement("div");
        cardBody.className = "card-body";
        foodCard.appendChild(cardBody);
    
        const foodName = document.createElement("h5");
        foodName.className = "card-title";
        foodName.innerText = (food.name).toUpperCase();
        cardBody.appendChild(foodName);
    
        const foodDetails = document.createElement("p");
        foodDetails.className = "card-details"
        let name = food.wine ?  food.wine.name :  'None';
        foodDetails.innerText = "Allergens \n" + food.allergens + "\n Description \n" + food.description
        + "\n Wine pairing \n" + name;
        cardBody.appendChild(foodDetails);
    
        foodList.append(foodCard);
    }
    
}

function deleteFood() {
    axios.delete("http://localhost:" + PORT + "/food/deleteFood/" + deleteFoodButton.id)
    .then ((response) => {
        console.log("Delete food with ID of " + deleteFoodButton.id + ": " + response.data);
        location.reload();
    }).catch ((error) => {
        console.error(error);
    })
}


function likeFood() {
    axios.put("http://localhost:8080/winepairingapp/updateFood")
    .then ((response) => {
        addLike(response.data);
    }).catch ((error) => {
        console.error(error);
    })
}

function addLike() {
    
}

