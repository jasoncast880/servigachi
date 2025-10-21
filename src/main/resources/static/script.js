const uploadArea = document.getElementById("uploadArea")
const fileUploadInput = document.getElementById("fileInput")
const uploadBtn = document.getElementById("confirmUpload")

uploadBtn.addEventListener("click", async()=> {
	if(fileUploadInput.files.length===0) return;

	const file = fileUploadInput.files[0];

	const formData = new FormData();
	formData.append('file',file);

	const resp = await fetch('/api/upload/audio', {
		method: 'POST',
		body: formData,
	});

	const result = await resp.json();
	console.log(result);
});

//

const dropDownBtn = document.getElementById("dropdownBtn")
const dropDownContent = document.getElementById("dropdownContent")

let isOpen = false;

// Toggle dd vis when button is clicked
dropDownBtn.addEventListener("click",async()=> {
	if(!isOpen) {
		const res = await fetch("/api/");
		const files = await res.json();

		//manipulate the innerHTML to reflect the db
		//clear
		dropDownContent.innerHTML = "";
		
		//populate with names from db
		files.forEach(file=> {
			const item = document.createElement("div");
			item.textContent = file.name;

			//on click close menu and show selection
			item.addEventListener("click",async()=> {
				dropDownBtn.textContent = file.name + " ▼";
				dropDownContent.style.display = "none";
				isOpen = false;
			});

			dropDownContent.appendChild(item);
		});
		dropDownContent.style.display = "block";
		isOpen = true;
	} else {
		dropDownContent.style.display = "none";
		isOpen = false;
	}
});
