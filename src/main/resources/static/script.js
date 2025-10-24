//upload portion
const uploadArea = document.getElementById("uploadArea")
const fileUploadInput = document.getElementById("fileInput")
const uploadBtn = document.getElementById("confirmUpload")

uploadBtn.addEventListener("click", async()=> {
	if(fileUploadInput.files.length===0) return;

	const file = fileUploadInput.files[0];

	const formData = new FormData();
	formData.append('file',file);

	//TODO !!!! discern filetype for endpoint mux, optionally add in md. annotations here
	const resp = await fetch('/api/upload/audio', {
		method: 'POST',
		body: formData,
	});

	const result = await resp.json();
	console.log(result);
});

// dropdown view db portion
const dropDownBtn = document.getElementById("dropdownBtn")
const dropDownContent = document.getElementById("dropdownContent")

let isOpen = false;

// Toggle dd vis when button is clicked
dropDownBtn.addEventListener("click",async()=> {
	if(!isOpen) {
		const res = await fetch("/api/files");
		const files = await res.json();

		//manipulate the innerHTML to reflect the db
		//clear
		dropDownContent.innerHTML = "";
		
		//populate with names from db
		files.forEach(file=> {
			const item = document.createElement("div");
			item.textContent = file.filename;

			//on click close menu and show selection
			item.addEventListener("click",async()=> {
				dropDownBtn.textContent = file.filename + " ▼";
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

//view panel
const viewPanel = document.getElementById("playbackArea")
