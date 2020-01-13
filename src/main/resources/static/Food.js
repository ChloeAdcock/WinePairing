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

function showAllFoods() {

    for (let food of allFoods) {
    
        const foodCard = document.createElement("div");
        foodCard.className = "card w-30 m-3";

        const deleteButton = document.createElement("button");
        deleteButton.className = "btn btn-default btn-sm far fa-trash-alt";
        deleteButton.value = food.id;
        foodCard.appendChild(deleteButton);
    
        const cardBody = document.createElement("div");
        cardBody.className = "card-body";
        foodCard.appendChild(cardBody);

        const likeButton = document.createElement("button");
        likeButton.className = "btn btn-default btn-sm far fa-thumbs-up";
        foodCard.appendChild(likeButton);
    
        const foodName = document.createElement("h5");
        foodName.className = "card-title";
        foodName.innerText = (food.name).toUpperCase();
        cardBody.appendChild(foodName);
    
        const foodDetails = document.createElement("p");
        foodDetails.className = "card-details"
        foodDetails.innerText = "Allergens \n" + food.allergens + "\n Description \n" + food.description
        + "\n Wine pairing \n" + food.wine.name;
        cardBody.appendChild(foodDetails);
    
        foodList.append(foodCard);
    }
    
}

function deleteFood() {
    axios.delete("http://localhost:8080/winepairingapp/deleteFood")
    .then ((response) => {
        removeFood(response.data);
    }).catch ((error) => {
        console.error(error);
    })
}

function removeFood() {

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

