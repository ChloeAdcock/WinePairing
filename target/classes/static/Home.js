function wineRequired(){
    let nameInput = document.getElementById("inputWineName");
    let nameText = document.getElementById("wineNameHelp");
    if (nameInput.value.length == 0) {
        nameText.innerText = "Please enter a wine name";
        nameInput.className = "required";
        nameText.className = "requiredText";
        return false;
    }
    return true;
}

function maxLengthWine() {
    let nameInput = document.getElementById("inputWineName");
    let grapeInput = document.getElementById("inputGrape");
    let descriptionInput = document.getElementById("inputWineDescription");
    let tastingNotesInput = document.getElementById("inputTastingNotes");

    let nameText = document.getElementById("wineNameHelp");
    let grapeText = document.getElementById("grapeHelp");
    let descriptionText = document.getElementById("wineDescriptionHelp");
    let tastingNotesText = document.getElementById("tastingNotesHelp");

    let valid = true;

    if (nameInput.value.length > 100) {
        nameInput.className = "required";
        nameText.className = "requiredText";
        valid = valid && false;
    }
    
    if (grapeInput.value.length > 100) {
        grapeInput.className = "required";
        grapeText.className = "requiredText";
        valid = valid && false;
    }
    
    if (descriptionInput.value.length > 100) {
        descriptionInput.className= "required";
        descriptionText.className = "requiredText";
        valid = valid && false;
    }
    
    if (tastingNotesInput.value.length > 100) {
        tastingNotesInput.className = "required";
        tastingNotesText.className = "requiredText";
        valid = valid && false;
    }
    return valid;
}

function foodRequired(){
    let nameInput = document.getElementById("inputFoodName");
    let text = document.getElementById("foodNameHelp");
    if (nameInput.value.length == 0) {
        text.innerText = "Please enter a food name";
        nameInput.className = "required";
        text.className = "requiredText";
        return false;
    }
    return true;
}

function maxLengthFood() {
    let nameInput = document.getElementById("inputFoodName");
    let allergensInput = document.getElementById("inputAllergens");
    let descriptionInput = document.getElementById("inputFoodDescription");

    let nameText = document.getElementById("foodNameHelp");
    let allergensText = document.getElementById("allergensHelp");
    let descriptionText = document.getElementById("foodDescriptionHelp");

    let valid = true;

    if (nameInput.value.length > 100) {
        nameInput.className = "required";
        nameText.className = "requiredText";
        valid = valid && false;
    }
    
    if (allergensInput.value.length > 100) {
        allergensInput.className = "required";
        allergensText.className = "requiredText";
        valid = valid && false;
    }
    
    if (descriptionInput.value.length > 100) {
        descriptionInput.className = "required";
        descriptionText.className = "requiredText";
        valid = valid && false;
    }
    return valid;
}

function addWine() {
        let wineName = document.getElementById('inputWineName').value;
        let wineGrape = document.getElementById('inputGrape').value;
        let wineDescription = document.getElementById('inputWineDescription').value;
        let tastingNotes = document.getElementById('inputTastingNotes').value;

        const data = {
            "name": wineName,
            "grape": wineGrape,
            "description": wineDescription,
            "tastingNotes": tastingNotes
        }
        
        if (wineRequired() == true && maxLengthWine() == true) {
        axios.post("/WinePairing/wine/addWine/", data)     
        .then ((response) => {
            console.log("post wines request: " + data)
            location.reload();
        }).catch ((error) => {
            console.error(error);
        } 
 )
}
}

let wines = [];
 function getWines() {
    axios.get("/WinePairing/wine/getWines/")
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
    
    if (foodRequired() == true && maxLengthFood() == true) {
    axios.post("/WinePairing/food/addFood?id=" + winePairing, data)     
    .then ((response) => {
        console.log("post foods request: " + data)
        location.reload();  
    }).catch ((error) => {
        console.error(error);
    }
)}
}


