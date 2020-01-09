function alphanumeric(inputText) {
    var letters = /^[0-9a-zA-Z]+$/;
    if(inputtxt.value.match(letters)){
        return inputText;
    }
    else {
        return null;
        alert('Please input alphanumeric characters only');
    }
}

const PORT = 8081;

function addWine() {
        var wineName = document.getElementById('inputWineName').value;
        var wineGrape = document.getElementById('inputGrape').value;
        var wineDescription = document.getElementById('inputWineDescription').value;
        var tastingNotes = document.getElementById('inputTastingNotes').value;

        const data = {
            "name": wineName,
            "grape": wineGrape,
            "description": wineDescription,
            "tastingNotes": tastingNotes
        }
        
        axios.post("http://localhost:" + PORT + "/wine/addWine", data)     
        .then ((response) => {
            console.log("post wines request: "+data)
            location.reload();
        }).catch ((error) => {
            console.error(error);
        }   
 )
}

let wines = [];
 function getWines() {
    axios.get("http://localhost:" + PORT + "/wine/getWines")
    .then ((response) => {
        wines = response.data;
        console.log("Get wines request: " + wines)
        showWineNames();
    }).catch ((error) => {
        console.error(error);
    })
}

const wineList = document.getElementById("wineDropdown");

function showWineNames() {
    
    for (let wine of wines) {
        const newWine = document.createElement("option");
         newWine.innerText = wine.name;
         newWine.value = wine.id;
         wineList.appendChild(newWine);
    }
}

function addFood() {
    var foodName = document.getElementById('inputFoodName').value;
    var allergens = document.getElementById('inputAllergens').value;
    var foodDescription = document.getElementById('inputFoodDescription').value;
    var winePairing = document.getElementById('wineDropdown').value;

    const data = {
        "name": foodName,
        "allergens": allergens,
        "description": foodDescription,
        "wine": {
            "id": winePairing
        }
    }
    
    axios.post("http://localhost:" + PORT + "/food/addFood", data)     
    .then (console.log(data))   
    .catch ((error) => {
        console.error(error);
    }
)}


