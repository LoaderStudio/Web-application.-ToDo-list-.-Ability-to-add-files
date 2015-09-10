function sendTimeForm(sReference) {
	document.timeForm.reference.value = sReference;
	document.timeForm.submit();
}

function sendTaskForm(sReference) {
	document.taskForm.reference.value = sReference;
	document.taskForm.submit();
}

function showFun(sReference) {
	document.getElementById('file').name = sReference;
	var myBlock = document.getElementById('uploadBlock');
	if (myBlock.style.display == 'none') {
		myBlock.style.display = 'inline';
	} else {
		myBlock.style.display = 'none';
	}
}

function sendUploadForm() {
	document.uploadForm.submit();
}

function sendDownloadForm(sReference) {
	document.sendDownloadForm.reference.value = sReference;
	document.sendDownloadForm.submit();
}

function removeFun(sReference) {
	document.removeForm.idFile.value = sReference;
	document.removeForm.submit();
}