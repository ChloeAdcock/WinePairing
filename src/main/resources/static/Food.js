const PORT = 8081;

let allFoods = [];
function getAllFoods() {
    axios.get("http://localhost:" + PORT + "/food/getFoods")
    .then ((response) => {
        allFoods = response.data;
        console.log(allFoods)
        showAllFoods();
    }).catch ((error) => {
        console.error(error);
    })
}

const foodList = document.getElementById("foods");

function showAllFoods(foods) {

    for (let food of foods) {
    
        const foodCard = document.createElement("div");
        foodCard.className = "card m-5";
    
        const cardBody = document.createElement("div");
        cardBody.className = "card-body";
        foodCard.appendChild(cardBody);
    
        const foodName = document.createElement("h5");
        foodName.className = "card-title";
        foodName.innerText = food.name;
        cardBody.appendChild(foodName);
    
        const foodDetails = document.createElement("p");
        foodDetails.className = "card-details"
        foodDetails.innerText = "Name" + food.name + "\n Allergens" + food.allergens + "\n Description" + food.description
        + "\n Wine pairing" + food.wine;
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

